package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.MBR_NOTE;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.common.Actions.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.getParameter;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import gov.uscourts.ao.mobileBriefcase.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.common.AppiumPageFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;

public class UIDocketingDPFPage extends AppiumPageFactory {

	// @WithTimeout(time = 10, unit = TimeUnit.SECONDS)
	@iOSFindBy(xpath = "//XCUIElementTypeTextView[1]")
	public static MobileElement descriptionField;

	public void verifyFieldsAreDisplayed(String descriptionText, String commentText, String submitText, DBType dbType,
			String dpfName, String el_id) {

		verifyElementIsDisplayed(descriptionText);
		getDefaulDescription(dbType, dpfName, el_id);
		verifyElementIsDisplayed(commentText);
		verifyElementIsDisplayed(submitText);
	}

	/**
	 * Verify an editable description field displays. The default description is
	 * defined in the Default description parameter of the note DPF. It will always
	 * be in the 5th position in the note DPF. If the value is 'SKIP', the note
	 * description should default to 'Transaction Note'
	 */
	public void getDefaulDescription(DBType dbType, String dpfName, String el_id) {
		try {
			if (getParameter(getAllColumns(dbType, getID(MBR_NOTE, el_id)), dpfName, 4).equals("SKIP")) {
				assertTrue(descriptionField.getText().equals("Transaction Note"));

			} else {
				String dbParam = replaceWithEmptyString(
						getParameter(getAllColumns(dbType, getID(MBR_NOTE, el_id)), dpfName, 4), "\\");
				String uiParam = replaceWithEmptyString(descriptionField.getText(), "'");

				assertEquals("NOTE DESCRIPTION MISMATCH", dbParam.replace("'", ""), uiParam);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyElementIsDisplayed(String text) {
		assertTrue(isDisplayed(Locator.XPATH, containsElement(text)));

	}

	public static String replaceWithEmptyString(String text, String charac) {
		if (text.contains(charac))
			;
		return text.replace(charac, "").trim();

	}



}
