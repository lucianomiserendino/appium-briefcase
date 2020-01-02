@AMB @Smoke @AMB-1008
Feature: Pending Tasks Category Displays on the Dashboard 

 
Scenario Outline: 
	A category entitled "Pending Tasks" will display on the dashboard if the judge has 
	any pending assignments and the site table variable briefcaseShowPendingTasks ='y'. 
	
	Given user is logged into Briefcase 
		|environment|userName         |password |server|
		|INTEGRATION|chambers courtney|Test2023!|CMKA  |
	Given If The judge has any pending assignments it will validate the total num of pending task on UI with DB "<dbtype>" . Use  judge's "<pe_id>" and  "<PE_RT_CODE>" to retrieve pending tasks from db 
	
	Examples: 
		|pe_id        |dbtype|PE_RT_CODE|
		|Colloton     |CMKA  |jud       |
		
		