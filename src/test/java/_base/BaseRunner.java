package _base;

import _support.context.UserContext;
import core.Global;
import helper.PropertyHelper;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Properties;

public class BaseRunner {
    private static TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass
    public void setUp() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @BeforeMethod
    public void initScenario() {
        Global.setThreadLocalMap(new HashMap<>());
        Global.set(UserContext.class, new UserContext());
        Global.set(Properties.class, PropertyHelper.loadPropertiesByFilePath("src/main/resources/env.properties"));
    }

    @Test(groups = "cucumber scenarios", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void runScenarios(PickleWrapper pickleEvent, FeatureWrapper cucumberFeature) {
        testNGCucumberRunner.runScenario(pickleEvent.getPickle());
    }

    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return testNGCucumberRunner.provideScenarios();
    }

    @AfterClass
    public void tearDown() {
        testNGCucumberRunner.finish();
    }
}