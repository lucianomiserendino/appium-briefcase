package gov.uscourts.ao.mobileBriefcase.DBUtils;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.common.Constants.COURT_USERS;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.common.Constants;

public class Queries {

	public static String getId(String id) {
		return id;

	}

	public static final String PE_ID = "(SELECT  PE_ID FROM PERSON, PERSONROLE WHERE "
			+ "PE_PR_PRID=PR_PRID AND PE_RT_CODE='jud' and PR_LAST_NAME='TEXT')";
	// Query to find appellate judge
	public static final String COLLOTONs_PE_ID = "(SELECT  PE_ID FROM PERSON, PERSONROLE WHERE "
			+ "PE_PR_PRID=PR_PRID AND PE_RT_CODE='jud' and PR_LAST_NAME='Colloton')";

	public static final String CMR_ID = "SELECT cmr_id FROM chm_mobile_referral, chambers_case_to_referral, chambers_referral"
			+ " WHERE cpr_vote_req = 'y' and cmr_ccr_id = ccr_id and ccr_cpr_id = cpr_id and cmr_cs_caseid = \"82226\" and"
			+ " cmr_cyv_code = 'prhr' and cmr_ju_pe_id = " + COLLOTONs_PE_ID;

	// Query to find the valid non-orally argued categories for the judge:
	public static final String CYV_CATEGORY = "SELECT DISTINCT CYV_CATEGORY FROM CHM_MOBILE_REFERRAL,"
			+ " CHM_REFTYPE_VAL WHERE CMR_JU_PE_ID = " + COLLOTONs_PE_ID + " AND CMR_DATE_END "
			+ "IS NULL AND CMR_CYV_CODE = CYV_CODE AND CYV_IS_BRIEFCASE = 'y' AND CYV_IS_ORAL_ARG = 'n'";

	// Query the site table where si_code = 'briefcaseTargetOnly' if
	// the si_value= 'y', run the following query
	public static final String MOTIONS_PETITIONS_SI_VALUE_Y = "SELECT COUNT(DISTINCT CS_CASEID) FROM CHM_MOBILE_REFERRAL, "
			+ "CHM_REFTYPE_VAL, CASE_DKTENTRY, CASE WHERE CMR_JU_PE_ID = " + COLLOTONs_PE_ID
			+ " AND CMR_CYV_CODE = CYV_CODE  AND CYV_CATEGORY = 'Motions/Petitions' "
			+ "AND CMR_CS_CASEID = CS_CASEID AND CMR_DATE_END IS NULL AND "
			+ "CD_CASEID = CMR_CS_CASEID AND CMR_DKTENTRYID = CD_DKTENTRYID  AND CD_CASE_EXT  = 1";

	// To find if a judge has any pending assignments run the following
	// query for the logged in judge:
	public static final String PENDING_TASK_ASSIGNMENTS = "select  count(distinct concat(cav_display, chc_cs_caseid)) "
			+ "			 FROM chm_mobile_referral JOIN chambers_case_to_referral on ccr_id = cmr_ccr_id \n"
			+ "			 join chambers_referral on ccr_cpr_id = cpr_id \n"
			+ "			 JOIN chm_assign_to_case on chc_cpr_id = ccr_cpr_id and chc_cs_caseid = cmr_cs_caseid \n"
			+ "			 JOIN chambers_assignment on cha_id = chc_cha_id and cha_chm_pe_id = cmr_ju_pe_id \n"
			+ "			 JOIN chm_assign_type_val on cav_code = cha_cav_code \n"
			+ "			 JOIN chambers_assign_date on chd_cha_id = cha_id \n"
			+ "			 join chm_assign_datetype_val on cdv_code = chd_cdv_code \n"
			+ "			 WHERE chc_date_end is null and cmr_ju_pe_id = ? and cmr_date_end is null; ";

	// Query to find staff assignments associated with the referral.
	public static final String STAFF_ASSIGNMENTS_LINKED_TO_THE_REFERRAL_NAME = "SELECT DISTINCT pr_first_name FROM CHM_MOBILE_REFERRAL,"
			+ " CHAMBERS_CASE_TO_REFERRAL, CHM_ASSIGN_TO_CASE, CHAMBERS_ASSIGNMENT, PERSON, PERSONROLE, CHM_ASSIGN_TYPE_VAL "
			+ "WHERE  CMR_CS_CASEID = 82226  AND CMR_CCR_ID = CCR_ID AND CCR_CPR_ID = CHC_CPR_ID AND CHC_CS_CASEID = "
			+ "CMR_CS_CASEID AND CHC_CHA_ID = CHA_ID AND CHA_JU_PE_ID =" + COLLOTONs_PE_ID
			+ "  AND CHC_DATE_END IS NULL AND CHA_CHM_PE_ID"
			+ " = PE_ID AND PE_PR_PRID = PR_PRID AND CMR_CYV_CODE IN (SELECT CYV_CODE FROM CHM_REFTYPE_VAL WHERE CYV_CATEGORY = "
			+ "'Motions/Petitions' ) AND CHA_CAV_CODE = CAV_CODE";

	public static final String STAFF_ASSIGNMENTS_LINKED_TO_THE_REFERRAL_DESCRIPTION = "SELECT DISTINCT cav_description FROM CHM_MOBILE_REFERRAL,"
			+ " CHAMBERS_CASE_TO_REFERRAL, CHM_ASSIGN_TO_CASE, CHAMBERS_ASSIGNMENT, PERSON, PERSONROLE, CHM_ASSIGN_TYPE_VAL "
			+ "WHERE  CMR_CS_CASEID = 82226  AND CMR_CCR_ID = CCR_ID AND CCR_CPR_ID = CHC_CPR_ID AND CHC_CS_CASEID = "
			+ "CMR_CS_CASEID AND CHC_CHA_ID = CHA_ID AND CHA_JU_PE_ID =" + COLLOTONs_PE_ID
			+ "  AND CHC_DATE_END IS NULL AND CHA_CHM_PE_ID"
			+ " = PE_ID AND PE_PR_PRID = PR_PRID AND CMR_CYV_CODE IN (SELECT CYV_CODE FROM CHM_REFTYPE_VAL WHERE CYV_CATEGORY = "
			+ "'Motions/Petitions' ) AND CHA_CAV_CODE = CAV_CODE";

	public static final String ASSIGNED_DATES = "SELECT  (ad.chd_date ) FROM chambers_assign_date ad, chm_assign_datetype_val,"
			+ " (Select max(chd_date) as maxnum, chd_cha_id from chambers_assign_date group by chd_cha_id)"
			+ " maxresults WHERE ad.chd_cha_id in (2236, 2235)  and chd_cdv_code = cdv_code and ad.chd_cha_id= "
			+ " maxresults.chd_cha_id and ad.chd_date = maxresults.maxnum ";

	public static final String REFLIEF_TEXT = "SELECT rl_list_text FROM relief_list,"
			+ " dktpart, chm_assign_to_case, chambers_case, chambers_case_to_referral WHERE chc_cha_id = 2235 "
			+ " and chc_cpr_id = ccr_cpr_id and ccs_cs_caseid = 82226 and ccr_ccs_id = ccs_id and ccr_dp_dktpartid "
			+ "= dp_dktpartid and dp_rlid = rl_id";

	public static final String NOTES_ATTACHED_TO_ASSIGNMENTS = "SELECT  dm_date_created, "
			+ "dm_description FROM document, chm_assign_to_note WHERE can_cha_id = 2236";

	public static final String ASSIGNMENT_DATETYPE_DATE = "SELECT cdv_description, ad.chd_date FROM "
			+ "chambers_assign_date ad, chm_assign_datetype_val, (Select max(chd_date) as maxnum, chd_cha_id from "
			+ "chambers_assign_date group by chd_cha_id) maxresults WHERE ad.chd_cha_id in (2236, 2235)"
			+ " and chd_cdv_code = cdv_code and ad.chd_cha_id=  maxresults.chd_cha_id and ad.chd_date = maxresults.maxnum";

	// CMR_CCR_ID

	public static final String VOTE_INFORMATION = "SELECT CMR_CCR_ID FROM CHM_MOBILE_REFERRAL,"
			+ " CHAMBERS_CASE_TO_REFERRAL, CHAMBERS_REFERRAL WHERE CPR_VOTE_REQ = 'y' AND "
			+ "CMR_CCR_ID = CCR_ID AND CCR_CPR_ID = CPR_ID AND CMR_CS_CASEID = 82226 AND "
			+ "CMR_CYV_CODE = 'prhr' AND CMR_JU_PE_ID = " + COLLOTONs_PE_ID;

	public static final String JUDGEs_INITIALS = " SELECT ju_initials FROM chm_mobile_referral, "
			+ "judge WHERE cmr_ccr_id = ? and cmr_ju_pe_id = ju_pe_id ";

	public static final String RELIEF = "select distinct  rl_list_text from chambers_case_to_referral b join chambers_case_to_referral a on"
			+ " b.ccr_cpr_id = a.ccr_cpr_id join dktpart on dp_dktpartid = b.ccr_dp_dktpartid join relief_list on rl_id = dp_rlid "
			+ "join chambers_caseref_to_judge on b.ccr_id = crj_ccr_id join judge on crj_ju_pe_id = ju_pe_id join chambers_referral on b.ccr_cpr_id = cpr_id "
			+ "join chambers_case on b.ccr_ccs_id = ccs_id join chm_reftype_val on cpr_cyv_code = cyv_code join case_dktentry on cd_id = dp_cd_id "
			+ "join dktentry on cd_dktentryid = de_dktentryid left join doctype_val on dp_doc_type = dty_code join event_list on el_id = de_elid"
			+ " left join chambers_vote left join (chm_vote_to_note inner join document on cvn_dm_dls_id = dm_dls_id and dm_internal_type = 'notevote'  ) on chv_id = cvn_chv_id "
			+ "on chv_crj_id = crj_id and chv_date_end is null left join chm_vote_val on chv_cvv_code = cvv_code where a.ccr_id = ? and b.ccr_date_end is null and "
			+ "NVL(cpr_vote_complete,'') <> 'y' and NVL(cpr_vote_req,'') <> 'n' order by  rl_list_text desc";

	public static final String JUDGEs_VOTE = "select distinct  cvv_display from chambers_case_to_referral b join chambers_case_to_referral a on "
			+ "b.ccr_cpr_id = a.ccr_cpr_id join dktpart on dp_dktpartid = b.ccr_dp_dktpartid join relief_list on rl_id = dp_rlid "
			+ "join chambers_caseref_to_judge on b.ccr_id = crj_ccr_id join judge on crj_ju_pe_id = ju_pe_id join chambers_referral on b.ccr_cpr_id = cpr_id "
			+ "join chambers_case on b.ccr_ccs_id = ccs_id join chm_reftype_val on cpr_cyv_code = cyv_code join case_dktentry on cd_id = dp_cd_id "
			+ "join dktentry on cd_dktentryid = de_dktentryid left join doctype_val on dp_doc_type = dty_code join event_list on el_id = de_elid "
			+ "left join chambers_vote left join (chm_vote_to_note inner join document on cvn_dm_dls_id = dm_dls_id "
			+ "and dm_internal_type = 'notevote'  ) on chv_id = cvn_chv_id on chv_crj_id = crj_id and chv_date_end is null "
			+ "left join chm_vote_val on chv_cvv_code = cvv_code where a.ccr_id = 34867 and b.ccr_date_end is null and NVL(cpr_vote_complete,'') <> 'y' "
			+ "and NVL(cpr_vote_req,'') <> 'n' and ju_initials='WDB'order by  cvv_display desc";

	public static final String VOTE_DATE = " select distinct  chv_date_created "
			+ "from chambers_case_to_referral b join chambers_case_to_referral a on b.ccr_cpr_id = a.ccr_cpr_id "
			+ "join dktpart on dp_dktpartid = b.ccr_dp_dktpartid join relief_list on rl_id = dp_rlid join chambers_caseref_to_judge on b.ccr_id = crj_ccr_id "
			+ "join judge on crj_ju_pe_id = ju_pe_id join chambers_referral on b.ccr_cpr_id = cpr_id join chambers_case on b.ccr_ccs_id = ccs_id "
			+ "join chm_reftype_val on cpr_cyv_code = cyv_code join case_dktentry on cd_id = dp_cd_id join dktentry on cd_dktentryid = de_dktentryid "
			+ "left join doctype_val on dp_doc_type = dty_code join event_list on el_id = de_elid left join chambers_vote left join (chm_vote_to_note inner join "
			+ "document on cvn_dm_dls_id = dm_dls_id and dm_internal_type = 'notevote'  ) on chv_id = cvn_chv_id on chv_crj_id = crj_id and chv_date_end is null "
			+ "left join chm_vote_val on chv_cvv_code = cvv_code where a.ccr_id = 34867 and b.ccr_date_end is null and NVL(cpr_vote_complete,'') <> 'y' and "
			+ "NVL(cpr_vote_req,'') <> 'n' and cvv_display='Approve' and ju_initials='WDB'order by chv_date_created  desc";

	public static final String ASSIGNMENT_CATEGORIES = "select distinct(sfa_display) from stfaty_mobile_referral, stfaty_ref_assign, "
			+ "stfaty_assign_val where smr_ra_id = ra_id and ra_pe_id = (SELECT distinct smr_assign_pe_id FROM stfaty_mobile_ref_cat, "
			+ "stfaty_mobile_referral WHERE smr_mrc_id = mrc_id and smr_sfa_code = 'sstfa' ) and smr_sfa_code = sfa_code and smr_date_end is null";

	// Observe there are six referral categories listed

	public static final String REFERRAL_CATEGORIES = "SELECT distinct(mrc_name),mrc_id FROM stfaty_mobile_ref_cat, stfaty_mobile_referral"
			+ " WHERE smr_mrc_id = mrc_id and smr_assign_pe_id = (SELECT distinct smr_assign_pe_id FROM stfaty_mobile_ref_cat, stfaty_mobile_referral"
			+ " WHERE smr_mrc_id = mrc_id and smr_sfa_code = 'sstfa')  and smr_sfa_code = 'sstfa'  order by mrc_name";

	// Query to Verify the number of referrals in each anders_cases category
	public static final String ANDERS_CASES = "SELECT count(smr_id) FROM stfaty_mobile_referral WHERE smr_mrc_id = (SELECT distinct mrc_id FROM"
			+ " stfaty_mobile_ref_cat, stfaty_mobile_referral WHERE smr_mrc_id = mrc_id and mrc_name=\"Anders Cases\" and smr_assign_pe_id = "
			+ "(SELECT distinct smr_assign_pe_id FROM stfaty_mobile_ref_cat, stfaty_mobile_referral WHERE smr_mrc_id = mrc_id and"
			+ " smr_sfa_code = 'sstfa')  and smr_sfa_code = 'sstfa' ) and smr_assign_pe_id = "
			+ "(SELECT distinct smr_assign_pe_id FROM stfaty_mobile_ref_cat, stfaty_mobile_referral WHERE smr_mrc_id = mrc_id and smr_sfa_code = 'sstfa' )";

	// Query to Verify the number of referrals in each ifp_motion_in_this_court
	// category
	public static final String IFP_MOTION_IN_THIS_COURT = "SELECT count(smr_id) FROM stfaty_mobile_referral WHERE smr_mrc_id = (SELECT distinct mrc_id FROM"
			+ " stfaty_mobile_ref_cat, stfaty_mobile_referral WHERE smr_mrc_id = mrc_id and mrc_name=\"IFP motion in this court\" and smr_assign_pe_id = "
			+ "(SELECT distinct smr_assign_pe_id FROM stfaty_mobile_ref_cat, stfaty_mobile_referral WHERE smr_mrc_id = mrc_id and"
			+ " smr_sfa_code = 'sstfa')  and smr_sfa_code = 'sstfa' ) and smr_assign_pe_id = "
			+ "(SELECT distinct smr_assign_pe_id FROM stfaty_mobile_ref_cat, stfaty_mobile_referral WHERE smr_mrc_id = mrc_id and smr_sfa_code = 'sstfa' )";

	// Query to Verify the number of referrals in each no_argument_referrals
	// category
	public static final String NO_ARGUMENT_REFERRALS = "SELECT count(smr_id) FROM stfaty_mobile_referral WHERE smr_mrc_id = (SELECT distinct mrc_id FROM"
			+ " stfaty_mobile_ref_cat, stfaty_mobile_referral WHERE smr_mrc_id = mrc_id and mrc_name=\"No Argument Referrals\" and smr_assign_pe_id = "
			+ "(SELECT distinct smr_assign_pe_id FROM stfaty_mobile_ref_cat, stfaty_mobile_referral WHERE smr_mrc_id = mrc_id and"
			+ " smr_sfa_code = 'sstfa')  and smr_sfa_code = 'sstfa' ) and smr_assign_pe_id = "
			+ "(SELECT distinct smr_assign_pe_id FROM stfaty_mobile_ref_cat, stfaty_mobile_referral WHERE smr_mrc_id = mrc_id and smr_sfa_code = 'sstfa' )";

	// Query to Verify the number of referrals in each pro_se_refs category
	public static final String PRO_SE_REFS = "SELECT count(smr_id) FROM stfaty_mobile_referral WHERE smr_mrc_id = (SELECT distinct mrc_id FROM"
			+ " stfaty_mobile_ref_cat, stfaty_mobile_referral WHERE smr_mrc_id = mrc_id and mrc_name=\"Pro Se Refs\" and smr_assign_pe_id = "
			+ "(SELECT distinct smr_assign_pe_id FROM stfaty_mobile_ref_cat, stfaty_mobile_referral WHERE smr_mrc_id = mrc_id and"
			+ " smr_sfa_code = 'sstfa')  and smr_sfa_code = 'sstfa' ) and smr_assign_pe_id = "
			+ "(SELECT distinct smr_assign_pe_id FROM stfaty_mobile_ref_cat, stfaty_mobile_referral WHERE smr_mrc_id = mrc_id and smr_sfa_code = 'sstfa' )";

	// Query to Verify the number of referrals in each summary_disposition
	// category
	public static final String SUMMARY_DISPOSITION = "SELECT count(smr_id) FROM stfaty_mobile_referral WHERE smr_mrc_id = (SELECT distinct mrc_id FROM"
			+ " stfaty_mobile_ref_cat, stfaty_mobile_referral WHERE smr_mrc_id = mrc_id and mrc_name=\"Summary Disposition\" and smr_assign_pe_id = "
			+ "(SELECT distinct smr_assign_pe_id FROM stfaty_mobile_ref_cat, stfaty_mobile_referral WHERE smr_mrc_id = mrc_id and"
			+ " smr_sfa_code = 'sstfa')  and smr_sfa_code = 'sstfa' ) and smr_assign_pe_id = "
			+ "(SELECT distinct smr_assign_pe_id FROM stfaty_mobile_ref_cat, stfaty_mobile_referral WHERE smr_mrc_id = mrc_id and smr_sfa_code = 'sstfa' )";

	// Query to Verify the number of referrals in each unassigned_referrals
	// category
	public static final String UNASSIGNED_REFERRALS = "SELECT count(smr_id) FROM stfaty_mobile_referral WHERE smr_mrc_id = (SELECT distinct mrc_id FROM"
			+ " stfaty_mobile_ref_cat, stfaty_mobile_referral WHERE smr_mrc_id = mrc_id and mrc_name=\"Unassigned Referrals\" and smr_assign_pe_id = "
			+ "(SELECT distinct smr_assign_pe_id FROM stfaty_mobile_ref_cat, stfaty_mobile_referral WHERE smr_mrc_id = mrc_id and"
			+ " smr_sfa_code = 'sstfa')  and smr_sfa_code = 'sstfa' ) and smr_assign_pe_id = "
			+ "(SELECT distinct smr_assign_pe_id FROM stfaty_mobile_ref_cat, stfaty_mobile_referral WHERE smr_mrc_id = mrc_id and smr_sfa_code = 'sstfa' )";

	public static final String NOTE_DPF_DEFAULT_DESCRIPTION = "select el_functions FROM event_list  where el_id="
			+ getId(COURT_USERS);

	public static final String DM_ACC_CRT = "select first 1 dm_acc_crt from  document order by dm_date_created desc";

	public static final String DM_ACC_CTLINK = "select first 1 dm_acc_ctlink from  document order by dm_date_created desc";

	public static final String DM_ACC_SPEC = "select first 1 dm_acc_spec from  document order by dm_date_created desc";

	public static final String DM_DESCRIPTION = "select first 1 dm_description from  document order by  dm_date_created desc";

	public static final String DOC_USER = "select distinct pr_prid from person, chm_mobile_referral, panel_case, panel, panel_to_judge, judge, personrole where "
			+ "cmr_id = " + getId(Constants.CMR_ID)
			+ " and cmr_ph_id = ph_id and pj_pn_id = ph_pn_id and pj_ju_ao_code = ju_ao_code and ju_pe_id = pe_id and  pe_pr_prid = pr_prid";

	public static final String DU_PRID = "select first " + executeQuery(DBType.CMKA, DOC_USER).size()
			+ " du_prid from  doc_user order by  du_date_created desc";

	// Query to get valid categories for the logged in user
	public static final String DB_LIST_OF_CATEGORIES = "SELECT DISTINCT (CYV_CATEGORY) FROM "
			+ "CHM_MOBILE_REFERRAL, CHM_REFTYPE_VAL WHERE  CMR_JU_PE_ID = ? AND CMR_DATE_END IS NULL  AND CMR_CYV_CODE = CYV_CODE AND CYV_IS_BRIEFCASE = 'y'";

	public static final String NON_ORALLY_ARGUED_CASES = "select distinct (cyv_category) from chm_mobile_referral, chm_reftype_val\n"
			+ "where \n" + "cmr_ju_pe_id = ? and\n" + "cmr_date_end is null and\n" + "cmr_cyv_code = cyv_code and\n"
			+ "cyv_is_briefcase = 'y' and\n" + "cyv_is_oral_arg = 'n'";

	public static final String BRIEFCASE_TARGET_ONLY_Y = "select count(distinct cs_caseid) from chm_mobile_referral, "
			+ "chm_reftype_val, case_dktentry, case where cmr_ju_pe_id = ? and cmr_cyv_code = cyv_code  and cyv_category = 'TEXT' and cmr_cs_caseid = cs_caseid and cmr_date_end is null and "
			+ "cd_caseid = cmr_cs_caseid and cmr_dktentryid = cd_dktentryid and cd_case_ext  = 1";

	public static final String BRIEFCASE_TARGET_ONLY_N = "select count(distinct cs_caseid) from chm_mobile_referral, "
			+ "chm_reftype_val, case_dktentry, case where cmr_ju_pe_id = ? and cmr_cyv_code = cyv_code  and cyv_category = 'TEXT' and cmr_cs_caseid = cs_caseid and cmr_date_end is null and "
			+ "cd_caseid = cmr_cs_caseid and cmr_dktentryid = cd_dktentryid";

	public static final String APPLICABLE_ACTIONS = "select el_list_text from mbr_event join event_list on el_id = me_el_id where "
			+ "(me_cyv_code = (select cmr_cyv_code from chm_mobile_referral where cmr_id = ?) or me_cyv_code = \"-\" "
			+ " or me_cyv_code is null) and (me_ic_code = (select cmr_ic_code from chm_mobile_referral where cmr_id = ?) "
			+ "or me_ic_code is null or me_ic_code = \"-\") and (me_cav_code = (select distinct(cav_code) FROM chm_mobile_referral JOIN chambers_case_to_referral "
			+ "on ccr_id = cmr_ccr_id JOIN chm_assign_to_case on chc_cpr_id = ccr_cpr_id and chc_cs_caseid = cmr_cs_caseid "
			+ "JOIN chambers_assignment on cha_id = chc_cha_id and cha_chm_pe_id = cmr_ju_pe_id JOIN chm_assign_type_val on cav_code = cha_cav_code JOIN chambers_assign_date "
			+ "on chd_cha_id = cha_id join chm_assign_datetype_val on cdv_code = chd_cdv_code "
			+ " WHERE chc_date_end is null and cmr_id = ?) or me_cav_code = \"-\" or me_cav_code is null) and el_functions is not null";

	public static final String STAFF_ASSIGNMENTS_LINKED_TO_THE_CASE = "SELECT distinct pr_first_name "
			+ "FROM chm_mobile_referral, chambers_case_to_referral, chm_assign_to_case, chambers_assignment, person, personrole, chm_assign_type_val\n"
			+ "WHERE\n" + "cmr_cs_caseid = 'TEXT' and\n" + "cmr_ccr_id = ccr_id and\n"
			+ "--ccr_cpr_id = chc_cpr_id and\n" + "chc_cs_caseid = cmr_cs_caseid and\n" + "chc_cpr_id = '1' and\n"
			+ "chc_cha_id = cha_id and\n" + "cha_ju_pe_id =? and\n" + "chc_date_end is null and\n"
			+ "cha_chm_pe_id = pe_id and\n" + "pe_pr_prid = pr_prid and\n" + "cha_cav_code = cav_code";

	public static final String CAV_DESCRIPTION = "SELECT distinct cav_description "
			+ "FROM chm_mobile_referral, chambers_case_to_referral, chm_assign_to_case, chambers_assignment, person, personrole, chm_assign_type_val\n"
			+ "WHERE\n" + "cmr_cs_caseid = 'TEXT' and\n" + "cmr_ccr_id = ccr_id and\n"
			+ "--ccr_cpr_id = chc_cpr_id and\n" + "chc_cs_caseid = cmr_cs_caseid and\n" + "chc_cpr_id = 1 and\n"
			+ "chc_cha_id = cha_id and\n" + "cha_ju_pe_id =? and\n" + "chc_date_end is null and\n"
			+ "cha_chm_pe_id = pe_id and\n" + "pe_pr_prid = pr_prid and\n" + "cha_cav_code = cav_code";

	public static final String ASSIGNMENT_DATE_TYPE = "SELECT cdv_display FROM chambers_assign_date ad, "
			+ "chm_assign_datetype_val,(Select max(chd_date) as maxnum, chd_cha_id from chambers_assign_date group by chd_cha_id) maxresults "
			+ "WHERE ad.chd_cha_id =? and chd_cdv_code = cdv_code and ad.chd_cha_id=  maxresults.chd_cha_id and ad.chd_date = maxresults.maxnum";

	public static final String ASSIGNMENT_DATE = "SELECT  ad.chd_date FROM chambers_assign_date ad, "
			+ "chm_assign_datetype_val,(Select max(chd_date) as maxnum, chd_cha_id from chambers_assign_date group by chd_cha_id) maxresults "
			+ "WHERE ad.chd_cha_id =? and chd_cdv_code = cdv_code and ad.chd_cha_id=  maxresults.chd_cha_id and ad.chd_date = maxresults.maxnum";

	public static final String MBR_NOTE = "select el_functions FROM event_list  where el_id=?";

	public static final String MBR_EVENT = "select * from mbr_event";

	public static final String ACTION_NAME = "select el_list_text FROM event_list where el_id=?";

	public static final String FILERS_INOFRMATION =

			"select pr_last_name, pr_first_name,  pr_middle_name,gn_display,  pt_display "
					+ " from chm_mobile_referral join chambers_case_to_referral on cmr_ccr_id = ccr_id join relate_dktpart "
					+ "on ccr_dp_dktpartid = rd_rel_dktpartid  join case_dktentry on rd_cre_cd_id = cd_id  join dktentry on "
					+ "cd_dktentryid = de_dktentryid  join dktperson on cd_id = dep_cd_id and dep_type = 'filer' and dep_py_pcid <> 1 "
					+ " join party on dep_py_pcid = py_pcid  join personrole on py_pe_id = pe_id  join pty_type_val"
					+ " on py_pt_code = pt_code  join person on pr_prid = pe_pr_prid  left join generation_val on gn_code = pr_gn_code "
					+ " where cmr_cs_caseid = 'TEXT' and cmr_ju_pe_id = ? and cmr_cyv_code = 'CODE' and cmr_ju_pe_id = ? ";

	public static final String FILERS_MIDDLE_NAME = "select pr_middle_name "
			+ " from chm_mobile_referral join chambers_case_to_referral on cmr_ccr_id = ccr_id join relate_dktpart "
			+ "on ccr_dp_dktpartid = rd_rel_dktpartid  join case_dktentry on rd_cre_cd_id = cd_id  join dktentry on "
			+ "cd_dktentryid = de_dktentryid  join dktperson on cd_id = dep_cd_id and dep_type = 'filer' and dep_py_pcid <> 1 "
			+ " join party on dep_py_pcid = py_pcid  join personrole on py_pe_id = pe_id  join pty_type_val"
			+ " on py_pt_code = pt_code  join person on pr_prid = pe_pr_prid  left join generation_val on gn_code = pr_gn_code "
			+ " where cmr_cs_caseid = 'TEXT' and cmr_ju_pe_id = ? and cmr_cyv_code = 'CODE' and cmr_ju_pe_id = ? ";

	public static final String FILED_DATE = "select  de_date_filed from chm_mobile_referral join chambers_case_to_referral on cmr_ccr_id = ccr_id "
			+ "join relate_dktpart on ccr_dp_dktpartid = rd_rel_dktpartid join case_dktentry on rd_cre_cd_id = cd_id "
			+ "join dktentry on cd_dktentryid = de_dktentryid join dktperson on cd_id = dep_cd_id and dep_type = 'filer' and dep_py_pcid <> 1 "
			+ "join party on dep_py_pcid = py_pcid join personrole on py_pe_id = pe_id join pty_type_val on py_pt_code = pt_code "
			+ "join person on pr_prid = pe_pr_prid left join generation_val on gn_code = pr_gn_code "
			+ "where cmr_cs_caseid = 'TEXT' and cmr_ju_pe_id = ? and cmr_cyv_code = 'CODE' and cmr_ju_pe_id = ?";

	public static final String JUDGE_VOTE_DPF_RELIEF = "select first 1 distinct  rl_list_text from chambers_case_to_referral b join chambers_case_to_referral a on"
			+ " b.ccr_cpr_id = a.ccr_cpr_id join dktpart on dp_dktpartid = b.ccr_dp_dktpartid join relief_list on rl_id = dp_rlid "
			+ "join chambers_caseref_to_judge on b.ccr_id = crj_ccr_id join judge on crj_ju_pe_id = ju_pe_id join chambers_referral on b.ccr_cpr_id = cpr_id "
			+ "join chambers_case on b.ccr_ccs_id = ccs_id join chm_reftype_val on cpr_cyv_code = cyv_code join case_dktentry on cd_id = dp_cd_id "
			+ "join dktentry on cd_dktentryid = de_dktentryid left join doctype_val on dp_doc_type = dty_code join event_list on el_id = de_elid"
			+ " left join chambers_vote left join (chm_vote_to_note inner join document on cvn_dm_dls_id = dm_dls_id and dm_internal_type = 'notevote'  ) on chv_id = cvn_chv_id "
			+ "on chv_crj_id = crj_id and chv_date_end is null left join chm_vote_val on chv_cvv_code = cvv_code where a.ccr_id = ? and b.ccr_date_end is null and "
			+ "NVL(cpr_vote_complete,'') <> 'y' and NVL(cpr_vote_req,'') <> 'n' order by  rl_list_text desc";

	public static final String JUDGES_INITIAL = "select ju_initials    from chambers_case_to_referral b join chambers_case_to_referral a on"
			+ " b.ccr_cpr_id = a.ccr_cpr_id join dktpart on dp_dktpartid = b.ccr_dp_dktpartid join relief_list on rl_id = dp_rlid "
			+ " join chambers_caseref_to_judge on b.ccr_id = crj_ccr_id join judge on crj_ju_pe_id = ju_pe_id join chambers_referral on b.ccr_cpr_id = cpr_id "
			+ " join chambers_case on b.ccr_ccs_id = ccs_id join chm_reftype_val on cpr_cyv_code = cyv_code join case_dktentry on cd_id = dp_cd_id "
			+ " join dktentry on cd_dktentryid = de_dktentryid left join doctype_val on dp_doc_type = dty_code join event_list on el_id = de_elid"
			+ " left join chambers_vote left join (chm_vote_to_note inner join document on cvn_dm_dls_id = dm_dls_id and dm_internal_type = 'notevote'  ) on chv_id = cvn_chv_id"
			+ " on chv_crj_id = crj_id and chv_date_end is null left join chm_vote_val on chv_cvv_code = cvv_code where a.ccr_id = ? and b.ccr_date_end is null and "
			+ "NVL(cpr_vote_complete,'') <> 'y' and NVL(cpr_vote_req,'') <> 'n' and   rl_list_text='TEXT' and cvv_display is not null  order by  chv_last_updated desc";

	public static final String JUDGES_VOTE = "select cvv_display  from chambers_case_to_referral b join chambers_case_to_referral a on"
			+ " b.ccr_cpr_id = a.ccr_cpr_id join dktpart on dp_dktpartid = b.ccr_dp_dktpartid join relief_list on rl_id = dp_rlid "
			+ " join chambers_caseref_to_judge on b.ccr_id = crj_ccr_id join judge on crj_ju_pe_id = ju_pe_id join chambers_referral on b.ccr_cpr_id = cpr_id "
			+ " join chambers_case on b.ccr_ccs_id = ccs_id join chm_reftype_val on cpr_cyv_code = cyv_code join case_dktentry on cd_id = dp_cd_id "
			+ " join dktentry on cd_dktentryid = de_dktentryid left join doctype_val on dp_doc_type = dty_code join event_list on el_id = de_elid"
			+ " left join chambers_vote left join (chm_vote_to_note inner join document on cvn_dm_dls_id = dm_dls_id and dm_internal_type = 'notevote'  ) on chv_id = cvn_chv_id"
			+ " on chv_crj_id = crj_id and chv_date_end is null left join chm_vote_val on chv_cvv_code = cvv_code where a.ccr_id = ? and b.ccr_date_end is null and "
			+ "NVL(cpr_vote_complete,'') <> 'y' and NVL(cpr_vote_req,'') <> 'n' and ju_initials='CODE' and   rl_list_text='TEXT' and cvv_display is not null  order by  chv_last_updated desc";

	public static final String JUDGES_VOTE_DATE = "select  first 1 distinct chv_last_updated   from chambers_case_to_referral b join chambers_case_to_referral a on"
			+ " b.ccr_cpr_id = a.ccr_cpr_id join dktpart on dp_dktpartid = b.ccr_dp_dktpartid join relief_list on rl_id = dp_rlid "
			+ " join chambers_caseref_to_judge on b.ccr_id = crj_ccr_id join judge on crj_ju_pe_id = ju_pe_id join chambers_referral on b.ccr_cpr_id = cpr_id "
			+ " join chambers_case on b.ccr_ccs_id = ccs_id join chm_reftype_val on cpr_cyv_code = cyv_code join case_dktentry on cd_id = dp_cd_id "
			+ " join dktentry on cd_dktentryid = de_dktentryid left join doctype_val on dp_doc_type = dty_code join event_list on el_id = de_elid"
			+ " left join chambers_vote left join (chm_vote_to_note inner join document on cvn_dm_dls_id = dm_dls_id and dm_internal_type = 'notevote'  ) on chv_id = cvn_chv_id"
			+ " on chv_crj_id = crj_id and chv_date_end is null left join chm_vote_val on chv_cvv_code = cvv_code where a.ccr_id = ? and b.ccr_date_end is null and "
			+ "NVL(cpr_vote_complete,'') <> 'y' and NVL(cpr_vote_req,'') <> 'n' and  ju_initials='CODE' and rl_list_text='TEXT'  and cvv_display is not null  order by  chv_last_updated desc";

	public static final String JUDGES_INITIALS = "select  distinct ju_initials   from chambers_case_to_referral b join chambers_case_to_referral a on"
			+ " b.ccr_cpr_id = a.ccr_cpr_id join dktpart on dp_dktpartid = b.ccr_dp_dktpartid join relief_list on rl_id = dp_rlid "
			+ " join chambers_caseref_to_judge on b.ccr_id = crj_ccr_id join judge on crj_ju_pe_id = ju_pe_id join chambers_referral on b.ccr_cpr_id = cpr_id "
			+ " join chambers_case on b.ccr_ccs_id = ccs_id join chm_reftype_val on cpr_cyv_code = cyv_code join case_dktentry on cd_id = dp_cd_id "
			+ " join dktentry on cd_dktentryid = de_dktentryid left join doctype_val on dp_doc_type = dty_code join event_list on el_id = de_elid"
			+ " left join chambers_vote left join (chm_vote_to_note inner join document on cvn_dm_dls_id = dm_dls_id and dm_internal_type = 'notevote'  ) on chv_id = cvn_chv_id"
			+ " on chv_crj_id = crj_id and chv_date_end is null left join chm_vote_val on chv_cvv_code = cvv_code where a.ccr_id = ? and b.ccr_date_end is null and "
			+ "NVL(cpr_vote_complete,'') <> 'y' and NVL(cpr_vote_req,'') <> 'n' and rl_list_text='TEXT'  order by  ju_initials desc";

	public static final String REFERRAL_CATEGORIES_FOR_STAFFATTORNEY = "select distinct(sfa_display) from stfaty_mobile_referral, stfaty_ref_assign, "
			+ "stfaty_assign_val where smr_ra_id = ra_id and ra_pe_id = ? and smr_sfa_code = sfa_code and smr_date_end is null";

	// Query to find Staff Members
	public static final String STAFF_MEMBERS_FIRST_NAME = "SELECT pr_first_name FROM group inner join member on gp_id = mb_gp_id_parent "
			+ " join personrole on pe_pr_prid = mb_ur_pr_prid join person on pe_pr_prid = pr_prid join user on ur_pr_prid = pr_prid where pe_rt_code TEXT and  "
			+ " gp_id in (select gp_id from group inner join member on gp_id = mb_gp_id_parent join person on pr_prid = mb_ur_pr_prid join personrole on pe_pr_prid = pr_prid  where pe_id = '?'"
			+ " and gp_name like '%Chambers%') and pe_date_end is null and pr_prid <> (select pr_prid from personrole join person on pe_pr_prid = pr_prid where pe_id = '?' and ur_date_disabled is null )";

	public static final String STAFF_MEMBERS_LAST_NAME = "SELECT pr_last_name FROM group inner join member on gp_id = mb_gp_id_parent "
			+ " join personrole on pe_pr_prid = mb_ur_pr_prid join person on pe_pr_prid = pr_prid join user on ur_pr_prid = pr_prid where  pe_rt_code TEXT and  "
			+ " gp_id in (select gp_id from group inner join member on gp_id = mb_gp_id_parent join person on pr_prid = mb_ur_pr_prid join personrole on pe_pr_prid = pr_prid  where pe_id = '?'"
			+ " and gp_name like '%Chambers%') and pe_date_end is null and pr_prid <> (select pr_prid from personrole join person on pe_pr_prid = pr_prid where pe_id = '?' and ur_date_disabled is null )";

	public static final String ASSIGNMENT_TYPE_IS_SKIP = "SELECT cav_display FROM chm_assign_type_val  WHERE cav_chm_role in ('staff', 'all')"
			+ " and cav_date_end is null  ORDER BY cav_display";

	public static final String ASSIGNMENT_TYPE_IS_COLON_DELIMITED_LIST = "SELECT cav_display \n"
			+ "	FROM chm_assign_type_val \n" + "	WHERE cav_chm_role in ('staff', 'all')\n"
			+ "	and cav_date_end is null\n" + "	and cav_code in (TEXT)\n" + "	ORDER BY cav_display";

	public static final String CHAMBERS_ASSIGNMENT = "select first 1 cha_id,cha_date_created  from chambers_assignment where cha_ju_pe_id = ? and cha_assigner_ju_pe_id =? order by cha_date_created desc";

	public static final String CHM_ASSIGN_TO_CASE = "select first 1 chc_cha_id,chc_date_created from chm_assign_to_case order by chc_date_created desc";

	public static final String CHAMBERS_ASSIGN_DATE = "select first 1 chd_cha_id,chd_date_created from chambers_assign_date order by chd_date_created desc";

	public static final String LATEST_CREATED_CASE = "select first 1 cs_caseid  from case order by cs_last_update desc ";

	public static final String ASSIGNEEs_FIRST_NAME = "select distinct  pr_first_name FROM chm_mobile_referral, "
			+ "chambers_case_to_referral, chm_assign_to_case, chambers_assignment, person, personrole, chm_assign_type_val WHERE cmr_cs_caseid = 'CMR_CS_CASEID'  and"
			+ " cmr_ccr_id = ccr_id and chc_cs_caseid = cmr_cs_caseid and chc_cha_id = cha_id and cha_ju_pe_id = '?'  and chc_date_end is null and"
			+ " cha_chm_pe_id = pe_id and pe_pr_prid = pr_prid  and cmr_cyv_code ='autotst' and cha_cav_code = cav_code";

	public static final String ASSIGNEEs_LAST_NAME = "select distinct   pr_last_name FROM chm_mobile_referral, "
			+ "chambers_case_to_referral, chm_assign_to_case, chambers_assignment, person, personrole, chm_assign_type_val WHERE cmr_cs_caseid = 'CMR_CS_CASEID'  and"
			+ " cmr_ccr_id = ccr_id and chc_cs_caseid = cmr_cs_caseid and chc_cha_id = cha_id and cha_ju_pe_id = '?'  and chc_date_end is null and"
			+ " cha_chm_pe_id = pe_id and pe_pr_prid = pr_prid  and cmr_cyv_code ='autotst' and cha_cav_code = cav_code";

	public static final String ASSIGNEES_CAV_DESCRIPTION = "select distinct cav_description FROM chm_mobile_referral, "
			+ "chambers_case_to_referral, chm_assign_to_case, chambers_assignment, person, personrole, chm_assign_type_val WHERE cmr_cs_caseid = 'CMR_CS_CASEID'  and"
			+ " cmr_ccr_id = ccr_id and chc_cs_caseid = cmr_cs_caseid and chc_cha_id = cha_id and cha_ju_pe_id = '?'  and chc_date_end is null and"
			+ " cha_chm_pe_id = pe_id and pe_pr_prid = pr_prid  and cmr_cyv_code ='autotst' and cha_cav_code = cav_code";

	public static final String ASSIGNEES_CHA_ID = "select distinct cha_id FROM chm_mobile_referral, "
			+ "chambers_case_to_referral, chm_assign_to_case, chambers_assignment, person, personrole, chm_assign_type_val WHERE cmr_cs_caseid = 'CMR_CS_CASEID'  and"
			+ " cmr_ccr_id = ccr_id and chc_cs_caseid = cmr_cs_caseid and chc_cha_id = cha_id and cha_ju_pe_id = '?'  and chc_date_end is null and"
			+ " cha_chm_pe_id = pe_id and pe_pr_prid = pr_prid  and cmr_cyv_code ='autotst' and cha_cav_code = cav_code";

	public static final String ASSIGNMENT_DUE_DATE = "SELECT chd_date FROM\n"
			+ "chambers_assign_date ad, chm_assign_datetype_val,\n"
			+ "    (Select max(chd_date) as maxnum, chd_cha_id\n" + "    from chambers_assign_date\n"
			+ "    group by chd_cha_id) maxresults\n" + "WHERE ad.chd_cha_id = ? and\n"
			+ "chd_cdv_code = cdv_code and\n" + "ad.chd_cha_id=  maxresults.chd_cha_id and\n"
			+ "ad.chd_date = maxresults.maxnum";

	public static final String CHAMBERS_PE_ID = "SELECT first 1 pe_id\n"
			+ "FROM group inner join member on gp_id = mb_gp_id_parent \n"
			+ "join personrole on pe_pr_prid = mb_ur_pr_prid \n" + "join person on pe_pr_prid = pr_prid \n"
			+ "join user on ur_pr_prid = pr_prid \n"
			+ "where  pr_first_name='PR_FIRST_NAME' and pr_last_name='PR_LAST_NAME' and   gp_id in (select gp_id from group inner join member on gp_id = mb_gp_id_parent join person on pr_prid = mb_ur_pr_prid \n"
			+ "join personrole on pe_pr_prid = pr_prid where pe_id = '?' and gp_name like '%Chambers%') and pe_date_end is null and pr_prid <> \n"
			+ "(select pr_prid from personrole join person on pe_pr_prid = pr_prid where pe_id = '?' and ur_date_disabled is null ) order by pe_date_created desc";

	public static final String CHC_DATE_END = "select first 1 chc_date_end from chambers_assignment \n"
			+ "join chambers_assign_date on cha_id = chd_cha_id \n"
			+ "join chm_assign_to_case on chc_cha_id = ? order by cha_last_updated desc";
	

	public static final String CHD_DATE = "select first 1 chd_date from chambers_assignment \n"
			+ "join chambers_assign_date on cha_id = chd_cha_id \n"
			+ "join chm_assign_to_case on chc_cha_id = ? order by cha_last_updated desc";

	public static final String CHA_CAV_CODE = "select first 1 cha_cav_code from chambers_assignment \n"
			+ "join chambers_assign_date on cha_id = chd_cha_id \n"
			+ "join chm_assign_to_case on chc_cha_id = ? order by cha_last_updated desc";
	

	public static final String CAV_CODE = "SELECT cav_code  \n" + "	FROM chm_assign_type_val \n"
			+ "	WHERE cav_display='TEXT' ";
	
	

}
