package gov.uscourts.ao.mobileBriefcase.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "html:target/cucumber-html-report",
		"json:target/cucumber.json" }, features = "./src/test/resources/Features",
		glue = { "gov.uscourts.ao.mobileBriefcase.stepDefinitions"}, tags =  "@AMB-2960", dryRun = false)

public class iOS_BriefcaseRunner {

}
