@Regression @AMB-3654
Feature: chmSilentAssignText TPF - Docket Text

@Smoke @AMB-3654
Scenario: 
Verify the chmSilentAssignText TPF enables the dictionary designer to contribute information in docket text from the chmSilentAssign DPF.
Note:  To view docket entries, go to a case referral, tap View Case Info, then tap Docket Entries. (line 1266)
       
		#Given I am logged into Briefcase 
	#|environment|userName| password |courtId|
	#|test       |test    | test     |test   |
		
		Then I select a user 
		|userType   |personrole        |jud      |
		|judge      |Appellate Judges  |test     |
		
		Then User selects random judge category
		|courtId|
		|test   |
			
	Then User selects random case number
		|courtId|
		|test   |
		
	Then User expands/collapse panel
	Then view docket entries
    	