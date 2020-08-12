package at.pasztor.cleancodesample.registration;

import at.pasztor.cleancodesample.user.api.UserCreateApi;
import at.pasztor.cleancodesample.variable.VariableStorage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.apache.commons.codec.binary.Base64;

import java.util.Random;
import java.util.regex.Pattern;

public class RegistrationSteps {
    public final static String  VARIABLE_REGISTRATION_EMAIL = "registration-email";
    public final static Pattern PATTERN_VERIFICATION_LINK   =
        Pattern.compile("^http(?:|s)://(?:[&/])/users/(.*)?verificationCode=(.*)$");

    private final VariableStorage variableStorage;

    public RegistrationSteps(final VariableStorage variableStorage) {
        this.variableStorage = variableStorage;
    }

    @Given("^I registered a user with a new e-mail address(?:|,|\\.)$")
    @When("^I register a user with a new e-mail address(?:|,|\\.)$")
    @Then("^I should be able to register a user with a new e-mail address(?:|,|\\.)$")
    public void register() {
        byte[] data = new byte[7];
        new Random().nextBytes(data);
        String email = Base64.encodeBase64String(data) + "@example.com";
        final String password = "asdfasdf";

        variableStorage.store(VARIABLE_REGISTRATION_EMAIL, email);

        final UserCreateApi.Request request = new UserCreateApi.Request(email, password);

        final HttpResponse<JsonNode> response = Unirest
            .post("https://localhost:8080/users")
            .header("Content-Type", "application/json")
            .accept("application/json")
            .body(request)
            .asJson();
    }
}
