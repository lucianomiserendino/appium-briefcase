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

		#Given I am logged into Briefcase 
		#|environment|userName| password |courtId|
		#|test       |test    | test     |test   |
		
	Then I select a user 
		|userType   |personrole        |jud     |
		|judge      |Appellate Judges  |test    |
			
	Then User selects random judge category
		|courtId|
		|test   |
			
	Then User selects random case number
		|courtId|
		|test   |		
	
	Given User observes the "Vote_Information" panel displays.   This should only display if the referral requires voting 
	
	Then   for each referral, observes the filer's name  first initial of pr_middle_name gn_display  party type and date filed displays in a light blue heading.
	
	
	
	#Use  "<cmr_ju_pe_id>" , "<cmr_cs_caseid>" ,"<cmr_cyv_code>" , "<ccr_id>" 

	#Then  User checks each judge's vote  and the date  displays under their initials, using  "<ccr_id>" 
	#	|courtId|
	#	|test   |
		
	#Examples: 
	#	|server         |refCategory  | caseNum |dbType| cmr_cs_caseid |cmr_ju_pe_id|cmr_cyv_code|ccr_id|
	#	|CMKA           |PETITION     | 15-3314  |CMKA  | 82226         |32          |prhr       |34870 |
		
	
	