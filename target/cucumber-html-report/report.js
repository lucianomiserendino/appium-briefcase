$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("CATEGORIES/Reg/NOTE_DPF_AMB-1055.feature");
formatter.feature({
  "line": 2,
  "name": "note DPF UI",
  "description": "",
  "id": "note-dpf-ui",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@Smoke"
    },
    {
      "line": 1,
      "name": "@AMB-1055"
    },
    {
      "line": 1,
      "name": "@S"
    }
  ]
});
formatter.scenarioOutline({
  "line": 7,
  "name": "",
  "description": "Verify when an action is selected that contains the note DPF, the note DPF UI displays.",
  "id": "note-dpf-ui;",
  "type": "scenario_outline",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 10,
  "name": "I am logged into Briefcase",
  "rows": [
    {
      "cells": [
        "environment",
        "userName",
        "password",
        "courtId"
      ],
      "line": 11
    },
    {
      "cells": [
        "Integration",
        "s haenni",
        "Test2024!",
        "test"
      ],
      "line": 12
    }
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 14,
  "name": "I select a user",
  "rows": [
    {
      "cells": [
        "role",
        "briefcaseUser"
      ],
      "line": 15
    },
    {
      "cells": [
        "Appellate Judges",
        "Colloton"
      ],
      "line": 16
    }
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 19,
  "name": "User selects \"\u003ccategory\u003e\" and \"\u003ccaseNum\u003e\"",
  "keyword": "Then "
});
formatter.step({
  "line": 20,
  "name": "User  selects action using \"\u003cel_id\u003e\"  and verifies the name of the action displays in the dark blue banner",
  "rows": [
    {
      "cells": [
        "courtId"
      ],
      "line": 21
    },
    {
      "cells": [
        "test"
      ],
      "line": 22
    }
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 24,
  "name": "User verifies the text \"Add New Note\" displays in the light blue banner.",
  "keyword": "Then "
});
formatter.step({
  "line": 25,
  "name": "user verifies an editable \"Description\" , \"Comment\" , \"Submit\" fields are  displayed.  The default description is defined in the Default description parameter of the note DPF (\"\u003cdbType\u003e\" , \"\u003cdpfName\u003e\", \"\u003cel_id\u003e\")",
  "keyword": "And "
});
formatter.examples({
  "line": 27,
  "name": "",
  "description": "",
  "id": "note-dpf-ui;;",
  "rows": [
    {
      "cells": [
        "category",
        "caseNum",
        "dbType",
        "el_id",
        "dpfName"
      ],
      "line": 28,
      "id": "note-dpf-ui;;;1"
    },
    {
      "cells": [
        "MOTION",
        "15-3703",
        "CMKA",
        "3153",
        "note"
      ],
      "line": 29,
      "id": "note-dpf-ui;;;2"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 8272558333,
  "status": "passed"
});
formatter.scenario({
  "line": 29,
  "name": "",
  "description": "Verify when an action is selected that contains the note DPF, the note DPF UI displays.",
  "id": "note-dpf-ui;;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 1,
      "name": "@S"
    },
    {
      "line": 1,
      "name": "@Smoke"
    },
    {
      "line": 1,
      "name": "@AMB-1055"
    }
  ]
});
formatter.step({
  "line": 10,
  "name": "I am logged into Briefcase",
  "rows": [
    {
      "cells": [
        "environment",
        "userName",
        "password",
        "courtId"
      ],
      "line": 11
    },
    {
      "cells": [
        "Integration",
        "s haenni",
        "Test2024!",
        "test"
      ],
      "line": 12
    }
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 14,
  "name": "I select a user",
  "rows": [
    {
      "cells": [
        "role",
        "briefcaseUser"
      ],
      "line": 15
    },
    {
      "cells": [
        "Appellate Judges",
        "Colloton"
      ],
      "line": 16
    }
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 19,
  "name": "User selects \"MOTION\" and \"15-3703\"",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 20,
  "name": "User  selects action using \"3153\"  and verifies the name of the action displays in the dark blue banner",
  "matchedColumns": [
    3
  ],
  "rows": [
    {
      "cells": [
        "courtId"
      ],
      "line": 21
    },
    {
      "cells": [
        "test"
      ],
      "line": 22
    }
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 24,
  "name": "User verifies the text \"Add New Note\" displays in the light blue banner.",
  "keyword": "Then "
});
formatter.step({
  "line": 25,
  "name": "user verifies an editable \"Description\" , \"Comment\" , \"Submit\" fields are  displayed.  The default description is defined in the Default description parameter of the note DPF (\"CMKA\" , \"note\", \"3153\")",
  "matchedColumns": [
    2,
    3,
    4
  ],
  "keyword": "And "
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_am_logged_into_Briefcase(UserInputData\u003e)"
});
formatter.result({
  "duration": 70500332336,
  "status": "passed"
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_select_a_user(UserInputData\u003e)"
});
formatter.result({
  "duration": 12706907201,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "MOTION",
      "offset": 14
    },
    {
      "val": "15-3703",
      "offset": 27
    }
  ],
  "location": "Common_StepDefinitions.user_selects_and(String,String)"
});
formatter.result({
  "duration": 23397168301,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "3153",
      "offset": 28
    }
  ],
  "location": "Common_StepDefinitions.user_selects_action_using_and_verifies_the_name_of_the_action_displays_in_the_dark_blue_banner(String,UserInputData\u003e)"
});
formatter.result({
  "duration": 79255256213,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Add New Note",
      "offset": 24
    }
  ],
  "location": "UIDocketingDPF_StepDefinitions.user_verifies_the_text_displays_in_the_light_blue_banner(String)"
});
formatter.result({
  "duration": 1235009521,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Description",
      "offset": 27
    },
    {
      "val": "Comment",
      "offset": 43
    },
    {
      "val": "Submit",
      "offset": 55
    },
    {
      "val": "CMKA",
      "offset": 177
    },
    {
      "val": "note",
      "offset": 186
    },
    {
      "val": "3153",
      "offset": 194
    }
  ],
  "location": "UIDocketingDPF_StepDefinitions.user_verifies_an_editable_fields_are_displayed_The_default_description_is_defined_in_the_Default_description_parameter_of_the_note_DPF(String,String,String,String,String,String)"
});
formatter.result({
  "duration": 5204384318,
  "status": "passed"
});
formatter.after({
  "duration": 32560667989,
  "status": "passed"
});
formatter.uri("CATEGORIES/Reg/NON-ORALLY_ARGUED_CASES_AMB-973.feature");
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
  "duration": 7390688745,
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
  "duration": 69988412347,
  "status": "passed"
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_select_a_user(UserInputData\u003e)"
});
formatter.result({
  "duration": 12630421906,
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
  "duration": 106788777524,
  "status": "passed"
});
formatter.after({
  "duration": 35734312574,
  "status": "passed"
});
formatter.uri("CATEGORIES/Reg/CHM_ASSIGN_DPF_AMB-1123 || AMB-1137 || AMB-1170 || AMB-1173.feature");
formatter.feature({
  "line": 2,
  "name": "chmAssign",
  "description": "",
  "id": "chmassign",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@Smoke"
    },
    {
      "line": 1,
      "name": "@AMB-1123"
    },
    {
      "line": 1,
      "name": "@AMB-1137"
    },
    {
      "line": 1,
      "name": "@AMB-1170"
    },
    {
      "line": 1,
      "name": "@AMB-1173"
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
  "description": "This task is to verify that a chambers user is able to create a new staff assignment,\nto verify back-end updates when a new staff assignment is created,\nedit existing staff assignments and verify Back-end after modifying assignment",
  "id": "chmassign;",
  "type": "scenario_outline",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 9,
  "name": "I am logged into Briefcase",
  "rows": [
    {
      "cells": [
        "environment",
        "userName",
        "password",
        "courtId"
      ],
      "line": 10
    },
    {
      "cells": [
        "Integration",
        "s haenni",
        "Test2024!",
        "test"
      ],
      "line": 11
    }
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 13,
  "name": "I select a user",
  "rows": [
    {
      "cells": [
        "role",
        "briefcaseUser"
      ],
      "line": 14
    },
    {
      "cells": [
        "Appellate Judges",
        "Colloton"
      ],
      "line": 15
    }
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 16,
  "name": "User selects \"\u003crefCat\u003e\" and \"\u003ccaseNum\u003e\"",
  "keyword": "Then "
});
formatter.step({
  "line": 18,
  "name": "User  selects action using \"\u003cel_id\u003e\"  and verifies the name of the action displays in the dark blue banner",
  "rows": [
    {
      "cells": [
        "courtId"
      ],
      "line": 19
    },
    {
      "cells": [
        "test"
      ],
      "line": 20
    }
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 21,
  "name": "user creates a new assignment, checks the back-end, edits existing assignment and checks the db  by using \"\u003cdbType\u003e\", \"\u003cdpfName\u003e\" , \"\u003cel_id\u003e\", \"\u003ccha_ju_pe_id\u003e\", \"\u003ccmr_cyv_code\u003e\", \"\u003ccmr_cs_caseid\u003e\", \"\u003ccaseNum\u003e\");",
  "keyword": "Then "
});
formatter.examples({
  "comments": [
    {
      "line": 22,
      "value": "#follow steps in @AMB-1123,@AMB-1137,@AMB-1170,@AMB-1173)"
    }
  ],
  "line": 25,
  "name": "",
  "description": "",
  "id": "chmassign;;",
  "rows": [
    {
      "cells": [
        "dbType",
        "el_id",
        "cha_ju_pe_id",
        "cmr_cs_caseid",
        "dbType",
        "cmr_cyv_code",
        "caseNum",
        "refCat",
        "dpfName"
      ],
      "line": 26,
      "id": "chmassign;;;1"
    },
    {
      "cells": [
        "CMKA",
        "3116",
        "32",
        "82226",
        "CMKA",
        "prhr",
        "15-3314",
        "PETITION",
        "chmAssign"
      ],
      "line": 27,
      "id": "chmassign;;;2"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 7394889584,
  "status": "passed"
});
formatter.scenario({
  "line": 27,
  "name": "",
  "description": "This task is to verify that a chambers user is able to create a new staff assignment,\nto verify back-end updates when a new staff assignment is created,\nedit existing staff assignments and verify Back-end after modifying assignment",
  "id": "chmassign;;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 1,
      "name": "@AMB-1123"
    },
    {
      "line": 1,
      "name": "@AMB-1137"
    },
    {
      "line": 1,
      "name": "@S"
    },
    {
      "line": 1,
      "name": "@Smoke"
    },
    {
      "line": 1,
      "name": "@AMB-1170"
    },
    {
      "line": 1,
      "name": "@AMB-1173"
    }
  ]
});
formatter.step({
  "line": 9,
  "name": "I am logged into Briefcase",
  "rows": [
    {
      "cells": [
        "environment",
        "userName",
        "password",
        "courtId"
      ],
      "line": 10
    },
    {
      "cells": [
        "Integration",
        "s haenni",
        "Test2024!",
        "test"
      ],
      "line": 11
    }
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 13,
  "name": "I select a user",
  "rows": [
    {
      "cells": [
        "role",
        "briefcaseUser"
      ],
      "line": 14
    },
    {
      "cells": [
        "Appellate Judges",
        "Colloton"
      ],
      "line": 15
    }
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 16,
  "name": "User selects \"PETITION\" and \"15-3314\"",
  "matchedColumns": [
    6,
    7
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 18,
  "name": "User  selects action using \"3116\"  and verifies the name of the action displays in the dark blue banner",
  "matchedColumns": [
    1
  ],
  "rows": [
    {
      "cells": [
        "courtId"
      ],
      "line": 19
    },
    {
      "cells": [
        "test"
      ],
      "line": 20
    }
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 21,
  "name": "user creates a new assignment, checks the back-end, edits existing assignment and checks the db  by using \"CMKA\", \"chmAssign\" , \"3116\", \"32\", \"prhr\", \"82226\", \"15-3314\");",
  "matchedColumns": [
    0,
    1,
    2,
    3,
    5,
    6,
    8
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_am_logged_into_Briefcase(UserInputData\u003e)"
});
formatter.result({
  "duration": 69991626669,
  "status": "passed"
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_select_a_user(UserInputData\u003e)"
});
formatter.result({
  "duration": 12754487848,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "PETITION",
      "offset": 14
    },
    {
      "val": "15-3314",
      "offset": 29
    }
  ],
  "location": "Common_StepDefinitions.user_selects_and(String,String)"
});
formatter.result({
  "duration": 19109096464,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "3116",
      "offset": 28
    }
  ],
  "location": "Common_StepDefinitions.user_selects_action_using_and_verifies_the_name_of_the_action_displays_in_the_dark_blue_banner(String,UserInputData\u003e)"
});
formatter.result({
  "duration": 57772546645,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "CMKA",
      "offset": 107
    },
    {
      "val": "chmAssign",
      "offset": 115
    },
    {
      "val": "3116",
      "offset": 129
    },
    {
      "val": "32",
      "offset": 137
    },
    {
      "val": "prhr",
      "offset": 143
    },
    {
      "val": "82226",
      "offset": 151
    },
    {
      "val": "15-3314",
      "offset": 160
    }
  ],
  "location": "CreateStaffAssignments_StepDefinitions.user_creates_a_new_assignment_checks_the_back_end_edits_existing_assignment_and_checks_the_db_by_using(String,String,String,String,String,String,String)"
});
formatter.result({
  "duration": 676293013450,
  "status": "passed"
});
formatter.after({
  "duration": 34171058278,
  "status": "passed"
});
formatter.uri("CATEGORIES/Reg/ACTIONS_PANEL_AMB-1038.feature");
formatter.feature({
  "line": 2,
  "name": "Display Actions Panel and actions",
  "description": "",
  "id": "display-actions-panel-and-actions",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@Smoke"
    },
    {
      "line": 1,
      "name": "@AMB-1038"
    },
    {
      "line": 1,
      "name": "@S"
    }
  ]
});
formatter.before({
  "duration": 7548041512,
  "status": "passed"
});
formatter.scenario({
  "line": 4,
  "name": "",
  "description": "\nIf there are records defined in the mbr_event table, \n     a collapsible panel entitled \"Actions\" should display, when expanded all the applicable actions should display.",
  "id": "display-actions-panel-and-actions;",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 9,
  "name": "I am logged into Briefcase",
  "rows": [
    {
      "cells": [
        "environment",
        "userName",
        "password",
        "courtId"
      ],
      "line": 10
    },
    {
      "cells": [
        "Integration",
        "s haenni",
        "Test2024!",
        "test"
      ],
      "line": 11
    }
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 13,
  "name": "I select a user",
  "rows": [
    {
      "cells": [
        "role",
        "briefcaseUser"
      ],
      "line": 14
    },
    {
      "cells": [
        "Appellate Judges",
        "Colloton"
      ],
      "line": 15
    }
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 17,
  "name": "User selects \"MOTION\" and \"15-3314\"",
  "keyword": "Then "
});
formatter.step({
  "line": 18,
  "name": "User verifies the correct \"Actions\" display for the selected referral",
  "rows": [
    {
      "cells": [
        "courtId",
        "cmr_id"
      ],
      "line": 20
    },
    {
      "cells": [
        "test",
        "2310499"
      ],
      "line": 21
    }
  ],
  "keyword": "And "
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_am_logged_into_Briefcase(UserInputData\u003e)"
});
formatter.result({
  "duration": 70448501033,
  "status": "passed"
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_select_a_user(UserInputData\u003e)"
});
formatter.result({
  "duration": 12625832889,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "MOTION",
      "offset": 14
    },
    {
      "val": "15-3314",
      "offset": 27
    }
  ],
  "location": "Common_StepDefinitions.user_selects_and(String,String)"
});
formatter.result({
  "duration": 19245523211,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Actions",
      "offset": 27
    }
  ],
  "location": "ActionsPanel_StepDefinitions.user_verifies_the_correct_display_for_the_selected_referral(String,DataTable)"
});
formatter.result({
  "duration": 56189130349,
  "status": "passed"
});
formatter.after({
  "duration": 35836611735,
  "status": "passed"
});
formatter.uri("CATEGORIES/Reg/NON_CASE_RELATED_DOCUMENTS_AMB-1207.feature");
formatter.feature({
  "line": 2,
  "name": "Non case related docs - Suppress case number for \u0027lbrrpt\u0027 category",
  "description": "",
  "id": "non-case-related-docs---suppress-case-number-for-\u0027lbrrpt\u0027-category",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@Smoke"
    },
    {
      "line": 1,
      "name": "@AMB-1207"
    },
    {
      "line": 1,
      "name": "@S"
    }
  ]
});
formatter.before({
  "duration": 7648856992,
  "status": "passed"
});
formatter.scenario({
  "line": 5,
  "name": "",
  "description": "User shouldn\u0027t see  case number when view documents that are not case related",
  "id": "non-case-related-docs---suppress-case-number-for-\u0027lbrrpt\u0027-category;",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 7,
  "name": "I am logged into Briefcase",
  "rows": [
    {
      "cells": [
        "environment",
        "userName",
        "password",
        "courtId"
      ],
      "line": 8
    },
    {
      "cells": [
        "Integration",
        "s haenni",
        "Test2024!",
        "test"
      ],
      "line": 9
    }
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 11,
  "name": "I select a user",
  "rows": [
    {
      "cells": [
        "role",
        "briefcaseUser"
      ],
      "line": 12
    },
    {
      "cells": [
        "Appellate Judges",
        "Colloton"
      ],
      "line": 13
    }
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 14,
  "name": "In \"CMKA\" , If the chm_mobile_referral.cmr_cyv_code \u003d \"lbrrpt\" , verify  cyv_category  displays on the Dashboard page. Verify case number don\u0027t display for  referrals where the chm_mobile_referral.cmr_cyv_code \u003d \u0027lbrrpt\u0027, Verify only  documents display the referral detail page. Verify any actions, assignment, or additional case information don\u0027t  display.Use PE_RT_CODE \"jud\" and judge \"Colloton\"",
  "keyword": "Given "
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_am_logged_into_Briefcase(UserInputData\u003e)"
});
formatter.result({
  "duration": 74194180754,
  "status": "passed"
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_select_a_user(UserInputData\u003e)"
});
formatter.result({
  "duration": 12410170614,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "CMKA",
      "offset": 4
    },
    {
      "val": "lbrrpt",
      "offset": 55
    },
    {
      "val": "jud",
      "offset": 373
    },
    {
      "val": "Colloton",
      "offset": 389
    }
  ],
  "location": "ReferralCategories_StepDefinitions.in_If_the_chm_mobile_referral_cmr_cyv_code_verify_cyv_category_displays_on_the_Dashboard_page_Verify_case_number_don_t_display_for_referrals_where_the_chm_mobile_referral_cmr_cyv_code_lbrrpt_Verify_only_documents_display_the_referral_detail_page_Verify_any_actions_assignment_or_additional_case_information_don_t_display_Use_PE_RT_CODE_and_judge(String,String,String,String)"
});
formatter.result({
  "duration": 18601647020,
  "status": "passed"
});
formatter.after({
  "duration": 32442215757,
  "status": "passed"
});
});