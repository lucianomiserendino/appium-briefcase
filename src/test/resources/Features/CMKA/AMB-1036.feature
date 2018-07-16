Feature: Display Vote Information Panel, filer info and judge's initials
         Display Actions Panel and actions

Background: 



		Given  User Navigates to environment 
	When  User enters Credentials to Login 
        |userName			|password	|
		|SysadminKasabolotova|Asalta6268!z|
	
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	Given  user selects a server "Appellate DC Development - CMKA" 
		
	And   User selects Judge Colloton  >> Petitions for Rehearing >> "15-3314"


@AMB_1036 
Scenario: 
If a referral requires voting, a collapsible Vote Information panel displays.  When expanded, it displays the following information:
1.  The name and party type of the person who filed the motion/petition
2.  The judges initials who are on the panel
3.  The relief on which the judge is ruling
4.  Each judge's vote and the date they voted

      
       Given User observes the "Vote Information" panel displays.   This should only display if the referral requires voting
       Then   for each referral, observes the filer's name  first initial of pr_middle_name gn_display, party type and date filed displays in a light blue heading. 
       And observes the judge's initials display in the same heading
       Then User checks each relief  that has been  displayed below the filer name.  
       Then  User checks each judge's vote  and the date  displays under their initials
    

@Regression
@AMB_1038
Scenario: 

       If there are records defined in the mbr_event table, 
       a collapsible panel entitled "Actions" should display,when expanded all the applicable actions should display.

      Given User verifies "Actions" is diplayed and  expands the Actions panel 
      Then  User verifies the correct actions display for the selected referral 





