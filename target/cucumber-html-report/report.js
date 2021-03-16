$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("CATEGORIES/Reg/NON-ORALLY_ARGUED_CASES_AMB-973.feature");
formatter.feature({
  "line": 2,
  "name": "Number of cases displayed for non-orally argued cases",
  "description": "",
  "id": "number-of-cases-displayed-for-non-orally-argued-cases",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@Smoke"
    },
    {
      "line": 1,
      "name": "@AMB-973"
    },
    {
      "line": 1,
      "name": "@S"
    }
  ]
});
formatter.scenarioOutline({
  "line": 5,
  "name": "",
  "description": "Tapping on a referral category that is not orally argued (chm_reftype_val.cdv_is_oral_arg\u003d\u0027n\u0027),\n  a list of cases should dipslay for the judge for that category.\n  Need to verify the correct number of referrals are being displayed.",
  "id": "number-of-cases-displayed-for-non-orally-argued-cases;",
  "type": "scenario_outline",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 11,
  "name": "I am logged into Briefcase",
  "rows": [
    {
      "cells": [
        "environment",
        "userName",
        "password",
        "courtId"
      ],
      "line": 12
    },
    {
      "cells": [
        "Integration",
        "s haenni",
        "Test2024!",
        "test"
      ],
      "line": 13
    }
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 15,
  "name": "I select a user",
  "rows": [
    {
      "cells": [
        "role",
        "briefcaseUser"
      ],
      "line": 16
    },
    {
      "cells": [
        "Appellate Judges",
        "Colloton"
      ],
      "line": 17
    }
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 19,
  "name": "User  Observes the categories on db and on the dashboard page ( \"\u003ccmr_cyv_code\u003e\" ) with judgeName \"\u003cjudgeName\u003e\" and \"\u003cPE_RT_CODE\u003e\"",
  "rows": [
    {
      "cells": [
        "courtId"
      ],
      "line": 20
    },
    {
      "cells": [
        "CMKA"
      ],
      "line": 21
    }
  ],
  "keyword": "Then "
});
formatter.examples({
  "line": 23,
  "name": "",
  "description": "",
  "id": "number-of-cases-displayed-for-non-orally-argued-cases;;",
  "rows": [
    {
      "cells": [
        "judgeName",
        "cmr_cyv_code",
        "PE_RT_CODE"
      ],
      "line": 24,
      "id": "number-of-cases-displayed-for-non-orally-argued-cases;;;1"
    },
    {
      "cells": [
        "Colloton",
        "lbrrpt",
        "jud"
      ],
      "line": 25,
      "id": "number-of-cases-displayed-for-non-orally-argued-cases;;;2"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 7520860077,
  "status": "passed"
});
formatter.scenario({
  "line": 25,
  "name": "",
  "description": "Tapping on a referral category that is not orally argued (chm_reftype_val.cdv_is_oral_arg\u003d\u0027n\u0027),\n  a list of cases should dipslay for the judge for that category.\n  Need to verify the correct number of referrals are being displayed.",
  "id": "number-of-cases-displayed-for-non-orally-argued-cases;;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 1,
      "name": "@AMB-973"
    },
    {
      "line": 1,
      "name": "@S"
    },
    {
      "line": 1,
      "name": "@Smoke"
    }
  ]
});
formatter.step({
  "line": 11,
  "name": "I am logged into Briefcase",
  "rows": [
    {
      "cells": [
        "environment",
        "userName",
        "password",
        "courtId"
      ],
      "line": 12
    },
    {
      "cells": [
        "Integration",
        "s haenni",
        "Test2024!",
        "test"
      ],
      "line": 13
    }
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 15,
  "name": "I select a user",
  "rows": [
    {
      "cells": [
        "role",
        "briefcaseUser"
      ],
      "line": 16
    },
    {
      "cells": [
        "Appellate Judges",
        "Colloton"
      ],
      "line": 17
    }
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 19,
  "name": "User  Observes the categories on db and on the dashboard page ( \"lbrrpt\" ) with judgeName \"Colloton\" and \"jud\"",
  "matchedColumns": [
    0,
    1,
    2
  ],
  "rows": [
    {
      "cells": [
        "courtId"
      ],
      "line": 20
    },
    {
      "cells": [
        "CMKA"
      ],
      "line": 21
    }
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_am_logged_into_Briefcase(UserInputData\u003e)"
});
formatter.result({
  "duration": 71956259986,
  "status": "passed"
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_select_a_user(UserInputData\u003e)"
});
formatter.result({
  "duration": 12623113421,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "lbrrpt",
      "offset": 65
    },
    {
      "val": "Colloton",
      "offset": 91
    },
    {
      "val": "jud",
      "offset": 106
    }
  ],
  "location": "ReferralCategories_StepDefinitions.user_Observes_the_categories_on_db_and_on_the_dashboard_page_with_judgeName_and(String,String,String,UserInputData\u003e)"
});
formatter.result({
  "duration": 131078222780,
  "status": "passed"
});
formatter.after({
  "duration": 28721864934,
  "status": "passed"
});
});