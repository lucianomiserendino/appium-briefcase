@db_Check
Feature: Verify Connection To DB Can Be Established 

Scenario: Verify Connection To Informix 
	And Verify DB Informix Connection Is Established
		|courtId     |db_dbservername|hostname      |db_port|db_Schema|db_username   |db_password|
		|test        |test           |test          |test   |test     |test          |test       |
		#Then Verify DB Informix Connection Is Established With Appache BasicDataSource
		
		
@login 
Scenario: Login 
	Given I am logged into Briefcase 
		|environment    |userName| password |courtId|
		|Integration    |JAHaenni| Test2024!|test   |
		
	Then I select a user 
		|role              |briefcaseUser|
		|Appellate Judges  |Colloton     |