package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ACTION_NAME;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CHAMBERS_GROUP_ID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DCG_GROUP;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DM_DATE_CREATED;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DM_DESCRIPTION;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DU_PRID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.LOGED_IN_JUDGES_LAST_NAME;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.LOGED_IN_JUDGES_PR_PRID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.MBR_NOTE;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.PANEL_JUDGES_PR_PRID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.PR_LAST_NAME;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.getCMRID;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.selectAction;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.getText;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.replace;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.sendKeys;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.getParameter;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.getStreamOfRandomInts;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.scrollUp;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.toArray;
import static java.util.Collections.sort;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;

import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.model.ElListText;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class DBDocketingDPFPage extends AppiumPageFactory {

	// @WithTimeout(time = 10, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='DocketingDPFList']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeTextView")
	public static MobileElement descriptionField;

	// @WithTimeout(time = 10, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='DocketingDPFList']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeTextView")
	public static MobileElement commentField;

	// @WithTimeout(time = 10, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "//*[contains(@name, 'User')]")
	public static MobileElement selectUser;

	@iOSXCUITFindBy(id = "Submit")
	public static MobileElement submit;

	@iOSXCUITFindBy(id = "Yes")
	public static MobileElement YESbtn;

	// @WithTimeout(time = 100, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(id = "OK")
	public static MobileElement OKbtn;

	ElListText list;

	public void selectActioName(List<ElListText> table, int index, String caseNum, String peID,
			List<UserInputData> userInputData) {

		list = table.get(index);

		scrollUp(By.id("DocumentList"));
		selectAction("Actions", list.getElListText(), userInputData);
		sendKeys(commentField, "Test-" + getStreamOfRandomInts());
		sendKeys(descriptionField, "Test-" + getStreamOfRandomInts() + "-");
		String description = getText(descriptionField);
		// try {
		tap(submit);
		// tap(YESbtn);
		// tap(OKbtn);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		getDataTable(table, index, caseNum, peID, userInputData);

		assertEquals(description,getAllColumns( DM_DESCRIPTION, userInputData));

	}

	public void getDataTable(List<ElListText> table, int index, String caseNum, String peID,
			List<UserInputData> userInputData) {
		list = table.get(index);
		
		
		System.out.println(getAllColumns( Queries.DM_ACC_CRT,userInputData)+"***************");
		System.out.println(getAllColumns( Queries.DM_ACC_CTLINK,userInputData)+"***************");
		System.out.println(getAllColumns( Queries.DM_ACC_SPEC,userInputData)+"***************");
		
		// assertEquals(getAllColumns(dbType, DM_ACC_CRT), list.getDm_acc_crt());

		// assertEquals(getAllColumns(dbType, DM_ACC_CTLINK), list.getDm_acc_ctlink());

		// assertEquals(getAllColumns(dbType, DM_ACC_SPEC), list.getDm_acc_spec());

		getDocUserRecord(table, index, caseNum, peID, userInputData);
	}

	public static String getActionName(String el_id, List<UserInputData> userInputData) {
		return getAllColumns(getID(ACTION_NAME, el_id), userInputData);
	}

	public void getDocUserRecord(List<ElListText> table, int index, String caseNum, String peId,
			List<UserInputData> userInputData) {
		list = table.get(index);
		List<String> docUserTable = getDocUserTable(userInputData);
		List<String> docGroupTable = getDocGroupTable(userInputData);
		List<String> panelJudges = getPanelJudgesPRID(caseNum, peId, userInputData);
		List<String> loggedInJudgesChambersGroupID = getLogedInJudgesChambersGroupID(caseNum, peId, userInputData);

		String actionName = getActionName(list.getElListText(), userInputData).trim();
		System.out.println(actionName + "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");

		/** No doc_group or doc_user records will be created */
		if (actionName.equals("note - court users")) {

			System.out.println(docUserTable + "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
			System.out.println(docGroupTable + "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");

			// assertNull(docUserTable);
			// assertNull(docGroupTable);

			/** No doc_group or doc_user records will be created */
		} else if (actionName.equals("note - court users linked to case")) {
			System.out.println(docUserTable + "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
			System.out.println(docGroupTable + "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");

			// assertNull(docUserTable);
			// assertNull(docGroupTable);

			/**
			 * A doc_user record will be created for each judge on the panel.No doc_group
			 * records will be created
			 */
		} else if (actionName.equals("note - panel judges only")) {

			System.out.println(docUserTable + "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
			System.out.println(panelJudges + "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
			System.out.println(docGroupTable + "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");

			// assertEquals(docUserTable, panelJudges);
			// assertNull(docGroupTable);

			/**
			 * A doc_user record will be created for each judge on the panel. A doc_group
			 * record will be created for the logged in judge's chambers group
			 */
		} else if (actionName.equals("note - panel judges and users chambers")) {

			System.out.println(docUserTable + "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
			System.out.println(panelJudges + "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
			System.out.println(docGroupTable + "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
			System.out.println(loggedInJudgesChambersGroupID + "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");

			// assertEquals(docUserTable, panelJudges);
			// assertEquals(docGroupTable, loggedInJudgesChambersGroupID);

			/**
			 * A doc_user record will not be created. A doc_group record will be created for
			 * the logged in judge's chambers only
			 */
		} else if (actionName.equals("note - panel judges chambers")) {

			System.out.println(docUserTable + "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
			System.out.println(docGroupTable + "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
			System.out.println(getchambersGroupId(panelJudges, caseNum, peId, userInputData)
					+ "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");

			// assertNull(docUserTable);
			// assertEquals(docGroupTable, getchambersGroupId(dbType, panelJudges, caseNum,
			// peId));

		} else if (actionName.equals("note - users chambers")) {

			System.out.println(docUserTable + "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
			System.out.println(docGroupTable + "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
			System.out.println(loggedInJudgesChambersGroupID + "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");

			// assertNull(docUserTable);
			// assertEquals(docGroupTable, loggedInJudgesChambersGroupID);

		} else if (actionName.equals("note - only groups and users")) {

			List<String> defaultPersonIDs = getDefaultIds(list.getElListText(), 3, userInputData);

			List<String> defaultGroupIDs = getDefaultIds(list.getElListText(), 2, userInputData);

			System.out.println(defaultPersonIDs + "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
			System.out.println(docUserTable + "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
			System.out.println(defaultGroupIDs + "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
			System.out.println(docGroupTable + "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");

			// assertEquals(defaultPersonIDs, docUserTable);
			// assertEquals(defaultGroupIDs, docGroupTable);

		}

	}

	public static List<String> getchambersGroupId(List<String> panelJudges, String caseNum, String peId,
			List<UserInputData> userInputData) {
		String cmrId = getCMRID("cmr_id", caseNum, peId, "autotst", userInputData);
		List<String> groupId = new ArrayList<>();
		List<String> chambersGroupId = new ArrayList<>();
		List<String> pr_prid = panelJudges;

		for (int a = 0; a < pr_prid.size(); a++) {

			List<String> prLastName = executeQuery(replace(PR_LAST_NAME, "CMR_ID", cmrId, "PR_PRID", pr_prid.get(a)),
					userInputData);

			for (int i = 0; i < prLastName.size(); i++) {

				groupId = getDBResult(
						getID(replace(CHAMBERS_GROUP_ID, "PR_LAST_NAME", prLastName.get(i)), pr_prid.get(a)),
						userInputData);

				for (int j = 0; i < groupId.size(); i++) {

					chambersGroupId.add(groupId.get(j));
				}
			}
		}
		sort(chambersGroupId);
		return chambersGroupId;
	}

	public static String getDM_DATE_CREATED(List<UserInputData> userInputData) {
		return getAllColumns(DM_DATE_CREATED, userInputData);
	}

	public static List<String> getDocUserTable(List<UserInputData> userInputData) {
		return getDBResult(replace(DU_PRID, "DU_DATE_CREATED", getDM_DATE_CREATED(userInputData)), userInputData);

	}

	public static List<String> getDocGroupTable(List<UserInputData> userInputData) {
		return getDBResult(replace(DCG_GROUP, "DCG_DATE_CREATED", getDM_DATE_CREATED(userInputData)), userInputData);
	}

	public static List<String> getIDs(String caseNum, String peId, String query, List<UserInputData> userInputData) {
		String cmrId = getCMRID("cmr_id", caseNum, peId, "autotst", userInputData);
		return getDBResult(replace(query, "CMR_ID", cmrId), userInputData);
	}

	/** query to find the panel judges */
	public static List<String> getPanelJudgesPRID(String caseNum, String peId, List<UserInputData> userInputData) {
		return getIDs(caseNum, peId, PANEL_JUDGES_PR_PRID, userInputData);
	}

	/** query to find the logged in judges PRID */
	public static List<String> getLogedInJudgesPRID(String caseNum, String peId, List<UserInputData> userInputData) {
		return getIDs(caseNum, peId, getID(LOGED_IN_JUDGES_PR_PRID, peId), userInputData);
	}

	/** query to find the logged in judges Last name */
	public static List<String> getLogedInJudgesLastName(String caseNum, String peId,
			List<UserInputData> userInputData) {
		return getIDs(caseNum, peId, getID(LOGED_IN_JUDGES_LAST_NAME, peId), userInputData);
	}

	/** query to find the logged in Judges Chamber's Group ID */
	public static List<String> getLogedInJudgesChambersGroupID(String caseNum, String peId,
			List<UserInputData> userInputData) {
		String judgesPRID = toArray(getLogedInJudgesPRID(caseNum, peId, userInputData));
		String judgesLastName = toArray(getLogedInJudgesLastName(caseNum, peId, userInputData));
		return getDBResult(getID(replace(CHAMBERS_GROUP_ID, "PR_LAST_NAME", judgesLastName), judgesPRID),
				userInputData);
	}

	/**
	 * To get the group IDs, run the below query for each prid found in the first
	 * query
	 */
	public static List<String> getPanelMembersChambers(String caseNum, String peId, List<UserInputData> userInputData) {
		String judgesPRID = toArray(getLogedInJudgesPRID(caseNum, peId, userInputData));
		String judgesLastName = toArray(getLogedInJudgesLastName(caseNum, peId, userInputData));
		return getDBResult(getID(replace(CHAMBERS_GROUP_ID, "PR_LAST_NAME", judgesLastName), judgesPRID),
				userInputData);
	}

	public static List<String> getDBResult(String query, List<UserInputData> userInputData) {
		List<String> dbResult = null;
		try {
			dbResult = executeQuery(query, userInputData);
			sort(dbResult);
		} catch (NullPointerException e) {

			e.getMessage();
		}
		return dbResult;
	}

	public static List<String> getDefaultIds(String elId, int index, List<UserInputData> userInputData) {

		String[] ID = null;

		String defaultID = getParameter(getAllColumns(getID(MBR_NOTE, elId), userInputData), "note", index)
				.split("\\|")[1];

		List<String> deIDs = new ArrayList<>();

		if (defaultID.contains(":")) {
			ID = defaultID.split(":");
			for (int i = 0; i < ID.length; i++) {
				deIDs.add(ID[i]);
			}
		} else {
			deIDs.add(defaultID);
		}
		return deIDs;

	}

}
