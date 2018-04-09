$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("LoginPage/SmokeTest.feature");
formatter.feature({
  "line": 1,
  "name": "Login Action",
  "description": "",
  "id": "login-action",
  "keyword": "Feature"
});
formatter.scenario({
  "comments": [
    {
      "line": 2,
      "value": "#@Smoke"
    }
  ],
  "line": 3,
  "name": "Successful Login with Valid Credentials",
  "description": "",
  "id": "login-action;successful-login-with-valid-credentials",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": "User Navigates to Sever",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "And User enters Crdenetials to Login",
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "User clicks on Send Key to Device",
  "keyword": "And "
});
formatter.step({
  "line": 8,
  "name": "User navigates to MobileBrifcase App",
  "keyword": "Then "
});
formatter.step({
  "line": 9,
  "name": "After user navigates to Appellate DC Development - CMKA - dev",
  "keyword": "And "
});
formatter.match({
  "location": "Login_StepDefinitions.user_Navigate_to_Sever()"
});
formatter.result({
  "duration": 12393855757,
  "status": "passed"
});
formatter.match({
  "location": "Login_StepDefinitions.and_User_enters_Crdenetials_to_Login()"
});
formatter.result({
  "duration": 1504473109,
  "status": "passed"
});
formatter.match({
  "location": "Login_StepDefinitions.user_clicks_on_Send_Key_to_Device()"
});
formatter.result({
  "duration": 1492269985,
  "status": "passed"
});
formatter.match({
  "location": "Login_StepDefinitions.user_navigates_to_MobileBrifcase_App()"
});
formatter.result({
  "duration": 3572506694,
  "status": "passed"
});
formatter.match({
  "location": "Login_StepDefinitions.after_user_navigates_to_Appellate_DC_Development_CMKA_dev()"
});
formatter.result({
  "duration": 2631157645,
  "status": "passed"
});
});