@AMB-1399
Feature: Display referrals when site var briefcaseTargetOnly = n 

#this script needs to get updated
#AMB-4112 #briefcaseMultipleReferral = n/N, briefcaseTargetOnly = n > Case Referral Detail -- referrals are not consolidated


Scenario: 
	Verify when an action is selected that contains the note DPF, the note DPF UI displays.
		
	Then User gets the si_value from the site table
	|si_value      |courtId    |
	|targetOnly    |test       |
	
		Given I set the site table variable "briefcaseTargetOnly" to "y"
				
		#Given I am logged into Briefcase 
		#|environment|user    |
		#|test       |judge   |
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |judge    |
		
		
     Then User selects a random category
			
     Then User selects a random case

     Given User observes the Vote Information panel displays.   This should only display if the referral requires voting 
     
     Then get the filler's info, party type, date filed, relief, closing date, votes, judge initials
		
		
		Given I set the site table variable "briefcaseTargetOnly" to "n"
				
	    Then User closes and reopens the app
		
		Then User taps on magnifying glass icon and searches for the same case 
		
        Then verify the Vote Information panel data remains the same with `briefcaseTargetOnly` set to `n` or `y`.
	
	    Given I set the site table variable "briefcaseTargetOnly" to "y"
