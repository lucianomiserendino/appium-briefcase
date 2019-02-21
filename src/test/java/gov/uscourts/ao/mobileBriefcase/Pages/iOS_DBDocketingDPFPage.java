package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.valueOf;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ACTION_NAME;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CHAMBERS_GROUP_ID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DCG_GROUP;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DM_ACC_CRT;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DM_ACC_CTLINK;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DM_ACC_SPEC;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DM_DATE_CREATED;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DM_DESCRIPTION;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DU_PRID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.LOGED_IN_JUDGES_LAST_NAME;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.LOGED_IN_JUDGES_PR_PRID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.PANEL_JUDGES_PR_PRID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.PR_LAST_NAME;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.selectAction;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.getText;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.replace;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.sendKeys;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.getStreamOfRandomInts;
import static java.util.Collections.sort;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.models.ElListText;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.WithTimeout;
import io.appium.java_client.pagefactory.iOSFindBy;

public class iOS_DBDocketingDPFPage extends AppiumPageFactory {

	@WithTimeout(time = 10, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//XCUIElementTypeTextView[1]")
	public static MobileElement descriptionField;

	@WithTimeout(time = 10, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//XCUIElementTypeTextView[2]")
	public static MobileElement commentField;

	@WithTimeout(time = 10, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//*[contains(@name, 'User')]")
	public static MobileElement selectUser;

	@iOSFindBy(id = "Submit")
	public static MobileElement submit;

	@iOSFindBy(id = "Yes")
	public static MobileElement YESbtn;

	@WithTimeout(time = 100, unit = TimeUnit.SECONDS)
	@iOSFindBy(id = "OK")
	public static MobileElement OKbtn;

	ElListText list;

	public void selectActioName(List<ElListText> table, int index, String dbType, String caseNum, String peID) {

		list = table.get(index);
		try {

			selectAction(valueOf(dbType), "Actions", list.getElListText());
			sendKeys(commentField, "Test-" + getStreamOfRandomInts());
			sendKeys(descriptionField, "Test-" + getStreamOfRandomInts() + "-");
			String description = getText(descriptionField);
			tap(submit);
			tap(YESbtn);
			tap(OKbtn);
			getDataTable(table, index, valueOf(dbType), caseNum, peID);
			assertEquals(description, getAllColumns(valueOf(dbType), DM_DESCRIPTION));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getDataTable(List<ElListText> table, int index, DBType dbType, String caseNum, String peID) {
		list = table.get(index);
		assertEquals(getAllColumns(dbType, DM_ACC_CRT), list.getDm_acc_crt());

		assertEquals(getAllColumns(dbType, DM_ACC_CTLINK), list.getDm_acc_ctlink());

		assertEquals(getAllColumns(dbType, DM_ACC_SPEC), list.getDm_acc_spec());

		getDocUserRecord(dbType, table, index, caseNum, peID);
	}

	public static String getActionName(DBType dbType, String el_id) {
		return getAllColumns(dbType, getID(ACTION_NAME, el_id));
	}

	public void getDocUserRecord(DBType dbType, List<ElListText> table, int index, String caseNum, String peId) {
		list = table.get(index);
		List<String> docUserTable = getDocUserTable(dbType);
		List<String> docGroupTable = getDocGroupTable(dbType);
		List<String> panelJudges = getPanelJudgesPRID(dbType, caseNum, peId);
		List<String> loggedInJudgesChambersGroupID = getLogedInJudgesChambersGroupID(dbType, caseNum, peId);

		/** No doc_group or doc_user records will be created */
		if (getActionName(dbType, list.getElListText()).equals("note - court users")) {
			assertNull(docUserTable);
			assertNull(docGroupTable);

			/** No doc_group or doc_user records will be created */
		} else if (getActionName(dbType, list.getElListText()).equals("note - court users linked to case")) {
			assertNull(docUserTable);
			assertNull(docGroupTable);

			/**
			 * A doc_user record will be created for each judge on the panel.No doc_group
			 * records will be created
			 */
		} else if (getActionName(dbType, list.getElListText()).equals("note - panel judges only")) {
			assertEquals(docUserTable, panelJudges);
			assertNull(docGroupTable);

			/**
			 * A doc_user record will be created for each judge on the panel. A doc_group
			 * record will be created for the logged in judge's chambers group
			 */
		} else if (getActionName(dbType, list.getElListText()).equals("note - panel judges and users chambers")) {

			assertEquals(docUserTable, panelJudges);
			assertEquals(docGroupTable, loggedInJudgesChambersGroupID);

			/**
			 * A doc_user record will not be created. A doc_group record will be created for
			 * the logged in judge's chambers only
			 */
		} else if (getActionName(dbType, list.getElListText()).equals("note - panel judges chambers")) {
			assertNull(docUserTable);
			assertEquals(docGroupTable, getchambersGroupId(dbType, panelJudges));

		} else if (getActionName(dbType, list.getElListText()).equals("note - users chambers")) {
			assertNull(docUserTable);
			assertEquals(docGroupTable, loggedInJudgesChambersGroupID);

		} else if (getActionName(dbType, list.getElListText()).equals("note - only groups and users")) {
		}

	}

	/**
	 * @return
	 */
	public static List<String> getchambersGroupId(DBType dbType, List<String> panelJudges) {

		List<String> groupId = new ArrayList<>();
		List<String> chambersGroupId = new ArrayList<>();
		List<String> pr_prid = panelJudges;

		for (int a = 0; a < pr_prid.size(); a++) {

			List<String> prLastName = executeQuery(dbType,
					replace(PR_LAST_NAME, "CMR_ID", "2315338", "PR_PRID", pr_prid.get(a)));

			for (int i = 0; i < prLastName.size(); i++) {

				groupId = getDBResult(dbType,
						getID(replace(CHAMBERS_GROUP_ID, "PR_LAST_NAME", prLastName.get(i)), pr_prid.get(a)));

				for (int j = 0; i < groupId.size(); i++) {

					chambersGroupId.add(groupId.get(j));
				}
			}
		}
		sort(chambersGroupId);
		return chambersGroupId;
	}

	public static String getDM_DATE_CREATED(DBType dbType) {
		return getAllColumns(dbType, DM_DATE_CREATED);
	}

	public static List<String> getDocUserTable(DBType dbType) {
		return getDBResult(dbType, replace(DU_PRID, "DU_DATE_CREATED", getDM_DATE_CREATED(dbType)));

	}

	public static List<String> getDocGroupTable(DBType dbType) {
		return getDBResult(dbType, replace(DCG_GROUP, "DCG_DATE_CREATED", getDM_DATE_CREATED(dbType)));
	}

	public static List<String> getIDs(DBType dbType, String caseNum, String peId, String query) {
		// String cmrId = getCMRID(dbType, caseNum, peId, "=autotst");
		return getDBResult(dbType, replace(query, "CMR_ID", "2315338"));
	}

	/** query to find the panel judges */
	public static List<String> getPanelJudgesPRID(DBType dbType, String caseNum, String peId) {
		return getIDs(dbType, caseNum, peId, PANEL_JUDGES_PR_PRID);
	}

	/** query to find the logged in judges PRID */
	public static List<String> getLogedInJudgesPRID(DBType dbType, String caseNum, String peId) {
		return getIDs(dbType, caseNum, peId, getID(LOGED_IN_JUDGES_PR_PRID, peId));
	}

	/** query to find the logged in judges Last name */
	public static List<String> getLogedInJudgesLastName(DBType dbType, String caseNum, String peId) {
		return getIDs(dbType, caseNum, peId, getID(LOGED_IN_JUDGES_LAST_NAME, peId));
	}

	/** query to find the logged in Judges Chamber's Group ID */
	public static List<String> getLogedInJudgesChambersGroupID(DBType dbType, String caseNum, String peId) {
		String judgesPRID = toArray(getLogedInJudgesPRID(dbType, caseNum, peId));
		String judgesLastName = toArray(getLogedInJudgesLastName(dbType, caseNum, peId));
		return getDBResult(dbType, getID(replace(CHAMBERS_GROUP_ID, "PR_LAST_NAME", judgesLastName), judgesPRID));
	}

	/**
	 * To get the group IDs, run the below query for each prid found in the first
	 * query
	 */
	public static List<String> getPanelMembersChambers(DBType dbType, String caseNum, String peId) {
		String judgesPRID = toArray(getLogedInJudgesPRID(dbType, caseNum, peId));
		String judgesLastName = toArray(getLogedInJudgesLastName(dbType, caseNum, peId));
		return getDBResult(dbType, getID(replace(CHAMBERS_GROUP_ID, "PR_LAST_NAME", judgesLastName), judgesPRID));
	}

	public static List<String> getDBResult(DBType dbType, String query) {
		List<String> dbResult = null;
		try {
			dbResult = executeQuery(dbType, query);
			sort(dbResult);
		} catch (NullPointerException e) {

			e.getMessage();
		}
		return dbResult;
	}

	public static String toArray(List<String> num) {
		String id = "";
		Object[] objects = num.toArray();
		for (Object obj : objects)
			id += obj;
		return id;
	}

}
