package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.JUDGE_VOTE_DPF_RELIEF;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.getCMRID;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.isDisplayed;
import static org.junit.Assert.assertTrue;

import java.util.List;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;

public class Site_TablePage extends AppiumPageFactory {

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

}
