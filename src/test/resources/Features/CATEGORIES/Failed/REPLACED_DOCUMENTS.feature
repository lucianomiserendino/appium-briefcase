Feature: Document Annotations - Annotated document is replaced


Background:

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
		
	 Then User expands/collapse panel
	 
	 
	@AMB-3483
Scenario: 
 
	 Verify in the event an annotated document is replaced in CM/ECF and the judge would want to view the replaced document and his/her annotations, the replaced document displays in Briefcase with a plus icon next to it, indicating there is an annotated version of the document. (line 310)
Verify when the plus icon is selected the annotated document displays under the replaced document. (line 311)


Then User verifies that the annotated/replaced documents are displayed as expected