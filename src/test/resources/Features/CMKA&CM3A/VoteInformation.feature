Feature: Display Vote Information Panel, filer info and judge's initials 
	Display Actions Panel and actions


	
@Smoke 
@AMB_1036 
Scenario Outline: 
	If a referral requires voting, a collapsible Vote Information panel displays.  When expanded, it displays the following information:
1.  The name and party type of the person who filed the motion/petition
2.  The judges initials who are on the panel
3.  The relief on which the judge is ruling
4.  Each judge's vote and the date they voted

	Given  User Navigates to  "<environment>" environment 
	When  User enters Credentials to Login "<userName>" and "<password>" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "<server>" 
	And   User selects judge, category "<refCategory>"  and "<caseNume>" 
	Given User observes ( "<dbType>" ) the "Vote Information" panel displays.   This should only display if the referral requires voting 
	Then   for each referral, observes the filer's name  first initial of pr_middle_name gn_display  party type and date filed displays in a light blue heading. Use "<dbType>", "<cmr_ju_pe_id>" , "<cmr_cs_caseid>" ,"<cmr_cyv_code>" , "<ccr_id>" .
	And observes the judge's initials display in the same heading and db "<dbType>" using  "<ccr_id>"

	#Then  User checks each judge's vote  and the date  displays under their initials 
	
	
	
	Examples: 
		|environment   |userName          |password  |server                                   |refCategory             | caseNume |dbType| cmr_cs_caseid |cmr_ju_pe_id|cmr_cyv_code|ccr_id|
		#|Integration   |chambers courtney |Test2020!|Appellate DC Development - CMKA           |Petitions for Rehearing | 15-3314  |CMKA  | 82226         |32          |prhr        |34870 |
		|Testing       |Chambers Haenni   |Test2017!|Appellate DC Installation Testing - CM3A  |Rehearing Petition      | 11-1012  |CM3A  | 67317         |2189563     |rhr         |2973  |
		
		
		
		
		
		
		
		
		
		
		
		
