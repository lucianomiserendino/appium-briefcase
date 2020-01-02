@AMB @Regression @AMB-1301
Feature: Verify data is displayed on the Dashboard after tapping the left-hand navigation expand/collapse arrows 

Scenario: 
	Given user is logged into Briefcase 
		|environment|userName         |password |server|
		|INTEGRATION|chambers courtney|Test2023!|CMKA  |
	Then user taps on left-hand navigation "Expand" arrows 
	And User Observes the referral categories that display on "CMKA" and on the dashboard using  "Colloton" and "jud". 
	Then user taps on left-hand navigation "Expand" arrows 
	And User Observes the referral categories that display on "CMKA" and on the dashboard using  "Colloton" and "jud". 
	