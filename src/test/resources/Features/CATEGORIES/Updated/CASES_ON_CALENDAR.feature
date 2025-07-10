@Regression
Feature:  Collapsible Panels in the Cases on Calendar Category
@AMB-1249 @AMB-3196 @AMB-3275 @AMB-3284


Scenario: As a user when viewing the 'Cases on Calendar' referral category the following is displayed:
#Screen 1:
#Month/Year Header
#Date and Day of the week  under header that are accordions
#When the user expands the accordion for a date the following is displayed
#1st line:  Case number, Case Title
#2nd line: Panel, Order, Time
#Current sorting of cases for each day will remain (time and case)
#The user is able to click on the case directly from the expanded accordion to access the case.

		#Given I am logged into Briefcase 
		#|environment|user    |
		#|test       |sysadmin|
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |sysadmin |
		
        When User selects a  "Case on Calendar" 
        
       And User verifies that the main headers display Month Year and sorted accordingly
        
       Then User verifies that there're Dates and Day of the week under main header
        
       When accordion for a date are expanded User should see Case number, Case Title, Panel, also Order