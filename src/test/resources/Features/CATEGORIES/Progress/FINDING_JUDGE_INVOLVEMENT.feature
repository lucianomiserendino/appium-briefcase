Feature: judge involvement

	
@AMB-3014
Scenario: 
     Find judge involvement via chm_mobile_referral.cmr_ic_code
     
	#Given I am logged into Briefcase 
	#|environment|userName| password |courtId|
	#|test       |test    | test     |test   |
		
		Then I select a user 
		|userType   |personrole        |jud      |
		|judge      |Appellate Judges  |test     |
		
	Then select a case that has involvement code
	
	Then Verify judge Involvement is displayed correctly

									
	
	