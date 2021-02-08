@AMB @Regression @AMB-1394 @a
Feature: Sorting on the Referral Detail Page 

 
Scenario Outline: 
	There is a sort field in the chm_mobile_docs table that the courts can set for sorting Document categories.
	  Briefcase should this field for sorting. Briefcase needs to be updated to sort document categories 
	  based on the chm_mobile_doc.cmd_sort field
	

	Given I am logged into Briefcase 
		|environment    |userName| password |courtId|
		|Integration    |s haenni| Test2023!|test   |
		
	Then I select a user
	|role              |briefcaseUser|
	|Appellate Judges  |Colloton     |
	
	Then  User selects "<refCat>" and "15-2594" 
	Then User verifies  Document Categories are sorted on the referral detail page ("<server>", "<cmr_cyv_code>", "<cmr_ju_pe_id>", "<cmr_cs_caseid>") 
	
	
	
	
	Examples: 
		|environment   |userName       |password  |server    |refCat           |userCategory    |judgeName|cmr_cyv_code| cmr_ju_pe_id| cmr_cs_caseid|
		|INTEGRATION   |s haenni       |Test2021!|CMKA       |TEST_AUTOMATION  |Appellate_Judges|Colloton |autotst     |32           |  81452      |
		#|Testing       |judgewilliams   |Testpass1! |CM3A           |Motion/Petition   | 
		
		
		
		