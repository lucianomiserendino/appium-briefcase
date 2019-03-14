Feature: Display reliefs in Vote Info panel/judgeVote DPF for applied cases



@AMB-1292
Scenario: 
	Given  User Navigates to  "INTEGRATION" environment 
	When  User enters Credentials to Login "judge werner" and "Test2019!" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "CMKA" 
	Then User selects a userCategory "Appellate_Judges" and  name "Benton" 
	Then User selects "MOTIONS_PETITIONS" and "18-12418" 