package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.execute;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DUPLICATED_LWK;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ToolsPage extends AppiumPageFactory {

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Tools']")
	public static List<WebElement> tools;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Tools']/following::XCUIElementTypeStaticText[@name='']")
	public static List<WebElement> redBullet;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"nav\"]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]")
	public static WebElement leftNav;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"Categories\"]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]")
	public static WebElement dash;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Apply All']")
	public static WebElement applyAll;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Apply All']/preceding:: XCUIElementTypeButton[2]")
	public static WebElement exsitingClerkBtn;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypePickerWheel")
	public static WebElement dropDown;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Done']")
	public static WebElement done;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Dashboard']")
	public static WebElement dashboard;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Submit']")
	public static WebElement submit;

	public void getToolsCategory() {
	    scrollUp();

	    int toolSize = tools.size();
	    int redBulletCount = redBullet.size();

	    if (CommonPages.siVal.equalsIgnoreCase("n")) {
	        assertTrue("The left nav displays the Tools category, even when briefcaseDisplayTools is set to 'n'.", toolSize == 0);
	    } else {
	        assertTrue("The left nav doesn't display the Tools category, even when briefcaseDisplayTools is set to 'y'.", toolSize >= 1);
	        assertTrue("The Tools icon displays a red badge with a count in it.", redBulletCount == 0);
	    }
	}

	public void applyWithoutExistingClerk() {
	    tools.get(0).click();
	    
	    if (Actions.isDisplayed(applyAll)) {
	        applyAll.click();

	        boolean progressBar = driver.getPageSource().contains("Please select an existing clerk");
	        assertTrue("Tapping 'Apply All' without existing clerk is not generating a message.", progressBar);
	    }
	}

	public void scrollUp() {
		Utility.scroll(dash, "up");
		Utility.scroll(leftNav, "up");
	}

	public void isSortedInDescending(List<UserInputData> userInputData) {
	    // Retrieve first and last names
	    List<String> firstNames = getListOfLwks(2, userInputData);
	    List<String> lastNames = getListOfLwks(3, userInputData);

	    // Get a random index within the bounds of the firstNames list
	    int index = (firstNames.size() > 1) ? Utility.getRandomNumberInRange(0, firstNames.size() - 1) : 0;

	    // Select an existing clerk by combining first and last names
	    selectExistingClerk(firstNames.get(index).trim() + " " + lastNames.get(index).trim());

	    // Create an instance of ReferralSortOrderPage
	    ReferralSortOrderPage sortOrderPage = new ReferralSortOrderPage();

	    // Retrieve the list of assignments sorted by case number in descending order
	    List<String> assignments = sortOrderPage.assignmentsSortedByCase();
	    Collections.reverse(assignments);
	    // Check if the assignments list is already sorted in descending order
	    boolean isSortedDesc = Utility.checkIfSorted(assignments);

	    // Assert that the list is sorted in descending order
	    assertTrue("REFERRALS ARE NOT SORTED BY CASE NUMBER IN DESCENDING ORDER ------> " + assignments, isSortedDesc);
	}

	public List<String> getListOfLwks(int columnIndex, List<UserInputData> userInputData) {
	    String peId = DocumentPage.get_pe_id("jud", userInputData);
	    return execute(getID(DUPLICATED_LWK, peId), columnIndex, userInputData);
	}


	public void scrollThroughTheList(String existingClerk) {
	    // Pause to ensure the dropdown is fully loaded
	    Page.sleep(1000);
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    Map<String, Object> params = new HashMap<>();
	    params.put("order", "next");
	    params.put("offset", 0.1);
	    params.put("element", ((RemoteWebElement) dropDown).getId());

	    if (existingClerk.isEmpty()) {
	        // Scroll through the list without looking for a specific clerk
	        js.executeScript("mobile: selectPickerWheelValue", params);
	    } else {
	        boolean elementNotFound = true;
	        while (elementNotFound) {
	            js.executeScript("mobile: selectPickerWheelValue", params);
	            // Check if the current dropdown text matches the existingClerk
	            if (dropDown.getText().trim().equals(existingClerk)) {
	                elementNotFound = false;
	            }
	        }
	    }
	    // Click the done button after finding the desired clerk or finishing the scroll
	    done.click();
	}


	public void selectExistingClerk(String existingClerk) {
	    // Click on the dashboard button
	    dashboard.click();

	    // Scroll up to refresh the view or access the tools
	    scrollUp();

	    // Get the size of the tools list
	    int toolSize = tools.size();

	    // Check if tools should be displayed based on siVal
	    if (CommonPages.siVal.equalsIgnoreCase("n")) {
	        // Verify that no tools are displayed if siVal is 'n'
	        assertTrue("The left nav displays the Tools category, even when briefcaseDisplayTools is set to 'n'.", toolSize == 0);
	    } else {
	        // If tools should be displayed, click on the first tool
	        tools.get(0).click();
	        
	        // Click on the existing clerk button
	        exsitingClerkBtn.click();
	        
	        // Scroll through the list to find the specified clerk
	        scrollThroughTheList(existingClerk);
	    }
	}



	public void duplicateAssignments(List<UserInputData> userInputData) {
	    try {
	        // Retrieve data from user input
	        List<String> firstNames = getListOfLwks(2, userInputData);
	        List<String> lastNames = getListOfLwks(3, userInputData);
	        List<String> cavDisplay = getListOfLwks(4, userInputData);
	        List<String> cyvDisplay = getListOfLwks(5, userInputData);
	        List<String> caseNumbers = getListOfLwks(6, userInputData);

	        // Combine relevant data into a single list for duplicate checking
	        List<String> combinedList = new ArrayList<>();
	        for (int i = 0; i < firstNames.size(); i++) {
	            combinedList.add(cavDisplay.get(i).trim() + ", " + cyvDisplay.get(i).trim() + ", " + caseNumbers.get(i).trim());
	        }

	        // Find duplicates in the combined list
	        List<String> duplicates = findDuplicates(combinedList);

	        if (!duplicates.isEmpty()) {
	            // Select a random duplicate entry
	            int randomIndex = Utility.getRandomNumberInRange(0, duplicates.size() - 1);
	            String[] duplicateIndices = duplicates.get(randomIndex).split(" ");

	            int index1 = Integer.parseInt(duplicateIndices[0]);
	            int index2 = Integer.parseInt(duplicateIndices[1]);

	            String clerkName1 = firstNames.get(index1).trim() + " " + lastNames.get(index1).trim();
	            String clerkName2 = firstNames.get(index2).trim() + " " + lastNames.get(index2).trim();

	            selectExistingClerk(clerkName1);
	            tapIndividualClerk(caseNumbers.get(index1).trim(), cyvDisplay.get(index1).trim(), cavDisplay.get(index1).trim());
	            scrollThroughTheList(clerkName2);

	            // Submit and verify duplication message
	            submit.click();
	            boolean duplicatedAssignmentMsg = driver.getPageSource().contains("Duplicated Assignment found for " + clerkName2);
	            assertTrue("Verify that user is prevented from creating duplicate assignments: " + clerkName1 + " : " + clerkName2, duplicatedAssignmentMsg);
	        } else {
	            throw new RuntimeException("Ensure there are law clerk assignments of a specific type in a specific referral, but assigned to two different law clerks. Law clerk list: " + firstNames + " " + lastNames);
	        }
	    } catch (RuntimeException e) {
	        System.err.println(e.getMessage());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


	public List<String> findDuplicates(List<String> list) {
	    Map<String, List<Integer>> indexMap = new HashMap<>();
	    List<String> duplicates = new ArrayList<>();

	    // Populate the map with list entries and their indices
	    for (int i = 0; i < list.size(); i++) {
	        String item = list.get(i);
	        if (!indexMap.containsKey(item)) {
	            indexMap.put(item, new ArrayList<>());
	        }
	        indexMap.get(item).add(i);
	    }

	    // Find duplicates by checking the map
	    for (Map.Entry<String, List<Integer>> entry : indexMap.entrySet()) {
	        List<Integer> indices = entry.getValue();
	        if (indices.size() > 1) {
	            for (int i = 0; i < indices.size(); i++) {
	                for (int j = i + 1; j < indices.size(); j++) {
	                    duplicates.add(indices.get(i) + " " + indices.get(j));
	                }
	            }
	        }
	    }

	    return duplicates;
	}


	public void tapIndividualClerk(String caseNum, String cyv_display, String cav_display) {

		Utility.scrollDownIfNotDisplayed("//XCUIElementTypeStaticText[contains(@name, '" + caseNum + "')]/preceding::"
				+ "XCUIElementTypeStaticText[contains(@name, '" + cyv_display + "')]/preceding::"
				+ "XCUIElementTypeStaticText[contains(@name, '" + cav_display
				+ "')]/preceding::XCUIElementTypeButton[1]");

	}

}
