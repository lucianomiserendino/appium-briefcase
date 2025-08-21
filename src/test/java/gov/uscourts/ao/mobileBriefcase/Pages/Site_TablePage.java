package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.JUDGE_VOTE_DPF_RELIEF;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.getCMRID;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.isDisplayed;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Base;
import gov.uscourts.ao.mobileBriefcase.page.common.Configuration;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.SystemPropertySetup;

public class Site_TablePage extends AppiumPageFactory {

	public final String sysadminUserName = "sysadminUserName";
	public final String sysadminPassword = "sysadminPassword";

	public void verifyRerilefIsDisplayed(DBType dbType, String id, String caseNum, String peId, String cmr_cyv_code) {

		String ccr_id = getCMRID(dbType, id, caseNum, peId, cmr_cyv_code);

		List<String> reliefs = getRelief(dbType, ccr_id);
		for (int i = 0; i < reliefs.size(); i++) {

			assertTrue(isDisplayed(Locator.XPATH,
					"//XCUIElementTypeOther[@name='DocumentList']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/ XCUIElementTypeStaticText[contains(@name, 'Vote Information')]/following:: XCUIElementTypeStaticText[contains(@name, '"
							+ reliefs.get(i) + "')]"));
		}
	}

	public static List<String> getRelief(DBType dbType, String ccr_id) {
		return executeQuery(dbType, getID(JUDGE_VOTE_DPF_RELIEF, ccr_id));

	}

	public void updateEventListTable(String elId,String dpf, List<UserInputData> userInputData) {

		String courtId = SystemPropertySetup.getCourtId(userInputData);

		Base.safariInstance();

		changeWindow("WEBVIEW");

		driver.navigate().to("https://cms-ecf-" + courtId + ".tsso.dcn/cmecf/servlet/STEditor");

		changeWindow("WEBVIEW");

		driver.findElement(By.name("usernameEntered"))
				.sendKeys(Configuration.getProperty(courtId + "." + this.sysadminUserName));
		driver.findElement(By.name("password"))
				.sendKeys(Configuration.getProperty(courtId + "." + this.sysadminPassword));

		driver.findElement(By.id("SUBMIT2")).click();

		Page.sleep(2000);

		if (driver.findElement(By.xpath("//strong[contains(text(),'Single Table Editor')]")).isDisplayed() == true) {

			WebElement selectElement = driver.findElement(By.name("table"));

			Select dropdown = new Select(selectElement);

			WebElement siteOption = dropdown.getOptions().stream()
					.filter(option -> option.getAttribute("value").equals("event_list")).findFirst().orElse(null);

			if (siteOption != null) {
				System.out.println("Option found: " + siteOption.getText());
				dropdown.selectByValue("event_list");
			} else {
				System.out.println("Option 'site' not found.");
			}

			WebElement selectButton = driver.findElement(By.name("submitButton"));
			selectButton.click();

			Page.sleep(2000);

			driver.findElement(By.name("el_id_primaryAuto")).sendKeys(elId);
			driver.findElement(By.name("searchButton")).click();

			Page.sleep(2000);

			WebElement hiddenInput = driver.findElement(By.name("el_functions__________1"));
			hiddenInput.clear();

			String newElfunctionVal = "judgeVote('ynnnn','SKIP','SKIP','JudgeVote Note','yes','ap:dn:gr','ynnnn','SKIP','SKIP','SKIP','SKIP');\n"
					+ "chmSilentAssign('" + dpf + "','bench','SKIP','SKIP','1')";
			
			hiddenInput.sendKeys(newElfunctionVal);

			WebElement updateRecord = driver.findElement(By.name("update1"));
			updateRecord.click();

			String actionName = getAllColumns(getID(Queries.MBR_NOTE, elId), userInputData).trim();

			String normalizedNewValue = newElfunctionVal.replaceAll("\\s+", "");
			String normalizedAction = actionName.replaceAll("-+$", "").replaceAll("\\s+", "");
			assertEquals("el_functions was not updated correctly for el_id. Expected: " + newElfunctionVal, normalizedNewValue,
					normalizedAction);

		}
	}

}
