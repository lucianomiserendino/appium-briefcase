package gov.uscourts.ao.mobileBriefcase.Pages;

import static org.openqa.selenium.support.PageFactory.initElements;

import java.util.List;

import org.openqa.selenium.WebElement;

import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions.Locator;
import gov.uscourts.ao.mobileBriefcase.page.common.Base;
import gov.uscourts.ao.mobileBriefcase.page.common.Utility;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ProposedOrdersPage extends Base {
	public ProposedOrdersPage() {
		initElements(new AppiumFieldDecorator(getInstance(Driver.IOS)), this);

	}

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Proposed Orders']/following:: XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeStaticText")
	public static List<WebElement> proposedOrder;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='*not selected*']/following:: XCUIElementTypeStaticText[@name='Select']")
	public static WebElement select;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeToolbar[@name='Toolbar'])[1]/following::XCUIElementTypeOther[1]/XCUIElementTypeButton")
	public static List<WebElement> toolBar1;

	@iOSXCUITFindBy(accessibility = "Annotations")
	public static WebElement annotations;

	@iOSXCUITFindBy(accessibility = "Close")
	public static WebElement close;

	public String selectRandomProposedOrder() {

		int index = Utility.getRandomNumberInRange(1, proposedOrder.size() - 1);
		return proposedOrder.get(index).getText().trim();

	}

	public void uploadDoc(String caseNum, List<UserInputData> userInputData) {

		String docName = selectRandomProposedOrder();

		Actions.tap(select);
		Actions.tap(Locator.XPATH, docName);
		try {
			WebElement el = Actions.findElementBy(Locator.XPATH, docName + ".pdf");
			if (el.isDisplayed()) {
				el.click();
			} else {
				throw new RuntimeException("Unable to select " + docName);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		AccessingAnnotatedDocuments.ifEditingToolsExist(toolBar1);
		Actions.tap(annotations);
		Actions.tap(close);
		AccessingAnnotatedDocuments.getBackEndUpdates(caseNum, docName, userInputData);
	}

}