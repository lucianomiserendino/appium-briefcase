@Regression @AMB-3366
Feature: Sorting on the Staff Attorney Referral List Page



Scenario: 
	Staff attorneys can sort referrals by tapping on the “Sort” button in the blue banner. A sort pop-up will display:
By default, the referrals are grouped in accordion panels by referral category and sorted by case number. The available sort options are:
 Default, Case Number, Status, Dates as defined by the courts. Courts can define up to three different dates on which to sort. The label for 
 these dates need to be created and stored in the ui_component table. The staff attorney referral query in the chm_mobile_config table will 
 then need to be updated to store the uic_id for in the appropriate field in the stfaty_mobile_referral table: smr_uic1, smr_uic2, smr_uic3.
Tapping an item in the sort pop-up will sort the referrals in ascending/descending order. The referrals will be sorted across referral
 categories. Therefore, after the user makes a sort selection, the referral category accordion panels will no longer display and the 
 referral category will be listed under the case number. A new accordion panel will display with all the sorted referrals. The panel 
 will contain the sorting options selected by the user. If a date is selected that is not used for all referrals, the referrals that 
 contain the date will be listed first. The remaining referrals will be listed at the bottom in an accordion panel entitled "Un-sorted"
  and sorted by case number. Once a sort selection has been made, a "Default" button will display in the banner. When tapped, the referral
   will go back to their original state (grouped in accordion panels by referral category and sorted by case number). The Default option will 
   also be listed in the list of sort options.
	
		zGiven I am logged into Briefcase 
		|environment|user    |
		|test       |sysadmin|
			
		Then I select a user 
		|userType   |personrole       |stf      |user     |
		|stf        |Staff Attorneys  |test     |sysadmin | 
		
	Then User selects random stf Aty category
		|courtId|
		|test   |
		
		Then User selects a sort option and verifies the items on the page is sorted accordingly
