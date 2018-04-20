Feature: DB & UI validation Smoke Tests


Background: 

	Given  User Navigates to Sever 
	When  User enters Crdenetials to Login 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And After user navigates to Appellate DC Development - CMKA - dev 
	
	
     @AMB_956
Scenario: 
     Referral categories display on the dashboard for the judge
     
	Given User Observes the referral categories that display on the dashboard and DB 
	
	
	@AMB_973
Scenario: 
	Tapping on a referral category that is not orally argued (chm_reftype_val.cdv_is_oral_arg='n'),
    a list of cases should dipslay for the judge for that category.
    Need to verify the correct number of referrals are being displayed.Verify if PDF document can be downloaded from the server to Briefcase and viewed within Briefcase
   

    Given  User clicks on Motions/Pettitions 
	Then finds the valid non-orally argued categories for the judge 
	Then User selects case "18-12418"
	And User taps on pdf doc in cmecf and verifies that it is downloaded from the server and opens in Briefcase

	
	
	
