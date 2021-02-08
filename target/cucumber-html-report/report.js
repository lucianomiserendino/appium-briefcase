$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("CATEGORIES/QUICK_DB_CONNECTION_TEST.feature");
formatter.feature({
  "line": 1,
  "name": "Verify Connection To DB Can Be Established",
  "description": "",
  "id": "verify-connection-to-db-can-be-established",
  "keyword": "Feature"
});
formatter.before({
  "duration": 9686435973,
  "status": "passed"
});
formatter.scenario({
  "line": 3,
  "name": "Verify Connection To Informix",
  "description": "",
  "id": "verify-connection-to-db-can-be-established;verify-connection-to-informix",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 2,
      "name": "@db_Check"
    }
  ]
});
formatter.step({
  "line": 4,
  "name": "Verify DB Informix Connection Is Established",
  "rows": [
    {
      "cells": [
        "courtId",
        "db_servername",
        "hostname",
        "db_port",
        "db_Schema",
        "db_username",
        "db_password"
      ],
      "line": 5
    },
    {
      "cells": [
        "test",
        "test",
        "test",
        "test",
        "test",
        "test",
        "test"
      ],
      "line": 6
    }
  ],
  "keyword": "And "
});
formatter.match({
  "location": "Common_StepDefinitions.verify_DB_Informix_Connection_Is_Established(UserInputData\u003e)"
});
formatter.result({
  "duration": 721321386,
  "status": "passed"
});
});