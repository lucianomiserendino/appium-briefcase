package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getEL_Function;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DM_ACC_CRT;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DM_ACC_CTLINK;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DM_ACC_SPEC;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DM_DESCRIPTION;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DOC_USER;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DU_PRID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.MBR_EVENT;
import static gov.uscourts.ao.mobileBriefcase.common.Base.driver;
import static gov.uscourts.ao.mobileBriefcase.common.BriefcaseUsers.Users.APPELLATE_JUDGES;
import static gov.uscourts.ao.mobileBriefcase.common.BriefcaseUsers.Users.COLLOTON_STEVEN;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.clickOnPanel;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.Actions.ACTIONS;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.Actions.SCREENING_PANELS;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.clickOn;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.getPanel;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.getStreamOfRandomInts;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.getText;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.getUserCategory;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.selectCaseNumber;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.sendKeys;
import static java.util.Collections.sort;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.common.Constants;
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

	public void selectAJudje() {
		getUserCategory(APPELLATE_JUDGES, selectUser, COLLOTON_STEVEN);
	}

	public void selectCase(String caseNumber) {
		selectCaseNumber(SCREENING_PANELS, caseNumber);
	}

	public void selectActionPanel(String actionsPanel) {
		getPanel(MBR_EVENT, " THERE'RE NO ACTIONS OR MBR_EVENT TABLE IS EMPTY ", actionsPanel);
	}

	public void selectAction(String action, mbrNotes notes) {
		try {
			clickOnPanel(ACTIONS, action);
			sendKeys(commentField, "Test-" + getStreamOfRandomInts());
			sendKeys(descriptionField, "Test-" + getStreamOfRandomInts() + "-");
			String description = getText(descriptionField);
			clickOn(submit);
			clickOn(YESbtn);
			clickOn(OKbtn);
			getDBRecords(notes);
			assertEquals(description, getAllColumns(DBType.CMKA,DM_DESCRIPTION));
	
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getDBRecords(mbrNotes note) {

		switch (note) {

		case COURT_USERS:

			getDBNotes(COURT_USERS, eventCode.COURT_USERS, DM_ACC_CRT, Y, DM_ACC_CTLINK, N, DM_ACC_SPEC, N);

			break;

		case COURT_USERS_LINKED_TO_CASE:

			getDBNotes(COURT_USERS_LINKED_TO_CASE, eventCode.COURT_USERS_LINKED_TO_CASE, DM_ACC_CRT, N, DM_ACC_CTLINK,
					Y, DM_ACC_SPEC, N);

			break;

		case PANEL_JUDGES_ONLY:

			getDBNotes(PANEL_JUDGES_ONLY, eventCode.PANEL_JUDGES_ONLY, DM_ACC_CRT, N, DM_ACC_CTLINK, N, DM_ACC_SPEC, Y);

			break;

		case PANEL_JUDGES_AND_USERs_CHAMBERS:

			getDBNotes(PANEL_JUDGES_AND_USERs_CHAMBERS, eventCode.PANEL_JUDGES_AND_USERs_CHAMBERS, DM_ACC_CRT, N,
					DM_ACC_CTLINK, N, DM_ACC_SPEC, Y);

			break;

		case PANEL_JUDGES_CHAMBERS:

			getDBNotes(PANEL_JUDGES_CHAMBERS, eventCode.PANEL_JUDGES_CHAMBERS, DM_ACC_CRT, N, DM_ACC_CTLINK, N,
					DM_ACC_SPEC, Y);

			break;

		case USERs_CHAMBERS:

			getDBNotes(USERs_CHAMBERS, eventCode.PANEL_JUDGES_ONLY, DM_ACC_CRT, N, DM_ACC_CTLINK, N, DM_ACC_SPEC, Y);

			break;

		case ONLY_GROUPS_AND_USERS:

			getDBNotes(ONLY_GROUPS_AND_USERS, eventCode.PANEL_JUDGES_ONLY, DM_ACC_CRT, N, DM_ACC_CTLINK, N, DM_ACC_SPEC,
					Y);

			break;

		default:
			break;
		}

	}

	public void verifyDOC_USER() {
		assertEquals(getSortedList(DU_PRID), getSortedList(DOC_USER));
	
	}

	public static List<String> getSortedList(String query) {
		List<String> sortedList = new ArrayList<>();
		sortedList.addAll(executeQuery(DBType.CMKA,query));
		sort(sortedList);
		return sortedList;

	}

	public void getDBNotes(String mbrNoteCourtUsersElId, String uiDestrictParam, String field1, String value1,
			String field2, String value2, String field3, String value3) {
		try {

			if (getEL_Function(mbrNoteCourtUsersElId, 5).equals(uiDestrictParam)) {
				assertEquals(getAllColumns(DBType.CMKA,field1), value1);

				assertEquals(getAllColumns(DBType.CMKA,field2), value2);
		
				assertEquals(getAllColumns(DBType.CMKA,field3), value3);
		
			} else {
	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void getMRB_NOTE(mbrNotes note) {
		switch (note) {
		case COURT_USERS:
			assertTrue(DM_ACC_CTLINK.equals(Y));
			break;

		case COURT_USERS_LINKED_TO_CASE:
			assertTrue(DM_ACC_CTLINK.equals(Y));
			break;

		case PANEL_JUDGES_ONLY:
			assertTrue(DM_ACC_SPEC.equals(Y));
			break;

		case PANEL_JUDGES_AND_USERs_CHAMBERS:
			assertTrue(DM_ACC_SPEC.equals(Y));
			break;

		case PANEL_JUDGES_CHAMBERS:
			assertTrue(DM_ACC_SPEC.equals(Y));
			break;

		case USERs_CHAMBERS:
			assertTrue(DM_ACC_SPEC.equals(Y));
			break;

		default:
			break;
		}
	}

	public enum mbrNotes {

		COURT_USERS, COURT_USERS_LINKED_TO_CASE, PANEL_JUDGES_ONLY, PANEL_JUDGES_AND_USERs_CHAMBERS, PANEL_JUDGES_CHAMBERS, USERs_CHAMBERS, ONLY_GROUPS_AND_USERS
	}

	public interface eventCode {

		public static final String COURT_USERS = "ynnnn";
		public static final String COURT_USERS_LINKED_TO_CASE = "nynnn";
		public static final String PANEL_JUDGES_ONLY = "nnnny";
		public static final String PANEL_JUDGES_AND_USERs_CHAMBERS = "nnyny";
		public static final String PANEL_JUDGES_CHAMBERS = "nnnyn";
		public static final String USERs_CHAMBERS = "nnynn";

	}

}
