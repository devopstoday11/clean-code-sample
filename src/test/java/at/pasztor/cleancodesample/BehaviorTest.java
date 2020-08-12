package at.pasztor.cleancodesample;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "classpath:at/pasztor/cleancodesample/features",
    plugin = {
        "pretty",
        "html:target/cucumber-reports"
    }
)
public class BehaviorTest {

}
