Feature: Applied Cases link 

@CMKA 
@Regression 
@AMB-1241 
Scenario: 
	Display Applied Cases link  when bookmarking case/referral
	Given  User Navigates to  "INTEGRATION" environment 
	When  User enters Credentials to Login "s haenni" and "Test2021!" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "CMKA" 
	Then User selects a userCategory "Appellate_Judges" and  name "Colloton" 
	Then user checks if "15-2622" is bookmarked 
	When User selects a  "Cases on Calendar" 
	And User selects "Feb 08, 2016 - Feb 12, 2016" for panel "LRS*, SMC, RRE" 
	Then User verifies there is a link icon next to case "15-2622" . Selects that case and bookmark the case on the referral document page 
	Then User  goes back to the "Cases on Calendar" page for "Feb 08, 2016 - Feb 12, 2016" for panel "LRS*, SMC, RRE" and verifies the link is there. 
	