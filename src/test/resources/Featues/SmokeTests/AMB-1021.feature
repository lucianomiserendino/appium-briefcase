Feature: Display of Staff Assignments 


Background: 

	Given  User Navigates to Sever 
	When  User enters Credentials to Login 
        |userName			|password	|
		|SysadminKasabolotova|Asalta6268!z|
	
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And After user navigates to Appellate DC Development - CMKA - dev
	
		|userName			|password	|briefcaseUser|
		|SysadminKasabolotova|Asalta6268!z|appellateJudges|
		  
    Given User selects Judge Colloton >> Motions/Petitions >> case "15-3314" 
	When User  observes a collapsible panel entitled "Assignments" displays and expands the Assignments panel
	
	


@Smoke
@AMB_1021 
Scenario: 
	Staff members can be assigned to referrals and/or just cases. 
    Verify that staff assignments are displaying on the referral list page.
    Staff assignments linked to the referral are displayed
    #These are staff assignments associated with the referral
	Then User observes there is an assignment for "Kyle Essley" and "Daniel Hay" 
	

@Smoke
@AMB_1021 	

	

@AMB_1021	

	Scenario: 
	Staff members can be assigned to referrals and/or just cases. 
    Verify that staff assignments are displaying on the referral list page.
     #This staff assignment is associated with the case.
	And User observes there is an assignment for "Chambers Courtney" 
	

@AMB_1030	
Scenario: 
		Display assignment info when selecting an assignment
		If a judge or staff assignment is selected, a new screen displays with the following information:
		1.  Name of the assignee
        2.  Type of assignment
        3.  Latest assignment date
        4.  Referral (relief) to which the assignment is attached
        5.  Assignment types and dates


		
	Given User selects "Kyle Essley" and observes a new page displays and a light blue banner that contains the assignee's name,latest assignment date and assignment type
    And  Under the assignment type, is the relief for the referral to which the assignment is linked.
	
	
	
	
@AMB_1033	
Scenario: 	
	 Display of Assignment Notes.
    If a judge or staff assignment is selected and there are assignment 
    or assignment date notes attached to the assignment/date, the following information will display:
     1.A light blue banner with the heading "Assignment Notes"
     2.The date of the note
     3.The note description
     4.The text of the note
 
     
    Given User selects "Daniel Hay" 
   Then User verifies the "Assignment Notes" banner displays
   Then User verifies the note description displays next to the date.
   And  User verifies text displays under the description

	
	
	
	
