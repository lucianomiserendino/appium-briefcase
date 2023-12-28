Feature: Common 
 


 @login
 Scenario: Login Functionality
	Given I am logged into Briefcase 
		|environment|sysadminUserName| sysadminPassword |courtId|judgeUserName|judgePassword|user|
		|test       |test            | test             |test   |test         |test         |test|
	
	 
	 @selectSTF  
Scenario: User	selection
		Then I select a user 
		|userType   |personrole        |stf  |user|
		|stf        |Staff Attorneys   |test |test|
		
		
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
		Then User closes and reopens the app

        
 @selectRandomTargetCase  
      Scenario: Random target case selection    
        Then User selects random target case
        
        
 @selectRandomAppliedCase  
      Scenario: Random applied case selection    
        Then User selects random applied case
        
   @selectRandomDocument
         Scenario: Random document selection    
        Then User downloads/opens a random document from Case Detail page 

 @expandPanel
   Scenario: Expand group icon
    Then User expands/collapse panel
    
     @collapsePanel
      Scenario: Collapse group icon
      Then User collapses panel
      
      
      
   @selectSTFSubCategoryandCase
Scenario: STF sub Category and case selection
	Then User selects sub Category
	|courtId|
	|test   |
	Then User selects a random stf Referral
	
@viewDocEntry
Scenario: View docket entry
	Then User navigates to View Case Info, then taps Docket Entries
	|test|
	
	@caseSync
	Scenario: Case Detail Page Sync
	Then sync all the documents for the selected case
	
	
@selectAction
Scenario: Selecting briefcase action
	Then User selects action
	|dpf       |courtId    |
	|chmAssign |test       |
	
	
@si_val
Scenario: Selecting si_value from site table
	Then User gets the si_value from the site table
	|si_value  |courtId    |
	|val       |test       |
        