$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("CATEGORIES/WILDCARD_SEARCH_AMB-1247.feature");
formatter.feature({
  "line": 2,
  "name": "Case Query -- return case list for wildcard searches",
  "description": "",
  "id": "case-query----return-case-list-for-wildcard-searches",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@t"
    },
    {
      "line": 1,
      "name": "@AMB"
    },
    {
      "line": 1,
      "name": "@Regression"
    },
    {
      "line": 1,
      "name": "@AMB-1247"
    }
  ]
});
formatter.scenario({
  "line": 4,
  "name": "",
  "description": "\nThis task is to verify  the app returns a result after performing a valid wildcard case search",
  "id": "case-query----return-case-list-for-wildcard-searches;",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 8,
  "name": "I am logged into Briefcase",
  "rows": [
    {
      "cells": [
        "environment",
        "userName",
        "password",
        "courtId"
      ],
      "line": 9
    },
    {
      "cells": [
        "test",
        "test",
        "test",
        "test"
      ],
      "line": 10
    }
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 12,
  "name": "I select a user",
  "rows": [
    {
      "cells": [
        "userType",
        "personrole",
        "jud"
      ],
      "line": 13
    },
    {
      "cells": [
        "judge",
        "Appellate Judges",
        "test"
      ],
      "line": 14
    }
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 16,
  "name": "User taps on magnifying glass icon and searches for a case and  verifies the result if not empty",
  "rows": [
    {
      "cells": [
        "caseNumber"
      ],
      "line": 17
    },
    {
      "cells": [
        "test"
      ],
      "line": 18
    }
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_am_logged_into_Briefcase(UserInputData\u003e)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_select_a_user(UserInputData\u003e)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
});