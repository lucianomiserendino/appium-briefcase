package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.Pages.JenieLoginPage.searchForACase;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.contains;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.performPageLoad;

import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;

public class DocketEntryPage extends AppiumPageFactory {

	public String getDocketEntries(String caseNum) {
		searchForACase(caseNum);
		contains(caseNum).click();
		performPageLoad(driver);
		return contains("Docket Entries - ").getText().split("-")[1].trim();
	}

}
