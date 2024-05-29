@AMB-1008
Feature: Pending Tasks Category Displays on the Dashboard 

 #dependent on AMB-2300
Scenario: 
	A category entitled "Pending Tasks" will display on the dashboard if the judge has 
	any pending assignments and the site table variable briefcaseShowPendingTasks ='y'. 
	
	
		Given I am logged into Briefcase 
		|environment|user    |
		|test       |sysadmin|
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |sysadmin |
		
	Given If The judge has any pending assignments it will validate the total num of pending task on UI with DB.
    |courtId|
	|test   |

		
		