package gov.uscourts.ao.mobileBriefcase.runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "html:target/cucumber-html-report",
		"json:target/cucumber.json" }, features = "./src/test/resources/Featues", glue = {
				"gov/uscourts/ao/mobileBriefcase/stepDefinitions" }, tags = {"@AMB_1008"}, dryRun = false)

public class BriefcaseRunner {
	




}
