package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.ACTION_NAME;
import static gov.uscourts.ao.mobileBriefcase.common.Base.driver;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.elementIsDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.getPanel;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.Actions.ACTIONS;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.click;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.isDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.selectCaseNumber;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.DBType;
import static gov.uscourts.ao.mobileBriefcase.common.Page.*;
import gov.uscourts.ao.mobileBriefcase.common.Helper.Actions;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class iOS_CommonPages {

	public iOS_CommonPages() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public void selectCategoryAndCase(String category, String caseNum) {
		selectCaseNumber(category, caseNum);
	}

	public static void getActionsPanel(DBType dbType, String el_id) {
		getPanel(ACTIONS);
		performPageLoad();
		clickOnPanel(ACTIONS, getActionName(dbType, el_id));

	}

	public static String getActionName(DBType dbType, String el_id) {
		return "//XCUIElementTypeTable[@name='DocumentList']/child::*//*[contains(@name, '"
				+ getAllColumns(dbType, getID(ACTION_NAME, el_id)) + "')]";

	}

	public static void clickOnPanel(Actions panel, String penlRow) {
		try {
			if (isDisplayed(By.xpath(penlRow)) == true) {
				click(penlRow);
			} else {
				getPanel(panel);
				click(penlRow);
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}

	}

	public void selectAction(DBType dbType, String el_id) {
		getActionsPanel(dbType, el_id);
		assertTrue(elementIsDisplayed(getAllColumns(dbType, getID(ACTION_NAME, el_id))));

	}

	public void verifyElementIsDisplayed(String element) {
		assertTrue(" PLEASE ENSURE THAT ELEMENT IS DISPLAYED ", elementIsDisplayed(element));
	}

}
