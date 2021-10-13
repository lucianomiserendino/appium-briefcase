@AMB-1008
Feature: Pending Tasks Category Displays on the Dashboard 

 #dependent on AMB-2300
Scenario Outline: 
	A category entitled "Pending Tasks" will display on the dashboard if the judge has 
	any pending assignments and the site table variable briefcaseShowPendingTasks ='y'. 
	
	
	Given I am logged into Briefcase 
		|environment|userName| password |courtId|
		|test       |test    | test     |test   |
		
	Then I select a user 
		|userType   |personrole        |jud     |
		|judge      |Appellate Judges  |test    |
	Given If The judge has any pending assignments it will validate the total num of pending task on UI with DB. Use  judge's "<pe_id>" and  "<PE_RT_CODE>" to retrieve pending tasks from db 
    |courtId|
	|test   |
	Examples: 
		|pe_id        |PE_RT_CODE|
		|Colloton     |jud       |
		
		