@smoke @AMB-956 @Regression @AMB-1052

Feature: Referral Categories display on the dashboard for the judge (online and offline)

Scenario: 
	Referral categories display on the dashboard for the judge 

		#Given I am logged into Briefcase 
		#|environment|user    |
		#|test       |sysadmin|
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |sysadmin |
		
		And the user verifies that referral categories are displayed on the dashboard in both online and offline modes.
		|jud       |courtId       |
		|test      |test          |
		
		
		#NOTE: 
		 #Query the chm_mobile_referral, and chm_reftype_val table to get valid categories for the logged in user. 
		# The pending tasks category will not be included in this task.  
		#This category is dynamically created if there are judge assignments. 
		# The bookmarked category is also not included in this task.
		
       #This ensures that pending referrals remain visible in offline mode for a sysadmin user (AMB-1052)	
 