package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getPE_ID;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.replace;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.getRandomInt;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.PageFactory.initElements;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.openqa.selenium.By;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Base;
import gov.uscourts.ao.mobileBriefcase.page.common.SystemPropertySetup;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class OtherSubmissions extends Base {

	public OtherSubmissions() {
		initElements(new AppiumFieldDecorator(getInstance(Driver.IOS)), this);
	}

	/**
	 * To find other submissions in the case, query the chm_mobile_referral table
	 * where the cmr_cs_caseid = caseid of the referral being viewed.
	 **/

	public void otherSubmissionsDisplayed(List<UserInputData> userInputData) {

		String cs_caseid = "";
		List<String> db_cyv_category = null;
		List<String> db_cmr_panel_members = null;
		List<String> db_cmr_ref_date = null;

		String name = SystemPropertySetup.getJudge(userInputData);
		String pe_id = getPE_ID("jud", name, userInputData);

		List<String> otherSubCase = executeQuery(getID(Queries.CMR_CS_CASEID, pe_id), userInputData);

		for (int i = 0; i < otherSubCase.size(); i++) {

			/** this finds the type of submission */
			List<String> refCategories = getOtherSubmissionsFromDb("cyv_category", pe_id, otherSubCase.get(i),userInputData);

			/** this finds the panel to whom the submission was sent */

			List<String> panelMembers = getOtherSubmissionsFromDb("cmr_panel_members", pe_id, otherSubCase.get(i),userInputData);

			/** this finds the date the item was submitted */
			List<String> refDate = getOtherSubmissionsFromDb("cmr_ref_date",pe_id, otherSubCase.get(i),userInputData);

			HashSet<String> hset = new HashSet<String>(refCategories);

			if (hset.size() > 1) {
				cs_caseid = otherSubCase.get(i);

				db_cyv_category = refCategories;
				db_cmr_panel_members = panelMembers;
				db_cmr_ref_date = refDate;

				break;

			}

		}
		int cat = getRandomInt(db_cyv_category.size());

		String randomCategory = db_cyv_category.get(cat);

		for (int i = 1; i <= db_cyv_category.size(); i++) {
			int a = db_cyv_category.indexOf(randomCategory);

			if (db_cyv_category.contains(randomCategory)) {

				db_cyv_category.remove(randomCategory);
				db_cmr_panel_members.remove(a);
				db_cmr_ref_date.remove(a);

			}
		}
		String caseNum = findCaseWithOtherSubmissions(userInputData,cs_caseid);

		Utility.scrollDownIfNotDisplayed(Actions.containsElement(randomCategory));

		Utility.scrollDownIfNotDisplayed(Actions.containsElement(caseNum));

		getOtherSubmissionsFromUI(db_cyv_category.size(), db_cyv_category, db_cmr_panel_members, db_cmr_ref_date);
	}

	public String findCaseWithOtherSubmissions(List<UserInputData> userInputData,String cs_caseid) {
		return getAllColumns( replace(Queries.CASE_NUMBER, "CS_CASEID", cs_caseid),userInputData);

	}

	public List<String> getOtherSubmissionsFromDb(String fieldName, String peId, String caseN,List<UserInputData> userInputData) {

		return executeQuery(
				replace(Queries.CYV_CATEGORY, "field", fieldName, "cmr_ju_pe_id", peId, "CMR_CS_CASEID", caseN), userInputData);
	}

	public void getOtherSubmissionsFromUI(int numOfSub, List<String> db_cyv_category, List<String> db_cmr_panel_members,
			List<String> db_cmr_ref_date) {

		List<String> ui_cmr_ref_date = new ArrayList<>();
		List<String> ui_cyv_category = new ArrayList<>();
		List<String> ui_cmr_panel_members = new ArrayList<>();

		for (int i = 1; i <= numOfSub; i++) {

			ui_cmr_ref_date.add(getNumberOfRows(i, 1));
			ui_cyv_category.add(getNumberOfRows(i, 2));
			ui_cmr_panel_members.add(getNumberOfRows(i, 3));

		}

		assertTrue(db_cyv_category.equals(ui_cyv_category));
		assertTrue(db_cmr_panel_members.equals(ui_cmr_panel_members));
		assertTrue(db_cmr_ref_date.equals(ui_cmr_panel_members));

	}

	public String getNumberOfRows(int row, int col) {
		return findElement(By.xpath(
				"//XCUIElementTypeStaticText[@name='Other Submissions in Case']/following::XCUIElementTypeOther[" + row
						+ "]/XCUIElementTypeOther[2]/XCUIElementTypeOther[" + col + "]/XCUIElementTypeStaticText"))
				.getText().trim();

	}

}
