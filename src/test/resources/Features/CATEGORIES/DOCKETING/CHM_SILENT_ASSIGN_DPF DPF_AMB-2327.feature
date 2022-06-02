Feature: chmSilentAssign DPF 
@AMB-2327 @Regression
Scenario Outline: chmSilentAssign DPF - Multiple assignments in a single transaction
	
	Given I am logged into Briefcase 
		|environment|userName| password |courtId|
		|test       |test    | test     |test   |
		
	Then I select a user 
		|userType   |personrole        |jud     |
		|judge      |Appellate Judges  |Benton   |
	Then  User selects "<refCat>" and "<caseNum>" 
	Then User  selects action using "<el_id>"  and verifies the name of the action displays in the dark blue banner 
		|courtId|
		|test   |
	Then User clicks on create New Staff Assignment	
	And User selects a staff member, assignment type, and at least one date, "<dbType>", "<dpfName>" , "<el_id>", "<cha_ju_pe_id>", "<cmr_cyv_code>", "<cmr_cs_caseid>", "<caseNum>"); 
	Then User clicks on create New Staff Assignment	
	And User selects the same staff member, a different assignment type, and at least one date, "<dbType>", "<dpfName>" , "<el_id>", "<cha_ju_pe_id>", "<cmr_cyv_code>", "<cmr_cs_caseid>", "<caseNum>"); 
		
	Examples: 
		|dbType |el_id     |cha_ju_pe_id|cmr_cs_caseid|dbType|cmr_cyv_code|caseNum |refCat         |dpfName  |
		|CMKA   |3116      |34          |82898        |CMKA  |autotst     |18-83118|TEST_AUTOMATION|chmAssign|
		
		
		
		
		
@AMB-2415
Scenario: 

	The chmSilentAssign DPF can be used in Briefcase to silently create or terminate judges’ assignments without user interaction.  Judges may have multiple matters 
	referred to them, but not all of them require their attention right away.  This DPF could be used to route tasks to judges so they know what work needs to be done now. 
	 For example, the lead judge may be initially tasked with reviewing a proposed order sent by the clerk’s office.  The lead judge then reviews the proposed order, and 
	 votes on it and circulates it to the second judge.  If the chmSilentAssign DPF is defined in the voting action the judge chose in Briefcase, it could terminate the
	  lead judge’s assignment and create a new assignment for the second judge to review the proposed order.  Once the second judge votes, the chmSilentAssign DPF could
	   be used to terminate the second judge’s assignment and create one for the third judge to review the proposed order.
Any assignments that are made to a judge will display in the “Pending Tasks” category on the Dashboard page, indicating what matters requires their attention.  

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
		
	Then User  selects action using "3068"  and verifies the name of the action displays in the dark blue banner 
		|courtId|
		|test   |
					
		