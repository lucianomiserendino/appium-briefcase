@Regression @AMB-3654
Feature: chmSilentAssignText TPF - Docket Text

@smoke
Scenario: 
Verify the chmSilentAssignText TPF enables the dictionary designer to contribute information in docket text from the chmSilentAssign DPF.
Note:  To view docket entries, go to a case referral, tap View Case Info, then tap Docket Entries. (line 1266)
       
		Given I am logged into Briefcase 
		|environment|user    |
		|test       |sysadmin|
		
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |sysadmin |
		
   Then User selects a random category
			
   Then User selects a random case
		
	Then User expands/collapse panel
	Then view docket entries
    	