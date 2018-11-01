package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.valueOf;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CHAMBERS_GROUP_ID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DCG_GROUP;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DM_DATE_CREATED;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DM_DESCRIPTION;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DU_PRID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.LOGED_IN_JUDGES_LAST_NAME;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.LOGED_IN_JUDGES_PR_PRID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.PANEL_JUDGES_PR_PRID;
import static gov.uscourts.ao.mobileBriefcase.Pages.iOS_CommonPages.getActionsPanel;
import static gov.uscourts.ao.mobileBriefcase.Pages.iOS_CommonPages.getCMRID;
import static gov.uscourts.ao.mobileBriefcase.common.Base.driver;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.clickOn;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.getStreamOfRandomInts;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.getText;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.replace;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.sendKeys;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.common.Constants;
import gov.uscourts.ao.mobileBriefcase.models.ElListText;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.WithTimeout;
import io.appium.java_client.pagefactory.iOSFindBy;

public class iOS_DBDocketingDPFPage implements Constants {

	public iOS_DBDocketingDPFPage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

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

	@iOSFindBy(id = "OK")
	public static MobileElement OKbtn;

	ElListText list;

	public void selectAction(List<ElListText> table, int index, String dbType, String caseNum, String peID) {

		list = table.get(index);
		try {
			
			getActionsPanel(valueOf(dbType), list.getElListText());
			sendKeys(commentField, "Test-" + getStreamOfRandomInts());
			sendKeys(descriptionField, "Test-" + getStreamOfRandomInts() + "-");
			String description = getText(descriptionField);
			clickOn(submit);
			clickOn(YESbtn);
			clickOn(OKbtn);
			getDataTable(table, index, valueOf(dbType), caseNum, peID);
			assertEquals(description, getAllColumns(valueOf(dbType), DM_DESCRIPTION));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getDataTable(List<ElListText> table, int index, DBType dbType, String caseNum, String peID) {
		list = table.get(index);
		// assertEquals(getAllColumns(dbType, DM_ACC_CRT), list.getDm_acc_crt());
		// assertEquals(getAllColumns(dbType, DM_ACC_CTLINK), list.getDm_acc_ctlink());
		// assertEquals(getAllColumns(dbType, DM_ACC_SPEC), list.getDm_acc_spec());
		getDocUserRecord(dbType, table, index, caseNum, peID);
	}

	public void getDocUserRecord(DBType dbType, List<ElListText> table, int index, String caseNum, String peId) {
		list = table.get(index);

		String docUserTable = getDocUserTable(dbType);
		String docGroupTable = getDocGroupTable(dbType);
		String panelJudges = getPanelJudgesPRID(dbType, caseNum, peId);
		String panelJudgesChambersGroupID = getLogedInJudgesChambersGroupID(dbType, caseNum, peId);

		/** No doc_group or doc_user records will be created */
		if (list.getElListText().equals("note - court users")) {
			assertTrue(isNull(docUserTable) && isNull(docGroupTable));

			/** No doc_group or doc_user records will be created */
		} else if (list.getElListText().equals("note - court users linked to case")) {
			assertTrue(isNull(docUserTable) && isNull(docGroupTable));

			/**
			 * A doc_user record will be created for each judge on the panel.No doc_group
			 * records will be created
			 */
		} else if (list.getElListText().equals("note - panel judges only")) {
			assertTrue(docUserTable.equals(panelJudges) && isNull(docGroupTable));

			/**
			 * A doc_user record will be created for each judge on the panel. A doc_group
			 * record will be created for the logged in judge's chambers group
			 */
		} else if (list.getElListText().equals("note - panel judges and users chambers")) {
			assertTrue(docUserTable.equals(panelJudges) && docGroupTable.equals(panelJudgesChambersGroupID));
			System.out.println(docGroupTable + "***************docGroupTable");
			System.out.println(panelJudgesChambersGroupID + "****************panelJudgesChambersGroupID");

			/**
			 * A doc_user record will not be created. A doc_group record will be created for
			 * the panel members chambers
			 */
		} else if (list.getElListText().equals("note - panel judges chambers")) {
			assertTrue(isNull(docUserTable));
			System.out.println(docGroupTable + "****************docGroupTable");

		}
	}

	public boolean isNull(String table) {
		return table.isEmpty();

	}

	public String getDM_DATE_CREATED(DBType dbType) {
		return getAllColumns(dbType, DM_DATE_CREATED);
	}

	public String getDocUserTable(DBType dbType) {
		return getAllColumns(dbType, replace(DU_PRID, "DU_DATE_CREATED", getDM_DATE_CREATED(dbType)));
	}

	public String getDocGroupTable(DBType dbType) {
		return getAllColumns(dbType, replace(DCG_GROUP, "DCG_DATE_CREATED", getDM_DATE_CREATED(dbType)));
	}

	public static String getIDs(DBType dbType, String caseNum, String peId, String query) {
		String cmrId = getCMRID(dbType, caseNum, peId, "in ('noargcs','pro','anders')");
		return getAllColumns(dbType, replace(query, "CMR_ID", cmrId));
	}

	/** query to find the panel judges */
	public static String getPanelJudgesPRID(DBType dbType, String caseNum, String peId) {
		return getIDs(dbType, caseNum, peId, PANEL_JUDGES_PR_PRID);
	}

	/** query to find the logged in judges PRID */
	public static String getLogedInJudgesPRID(DBType dbType, String caseNum, String peId) {
		return getIDs(dbType, caseNum, peId, getID(LOGED_IN_JUDGES_PR_PRID, peId));
	}

	/** query to find the logged in judges Last name */
	public static String getLogedInJudgesLastName(DBType dbType, String caseNum, String peId) {
		return getIDs(dbType, caseNum, peId, getID(LOGED_IN_JUDGES_LAST_NAME, peId));
	}

	/** query to find the logged in Judges Chamber's Group ID */
	public static String getLogedInJudgesChambersGroupID(DBType dbType, String caseNum, String peId) {
		String judgesPRID = getLogedInJudgesPRID(dbType, caseNum, peId);
		String judgesLastName = getLogedInJudgesLastName(dbType, caseNum, peId);
		return getAllColumns(dbType, getID(replace(CHAMBERS_GROUP_ID, "PR_LAST_NAME", judgesLastName), judgesPRID));

	}

}
