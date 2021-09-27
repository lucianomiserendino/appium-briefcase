Feature: Verify Connection To DB Can Be Established 
@db_Check
Scenario: Verify Connection To Informix 
	And Verify DB Informix Connection Is Established
		|courtId     |db_servername|hostname      |db_port|db_Schema|db_username   |db_password|
		|test        |test           |test          |test   |test     |test          |test       |
		
		
