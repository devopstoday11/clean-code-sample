# Start from an alpine OpenJDK
FROM openjdk:8-jdk-alpine

# Health check checks availability of the webserver.
RUN apk add --no-cache curl
HEALTHCHECK CMD curl http://127.0.0.1:8080/

# Create working directory
RUN mkdir /opt/app
WORKDIR /opt/app

# Document service exposure
EXPOSE 8080/tcp

# Document configuration variables
ENV USUMU_SECRET=""
ENV USUMU_INIT_VECTOR=""
ENV USUMU_S3_ACCESS_KEY_ID=""
ENV USUMU_S3_SECRET_ACCESS_KEY=""
ENV USUMU_S3_REGION=""
ENV USUMU_S3_BUCKET=""
ENV USUMU_S3_BUCKET_HOST=""

# Process build argument.
ARG VERSION
RUN if [[ -z "${VERSION}" ]]; then echo "The VERSION build argument has not been passed. Aborting." >&2; exit 1; fi

# Set image labels
LABEL maintainer="janos@pasztor.at"
LABEL version="${VERSION}"
LABEL description="A sample project for clean code in Spring Boot."

# Create entry point shell script such that the version can be dynamically passed.
ENTRYPOINT ["/init.sh"]
CMD []
RUN echo -e "#!/bin/sh\nexec /usr/bin/java -jar /opt/app/clean-code-sample-${VERSION}.jar $@" >/init.sh
RUN chmod +x /init.sh

# Copy compiled executable.
COPY target/api-${VERSION}.jar /opt/usumu/

# Run as nobody. The user is provided as a numeric ID so the non-root enforcement on Kubernetes works.
USER 65535

