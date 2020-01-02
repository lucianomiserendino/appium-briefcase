@AMB @AMB-1241 @Regression
Feature: Applied Cases link 


Scenario: 
	Display Applied Cases link  when bookmarking case/referral
	Given user is logged into Briefcase 
		|environment|userName|password |courtId|
		|INTEGRATION|s haenni|Test2022!|CMKA  |
	Then User selects a userCategory "Appellate_Judges" and  name "Colloton" 
	Then user checks if "15-2622" is bookmarked 
	When User selects a  "Cases on Calendar" 
	And User selects "Feb 08, 2016 - Feb 12, 2016" for panel "LRS*, SMC, RRE" 
	Then User verifies there is a link icon next to case "15-2622" . Selects that case and bookmark the case on the referral document page 
	Then User  goes back to the "Cases on Calendar" page for "Feb 08, 2016 - Feb 12, 2016" for panel "LRS*, SMC, RRE" and verifies the link is there. 

	 