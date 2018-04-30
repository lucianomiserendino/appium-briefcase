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
}
