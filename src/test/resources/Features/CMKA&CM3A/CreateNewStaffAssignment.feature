Feature: Display of Assignment Notes 


@Smoke
@AMB-1123 
@AMB-1137
@AMB-1170
Scenario Outline: 
	This task is to verify that a chambers user is able to create a new staff assignment.And verify a chambers user can edit existing staff assignments
	Given User creates a new case
	Given  User Navigates to  "<environment>" environment 
	When  User enters Credentials to Login "<userName>" and "<password>" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "<server>" 
	When User selects a Judge, category: "<category>" and case 
	Then User  selects action using dbType "<dbType>" and  "<actionElID>"  and verifies the name of the action displays in the dark blue banner 
	Then User  verifies the "Assignments"  in a light blue banner , "New Staff Assignment"  and "Submit" button display on the page 
	When User taps on "New Staff Assignment" it  will display a new page. Verify page is entitled "Create Assignment" 
	And User verifies a label "Staff Member" and "Please Select" is  displayed next to the  drop-down that contains a list of staff and click on it 
	Then User verifies when tapping the drop-down a popup displays a list of staff based on the screen parameter in the DPF, "<dbType>" , "<actionElID>" , "<cha_ju_pe_id>" 
	Then user verifies  a label "Assignment" is  displayed  under the staff drop-down , "Please Select" is the default value and clicks on it 
	And User verifies that when you tap the Please Select button next to the Assignment label, a pop-up displays with valid assignment types.  , "<dbType>" and "<actionElID>" 
	Then User verifies that  A date field "Assigned" will be displayed for each value followed by "Select Date".  Verifies when tapping a the date field, that a calendar pop-ups and today's date is selected by default.  Select a date and verify the date field is populated with the date 
	Then User verifies that  A date field "Assignment Due" is displayed, followed by "Select Date".  Verifies when tapping a the date field, that a calendar pop-ups . Select a date and verify the date field is populated with the date
	And User verifies "Comment"  is displayed under the assignment  date type and a text area displays next to the label enabling the user to enter notes about the assignment. Then  User verifies  "Apply" button displays . If the user clicks the Apply button, the popup will close and the new assignment will display on the chmAssign DPF screen. And User verifies  "Cancel" button displays. If the user clicks Cancel, the popup will close and no data will be saved. 
	Then User clicks on "Apply" and then "Submit" button and verifies that beck end "<dbType>" is updated correctly using "<cha_ju_pe_id>" ,"<actionElID>"
	
	
	Examples: 
		|environment   |userName          |password  |server                                    |category                |dbType |case     |actionElID   |cha_ju_pe_id|cmr_cs_caseid|dbtype|
		|Integration   |judge werner      |Test2018! |Appellate DC Development - CMKA           |Test Automation         |CMKA   |18-8339  |3145         |34          |82271       |CMKA  |
		
		