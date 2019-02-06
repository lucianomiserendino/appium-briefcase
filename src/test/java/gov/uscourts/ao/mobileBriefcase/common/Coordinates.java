package gov.uscourts.ao.mobileBriefcase.common;

import io.appium.java_client.TouchAction;

public class Coordinates extends Base {

	private static final int DISMISS_X = 506;
	private static final int DISMISS_Y = 741;

	public static void select(BriefcaseCoordinates coordinates) {

		switch (coordinates) {
		case DISMISS:
			tapByCoordinates(DISMISS_X, DISMISS_Y);
			break;

		default:
			break;

		}

	}

	public static TouchAction tapByCoordinates(int x, int y) {
		return new TouchAction(driver).tap(x, y).perform();

	}

	public enum BriefcaseCoordinates {

		DISMISS

	}

}