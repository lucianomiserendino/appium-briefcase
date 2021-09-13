package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.common.Actions.replace;
import static gov.uscourts.ao.mobileBriefcase.common.Utility.changeDateFormat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities;
import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.common.Actions;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;

public class VoteClosingDate {

	public void getVoteClosingDate(String ccr_id, List<UserInputData> userInputData) {

		String closingDate = changeDateFormat(
				DBUtilities.execute(replace(Queries.VOTE_CLOSING_DATE, "CCR_ID", ccr_id), 2, userInputData).get(0),
				"yyyy-MM-dd", "M/d/yyyy");

		assertTrue(Actions.contains("Vote Closing: " + closingDate).isDisplayed());

	}
}
