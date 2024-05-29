package gov.uscourts.ao.mobileBriefcase.Pages;

import static gov.uscourts.ao.mobileBriefcase.page.common.Actions.replace;
import static gov.uscourts.ao.mobileBriefcase.page.common.Utility.changeDateFormat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities;
import gov.uscourts.ao.mobileBriefcase.DBUtils.Queries;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;
import gov.uscourts.ao.mobileBriefcase.page.common.Actions;

public class VoteClosingDate {

	public void getVoteClosingDate(String ccr_id, List<UserInputData> userInputData) {
	    try {
	        // Execute the query to get the vote closing date
	        String query = replace(Queries.VOTE_CLOSING_DATE, "CCR_ID", ccr_id);
	        List<String> result = DBUtilities.execute(query, 2, userInputData);
	        String voteClosingDate = result.get(0);

	        // Change the date format
	        String closingDate = changeDateFormat(voteClosingDate, "yyyy-MM-dd", "M/d/yyyy");

	        // Assert that the UI element with the formatted date is displayed
	        assertTrue("Vote closing date is missing in vote information pane or is incorrect",
	                Actions.contains("Vote Closing: " + closingDate).isDisplayed());
	    } catch (Exception e) {
	        // Handle any exceptions that occur
	        System.err.println("An error occurred: " + e.getMessage());
	        // You can choose to rethrow the exception or handle it in another way
	    }
	}


}
