Feature: Display Vote Information Panel, filer info and judge's initials 
	Display Actions Panel and actions

	#dependent on AMB-2257
@AMB-1036 
Scenario: 
	If a referral requires voting, a collapsible Vote Information panel displays.  When expanded, it displays the following information:
1.  The name and party type of the person who filed the motion/petition
2.  The judges initials who are on the panel
3.  The relief on which the judge is ruling
4.  Each judge's vote and the date they voted

		Given I am logged into Briefcase 
		|environment|user    |
		|test       |sysadmin|
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |sysadmin |
		
		Then User selects random judge category
		|courtId|
		|test   |
			
	   Then User selects random case number
		|courtId|
		|test   |
		
     Given User observes the Vote Information panel displays.   This should only display if the referral requires voting 
	Then  For each referral, observe the filer's name (pr_last_name, pr_first_name, first initial of pr_middle_name, gn_display) party type (pt_description) and date filed (de_date_filed) displays in a light blue heading
	
	Then  User checks each judge's vote  and the date  displays under their initials, using  ccr_id
		|courtId|
		|test   |
		
	