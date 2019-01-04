package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.*;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.BRIEFCASE_CT_ADMIN_DKT_VALUE;
import static gov.uscourts.ao.mobileBriefcase.Pages.iOS_LoginPage.searchForACase;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.common.AppiumPageFactory;

public class iOS_DocketEntriesPage extends AppiumPageFactory {

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
		insertData(dbType, getID(BRIEFCASE_CT_ADMIN_DKT_VALUE, value));
	}


	
}
