package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.execute;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.JUDGES_INITIALS;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.MBR_NOTE;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.getGroupIcons;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.selectAction;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.findElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.getText;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.replace;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.sendKeys;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.tap;
import static gov.uscourts.ao.mobileBriefcase.page.common.Page.performPageLoad;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.changeDateFormat;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.clickOnNumberInRange;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.getParameter;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.getStreamOfRandomInts;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.scrollDownIfNotDisplayed;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
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
import gov.uscourts.ao.mobileBriefcase.page.common.Utility.Direction;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class JudgeVoteDPFPage extends AppiumPageFactory {


	@iOSXCUITFindBy(accessibility = "Close")
	public static WebElement close;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Comment']/following:: XCUIElementTypeOther[1]/XCUIElementTypeTextView")
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
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"MasterNavPage\"]/XCUIElementTypeOther[1]/XCUIElementTypeTable/XCUIElementTypeCell[2]")
	public static WebElement dashboard;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"VoteOptions\"]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText")
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
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"MasterNavPage\"]/XCUIElementTypeOther[1]/XCUIElementTypeTable/XCUIElementTypeCell[1]")
	public static WebElement collapseBtn;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Apply ruling to all reliefs']")
	public static List<WebElement> applyRulling;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Apply ruling to all reliefs']/preceding:: XCUIElementTypeSwitch[1]")
	public static WebElement toggle;
	
	public static String cyv_category = "";
	public static String caseid = "";
	public static String cyv_code = "";
	public static String dm_description = "";
	public static String dm_file_name = "";
	public static String rl_list_text = "";
	public static String cvv_display = "";
	public static String chv_date_created = "";
	public static String foundCcrId = "";
	public static String relief = "";

	/** verify relief is displayed on the popup page */
	public String selectViewVotes(String caseId,String cmr_cyv_code,List<UserInputData> userInputData) {
		String relief = "";
		String foundCcrId = "";

		List<String> ccrIdList = CommonPages.findCCRID(userInputData, caseId, cmr_cyv_code);

		if (ccrIdList == null) {
			throw new IllegalArgumentException("Error: CCR ID list is null.");
		} else {
			for (int i = 0; i < ccrIdList.size(); i++) {
				String ccrId = ccrIdList.get(i);
				String dbRelief = getRelief(ccrId, userInputData);

				if (dbRelief == null) {
					relief = "-";
				} else {
					relief = dbRelief;
				}

				boolean isTapped = tapIfExists(Locator.XPATH, "(//XCUIElementTypeStaticText[@name='" + relief.trim()
						+ "']/following::XCUIElementTypeOther/XCUIElementTypeButton[@name='View Votes'])[1]");

				if (isTapped) {
					foundCcrId = ccrId;
					break;
				}
			}
		}

		if (foundCcrId.isEmpty()) {
			System.out.println("No CCR ID found, failed to tap on 'View Votes' button.");
		}

		return foundCcrId;
	}

	private boolean tapIfExists(Locator locator, String xpath) {
		try {
			List<WebElement> elements = driver.findElements(By.xpath(xpath));
			if (!elements.isEmpty()) {
				elements.get(0).click();
				return true;
			} else {
				return false;
			}
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	/** verify each judge's vote and the day they voted on the popup page */
	public void verifyJudgesVote(String ccr_id, List<UserInputData> userInputData) {
		performPageLoad(driver);
		getJudgesInitials(ccr_id, JUDGES_INITIALS, userInputData);

	}

	public static void getJudgesInitials(String ccr_id, String initials, List<UserInputData> userInputData) {
	    List<String> dbInitials = execute(getID(initials, ccr_id), 2, userInputData);
	    List<String> voteType = execute(getID(initials, ccr_id), 4, userInputData);
	    List<String> voteDate = execute(getID(initials, ccr_id), 5, userInputData);

	    Map<String, String> latestVoteXPaths = new HashMap<>();
	    Map<String, LocalDate> latestVoteDates = new HashMap<>();

	    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");

	    for (int i = 0; i < dbInitials.size(); i++) {
	        String judge = dbInitials.get(i).trim();
	        String currentVote = voteType.get(i);
	        String currentVoteDateStr = voteDate.get(i);

	        if (currentVote != null && currentVoteDateStr != null) {
	            LocalDate currentDate = LocalDate.parse(currentVoteDateStr.substring(0, 10), inputFormatter);
	            String formattedDate = currentDate.format(outputFormatter);

	            String xpath = String.format(
	                "//XCUIElementTypeOther[@name='JudgesVotesList']/child::*//*[contains(@name, '%s')]"
	                        + "/following::XCUIElementTypeStaticText[contains(@name, '%s')]"
	                        + "/following::XCUIElementTypeStaticText[contains(@name, '%s')]",
	                judge, currentVote.trim(), formattedDate);

	            // Store only the latest vote per judge
	            if (!latestVoteDates.containsKey(judge) || currentDate.isAfter(latestVoteDates.get(judge))) {
	                latestVoteDates.put(judge, currentDate);
	                latestVoteXPaths.put(judge, xpath);
	            }
	        }
	    }

	    // Iterate through unique judges only
	    for (String judge : latestVoteXPaths.keySet()) {
	        assertTrue("Verify judge initials: " + judge,
	            scrollDownIfNotDisplayed(Actions.containsElement(judge)));

	        for (int j = 0; j < 8; j++) {
	            Utility.scrollPage("down");
	        }
	    }

	    for (String xpath : latestVoteXPaths.values()) {
	        assertTrue("Latest judge vote not displayed in View Votes popup", scrollDownIfNotDisplayed(xpath));
	    }

	   // tap(close);
	     tap(cancel);
	}

	public String getVoteSelection(String caseId,String cmr_cyv_code,String actionName, String category, String dpfName,
			List<UserInputData> userInputData) {

		String elementId = getAllColumns(getID(Queries.EL_ID, actionName), userInputData);
		String voteText = "";
		String resultText = "";

		List<String> ccrIdList = CommonPages.findCCRID(userInputData, caseId, cmr_cyv_code);
		if (ccrIdList == null) {
			throw new IllegalArgumentException("Error: CCR ID list is null.");
		}

		for (String ccrId : ccrIdList) {
			relief = getRelief(ccrId, userInputData);
			if (relief == null) {
				relief = "-";
			} else {
				relief = relief.trim();
			}

			if (tapIfExists(Locator.XPATH, getIndexOfVoteButton(relief, 1))) {
				foundCcrId = ccrId;
				break;
			}
		}

		if (foundCcrId == null) {
			throw new IllegalArgumentException("Error: No CCR ID found.");
		}

		Page.sleep(4000);

		List<WebElement> votes = judgeVotes;
		StringBuilder voteListBuilder = new StringBuilder();
		for (WebElement row : votes) {
			voteListBuilder.append(row.getText());
		}

		String voteList = voteListBuilder.toString();
		List<String> itemsToRemove = Arrays.asList("Please Select", "No Change");
		for (String item : itemsToRemove) {
		    if (voteList.contains(item)) {
		        try {
		            votes.remove(item);
		        } catch (NoSuchElementException e) {
		            System.err.println("Element not found: " + e.getMessage());
		        }
		    }
		}

		voteText = clickOnNumberInRange(votes);
		resultText = getVoteName(voteText, relief.trim());

		tap(Locator.XPATH, getIndexOfNoteIcon(relief.trim()));
		addVote(dpfName, getID(MBR_NOTE, elementId), relief.trim(), elementId.trim(), userInputData);
		tap(back);

		return resultText;
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
	public void verifyNoteText(String relief, String ccrId, String category, List<UserInputData> userInputData,
			String caseNum) {
		
		//Utility.tapAndSwipe(Direction.UP);
		// Open the Vote Information panel
		CommonPages.getPanel(Panel.valueOf("Vote_Information"));

		// Click on the NoteIcon
		getVote(relief.trim()).click();
		performPageLoad(driver);

		// Verify that the note text is displayed
		assertTrue(isDisplayed(Locator.XPATH, containsElement("$$")));

		// Retrieve the description from the database
		String description = getAllColumns(Queries.DM_DESCRIPTION, userInputData);

		// Verify that the description is displayed
		assertTrue(isDisplayed(Locator.XPATH, containsElement(description)));

		// Close the panel
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

	public void addVote(String dpfName, String query, String relief, String elementId,
			List<UserInputData> userInputData) {
		String noteText = "";

		// Check if the parameter is set to "SKIP"
		if ("SKIP".equals(getParameter(getAllColumns(query, userInputData), dpfName, 4))) {
			
			assertTrue("THE \"NOTE HISTORY PARAMETER\" IS NOT SET TO \"SKIP\"", 
				    commentField.getText() == null || commentField.getText().isEmpty());

			tap(cancel);
		} else {
			Page.sleep(3000);

			// Clear and enter a new comment
			commentField.click();
			commentField.clear();
			noteText = sendANote();
			tap(applyBtn);

			// Scroll down if the Submit button is not displayed
			scrollDownIfNotDisplayed("//XCUIElementTypeButton[@name='Submit']");
			CommonPages.ifDownloaded(progress);
			Page.sleep(2000);
			int maxScrollAttempts = 4;
			boolean isCaseInfoVisible = false;

			for (int attempt = 0; attempt < maxScrollAttempts; attempt++) {
			    Utility.scrollPage("down");
			    
			    try {
			        Page.waitForVisibilityOfElement(caseInfo, driver);
			        isCaseInfoVisible = true;
			        break; 
			    } catch (TimeoutException e) {
			        
			    }
			}

			if (!isCaseInfoVisible) {
			    throw new AssertionError("caseInfo is not visible after scrolling down 4 times.");
			}

			selectAction("Actions", elementId, userInputData);
			performPageLoad(driver);

			// Tap the note icon and verify the note text
			tap(Locator.XPATH, getIndexOfNoteIcon(relief));
			Page.sleep(2000);
			assertEquals(
					"THE \"NOTE HISTORY PARAMETER\" IS SET TO \"Y\", HOWEVER THE TEXT OF THE PREVIOUS VOTE NOTE IS NOT DISPLAYED CORRECTLY!",
					noteText, getText(Page.waitForVisibilityOfElement(commentField, driver)));

			tap(cancel);
		}
	}

	public static WebElement getVote(String relief) {

		return findElement(By.xpath("//XCUIElementTypeStaticText[@name='"+relief+"']/following::XCUIElementTypeButton[@name='NoteIcon'][1]"));

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
		return execute(getID(JUDGES_INITIALS, ccr_id), 3, userInputData).get(0);

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
		collapseBtn.click();
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
	
	public String addVote(String caseId,String cmr_cyv_code,
			List<UserInputData> userInputData) {
		
		if (applyRulling.size() == 1) {
			if (Utility.getToggleState(toggle) == false) {
				toggle.click();
			}
		}
		String voteText = "";
		String resultText = "";

		List<String> ccrIdList = CommonPages.findCCRID(userInputData, caseId, cmr_cyv_code);
		if (ccrIdList == null) {
			throw new IllegalArgumentException("Error: CCR ID list is null.");
		}

		for (String ccrId : ccrIdList) {
			relief = getRelief(ccrId, userInputData);
			if (relief == null) {
				relief = "-";
			} else {
				relief = relief.trim();
			}

			if (tapIfExists(Locator.XPATH, getIndexOfVoteButton(relief, 1))) {
				foundCcrId = ccrId;
				break;
			}
		}

		if (foundCcrId == null) {
			throw new IllegalArgumentException("Error: No CCR ID found.");
		}

		Page.sleep(4000);

		List<WebElement> votes = judgeVotes;
		StringBuilder voteListBuilder = new StringBuilder();
		for (WebElement row : votes) {
			voteListBuilder.append(row.getText());
		}

		String voteList = voteListBuilder.toString();
		List<String> itemsToRemove = Arrays.asList("Please Select", "No Change");
		for (String item : itemsToRemove) {
		    if (voteList.contains(item)) {
		        try {
		            votes.remove(item);
		        } catch (NoSuchElementException e) {
		            System.err.println("Element not found: " + e.getMessage());
		        }
		    }
		}

		voteText = clickOnNumberInRange(votes);
		resultText = getVoteName(voteText, relief.trim());


		scrollDownIfNotDisplayed("//XCUIElementTypeButton[@name='Submit']");
		
		performPageLoad(driver);
		collapseBtn.click();
		dashboard.click();
		return resultText;
	}


}