Feature: Display of Staff Assignments 


Background: 

	Given  User Navigates to Sever 
	When  User enters Credentials to Login 
	#Login as Chambers Courtney // Test2020!
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And After user navigates to Appellate DC Development - CMKA - dev 
	
	
	
@Smoke	
@AMB_1021 
Scenario: 
	Staff members can be assigned to referrals and/or just cases. 
    Verify that staff assignments are displaying on the referral list page.
    Staff assignments linked to the referral are displayed
    #These are staff assignments associated with the referral
    
    Given User selects Judge Colloton >> Motions/Petitions >> case "15-3314" 
	When User  observes a collapsible panel entitled "Assignments" displays. 
	Then User observes there is an assignment for "Kyle Essley" and "Daniel Hay" 
     #This staff assignment is associated with the case.
	And User observes there is an assignment for "Chambers Courtney" 
	
	
