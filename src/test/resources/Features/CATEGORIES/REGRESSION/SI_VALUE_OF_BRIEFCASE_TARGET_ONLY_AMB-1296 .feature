@AMB-1399
Feature: Display referrals when site var briefcaseTargetOnly = n 




Scenario Outline: 
	Verify when an action is selected that contains the note DPF, the note DPF UI displays.
	
	Given User sets the "<si_code>" site var to "<si_val>" on "<server>" 
		
			Given I am logged into Briefcase 
		|environment|userName| password |courtId|
		|test       |test    | test     |test   |
		
	Then I select a user 
		|userType   |personrole        |jud     |
		|judge      |Appellate Judges  |Benton  |
		
	Then User selects "<category>" and "<caseNum>" 
	#Then User verifies "<panel>" panel is displayed and expands the  panel 
	Then User observes a collapsible panel entitled "<panel>" displays 
	
	Then user observes all reliefs display under Vote Information, use DBType "<server>" , "<coulumName>" , "<caseNum>" , "<pe_id>" , "<cmr_cyv_code>" 
	
	Examples: 
	
		|server      | si_code            |si_val   | category |caseNum   |panel            |coulumName|pe_id|cmr_cyv_code|
		|CMKA        |briefcaseTargetOnly | y       |MOTION    | 19-11519 |Vote_Information |cmr_ccr_id|34   |motpet      |
		|CMKA        |briefcaseTargetOnly | n       |MOTION    | 19-11519 |Vote_Information |cmr_ccr_id|34   |motpet      |
		
		
		
