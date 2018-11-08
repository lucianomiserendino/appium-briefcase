package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.valueOf;
import static gov.uscourts.ao.mobileBriefcase.Pages.iOS_CommonPages.getActionsPanel;
import static gov.uscourts.ao.mobileBriefcase.Pages.iOS_CommonPages.verifyElementIsDisplayed;

import java.util.List;

import gov.uscourts.ao.mobileBriefcase.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.models.ElListText;

public class iOS_DocWPPage extends AppiumPageFactory {
	ElListText list;

	public void selectAction(List<ElListText> table, int index, String dbType) {
		list = table.get(index);
		getActionsPanel(valueOf(dbType), list.getEl_id());

	}

	public void verifyUploadDocPageIsDisplayed(String UploadDocuments, String notSelected, String enterDescription,
			String selectFile, String removeFile, String submit) {
		verifyElementIsDisplayed(UploadDocuments);
		verifyElementIsDisplayed(notSelected);
		verifyElementIsDisplayed(enterDescription);
		verifyElementIsDisplayed(selectFile);
		verifyElementIsDisplayed(removeFile);
		verifyElementIsDisplayed(submit);
	}

}
