package gov.uscourts.ao.mobileBriefcase.DBUtils;

public class Queries {

	// Query to find appellate judge
	public static final String PE_ID = "(SELECT  PE_ID FROM PERSON, PERSONROLE WHERE "
			+ "PE_PR_PRID=PR_PRID AND PE_RT_CODE='jud' and PR_LAST_NAME='Colloton')";

	// Query to find the valid non-orally argued categories for the judge:
	public static final String CYV_CATEGORY = "SELECT DISTINCT CYV_CATEGORY FROM CHM_MOBILE_REFERRAL,"
			+ " CHM_REFTYPE_VAL WHERE CMR_JU_PE_ID = " + PE_ID + " AND CMR_DATE_END "
			+ "IS NULL AND CMR_CYV_CODE = CYV_CODE AND CYV_IS_BRIEFCASE = 'y' AND CYV_IS_ORAL_ARG = 'n'";

	// Query the site table where si_code = 'briefcaseTargetOnly' if
	// the si_value= 'y', run the following query
	public static final String SI_VALUE_Y = "SELECT COUNT(DISTINCT CS_CASEID) FROM CHM_MOBILE_REFERRAL, "
			+ "CHM_REFTYPE_VAL, CASE_DKTENTRY, CASE WHERE CMR_JU_PE_ID = " + PE_ID
			+ " AND CMR_CYV_CODE = CYV_CODE  AND CYV_CATEGORY = 'Motions/Petitions' "
			+ "AND CMR_CS_CASEID = CS_CASEID AND CMR_DATE_END IS NULL AND "
			+ "CD_CASEID = CMR_CS_CASEID AND CMR_DKTENTRYID = CD_DKTENTRYID  AND CD_CASE_EXT  = 1";

	// Query the site table where si_code = 'briefcaseTargetOnly' if the
	// si_value = 'n' or does not exist, run the following query
	public static final String SI_VALUE_N = "SELECT COUNT(DISTINCT CS_CASEID) FROM CHM_MOBILE_REFERRAL, "
			+ "CHM_REFTYPE_VAL, CASE_DKTENTRY, CASE WHERE CMR_JU_PE_ID = " + PE_ID
			+ "AND CMR_CYV_CODE = CYV_CODE  AND CYV_CATEGORY = 'Motions/Petitions' "
			+ "AND CMR_CS_CASEID = CS_CASEID AND CMR_DATE_END IS NULL AND "
			+ "CD_CASEID = CMR_CS_CASEID AND CMR_DKTENTRYID = CD_DKTENTRYID ";

	// Query to get valid categories for the logged in user
	public static final String DB_LIST_OF_CATEGORIES = "SELECT DISTINCT (CYV_CATEGORY) FROM "
			+ "CHM_MOBILE_REFERRAL, CHM_REFTYPE_VAL WHERE  CMR_JU_PE_ID = " + PE_ID
			+ " AND CMR_DATE_END IS NULL  AND CMR_CYV_CODE = CYV_CODE AND CYV_IS_BRIEFCASE = 'y'";

	// To find if a judge has any pending assignments run the following
	// query for the logged in judge:
	public static final String PENDING_TASK_ASSIGNMENTS = "SELECT COUNT(CHC_CHA_ID) FROM CHM_MOBILE_REFERRAL, "
			+ "CHAMBERS_CASE_TO_REFERRAL, CHM_ASSIGN_TO_CASE, CHAMBERS_ASSIGNMENT WHERE CMR_CCR_ID = CCR_ID AND CCR_CPR_ID = "
			+ "CHC_CPR_ID AND CMR_CS_CASEID = CHC_CS_CASEID AND CHC_DATE_END IS null AND " + " CMR_JU_PE_ID = " + PE_ID
			+ " AND  CHC_CHA_ID = CHA_ID AND CMR_JU_PE_ID = CHA_CHM_PE_ID";

	// Query to find staff assignments associated with the referral.
	public static final String STAFF_ASSIGNMENTS_LINKED_TO_THE_REFERRAL = "SELECT DISTINCT PR_FIRST_NAME FROM CHM_MOBILE_REFERRAL,"
			+ " CHAMBERS_CASE_TO_REFERRAL, CHM_ASSIGN_TO_CASE, CHAMBERS_ASSIGNMENT, PERSON, PERSONROLE, CHM_ASSIGN_TYPE_VAL "
			+ "WHERE  CMR_CS_CASEID = 82226  AND CMR_CCR_ID = CCR_ID AND CCR_CPR_ID = CHC_CPR_ID AND CHC_CS_CASEID = "
			+ "CMR_CS_CASEID AND CHC_CHA_ID = CHA_ID AND CHA_JU_PE_ID =" + PE_ID
			+ "  AND CHC_DATE_END IS NULL AND CHA_CHM_PE_ID"
			+ " = PE_ID AND PE_PR_PRID = PR_PRID AND CMR_CYV_CODE IN (SELECT CYV_CODE FROM CHM_REFTYPE_VAL WHERE CYV_CATEGORY = "
			+ "'Motions/Petitions' ) AND CHA_CAV_CODE = CAV_CODE";

	public static final String ASSIGNED_DATES = "SELECT  (ad.chd_date ) FROM chambers_assign_date ad, chm_assign_datetype_val,"
			+ " (Select max(chd_date) as maxnum, chd_cha_id from chambers_assign_date group by chd_cha_id)"
			+ " maxresults WHERE ad.chd_cha_id in (2236, 2235)  and chd_cdv_code = cdv_code and ad.chd_cha_id= "
			+ " maxresults.chd_cha_id and ad.chd_date = maxresults.maxnum ";

	public static final String STAFF_ASSIGNMENTS_LINKED_TO_THE_CASE = "SELECT DISTINCT  PR_LAST_NAME  FROM chm_mobile_referral, chambers_case_to_referral,"
			+ " chm_assign_to_case, chambers_assignment, person, personrole, chm_assign_type_val WHERE "
			+ "cmr_cs_caseid = 82226 and cmr_ccr_id = ccr_id and chc_cs_caseid = cmr_cs_caseid and chc_cpr_id = 1 and "
			+ "chc_cha_id = cha_id and cha_ju_pe_id = "+PE_ID+" and chc_date_end is null and cha_chm_pe_id = pe_id and "
			+ "pe_pr_prid = pr_prid and cha_cav_code = cav_code";

}
