Feature: Display of Staff Assignments 


Background: 

	Given  User Navigates to Sever 
	When  User enters Credentials to Login 
        |userName			|password	|
		|SysadminKasabolotova|Asalta6268!z|
	
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And After user navigates to Appellate DC Development - CMKA - dev
		  
    Given User selects Judge Colloton >> Motions/Petitions >> case "15-3314" 
	When User  observes a collapsible panel entitled "Assignments" displays and expands the Assignments panel
	


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
	
	
	
	

	
	
	
