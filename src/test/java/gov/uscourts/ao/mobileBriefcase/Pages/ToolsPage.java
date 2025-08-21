package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.execute;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DUPLICATED_LWK;
import static gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.ifDownloaded;
import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.containsElement;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.scrollDownIfNotDisplayed;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import gov.uscourts.ao.mobileBriefcase.Pages.CaseQueryPage.Search;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.GroupIcons;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.page.common.Page;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ToolsPage extends AppiumPageFactory {

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Tools']")
	public static List<WebElement> tools;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='MasterNavPage']/XCUIElementTypeOther[1]/XCUIElementTypeTable/XCUIElementTypeCell")
	public static WebElement leftNav;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='Categories']/XCUIElementTypeScrollView[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther")
	public static WebElement dash;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Apply All']")
	public static WebElement applyAll;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Apply All']/preceding:: XCUIElementTypeButton[2]")
	public static WebElement exsitingClerkBtn;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Apply All']/preceding:: XCUIElementTypeButton[1]")
	public static WebElement newClerkBtn;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypePickerWheel")
	public static WebElement dropDown;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Done']")
	public static WebElement done;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Dashboard']")
	public static WebElement dashboard;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Submit']")
	public static WebElement submit;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"MasterNavPage\"]/XCUIElementTypeOther[1]/XCUIElementTypeTable/XCUIElementTypeCell[1]")
	public static WebElement collapseBtn;
	
	@iOSXCUITFindBy(xpath ="//XCUIElementTypeScrollView//XCUIElementTypeButton[contains(@name, '-')]/preceding:: XCUIElementTypeStaticText[1]")
	public static WebElement categoryName;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeActivityIndicator[@name='Progress halted' or @name='Loading']")
	public static List<WebElement> activityIndicator;
	
	public void verifyToolsCategoryVisibility() {
	    scrollUp(3);

	    int toolSize = tools.size();

	    if ("n".equalsIgnoreCase(CommonPages.siCode)) {
	        assertTrue("Tools should not be visible when briefcaseDisplayTools = 'n'", toolSize == 0);
	    } else {
	        assertTrue("Tools should be visible when briefcaseDisplayTools = 'y'", toolSize >= 1);

	        // Locate the 'Tools' element
	        WebElement toolsLabel = tools.stream()
	            .filter(el -> el.getText().equalsIgnoreCase("Tools"))
	            .findFirst()
	            .orElseThrow(() -> new AssertionError("'Tools' label not found"));

	        // Look for numeric badges after the Tools label
	        List<WebElement> followingTextElements = toolsLabel.findElements(By.xpath("following-sibling::XCUIElementTypeStaticText"));

	        boolean hasNumericBadge = followingTextElements.stream()
	            .anyMatch(el -> el.getText().matches("\\d+"));

	        assertFalse("'Tools' should not have a numeric badge, but one was found.", hasNumericBadge);
	    }
	}



	public void tapApplyAllWithoutClerkAndVerifyMessage() {
	    tools.get(0).click();

	    if (Actions.isDisplayed(applyAll)) {
	        applyAll.click();
	        boolean errorMessageShown = driver.getPageSource().contains("Please select an existing clerk");
	        assertTrue("Expected message not shown when applying without clerk.", errorMessageShown);
	    }
	}

	public void scrollUp(int maxTries) {
	    for (int i = 0; i < maxTries; i++) {
	        Utility.scroll(dash, "up");
	        Utility.scroll(leftNav, "up");
	    }
	}

	public void isSortedInDescending(List<UserInputData> userInputData) {
		
		List<String> firstNames = getListOfLwks(2, userInputData);
		List<String> lastNames = getListOfLwks(3, userInputData);

		int index = (firstNames.size() > 1) ? Utility.getRandomNumberInRange(0, firstNames.size() - 1) : 0;

		// Select an existing clerk by combining first and last names
		selectExistingClerk(firstNames.get(index).trim() + " " + lastNames.get(index).trim());

		ReferralSortOrderPage sortOrderPage = new ReferralSortOrderPage();

		// Retrieve the list of assignments sorted by case number in descending order
		List<String> assignments = sortOrderPage.assignmentsSortedByCase();
		
		   assertTrue("REFERRALS ARE NOT SORTED BY CASE NUMBER IN DESCENDING ORDER ------> " + assignments,
	               Utility.checkCaseNumberOrder(assignments, false));
		   
	}

	public List<String> getListOfLwks(int columnIndex, List<UserInputData> userInputData) {
		String peId = DocumentPage.get_pe_id("jud", userInputData);
		return execute(getID(DUPLICATED_LWK, peId), columnIndex, userInputData);
	}

	public void scrollThroughTheList(String existingClerk) {
	    Page.sleep(2000);
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    if (existingClerk.isEmpty()) {
	        WebElement dropDown = driver.findElement(By.className("XCUIElementTypePickerWheel"));
	        Map<String, Object> params = new HashMap<>();
	        params.put("order", "next");
	        params.put("offset", 0.1);
	        params.put("element", ((RemoteWebElement) dropDown).getId());

	        js.executeScript("mobile: selectPickerWheelValue", params);
	    } else {
	        boolean elementNotFound = true;
	        boolean firstScroll = true;

	        while (elementNotFound) {
	            WebElement dropDown;
	            try {
	                dropDown = driver.findElement(By.className("XCUIElementTypePickerWheel"));
	            } catch (NoSuchElementException e) {
	                Page.sleep(500);
	                dropDown = driver.findElement(By.className("XCUIElementTypePickerWheel"));
	            }

	            Map<String, Object> params = new HashMap<>();
	            params.put("order", "next");
	            params.put("offset", 0.1);
	            params.put("element", ((RemoteWebElement) dropDown).getId());

	            js.executeScript("mobile: selectPickerWheelValue", params);

	            if (firstScroll) {
	                Page.sleep(500); 
	                firstScroll = false;
	            }

	            Page.sleep(300); 
	            try {
	                dropDown = driver.findElement(By.className("XCUIElementTypePickerWheel"));
	                if (dropDown.getText().trim().equals(existingClerk)) {
	                    elementNotFound = false;
	                }
	            } catch (NoSuchElementException e) {
	               
	            }
	        }
	    }

	    done.click();
	}


	public void selectExistingClerk(String existingClerk) {
		

		// Scroll up to refresh the view or access the tools
		scrollUp(3);

		// Get the size of the tools list
		int toolSize = tools.size();

		// Check if tools should be displayed based on siVal
		if (CommonPages.siCode.equalsIgnoreCase("n")) {
			// Verify that no tools are displayed if siVal is 'n'
			assertTrue("The left nav displays the Tools category, even when briefcaseDisplayTools is set to 'n'.",
					toolSize == 0);
		} else {
			// If tools should be displayed, click on the first tool
			tools.get(0).click();

			// Click on the existing clerk button
			exsitingClerkBtn.click();

			collapseBtn.click();
			
			// Scroll through the list to find the specified clerk
			scrollThroughTheList(existingClerk);
		}
	}

	public void duplicateAssignments(List<UserInputData> userInputData) {

			
				// Retrieve data from user input
				List<String> firstNames = getListOfLwks(2, userInputData);
				List<String> lastNames = getListOfLwks(3, userInputData);
				List<String> cavDisplay = getListOfLwks(4, userInputData);
				List<String> cyvDisplay = getListOfLwks(5, userInputData);
				List<String> caseNumbers = getListOfLwks(6, userInputData);

				// Combine relevant data into a single list for duplicate checking
				List<String> combinedList = new ArrayList<>();
				for (int i = 0; i < firstNames.size(); i++) {
					combinedList.add(cavDisplay.get(i).trim() + ", " + cyvDisplay.get(i).trim() + ", "
							+ caseNumbers.get(i).trim());
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
					tapIndividualClerk(caseNumbers.get(index1).trim(), cyvDisplay.get(index1).trim(),
							cavDisplay.get(index1).trim());
					scrollThroughTheList(clerkName2);

					// Submit and verify duplication message
					submit.click();
					boolean duplicatedAssignmentMsg = driver.getPageSource()
							.contains("Duplicated Assignment found for " + clerkName2);
					
					assertTrue("Verify that user is prevented from creating duplicate assignments: " + clerkName1
							+ " : " + clerkName2, duplicatedAssignmentMsg);
					
					// Re-fetch list from DB after the attempt
					List<String> updatedFirstNames = getListOfLwks(2, userInputData);
					List<String> updatedLastNames = getListOfLwks(3, userInputData);
					List<String> updatedCavDisplay = getListOfLwks(4, userInputData);
					List<String> updatedCyvDisplay = getListOfLwks(5, userInputData);
					List<String> updatedCaseNumbers = getListOfLwks(6, userInputData);

					// Build expected key and clerk name
					String expectedKey = cavDisplay.get(index1).trim() + ", "
					                   + cyvDisplay.get(index1).trim() + ", "
					                   + caseNumbers.get(index1).trim();
					String expectedClerk = firstNames.get(index1).trim() + " " + lastNames.get(index1).trim();

					System.out.println("Expected entry key: " + expectedKey);
					System.out.println("Expected clerk: " + expectedClerk);

					boolean recordStillExists = false;

					for (int i = 0; i < updatedCaseNumbers.size(); i++) {
					    String currentKey = updatedCavDisplay.get(i).trim() + ", "
					                      + updatedCyvDisplay.get(i).trim() + ", "
					                      + updatedCaseNumbers.get(i).trim();
					    String currentClerk = updatedFirstNames.get(i).trim() + " " + updatedLastNames.get(i).trim();

					    if (currentKey.equals(expectedKey) && currentClerk.equals(expectedClerk)) {
					        recordStillExists = true;
					        System.out.println("✔ Match found: " + currentKey + " -> " + currentClerk);
					        break;
					    }
					}

					assertTrue("Verify that the original assignment with the same clerk still exists in the DB after failed duplicate attempt: "
					        + expectedKey + " -> " + expectedClerk, recordStillExists);

				} else {
					throw new RuntimeException(
							"Ensure there are law clerk assignments of a specific type in a specific referral, but assigned to two different law clerks. Law clerk list: "
									+ firstNames + " " + lastNames);
				}

	}

	public List<String> findDuplicates(List<String> list) {
		Map<String, List<Integer>> indexMap = new HashMap<>();
		List<String> duplicates = new ArrayList<>();

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

		Utility.scrollDownIfNotDisplayed("//XCUIElementTypeButton[contains(@name, '" + caseNum + "')]/preceding::"
				+ "XCUIElementTypeStaticText[contains(@name, '" + cyv_display + "')]/preceding::"
				+ "XCUIElementTypeStaticText[contains(@name, '" + cav_display
				+ "')]/preceding::XCUIElementTypeButton[1]");

	}
	
	public boolean isNavigatedToTargetCasePage() {

	    scrollUp(3);

	    int toolSize = tools.size();

	    if (CommonPages.siCode.equalsIgnoreCase("n")) {
	        return toolSize == 0;
	    }

	    tools.get(0).click();

	    exsitingClerkBtn.click();

	    // Scroll through the list to find the specified clerk
	    findExsitingLwk();

	    collapseBtn.click();

	    ReferralSortOrderPage sortOrderPage = new ReferralSortOrderPage();
	    List<String> assignments = sortOrderPage.assignmentsSortedByCase();

	    List<String> categories = driver.findElements(By.xpath(
	            "//XCUIElementTypeScrollView//XCUIElementTypeButton[contains(@name, '-')]/preceding::XCUIElementTypeStaticText[1]"))
	            .stream()
	            .map(e -> e.getText().trim())
	            .collect(Collectors.toList());

	    for (int i = 0; i < assignments.size(); i++) {
	        CaseQueryPage casequerypage = new CaseQueryPage();
	        casequerypage.searchForACase(categories.get(i), assignments.get(i), Search.caseNumber);

	        CommonPages page = new CommonPages();
	        page.getGroupIcons(GroupIcons.Expand);

	        if (Utility.isDisplayed(containsElement("Applied"))) {
	            // Collapse and go back to Tools
	            collapseBtn.click();
	            tools.get(0).click();
	            collapseBtn.click();
	            Actions.contains(assignments.get(i)).click();
	            CommonPages.ifDownloaded(activityIndicator);

	            return Actions.isDisplayed(Actions.contains("Case #" + assignments.get(i)));
	        }
	    }

	    return false; 
	}
	


	public void findExsitingLwk() {
		    Page.sleep(2000);
		    JavascriptExecutor js = (JavascriptExecutor) driver;

		    boolean foundNonEmpty = false;
		    boolean firstScroll = true;

		    while (!foundNonEmpty) {
		        WebElement dropDown;
		        try {
		            dropDown = driver.findElement(By.className("XCUIElementTypePickerWheel"));
		        } catch (NoSuchElementException e) {
		            Page.sleep(500);
		            dropDown = driver.findElement(By.className("XCUIElementTypePickerWheel"));
		        }

		        Map<String, Object> params = new HashMap<>();
		        params.put("order", "next");
		        params.put("offset", 0.1);
		        params.put("element", ((RemoteWebElement) dropDown).getId());

		        js.executeScript("mobile: selectPickerWheelValue", params);

		        if (firstScroll) {
		            Page.sleep(500);
		            firstScroll = false;
		        }

		        Page.sleep(300);
		        try {
		            dropDown = driver.findElement(By.className("XCUIElementTypePickerWheel"));
		            if (!dropDown.getText().trim().isEmpty()) {
		                foundNonEmpty = true;
		            }
		        } catch (NoSuchElementException ignored) {}
		    }

		    done.click();
		}
	
	public boolean isExistingClerkExcludedFromNewClerkList() {
	    scrollUp(3);

	    int toolSize = tools.size();

	    if (CommonPages.siCode.equalsIgnoreCase("n")) {
	        return toolSize == 0;
	    }

	    tools.get(0).click();
	    exsitingClerkBtn.click();

	    String existingLwk = findExistingLwkRandomByIndex(); 

	    newClerkBtn.click();

	    List<String> newLwkList = getNewLwks(); 

	    boolean isExcluded = !newLwkList.contains(existingLwk);
	    System.out.println("Existing clerk: " + existingLwk);
	    System.out.println("New clerk list: " + newLwkList);
	    System.out.println("Is existing clerk excluded? " + isExcluded);

	    return isExcluded;
	}

	public String findExistingLwkRandomByIndex() {
	    Page.sleep(2000);
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    List<String> allValues = new ArrayList<>();
	    Set<String> seenValues = new HashSet<>();
	    WebElement dropDown = driver.findElement(By.className("XCUIElementTypePickerWheel"));
	    boolean reachedEnd = false;

	    while (!reachedEnd) {
	        dropDown = driver.findElement(By.className("XCUIElementTypePickerWheel"));
	        String currentValue = dropDown.getText().trim();

	        if (!currentValue.isEmpty() && !seenValues.contains(currentValue)) {
	            allValues.add(currentValue);
	            seenValues.add(currentValue);
	        } else if (!currentValue.isEmpty() && seenValues.contains(currentValue)) {
	            reachedEnd = true; 
	            break;
	        }

	        Map<String, Object> params = new HashMap<>();
	        params.put("order", "next");
	        params.put("offset", 0.15);
	        params.put("element", ((RemoteWebElement) dropDown).getId());

	        int attempts = 0;
	        while (attempts < 3) {
	            try {
	                js.executeScript("mobile: selectPickerWheelValue", params);
	                break;
	            } catch (org.openqa.selenium.InvalidElementStateException e) {
	                Page.sleep(300);
	                attempts++;
	            }
	        }
	        Page.sleep(300);
	    }

	    int size = allValues.size();
	    if (size == 0) return ""; 
	    int randomIndex = new Random().nextInt(size);

	    dropDown = driver.findElement(By.className("XCUIElementTypePickerWheel"));
	    for (int i = 0; i <= randomIndex; i++) {
	        Map<String, Object> params = new HashMap<>();
	        params.put("order", "next");
	        params.put("offset", 0.15);
	        params.put("element", ((RemoteWebElement) dropDown).getId());

	        int attempts = 0;
	        while (attempts < 3) {
	            try {
	                js.executeScript("mobile: selectPickerWheelValue", params);
	                break;
	            } catch (org.openqa.selenium.InvalidElementStateException e) {
	                Page.sleep(300);
	                attempts++;
	            }
	        }
	        Page.sleep(300);
	    }

	    dropDown = driver.findElement(By.className("XCUIElementTypePickerWheel"));
	    String randomValue = dropDown.getText().trim();

	    done.click(); 
	    System.out.println("Randomly selected clerk: " + randomValue);
	    return randomValue;
	}


	
	public List<String> getNewLwks() {
	    Page.sleep(2000);
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    List<String> clerkNames = new ArrayList<>();
	    Set<String> seenValues = new HashSet<>();

	    WebElement dropDown = driver.findElement(By.className("XCUIElementTypePickerWheel"));
	    boolean reachedEnd = false;

	    while (!reachedEnd) {
	        dropDown = driver.findElement(By.className("XCUIElementTypePickerWheel"));
	        String currentValue = dropDown.getText().trim();

	        if (!currentValue.isEmpty() && !seenValues.contains(currentValue)) {
	            clerkNames.add(currentValue);
	            seenValues.add(currentValue);
	        } else if (!currentValue.isEmpty() && seenValues.contains(currentValue)) {
	            reachedEnd = true;
	            break;
	        }

	        Map<String, Object> params = new HashMap<>();
	        params.put("order", "next");
	        params.put("offset", 0.15); 
	        params.put("element", ((RemoteWebElement) dropDown).getId());

	        int attempts = 0;
	        while (attempts < 3) {
	            try {
	                js.executeScript("mobile: selectPickerWheelValue", params);
	                break; 
	            } catch (org.openqa.selenium.InvalidElementStateException e) {
	                Page.sleep(300); 
	                attempts++;
	            }
	        }

	        Page.sleep(300); 
	    }

	    done.click();
	    System.out.println("Collected clerk names: " + clerkNames);
	    return clerkNames;
	}




}
