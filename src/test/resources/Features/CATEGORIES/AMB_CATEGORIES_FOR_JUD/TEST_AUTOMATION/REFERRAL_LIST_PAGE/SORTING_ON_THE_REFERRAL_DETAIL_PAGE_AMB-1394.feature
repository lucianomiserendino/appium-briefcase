Feature: Sorting on the Referral Detail Page 


@Regression
@AMB-1394 
Scenario Outline: 
	There is a sort field in the chm_mobile_docs table that the courts can set for sorting Document categories.
	  Briefcase should this field for sorting. Briefcase needs to be updated to sort document categories 
	  based on the chm_mobile_doc.cmd_sort field
	

	Given  User Navigates to  "<environment>" environment 
	When  User enters Credentials to Login "<userName>" and "<password>" 
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And  user selects a "<server>" 
	Then User selects a userCategory "<userCategory>" and  name "<judgeName>" 
	Then  User selects "<refCat>" and "<caseNum>" 
	Then User verifies  Document Categories are sorted on the referral detail page ("<server>", "<cmr_cyv_code>", "<cmr_ju_pe_id>", "<cmr_cs_caseid>") 
	
	
	
	
	Examples: 
		|environment   |userName          |password  |server         |refCat            |caseNum|userCategory    |judgeName|cmr_cyv_code| cmr_ju_pe_id| cmr_cs_caseid|
		|INTEGRATION   |s haenni          |Test2021! |CMKA           |TEST_AUTOMATION   |15-2594|Appellate_Judges|Colloton |autotst     |32           |  81452      |
		#|Testing       |judgewilliams   |Testpass1! |CM3A           |Motion/Petition   | 
		
		
		
		