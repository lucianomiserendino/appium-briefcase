Feature: Display other submissions that have been made in a case on referral detail page
@AMB-3155


Scenario: As a user I want to display any other submissions that have been made in a case on the referral detail page. For example, when
 viewing a motion submission, if any other motions or screenings have been submitted in that case, the judge wants to see those as well. 
 This goes for any category (motions, screeners, petitions, oa, etc.).
       
		#Given I am logged into Briefcase 
		#|environment|userName| password |courtId|
		#|test       |test    | test     |test   |
		
	Then I select a user 
		|userType   |personrole        |jud     |
		|judge      |Appellate Judges  |test    |
		
		Then I navigate to a case with multiple referrals and verify that the case displays the Other Submissions in Case panel and contains the date, type of submission, the panel it was sent to and the status. 