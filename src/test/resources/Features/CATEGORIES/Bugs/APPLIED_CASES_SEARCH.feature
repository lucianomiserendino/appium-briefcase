@Regression
Feature:  Prevent the user from navigating to an applied case referral detail page from Search.   
  when a user selects an applied case in the Applied cases panel, the additional case information screen displays 

#BUG AMB-4965

Background:
		#Given I am logged into Briefcase 
		#|environment|user    |
		#|test       |sysadmin|
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |sysadmin |
			
		
@AMB-3136 @AMB-2636 @AMB-3458
Scenario:
As a judge user when entering an applied case number in the Search box and selecting 'On Device' ,
 the user is directed to the target case referral detail page.
The user is not directed to the applied case.
The applied case can be selected from the target case.

			
	#This also covers:   additional case information screen test, when  an applied case is selected from the Applied cases panel,
	#verifies that Applied cases display in the referral list page when briefcaseTargetOnly = n 
	
	Then User taps on magnifying glass icon and searches for applied case, selects On Device option & verifies that the user is directed to the target case referral detail page
	|userType |
	|judge    |
	
	
	
	
	

