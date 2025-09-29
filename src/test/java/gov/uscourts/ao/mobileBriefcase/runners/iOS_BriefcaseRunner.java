package gov.uscourts.ao.mobileBriefcase.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {
        "pretty",
        "html:target/cucumber-html-report.html",
        "json:target/cucumber.json"
    },
    features = "./src/test/resources/Features",
    glue = "gov.uscourts.ao.mobileBriefcase.stepDefinitions", tags = "@AMB-1189",
    dryRun = false
)
public class iOS_BriefcaseRunner {
}
