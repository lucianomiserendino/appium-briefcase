package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.valueOf;
import static gov.uscourts.ao.mobileBriefcase.Pages.iOS_CommonPages.getActionsPanel;
import static gov.uscourts.ao.mobileBriefcase.Pages.iOS_CommonPages.verifyElementIsDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Base.driver;

import java.util.List;

import org.openqa.selenium.support.PageFactory;

import gov.uscourts.ao.mobileBriefcase.models.ElListText;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class iOS_DocWPPage {
	ElListText list;

	public iOS_DocWPPage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

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
