package gov.uscourts.ao.mobileBriefcase.common;

import io.appium.java_client.TouchAction;

public class Coordinates extends Base {

	private static final int DASHBOARD_X = 33;
	private static final int DASHBOARD_Y = 116;

	private static final int BACK_X = 147;
	private static final int BACK_Y = 48;

	private static final int DISMISS_X = 506;
	private static final int DISMISS_Y = 741;

	public static void select(BriefcaseCoordinates coordinates) {

		switch (coordinates) {

		case BACK:
			tapByCoordinates(BACK_X, BACK_Y);
			break;

		case DISMISS:
			tapByCoordinates(DISMISS_X, DISMISS_Y);
			break;

		case DASHBOARD:
			tapByCoordinates(DASHBOARD_X, DASHBOARD_Y);
			break;

		default:
			break;

		}

	}

	public static TouchAction tapByCoordinates(int x, int y) {
		return new TouchAction(driver).tap(x, y).perform();

	}

	public enum BriefcaseCoordinates {

		BACK, DISMISS, DASHBOARD,

	}

}