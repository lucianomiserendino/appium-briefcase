Feature: Display of Assignment Notes 

	
@Smoke
@AMB_1033 
   Scenario Outline: 
	Display of Assignment Notes.
    If a judge or staff assignment is selected and there are assignment 
    or assignment date notes attached to the assignment/date, the following information will display:
     1.A light blue banner with the heading "Assignment Notes"
     2.The date of the note
     3.The note description
     4.The text of the note
 

	Given  User Navigates to  "<environment>" environment 
	When  User enters Credentials to Login "<userName>" and "<password>" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "<server>" 
	When User selects Judge, then   "<refCat>" and  "<caseNum>" 
	Then User  observes a collapsible panel entitled "Assignments" displays and expands the assignments panel 
	Given User selects "Daniel Hay" 
	Then User verifies the "Assignment Notes" banner displays 
	Then User verifies the date of the assignment note displays,left justified 
	And  User verifies the note description displays next to the date 
	Then User verifies text displays under the description 
	
	
		Examples: 
		|environment   |userName          |password  |server                                   |refCat       | caseNum |dbType| 
		|Integration   |chambers courtney |Test2021!|Appellate DC Development - CMKA           |Motions/Petitions | 15-3314  |CMKA  |
	
	@Smoke	
	@AMB_1021
Scenario Outline: 
	Staff members can be assigned to referrals and/or just cases. 
    Verify that staff assignments are displaying on the referral list page.
    Staff assignments linked to the referral are displayed
	#These are staff assignments associated with the referral
	
	Given  User Navigates to  "<environment>" environment 
	When  User enters Credentials to Login "<userName>" and "<password>" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "<server>" 
	When User selects Judge , "<category>" and  "<case>" 
	Then User  observes a collapsible panel entitled "Assignments" displays and expands the Assignments panel 
	Then User observes there is an assignment for "Kyle Essley" and "Daniel Hay" 
	
	Examples: 
		|environment   |userName          |password  |server                                    |assignmnet         |category          |case    |
		|Integration   |chambers courtney |Test2021! |Appellate DC Development - CMKA           |Chambers Courtney  |Motions/Petitions |15-3314 |
		
	
	