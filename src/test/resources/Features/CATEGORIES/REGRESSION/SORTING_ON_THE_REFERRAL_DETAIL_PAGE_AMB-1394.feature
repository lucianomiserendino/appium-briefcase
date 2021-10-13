@Regression @AMB-1394
Feature: Sorting on the Referral Detail Page 

 
Scenario Outline: 
	There is a sort field in the chm_mobile_docs table that the courts can set for sorting Document categories.
	  Briefcase should this field for sorting. Briefcase needs to be updated to sort document categories 
	  based on the chm_mobile_doc.cmd_sort field
	

	Given I am logged into Briefcase 
		|environment|userName| password |courtId|
		|test       |test    | test     |test   |
		
	Then I select a user 
		|userType   |personrole        |jud     |
		|judge      |Appellate Judges  |test    |
	
	Then  User selects "<refCat>" and "21-3877" 
	Then User verifies  Document Categories are sorted on the referral detail page ("<server>", "<cmr_cyv_code>", "<cmr_ju_pe_id>", "<cmr_cs_caseid>") 
	
	
	
	
	Examples: 
		|environment   |userName       |password  |server    |refCat           |userCategory    |judgeName|cmr_cyv_code| cmr_ju_pe_id| cmr_cs_caseid|
		|INTEGRATION   |s haenni       |Test2025! |CMKA       |TEST_AUTOMATION  |Appellate_Judges|Colloton |autotst     |32           |  83432      |
		#|Testing       |judgewilliams   |Testpass1! |CM3A           |Motion/Petition   | 
		
		
		
		