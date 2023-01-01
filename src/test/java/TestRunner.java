import _base.BaseRunner;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/test/java/features"},
        glue = {"steps"},
        plugin = {"pretty", "json:target/cucumber/cucumber.json"},
        tags = "@api.profilemanagement")
public class TestRunner extends BaseRunner {
}
