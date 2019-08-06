Feature: Staff Assignments 




@AMB-1021 
Scenario Outline: 
	Staff members can be assigned to referrals and/or just cases. 
	 This task is to verify that staff assignments are displaying on the referral list page
	Given  User Navigates to  "INTEGRATION" environment 
	When  User enters Credentials to Login "s haenni" and "Test2022!" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "CMKA" 
	Then User selects a userCategory "Appellate_Judges" and  name "Colloton" 
	Then  User selects "<refCat>" and "<caseNum>" 
	Then User observes a collapsible panel entitled "Assignments" displays 
	And User verifies the staff assignments associated with the referral by using "<dbType>" , "<cmr_cs_caseid>" , "<cha_ju_pe_id>" , "<cmr_cyv_code>" , 
	Examples: 
		|dbType |cha_ju_pe_id|cmr_cs_caseid|dbType|cmr_cyv_code|caseNum|refCat            |
		|CMKA   |32          |82226        |CMKA  |motpet      |15-3314|MOTIONS_PETITIONS |
		
		
		
		@AMB-1030,AMB-1033 
		Scenario Outline: 
			If a judge or staff assignment is selected, a new screen displays with the following information:
			1.  Name of the assignee
2.  Type of assignment
3.  Latest assignment date
4.  Referral (relief) to which the assignment is attached
5.  Assignment types and dates
6.  Assignment notes
			Given User gets judge's/staff assignment's info from DataBase  by using "<dbType>" , "<caseNum>" , "<cha_ju_pe_id>" , "<cmr_cyv_code>" 
			Given  User Navigates to  "INTEGRATION" environment 
			When  User enters Credentials to Login "s haenni" and "Test2021!" 
			And User clicks on Send Key to Device 
			Then User navigates to MobileBrifcase App 
			And  user selects a "CMKA" 
			Then User selects a userCategory "Appellate_Judges" and  name "Colloton" 
			Then  User selects "<refCat>" and "<caseNum>" 
			Then User observes a collapsible panel entitled "Assignments" displays 
			And User selects a judge or staff assignment  and verifies the information and notes that display on the page 
			Examples: 
				|dbType |cha_ju_pe_id|cmr_cs_caseid|dbType|cmr_cyv_code|caseNum|refCat            |
				|CMKA   |32          |82226        |CMKA  |motpet      |15-3314|MOTIONS_PETITIONS |
				
		