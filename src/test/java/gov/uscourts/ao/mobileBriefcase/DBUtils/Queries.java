package gov.uscourts.ao.mobileBriefcase.DBUtils;

public class Queries {
	static String a = "";
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
			+ "chc_cha_id = cha_id and cha_ju_pe_id = " + PE_ID
			+ " and chc_date_end is null and cha_chm_pe_id = pe_id and "
			+ "pe_pr_prid = pr_prid and cha_cav_code = cav_code";

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

	public static final String VOTE_INFORMATION = "SELECT CMR_ID FROM CHM_MOBILE_REFERRAL,"
			+ " CHAMBERS_CASE_TO_REFERRAL, CHAMBERS_REFERRAL WHERE CPR_VOTE_REQ = 'y' AND "
			+ "CMR_CCR_ID = CCR_ID AND CCR_CPR_ID = CPR_ID AND CMR_CS_CASEID = 82226 AND "
			+ "CMR_CYV_CODE = 'prhr' AND CMR_JU_PE_ID = " + PE_ID;

	public static final String FILERs_INFORMATION =

			"select pr_last_name, pr_first_name,  pr_middle_name,  pt_description"
					+ " from chm_mobile_referral join chambers_case_to_referral on cmr_ccr_id = ccr_id join relate_dktpart "
					+ "on ccr_dp_dktpartid = rd_rel_dktpartid  join case_dktentry on rd_cre_cd_id = cd_id  join dktentry on "
					+ "cd_dktentryid = de_dktentryid  join dktperson on cd_id = dep_cd_id and dep_type = 'filer' and dep_py_pcid <> 1 "
					+ " join party on dep_py_pcid = py_pcid  join personrole on py_pe_id = pe_id  join pty_type_val"
					+ " on py_pt_code = pt_code  join person on pr_prid = pe_pr_prid  left join generation_val on gn_code = pr_gn_code "
					+ " where cmr_cs_caseid = 82226 and cmr_ju_pe_id = 32 and cmr_cyv_code = 'prhr' and cmr_ju_pe_id = 32 ";

	public static final String JUDGEs_INITIALS = " SELECT ju_initials FROM chm_mobile_referral, "
			+ "judge WHERE cmr_ccr_id = 34870 and cmr_ju_pe_id = ju_pe_id ";

	public static final String FILED_DATE = ""
			+ "select  de_date_filed from chm_mobile_referral join chambers_case_to_referral on cmr_ccr_id = ccr_id "
			+ "join relate_dktpart on ccr_dp_dktpartid = rd_rel_dktpartid join case_dktentry on rd_cre_cd_id = cd_id "
			+ "join dktentry on cd_dktentryid = de_dktentryid join dktperson on cd_id = dep_cd_id and dep_type = 'filer' and dep_py_pcid <> 1 "
			+ "join party on dep_py_pcid = py_pcid join personrole on py_pe_id = pe_id join pty_type_val on py_pt_code = pt_code "
			+ "join person on pr_prid = pe_pr_prid left join generation_val on gn_code = pr_gn_code "
			+ "where cmr_cs_caseid = 82226 and cmr_ju_pe_id = 32 and cmr_cyv_code = 'prhr' and cmr_ju_pe_id = 32";

	public static final String RELIEF = "select distinct  rl_list_text from chambers_case_to_referral b join chambers_case_to_referral a on"
			+ " b.ccr_cpr_id = a.ccr_cpr_id join dktpart on dp_dktpartid = b.ccr_dp_dktpartid join relief_list on rl_id = dp_rlid "
			+ "join chambers_caseref_to_judge on b.ccr_id = crj_ccr_id join judge on crj_ju_pe_id = ju_pe_id join chambers_referral on b.ccr_cpr_id = cpr_id "
			+ "join chambers_case on b.ccr_ccs_id = ccs_id join chm_reftype_val on cpr_cyv_code = cyv_code join case_dktentry on cd_id = dp_cd_id "
			+ "join dktentry on cd_dktentryid = de_dktentryid left join doctype_val on dp_doc_type = dty_code join event_list on el_id = de_elid"
			+ " left join chambers_vote left join (chm_vote_to_note inner join document on cvn_dm_dls_id = dm_dls_id and dm_internal_type = 'notevote'  ) on chv_id = cvn_chv_id "
			+ "on chv_crj_id = crj_id and chv_date_end is null left join chm_vote_val on chv_cvv_code = cvv_code where a.ccr_id = 34870 and b.ccr_date_end is null and "
			+ "NVL(cpr_vote_complete,'') <> 'y' and NVL(cpr_vote_req,'') <> 'n' order by  rl_list_text desc";

	public static final String JUDGEs_VOTE = "select distinct  cvv_display from chambers_case_to_referral b join chambers_case_to_referral a on "
			+ "b.ccr_cpr_id = a.ccr_cpr_id join dktpart on dp_dktpartid = b.ccr_dp_dktpartid join relief_list on rl_id = dp_rlid "
			+ "join chambers_caseref_to_judge on b.ccr_id = crj_ccr_id join judge on crj_ju_pe_id = ju_pe_id join chambers_referral on b.ccr_cpr_id = cpr_id "
			+ "join chambers_case on b.ccr_ccs_id = ccs_id join chm_reftype_val on cpr_cyv_code = cyv_code join case_dktentry on cd_id = dp_cd_id "
			+ "join dktentry on cd_dktentryid = de_dktentryid left join doctype_val on dp_doc_type = dty_code join event_list on el_id = de_elid "
			+ "left join chambers_vote left join (chm_vote_to_note inner join document on cvn_dm_dls_id = dm_dls_id "
			+ "and dm_internal_type = 'notevote'  ) on chv_id = cvn_chv_id on chv_crj_id = crj_id and chv_date_end is null "
			+ "left join chm_vote_val on chv_cvv_code = cvv_code where a.ccr_id = 34870 and b.ccr_date_end is null and NVL(cpr_vote_complete,'') <> 'y' "
			+ "and NVL(cpr_vote_req,'') <> 'n' and cvv_display='Deny' and ju_initials='SMC'order by  cvv_display desc";

	public static final String VOTE_DATE = " select distinct  chv_date_created "
			+ "from chambers_case_to_referral b join chambers_case_to_referral a on b.ccr_cpr_id = a.ccr_cpr_id "
			+ "join dktpart on dp_dktpartid = b.ccr_dp_dktpartid join relief_list on rl_id = dp_rlid join chambers_caseref_to_judge on b.ccr_id = crj_ccr_id "
			+ "join judge on crj_ju_pe_id = ju_pe_id join chambers_referral on b.ccr_cpr_id = cpr_id join chambers_case on b.ccr_ccs_id = ccs_id "
			+ "join chm_reftype_val on cpr_cyv_code = cyv_code join case_dktentry on cd_id = dp_cd_id join dktentry on cd_dktentryid = de_dktentryid "
			+ "left join doctype_val on dp_doc_type = dty_code join event_list on el_id = de_elid left join chambers_vote left join (chm_vote_to_note inner join "
			+ "document on cvn_dm_dls_id = dm_dls_id and dm_internal_type = 'notevote'  ) on chv_id = cvn_chv_id on chv_crj_id = crj_id and chv_date_end is null "
			+ "left join chm_vote_val on chv_cvv_code = cvv_code where a.ccr_id = 34870 and b.ccr_date_end is null and NVL(cpr_vote_complete,'') <> 'y' and "
			+ "NVL(cpr_vote_req,'') <> 'n' and cvv_display='Deny' and ju_initials='SMC'order by chv_date_created  desc";

	public static final String APPLICABLE_ACTIONS = "select el_list_text from mbr_event join event_list on el_id = me_el_id where "
			+ "(me_cyv_code = (select cmr_cyv_code from chm_mobile_referral where cmr_id = 2302201) or me_cyv_code = \"-\" "
			+ "or me_cyv_code is null) and (me_ic_code = (select cmr_ic_code from chm_mobile_referral where cmr_id = 2302201) "
			+ "or me_ic_code is null or me_ic_code = \"-\") and (me_cav_code = (select distinct(cav_code) FROM chm_mobile_referral JOIN chambers_case_to_referral "
			+ "on ccr_id = cmr_ccr_id JOIN chm_assign_to_case on chc_cpr_id = ccr_cpr_id and chc_cs_caseid = cmr_cs_caseid "
			+ "JOIN chambers_assignment on cha_id = chc_cha_id and cha_chm_pe_id = cmr_ju_pe_id "
			+ "JOIN chm_assign_type_val on cav_code = cha_cav_code JOIN chambers_assign_date on chd_cha_id = cha_id "
			+ "join chm_assign_datetype_val on cdv_code = chd_cdv_code "
			+ "WHERE chc_date_end is null and cmr_id = 2302201) or me_cav_code = \"-\" or me_cav_code is null) and el_functions is not null ";

	public static final String ASSIGNMENT_CATEGORIES = "select distinct(sfa_display) from stfaty_mobile_referral, stfaty_ref_assign, "
			+ "stfaty_assign_val where smr_ra_id = ra_id and ra_pe_id = (SELECT distinct smr_assign_pe_id FROM stfaty_mobile_ref_cat, "
			+ "stfaty_mobile_referral WHERE smr_mrc_id = mrc_id and smr_sfa_code = 'sstfa' ) and smr_sfa_code = sfa_code and smr_date_end is null";

	public static final String REFERRAL_CATEGORIES = "SELECT distinct(mrc_name),mrc_id FROM stfaty_mobile_ref_cat, stfaty_mobile_referral"
			+ " WHERE smr_mrc_id = mrc_id and smr_assign_pe_id = (SELECT distinct smr_assign_pe_id FROM stfaty_mobile_ref_cat, stfaty_mobile_referral"
			+ " WHERE smr_mrc_id = mrc_id and smr_sfa_code = 'sstfa')  and smr_sfa_code = 'sstfa'  order by mrc_name";

}
