@Regression @updated @smoke
Feature: Site table variable 'briefcaseDisplayTools'
@AMB-4002 @AMB-4003 @AMB-4004


Scenario:
The 'briefcaseDisplayTools' site table variable is used to enable/disable the Tools category in Briefcase. 
In order to enable the this category, set the site table variable to 'y', to disable set it to 'n'

	Then User gets the si_value from the site table
	|si_value      |courtId    |
	|displayTools  |test       |
  
		Given I am logged into Briefcase 
		|environment|user    |
		|test       |sysadmin|
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |sysadmin |
			

	Then Verify the site table variable 'briefcaseDisplayTools' is being honored

    And Verify that tapping "Apply All" without existing clerk generate a message
				
		