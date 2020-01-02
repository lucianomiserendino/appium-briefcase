@AMB @Regression @AMB-1296 
Feature: Display referrals when site var briefcaseTargetOnly = n 




Scenario Outline: 
	Verify when an action is selected that contains the note DPF, the note DPF UI displays.
	
	Given User sets the "<si_code>" site var to "<si_val>" on "<server>" 
	Given user is logged into Briefcase 
		|environment|userName     |password |server|
		|INTEGRATION|judge werner |Test2020!|CMKA  |
	Then User selects "<category>" and "<caseNum>" 
	Then User verifies "<panel>" panel is displayed and expands the  panel 
	Then user observes all reliefs display under Vote Information, use DBType "<server>" , "<coulumName>" , "<caseNum>" , "<pe_id>" , "<cmr_cyv_code>" 
	
	Examples: 
	
		|environment |server      | si_code            |si_val   | category         |caseNum   |panel            |coulumName|pe_id|cmr_cyv_code|
		|INTEGRATION |CMKA        |briefcaseTargetOnly | y       |MOTIONS_PETITIONS | 19-11519 |Vote_Information |cmr_ccr_id|34   |motpet      |
		|INTEGRATION |CMKA        |briefcaseTargetOnly | n       |MOTIONS_PETITIONS | 19-11519 |Vote_Information |cmr_ccr_id|34   |motpet      |
		
		
		
