package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.APPLICABLE_ACTIONS;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.MBR_EVENT;
import static gov.uscourts.ao.mobileBriefcase.common.Base.driver;
import static gov.uscourts.ao.mobileBriefcase.common.BriefcaseUsers.Users.APPELLATE_JUDGES;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.getCategories;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.getCollapsablePanel;
import static java.util.Collections.sort;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.WithTimeout;
import io.appium.java_client.pagefactory.iOSFindBy;

public class ActionsPanelPage {

	public ActionsPanelPage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	private int startRow = 2;
	private int cellIndex = 1;
	private String name = "DocumentList";

	@WithTimeout(time = 10, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//*[contains(@name, 'Actions')]")
	public static MobileElement actions;

	@WithTimeout(time = 10, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//*[contains(@name, 'User')]")
	public static MobileElement selectUser;



	public void verifyActionsPanelIsDisplayed(String actionsPanel) {

		performPageLoad();
		try {
			if (executeQuery(MBR_EVENT).size() > 0) {
				assertTrue(actions.isDisplayed());
				actions.click();
			} else {
				assertTrue(!(executeQuery(MBR_EVENT).size() > 0));

			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

	}

	public void compareApplicableActions() {

		List<String> dbApplicableActions = executeQuery(APPLICABLE_ACTIONS);
		sort(dbApplicableActions);

		try {

			if (executeQuery(MBR_EVENT).size() > 0 && actions.isDisplayed()) {
				
		
				assertTrue("------APPLICABLE ACTIONS MISMACTH------",
						dbApplicableActions.containsAll(getCategories(name, startRow, executeQuery(APPLICABLE_ACTIONS).size(), cellIndex)));

				actions.click();
			} else {

				assertTrue(!(executeQuery(MBR_EVENT).size() > 0));
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} finally {
			getCollapsablePanel(selectUser, APPELLATE_JUDGES);

		}

	}

}
