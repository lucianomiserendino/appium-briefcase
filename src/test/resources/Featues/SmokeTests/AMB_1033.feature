Feature:  Display of Assignment Notes
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
	Then User  observes a collapsible panel entitled "Assignments" displays and expands the Assignments panel
	

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
   Then User verifies the date of the assignment note displays,left justified
   And  User verifies the note description displays next to the date
   Then User verifies text displays under the description

	