package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.changeDateFormat;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities;
import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import gov.uscourts.ao.mobileBriefcase.stepDefinitions.Document_StepDefinitions;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ReplaceAnnotatedDocuementsPage extends AppiumPageFactory {

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='+' or @name='-']")
	public static List<WebElement> replacedIcon;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name, 'Applied Referrals')]")
	public static List<WebElement> appliedRefs;

	static String panel = "";
	static int randomDoc;
	static String docName = "";
	static String filedDate = "";
	static String annotatedBy = "";
	static String replacedDate = "";

	public static void getDocumentCategoryList() {

		if (appliedRefs.size() > 0) {

			panel = "Applied";

		} else {
			panel = "Actions";
		}
		Boolean elementNotFound = true;

		while (elementNotFound) {
			for (int i = 0; i < DocumentPage.getDocCategoryLocator(panel).size(); i++) {

				DocumentPage.getDocCategoryLocator(panel).get(i).click();

				if (replacedIcon.size() > 0) {

					for (int j = 0; j < replacedIcon.size(); j++) {
						if (replacedIcon.get(j).getAttribute("name").trim().equals("-")) {
							replacedIcon.get(j).click();
						}
					}

					if (replacedIcon.size() > 1) {
						randomDoc = Utility.getRandomInt(replacedIcon.size() - 1);

					} else {
						randomDoc = 0;
					}
					int a = randomDoc + 1;

					WebElement replacedDoc = getDocInformation(a, 2);
					WebElement filed = getDocInformation(a, 1);
					filedDate = filed.getText().trim();
					docName = replacedDoc.getText().trim();

					replacedIcon.get(randomDoc).click();

					String by = "Annotated by";
					annotatedBy = isAnnotatedBy(Actions.containsElement(by)).getText();

					replacedDate = isAnnotatedBy(
							Actions.containsElement(by) + "/following::XCUIElementTypeStaticText[1]").getText();

					elementNotFound = false;

					break;

				} else {
					DocumentPage.getDocCategoryLocator(panel).get(i).click();
					elementNotFound = true;
				}
			}
		}

	}

	public static WebElement isAnnotatedBy(String xpath) {
		return Actions.findElementBy(Locator.XPATH, xpath);
	}

	public void isDisplayed(List<UserInputData> userInputData) {

		String ur_username = getDBColumn(2, userInputData).toString();

		String de_date_filed = getDBColumn(2, userInputData).toString();

		String month = "";

		String date = filedDate.split(", ")[1].split(" ")[0];

		String nameOfTheMonth = date.split(" ")[0];

		month += Utility.parseMonthName(nameOfTheMonth);

		filedDate += date.replace(nameOfTheMonth, month).trim();

		String uiFiledDate = changeDateFormat(filedDate.replace(",", ""), "MM dd yyyy", "yyyy-M-d");

		Assert.assertEquals(de_date_filed, uiFiledDate);

		Assert.assertEquals(ur_username, annotatedBy);

	}

	public List<String> getDBColumn(int col, List<UserInputData> userInputData) {
		String caseNum = Document_StepDefinitions.regularCase;
		String cs_year = Utility.splitBy(caseNum, 0);
		String cs_number = Utility.splitBy(caseNum, 1);
		return DBUtilities.execute(Actions.replace(Queries.REPLACED_ANNOTATED_DOC, "CS_YEAR", cs_year, "CS_NUMBER",
				cs_number, "DM_DESCRIPTION", docName), col, userInputData);

	}

	public static WebElement getDocInformation(int randomDoc, int row) {
		return Actions.findElementBy(Locator.XPATH, "//XCUIElementTypeButton[@name= '+'][" + randomDoc
				+ "]/following::XCUIElementTypeStaticText[@name='Downloaded'][1]/preceding::XCUIElementTypeStaticText["
				+ row + "]");

	}

}
