package gov.uscourts.ao.mobileBriefcase.DBUtils;

public class Queries {

	/** Query to find peId of the judge */
	public static final String PE_ID = "(select  pe_id from person, personrole where "
			+ "pe_pr_prid=pr_prid and pe_rt_code='PE_RT_CODE' and pr_last_name='PR_LAST_NAME')";

	/** Query to get valid categories for the logged in user */
	public static final String REFERRAL_CATEGORIES = "select distinct (cyv_category) from "
			+ "chm_mobile_referral, chm_reftype_val where  cmr_ju_pe_id = ? and cmr_date_end is null  and cmr_cyv_code = cyv_code and cyv_is_briefcase = 'y'";

	/**
	 * To find if a judge has any pending assignments run the following query for
	 * the logged in judge:
	 */
	public static final String PENDING_TASK_ASSIGNMENTS = "select  count(distinct concat(cav_display, chc_cs_caseid)) "
			+ "			 FROM chm_mobile_referral JOIN chambers_case_to_referral on ccr_id = cmr_ccr_id \n"
			+ "			 join chambers_referral on ccr_cpr_id = cpr_id \n"
			+ "			 JOIN chm_assign_to_case on chc_cpr_id = ccr_cpr_id and chc_cs_caseid = cmr_cs_caseid \n"
			+ "			 JOIN chambers_assignment on cha_id = chc_cha_id and cha_chm_pe_id = cmr_ju_pe_id \n"
			+ "			 JOIN chm_assign_type_val on cav_code = cha_cav_code \n"
			+ "			 JOIN chambers_assign_date on chd_cha_id = cha_id \n"
			+ "			 join chm_assign_datetype_val on cdv_code = chd_cdv_code \n"
			+ "			 WHERE chc_date_end is null and cmr_ju_pe_id = ? and cmr_date_end is null; ";

	/** Query to find the valid non-orally argued categories for the judge: */
	public static final String NON_ORALLY_ARGUED_CASES = "select distinct (cyv_category) from chm_mobile_referral, chm_reftype_val\n"
			+ "where \n" + "cmr_ju_pe_id = ? and\n" + "cmr_date_end is null and\n" + "cmr_cyv_code = cyv_code and\n"
			+ "cyv_is_briefcase = 'y' and\n" + "cyv_is_oral_arg = 'n' and  cmr_cyv_code != 'CMR_CYV_CODE'";

	/**
	 * Query the site table where si_code = 'briefcaseTargetOnly' if the si_value =
	 * 'y', run the following query:
	 */
	public static final String BRIEFCASE_TARGET_ONLY_Y = "select count(distinct cs_caseid) from chm_mobile_referral, "
			+ "chm_reftype_val, case_dktentry, case where cmr_ju_pe_id = ? and cmr_cyv_code = cyv_code  and cyv_category = 'CYV_CATEGORY' and cmr_cs_caseid = cs_caseid and cmr_date_end is null and "
			+ "cd_caseid = cmr_cs_caseid and cmr_dktentryid = cd_dktentryid and cd_case_ext  = 1";
	/**
	 * Query the site table where si_code = 'briefcaseTargetOnly' if the si_value =
	 * 'n' or does not exist, run the following query:
	 */
	public static final String BRIEFCASE_TARGET_ONLY_N = "select count(distinct cs_caseid) from chm_mobile_referral, "
			+ "chm_reftype_val, case_dktentry, case where cmr_ju_pe_id = ? and cmr_cyv_code = cyv_code  and cyv_category = 'CYV_CATEGORY' and cmr_cs_caseid = cs_caseid and cmr_date_end is null and "
			+ "cd_caseid = cmr_cs_caseid and cmr_dktentryid = cd_dktentryid";

	/**
	 * Query the stfaty_mobile_referral, and stfaty_assign_val table to get valid
	 * assignment categories for the logged in user
	 */
	public static final String SAs_ASSIGNMENT_CATEGORIES = "select distinct(sfa_display) from stfaty_mobile_referral, stfaty_ref_assign, stfaty_assign_val\n"
			+ "where smr_ra_id = ra_id and\n" + "ra_pe_id = 'RA_PE_ID' and\n" + "smr_sfa_code = sfa_code and\n"
			+ "smr_date_end is null";

	/** Observe there are six referral categories listed */

	public static final String SAs_REFERRAL_CATEGORIES =

			"SELECT distinct ( mrc_name)  FROM stfaty_mobile_ref_cat, stfaty_mobile_referral\n"
					+ "WHERE smr_mrc_id = mrc_id and\n" + "smr_assign_pe_id = 'SMR_ASSIGN_PE_ID'  and\n"
					+ "smr_sfa_code = 'sstfa'  \n" + "order by mrc_name";

	public static final String ID_OF_THE_REFERRAL_CATEGORY = "SELECT distinct(mrc_id),mrc_id FROM stfaty_mobile_ref_cat, stfaty_mobile_referral"
			+ " WHERE smr_mrc_id = mrc_id and smr_assign_pe_id = 'SMR_ASSIGN_PE_ID' and  mrc_name='MRC_NAME'  and smr_sfa_code = 'sstfa'";

	/** Query to to find the number of referrals in each categories */
	public static final String REFERRAL_NUMBERS = "SELECT count(smr_id) FROM stfaty_mobile_referral WHERE smr_mrc_id = SMR_MRC_ID "
			+ "and smr_assign_pe_id = 'SMR_ASSIGN_PE_ID' and smr_sfa_code = 'sstfa'";

	public static final String SAs_DOCUMENT_CATEGORIES = "SELECT distinct cmd_doc_category FROM chm_mobile_docs, stfaty_mobile_referral\n"
			+ "WHERE smr_assign_pe_id ='SMR_ASSIGN_PE_ID'  and\n" + "smr_mrc_id = SMR_MRC_ID  and\n"
			+ "smr_id = cmd_smr_id ";

	public static final String DOCUMENT_DESCRIPTION = "SELECT distinct cmd_description FROM chm_mobile_docs, stfaty_mobile_referral\n"
			+ "WHERE smr_assign_pe_id = 'SMR_ASSIGN_PE_ID' and smr_sfa_code='sstfa' and\n"
			+ "smr_mrc_id = SMR_MRC_ID  and\n" + "smr_id = cmd_smr_id and cmd_doc_category = 'CMD_DOC_CATEGORY'";

	public static final String CASE_ID = "SELECT cs_caseid FROM case WHERE cs_year = 'CS_YEAR' and cs_number = 'CS_NUMBER'";

	public static final String DKT_ENTRY_ID = "SELECT first 1 distinct cmr_dktentryid FROM chm_mobile_referral WHERE cmr_cs_caseid = ?";

	public static final String CMD_DM_DLS_ID = " SELECT first 1 cmd_dm_dls_id FROM chm_mobile_docs, chm_mobile_referral\n"
			+ "		 WHERE cmd_cmr_id = cmr_id  and cmr_cs_caseid =?";

	public static final String DM_DLS_ID = "SELECT dm_dls_id FROM document, case_dktentry where cd_caseid = ? and "
			+ "dm_dktentryid = cd_dktentryid and dm_internal_type = 'noteTrans'";

	public static final String MBR_EVENT = "select * from mbr_event";

	public static final String APPLICABLE_ACTIONS = "select el_list_text from mbr_event join event_list on el_id = me_el_id where "
			+ "(me_cyv_code = (select cmr_cyv_code from chm_mobile_referral where cmr_id = ?) or me_cyv_code = \"-\" "
			+ " or me_cyv_code is null) and (me_ic_code = (select cmr_ic_code from chm_mobile_referral where cmr_id = ?) "
			+ "or me_ic_code is null or me_ic_code = \"-\") and (me_cav_code = (select distinct(cav_code) FROM chm_mobile_referral JOIN chambers_case_to_referral "
			+ "on ccr_id = cmr_ccr_id JOIN chm_assign_to_case on chc_cpr_id = ccr_cpr_id and chc_cs_caseid = cmr_cs_caseid "
			+ "JOIN chambers_assignment on cha_id = chc_cha_id and cha_chm_pe_id = cmr_ju_pe_id JOIN chm_assign_type_val on cav_code = cha_cav_code JOIN chambers_assign_date "
			+ "on chd_cha_id = cha_id join chm_assign_datetype_val on cdv_code = chd_cdv_code "
			+ " WHERE chc_date_end is null and cmr_id = ?) or me_cav_code = \"-\" or me_cav_code is null) and el_functions is not null";

	public static final String ACTION_NAME = "select el_list_text FROM event_list where el_id=?";

	public static final String EL_ID = "select  el_id FROM event_list where  el_list_text ='?'";

	public static final String JUDGE_VOTE_DPF_RELIEF = "select first 1 distinct  rl_list_text from chambers_case_to_referral b join chambers_case_to_referral a on"
			+ " b.ccr_cpr_id = a.ccr_cpr_id join dktpart on dp_dktpartid = b.ccr_dp_dktpartid join relief_list on rl_id = dp_rlid "
			+ "join chambers_caseref_to_judge on b.ccr_id = crj_ccr_id join judge on crj_ju_pe_id = ju_pe_id join chambers_referral on b.ccr_cpr_id = cpr_id "
			+ "join chambers_case on b.ccr_ccs_id = ccs_id join chm_reftype_val on cpr_cyv_code = cyv_code join case_dktentry on cd_id = dp_cd_id "
			+ "join dktentry on cd_dktentryid = de_dktentryid left join doctype_val on dp_doc_type = dty_code join event_list on el_id = de_elid"
			+ " left join chambers_vote left join (chm_vote_to_note inner join document on cvn_dm_dls_id = dm_dls_id and dm_internal_type = 'notevote'  ) on chv_id = cvn_chv_id "
			+ "on chv_crj_id = crj_id and chv_date_end is null left join chm_vote_val on chv_cvv_code = cvv_code where a.ccr_id = ? and b.ccr_date_end is null and "
			+ "NVL(cpr_vote_complete,'') <> 'y' and NVL(cpr_vote_req,'') <> 'n' order by  rl_list_text asc";

	public static final String JUDGES_INITIALS = "select  distinct ju_initials   from chambers_case_to_referral b join chambers_case_to_referral a on"
			+ " b.ccr_cpr_id = a.ccr_cpr_id join dktpart on dp_dktpartid = b.ccr_dp_dktpartid join relief_list on rl_id = dp_rlid "
			+ " join chambers_caseref_to_judge on b.ccr_id = crj_ccr_id join judge on crj_ju_pe_id = ju_pe_id join chambers_referral on b.ccr_cpr_id = cpr_id "
			+ " join chambers_case on b.ccr_ccs_id = ccs_id join chm_reftype_val on cpr_cyv_code = cyv_code join case_dktentry on cd_id = dp_cd_id "
			+ " join dktentry on cd_dktentryid = de_dktentryid left join doctype_val on dp_doc_type = dty_code join event_list on el_id = de_elid"
			+ " left join chambers_vote left join (chm_vote_to_note inner join document on cvn_dm_dls_id = dm_dls_id and dm_internal_type = 'notevote'  ) on chv_id = cvn_chv_id"
			+ " on chv_crj_id = crj_id and chv_date_end is null left join chm_vote_val on chv_cvv_code = cvv_code where a.ccr_id = ? and b.ccr_date_end is null and "
			+ "NVL(cpr_vote_complete,'') <> 'y' and NVL(cpr_vote_req,'') <> 'n' and rl_list_text='RL_LIST_TEXT'  order by  ju_initials desc";

	public static final String JUDGES_INITIAL = "select ju_initials    from chambers_case_to_referral b join chambers_case_to_referral a on"
			+ " b.ccr_cpr_id = a.ccr_cpr_id join dktpart on dp_dktpartid = b.ccr_dp_dktpartid join relief_list on rl_id = dp_rlid "
			+ " join chambers_caseref_to_judge on b.ccr_id = crj_ccr_id join judge on crj_ju_pe_id = ju_pe_id join chambers_referral on b.ccr_cpr_id = cpr_id "
			+ " join chambers_case on b.ccr_ccs_id = ccs_id join chm_reftype_val on cpr_cyv_code = cyv_code join case_dktentry on cd_id = dp_cd_id "
			+ " join dktentry on cd_dktentryid = de_dktentryid left join doctype_val on dp_doc_type = dty_code join event_list on el_id = de_elid"
			+ " left join chambers_vote left join (chm_vote_to_note inner join document on cvn_dm_dls_id = dm_dls_id and dm_internal_type = 'notevote'  ) on chv_id = cvn_chv_id"
			+ " on chv_crj_id = crj_id and chv_date_end is null left join chm_vote_val on chv_cvv_code = cvv_code where a.ccr_id = ? and b.ccr_date_end is null and "
			+ "NVL(cpr_vote_complete,'') <> 'y' and NVL(cpr_vote_req,'') <> 'n' and   rl_list_text='RL_LIST_TEXT' and cvv_display is not null  order by  chv_last_updated desc";

	public static final String JUDGES_VOTE = "select cvv_display  from chambers_case_to_referral b join chambers_case_to_referral a on"
			+ " b.ccr_cpr_id = a.ccr_cpr_id join dktpart on dp_dktpartid = b.ccr_dp_dktpartid join relief_list on rl_id = dp_rlid "
			+ " join chambers_caseref_to_judge on b.ccr_id = crj_ccr_id join judge on crj_ju_pe_id = ju_pe_id join chambers_referral on b.ccr_cpr_id = cpr_id "
			+ " join chambers_case on b.ccr_ccs_id = ccs_id join chm_reftype_val on cpr_cyv_code = cyv_code join case_dktentry on cd_id = dp_cd_id "
			+ " join dktentry on cd_dktentryid = de_dktentryid left join doctype_val on dp_doc_type = dty_code join event_list on el_id = de_elid"
			+ " left join chambers_vote left join (chm_vote_to_note inner join document on cvn_dm_dls_id = dm_dls_id and dm_internal_type = 'notevote'  ) on chv_id = cvn_chv_id"
			+ " on chv_crj_id = crj_id and chv_date_end is null left join chm_vote_val on chv_cvv_code = cvv_code where a.ccr_id = ? and b.ccr_date_end is null and "
			+ "NVL(cpr_vote_complete,'') <> 'y' and NVL(cpr_vote_req,'') <> 'n' and ju_initials='JU_INITIALS' and   rl_list_text='RL_LIST_TEXT' and cvv_display is not null  order by  chv_last_updated desc";

	public static final String JUDGES_VOTE_DATE = "select  first 1 distinct chv_last_updated   from chambers_case_to_referral b join chambers_case_to_referral a on"
			+ " b.ccr_cpr_id = a.ccr_cpr_id join dktpart on dp_dktpartid = b.ccr_dp_dktpartid join relief_list on rl_id = dp_rlid "
			+ " join chambers_caseref_to_judge on b.ccr_id = crj_ccr_id join judge on crj_ju_pe_id = ju_pe_id join chambers_referral on b.ccr_cpr_id = cpr_id "
			+ " join chambers_case on b.ccr_ccs_id = ccs_id join chm_reftype_val on cpr_cyv_code = cyv_code join case_dktentry on cd_id = dp_cd_id "
			+ " join dktentry on cd_dktentryid = de_dktentryid left join doctype_val on dp_doc_type = dty_code join event_list on el_id = de_elid"
			+ " left join chambers_vote left join (chm_vote_to_note inner join document on cvn_dm_dls_id = dm_dls_id and dm_internal_type = 'notevote'  ) on chv_id = cvn_chv_id"
			+ " on chv_crj_id = crj_id and chv_date_end is null left join chm_vote_val on chv_cvv_code = cvv_code where a.ccr_id = ? and b.ccr_date_end is null and "
			+ "NVL(cpr_vote_complete,'') <> 'y' and NVL(cpr_vote_req,'') <> 'n' and  ju_initials='JU_INITIALS' and rl_list_text='RL_LIST_TEXT'  and cvv_display is not null  order by  chv_last_updated desc";

	public static final String MBR_NOTE = "select el_functions FROM event_list  where el_id=?";

	public static final String lbrrpt_CATEGORY = "select distinct (cmr_cyv_code) from chm_mobile_referral, chm_reftype_val where cmr_ju_pe_id = ? and cmr_date_end is null and cmr_cyv_code = cyv_code and\n"
			+ "			cyv_is_briefcase = 'y' and cyv_is_oral_arg = 'n' ";

	public static final String lbrrpt_CYV_CATEGORY = "select distinct (cyv_category) from chm_mobile_referral, chm_reftype_val where cmr_ju_pe_id = ? and cmr_date_end is null and cmr_cyv_code = cyv_code and\n"
			+ "			cyv_is_briefcase = 'y' and cyv_is_oral_arg = 'n' and cmr_cyv_code = 'CMR_CYV_CODE'";

	public static final String lbrrpt_DOCUMENT_CATEGORY = "select distinct cmd_doc_category  from chm_mobile_docs join chm_mobile_referral on cmr_id = cmd_cmr_id where cmr_cyv_code = 'lbrrpt' and cmr_date_end is null and cmr_ju_pe_id =? ";

	public static final String REFERRAL_DOCUMENTS = "select cmd_description from chm_mobile_docs join chm_mobile_referral on cmr_id = cmd_cmr_id where cmr_cyv_code = 'lbrrpt' and cmr_date_end is null and cmd_doc_category='CMD_DOC_CATEGORY' and cmr_ju_pe_id =? ";

	public static final String DM_ACC_CRT = "select first 1 dm_acc_crt from  document where dm_date_created >='DM_DATE_CREATED'";
	// public static final String DM_ACC_CRT = "select first 1 dm_acc_crt from
	// document order by dm_date_created desc";

	public static final String DM_ACC_CTLINK = "select first 1 dm_acc_ctlink from  document where dm_date_created>='DM_DATE_CREATED'";
	// public static final String DM_ACC_CTLINK = "select first 1 dm_acc_ctlink from
	// document order by dm_date_created desc";

	public static final String DM_ACC_SPEC = "select first 1 dm_acc_spec from  document where dm_date_created>='DM_DATE_CREATED'";
	// public static final String DM_ACC_SPEC = "select first 1 dm_acc_spec from
	// document order by dm_date_created desc";

	// public static final String DU_DATE_CREATED = "select first 1 du_date_created
	// from doc_user order by du_date_created desc";
	// public static final String DM_DATE_CREATED = "select first 1 dm_date_created
	// from document order by dm_date_created desc";

	// public static final String DCG_DATE_CREATED = "select first 1
	// dcg_date_created from doc_group order by dcg_date_created desc";

	public static final String DM_DESCRIPTION = "select first 1 dm_description from  document order by  dm_date_created desc";

	public static final String DU_PRID = "select  du_prid  from  doc_user where  du_date_created>='DU_DATE_CREATED'";
	// public static final String DU_PRID = "select du_prid from doc_user where
	// du_date_created='DU_DATE_CREATED'";

	public static final String DCG_GROUP = "select  dcg_group from  doc_group  where dcg_date_created>='DCG_DATE_CREATED'";
	// public static final String DCG_GROUP = "select dcg_group from doc_group where
	// dcg_date_created='DCG_DATE_CREATED'";

	public static final String CHAMBERS_GROUP_ID = "select distinct gp_id from group, personrole, person, member where "
			+ "mb_ur_pr_prid = pr_prid and mb_gp_id_parent = gp_id and pe_pr_prid=? and gp_name LIKE '%PR_LAST_NAME''s Chambers%'";

	public static final String cmr_id = "SELECT cmr_id FROM case, chm_mobile_referral WHERE cs_year = 'CS_YEAR' and cs_number = 'CS_NUMBER' and cs_caseid = cmr_cs_caseid "
			+ "and cmr_cyv_code='CMR_CYV_CODE' and cmr_ju_pe_id ='CMR_JU_PE_ID'";

	public static final String PANEL_JUDGES_PR_PRID = "select distinct pr_prid from person, chm_mobile_referral, panel_case, panel, panel_to_judge, judge, personrole\n"
			+ "where \n" + "cmr_id = 'CMR_ID'" + "" + " and\n" + "cmr_ph_id = ph_id and\n" + "--ph_pn_id = pn_id and\n"
			+ "pj_pn_id = ph_pn_id and\n" + "pj_ju_ao_code = ju_ao_code and\n" + "ju_pe_id" + "" + " = pe_id and \n"
			+ "pe_pr_prid = pr_prid and\n" + "pj_date_remove is null";

	public static final String LOGED_IN_JUDGES_PR_PRID = "select distinct pr_prid from person, chm_mobile_referral, panel_case, panel, panel_to_judge, judge, personrole\n"
			+ "where \n" + "cmr_id = 'CMR_ID'" + "" + " and\n" + "cmr_ph_id = ph_id and\n" + "--ph_pn_id = pn_id and\n"
			+ "pj_pn_id = ph_pn_id and\n" + "pj_ju_ao_code = ju_ao_code and\n" + "ju_pe_id" + "" + " = pe_id and \n"
			+ "pe_pr_prid = pr_prid and ju_pe_id = ?";

	public static final String LOGED_IN_JUDGES_LAST_NAME = "select distinct pr_last_name from person, chm_mobile_referral, panel_case, panel, panel_to_judge, judge, personrole\n"
			+ "where \n" + "cmr_id = 'CMR_ID'" + "" + " and\n" + "cmr_ph_id = ph_id and\n" + "--ph_pn_id = pn_id and\n"
			+ "pj_pn_id = ph_pn_id and\n" + "pj_ju_ao_code = ju_ao_code and\n" + "ju_pe_id" + "" + " = pe_id and \n"
			+ "pe_pr_prid = pr_prid and ju_pe_id = ?";

	public static final String PR_LAST_NAME = "select distinct pr_last_name   from person, chm_mobile_referral, panel_case, panel, panel_to_judge, judge, personrole\n"
			+ "where \n" + "cmr_id = CMR_ID and\n" + "cmr_ph_id = ph_id and\n" + "--ph_pn_id = pn_id and\n"
			+ "pj_pn_id = ph_pn_id and\n" + "pj_ju_ao_code = ju_ao_code and\n" + "ju_pe_id = pe_id and \n"
			+ "pe_pr_prid = pr_prid and pr_prid='PR_PRID' and pj_date_remove is null";

	public static final String STAFF_ASSIGNMENTS_ASSOCIATED_WITH_THE_REFERRAL = "SELECT distinct pr_first_name, cha_id\n"
			+ "	FROM chm_mobile_referral, chambers_case_to_referral, chm_assign_to_case, chambers_assignment, person, personrole, chm_assign_type_val\n"
			+ "	WHERE\n" + "	cmr_cs_caseid = CMR_CS_CASEID  and\n" + "	cmr_ccr_id = ccr_id and\n"
			+ "	ccr_cpr_id = chc_cpr_id and\n" + "	chc_cs_caseid = cmr_cs_caseid and\n"
			+ "	chc_cha_id = cha_id and\n" + "	cha_ju_pe_id = CHA_JU_PE_ID  and\n" + "	chc_date_end is null and\n"
			+ "	cha_chm_pe_id = pe_id and\n" + "	pe_pr_prid = pr_prid and\n"
			+ "	cmr_cyv_code in (Select cyv_code from chm_reftype_val where cyv_category = 'CYV_CATEGORY') and\n"
			+ "	cha_cav_code = cav_code";

	public static final String CDV_DESCRIPTION = "SELECT cdv_description FROM\n"
			+ "chambers_assign_date ad, chm_assign_datetype_val,\n"
			+ "    (Select max(chd_date) as maxnum, chd_cha_id\n" + "    from chambers_assign_date\n"
			+ "    group by chd_cha_id) maxresults\n" + "WHERE ad.chd_cha_id='CHD_CHA_ID' and\n"
			+ "chd_cdv_code = cdv_code and\n" + "ad.chd_cha_id=  maxresults.chd_cha_id and\n"
			+ "ad.chd_date = maxresults.maxnum";

	public static final String AD_CHD_DATE = "SELECT ad.chd_date FROM\n"
			+ "chambers_assign_date ad, chm_assign_datetype_val,\n"
			+ "    (Select max(chd_date) as maxnum, chd_cha_id\n" + "    from chambers_assign_date\n"
			+ "    group by chd_cha_id) maxresults\n" + "WHERE ad.chd_cha_id='CHD_CHA_ID' and\n"
			+ "chd_cdv_code = cdv_code and\n" + "ad.chd_cha_id=  maxresults.chd_cha_id and\n"
			+ "ad.chd_date = maxresults.maxnum";

	// Query to find Staff Members
	public static final String STAFF_MEMBERS_FIRST_NAME = "SELECT distinct pr_first_name, pr_last_name FROM group inner join member on gp_id = mb_gp_id_parent "
			+ " join personrole on pe_pr_prid = mb_ur_pr_prid join person on pe_pr_prid = pr_prid join user on ur_pr_prid = pr_prid where pe_rt_code TEXT and  "
			+ " gp_id in (select gp_id from group inner join member on gp_id = mb_gp_id_parent join person on pr_prid = mb_ur_pr_prid join personrole on pe_pr_prid = pr_prid  where pe_id = '?'"
			+ " and gp_name like '%Chambers%') and pe_date_end is null and pr_prid <> (select pr_prid from personrole join person on pe_pr_prid = pr_prid where pe_id = '?' and ur_date_disabled is null )";

	public static final String ASSIGNMENT_TYPE_IS_SKIP = "SELECT cav_display FROM chm_assign_type_val  WHERE cav_chm_role in ('staff', 'all')"
			+ " and cav_date_end is null  ORDER BY cav_display";

	public static final String ASSIGNMENT_TYPE_IS_COLON_DELIMITED_LIST = "SELECT cav_display \n"
			+ "	FROM chm_assign_type_val \n" + "	WHERE cav_chm_role in ('staff', 'all')\n"
			+ "	and cav_date_end is null\n" + "	and cav_code in (TEXT)\n" + " and 	ORDER BY cav_display";
	// " cmr_cyv_code='prhr'/

	public static final String CAV_DESCRIPTION = "SELECT distinct cav_description FROM chm_mobile_referral, chambers_case_to_referral, chm_assign_to_case, chambers_assignment, "
			+ "person, personrole, chm_assign_type_val WHERE cmr_cs_caseid = CMR_CS_CASEID  and cmr_ccr_id = ccr_id and  chc_cs_caseid = cmr_cs_caseid and "
			+ "chc_cha_id = cha_id and cha_ju_pe_id = ?  and chc_date_end is  null and cha_chm_pe_id = pe_id and pe_pr_prid = pr_prid and"
			+ " cmr_cyv_code='CMR_CYV_CODE' and  cha_cav_code = cav_code and pr_first_name='PR_FIRST_NAME' and pr_last_name='PR_LAST_NAME' ";

	public static final String TERTMINATED_DATE = "SELECT  distinct chc_date_end FROM chm_mobile_referral, chambers_case_to_referral, chm_assign_to_case, chambers_assignment, "
			+ "person, personrole, chm_assign_type_val WHERE cmr_cs_caseid = CMR_CS_CASEID  and cmr_ccr_id = ccr_id and  chc_cs_caseid = cmr_cs_caseid and "
			+ "chc_cha_id = cha_id and cha_ju_pe_id = ?  and cav_description='CAV_DESCRIPTION' and chc_date_end is null and chc_date_end is not null  and cha_chm_pe_id = pe_id and pe_pr_prid = pr_prid and"
			+ " cmr_cyv_code='CMR_CYV_CODE' and  cha_cav_code = cav_code and pr_first_name='PR_FIRST_NAME' and pr_last_name='PR_LAST_NAME' ";

	public static final String CHAMBERS_ASSIGNMENT = "select first 1 cha_id,cha_date_created  from chambers_assignment where cha_ju_pe_id = ? and cha_assigner_ju_pe_id =? order by cha_date_created desc";

	public static final String CHM_ASSIGN_TO_CASE = "select first 1 chc_cha_id,chc_date_created from chm_assign_to_case order by chc_date_created desc";

	public static final String CHAMBERS_ASSIGN_DATE = "select first 1 chd_cha_id,chd_date_created from chambers_assign_date order by chd_date_created desc";

	public static final String LATEST_CREATED_CASE = "select first 1 cs_caseid  from case order by cs_last_update desc ";

	// cmr_cyv_code ='prhr'
	public static final String ASSIGNEEs_FIRST_NAME = "select first 1  pr_first_name FROM chm_mobile_referral, "
			+ "chambers_case_to_referral, chm_assign_to_case, chambers_assignment, person, personrole, chm_assign_type_val WHERE cmr_cs_caseid = 'CMR_CS_CASEID'  and"
			+ " cmr_ccr_id = ccr_id and chc_cs_caseid = cmr_cs_caseid and chc_cha_id = cha_id and cha_ju_pe_id = '?'  and chc_date_end is null and"
			+ " cha_chm_pe_id = pe_id and pe_pr_prid = pr_prid  and cmr_cyv_code ='prhr' and cha_cav_code = cav_code order by  chc_last_updated desc";

	public static final String ASSIGNEEs_LAST_NAME = "select first 1  pr_last_name FROM chm_mobile_referral, "
			+ "chambers_case_to_referral, chm_assign_to_case, chambers_assignment, person, personrole, chm_assign_type_val WHERE cmr_cs_caseid = 'CMR_CS_CASEID'  and"
			+ " cmr_ccr_id = ccr_id and chc_cs_caseid = cmr_cs_caseid and chc_cha_id = cha_id and cha_ju_pe_id = '?'  and chc_date_end is null and"
			+ " cha_chm_pe_id = pe_id and pe_pr_prid = pr_prid  and cmr_cyv_code ='prhr' and cha_cav_code = cav_code order by  chc_last_updated desc";

	public static final String ASSIGNEES_CAV_DESCRIPTION = "select first 1  cav_description FROM chm_mobile_referral, "
			+ "chambers_case_to_referral, chm_assign_to_case, chambers_assignment, person, personrole, chm_assign_type_val WHERE cmr_cs_caseid = 'CMR_CS_CASEID'  and"
			+ " cmr_ccr_id = ccr_id and chc_cs_caseid = cmr_cs_caseid and chc_cha_id = cha_id and cha_ju_pe_id = '?'  and chc_date_end is null and"
			+ " cha_chm_pe_id = pe_id and pe_pr_prid = pr_prid  and cmr_cyv_code ='prhr' and cha_cav_code = cav_code order by  chc_last_updated desc";

	// cmr_cyv_code ='prhr'
	public static final String ASSIGNEES_CHA_ID = "select first 1 cha_id FROM chm_mobile_referral, "
			+ "chambers_case_to_referral, chm_assign_to_case, chambers_assignment, person, personrole, chm_assign_type_val WHERE cmr_cs_caseid = 'CMR_CS_CASEID'  and"
			+ " cmr_ccr_id = ccr_id and chc_cs_caseid = cmr_cs_caseid and chc_cha_id = cha_id and cha_ju_pe_id = '?'  and chc_date_end is null and"
			+ " cha_chm_pe_id = pe_id and pe_pr_prid = pr_prid and cha_cav_code = cav_code order by cha_id desc";

	public static final String ASSIGNMENT_DUE_DATE = "SELECT chd_date FROM\n"
			+ "chambers_assign_date ad, chm_assign_datetype_val,\n"
			+ "    (Select max(chd_date) as maxnum, chd_cha_id\n" + "    from chambers_assign_date\n"
			+ "    group by chd_cha_id) maxresults\n" + "WHERE ad.chd_cha_id = ? and\n"
			+ "chd_cdv_code = cdv_code and\n" + "ad.chd_cha_id=  maxresults.chd_cha_id and\n"
			+ "ad.chd_date = maxresults.maxnum";

	public static final String CHC_DATE_END = "select first 1 chc_date_end from chambers_assignment \n"
			+ "join chambers_assign_date on cha_id = chd_cha_id \n"
			+ "join chm_assign_to_case on chc_cha_id = ? order by cha_last_updated desc";

	public static final String CHD_DATE = "select first 1 chd_date from chambers_assignment \n"
			+ "join chambers_assign_date on cha_id = chd_cha_id \n"
			+ "join chm_assign_to_case on chc_cha_id = ? order by cha_last_updated desc";
	public static final String CHD_CDV_CODE = "select first 1 chd_cdv_code from chambers_assignment \n"
			+ "join chambers_assign_date on cha_id = chd_cha_id \n"
			+ "join chm_assign_to_case on chc_cha_id = ? order by cha_last_updated desc";

	public static final String CHA_CAV_CODE = "select first 1 cha_cav_code from chambers_assignment \n"
			+ "join chambers_assign_date on cha_id = chd_cha_id \n"
			+ "join chm_assign_to_case on chc_cha_id = ? order by cha_last_updated desc";

	public static final String CAV_CODE = "SELECT cav_code  \n" + "	FROM chm_assign_type_val \n"
			+ "	WHERE cav_display='TEXT' ";

	public static final String UPDATE_CHAMBERS_CASE_TO_REFERRAL = "UPDATE\n" + "  chambers_case_to_referral\n" + "SET\n"
			+ "  ccr_date_end = 'TEXT' where ccr_id =?";

	public static final String CCR_DATE_END = "select ccr_date_end from chambers_case_to_referral where ccr_id =?";

	public static final String CASES_ON_CALENDAR_SESSIONS = "select   VALUE "
			+ "from chm_mobile_referral inner join panel_case on cmr_ph_id = ph_id \n"
			+ "inner join panel_sitting on ph_pns_id = pns_id \n" + "inner join cluster on pns_clu_id = clu_id \n"
			+ "inner join court_session on clu_cts_id = cts_id \n"
			+ "left outer join argue_time_val on ph_arg_code = arg_code \n"
			+ "inner join chm_reftype_val on cyv_code = cmr_cyv_code \n"
			+ "where cmr_ju_pe_id = 'CMR_JU_PE_ID' and cyv_is_oral_arg = 'y' and cmr_cs_caseid='CMR_CS_CASEID' and cmr_date_end is null ";

	public static final String SET_SITE_TABLE_VARIABLE_VALUE = "UPDATE  site SET  si_value = 'SI_VALUE' where si_code ='SI_CODE'";

	public static final String SITE_TABLE_VARIABLE_VALUE = "SELECT si_value FROM site WHERE si_code = '?'";

	public static final String RELIEF = "select distinct  rl_list_text from chambers_case_to_referral b join chambers_case_to_referral a on"
			+ " b.ccr_cpr_id = a.ccr_cpr_id join dktpart on dp_dktpartid = b.ccr_dp_dktpartid join relief_list on rl_id = dp_rlid "
			+ "join chambers_caseref_to_judge on b.ccr_id = crj_ccr_id join judge on crj_ju_pe_id = ju_pe_id join chambers_referral on b.ccr_cpr_id = cpr_id "
			+ "join chambers_case on b.ccr_ccs_id = ccs_id join chm_reftype_val on cpr_cyv_code = cyv_code join case_dktentry on cd_id = dp_cd_id "
			+ "join dktentry on cd_dktentryid = de_dktentryid left join doctype_val on dp_doc_type = dty_code join event_list on el_id = de_elid"
			+ " left join chambers_vote left join (chm_vote_to_note inner join document on cvn_dm_dls_id = dm_dls_id and dm_internal_type = 'notevote'  ) on chv_id = cvn_chv_id "
			+ "on chv_crj_id = crj_id and chv_date_end is null left join chm_vote_val on chv_cvv_code = cvv_code where a.ccr_id = ? and b.ccr_date_end is null and "
			+ "NVL(cpr_vote_complete,'') <> 'y' and NVL(cpr_vote_req,'') <> 'n' order by  rl_list_text desc";

	public static final String JUDGEs_VOTE = "select first 1  distinct cvv_display from chambers_case_to_referral b join chambers_case_to_referral a on\n"
			+ " b.ccr_cpr_id = a.ccr_cpr_id join dktpart on dp_dktpartid = b.ccr_dp_dktpartid join relief_list on rl_id = dp_rlid \n"
			+ "join chambers_caseref_to_judge on b.ccr_id = crj_ccr_id join judge on crj_ju_pe_id = ju_pe_id join chambers_referral on b.ccr_cpr_id = cpr_id \n"
			+ "join chambers_case on b.ccr_ccs_id = ccs_id join chm_reftype_val on cpr_cyv_code = cyv_code join case_dktentry on cd_id = dp_cd_id \n"
			+ "join dktentry on cd_dktentryid = de_dktentryid left join doctype_val on dp_doc_type = dty_code join event_list on el_id = de_elid\n"
			+ " left join chambers_vote left join (chm_vote_to_note inner join document on cvn_dm_dls_id = dm_dls_id and dm_internal_type = 'notevote'  ) on chv_id = cvn_chv_id \n"
			+ "on chv_crj_id = crj_id and chv_date_end is null left join chm_vote_val on chv_cvv_code = cvv_code where a.ccr_id = ? and b.ccr_date_end is null and \n"
			+ "NVL(cpr_vote_complete,'') <> 'y' and NVL(cpr_vote_req,'') <> 'n'  and  chv_date_created ='CHV_DATE_CREATED'";

	public static final String VOTE_DATE = "	select   first 1  distinct chv_date_created  from chambers_case_to_referral b join chambers_case_to_referral a on\n"
			+ " b.ccr_cpr_id = a.ccr_cpr_id join dktpart on dp_dktpartid = b.ccr_dp_dktpartid join relief_list on rl_id = dp_rlid \n"
			+ "join chambers_caseref_to_judge on b.ccr_id = crj_ccr_id join judge on crj_ju_pe_id = ju_pe_id join chambers_referral on b.ccr_cpr_id = cpr_id \n"
			+ "join chambers_case on b.ccr_ccs_id = ccs_id join chm_reftype_val on cpr_cyv_code = cyv_code join case_dktentry on cd_id = dp_cd_id \n"
			+ "join dktentry on cd_dktentryid = de_dktentryid left join doctype_val on dp_doc_type = dty_code join event_list on el_id = de_elid\n"
			+ " left join chambers_vote left join (chm_vote_to_note inner join document on cvn_dm_dls_id = dm_dls_id and dm_internal_type = 'notevote'  ) on chv_id = cvn_chv_id \n"
			+ "on chv_crj_id = crj_id and chv_date_end is null left join chm_vote_val on chv_cvv_code = cvv_code where a.ccr_id = ? and b.ccr_date_end is null and \n"
			+ "NVL(cpr_vote_complete,'') <> 'y' and NVL(cpr_vote_req,'') <> 'n' and ju_initials='JU_INITIALS' order by  chv_date_created asc";

	// public static final String VOTE_DATE="select first 1 distinct
	// chv_date_created, cvn_date_created " +
	// "from chambers_case_to_referral b join chambers_case_to_referral a on \n" +
	// "b.ccr_cpr_id = a.ccr_cpr_id \n" +
	// "join dktpart on dp_dktpartid = b.ccr_dp_dktpartid \n" +
	// "join relief_list on rl_id = dp_rlid \n" +
	// "join chambers_caseref_to_judge on b.ccr_id = crj_ccr_id \n" +
	// "join judge on crj_ju_pe_id = ju_pe_id \n" +
	// "join chambers_referral on b.ccr_cpr_id = cpr_id \n" +
	// "join chambers_case on b.ccr_ccs_id = ccs_id \n" +
	// "join chm_reftype_val on cpr_cyv_code = cyv_code \n" +
	// "join case_dktentry on cd_id = dp_cd_id \n" +
	// "join dktentry on cd_dktentryid = de_dktentryid \n" +
	// "left join doctype_val on dp_doc_type = dty_code \n" +
	// "join event_list on el_id = de_elid \n" +
	// "left join chambers_vote left join (chm_vote_to_note inner join \n" +
	// "document on cvn_dm_dls_id = dm_dls_id \n" +
	// "and dm_internal_type = 'notevote' ) on chv_id = cvn_chv_id \n" +
	// "on chv_crj_id = crj_id and chv_date_end is null \n" +
	// "left join chm_vote_val on chv_cvv_code = cvv_code \n" +
	// "where a.ccr_id = ? and b.ccr_date_end is null and NVL(cpr_vote_complete,'')
	// <> 'y' and NVL(cpr_vote_req,'') <> 'n' and ju_initials='JU_INITIALS' \n" +
	// "order by cvn_date_created desc";
	//
	public static final String FILERS_MIDDLE_NAME = "select pr_middle_name "
			+ " from chm_mobile_referral join chambers_case_to_referral on cmr_ccr_id = ccr_id join relate_dktpart "
			+ "on ccr_dp_dktpartid = rd_rel_dktpartid  join case_dktentry on rd_cre_cd_id = cd_id  join dktentry on "
			+ "cd_dktentryid = de_dktentryid  join dktperson on cd_id = dep_cd_id and dep_type = 'filer' and dep_py_pcid <> 1 "
			+ " join party on dep_py_pcid = py_pcid  join personrole on py_pe_id = pe_id  join pty_type_val"
			+ " on py_pt_code = pt_code  join person on pr_prid = pe_pr_prid  left join generation_val on gn_code = pr_gn_code "
			+ " where cmr_cs_caseid = 'TEXT' and cmr_ju_pe_id = ? and cmr_cyv_code = 'CODE' and cmr_ju_pe_id = ? ";

	// pr_last_name, pr_first_name, pr_middle_name,gn_display, pt_display
	public static final String FILERS_INOFRMATION = "select FIELD "
			+ " from chm_mobile_referral join chambers_case_to_referral on cmr_ccr_id = ccr_id join relate_dktpart "
			+ "on ccr_dp_dktpartid = rd_rel_dktpartid  join case_dktentry on rd_cre_cd_id = cd_id  join dktentry on "
			+ "cd_dktentryid = de_dktentryid  join dktperson on cd_id = dep_cd_id and dep_type = 'filer' and dep_py_pcid <> 1 "
			+ " join party on dep_py_pcid = py_pcid  join personrole on py_pe_id = pe_id  join pty_type_val"
			+ " on py_pt_code = pt_code  join person on pr_prid = pe_pr_prid  left join generation_val on gn_code = pr_gn_code "
			+ " where cmr_cs_caseid = 'TEXT' and cmr_ju_pe_id = ? and cmr_cyv_code = 'CODE'";

	public static final String FILED_DATE = "select  de_date_filed from chm_mobile_referral join chambers_case_to_referral on cmr_ccr_id = ccr_id "
			+ "join relate_dktpart on ccr_dp_dktpartid = rd_rel_dktpartid join case_dktentry on rd_cre_cd_id = cd_id "
			+ "join dktentry on cd_dktentryid = de_dktentryid join dktperson on cd_id = dep_cd_id and dep_type = 'filer' and dep_py_pcid <> 1 "
			+ "join party on dep_py_pcid = py_pcid join personrole on py_pe_id = pe_id join pty_type_val on py_pt_code = pt_code "
			+ "join person on pr_prid = pe_pr_prid left join generation_val on gn_code = pr_gn_code "
			+ "where cmr_cs_caseid = 'TEXT' and cmr_ju_pe_id = ? and cmr_cyv_code = 'CODE' and cmr_ju_pe_id = ?";

	public static final String JUDGEs_INITIALS = " SELECT ju_initials FROM chm_mobile_referral, "
			+ "judge WHERE cmr_ccr_id = ? and cmr_ju_pe_id = ju_pe_id order by ju_initials desc";

	// Query to find staff assignments associated with the referral.
	public static final String CHA_ID = "select distinct cha_id from chm_mobile_referral,"
			+ " chambers_case_to_referral, chm_assign_to_case, chambers_assignment, person, personrole, chm_assign_type_val "
			+ "where  cmr_cs_caseid = 'CMR_CS_CASEID'  and cmr_ccr_id = ccr_id and ccr_cpr_id = chc_cpr_id and chc_cs_caseid = "
			+ "cmr_cs_caseid and chc_cha_id = cha_id and cha_ju_pe_id ='CHA_JU_PE_ID'"
			+ "  and chc_date_end is null and cha_chm_pe_id"
			+ " = pe_id and pe_pr_prid = pr_prid and cmr_cyv_code='CMR_CYV_CODE' and pr_last_name='PR_LAST_NAME'  and cha_cav_code = cav_code";

	// Query to find staff assignments associated with the referral.
	public static final String STAFF_ASSIGNMENTS_LINKED_TO_THE_REFERRAL = "select distinct pr_last_name from chm_mobile_referral,"
			+ " chambers_case_to_referral, chm_assign_to_case, chambers_assignment, person, personrole, chm_assign_type_val "
			+ "where  cmr_cs_caseid = 'CMR_CS_CASEID'  and cmr_ccr_id = ccr_id and ccr_cpr_id = chc_cpr_id and chc_cs_caseid = "
			+ "cmr_cs_caseid and chc_cha_id = cha_id and cha_ju_pe_id ='CHA_JU_PE_ID'"
			+ "  and chc_date_end is null and cha_chm_pe_id"
			+ " = pe_id and pe_pr_prid = pr_prid and cmr_cyv_code='CMR_CYV_CODE'   and cha_cav_code = cav_code";

	public static final String STAFF_ASSIGNMENTS_LINKED_TO_THE_REFERRAL_LAST_NAME = "select distinct pr_first_name from chm_mobile_referral,"
			+ " chambers_case_to_referral, chm_assign_to_case, chambers_assignment, person, personrole, chm_assign_type_val "
			+ "where  cmr_cs_caseid = 'CMR_CS_CASEID'  and cmr_ccr_id = ccr_id and ccr_cpr_id = chc_cpr_id and chc_cs_caseid = "
			+ "cmr_cs_caseid and chc_cha_id = cha_id and cha_ju_pe_id ='CHA_JU_PE_ID'"
			+ "  and chc_date_end is null and cha_chm_pe_id"
			+ " = pe_id and pe_pr_prid = pr_prid and cmr_cyv_code='CMR_CYV_CODE' and pr_last_name='PR_LAST_NAME' and cha_cav_code = cav_code";

	public static final String ASSIGNMENT_TYPE = "select distinct cav_description from chm_mobile_referral,"
			+ " chambers_case_to_referral, chm_assign_to_case, chambers_assignment, person, personrole, chm_assign_type_val "
			+ "where  cmr_cs_caseid = 'CMR_CS_CASEID'  and cmr_ccr_id = ccr_id and ccr_cpr_id = chc_cpr_id and chc_cs_caseid = "
			+ "cmr_cs_caseid and chc_cha_id = cha_id and cha_ju_pe_id ='CHA_JU_PE_ID'"
			+ "  and chc_date_end is null and cha_chm_pe_id"
			+ " = pe_id and pe_pr_prid = pr_prid and cmr_cyv_code='CMR_CYV_CODE' and pr_last_name='PR_LAST_NAME' and cha_cav_code = cav_code";

	public static final String CMR_CCR_ID = "SELECT first 1 ID FROM case, chm_mobile_referral WHERE cs_year = 'CS_YEAR' and cs_number = 'CS_NUMBER' and cs_caseid = cmr_cs_caseid "
			+ "and cmr_cyv_code='CMR_CYV_CODE' and cmr_ju_pe_id ='CMR_JU_PE_ID'";

	public static final String CCR_ID = "SELECT  first 1 ccr_id  FROM chm_mobile_referral, chambers_case_to_referral, chambers_referral WHERE cpr_vote_req = 'y'"
			+ " and cmr_ccr_id = ccr_id and ccr_cpr_id = cpr_id and cmr_cs_caseid = 'CMR_CS_CASEID' and cmr_ju_pe_id = 'CMR_JU_PE_ID'";

	public static final String CMR_ID = "SELECT  first 1 cmr_id  FROM chm_mobile_referral, chambers_case_to_referral, chambers_referral WHERE cpr_vote_req = 'y'"
			+ " and cmr_ccr_id = ccr_id and ccr_cpr_id = cpr_id and cmr_cs_caseid = 'CMR_CS_CASEID' and cmr_ju_pe_id = 'CMR_JU_PE_ID'";

	public static final String FILLERs_INFORMATION = "select  FIELD " + "from chm_mobile_referral\n"
			+ "join chambers_case_to_referral on cmr_ccr_id = ccr_id\n"
			+ "join relate_dktpart on ccr_dp_dktpartid = rd_rel_dktpartid \n"
			+ "join case_dktentry on rd_cre_cd_id = cd_id \n" + "join dktentry on cd_dktentryid = de_dktentryid \n"
			+ "join dktperson on cd_id = dep_cd_id and dep_type = 'filer' and dep_py_pcid <> 1 \n"
			+ "join party on dep_py_pcid = py_pcid \n" + "join personrole on py_pe_id = pe_id \n"
			+ "join pty_type_val on py_pt_code = pt_code \n" + "join person on pr_prid = pe_pr_prid \n"
			+ "left join generation_val on gn_code = pr_gn_code --where cd_dktentryid =  \n"
			+ "where cmr_cs_caseid = CMR_CS_CASEID and cmr_ju_pe_id = CMR_JU_PE_ID and cmr_cyv_code = 'CMR_CYV_CODE' \n"
			+ "union \n" + "select  FIELD  from chm_mobile_referral\n"
			+ "join chambers_case_to_referral on cmr_ccr_id = ccr_id\n"
			+ "join relate_dktpart on ccr_dp_dktpartid = rd_rel_dktpartid \n"
			+ "join case_dktentry on rd_cre_cd_id = cd_id \n" + "join dktentry on cd_dktentryid = de_dktentryid \n"
			+ "join dktperson on cd_id = dep_cd_id and dep_type = 'filer'  and dep_py_pcid = 1 \n"
			+ "join personrole on pe_id = dep_peid \n" + "join person on pr_prid = pe_pr_prid \n"
			+ "left join generation_val on gn_code = pr_gn_code --where cd_dktentryid = \n"
			+ "where cmr_cs_caseid = CMR_CS_CASEID and cmr_ju_pe_id = CMR_JU_PE_ID and cmr_cyv_code = 'CMR_CYV_CODE'";

	public static final String DOCUMENT_CATEGORIES = "select distinct (cmd_doc_category),cmd_sort from chm_mobile_docs join chm_mobile_referral on cmr_id = cmd_cmr_id where cmr_cyv_code = 'CMR_CYV_CODE' \n"
			+ " and cmr_date_end is null and cmr_ju_pe_id ='CMR_JU_PE_ID'  and  cmr_cs_caseid='CMR_CS_CASEID'  order by cmd_sort";

	public static final String ASSIGNMENT_INFO = "select  pr_last_name, pr_first_name,  cav_display, cdv_display, chd_date, can_dm_dls_id, can_date_created, rl_list_text, cdn_dm_dls_id, cdn_date_created from chm_assign_to_case join chambers_assignment on cha_id = chc_cha_id join chm_assign_type_val on cha_cav_code = cav_code "

			+ "join chambers_assign_date on chd_cha_id = cha_id join chm_assign_datetype_val on cdv_code = chd_cdv_code "

			+ "join personrole on cha_chm_pe_id = pe_id join person on pr_prid = pe_pr_prid "

			+ "left join chm_assign_to_note on cha_id = can_cha_id left join chambers_case_to_referral on ccr_cpr_id = chc_cpr_id left join chm_assign_date_to_note on cha_id = cdn_chd_cha_id "

			+ "join dktpart on dp_dktpartid = ccr_dp_dktpartid join relief_list on rl_id = dp_rlid "

			+ "where chc_cpr_id in (select ccr_cpr_id from chambers_case_to_referral where ccr_id in "

			+ "(select cmr_ccr_id from chm_mobile_referral where cmr_id = CMR_ID)) and (cha_chm_pe_id in (select cmr_ju_pe_id from chm_mobile_referral "

			+ "where cmr_id = CMR_ID) or cha_chm_pe_id = cha_ju_pe_id)  and  chc_date_end is null "

			+ "union "

			+ "select pr_last_name, pr_first_name,  cav_display, cdv_display, chd_date, can_dm_dls_id, can_date_created, rl_list_text, cdn_dm_dls_id, cdn_date_created from chm_assign_to_case join chambers_assignment on cha_id = chc_cha_id join chm_assign_type_val on cha_cav_code = cav_code "

			+ "join chambers_assign_date on chd_cha_id = cha_id join chm_assign_datetype_val on cdv_code = chd_cdv_code "

			+ "join personrole on cha_chm_pe_id = pe_id join person on pr_prid = pe_pr_prid "

			+ "left join chm_assign_to_note on cha_id = can_cha_id left join chambers_case_to_referral on ccr_cpr_id = chc_cpr_id left join chm_assign_date_to_note on cha_id = cdn_chd_cha_id "

			+ "join dktpart on dp_dktpartid = ccr_dp_dktpartid join relief_list on rl_id = dp_rlid "

			+ "where chc_cpr_id in (select ccr_cpr_id from chambers_case_to_referral where ccr_id in "

			+ "(select cmr_ccr_id from chm_mobile_referral where cmr_id = CMR_ID)) and cha_chm_pe_id not in (select cmr_ju_pe_id from chm_mobile_referral "

			+ "where cmr_id = CMR_ID) and cha_ju_pe_id in (select cmr_ju_pe_id from chm_mobile_referral where cmr_id = CMR_ID) and chc_date_end is null "

			+ "union "

			+ "select  pr_last_name, pr_first_name,  cav_display, cdv_display, chd_date, can_dm_dls_id, can_date_created, rl_list_text, cdn_dm_dls_id, cdn_date_created from chm_assign_to_case join chambers_assignment on cha_id = chc_cha_id join chm_assign_type_val on cha_cav_code = cav_code "

			+ "join chambers_assign_date on chd_cha_id = cha_id join chm_assign_datetype_val on cdv_code = chd_cdv_code "

			+ "join personrole on cha_chm_pe_id = pe_id join person on pr_prid = pe_pr_prid "

			+ "left join chm_assign_to_note on cha_id = can_cha_id left join chambers_case_to_referral on ccr_cpr_id = chc_cpr_id left join chm_assign_date_to_note on cha_id = cdn_chd_cha_id "

			+ "join dktpart on dp_dktpartid = ccr_dp_dktpartid join relief_list on rl_id = dp_rlid "

			+ "where chc_cpr_id = 1 and chc_cs_caseid in (select cmr_cs_caseid from chm_mobile_referral where cmr_id = CMR_ID) "

			+ "and cha_ju_pe_id in (select cmr_ju_pe_id from chm_mobile_referral where cmr_id = CMR_ID)  and  chc_date_end is null";

	public static final String EXISTING_STAFF_ASSIGNMENTS = "select  distinct cav_display from chm_assign_to_case join chambers_assignment on cha_id = chc_cha_id join chm_assign_type_val on cha_cav_code = cav_code "

			+ "join chambers_assign_date on chd_cha_id = cha_id join chm_assign_datetype_val on cdv_code = chd_cdv_code "

			+ "join personrole on cha_chm_pe_id = pe_id join person on pr_prid = pe_pr_prid "

			+ "left join chm_assign_to_note on cha_id = can_cha_id left join chambers_case_to_referral on ccr_cpr_id = chc_cpr_id left join chm_assign_date_to_note on cha_id = cdn_chd_cha_id "

			+ "join dktpart on dp_dktpartid = ccr_dp_dktpartid join relief_list on rl_id = dp_rlid "

			+ "where chc_cpr_id in (select ccr_cpr_id from chambers_case_to_referral where ccr_id in "

			+ "(select cmr_ccr_id from chm_mobile_referral where cmr_id = CMR_ID)) and (cha_chm_pe_id in (select cmr_ju_pe_id from chm_mobile_referral "

			+ "where cmr_id = CMR_ID) or cha_chm_pe_id = cha_ju_pe_id)  and pr_last_name='PR_LAST_NAME' and  pr_first_name='PR_FIRST_NAME' and   chc_date_end is null "

			+ "union "

			+ "select distinct cav_display from chm_assign_to_case join chambers_assignment on cha_id = chc_cha_id join chm_assign_type_val on cha_cav_code = cav_code "

			+ "join chambers_assign_date on chd_cha_id = cha_id join chm_assign_datetype_val on cdv_code = chd_cdv_code "

			+ "join personrole on cha_chm_pe_id = pe_id join person on pr_prid = pe_pr_prid "

			+ "left join chm_assign_to_note on cha_id = can_cha_id left join chambers_case_to_referral on ccr_cpr_id = chc_cpr_id left join chm_assign_date_to_note on cha_id = cdn_chd_cha_id "

			+ "join dktpart on dp_dktpartid = ccr_dp_dktpartid join relief_list on rl_id = dp_rlid "

			+ "where chc_cpr_id in (select ccr_cpr_id from chambers_case_to_referral where ccr_id in "

			+ "(select cmr_ccr_id from chm_mobile_referral where cmr_id = CMR_ID)) and cha_chm_pe_id not in (select cmr_ju_pe_id from chm_mobile_referral "

			+ "where cmr_id = CMR_ID) and cha_ju_pe_id in (select cmr_ju_pe_id from chm_mobile_referral where cmr_id = CMR_ID) and  pr_last_name='PR_LAST_NAME' and  pr_first_name='PR_FIRST_NAME' and   chc_date_end is null "

			+ "union "

			+ "select distinct cav_display  from chm_assign_to_case join chambers_assignment on cha_id = chc_cha_id join chm_assign_type_val on cha_cav_code = cav_code "

			+ "join chambers_assign_date on chd_cha_id = cha_id join chm_assign_datetype_val on cdv_code = chd_cdv_code "

			+ "join personrole on cha_chm_pe_id = pe_id join person on pr_prid = pe_pr_prid "

			+ "left join chm_assign_to_note on cha_id = can_cha_id left join chambers_case_to_referral on ccr_cpr_id = chc_cpr_id left join chm_assign_date_to_note on cha_id = cdn_chd_cha_id "

			+ "join dktpart on dp_dktpartid = ccr_dp_dktpartid join relief_list on rl_id = dp_rlid "

			+ "where chc_cpr_id = 1 and chc_cs_caseid in (select cmr_cs_caseid from chm_mobile_referral where cmr_id = CMR_ID) "

			+ "and cha_ju_pe_id in (select cmr_ju_pe_id from chm_mobile_referral where cmr_id = CMR_ID)  and pr_last_name='PR_LAST_NAME' and  pr_first_name='PR_FIRST_NAME' and    chc_date_end is null";

	public static final String ASSIGNMENT_DATE_TYPE = "select   cav_display,cdv_display,chd_date from chm_assign_to_case join chambers_assignment on cha_id = chc_cha_id join chm_assign_type_val on cha_cav_code = cav_code "

			+ "	 join chambers_assign_date on chd_cha_id = cha_id join chm_assign_datetype_val on cdv_code = chd_cdv_code "

			+ "	 join personrole on cha_chm_pe_id = pe_id join person on pr_prid = pe_pr_prid "

			+ "	 left join chm_assign_to_note on cha_id = can_cha_id left join chambers_case_to_referral on ccr_cpr_id = chc_cpr_id left join chm_assign_date_to_note on cha_id = cdn_chd_cha_id "

			+ "	 join dktpart on dp_dktpartid = ccr_dp_dktpartid join relief_list on rl_id = dp_rlid "

			+ "	 where chc_cpr_id in (select ccr_cpr_id from chambers_case_to_referral where ccr_id in "

			+ "	 (select cmr_ccr_id from chm_mobile_referral where cmr_id = 'CMR_ID')) and (cha_chm_pe_id in (select cmr_ju_pe_id from chm_mobile_referral "

			+ "  where cmr_id = 'CMR_ID') or cha_chm_pe_id = cha_ju_pe_id) and pr_last_name='PR_LAST_NAME' and pr_first_name='PR_FIRST_NAME' and chc_date_end is null";

	public static final String by_PJ_JUDGE_ORDER = "SELECT first 1  cmr_panel_members FROM chm_mobile_referral, judge, panel_to_judge "
			+ "WHERE cmr_ccr_id ='CMR_CCR_ID' and cmr_ju_pe_id = ju_pe_id order by pj_judge_order";

	public static final String by_JU_SENIORITY_SORT = "	SELECT first 1  cmr_panel_members FROM chm_mobile_referral, judge "
			+ "WHERE cmr_ccr_id = 'CMR_CCR_ID' and cmr_ju_pe_id = ju_pe_id order by ju_seniority_sort";

	public static final String VOTE_CLOSING_DATE = "select first 1 distinct cpr_vote_closing_date, b.ccr_id, rl_list_text, el_list_text, el_id, crj_id, ju_initials, b.ccr_dp_dktpartid, "
			+ "cpr_single_judge, cpr_vote_req, "
			+ "ccs_cs_caseid, ccs_id, b.ccr_ph_id, cyv_display as cyv_description, cpr_id, "
			+ "cpr_date_ref, cpr_cyv_code, " + "dp_part_ext, dp_rlid, dp_doc_type, dp_doc_subtype, "
			+ "cd_dktentryid, cd_dktentry_num, cd_case_ext, " + "de_date_filed, rl_ref_text, dty_descrip , "
			+ "chv_id, chv_cvv_code, cvv_display, cvn_dm_dls_id, cvn_date_created, chv_date_created, nvl(pj_judge_order, "
			+ "ju_seniority_sort) pj_judge_order, ic_chm_judge_panrole, pj_presiding "
			+ "from chambers_case_to_referral b join chambers_case_to_referral a on " + "b.ccr_cpr_id = a.ccr_cpr_id "
			+ "join dktpart on dp_dktpartid = b.ccr_dp_dktpartid " + "join relief_list on rl_id = dp_rlid "
			+ "join chambers_caseref_to_judge on b.ccr_id = crj_ccr_id " + "join judge on crj_ju_pe_id = ju_pe_id "
			+ "join chambers_referral on b.ccr_cpr_id = cpr_id " + "join chambers_case on b.ccr_ccs_id = ccs_id "
			+ "join chm_reftype_val on cpr_cyv_code = cyv_code " + "join case_dktentry on cd_id = dp_cd_id "
			+ "join dktentry on cd_dktentryid = de_dktentryid " + "left join doctype_val on dp_doc_type = dty_code "
			+ "join event_list on el_id = de_elid " + "left join chambers_vote left join (chm_vote_to_note inner join "
			+ "document on cvn_dm_dls_id = dm_dls_id " + "and dm_internal_type = 'notevote'  ) on chv_id = cvn_chv_id "
			+ "on chv_crj_id = crj_id and chv_date_end is null " + "left join chm_vote_val on chv_cvv_code = cvv_code "
			+ "left join panel_case on a.ccr_ph_id = ph_id  left join panel_to_judge on ph_pn_id = pj_pn_id and pj_ju_ao_code = ju_ao_code "
			+ "left join panel_involvement on pni_ph_id = ph_id and pni_ju_ao_code = ju_ao_code "
			+ "left join inv_codes_val on pni_ic_code = inv_codes_val.ic_code and ic_chm_judge_panrole = 'l' "
			+ "where a.ccr_id = 'CCR_ID' {IF TARGET ONLY ADD THIS and a.ccr_ccs_id = b.ccr_ccs_id} and b.ccr_date_end is null and NVL(cpr_vote_complete,'') <> 'y' and NVL(cpr_vote_req,'') <> 'n' "
			+ "and crj_date_remove is null "
			+ "order by cpr_date_ref, de_date_filed, cd_case_ext, ccs_id, pj_judge_order, cvn_date_created desc";

	public static final String me_code = "select me_cyv_code from mbr_event where me_el_id = '?'";

	public static final String annotatedDoc = "select cs_year||\"-\"||cs_number case_num, "
			+ " orig_doc.dm_description "
			+ "from mbr_annot_to_doc, user, document orig_doc, document annot_doc, personrole, dktentry, case_dktentry, case, outer doc_user, outer doc_group\n "
			+ "where mad_orig_dm_dls_id = orig_doc.dm_dls_id and mad_annot_dm_dls_id = annot_doc.dm_dls_id and mad_pe_id = pe_id and pe_pr_prid = ur_pr_prid\n "
			+ "and orig_doc.dm_dktentryid = de_dktentryid and orig_doc.dm_dktentryid = cd_dktentryid and cd_caseid = cs_caseid\n "
			+ "and annot_doc.dm_dktentryid = du_dktentryid and annot_doc.dm_seq = du_seq\n "
			+ "and annot_doc.dm_dktentryid = dcg_dktentryid and annot_doc.dm_seq = dcg_seq\n"
			+ "--and orig_doc.dm_dls_id = #####  -- these three optional if you want to narrow results.\n "
			+ "and annot_doc.dm_last_updated > TODAY \n"
			+ "and orig_doc.dm_description matches \"*TEXT*\" \n"
			+ "order by annot_doc.dm_last_updated desc, mad_orig_dm_dls_id desc;";

}
