Feature: Display of Assignment Notes 
Background: 

	Given  User Navigates to environment 
	When  User enters Credentials to Login 
		|userName			|password	|
		|chambers courtney  |Test2020!  |
		
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	
	
	
	
@Smoke
@AMB_1021
Scenario Outline: 
	Staff members can be assigned to referrals and/or just cases. 
    Verify that staff assignments are displaying on the referral list page.
	#This staff assignment is associated with the case.
	Given  user selects a "<server>" 
	When User selects Judge , "<category>" and  "<case>" 
	Then User  observes a collapsible panel entitled "Assignments" displays and expands the Assignments panel 
	And User observes there is an assignment for "<assignmnet>" , "<dbtype>",  where  cmr_cs_caseid equals"<cmr_cs_caseid>" ,cha_ju_pe_id equals "<cha_ju_pe_id>" and chd_cha_id equals "<chd_cha_id>"
	
	Examples: 
		|server                          |assignmnet          |category          |case    |cha_ju_pe_id|cmr_cs_caseid|dbtype|chd_cha_id|
		|Appellate DC Development - CMKA |Chambers Courtney   |Motions/Petitions |15-3314 |32          |82226        |CMKA  |2349|
		#|Appellate DC Development - CM5A |Allison Lawclerk   |Motion/Petition   |12-16637|2189563     |68765        |CM5A  |550|
		
	