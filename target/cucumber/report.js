$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("NonOrallyArguedCases/AMB_973.feature");
formatter.feature({
  "line": 1,
  "name": "Number of cases displayed for non-orally argued cases",
  "description": "",
  "id": "number-of-cases-displayed-for-non-orally-argued-cases",
  "keyword": "Feature"
});
formatter.background({
  "line": 4,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 5,
  "name": "User Navigates to Sever",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "User enters Crdenetials to Login",
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
  "duration": 16020886286,
  "status": "passed"
});
formatter.match({
  "location": "Login_StepDefinitions.and_User_enters_Crdenetials_to_Login()"
});
formatter.result({
  "duration": 1304465807,
  "status": "passed"
});
formatter.match({
  "location": "Login_StepDefinitions.user_clicks_on_Send_Key_to_Device()"
});
formatter.result({
  "duration": 1042682238,
  "status": "passed"
});
formatter.match({
  "location": "Login_StepDefinitions.user_navigates_to_MobileBrifcase_App()"
});
formatter.result({
  "duration": 3388623334,
  "status": "passed"
});
formatter.match({
  "location": "Login_StepDefinitions.after_user_navigates_to_Appellate_DC_Development_CMKA_dev()"
});
formatter.result({
  "duration": 2447904277,
  "status": "passed"
});
formatter.scenario({
  "line": 12,
  "name": "",
  "description": "Tapping on a referral category that is not orally argued (chm_reftype_val.cdv_is_oral_arg\u003d\u0027n\u0027),\n  a list of cases should dipslay for the judge for that category.\n  Need to verify the correct number of referrals are being displayed.",
  "id": "number-of-cases-displayed-for-non-orally-argued-cases;",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 11,
      "name": "@Smoke"
    }
  ]
});
formatter.step({
  "line": 17,
  "name": "User obtains the judge\u0027s pe_id on the Dashboard Page",
  "keyword": "When "
});
formatter.step({
  "line": 18,
  "name": "finds the valid non-orally argued categories for the judge",
  "keyword": "Then "
});
formatter.match({
  "location": "NonOrallyArguedCases_StepDefinitions.user_obtains_the_judge_s_pe_id_on_the_Dashboard_Page()"
});
formatter.result({
  "duration": 35116,
  "status": "passed"
});
formatter.match({
  "location": "NonOrallyArguedCases_StepDefinitions.finds_the_valid_non_orally_argued_categories_for_the_judge()"
});
formatter.result({
  "duration": 11589,
  "status": "passed"
});
});