package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.insertData;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.SITE_TABLE_VARIABLE_VALUE;
import static gov.uscourts.ao.mobileBriefcase.Pages.JenieLoginPage.searchForACase;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities;
import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.common.AppiumPageFactory;

public class DocketEntriesPage extends AppiumPageFactory {

	public String getDocketEntries(String caseNum) {
		searchForACase(caseNum);
		contains(caseNum).click();
		performPageLoad(driver);
		return contains("Docket Entries - ").getText().split("-")[1].trim();
	}

	/**
	 * This method changes the value of the site table variable
	 * "briefcaseCtAdminDkt"
	 */
	public void changeValue(DBType dbType, String value) {
		insertData(dbType, getID(SITE_TABLE_VARIABLE_VALUE, value));
	}


	
}
