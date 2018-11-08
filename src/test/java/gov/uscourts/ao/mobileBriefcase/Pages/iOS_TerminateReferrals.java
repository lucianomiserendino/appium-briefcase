package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getAllColumns;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getText;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.insertData;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.valueOf;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.CCR_DATE_END;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.UPDATE_CHAMBERS_CASE_TO_REFERRAL;
import static gov.uscourts.ao.mobileBriefcase.Pages.iOS_CommonPages.getCMRID;
import static gov.uscourts.ao.mobileBriefcase.common.BriefcaseCoordinates.select;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.clickOnElement;
import static gov.uscourts.ao.mobileBriefcase.common.Helper.elementIsDisplayed;
import static gov.uscourts.ao.mobileBriefcase.common.Utilities.getCurrentDateTime;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import gov.uscourts.ao.mobileBriefcase.common.AppiumPageFactory;
import gov.uscourts.ao.mobileBriefcase.common.BriefcaseCoordinates.Coordinates;

public class iOS_TerminateReferrals extends AppiumPageFactory {

	/** find the ccr_id for the referral */
	public static String getCMR_CCR_ID(String caseNum, String dbType, String peID) {
		return getCMRID(valueOf(dbType), caseNum, peID, "='autotst'");

	}

	public void terminateReferral(String caseNum, String dbType, String peID) {
		assertTrue(elementIsDisplayed(caseNum));
		setCCR_DATE_END(ReferralTermination.TERMINATE, caseNum, dbType, peID);
		select(Coordinates.DASHBOARD);

	}

	public void verifyReferralIsTerminated(String category, String caseNum, String dbType, String peID) {

		clickOnElement(category);
		String ccr_date_end = getAllColumns(valueOf(dbType),
				getID((CCR_DATE_END), getCMR_CCR_ID(caseNum, dbType, peID)));
		assertFalse("******VERIFY REFERRAL IS NOT DISPLAYED AFTER TERINATION*******", elementIsDisplayed(caseNum));
		assertNotNull("******VERIFY REFERRAL IS TERMINATED AND CCR_DATE_END IS NOT NULL*******", ccr_date_end);

	}

	public void setCCR_DATE_END(ReferralTermination term, String caseNum, String dbType, String peID) {
		switch (term) {
		case TERMINATE:
			updateChambersCaseToReferralEndDate(dbType, caseNum, peID, getCurrentDateTime());
			break;
		case UN_TERMINATE:
			updateChambersCaseToReferralEndDate(dbType, caseNum, peID, "NULL");
			break;
		default:
			break;
		}
	}

	/**
	 * set case_to_referral.ccr_date_end date field to terminate/un-terminate the
	 * referral
	 */
	public static void updateChambersCaseToReferralEndDate(String dbType, String caseNum, String peID,
			String ccr_date_end) {
		insertData(valueOf(dbType),
				getID(getText(UPDATE_CHAMBERS_CASE_TO_REFERRAL, ccr_date_end), getCMR_CCR_ID(caseNum, dbType, peID)));
	}

	public enum ReferralTermination {
		TERMINATE, UN_TERMINATE
	}

}
