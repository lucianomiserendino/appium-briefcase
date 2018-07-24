package gov.uscourts.ao.mobileBriefcase.common;

import static gov.uscourts.ao.mobileBriefcase.common.Utilities.clickOn;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;

public class Categories {

	@iOSFindBy(accessibility = "Anders Motion")
	public static MobileElement andersMotion_cm5a;

	@iOSFindBy(accessibility = "Case on Calendar")
	public static MobileElement caseOnCalendar_cm5a;

	@iOSFindBy(accessibility = "IFP motion")
	public static MobileElement IFP_Motion_cm5a;

	@iOSFindBy(accessibility = "Jurisdictional")
	public static MobileElement jurisdictional_cm5a;

	@iOSFindBy(accessibility = "Motion/Petition")
	public static MobileElement motionPetition_cm5a;

	@iOSFindBy(accessibility = "OSC Referral")
	public static MobileElement OSCReferral_cm5a;

	@iOSFindBy(accessibility = "Rule 34")
	public static MobileElement rule34_cm5a;

	@iOSFindBy(accessibility = "Screening")
	public static MobileElement screening_cm5a;

	public static void getCategory(Category category) {
		switch (category) {
		case ANDERS_MOTION:
			clickOn(andersMotion_cm5a);
			break;

		case CASE_ON_CALENDAR:
			clickOn(caseOnCalendar_cm5a);
			break;
			
		case IFP_MOTION:
			clickOn(IFP_Motion_cm5a);
			break;
			
		case JURISDICTIONAL:
			clickOn(jurisdictional_cm5a);
			break;
			
		case MOTION_PETITION:
			clickOn(motionPetition_cm5a);
			break;
			
		case OSC_REFERRAL:
			clickOn(OSCReferral_cm5a);
			break;
			
		case RULE_34:
			clickOn(rule34_cm5a);
			break;
			
		case SCREENING:
			clickOn(screening_cm5a);
			break;

		default:
			break;
		}
	}

	public enum Category {
		ANDERS_MOTION, CASE_ON_CALENDAR, IFP_MOTION, JURISDICTIONAL, MOTION_PETITION, OSC_REFERRAL, RULE_34, SCREENING
	}
}
