package gov.uscourts.ao.mobileBriefcase.runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "html:target/cucumber",
		"json:target/cucumber.json" }, features = "./src/test/resources/Features", glue = {
				"gov.uscourts.ao.mobileBriefcase.stepDefinitions" }, tags = {"@smoke"}, dryRun = false)

public class iOS_BriefcaseRunner {

}
          


	