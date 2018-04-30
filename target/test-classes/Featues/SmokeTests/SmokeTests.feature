Feature: DB & UI validation Smoke Tests 

Background: 
 
	Given  User Navigates to Sever 
	When  User enters Credentials to Login 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And After user navigates to Appellate DC Development - CMKA - dev 
	
@AMB_1010 
Scenario: 
	There is a sorting feature on the referral list page that enables users to sort referrals by date referred or case number.  
The default is by date referred in descending order (newest first). 

	Given User selects a judge and then the motions/petitions category 
	When User selects the sort button. User verifies the Date Down Arrow is selected by default and verifies the referrals are sorted by referred date in descending order (newest first). 
    And  User clicks the Date Up Arrow button and verifies the referrals are sorted by referred date in ascending order (oldest first).
	Then User clicks the Case Down Arrow button and  verifies the referrals are sorted by case number in descending order after User clicks the Case Up Arrow button, verifies the referrals are sorted by case number in ascending order.	
	
	
@AMB_956 
Scenario: 
	Referral categories display on the dashboard for the judge
	Given User Observes the referral categories that display on the dashboard and DB 
	
	
	@AMB_973
Scenario: 
	Tapping on a referral category that is not orally argued (chm_reftype_val.cdv_is_oral_arg='n'),
    a list of cases should dipslay for the judge for that category.
    Need to verify the correct number of referrals are being displayed.
    
	Given User finds the valid non-orally argued categories for the judge 
	
@AMB_1006 
Scenario: 
	Verify if PDF document can be downloaded from the server to Briefcase and viewed within Briefcase
     
	Given  User clicks on Motions/Pettitions 
	Then User selects case "18-12418" 
	And User taps on pdf doc in cmecf and verifies that it is downloaded from the server and opens in Briefcase 
	
	
@AMB_1008 
Scenario: 
	Pending Tasks Category Displays on the Dashboard
     A category entitled "Pending Tasks" will display on the dashboard if the judge has 
     any pending assignments and the site table variable briefcaseShowPendingTasks = 'y'.

 
	Given If The judge has any pending assignments it will validate the total num of pending task on UI with DB 
	
	
	
Scenario: 
	When User LogOut from the Application 
	Then It should take the user back to server page 
	
	
