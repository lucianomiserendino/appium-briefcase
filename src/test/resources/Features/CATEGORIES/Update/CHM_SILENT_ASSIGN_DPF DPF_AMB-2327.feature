Feature: chmSilentAssign DPF 
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
		#|environment|user    |
		#|test       |sysadmin|
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |sysadmin |
			
   Then User selects a random category
			
   Then User selects a random case
				
	Then User select chmAssign action 
	|dpf       |courtId    |
	|create    |test       |
		




@AMB-3643
Scenario: 
 
        Given user locates a random referral
         
        And user locates chmAssign action
        |dpf |
        |term|
       
        Then changes the values in event_list table
        |chmSilentAssignMode |
        |termPanel           |
        
		Given I am logged into Briefcase 
		|environment|user    |
		|test       |sysadmin|
			
		Then I select a user 
		|userType   |personrole        |jud      |user     |
		|judge      |Appellate Judges  |test     |sysadmin |
			      
        Then User taps on magnifying glass icon and searches for the randomly located case
              
   		Then User submits chmSilentAssign dpf
   		      
        And User selects that referral in My Assignments folder and validates chmSilentAssignment
        