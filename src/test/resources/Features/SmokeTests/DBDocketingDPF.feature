Feature: note DPF back-end updates 

Background: 


	Given  User Navigates to Sever 
	When  User enters Credentials to Login 
		|userName			|password	|
		|SysadminKasabolotova|Asalta6268!z|
		
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And After user navigates to Appellate DC Development - CMKA - dev 
	
	

@AMB_1075 
Scenario: 
	Adding a note back-end database updates.
       hen User expands the Actions panel, selects an action
       
	Given Select Judge Colloton , Screening Panels "15-3703" 
	When User  selects an "Action" 
	
	Then User selects "note - court users",user enters a comment in the editable field and submits . However dm_acc_crt = 'y', dm_acc_ctlink = 'n', dm_acc_spec = 'n'. No doc_group or doc_user records are created. 
	Then User selects "mbr note - court users linked to case"user enters a comment in the editable field and submits . However the dm_acc_crt = 'n', dm_acc_ctlink = 'y', dm_acc_spec = 'n'. No doc_group or doc_user records are created 
	
	Then User selects "mbr note - panel judges only",user enters a comment in the editable field and submits . However  the dm_acc_crt = 'n', dm_acc_ctlink = 'n', dm_acc_spec = 'y'. 
	And user verifies a doc_user record is  created for each judge on the panel 
	
	Then  User selects "mbr note - panel judges and user's chambers",user enters a comment in the editable field and submits . However the dm_acc_crt = 'n', dm_acc_ctlink = 'n', dm_acc_spec = 'y'. 
	And user verifies  a doc_user record is   created for each judge on the panel that's not the logged in judge. 
	And user verifies  a doc_group record will be created for the logged in judge's chambers group 
	
	Then  User selects "mbr note - panel judges chambers",user enters a comment in the editable field and submits . However  the dm_acc_crt = 'n', dm_acc_ctlink = 'n', dm_acc_spec = 'y'. 
	And  user verifies  a doc_user record will not be created 
	And user verifies  a  doc_group record will be created for the panel members chambers 
	
	Then  User selects  "mbr note - users chambers",user enters a comment in the editable field and submits . However  the dm_acc_crt = 'n', dm_acc_ctlink = 'n', dm_acc_spec = 'y'. 
	And user verifies  a doc_user record will not be created 
	And user verifies  a doc_group record will be created for the logged in judge's chambers only 
	
	
	Then  User selects  "note - only groups and users",user enters a comment in the editable field and submits . However the dm_acc_crt = 'n',  dm_acc_ctlink = 'n', dm_acc_spec = 'y'. 
	And user verifies  a doc_user record will not be created for the pr_prids following the pipe in the Note Available personroles -Default person IDs 
	And user verifies  a doc_group record will be created for the groups ids following the pipe in the Note Available - Default Group IDs