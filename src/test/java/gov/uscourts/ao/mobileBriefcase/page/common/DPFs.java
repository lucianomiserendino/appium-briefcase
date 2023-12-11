package gov.uscourts.ao.mobileBriefcase.page.common;

import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.executeQuery;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities.getID;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.APPLICABLE_ACTIONS;
import static gov.uscourts.ao.mobileBriefcase.DBUtils.Queries.DPF;
import static java.util.Collections.sort;

import java.util.List;

import gov.uscourts.ao.mobileBriefcase.DBUtils.DBUtilities;
import gov.uscourts.ao.mobileBriefcase.Pages.CommonPages;
import gov.uscourts.ao.mobileBriefcase.model.UserInputData;

public class DPFs extends AppiumPageFactory {

	public List<String> getListOfBriefcaseDPFs(String caseNumber, List<UserInputData> userInputData) {

		String cmr_id = CommonPages.getCMRID(caseNumber, userInputData);

		List<String> dbResult = executeQuery(getID(APPLICABLE_ACTIONS, cmr_id), userInputData);

		sort(dbResult);

		return dbResult;

	}

	public String getChmAssign(DPF dpf, String caseNumber, List<UserInputData> userInputData) {

		List<String> dpfList = getListOfBriefcaseDPFs(caseNumber, userInputData);

		String el_functions = "";
		switch (dpf) {

		case chmAssign:
			el_functions = "chmAssign";

			break;

		case chmSilentAssign:
			el_functions = "chmSilentAssign";
			break;

		case note:
			el_functions = "note";
			break;

		case docWP:
			el_functions = "docWP";
			break;

		case judgeVote:
			el_functions = "judgeVote";
			break;

		default:
			break;
		}
		String a = "";

		Boolean elementNotFound = true;

		while (elementNotFound) {

			for (int i = 0; i < dpfList.size(); ++i) {

				String dbResult = DBUtilities.getAllColumns(
						Actions.replace(DPF, "EL_FUNCTIONS", el_functions, "EL_LIST_TEXT", dpfList.get(i).trim()),
						userInputData);

				if (dbResult.contains(el_functions)) {

					if (getParameter(dbResult) == 2) {

						a = dpfList.get(i).trim();
						elementNotFound = false;
						break;
					}
				} else {
					elementNotFound = true;

				}
			}

		}
		return a;

	}

	public static int getParameter(String param) {

		String[] items = param.split(";");

		return items.length;
	}

	public enum DPF {
		chmAssign, chmSilentAssign, note, docWP, judgeVote
	}

}
