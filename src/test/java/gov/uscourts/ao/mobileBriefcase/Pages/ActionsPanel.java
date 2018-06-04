package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.common.Base.driver;
import static gov.uscourts.ao.mobileBriefcase.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.click;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.getCollapsablePanel;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.selectAUser;
import static java.util.Collections.sort;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.common.BriefcaseUsers.Users;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.WithTimeout;
import io.appium.java_client.pagefactory.iOSFindBy;

public class ActionsPanel {

	public ActionsPanel() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@WithTimeout(time = 5, unit = TimeUnit.SECONDS)

	String actions = "//*[contains(@name, 'Actions')]";

	@WithTimeout(time = 10, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//*[contains(@name, 'User')]")
	public static MobileElement selectUser;

	
	public void selectAnAttorney() {

		selectAUser(Users.COLLOTON_STEVEN);


	}
	
	public void verifyActionsPanelIsDisplayed(String actionsPanel) {

		performPageLoad();

		if (getActionsText() == true) {
			assertTrue(executeQuery(Queries.MBR_EVENT).size() > 0);
			click(actions);
		} else {
			assertTrue(getActionsText() == false);
		
		}

	}

	public boolean getActionsText() {
		return isDisplayed(By.xpath(actions));

	}

	public void compareApplicableActions() {

		try {
			List<String> dbApplicableActions = executeQuery(Queries.APPLICABLE_ACTIONS);
			sort(dbApplicableActions);
			if (executeQuery(Queries.MBR_EVENT).size() > 0 && getActionsText() == true) {
				assertTrue("------APPLICABLE ACTIONS MISMACTH------",
						dbApplicableActions.containsAll(getUiApplicableAction()));
				click(actions);
			} else {

				assertTrue(getActionsText() == false);
			}
		} catch (Exception e) {
			e.getMessage();
		} finally {
			getCollapsablePanel(selectUser, Users.APPELLATE_JUDGES);

		}

	}

	public static List<String> getUiApplicableAction() {

		List<String> uiApplicableActions = new ArrayList<>();

		for (int i = 2; i <= 12; ++i) {

			String actions = driver
					.findElement(By.xpath("//XCUIElementTypeTable[@name='DocumentList']/XCUIElementTypeCell[" + i
							+ "]/XCUIElementTypeStaticText[1]"))
					.getText().trim();
			uiApplicableActions.add(actions);
			sort(uiApplicableActions);
		}
		return uiApplicableActions;
	}
}
