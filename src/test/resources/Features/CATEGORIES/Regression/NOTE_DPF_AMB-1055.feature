@smoke
Feature: note DPF UI 

@AMB-1055 @Regression
Scenario: 
	Verify when an action is selected that contains the note DPF, the note DPF UI displays.


	
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
				
	Then User selects action
	|dpf       |courtId    |
	|note      |test       |
	
	Then User verifies the text "Add New Note" displays in the light blue banner. 
	
	And user verifies an editable Description , Comment , Submit fields are  displayed.  The default description is defined in the Default description parameter of the note DPF 
		|courtId|
		|test   |
    
    

    