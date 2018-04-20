package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.common.Utilities.getListOfCategories;

import java.util.List;

import org.openqa.selenium.support.PageFactory;

import gov.uscourts.ao.mobileBriefcase.common.Base;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

public class ReferralCategoriesPage extends Base {

	public ReferralCategoriesPage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@iOSFindBy(accessibility = "Screening Panels")
	public static MobileElement screeningPanels;

	@iOSFindBy(accessibility = "Petitions for Rehearing")
	public static MobileElement petitionsForRehearing;

	@iOSFindBy(accessibility = "Cases on Calendar")
	public static MobileElement casesOnCalendar;

	@iOSFindBy(accessibility = "Motions/Petitions")
	public static MobileElement motionsPetitions;

	public List<String> UIreferralCategoriesList() {
		return getListOfCategories(motionsPetitions, casesOnCalendar, petitionsForRehearing, screeningPanels);

	}
}