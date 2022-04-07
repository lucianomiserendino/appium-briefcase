Feature: Ability to Delete Individual Docs for Statff Attorneys

  @AMB-2416
  Scenario: As a Staff Attorney I can delete individual documents

	

	#Given I am logged into Briefcase 
		#|environment|userName| password |courtId|
		#|test       |test    | test     |test   |
		
		Then I select a user 
		|userType   |personrole        |stf  |
		|stf        |Staff Attorneys   |test |
		
#	Given I tap on referral category, the categories can be found using 'RA_PE_ID' : "434" 
	Then Verify that swiping over a document in either direction deletes the document

