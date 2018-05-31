package gov.uscourts.ao.mobileBriefcase.common;

import java.util.ArrayList;
import java.util.List;

public interface ConstantVariables {


	public static final String ANDERS_CASES = "Anders Cases";

	public static final String IFP_MOTION_IN_THIS_COURT = "IFP motion in this court";

	public static final String NO_ARGUMENT_REFERRALS = "No Argument Referrals";

	public static final String PRO_SE_REFS = "Pro Se Refs";

	public static final String SUMMARY_DISPOSITION = "Summary Disposition";

	public static final String UNASSIGNED_REFERRALS = "Unassigned Referrals";

	public static  List<String> getConstants() {
		 List<String> variables = new ArrayList<>();
		 variables.add(ANDERS_CASES);
		 variables.add(IFP_MOTION_IN_THIS_COURT);
		 variables.add(NO_ARGUMENT_REFERRALS);
		 variables.add(PRO_SE_REFS);
		 variables.add(SUMMARY_DISPOSITION);
		 variables.add(UNASSIGNED_REFERRALS);
		 return variables;
		 
		 
	

	}

}
