package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.execute;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ACTION_NAME;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.JUDGES_INITIALS;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.JUDGE_VOTE_DPF_RELIEF;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.MBR_NOTE;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.getGroupIcons;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.selectAction;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElementBy;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.getText;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.replace;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.sendKeys;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.waitForVisibilityOfElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.changeDateFormat;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.clickOnNumberInRange;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.getParameter;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.getStreamOfRandomInts;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.scrollDownIfNotDisplayed;
import static java.util.Collections.sort;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities;
import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.GroupIcons;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.Panel;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class JudgeVoteDPFPage extends AppiumPageFactory {

	String select = "Please Select";

	@iOSXCUITFindBy(accessibility = "Close")
	public static WebElement close;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='NoteList']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeTextView")
	public static WebElement commentField;

	@iOSXCUITFindBy(id = "Apply")
	public static WebElement applyBtn;

	@iOSXCUITFindBy(id = "Select All")
	public static WebElement selectAll;

	@iOSXCUITFindBy(id = "Cut")
	public static WebElement cut;

	@iOSXCUITFindBy(id = "Back")
	public static WebElement back;

	@iOSXCUITFindBy(id = "Cancel")
	public static WebElement cancel;

	@iOSXCUITFindBy(id = "Submit")
	public static WebElement submit;

	// @WithTimeout(time = 60, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(id = "Yes")
	public static WebElement yesBtn;

	// @WithTimeout(time = 100, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(id = "OK")
	public static WebElement okBtn;

	// @WithTimeout(time = 100, unit = TimeUnit.SECONDS)
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Dashboard'])[1]")
	public static WebElement dashboard;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='VoteOptions']/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText")
	public static List<WebElement> judgeVotes;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeButton")
	public static List<WebElement> doc;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Configuration Error']")
	public static WebElement configError;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Dismiss']")
	public static WebElement dismiss;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='PDF View']")
	public static WebElement pdf;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='Page Label']")
	public static WebElement pageLabel;

	@iOSXCUITFindBy(xpath = "//*[contains(@name, 'The requested document cannot be displayed at this time. Invalid Document: dls')]")
	public static List<WebElement> jpgMessage;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeAlert[@name='Briefcase']")
	public static WebElement jpgAlert;

	@iOSXCUITFindBy(accessibility = "DocumentList")
	public static WebElement documentList;

	@iOSXCUITFindBy(accessibility = "PDF View")
	public static WebElement pdfView;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeToolbar[@name='Toolbar'])[1]/following::XCUIElementTypeOther[1]/XCUIElementTypeButton")
	public static List<WebElement> toolBar1;

	@iOSXCUITFindBy(accessibility = "Annotations")
	public static WebElement annotations;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeActivityIndicator[@name='Sending...' or @name='In progress']")
	public static List<WebElement> progress;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Case Information']")
	public static WebElement caseInfo;

	public static String cyv_category = "";
	public static String caseid = "";
	public static String cyv_code = "";
	public static String dm_description = "";
	public static String dm_file_name = "";
	public static String rl_list_text = "";
	public static String cvv_display = "";
	public static String chv_date_created = "";

	/** verify relief is displayed on the popup page */
	public String selectViewVotes(String category, List<UserInputData> userInputData, String caseNum) {

		String ccr_id = CommonPages.getCCRID(caseNum, category, userInputData);
		String dbRelief = getRelief(ccr_id, userInputData);
		String relief = "";
		if (dbRelief.equals(null)) {
			relief = "-";
		} else {
			relief = dbRelief;
		}
		tap(Locator.XPATH, "(//XCUIElementTypeStaticText[@name='" + relief.trim()
				+ "']/following::XCUIElementTypeOther/XCUIElementTypeButton[@name='View Votes'])[1]");
		return relief.trim();

	}

	/** verify each judge's vote and the day they voted on the popup page */
	public void verifyJudgesVote(String category, List<UserInputData> userInputData, String caseNum) {
		performPageLoad(driver);
		getJudgesInitials(category, JUDGES_INITIALS, userInputData, caseNum);

	}

	public static void getJudgesInitials(String category, String initials, List<UserInputData> userInputData,
			String caseNum) {

		String ccr_id = CommonPages.getCCRID(caseNum, category, userInputData);

		List<String> dbInitials = execute(getID(initials, ccr_id), 2, userInputData);
		
		List<String> voteType = execute(getID(initials, ccr_id), 4, userInputData);

		
		List<String> voteDate = execute(getID(initials, ccr_id), 5, userInputData);

		
		  

		/** get judge's initials */

		/** verify all initials are displayed */
        List<String> resultList = new ArrayList<>();
		for (int i = 0; i < dbInitials.size(); i++) {
			
           assertTrue("Verify correct judges' intials are listed in the View Votes popup: "+dbInitials+ "",scrollDownIfNotDisplayed(Actions.containsElement(dbInitials.get(i).trim())));

            
            String currentVote = voteType.get(i);
            String currentVoteDate = voteDate.get(i);
            
            if (currentVote != null && currentVoteDate != null) {
                String xpath = String.format(
                        "//XCUIElementTypeOther[@name='JudgesVotesList']/child::*//*[contains(@name, '%s')]" +
                        "/following::XCUIElementTypeOther/XCUIElementTypeStaticText[contains(@name, '%s')]" +
                        "/following::XCUIElementTypeOther/XCUIElementTypeStaticText[contains(@name, '%s')]",
                        dbInitials.get(i).trim(), currentVote.trim(), changeDateFormat(currentVoteDate.substring(0, 10),"yyyy-MM-dd",
    							"M/d/yyyy"));
                
                resultList.add(xpath);
            }
        }

        for (String result : resultList) {
        	 assertTrue( "Judges’ votes are missing from the View Votes popup",scrollDownIfNotDisplayed(result));
        }
  
    	tap(close);
    	
}
	
	
	public String getVoteSelection(String actioName,String category, String dpfName, List<UserInputData> userInputData, String caseNum) {

		String elId = getAllColumns(getID(Queries.EL_ID, actioName), userInputData);

		String ccr_id = CommonPages.getCCRID(caseNum, category, userInputData);

		String voteText = "";
		String text = "";
		String reliefText = getRelief(ccr_id, userInputData);

		if (isDisplayed(Locator.XPATH, getIndexOfVoteButton(reliefText, 1)) == true) {
			tap(Locator.XPATH, getIndexOfVoteButton(reliefText, 1));
		} else {
			tap(back);
			CommonPages.getActionName(getAllColumns(getID(ACTION_NAME, elId), userInputData));
			tap(Locator.XPATH, getIndexOfVoteButton(reliefText, 1));
		}
		Page.sleep(4000);
		List<WebElement> votes = judgeVotes;

		String voteList = "";
		Iterator<WebElement> i = votes.iterator();
		while (i.hasNext()) {
			WebElement row = i.next();
			voteList += row.getText();
		}

		if (voteList.contains(select)) {
			try {
				votes.remove(select);

			} catch (NoSuchElementException e) {
				e.getMessage();
			}

			voteText += clickOnNumberInRange(votes);
			text += getVoteName(voteText, reliefText);

		} else {
			voteText += clickOnNumberInRange(votes);
			text += getVoteName(voteText, reliefText);
		}

		tap(Locator.XPATH, getIndexOfNoteIcon(reliefText));

		addVote(dpfName, getID(MBR_NOTE, elId), reliefText, elId, userInputData);

		tap(back);
		Utility.scroll(documentList, "up");

		return text;
	}

	public String getVoteName(String voteText, String reliefText) {
		if (voteText.equals("No Change")) {
			return getText(Locator.XPATH, getIndexOfVoteButton(reliefText, 1));
		} else {
			return voteText;
		}
	}

	/**
	 * After adding vote to a note, this will verify judge's vote is updated in Vote
	 * Information Panel
	 */
	public void verifyNoteText(String category,  List<UserInputData> userInputData,
			String caseNum) {
		String ccr_id = CommonPages.getCCRID(caseNum, category, userInputData);

		CommonPages.getPanel(Panel.valueOf("Vote_Information"));
		String relief = getRelief(ccr_id, userInputData);
		getVote(relief).click();
		performPageLoad(driver);
		assertTrue(isDisplayed(Locator.XPATH, containsElement("$$")));
		String title = getAllColumns(Queries.DM_DESCRIPTION, userInputData);
		assertTrue(isDisplayed(Locator.XPATH, containsElement(title)));
		
		tap(close);

	}

	public static void ifDocumentAccessbile(String docName) {

		if (docName.equalsIgnoreCase("pdf")) {
			performPageLoad(driver);

			if (Actions.isDisplayed(configError) == true) {
				dismiss.click();
			}
			assertTrue("Verify doc popup is accessible from judgeVote note and document description is correct",
					isDisplayed(pdf));
			assertTrue(isDisplayed(pageLabel));

		} else if (docName.equalsIgnoreCase("jpg")) {

			assertTrue(driver.getPageSource()
					.contains("The requested document cannot be displayed at this time. Invalid Document: dls"));

		}
		Page.waitToBeClickable(close, driver);
	}

	public void addVote(String dpfName, String query, String relief, String el_id, List<UserInputData> userInputData) {
		String text = "";
		if (getParameter(getAllColumns(query, userInputData), dpfName, 4).equals("SKIP")) {
			assertNull(" THE \"NOTE HISTORY PARAMETER\" IS NOT SET TO \"SKIP\" ", commentField.getText());
			tap(cancel);
		} else {
			Page.sleep(3000);

			commentField.click();
			commentField.clear();

			text = sendANote();
			tap(applyBtn);

			scrollDownIfNotDisplayed("//XCUIElementTypeButton[@name='Submit']");
             CommonPages.ifDownloaded(progress);
            Page.waitForVisibilityOfElement(caseInfo, driver);
			getGroupIcons(GroupIcons.Expand);
			selectAction("Actions", el_id, userInputData);
			performPageLoad(driver);
			tap(Locator.XPATH, getIndexOfNoteIcon(relief));
			Page.sleep(2000);
			assertEquals(
					" THE \"NOTE HISTORY PARAMETER\" IS SET TO \"Y\", HOWEVER THE TEXT OF THE PREVIOUS VOTE NOTE IS NOT DISPLYED CORRECTLY! ",
					text, getText(Page.waitForVisibilityOfElement(commentField, driver)));
			tap(cancel);

		}

	}

	public static WebElement getVote(String relief) {

		return findElement(By.xpath("(//XCUIElementTypeStaticText[@name='" + relief
				+ "']/following::XCUIElementTypeOther[contains(@name, 'NoteIcon')])[1]"));

	}

	public static String sendANote() {
		String note = "$$";
		sendKeys(commentField, note);
		return note;

	}

	public static String getTodaysDate() {
		return "TEST-" + changeDateFormat(getStreamOfRandomInts().split(" ")[0], "yyyy/MM/dd", "MM/dd/yyyy");
	}

	public static String getRelief(String ccr_id, List<UserInputData> userInputData) {
		return execute(getID(JUDGES_INITIALS, ccr_id), 3, userInputData).get(0);//getAllColumns(getID(JUDGE_VOTE_DPF_RELIEF, ccr_id), userInputData);

	}

	public static String getIndexOfVoteButton(String relief, int index) {
		return "(//XCUIElementTypeStaticText[@name='" + relief
				+ "']/following::XCUIElementTypeOther/XCUIElementTypeButton)[" + index + "]";

	}

	public static String getIndexOfNoteIcon(String relief) {
		return "(//XCUIElementTypeStaticText[@name='" + relief
				+ "']/following::XCUIElementTypeOther/XCUIElementTypeButton[@name='View Votes'][1]"
				+ "/following::XCUIElementTypeOther//XCUIElementTypeStaticText)[1]";

	}

	public void verifyCourtAdminAccess() {
		getGroupIcons(GroupIcons.Expand);
		assertFalse(
				"SITE TABLE VARIABLE \"BRIEFCASECTADMINDKT\" IS SET TO 'N', HOWEVER COURT ADMINS CAN SEE ACTIONS IN BRIEFCASE",
				isDisplayed(Locator.XPATH, containsElement("Actions")));
	}

	public List<String> getReferralWithDoc(List<UserInputData> userInputData) {

		String crj_ju_pe_id = DocumentPage.get_pe_id("jud", userInputData);

		String voteDoc = replace(Queries.JUDGE_VOTE_NOTE_DOC, "CRJ_JU_PE_ID", crj_ju_pe_id);

		List<String> caseWithDoc = DBUtilities.executeQuery(voteDoc, userInputData);

		int random = Utility.getRandomNumberInRange(1, caseWithDoc.size());

		List<String> value = new ArrayList<>();

		for (int j = 2; j < 10; j++) {

			value.add(execute(voteDoc, j, userInputData).get(random).trim());
		}

		cyv_category = value.get(0);

		caseid = DBUtilities.getAllColumns(replace(Queries.CASE_NUMBER, "CS_CASEID", value.get(1)), userInputData);
		cyv_code = value.get(2);
		dm_description = value.get(3);
		dm_file_name = value.get(4);
		rl_list_text = value.get(5);
		cvv_display = value.get(6);
		chv_date_created = value.get(7);

		return value;

	}

	public void verifyDocumentIsDisplayed() {
		Page.performPageLoad(driver);
		String el = null;

		getVote(rl_list_text).click();

		if (dm_description.length() > 0) {
			el = dm_description;

		} else {
			if (dm_file_name.length() > 0) {
				el = dm_description;

			} else {
				el = "Document";

			}
		}
		assertTrue(isDisplayed(Locator.XPATH, containsElement(el)));
		Actions.contains(el).click();
		CommonPages.ifDownloaded(progress);
		ifDocumentAccessbile(el);

	}

}