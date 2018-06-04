package gov.uscourts.ao.mobileBriefcase.common;

import io.appium.java_client.TouchAction;

public class BriefcaseUsers extends Base {

	private static final int APPELLATE_JUDGES_X = 195;
	private static final int APPELLATE_JUDGES_Y = 92;

	private static final int BANKRUPTCY_JUDGES_X = 195;
	private static final int BANKRUPTCY_JUDGES_Y = 139;

	private static final int STAFF_ATTORNEYS_X = 195;
	private static final int STAFF_ATTORNEYS_Y = 195;

	private static final int APPELATE_JUDGE_COLLOTON_X = 158;
	private static final int APPELATE_JUDGE_COLLOTON_Y = 389;

	private static final int STAFF_ATTORNEYS_BENJAMIN_X = 150;
	private static final int STAFF_ATTORNEYS_BENJAMIN_Y = 290;

	private static final int MOTIONS_PETITIONS_X = 206;
	private static final int MOTIONS_PETITIONS_Y = 338;

	private static final int DASHBOARD_X = 33;
	private static final int DASHBOARD_Y = 116;

	private static final int ANDERS_CASES_X = 173;
	private static final int ANDERS_CASES_Y = 290;

	private static final int CASES_X = 371;
	private static final int CASES_Y = 345;

	public static void selectUser(Users coordinates) {

		switch (coordinates) {

		case CASES:
			tapByCoordinates(CASES_X, CASES_Y);
			break;

		case ANDERS_CASES:
			tapByCoordinates(ANDERS_CASES_X, ANDERS_CASES_Y);
			break;

		case MOTIONS_PETITIONS:
			tapByCoordinates(MOTIONS_PETITIONS_X, MOTIONS_PETITIONS_Y);
			break;
		case DASHBOARD:
			tapByCoordinates(DASHBOARD_X, DASHBOARD_Y);
			break;

		case APPELLATE_JUDGES:
			tapByCoordinates(APPELLATE_JUDGES_X, APPELLATE_JUDGES_Y);
			break;

		case BANKRUPTCY_JUDGES:
			tapByCoordinates(BANKRUPTCY_JUDGES_X, BANKRUPTCY_JUDGES_Y);
			break;

		case STAFF_ATTORNEYS:
			tapByCoordinates(STAFF_ATTORNEYS_X, STAFF_ATTORNEYS_Y);
			break;

		case COLLOTON_STEVEN:
			tapByCoordinates(APPELATE_JUDGE_COLLOTON_X, APPELATE_JUDGE_COLLOTON_Y);
			break;

		case BROWN_BENJAMIN:
			tapByCoordinates(STAFF_ATTORNEYS_BENJAMIN_X, STAFF_ATTORNEYS_BENJAMIN_Y);
			break;

		default:
			break;

		}

	}

	public static TouchAction tapByCoordinates(int x, int y) {
		return new TouchAction(driver).tap(x, y).perform();

	}

	public enum Users {

		/** Referral categories */

		ANDERS_CASES, CASES,

		/** Dashboard Categories */

		MOTIONS_PETITIONS, DASHBOARD,

		/** User's Categories */

		APPELLATE_JUDGES, BANKRUPTCY_JUDGES, STAFF_ATTORNEYS,

		/** Appellate judges */

		BEAM_ARLEN, BENTON_DUANE, BOWMAN_PASCO, BRIGHT_MYRON, BYE_KERMIT, COLLOTON_STEVEN, GRUENDER_RAYMOND, JKELLY_JUDGE, MASON_CHRISTOPHER, MELLOY_MICHAEL, MURPHY_DIANA, RILLEY_WILLIAM, ROUNDUP_JUDGE, SHEPERD_BOBBY, SMITH_LAVENSKI, WOLLMAN_ROGER,

		/** Bankruptcy judges */

		FEDERMAN_ARTUR, KRESSEL_ROBERT, NAIL_CHARLES, SALADION_THOMAS, SCHERMER_BARRY, SHODEEN_ANITA,

		/** Staff attorneys */

		ATTORNEY_NEW, BROWN_BENJAMIN, CALLOWAY_RENE, DEIGHTON_ELIZABETH, DOCKERY_BETH, DRISCOLL_JOHN, D_SOUZA_LISA, DULANEY_KEVIN, EDITOR_NO, GORDON_SHIRA, HAUSMANN_JARED, INBOX_SAO, JOHNSON_TAMI, JONES_KIM, KICIELINSKI_MARYLEE, KRUMM_TERESA, LAUGHLIN_SUSAN, LAWDER_SHAWN, LONG_MICHELE, MCPECK_GINA, O_BANION_STEPHANIE, OBERKFELL_LAURA, PUTZEL_JOHN, ROZENBERG_CALEB, SMITH_STAFFATTORNEY, SONTAG_RUTH, STAFFATTORNEY_KRISTEN, TANG_JULIE, VIEWER_SAO, WASHINGTON_MARTHA, WATSON_LENELL, ZENG_TRACEY

	}

}
