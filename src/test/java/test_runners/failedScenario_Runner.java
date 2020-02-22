package test_runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        features = "@target/rerun.txt",// this is the locations for failing sceniarios or cases
        glue = "step_definitions",
        plugin = {
                "pretty",
                "html:target/cucumber-failed-reports/BookingPage-report",// change locations where is fail location
                "json:target/cucumber-failed-reports/cucumberFailedTests.json",
                "rerun:target/rerun.txt"// this is for failing test cases on TARGER FOLDER
        },
        tags = {"@ff"},
        dryRun = false
)
public class failedScenario_Runner {

}
