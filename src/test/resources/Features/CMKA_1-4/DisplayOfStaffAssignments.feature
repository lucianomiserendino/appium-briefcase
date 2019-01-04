Feature: Display of Assignments and Assignment Notes 

Background: 

	Given  User Navigates to  "INTEGRATION" environment 
	When  User enters Credentials to Login "s haenni" and "Test2020!" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "CMKA" 
	Then User selects a userCategory "Appellate_Judges" and  name "Colloton" 
	Then User selects "MOTIONS_PETITIONS" and "15-3314" 
	

@AMB-1021 
Scenario Outline: 
	Staff members can be assigned to referrals and/or just cases. 
    Verify that staff assignments are displaying on the referral list page.


	Then User  observes a collapsible panel entitled "Assignments" displays and expands the Assignments panel 
	#These staff assignments linked to the referral
	And User observes there is an assignment for 
	
	|assignment |
	|Kyle Essley|
	|Daniel Hay |
	
	#These are staff assignments associated with the referral
	#Then User observes there is an assignment for "Kyle Essley" and "Daniel Hay" 
	
	
	Examples: 
		|assignmnet         |category                |case    |cha_ju_pe_id|cmr_cs_caseid|dbtype|chd_cha_id|
		|Chambers Courtney  |Motions/Petitions       |15-3314 |32          |82226        |CMKA  |2349      |
		#|Allison Lawclerk   |Motion/Petition         |12-16637 |2189563     |68765        |CM5A  |550      |
		
		
		
		
		
	 
		@AMB-1033 
		Scenario Outline: 
			Display of Assignment Notes.
    If a judge or staff assignment is selected and there are assignment 
    or assignment date notes attached to the assignment/date, the following information will display:
     1.A light blue banner with the heading "Assignment Notes"
     2.The date of the note
     3.The note description
     4.The text of the note
 

			When User selects Judge,  "<category>" and  "<case>" 
			Then User  observes a collapsible panel entitled "Assignments" displays and expands the Assignments panel 
			Given User selects "Daniel Hay" 
			Then User verifies the "Assignment Notes" banner displays 
			Then User verifies the date of the assignment note displays,left justified 
			And  User verifies the note description displays next to the date 
			Then User verifies text displays under the description 
			
			
			Examples: 
				|category       | case |dbType| 
				|Motions/Petitions | 15-3314  |CMKA  |
				
