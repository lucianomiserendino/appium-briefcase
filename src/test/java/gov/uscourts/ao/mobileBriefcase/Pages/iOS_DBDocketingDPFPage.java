package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.valueOf;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DM_ACC_CRT;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DM_ACC_CTLINK;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DM_ACC_SPEC;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DM_DESCRIPTION;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DOC_USER;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DU_PRID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.MBR_NOTE;
import static gov.uscourts.ao.mobileBriefcase.common.Base.driver;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.clickOnPanel;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.Actions.ACTIONS;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.clickOn;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.getParameter;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.getStreamOfRandomInts;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.getText;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.sendKeys;
import static java.util.Collections.sort;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
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

	public void selectAction(List<ElListText> table, int index, String dbType) {

		list = table.get(index);
		try {
			clickOnPanel(ACTIONS, list.getElListText());
			sendKeys(commentField, "Test-" + getStreamOfRandomInts());
			sendKeys(descriptionField, "Test-" + getStreamOfRandomInts() + "-");
			String description = getText(descriptionField);
			clickOn(submit);
			clickOn(YESbtn);
			clickOn(OKbtn);
			getDataTable(table, index, valueOf(dbType));
			assertEquals(description, getAllColumns(valueOf(dbType), DM_DESCRIPTION));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getDataTable(List<ElListText> table, int index, DBType dbType) {

		list = table.get(index);
		assertEquals(getAllColumns(dbType, DM_ACC_CRT), list.getDm_acc_crt());
		assertEquals(getAllColumns(dbType, DM_ACC_CTLINK), list.getDm_acc_ctlink());
		assertEquals(getAllColumns(dbType, DM_ACC_SPEC), list.getDm_acc_spec());

	}

	public void verifyDOC_USER() {
		assertEquals(getSortedList(DU_PRID), getSortedList(DOC_USER));

	}

	public static List<String> getSortedList(String query) {
		List<String> sortedList = new ArrayList<>();
		sortedList.addAll(executeQuery(DBType.CMKA, query));
		sort(sortedList);
		return sortedList;

	}
	
	
public static void main(String[] args) {
	System.out.println(getParameter(getAllColumns(DBType.CMKA, getID(MBR_NOTE, "3060")), 0));
	System.out.println(getAllColumns(DBType.CMKA, getID(MBR_NOTE, "3060")));

}

}
