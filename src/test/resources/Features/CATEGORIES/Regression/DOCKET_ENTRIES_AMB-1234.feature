@AMB-1234 
Feature: Docket Entries for Chambers Users 

Scenario: 

       
		Given I am logged into Briefcase 
		|environment|user    |
		|test       |judge   |
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |judge    |
		
		
	Then User selects a random category

	Then User taps on magnifying glass icon and searches for a case and  verifies the result is not empty
	|caseNumber|
	|test      |
	
	Then user gets the entries of the judge
	
	Then User logs out from the Briefcase
	
		Given I am logged into Briefcase 
		|environment|user    |
		|test       |ja      |
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |ja       |
		
    Then User taps on magnifying glass icon and searches for the same case 
    
	Then user verifies a JA or law clerk can see the same entries as their judge
	
	