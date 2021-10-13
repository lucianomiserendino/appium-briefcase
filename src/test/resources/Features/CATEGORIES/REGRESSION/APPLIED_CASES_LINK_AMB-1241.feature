@Regression @AMB-1241 
Feature: Applied Cases link 


Scenario: 
	Display Applied Cases link  when bookmarking case/referral
	Given I am logged into Briefcase 
		|environment|userName| password |courtId|
		|test       |test    | test     |test   |
		
	Then I select a user 
		|userType   |personrole        |jud     |
		|judge      |Appellate Judges  |test    |
	|Appellate Judges  |Colloton     |
	
	Then user checks if "15-2622" is bookmarked 
	When User selects a  "Cases on Calendar" 
	And User selects "Feb 09, 2016 - Feb 10, 2016" for panel "LRS*, SMC, RRE" 
	Then User verifies there is a link icon next to case "15-2622" . Selects that case and bookmark the case on the referral document page 
	Then User  goes back to the "Cases on Calendar" page for "Feb 09, 2016 - Feb 10, 2016" for panel "LRS*, SMC, RRE" and verifies the link is there. 

	#make sure the site table variable briefcaseTargetOnly ='y'