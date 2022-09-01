Feature: Common 
 


 @login
 Scenario: Login Functionality
	Given I am logged into Briefcase 
		|environment|userName| password |courtId|
		|test       |test    | test     |test   |
	
	 
	 @selectSTF  
Scenario: User	selection
		Then I select a user 
		|userType   |personrole        |stf  |
		|stf        |Staff Attorneys   |test |
		
		
	 @selectJUD 
Scenario: User	selection
	Then I select a user 
		|userType   |personrole        |jud     |
		|judge      |Appellate Judges  |test    |
		

@selectCategoryandCase

Scenario: Category and case selection
	Then User selects
	|refCategory|caseNumber|
	|test       |test      |
	
	
@selectRandomStfCategory
	Scenario: Random category and case selection
	
   Then I select a user 
		|userType   |personrole        |stf  |
		|stf        |Staff Attorneys   |test |
	Then User selects random stf Aty category
		|courtId|
		|test   |
	
@selectRandomJudgeCategory
    Scenario: Random category and case selection
		Then I select a user 
		|userType   |personrole        |jud      |
		|judge      |Appellate Judges  |test     |
		
	Then User selects random judge category
		|courtId|
		|test   |
	
		
@selectRandomCase
	Scenario: Random case selection
		Then I select a user 
		|userType   |personrole        |jud      |
		|judge      |Appellate Judges  |test     |
		
		Then User selects random judge category
		|courtId|
		|test   |
			
	Then User selects random case number
		|courtId|
		|test   |
	
	
			
@reopenTheApp
	Scenario: Closing and reopening Briefcase
		Then I select a user 
		|userType   |personrole        |jud      |
		|judge      |Appellate Judges  |test     |
		Then User closes and reopens the app

@selectRandomDocument
     Scenario: Random document selection
        Then user selects random document
		
		