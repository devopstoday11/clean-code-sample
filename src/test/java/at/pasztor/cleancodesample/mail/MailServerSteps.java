package at.pasztor.cleancodesample.mail;

import at.pasztor.cleancodesample.registration.RegistrationSteps;
import at.pasztor.cleancodesample.variable.VariableStorage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static junit.framework.TestCase.assertTrue;

public class MailServerSteps {
    private final MailStorage mailStorage;
    private final VariableStorage variableStorage;
    private static final Pattern textLinkPattern = Pattern.compile("(http(s|)://([^\\s]*))");
    private static final Pattern htmlLinkPattern = Pattern.compile("href=\"([^\"]*)\"");

    @Autowired
    public MailServerSteps(
        MailStorage mailStorage,
        VariableStorage variableStorage
    ) {
        this.mailStorage = mailStorage;
        this.variableStorage = variableStorage;
    }


    @Given("^I received a verification e-mail to that e-mail address(?:|,|\\.)$")
    @When("^I receive a verification e-mail to that e-mail address(?:|,|\\.)$")
    @Then("^I should receive a verification e-mail to that e-mail address(?:|,|\\.)$")
    public void shouldReceiveVerificationEmail() throws IOException, MessagingException {
        final String email = variableStorage.get(RegistrationSteps.VARIABLE_REGISTRATION_EMAIL);
        assertTrue("No e-mail received to the e-mail just registered", mailStorage.read(email).size() > 0);
        final Message lastEmail = mailStorage.read(email)
                .get(mailStorage.read(email)
                        .size() - 1);
        assertTrue("No link found", findLink(RegistrationSteps.PATTERN_VERIFICATION_LINK, lastEmail.message));
    }

    private boolean findLink(Pattern linkPattern, Part message) throws MessagingException, IOException {
        if (message.getContentType().startsWith("text/html") || message.getContentType().startsWith("text/plain")) {
            //Handle e-mail body
            String content = (String) message.getContent();
            Matcher matcher;
            if (message.getContentType().startsWith("text/html")) {
                matcher = htmlLinkPattern.matcher(content);
            } else if (message.getContentType().startsWith("text/plain")) {
                matcher = textLinkPattern.matcher(content);
            } else {
                return false;
            }

            while (matcher.find()) {
                String match = matcher.group(1);
                if (message.getContentType().startsWith("text/html")) {
                    match = StringEscapeUtils.escapeHtml4(match);
                }
                Matcher linkMatcher = linkPattern.matcher(match);
                if (linkMatcher.matches()) {
                    return true;
                }
            }
        } else if (message.getContentType().startsWith("multipart/")) {
            //Handle multipart e-mail
            MimeMultipart multipart = (MimeMultipart) message.getContent();
            for (int partIndex = 0; partIndex < multipart.getCount(); partIndex++) {
                BodyPart part = multipart.getBodyPart(partIndex);
                if (findLink(linkPattern, part)) {
                    return true;
                }
            }
        }
        return false;
    }
}
