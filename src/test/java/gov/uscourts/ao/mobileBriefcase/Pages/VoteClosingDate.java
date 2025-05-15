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


	public void verifyVoteClosingDate(List<UserInputData> userInputData) {
	    List<String> ccrIds = CommonPages.findCCRID(userInputData);
	    System.out.println("Found CCR_IDs: " + ccrIds);

	    boolean voteClosingDateIsValid = false;

	    for (String ccrId : ccrIds) {
	        try {
	            String query = replace(Queries.VOTE_CLOSING_DATE, "CCR_ID", ccrId);

	            List<String> voteClosingDateResults = DBUtilities.execute(query, 2, userInputData);
	            List<String> reliefResults = DBUtilities.execute(query, 4, userInputData);

	            if (!voteClosingDateResults.isEmpty() && voteClosingDateResults.get(0) != null &&
	                !reliefResults.isEmpty() && reliefResults.get(0) != null) {

	                String voteClosingDate = voteClosingDateResults.get(0).trim();
	                String reliefText = reliefResults.get(0).trim();

	                System.out.println("Vote Closing Date for CCR ID " + ccrId + ": " + voteClosingDate);
	                System.out.println("Relief text for CCR ID " + ccrId + ": " + reliefText);

	                String formattedDate = changeDateFormat(voteClosingDate, "yyyy-MM-dd", "M/d/yyyy");

	             
	                String expectedText = "Filed')]/following::XCUIElementTypeStaticText[contains(@name, 'Vote Closing: " + formattedDate+"')]";
	                String expectedXPath = expectedText + "/following::XCUIElementTypeStaticText[contains(@name, '" + reliefText;

	                
	                if (Actions.contains(expectedXPath).isDisplayed()) {
	                    voteClosingDateIsValid = true;
	                    break;
	                }
	            }
	        } catch (Exception e) {
	            System.out.println("Exception for CCR ID " + ccrId + ": " + e.getMessage());
	        }
	    }

	    assertTrue("None of the CCR_IDs returned a valid Vote Closing Date with matching relief.", voteClosingDateIsValid);
	}




}
