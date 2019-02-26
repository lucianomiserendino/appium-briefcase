Feature: Pending Tasks Category Displays on the Dashboard 


@Smoke 
@AMB-1008 
Scenario Outline: 
	A category entitled "Pending Tasks" will display on the dashboard if the judge has 
	any pending assignments and the site table variable briefcaseShowPendingTasks ='y'. 
	
	Given  User Navigates to  "<environment>" environment 
	When  User enters Credentials to Login "<userName>" and "<password>" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "<server>" 
	Given If The judge has any pending assignments it will validate the total num of pending task on UI with DB "<dbtype>" . Use  judge's "<pe_id>" and  "<PE_RT_CODE>" to retrieve pending tasks from db 
	
	Examples: 
		|environment   |userName          |password  |server        |pe_id        |dbtype|PE_RT_CODE|
		|INTEGRATION   |chambers courtney |Test2022! |CMKA          |Colloton     |CMKA  |jud       |
		#|Testing       |judgewilliams   |Testpass1! |Appellate DC Installation Testing - CM3A  |2189563|CM3A  |
		
		