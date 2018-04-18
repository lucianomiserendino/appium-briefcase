package gov.uscourts.ao.mobileBriefcase.runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "./src/test/resources/Featues", 
                 glue = {"gov/uscourts/ao/mobileBriefcase/stepDefinitions" }, 
                 tags = { "@AMB_973" }, 
                 dryRun = false)

public class BriefcaseRunner {

}
