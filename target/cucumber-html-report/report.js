$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("CATEGORIES/SMOKE/AMB-1120.feature");
formatter.feature({
  "line": 2,
  "name": "Log in as a staff attorney and verify data is displayed.",
  "description": "",
  "id": "log-in-as-a-staff-attorney-and-verify-data-is-displayed.",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@Smoke"
    },
    {
      "line": 1,
      "name": "@AMB-1120"
    }
  ]
});
formatter.before({
  "duration": 10955748042,
  "status": "passed"
});
formatter.scenario({
  "line": 6,
  "name": "Log in as a staff attorney and verify data is displayed.",
  "description": "",
  "id": "log-in-as-a-staff-attorney-and-verify-data-is-displayed.;log-in-as-a-staff-attorney-and-verify-data-is-displayed.",
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
        "Integration",
        "KristenStaffAttorney",
        "Test2027!",
        "test"
      ],
      "line": 10
    }
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 11,
  "name": "User verifies Data is displayed on the Dashboard, retrieves categories from db ,\u0027RA_PE_ID\u0027 : \"434\"",
  "rows": [
    {
      "cells": [
        "courtId"
      ],
      "line": 12
    },
    {
      "cells": [
        "test"
      ],
      "line": 13
    }
  ],
  "keyword": "Given "
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_am_logged_into_Briefcase(UserInputData\u003e)"
});
formatter.result({
  "duration": 71703677413,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "434",
      "offset": 94
    }
  ],
  "location": "StaffAttorney_StepDefinitions.user_verifies_Data_is_displayed_on_the_Dashboard_retrieves_categories_from_db_RA_PE_ID(String,UserInputData\u003e)"
});
formatter.result({
  "duration": 7340832114,
  "status": "passed"
});
formatter.after({
  "duration": 28528102379,
  "status": "passed"
});
formatter.uri("CATEGORIES/SMOKE/APPLIED_CASES_LINK_AMB-1241.feature");
formatter.feature({
  "line": 2,
  "name": "Applied Cases link",
  "description": "",
  "id": "applied-cases-link",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@Smoke"
    },
    {
      "line": 1,
      "name": "@AMB-1241"
    }
  ]
});
formatter.before({
  "duration": 9809463441,
  "status": "passed"
});
formatter.scenario({
  "line": 5,
  "name": "",
  "description": "Display Applied Cases link  when bookmarking case/referral",
  "id": "applied-cases-link;",
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
  "line": 15,
  "name": "user checks if \"15-2622\" is bookmarked",
  "keyword": "Then "
});
formatter.step({
  "line": 16,
  "name": "User selects a  \"Cases on Calendar\"",
  "keyword": "When "
});
formatter.step({
  "line": 17,
  "name": "User selects \"Feb 09, 2016 - Feb 10, 2016\" for panel \"LRS*, SMC, RRE\"",
  "keyword": "And "
});
formatter.step({
  "line": 18,
  "name": "User verifies there is a link icon next to case \"15-2622\" . Selects that case and bookmark the case on the referral document page",
  "keyword": "Then "
});
formatter.step({
  "line": 19,
  "name": "User  goes back to the \"Cases on Calendar\" page for \"Feb 09, 2016 - Feb 10, 2016\" for panel \"LRS*, SMC, RRE\" and verifies the link is there.",
  "keyword": "Then "
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_am_logged_into_Briefcase(UserInputData\u003e)"
});
formatter.result({
  "duration": 70545512256,
  "status": "passed"
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_select_a_user(UserInputData\u003e)"
});
formatter.result({
  "duration": 15476749962,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "15-2622",
      "offset": 16
    }
  ],
  "location": "AppliedCases_StepDefinitions.user_checks_if_is_bookmarked(String)"
});
formatter.result({
  "duration": 2127830936,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Cases on Calendar",
      "offset": 17
    }
  ],
  "location": "Common_StepDefinitions.user_selects_a(String)"
});
formatter.result({
  "duration": 9780621186,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Feb 09, 2016 - Feb 10, 2016",
      "offset": 14
    },
    {
      "val": "LRS*, SMC, RRE",
      "offset": 54
    }
  ],
  "location": "AppliedCases_StepDefinitions.user_selects_for_panel(String,String)"
});
formatter.result({
  "duration": 2995487528,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "15-2622",
      "offset": 49
    }
  ],
  "location": "AppliedCases_StepDefinitions.user_verifies_there_is_a_link_icon_next_to_case_Selects_that_case_and_bookmark_the_case_on_the_referral_document_page(String)"
});
formatter.result({
  "duration": 15159855227,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Cases on Calendar",
      "offset": 24
    },
    {
      "val": "Feb 09, 2016 - Feb 10, 2016",
      "offset": 53
    },
    {
      "val": "LRS*, SMC, RRE",
      "offset": 93
    }
  ],
  "location": "AppliedCases_StepDefinitions.user_goes_back_to_the_page_for_for_panel_and_verifies_the_link_is_there(String,String,String)"
});
formatter.result({
  "duration": 8093504707,
  "status": "passed"
});
formatter.after({
  "duration": 31796707515,
  "status": "passed"
});
formatter.uri("CATEGORIES/SMOKE/ASSIGNMENT_CATEGORIES_AMB-1047.feature");
formatter.feature({
  "line": 2,
  "name": "Assignment categories display on the dashboard for Staff Attorneys",
  "description": "",
  "id": "assignment-categories-display-on-the-dashboard-for-staff-attorneys",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@Smoke"
    },
    {
      "line": 1,
      "name": "@AMB-1047"
    }
  ]
});
formatter.before({
  "duration": 9062032625,
  "status": "passed"
});
formatter.scenario({
  "line": 6,
  "name": "",
  "description": "Staff attorney assignments are court definable in the stfaty_assign_val table. \nStaff attorney referrals are stored in the stfaty_mobile_referral table. There is a FK to the sftaty_assign_val table (smr_sfa_code). \n This is how the assignment category is obtained.",
  "id": "assignment-categories-display-on-the-dashboard-for-staff-attorneys;",
  "type": "scenario",
  "keyword": "Scenario"
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
        "Staff Attorneys",
        "Brown, Benjamin"
      ],
      "line": 16
    }
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 17,
  "name": "User verifies Data is displayed on the Dashboard, retrieves categories from db ,\u0027RA_PE_ID\u0027 : \"434\"",
  "rows": [
    {
      "cells": [
        "courtId"
      ],
      "line": 18
    },
    {
      "cells": [
        "test"
      ],
      "line": 19
    }
  ],
  "keyword": "Given "
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_am_logged_into_Briefcase(UserInputData\u003e)"
});
formatter.result({
  "duration": 69700816950,
  "status": "passed"
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_select_a_user(UserInputData\u003e)"
});
formatter.result({
  "duration": 16963956745,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "434",
      "offset": 94
    }
  ],
  "location": "StaffAttorney_StepDefinitions.user_verifies_Data_is_displayed_on_the_Dashboard_retrieves_categories_from_db_RA_PE_ID(String,UserInputData\u003e)"
});
formatter.result({
  "duration": 6144111095,
  "status": "passed"
});
formatter.after({
  "duration": 19672075542,
  "status": "passed"
});
formatter.uri("CATEGORIES/SMOKE/BADGE_NUMBERS-AMB-1230.feature");
formatter.feature({
  "line": 2,
  "name": "Badge numbers in the navigation",
  "description": "",
  "id": "badge-numbers-in-the-navigation",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@Smoke"
    },
    {
      "line": 1,
      "name": "@AMB"
    },
    {
      "line": 1,
      "name": "@AMB-1230"
    }
  ]
});
formatter.before({
  "duration": 9050694537,
  "status": "passed"
});
formatter.scenario({
  "line": 5,
  "name": "",
  "description": "Verify Badge numbers in the navigation",
  "id": "badge-numbers-in-the-navigation;",
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
  "line": 10,
  "name": "I select a user",
  "rows": [
    {
      "cells": [
        "role",
        "briefcaseUser"
      ],
      "line": 11
    },
    {
      "cells": [
        "Appellate Judges",
        "Colloton"
      ],
      "line": 12
    }
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 13,
  "name": "Verify the number of new items that displays in the red badge in the navigation match the number of new items listed on the Dashboard page.",
  "keyword": "Given "
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_am_logged_into_Briefcase(UserInputData\u003e)"
});
formatter.result({
  "duration": 70787370429,
  "status": "passed"
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_select_a_user(UserInputData\u003e)"
});
formatter.result({
  "duration": 14881417492,
  "status": "passed"
});
formatter.match({
  "location": "ReferralCategories_StepDefinitions.verify_the_number_of_new_items_that_displays_in_the_red_badge_in_the_navigation_match_the_number_of_new_items_listed_on_the_Dashboard_page()"
});
formatter.result({
  "duration": 1357241885,
  "status": "passed"
});
formatter.after({
  "duration": 42519175258,
  "status": "passed"
});
formatter.uri("CATEGORIES/SMOKE/BOOKMARK_REFERRALS_AMB-1015.feature");
formatter.feature({
  "line": 2,
  "name": "Bookmark functionality",
  "description": "",
  "id": "bookmark-functionality",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@Smoke"
    },
    {
      "line": 1,
      "name": "@AMB-1015"
    }
  ]
});
formatter.before({
  "duration": 9152512755,
  "status": "passed"
});
formatter.scenario({
  "line": 4,
  "name": "",
  "description": "There is functionality in Briefcase that enables users to bookmark referrals.  \nThe following scenarios need to be automated:",
  "id": "bookmark-functionality;",
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
  "line": 12,
  "name": "I select a user",
  "rows": [
    {
      "cells": [
        "role",
        "briefcaseUser"
      ],
      "line": 13
    },
    {
      "cells": [
        "Appellate Judges",
        "Colloton"
      ],
      "line": 14
    }
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 15,
  "name": "User verifies there\u0027s no bookmark icon displays in the navigation and on the dashboard",
  "keyword": "Then "
});
formatter.step({
  "line": 16,
  "name": "User selects a  \"Test Automation\"",
  "keyword": "When "
});
formatter.step({
  "line": 17,
  "name": "user  taps on the bookmark icon next to a case  and verifies the bookmark icon displays in the navigation and on the dashboard page.",
  "keyword": "Then "
});
formatter.step({
  "line": 18,
  "name": "user taps on bookmark icon in the navigation or on the dashboard then taps on the bookmark icon next to the case he just bookmarked and verifies the case is removed from the bookmark category",
  "keyword": "And "
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_am_logged_into_Briefcase(UserInputData\u003e)"
});
formatter.result({
  "duration": 71389529207,
  "status": "passed"
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_select_a_user(UserInputData\u003e)"
});
formatter.result({
  "duration": 14806491140,
  "status": "passed"
});
formatter.match({
  "location": "BookmarkedList_stepDefinitions.user_verifies_there_s_no_bookmark_icon_displays_in_the_navigation_and_on_the_dashboard()"
});
formatter.result({
  "duration": 12086879864,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Test Automation",
      "offset": 17
    }
  ],
  "location": "Common_StepDefinitions.user_selects_a(String)"
});
formatter.result({
  "duration": 2500080362,
  "status": "passed"
});
formatter.match({
  "location": "BookmarkedList_stepDefinitions.user_taps_on_the_bookmark_icon_next_to_a_case_and_verifies_the_bookmark_icon_displays_in_the_navigation_and_on_the_dashboard_page()"
});
formatter.result({
  "duration": 9301359299,
  "status": "passed"
});
formatter.match({
  "location": "BookmarkedList_stepDefinitions.user_taps_on_bookmark_icon_in_the_navigation_or_on_the_dashboard_then_taps_on_the_bookmark_icon_next_to_the_case_he_just_bookmarked_and_verifies_the_case_is_removed_from_the_bookmark_category()"
});
formatter.result({
  "duration": 10534655741,
  "error_message": "java.lang.AssertionError: expected:\u003c0\u003e but was:\u003c1\u003e\n\tat org.junit.Assert.fail(Assert.java:88)\n\tat org.junit.Assert.failNotEquals(Assert.java:834)\n\tat org.junit.Assert.assertEquals(Assert.java:645)\n\tat org.junit.Assert.assertEquals(Assert.java:631)\n\tat gov.uscourts.ao.mobileBriefcase.Pages.BookmarkedListPage.bookmarkReferral(BookmarkedListPage.java:70)\n\tat gov.uscourts.ao.mobileBriefcase.Pages.BookmarkedListPage.removeBookmarkedReferral(BookmarkedListPage.java:63)\n\tat gov.uscourts.ao.mobileBriefcase.stepDefinitions.BookmarkedList_stepDefinitions.user_taps_on_bookmark_icon_in_the_navigation_or_on_the_dashboard_then_taps_on_the_bookmark_icon_next_to_the_case_he_just_bookmarked_and_verifies_the_case_is_removed_from_the_bookmark_category(BookmarkedList_stepDefinitions.java:22)\n\tat ✽.And user taps on bookmark icon in the navigation or on the dashboard then taps on the bookmark icon next to the case he just bookmarked and verifies the case is removed from the bookmark category(CATEGORIES/SMOKE/BOOKMARK_REFERRALS_AMB-1015.feature:18)\n",
  "status": "failed"
});
formatter.after({
  "duration": 76613431312,
  "error_message": "org.openqa.selenium.NoSuchElementException: Can\u0027t locate an element by this strategy: By.chained({By.xpath: //XCUIElementTypeStaticText[@name\u003d\u0027Logout of Briefcase\u0027]})\n\tat io.appium.java_client.pagefactory.AppiumElementLocator.findElement(AppiumElementLocator.java:126)\n\tat io.appium.java_client.pagefactory.interceptors.InterceptorOfASingleElement.intercept(InterceptorOfASingleElement.java:60)\n\tat io.appium.java_client.ios.IOSElement$$EnhancerByCGLIB$$d311658.click(\u003cgenerated\u003e)\n\tat gov.uscourts.ao.mobileBriefcase.Pages.JenieLoginPage.logout(JenieLoginPage.java:196)\n\tat gov.uscourts.ao.mobileBriefcase.stepDefinitions.Hook.tearDown(Hook.java:19)\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n\tat java.lang.reflect.Method.invoke(Method.java:498)\n\tat cucumber.runtime.Utils$1.call(Utils.java:40)\n\tat cucumber.runtime.Timeout.timeout(Timeout.java:16)\n\tat cucumber.runtime.Utils.invoke(Utils.java:34)\n\tat cucumber.runtime.java.JavaHookDefinition.execute(JavaHookDefinition.java:60)\n\tat cucumber.runtime.Runtime.runHookIfTagsMatch(Runtime.java:224)\n\tat cucumber.runtime.Runtime.runHooks(Runtime.java:212)\n\tat cucumber.runtime.Runtime.runAfterHooks(Runtime.java:206)\n\tat cucumber.runtime.model.CucumberScenario.run(CucumberScenario.java:46)\n\tat cucumber.runtime.junit.ExecutionUnitRunner.run(ExecutionUnitRunner.java:102)\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:63)\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:18)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.runtime.junit.FeatureRunner.run(FeatureRunner.java:70)\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:95)\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:38)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.api.junit.Cucumber.run(Cucumber.java:100)\n\tat org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:86)\n\tat org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:538)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:760)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:460)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:206)\nCaused by: org.openqa.selenium.TimeoutException: Expected condition failed: waiting for io.appium.java_client.pagefactory.AppiumElementLocator$WaitingFunction@bb25753 (tried for 1 second(s) with 500 milliseconds interval)\n\tat org.openqa.selenium.support.ui.FluentWait.timeoutException(FluentWait.java:304)\n\tat org.openqa.selenium.support.ui.FluentWait.until(FluentWait.java:272)\n\tat io.appium.java_client.pagefactory.AppiumElementLocator.waitFor(AppiumElementLocator.java:99)\n\tat io.appium.java_client.pagefactory.AppiumElementLocator.findElement(AppiumElementLocator.java:119)\n\t... 41 more\nCaused by: org.openqa.selenium.NoSuchElementException: Cannot locate an element using By.chained({By.xpath: //XCUIElementTypeStaticText[@name\u003d\u0027Logout of Briefcase\u0027]})\nFor documentation on this error, please visit: https://www.seleniumhq.org/exceptions/no_such_element.html\nBuild info: version: \u00273.141.59\u0027, revision: \u0027e82be7d358\u0027, time: \u00272018-11-14T08:17:03\u0027\nSystem info: host: \u0027Saltanat-MacBook-Pro.local\u0027, ip: \u0027fe80:0:0:0:4d9:a410:10f1:91cf%en0\u0027, os.name: \u0027Mac OS X\u0027, os.arch: \u0027x86_64\u0027, os.version: \u002710.15.7\u0027, java.version: \u00271.8.0_162\u0027\nDriver info: driver.version: IOSDriver\n\tat io.appium.java_client.pagefactory.bys.builder.ByChained.findElement(ByChained.java:74)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:315)\n\tat io.appium.java_client.DefaultGenericMobileDriver.findElement(DefaultGenericMobileDriver.java:58)\n\tat io.appium.java_client.AppiumDriver.findElement(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.findElement(IOSDriver.java:1)\n\tat io.appium.java_client.pagefactory.bys.ContentMappedBy.findElement(ContentMappedBy.java:50)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:315)\n\tat io.appium.java_client.DefaultGenericMobileDriver.findElement(DefaultGenericMobileDriver.java:58)\n\tat io.appium.java_client.AppiumDriver.findElement(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.findElement(IOSDriver.java:1)\n\tat io.appium.java_client.pagefactory.AppiumElementLocator.lambda$0(AppiumElementLocator.java:120)\n\tat io.appium.java_client.pagefactory.AppiumElementLocator$WaitingFunction.apply(AppiumElementLocator.java:172)\n\tat io.appium.java_client.pagefactory.AppiumElementLocator$WaitingFunction.apply(AppiumElementLocator.java:1)\n\tat org.openqa.selenium.support.ui.FluentWait.until(FluentWait.java:249)\n\t... 43 more\n",
  "status": "failed"
});
formatter.uri("CATEGORIES/SMOKE/CALENDARED_CASES_AMB-1249.feature");
formatter.feature({
  "line": 2,
  "name": "Display correct days for calendared cases",
  "description": "",
  "id": "display-correct-days-for-calendared-cases",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@Smoke"
    },
    {
      "line": 1,
      "name": "@AMB-1249"
    }
  ]
});
formatter.before({
  "duration": 10635298610,
  "status": "passed"
});
formatter.scenario({
  "line": 4,
  "name": "",
  "description": "Verify  that days  for calendared cases are displayed correctly",
  "id": "display-correct-days-for-calendared-cases;",
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
  "name": "User selects a  \"Cases on\"",
  "keyword": "When "
});
formatter.step({
  "line": 15,
  "name": "User selects a session and verifies days are displayed corrcetly in that session, judge\u0027s peID is \"32\"",
  "rows": [
    {
      "cells": [
        "courtId"
      ],
      "line": 16
    },
    {
      "cells": [
        "test"
      ],
      "line": 17
    }
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_am_logged_into_Briefcase(UserInputData\u003e)"
});
formatter.result({
  "duration": 73960380506,
  "status": "passed"
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_select_a_user(UserInputData\u003e)"
});
formatter.result({
  "duration": 13844680810,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Cases on",
      "offset": 17
    }
  ],
  "location": "Common_StepDefinitions.user_selects_a(String)"
});
formatter.result({
  "duration": 8737181073,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "32",
      "offset": 99
    }
  ],
  "location": "CalendaredCases_StepDefinitions.user_selects_a_session_and_verifies_days_are_displayed_corrcetly_in_that_session_judge_s_peID_is(String,UserInputData\u003e)"
});
formatter.result({
  "duration": 32163778528,
  "status": "passed"
});
formatter.after({
  "duration": 35873481873,
  "status": "passed"
});
formatter.uri("CATEGORIES/SMOKE/DB_DOCKETING_DPF_AMB-1075.feature");
formatter.feature({
  "line": 2,
  "name": "note DPF back-end updates",
  "description": "",
  "id": "note-dpf-back-end-updates",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@AMB"
    },
    {
      "line": 1,
      "name": "@AMB-1075"
    },
    {
      "line": 1,
      "name": "@Smoke"
    }
  ]
});
formatter.before({
  "duration": 8408288014,
  "status": "passed"
});
formatter.scenario({
  "line": 4,
  "name": "",
  "description": "Adding a note back-end database updates.",
  "id": "note-dpf-back-end-updates;",
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
  "line": 15,
  "name": "User selects \"TEST_AUTOMATION\" and \"15-2594\"",
  "keyword": "Then "
});
formatter.step({
  "line": 16,
  "name": "User selecs action, enters a comment in the editable field, submits and verifies Db \"CMKA\" is updated correctly, ( use \"15-2594\"  and \"32\" )",
  "rows": [
    {
      "cells": [
        "el_list_text",
        "dm_acc_crt",
        "dm_acc_ctlink",
        "dm_acc_spec"
      ],
      "line": 18
    },
    {
      "cells": [
        "3060",
        "y",
        "n",
        "n"
      ],
      "line": 19
    },
    {
      "cells": [
        "3070",
        "n",
        "y",
        "n"
      ],
      "line": 20
    },
    {
      "cells": [
        "3128",
        "n",
        "n",
        "y"
      ],
      "line": 21
    },
    {
      "cells": [
        "3118",
        "n",
        "n",
        "y"
      ],
      "line": 22
    },
    {
      "cells": [
        "3074",
        "n",
        "n",
        "y"
      ],
      "line": 23
    },
    {
      "cells": [
        "3127",
        "n",
        "n",
        "y"
      ],
      "line": 24
    },
    {
      "cells": [
        "3072",
        "n",
        "n",
        "y"
      ],
      "line": 25
    }
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_am_logged_into_Briefcase(UserInputData\u003e)"
});
formatter.result({
  "duration": 71998407090,
  "status": "passed"
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_select_a_user(UserInputData\u003e)"
});
formatter.result({
  "duration": 13956177706,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "TEST_AUTOMATION",
      "offset": 14
    },
    {
      "val": "15-2594",
      "offset": 36
    }
  ],
  "location": "Common_StepDefinitions.user_selects_and(String,String)"
});
formatter.result({
  "duration": 13832250365,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "CMKA",
      "offset": 85
    },
    {
      "val": "15-2594",
      "offset": 120
    },
    {
      "val": "32",
      "offset": 135
    }
  ],
  "location": "DBDocketingDPF_StepDefinitions.user_selecs_action_enters_a_comment_in_the_editable_field_submits_and_verifies_Db_is_updated_correctly_use_and(String,String,String,ElListText\u003e)"
});
formatter.result({
  "duration": 899586147430,
  "status": "passed"
});
formatter.after({
  "duration": 32562068807,
  "status": "passed"
});
formatter.uri("CATEGORIES/SMOKE/DOCKET_ENTRIES_AMB-1234.feature");
formatter.feature({
  "line": 2,
  "name": "Docket Entries for Chambers Users",
  "description": "",
  "id": "docket-entries-for-chambers-users",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@Smoke"
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
      "name": "@AMB-1234"
    }
  ]
});
formatter.before({
  "duration": 10496215792,
  "status": "passed"
});
formatter.scenario({
  "line": 4,
  "name": "",
  "description": "",
  "id": "docket-entries-for-chambers-users;",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 6,
  "name": "I am logged into Briefcase",
  "rows": [
    {
      "cells": [
        "environment",
        "userName",
        "password",
        "courtId"
      ],
      "line": 7
    },
    {
      "cells": [
        "Integration",
        "s haenni",
        "Test2024!",
        "test"
      ],
      "line": 8
    }
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 9,
  "name": "I select a user",
  "rows": [
    {
      "cells": [
        "role",
        "briefcaseUser"
      ],
      "line": 10
    },
    {
      "cells": [
        "Appellate Judges",
        "Benton"
      ],
      "line": 11
    }
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 12,
  "name": "user gets the entries of the judge ( \"15-2594\" ) and logs out",
  "keyword": "Then "
});
formatter.step({
  "line": 14,
  "name": "I am logged into Briefcase",
  "rows": [
    {
      "cells": [
        "environment",
        "userName",
        "password",
        "courtId"
      ],
      "line": 15
    },
    {
      "cells": [
        "Integration",
        "JAHaenni",
        "Test2025!",
        "test"
      ],
      "line": 16
    }
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 18,
  "name": "user verifies a JA or law clerk can see the same entries as their judge  ( \"15-2594\" )",
  "keyword": "Then "
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_am_logged_into_Briefcase(UserInputData\u003e)"
});
formatter.result({
  "duration": 70558301272,
  "status": "passed"
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_select_a_user(UserInputData\u003e)"
});
formatter.result({
  "duration": 13241254386,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "15-2594",
      "offset": 38
    }
  ],
  "location": "DocketEntry_StepDefinitions.user_gets_the_entries_of_the_judge_and_logs_out(String)"
});
formatter.result({
  "duration": 51307417567,
  "status": "passed"
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_am_logged_into_Briefcase(UserInputData\u003e)"
});
formatter.result({
  "duration": 70962363891,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "15-2594",
      "offset": 76
    }
  ],
  "location": "DocketEntry_StepDefinitions.user_verifies_a_JA_or_law_clerk_can_see_the_same_entries_as_their_judge(String)"
});
formatter.result({
  "duration": 13695658189,
  "status": "passed"
});
formatter.after({
  "duration": 75074836907,
  "error_message": "org.openqa.selenium.NoSuchElementException: Can\u0027t locate an element by this strategy: By.chained({By.xpath: //XCUIElementTypeStaticText[@name\u003d\u0027Logout of Briefcase\u0027]})\n\tat io.appium.java_client.pagefactory.AppiumElementLocator.findElement(AppiumElementLocator.java:126)\n\tat io.appium.java_client.pagefactory.interceptors.InterceptorOfASingleElement.intercept(InterceptorOfASingleElement.java:60)\n\tat io.appium.java_client.ios.IOSElement$$EnhancerByCGLIB$$d311658.click(\u003cgenerated\u003e)\n\tat gov.uscourts.ao.mobileBriefcase.Pages.JenieLoginPage.logout(JenieLoginPage.java:196)\n\tat gov.uscourts.ao.mobileBriefcase.stepDefinitions.Hook.tearDown(Hook.java:19)\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n\tat java.lang.reflect.Method.invoke(Method.java:498)\n\tat cucumber.runtime.Utils$1.call(Utils.java:40)\n\tat cucumber.runtime.Timeout.timeout(Timeout.java:16)\n\tat cucumber.runtime.Utils.invoke(Utils.java:34)\n\tat cucumber.runtime.java.JavaHookDefinition.execute(JavaHookDefinition.java:60)\n\tat cucumber.runtime.Runtime.runHookIfTagsMatch(Runtime.java:224)\n\tat cucumber.runtime.Runtime.runHooks(Runtime.java:212)\n\tat cucumber.runtime.Runtime.runAfterHooks(Runtime.java:206)\n\tat cucumber.runtime.model.CucumberScenario.run(CucumberScenario.java:46)\n\tat cucumber.runtime.junit.ExecutionUnitRunner.run(ExecutionUnitRunner.java:102)\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:63)\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:18)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.runtime.junit.FeatureRunner.run(FeatureRunner.java:70)\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:95)\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:38)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.api.junit.Cucumber.run(Cucumber.java:100)\n\tat org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:86)\n\tat org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:538)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:760)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:460)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:206)\nCaused by: org.openqa.selenium.TimeoutException: Expected condition failed: waiting for io.appium.java_client.pagefactory.AppiumElementLocator$WaitingFunction@11b74ecb (tried for 1 second(s) with 500 milliseconds interval)\n\tat org.openqa.selenium.support.ui.FluentWait.timeoutException(FluentWait.java:304)\n\tat org.openqa.selenium.support.ui.FluentWait.until(FluentWait.java:272)\n\tat io.appium.java_client.pagefactory.AppiumElementLocator.waitFor(AppiumElementLocator.java:99)\n\tat io.appium.java_client.pagefactory.AppiumElementLocator.findElement(AppiumElementLocator.java:119)\n\t... 41 more\nCaused by: org.openqa.selenium.NoSuchElementException: Cannot locate an element using By.chained({By.xpath: //XCUIElementTypeStaticText[@name\u003d\u0027Logout of Briefcase\u0027]})\nFor documentation on this error, please visit: https://www.seleniumhq.org/exceptions/no_such_element.html\nBuild info: version: \u00273.141.59\u0027, revision: \u0027e82be7d358\u0027, time: \u00272018-11-14T08:17:03\u0027\nSystem info: host: \u0027Saltanat-MacBook-Pro.local\u0027, ip: \u0027fe80:0:0:0:4d9:a410:10f1:91cf%en0\u0027, os.name: \u0027Mac OS X\u0027, os.arch: \u0027x86_64\u0027, os.version: \u002710.15.7\u0027, java.version: \u00271.8.0_162\u0027\nDriver info: driver.version: IOSDriver\n\tat io.appium.java_client.pagefactory.bys.builder.ByChained.findElement(ByChained.java:74)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:315)\n\tat io.appium.java_client.DefaultGenericMobileDriver.findElement(DefaultGenericMobileDriver.java:58)\n\tat io.appium.java_client.AppiumDriver.findElement(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.findElement(IOSDriver.java:1)\n\tat io.appium.java_client.pagefactory.bys.ContentMappedBy.findElement(ContentMappedBy.java:50)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:315)\n\tat io.appium.java_client.DefaultGenericMobileDriver.findElement(DefaultGenericMobileDriver.java:58)\n\tat io.appium.java_client.AppiumDriver.findElement(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.findElement(IOSDriver.java:1)\n\tat io.appium.java_client.pagefactory.AppiumElementLocator.lambda$0(AppiumElementLocator.java:120)\n\tat io.appium.java_client.pagefactory.AppiumElementLocator$WaitingFunction.apply(AppiumElementLocator.java:172)\n\tat io.appium.java_client.pagefactory.AppiumElementLocator$WaitingFunction.apply(AppiumElementLocator.java:1)\n\tat org.openqa.selenium.support.ui.FluentWait.until(FluentWait.java:249)\n\t... 43 more\n",
  "status": "failed"
});
formatter.uri("CATEGORIES/SMOKE/DOCUMENT_CATEGORIES_AMB-1050.feature");
formatter.feature({
  "line": 2,
  "name": "Verify Document Categories display for Staff Attorneys",
  "description": "",
  "id": "verify-document-categories-display-for-staff-attorneys",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@Smoke"
    },
    {
      "line": 1,
      "name": "@AMB-1050"
    }
  ]
});
formatter.before({
  "duration": 8703724778,
  "status": "passed"
});
formatter.background({
  "line": 5,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
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
        "Staff Attorneys",
        "Brown, Benjamin"
      ],
      "line": 13
    }
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_am_logged_into_Briefcase(UserInputData\u003e)"
});
formatter.result({
  "duration": 71548410049,
  "status": "passed"
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_select_a_user(UserInputData\u003e)"
});
formatter.result({
  "duration": 12960590722,
  "status": "passed"
});
formatter.scenario({
  "line": 16,
  "name": "",
  "description": "If selecting a referral, the documents display on the referral detail page.  They are grouped by document category.\n  Each document category is a collapsible panel.  This task is to verify the correct document categories and number of docs in each\n   category display for a selected referral.",
  "id": "verify-document-categories-display-for-staff-attorneys;",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 21,
  "name": "User selects assignment type \"Senior Staff Attorney\"",
  "keyword": "Given "
});
formatter.step({
  "line": 22,
  "name": "User selects category \"Anders Cases\" and \"15-3015\" ,SMR_ASSIGN_PE_ID : \"434\"",
  "keyword": "Then "
});
formatter.step({
  "line": 23,
  "name": "After selecting \"Anders Cases\" , user verifies the document categories and the number of docs displayed for each category matches the number of docs in the DB \"CMKA\". smr_assign_pe_id: \"434\"",
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "Senior Staff Attorney",
      "offset": 30
    }
  ],
  "location": "StaffAttorney_StepDefinitions.user_selects_assignment_type(String)"
});
formatter.result({
  "duration": 3099679820,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Anders Cases",
      "offset": 23
    },
    {
      "val": "15-3015",
      "offset": 42
    },
    {
      "val": "434",
      "offset": 72
    }
  ],
  "location": "StaffAttorney_StepDefinitions.user_selects_category_and_SMR_ASSIGN_PE_ID(String,String,String)"
});
formatter.result({
  "duration": 50380923734,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Anders Cases",
      "offset": 17
    },
    {
      "val": "CMKA",
      "offset": 160
    },
    {
      "val": "434",
      "offset": 186
    }
  ],
  "location": "StaffAttorney_StepDefinitions.after_selecting_user_verifies_the_document_categories_and_the_number_of_docs_displayed_for_each_category_matches_the_number_of_docs_in_the_DB_smr_assign_pe_id(String,String,String)"
});
formatter.result({
  "duration": 40576048900,
  "status": "passed"
});
formatter.after({
  "duration": 30435431609,
  "status": "passed"
});
formatter.uri("CATEGORIES/SMOKE/EXPAND_COLLAPSE_ARROWS_AMB-1301.feature");
formatter.feature({
  "line": 2,
  "name": "Verify data is displayed on the Dashboard after tapping the left-hand navigation expand/collapse arrows",
  "description": "",
  "id": "verify-data-is-displayed-on-the-dashboard-after-tapping-the-left-hand-navigation-expand/collapse-arrows",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@Smoke"
    },
    {
      "line": 1,
      "name": "@AMB-1301"
    }
  ]
});
formatter.before({
  "duration": 8290561506,
  "status": "passed"
});
formatter.scenario({
  "line": 6,
  "name": "",
  "description": "",
  "id": "verify-data-is-displayed-on-the-dashboard-after-tapping-the-left-hand-navigation-expand/collapse-arrows;",
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
        "Integration",
        "s haenni",
        "Test2024!",
        "test"
      ],
      "line": 10
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
  "name": "user taps on left-hand navigation \"Expand\" arrows",
  "keyword": "Then "
});
formatter.step({
  "line": 15,
  "name": "User observes the referral categories that display on the dashboard. Query the chm_mobile_referral, and chm_reftype_val table to get valid categories for the logged in user.",
  "rows": [
    {
      "cells": [
        "briefcaseUser",
        "courtId"
      ],
      "line": 16
    },
    {
      "cells": [
        "Colloton",
        "test"
      ],
      "line": 17
    }
  ],
  "keyword": "And "
});
formatter.step({
  "line": 18,
  "name": "user taps on left-hand navigation \"Expand\" arrows",
  "keyword": "Then "
});
formatter.step({
  "line": 19,
  "name": "User observes the referral categories that display on the dashboard. Query the chm_mobile_referral, and chm_reftype_val table to get valid categories for the logged in user.",
  "rows": [
    {
      "cells": [
        "briefcaseUser",
        "courtId"
      ],
      "line": 20
    },
    {
      "cells": [
        "Colloton",
        "test"
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
  "duration": 72125886221,
  "status": "passed"
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_select_a_user(UserInputData\u003e)"
});
formatter.result({
  "duration": 12542722950,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Expand",
      "offset": 35
    }
  ],
  "location": "ReferralCategories_StepDefinitions.user_taps_on_left_hand_navigation_arrows(String)"
});
formatter.result({
  "duration": 10015320539,
  "status": "passed"
});
formatter.match({
  "location": "ReferralCategories_StepDefinitions.user_observes_the_referral_categories_that_display_on_the_dashboard_Query_the_chm_mobile_referral_and_chm_reftype_val_table_to_get_valid_categories_for_the_logged_in_user(UserInputData\u003e)"
});
formatter.result({
  "duration": 37059384647,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Expand",
      "offset": 35
    }
  ],
  "location": "ReferralCategories_StepDefinitions.user_taps_on_left_hand_navigation_arrows(String)"
});
formatter.result({
  "duration": 3152738530,
  "status": "passed"
});
formatter.match({
  "location": "ReferralCategories_StepDefinitions.user_observes_the_referral_categories_that_display_on_the_dashboard_Query_the_chm_mobile_referral_and_chm_reftype_val_table_to_get_valid_categories_for_the_logged_in_user(UserInputData\u003e)"
});
formatter.result({
  "duration": 35934514043,
  "status": "passed"
});
formatter.after({
  "duration": 32562982252,
  "status": "passed"
});
formatter.uri("CATEGORIES/SMOKE/JUDGE_VOTE_DPF_AMB-1100 || 1103 || 1210 || AMB-1197.feature");
formatter.feature({
  "line": 2,
  "name": "JudgeVote DPF UI",
  "description": "",
  "id": "judgevote-dpf-ui",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@Smoke"
    }
  ]
});
formatter.before({
  "duration": 8598125596,
  "status": "passed"
});
formatter.background({
  "comments": [
    {
      "line": 3,
      "value": "#related to AMB-1097 as well"
    }
  ],
  "line": 4,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 6,
  "name": "I am logged into Briefcase",
  "rows": [
    {
      "cells": [
        "environment",
        "userName",
        "password",
        "courtId"
      ],
      "line": 7
    },
    {
      "cells": [
        "Integration",
        "s haenni",
        "Test2024!",
        "test"
      ],
      "line": 8
    }
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 10,
  "name": "I select a user",
  "rows": [
    {
      "cells": [
        "role",
        "briefcaseUser"
      ],
      "line": 11
    },
    {
      "cells": [
        "Appellate Judges",
        "Benton"
      ],
      "line": 12
    }
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_am_logged_into_Briefcase(UserInputData\u003e)"
});
formatter.result({
  "duration": 70274382844,
  "status": "passed"
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_select_a_user(UserInputData\u003e)"
});
formatter.result({
  "duration": 12225681208,
  "status": "passed"
});
formatter.scenario({
  "line": 15,
  "name": "",
  "description": "In the judgeVote DPF, when the user selects the View Votes button, a popup should display with each judge\u0027s vote and the day they voted.",
  "id": "judgevote-dpf-ui;",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 14,
      "name": "@AMB-1100"
    }
  ]
});
formatter.step({
  "line": 17,
  "name": "User selects \"TEST_AUTOMATION\" and \"15-2594\"",
  "keyword": "Then "
});
formatter.step({
  "line": 19,
  "name": "User  selects action using \"3142\"  and verifies the name of the action displays in the dark blue banner",
  "rows": [
    {
      "cells": [
        "courtId"
      ],
      "line": 20
    },
    {
      "cells": [
        "test"
      ],
      "line": 21
    }
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 22,
  "name": "user selects the \"View Votes\" button next to the relief. User verifies  a popup displays.  In the red banner, the relief they are voting , \"CMKA\" , \"35683\"",
  "keyword": "Then "
});
formatter.step({
  "line": 23,
  "name": "User verifies each judges\u0027 initials to whom the referral was sent , as well as their vote and date they voted. Use \"CMKA\" , \"35683\"",
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "TEST_AUTOMATION",
      "offset": 14
    },
    {
      "val": "15-2594",
      "offset": 36
    }
  ],
  "location": "Common_StepDefinitions.user_selects_and(String,String)"
});
formatter.result({
  "duration": 12629030304,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "3142",
      "offset": 28
    }
  ],
  "location": "Common_StepDefinitions.user_selects_action_using_and_verifies_the_name_of_the_action_displays_in_the_dark_blue_banner(String,UserInputData\u003e)"
});
formatter.result({
  "duration": 64078796108,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "View Votes",
      "offset": 18
    },
    {
      "val": "CMKA",
      "offset": 140
    },
    {
      "val": "35683",
      "offset": 149
    }
  ],
  "location": "JudgeVoteDPF_StepDefinitions.user_selects_the_button_next_to_the_relief_User_verifies_a_popup_displays_In_the_red_banner_the_relief_they_are_voting(String,String,String)"
});
formatter.result({
  "duration": 2824735469,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "CMKA",
      "offset": 116
    },
    {
      "val": "35683",
      "offset": 125
    }
  ],
  "location": "JudgeVoteDPF_StepDefinitions.user_verifies_each_judges_initials_to_whom_the_referral_was_sent_as_well_as_their_vote_and_date_they_voted_Use(String,String)"
});
formatter.result({
  "duration": 46257523790,
  "status": "passed"
});
formatter.after({
  "duration": 33803988568,
  "status": "passed"
});
formatter.before({
  "duration": 9148854569,
  "status": "passed"
});
formatter.background({
  "comments": [
    {
      "line": 3,
      "value": "#related to AMB-1097 as well"
    }
  ],
  "line": 4,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 6,
  "name": "I am logged into Briefcase",
  "rows": [
    {
      "cells": [
        "environment",
        "userName",
        "password",
        "courtId"
      ],
      "line": 7
    },
    {
      "cells": [
        "Integration",
        "s haenni",
        "Test2024!",
        "test"
      ],
      "line": 8
    }
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 10,
  "name": "I select a user",
  "rows": [
    {
      "cells": [
        "role",
        "briefcaseUser"
      ],
      "line": 11
    },
    {
      "cells": [
        "Appellate Judges",
        "Benton"
      ],
      "line": 12
    }
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_am_logged_into_Briefcase(UserInputData\u003e)"
});
formatter.result({
  "duration": 69909877636,
  "status": "passed"
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_select_a_user(UserInputData\u003e)"
});
formatter.result({
  "duration": 15113356286,
  "status": "passed"
});
formatter.scenario({
  "line": 28,
  "name": "",
  "description": "The judgeVote DPF enables judges to add notes to a vote.  There is a parameter in the judgeVote DPF called Note History.  \nIf the parameter is set to \u0027y\u0027, the text of the previous vote note (if there is one), should display when the judge adds a \nnote to a new vote.  To the judge, it appears that he/she is just editing an existing vote, even though CM/ECF is creating a new note.",
  "id": "judgevote-dpf-ui;",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 26,
      "name": "@AMB-1103"
    },
    {
      "line": 27,
      "name": "@AMB-1210"
    }
  ]
});
formatter.step({
  "line": 32,
  "name": "User selects \"TEST_AUTOMATION\" and \"15-2594\"",
  "keyword": "Then "
});
formatter.step({
  "line": 33,
  "name": "User  selects action using \"3142\"  and verifies the name of the action displays in the dark blue banner",
  "rows": [
    {
      "cells": [
        "courtId"
      ],
      "line": 34
    },
    {
      "cells": [
        "test"
      ],
      "line": 35
    }
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 36,
  "name": "user selects a vote and adds notes to a vote. Use  db \"CMKA\" ,ccrID \"35683\" , elID  \"3142\" , and dpf \"judgeVote\"",
  "keyword": "Then "
});
formatter.step({
  "line": 37,
  "name": "User verifies judge\u0027s vote is updated in Vote Information Panel. Use  db \"CMKA\" ,ccrID \"35683\"",
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "TEST_AUTOMATION",
      "offset": 14
    },
    {
      "val": "15-2594",
      "offset": 36
    }
  ],
  "location": "Common_StepDefinitions.user_selects_and(String,String)"
});
formatter.result({
  "duration": 11199434795,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "3142",
      "offset": 28
    }
  ],
  "location": "Common_StepDefinitions.user_selects_action_using_and_verifies_the_name_of_the_action_displays_in_the_dark_blue_banner(String,UserInputData\u003e)"
});
formatter.result({
  "duration": 42718415986,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "CMKA",
      "offset": 55
    },
    {
      "val": "35683",
      "offset": 69
    },
    {
      "val": "3142",
      "offset": 85
    },
    {
      "val": "judgeVote",
      "offset": 102
    }
  ],
  "location": "JudgeVoteDPF_StepDefinitions.user_selects_a_vote_and_adds_notes_to_a_vote_Use_db_ccrID_elID_and_dpf(String,String,String,String)"
});
formatter.result({
  "duration": 137305120689,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "CMKA",
      "offset": 74
    },
    {
      "val": "35683",
      "offset": 88
    }
  ],
  "location": "JudgeVoteDPF_StepDefinitions.user_verifies_judge_s_vote_is_updated_in_Vote_Information_Panel_Use_db_ccrID(String,String)"
});
formatter.result({
  "duration": 36174760588,
  "status": "passed"
});
formatter.after({
  "duration": 40123122458,
  "status": "passed"
});
formatter.uri("CATEGORIES/SMOKE/PDF_DOCUMENT.feature");
formatter.feature({
  "line": 2,
  "name": "Verify a PDF document can be downloaded and opened in Briefcase",
  "description": "",
  "id": "verify-a-pdf-document-can-be-downloaded-and-opened-in-briefcase",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@AMB-1006"
    },
    {
      "line": 1,
      "name": "@Smoke"
    }
  ]
});
formatter.scenarioOutline({
  "line": 5,
  "name": "",
  "description": "Verify that PDF documents can be downloaded from the server to Briefcase and viewed within Briefcase.",
  "id": "verify-a-pdf-document-can-be-downloaded-and-opened-in-briefcase;",
  "type": "scenario_outline",
  "keyword": "Scenario Outline"
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
        "Integration",
        "s haenni",
        "Test2024!",
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
        "role",
        "briefcaseUser"
      ],
      "line": 13
    },
    {
      "cells": [
        "Appellate Judges",
        "Colloton"
      ],
      "line": 14
    }
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 15,
  "name": "User deletes all docs from the device",
  "keyword": "Then "
});
formatter.step({
  "line": 16,
  "name": "User selects \"\u003crefCategory\u003e\" and \"\u003ccaseNum\u003e\"",
  "keyword": "Then "
});
formatter.step({
  "line": 17,
  "name": "User taps document pdf doc in cmecf and verify that it is downloaded from the server and opens in Briefcase",
  "keyword": "Then "
});
formatter.examples({
  "line": 19,
  "name": "",
  "description": "",
  "id": "verify-a-pdf-document-can-be-downloaded-and-opened-in-briefcase;;",
  "rows": [
    {
      "cells": [
        "server",
        "refCategory",
        "caseNum"
      ],
      "line": 20,
      "id": "verify-a-pdf-document-can-be-downloaded-and-opened-in-briefcase;;;1"
    },
    {
      "cells": [
        "CMKA",
        "MOTION",
        "18-12418"
      ],
      "line": 21,
      "id": "verify-a-pdf-document-can-be-downloaded-and-opened-in-briefcase;;;2"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 8800729900,
  "status": "passed"
});
formatter.scenario({
  "line": 21,
  "name": "",
  "description": "Verify that PDF documents can be downloaded from the server to Briefcase and viewed within Briefcase.",
  "id": "verify-a-pdf-document-can-be-downloaded-and-opened-in-briefcase;;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 1,
      "name": "@AMB-1006"
    },
    {
      "line": 1,
      "name": "@Smoke"
    }
  ]
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
        "Integration",
        "s haenni",
        "Test2024!",
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
        "role",
        "briefcaseUser"
      ],
      "line": 13
    },
    {
      "cells": [
        "Appellate Judges",
        "Colloton"
      ],
      "line": 14
    }
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 15,
  "name": "User deletes all docs from the device",
  "keyword": "Then "
});
formatter.step({
  "line": 16,
  "name": "User selects \"MOTION\" and \"18-12418\"",
  "matchedColumns": [
    1,
    2
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 17,
  "name": "User taps document pdf doc in cmecf and verify that it is downloaded from the server and opens in Briefcase",
  "keyword": "Then "
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_am_logged_into_Briefcase(UserInputData\u003e)"
});
formatter.result({
  "duration": 72762016028,
  "status": "passed"
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_select_a_user(UserInputData\u003e)"
});
formatter.result({
  "duration": 18804369698,
  "status": "passed"
});
formatter.match({
  "location": "NoticesOfDocketActivity_StepDefinitions.user_deletes_all_docs_from_the_device()"
});
formatter.result({
  "duration": 45288862020,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "MOTION",
      "offset": 14
    },
    {
      "val": "18-12418",
      "offset": 27
    }
  ],
  "location": "Common_StepDefinitions.user_selects_and(String,String)"
});
formatter.result({
  "duration": 13230230996,
  "status": "passed"
});
formatter.match({
  "location": "NoticesOfDocketActivity_StepDefinitions.user_taps_document_pdf_doc_in_cmecf_and_verify_that_it_is_downloaded_from_the_server_and_opens_in_Briefcase()"
});
formatter.result({
  "duration": 229861960328,
  "error_message": "org.openqa.selenium.TimeoutException: Expected condition failed: waiting for presence of element located by: By.xpath: //*[contains(@name, \u0027PDF View\u0027)] (tried for 120 second(s) with 500 milliseconds interval)\n\tat org.openqa.selenium.support.ui.WebDriverWait.timeoutException(WebDriverWait.java:95)\n\tat org.openqa.selenium.support.ui.FluentWait.until(FluentWait.java:272)\n\tat gov.uscourts.ao.mobileBriefcase.common.Page.waitForPresenceOfElementLocated(Page.java:17)\n\tat gov.uscourts.ao.mobileBriefcase.common.Actions.findElementBy(Actions.java:55)\n\tat gov.uscourts.ao.mobileBriefcase.common.Utility.isDisplayed(Utility.java:56)\n\tat gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.verifyElementIsDisplayed(CommonPages.java:233)\n\tat gov.uscourts.ao.mobileBriefcase.Pages.NoticesOfDocketActivityPage.verifyPDFIsDownloaded(NoticesOfDocketActivityPage.java:190)\n\tat gov.uscourts.ao.mobileBriefcase.stepDefinitions.NoticesOfDocketActivity_StepDefinitions.user_taps_document_pdf_doc_in_cmecf_and_verify_that_it_is_downloaded_from_the_server_and_opens_in_Briefcase(NoticesOfDocketActivity_StepDefinitions.java:42)\n\tat ✽.Then User taps document pdf doc in cmecf and verify that it is downloaded from the server and opens in Briefcase(CATEGORIES/SMOKE/PDF_DOCUMENT.feature:17)\nCaused by: org.openqa.selenium.NoSuchElementException: An element could not be located on the page using the given search parameters.\nFor documentation on this error, please visit: https://www.seleniumhq.org/exceptions/no_such_element.html\nBuild info: version: \u00273.141.59\u0027, revision: \u0027e82be7d358\u0027, time: \u00272018-11-14T08:17:03\u0027\nSystem info: host: \u0027Saltanat-MacBook-Pro.local\u0027, ip: \u0027fe80:0:0:0:4d9:a410:10f1:91cf%en0\u0027, os.name: \u0027Mac OS X\u0027, os.arch: \u0027x86_64\u0027, os.version: \u002710.15.7\u0027, java.version: \u00271.8.0_162\u0027\nDriver info: io.appium.java_client.ios.IOSDriver\nCapabilities {autoAcceptAlerts: true, automationName: XCUITest, browserName: , bundleId: gov.uscourts.ambbriefcase-beta, databaseEnabled: false, deviceName: iPad Pro (12.9-inch) (4th g..., deviceType: ipad, fullReset: false, javascriptEnabled: true, locationContextEnabled: false, networkConnectionEnabled: false, noReset: true, platform: MAC, platformName: iOS, platformVersion: 14.4, safariInitialUrl: http://0.0.0.0:4723/welcome , startIWDP: true, takesScreenshot: true, takes_screehshot: true, udid: 35A1B9DD-F86D-4CE7-B288-79F..., webStorageEnabled: false, xcodeOrgId: 6A52AWXC9Z, xcodeSigningId: iPhone Developer}\nSession ID: 19111db5-b875-4ba5-8a74-b411ecdeae32\n*** Element info: {Using\u003dxpath, value\u003d//*[contains(@name, \u0027PDF View\u0027)]}\n\tat sun.reflect.GeneratedConstructorAccessor26.newInstance(Unknown Source)\n\tat sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)\n\tat java.lang.reflect.Constructor.newInstance(Constructor.java:423)\n\tat org.openqa.selenium.remote.http.W3CHttpResponseCodec.createException(W3CHttpResponseCodec.java:187)\n\tat org.openqa.selenium.remote.http.W3CHttpResponseCodec.decode(W3CHttpResponseCodec.java:122)\n\tat org.openqa.selenium.remote.http.W3CHttpResponseCodec.decode(W3CHttpResponseCodec.java:49)\n\tat org.openqa.selenium.remote.HttpCommandExecutor.execute(HttpCommandExecutor.java:158)\n\tat io.appium.java_client.remote.AppiumCommandExecutor.execute(AppiumCommandExecutor.java:231)\n\tat org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:552)\n\tat io.appium.java_client.DefaultGenericMobileDriver.execute(DefaultGenericMobileDriver.java:42)\n\tat io.appium.java_client.AppiumDriver.execute(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.execute(IOSDriver.java:1)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:323)\n\tat io.appium.java_client.DefaultGenericMobileDriver.findElement(DefaultGenericMobileDriver.java:62)\n\tat io.appium.java_client.AppiumDriver.findElement(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.findElement(IOSDriver.java:1)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElementByXPath(RemoteWebDriver.java:428)\n\tat io.appium.java_client.DefaultGenericMobileDriver.findElementByXPath(DefaultGenericMobileDriver.java:152)\n\tat io.appium.java_client.AppiumDriver.findElementByXPath(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.findElementByXPath(IOSDriver.java:1)\n\tat org.openqa.selenium.By$ByXPath.findElement(By.java:353)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:315)\n\tat io.appium.java_client.DefaultGenericMobileDriver.findElement(DefaultGenericMobileDriver.java:58)\n\tat io.appium.java_client.AppiumDriver.findElement(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.findElement(IOSDriver.java:1)\n\tat org.openqa.selenium.support.ui.ExpectedConditions$6.apply(ExpectedConditions.java:182)\n\tat org.openqa.selenium.support.ui.ExpectedConditions$6.apply(ExpectedConditions.java:179)\n\tat org.openqa.selenium.support.ui.FluentWait.until(FluentWait.java:249)\n\tat gov.uscourts.ao.mobileBriefcase.common.Page.waitForPresenceOfElementLocated(Page.java:17)\n\tat gov.uscourts.ao.mobileBriefcase.common.Actions.findElementBy(Actions.java:55)\n\tat gov.uscourts.ao.mobileBriefcase.common.Utility.isDisplayed(Utility.java:56)\n\tat gov.uscourts.ao.mobileBriefcase.Pages.CommonPages.verifyElementIsDisplayed(CommonPages.java:233)\n\tat gov.uscourts.ao.mobileBriefcase.Pages.NoticesOfDocketActivityPage.verifyPDFIsDownloaded(NoticesOfDocketActivityPage.java:190)\n\tat gov.uscourts.ao.mobileBriefcase.stepDefinitions.NoticesOfDocketActivity_StepDefinitions.user_taps_document_pdf_doc_in_cmecf_and_verify_that_it_is_downloaded_from_the_server_and_opens_in_Briefcase(NoticesOfDocketActivity_StepDefinitions.java:42)\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n\tat java.lang.reflect.Method.invoke(Method.java:498)\n\tat cucumber.runtime.Utils$1.call(Utils.java:40)\n\tat cucumber.runtime.Timeout.timeout(Timeout.java:16)\n\tat cucumber.runtime.Utils.invoke(Utils.java:34)\n\tat cucumber.runtime.java.JavaStepDefinition.execute(JavaStepDefinition.java:38)\n\tat cucumber.runtime.StepDefinitionMatch.runStep(StepDefinitionMatch.java:37)\n\tat cucumber.runtime.Runtime.runStep(Runtime.java:300)\n\tat cucumber.runtime.model.StepContainer.runStep(StepContainer.java:44)\n\tat cucumber.runtime.model.StepContainer.runSteps(StepContainer.java:39)\n\tat cucumber.runtime.model.CucumberScenario.run(CucumberScenario.java:44)\n\tat cucumber.runtime.junit.ExecutionUnitRunner.run(ExecutionUnitRunner.java:102)\n\tat org.junit.runners.Suite.runChild(Suite.java:128)\n\tat org.junit.runners.Suite.runChild(Suite.java:27)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.runtime.junit.ExamplesRunner.run(ExamplesRunner.java:59)\n\tat org.junit.runners.Suite.runChild(Suite.java:128)\n\tat org.junit.runners.Suite.runChild(Suite.java:27)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.runtime.junit.ScenarioOutlineRunner.run(ScenarioOutlineRunner.java:53)\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:63)\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:18)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.runtime.junit.FeatureRunner.run(FeatureRunner.java:70)\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:95)\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:38)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.api.junit.Cucumber.run(Cucumber.java:100)\n\tat org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:86)\n\tat org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:538)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:760)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:460)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:206)\n",
  "status": "failed"
});
formatter.after({
  "duration": 41648298770,
  "status": "passed"
});
formatter.uri("CATEGORIES/SMOKE/PENDING_TASKS_CATEGORY_AMB-1008.feature");
formatter.feature({
  "line": 2,
  "name": "Pending Tasks Category Displays on the Dashboard",
  "description": "",
  "id": "pending-tasks-category-displays-on-the-dashboard",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@Smoke"
    },
    {
      "line": 1,
      "name": "@AMB-1008"
    }
  ]
});
formatter.scenarioOutline({
  "comments": [
    {
      "line": 4,
      "value": "#dependent on AMB-2300"
    }
  ],
  "line": 5,
  "name": "",
  "description": "A category entitled \"Pending Tasks\" will display on the dashboard if the judge has \nany pending assignments and the site table variable briefcaseShowPendingTasks \u003d\u0027y\u0027.",
  "id": "pending-tasks-category-displays-on-the-dashboard;",
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
  "name": "If The judge has any pending assignments it will validate the total num of pending task on UI with DB. Use  judge\u0027s \"\u003cpe_id\u003e\" and  \"\u003cPE_RT_CODE\u003e\" to retrieve pending tasks from db",
  "rows": [
    {
      "cells": [
        "courtId"
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
  "keyword": "Given "
});
formatter.examples({
  "line": 19,
  "name": "",
  "description": "",
  "id": "pending-tasks-category-displays-on-the-dashboard;;",
  "rows": [
    {
      "cells": [
        "pe_id",
        "PE_RT_CODE"
      ],
      "line": 20,
      "id": "pending-tasks-category-displays-on-the-dashboard;;;1"
    },
    {
      "cells": [
        "Colloton",
        "jud"
      ],
      "line": 21,
      "id": "pending-tasks-category-displays-on-the-dashboard;;;2"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 13634799941,
  "status": "passed"
});
formatter.scenario({
  "line": 21,
  "name": "",
  "description": "A category entitled \"Pending Tasks\" will display on the dashboard if the judge has \nany pending assignments and the site table variable briefcaseShowPendingTasks \u003d\u0027y\u0027.",
  "id": "pending-tasks-category-displays-on-the-dashboard;;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 1,
      "name": "@Smoke"
    },
    {
      "line": 1,
      "name": "@AMB-1008"
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
  "name": "If The judge has any pending assignments it will validate the total num of pending task on UI with DB. Use  judge\u0027s \"Colloton\" and  \"jud\" to retrieve pending tasks from db",
  "matchedColumns": [
    0,
    1
  ],
  "rows": [
    {
      "cells": [
        "courtId"
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
  "keyword": "Given "
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_am_logged_into_Briefcase(UserInputData\u003e)"
});
formatter.result({
  "duration": 15240133186,
  "error_message": "org.openqa.selenium.NoSuchElementException: Can\u0027t locate an element by this strategy: By.chained({By.xpath: //XCUIElementTypeOther[@name\u003d\"JENIE Single Sign On\"]/XCUIElementTypeOther[5]/XCUIElementTypeTextField})\n\tat io.appium.java_client.pagefactory.AppiumElementLocator.findElement(AppiumElementLocator.java:126)\n\tat io.appium.java_client.pagefactory.interceptors.InterceptorOfASingleElement.intercept(InterceptorOfASingleElement.java:60)\n\tat io.appium.java_client.ios.IOSElement$$EnhancerByCGLIB$$d311658.click(\u003cgenerated\u003e)\n\tat gov.uscourts.ao.mobileBriefcase.common.Actions.sendKeys(Actions.java:88)\n\tat gov.uscourts.ao.mobileBriefcase.Pages.JenieLoginPage.sendCredentials(JenieLoginPage.java:124)\n\tat gov.uscourts.ao.mobileBriefcase.Pages.JenieLoginPage.login(JenieLoginPage.java:217)\n\tat gov.uscourts.ao.mobileBriefcase.stepDefinitions.JenieLogin_StepDefinitions.i_am_logged_into_Briefcase(JenieLogin_StepDefinitions.java:19)\n\tat ✽.Given I am logged into Briefcase(CATEGORIES/SMOKE/PENDING_TASKS_CATEGORY_AMB-1008.feature:10)\nCaused by: org.openqa.selenium.TimeoutException: Expected condition failed: waiting for io.appium.java_client.pagefactory.AppiumElementLocator$WaitingFunction@d3767cf (tried for 1 second(s) with 500 milliseconds interval)\n\tat org.openqa.selenium.support.ui.FluentWait.timeoutException(FluentWait.java:304)\n\tat org.openqa.selenium.support.ui.FluentWait.until(FluentWait.java:272)\n\tat io.appium.java_client.pagefactory.AppiumElementLocator.waitFor(AppiumElementLocator.java:99)\n\tat io.appium.java_client.pagefactory.AppiumElementLocator.findElement(AppiumElementLocator.java:119)\n\tat io.appium.java_client.pagefactory.interceptors.InterceptorOfASingleElement.intercept(InterceptorOfASingleElement.java:60)\n\tat io.appium.java_client.ios.IOSElement$$EnhancerByCGLIB$$d311658.click(\u003cgenerated\u003e)\n\tat gov.uscourts.ao.mobileBriefcase.common.Actions.sendKeys(Actions.java:88)\n\tat gov.uscourts.ao.mobileBriefcase.Pages.JenieLoginPage.sendCredentials(JenieLoginPage.java:124)\n\tat gov.uscourts.ao.mobileBriefcase.Pages.JenieLoginPage.login(JenieLoginPage.java:217)\n\tat gov.uscourts.ao.mobileBriefcase.stepDefinitions.JenieLogin_StepDefinitions.i_am_logged_into_Briefcase(JenieLogin_StepDefinitions.java:19)\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n\tat java.lang.reflect.Method.invoke(Method.java:498)\n\tat cucumber.runtime.Utils$1.call(Utils.java:40)\n\tat cucumber.runtime.Timeout.timeout(Timeout.java:16)\n\tat cucumber.runtime.Utils.invoke(Utils.java:34)\n\tat cucumber.runtime.java.JavaStepDefinition.execute(JavaStepDefinition.java:38)\n\tat cucumber.runtime.StepDefinitionMatch.runStep(StepDefinitionMatch.java:37)\n\tat cucumber.runtime.Runtime.runStep(Runtime.java:300)\n\tat cucumber.runtime.model.StepContainer.runStep(StepContainer.java:44)\n\tat cucumber.runtime.model.StepContainer.runSteps(StepContainer.java:39)\n\tat cucumber.runtime.model.CucumberScenario.run(CucumberScenario.java:44)\n\tat cucumber.runtime.junit.ExecutionUnitRunner.run(ExecutionUnitRunner.java:102)\n\tat org.junit.runners.Suite.runChild(Suite.java:128)\n\tat org.junit.runners.Suite.runChild(Suite.java:27)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.runtime.junit.ExamplesRunner.run(ExamplesRunner.java:59)\n\tat org.junit.runners.Suite.runChild(Suite.java:128)\n\tat org.junit.runners.Suite.runChild(Suite.java:27)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.runtime.junit.ScenarioOutlineRunner.run(ScenarioOutlineRunner.java:53)\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:63)\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:18)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.runtime.junit.FeatureRunner.run(FeatureRunner.java:70)\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:95)\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:38)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.api.junit.Cucumber.run(Cucumber.java:100)\n\tat org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:86)\n\tat org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:538)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:760)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:460)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:206)\nCaused by: org.openqa.selenium.NoSuchElementException: Cannot locate an element using By.chained({By.xpath: //XCUIElementTypeOther[@name\u003d\"JENIE Single Sign On\"]/XCUIElementTypeOther[5]/XCUIElementTypeTextField})\nFor documentation on this error, please visit: https://www.seleniumhq.org/exceptions/no_such_element.html\nBuild info: version: \u00273.141.59\u0027, revision: \u0027e82be7d358\u0027, time: \u00272018-11-14T08:17:03\u0027\nSystem info: host: \u0027Saltanat-MacBook-Pro.local\u0027, ip: \u0027fe80:0:0:0:4d9:a410:10f1:91cf%en0\u0027, os.name: \u0027Mac OS X\u0027, os.arch: \u0027x86_64\u0027, os.version: \u002710.15.7\u0027, java.version: \u00271.8.0_162\u0027\nDriver info: driver.version: IOSDriver\n\tat io.appium.java_client.pagefactory.bys.builder.ByChained.findElement(ByChained.java:74)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:315)\n\tat io.appium.java_client.DefaultGenericMobileDriver.findElement(DefaultGenericMobileDriver.java:58)\n\tat io.appium.java_client.AppiumDriver.findElement(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.findElement(IOSDriver.java:1)\n\tat io.appium.java_client.pagefactory.bys.ContentMappedBy.findElement(ContentMappedBy.java:50)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:315)\n\tat io.appium.java_client.DefaultGenericMobileDriver.findElement(DefaultGenericMobileDriver.java:58)\n\tat io.appium.java_client.AppiumDriver.findElement(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.findElement(IOSDriver.java:1)\n\tat io.appium.java_client.pagefactory.AppiumElementLocator.lambda$0(AppiumElementLocator.java:120)\n\tat io.appium.java_client.pagefactory.AppiumElementLocator$WaitingFunction.apply(AppiumElementLocator.java:172)\n\tat io.appium.java_client.pagefactory.AppiumElementLocator$WaitingFunction.apply(AppiumElementLocator.java:1)\n\tat org.openqa.selenium.support.ui.FluentWait.until(FluentWait.java:249)\n\t... 64 more\n",
  "status": "failed"
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_select_a_user(UserInputData\u003e)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "Colloton",
      "offset": 117
    },
    {
      "val": "jud",
      "offset": 133
    }
  ],
  "location": "ReferralCategories_StepDefinitions.if_The_judge_has_any_pending_assignments_it_will_validate_the_total_num_of_pending_task_on_UI_with_DB_Use_judge_s_and_to_retrieve_pending_tasks_from_db(String,String,UserInputData\u003e)"
});
formatter.result({
  "status": "skipped"
});
formatter.after({
  "duration": 120844666074,
  "error_message": "org.openqa.selenium.TimeoutException: Expected condition failed: waiting for presence of element located by: By.xpath: //*[contains(@name, \u0027Dashboard\u0027)] (tried for 120 second(s) with 500 milliseconds interval)\n\tat org.openqa.selenium.support.ui.WebDriverWait.timeoutException(WebDriverWait.java:95)\n\tat org.openqa.selenium.support.ui.FluentWait.until(FluentWait.java:272)\n\tat gov.uscourts.ao.mobileBriefcase.common.Page.waitForPresenceOfElementLocated(Page.java:17)\n\tat gov.uscourts.ao.mobileBriefcase.common.Actions.contains(Actions.java:67)\n\tat gov.uscourts.ao.mobileBriefcase.Pages.JenieLoginPage.logout(JenieLoginPage.java:185)\n\tat gov.uscourts.ao.mobileBriefcase.stepDefinitions.Hook.tearDown(Hook.java:19)\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n\tat java.lang.reflect.Method.invoke(Method.java:498)\n\tat cucumber.runtime.Utils$1.call(Utils.java:40)\n\tat cucumber.runtime.Timeout.timeout(Timeout.java:16)\n\tat cucumber.runtime.Utils.invoke(Utils.java:34)\n\tat cucumber.runtime.java.JavaHookDefinition.execute(JavaHookDefinition.java:60)\n\tat cucumber.runtime.Runtime.runHookIfTagsMatch(Runtime.java:224)\n\tat cucumber.runtime.Runtime.runHooks(Runtime.java:212)\n\tat cucumber.runtime.Runtime.runAfterHooks(Runtime.java:206)\n\tat cucumber.runtime.model.CucumberScenario.run(CucumberScenario.java:46)\n\tat cucumber.runtime.junit.ExecutionUnitRunner.run(ExecutionUnitRunner.java:102)\n\tat org.junit.runners.Suite.runChild(Suite.java:128)\n\tat org.junit.runners.Suite.runChild(Suite.java:27)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.runtime.junit.ExamplesRunner.run(ExamplesRunner.java:59)\n\tat org.junit.runners.Suite.runChild(Suite.java:128)\n\tat org.junit.runners.Suite.runChild(Suite.java:27)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.runtime.junit.ScenarioOutlineRunner.run(ScenarioOutlineRunner.java:53)\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:63)\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:18)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.runtime.junit.FeatureRunner.run(FeatureRunner.java:70)\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:95)\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:38)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.api.junit.Cucumber.run(Cucumber.java:100)\n\tat org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:86)\n\tat org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:538)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:760)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:460)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:206)\nCaused by: org.openqa.selenium.NoSuchElementException: An element could not be located on the page using the given search parameters.\nFor documentation on this error, please visit: https://www.seleniumhq.org/exceptions/no_such_element.html\nBuild info: version: \u00273.141.59\u0027, revision: \u0027e82be7d358\u0027, time: \u00272018-11-14T08:17:03\u0027\nSystem info: host: \u0027Saltanat-MacBook-Pro.local\u0027, ip: \u0027fe80:0:0:0:4d9:a410:10f1:91cf%en0\u0027, os.name: \u0027Mac OS X\u0027, os.arch: \u0027x86_64\u0027, os.version: \u002710.15.7\u0027, java.version: \u00271.8.0_162\u0027\nDriver info: io.appium.java_client.ios.IOSDriver\nCapabilities {autoAcceptAlerts: true, automationName: XCUITest, browserName: , bundleId: gov.uscourts.ambbriefcase-beta, databaseEnabled: false, deviceName: iPad Pro (12.9-inch) (4th g..., deviceType: ipad, fullReset: false, javascriptEnabled: true, locationContextEnabled: false, networkConnectionEnabled: false, noReset: true, platform: MAC, platformName: iOS, platformVersion: 14.4, safariInitialUrl: http://0.0.0.0:4723/welcome , startIWDP: true, takesScreenshot: true, takes_screehshot: true, udid: 35A1B9DD-F86D-4CE7-B288-79F..., webStorageEnabled: false, xcodeOrgId: 6A52AWXC9Z, xcodeSigningId: iPhone Developer}\nSession ID: 57e4515f-6983-459e-a492-9b24f4f54fbb\n*** Element info: {Using\u003dxpath, value\u003d//*[contains(@name, \u0027Dashboard\u0027)]}\n\tat sun.reflect.GeneratedConstructorAccessor26.newInstance(Unknown Source)\n\tat sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)\n\tat java.lang.reflect.Constructor.newInstance(Constructor.java:423)\n\tat org.openqa.selenium.remote.http.W3CHttpResponseCodec.createException(W3CHttpResponseCodec.java:187)\n\tat org.openqa.selenium.remote.http.W3CHttpResponseCodec.decode(W3CHttpResponseCodec.java:122)\n\tat org.openqa.selenium.remote.http.W3CHttpResponseCodec.decode(W3CHttpResponseCodec.java:49)\n\tat org.openqa.selenium.remote.HttpCommandExecutor.execute(HttpCommandExecutor.java:158)\n\tat io.appium.java_client.remote.AppiumCommandExecutor.execute(AppiumCommandExecutor.java:231)\n\tat org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:552)\n\tat io.appium.java_client.DefaultGenericMobileDriver.execute(DefaultGenericMobileDriver.java:42)\n\tat io.appium.java_client.AppiumDriver.execute(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.execute(IOSDriver.java:1)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:323)\n\tat io.appium.java_client.DefaultGenericMobileDriver.findElement(DefaultGenericMobileDriver.java:62)\n\tat io.appium.java_client.AppiumDriver.findElement(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.findElement(IOSDriver.java:1)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElementByXPath(RemoteWebDriver.java:428)\n\tat io.appium.java_client.DefaultGenericMobileDriver.findElementByXPath(DefaultGenericMobileDriver.java:152)\n\tat io.appium.java_client.AppiumDriver.findElementByXPath(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.findElementByXPath(IOSDriver.java:1)\n\tat org.openqa.selenium.By$ByXPath.findElement(By.java:353)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:315)\n\tat io.appium.java_client.DefaultGenericMobileDriver.findElement(DefaultGenericMobileDriver.java:58)\n\tat io.appium.java_client.AppiumDriver.findElement(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.findElement(IOSDriver.java:1)\n\tat org.openqa.selenium.support.ui.ExpectedConditions$6.apply(ExpectedConditions.java:182)\n\tat org.openqa.selenium.support.ui.ExpectedConditions$6.apply(ExpectedConditions.java:179)\n\tat org.openqa.selenium.support.ui.FluentWait.until(FluentWait.java:249)\n\t... 59 more\n",
  "status": "failed"
});
formatter.uri("CATEGORIES/SMOKE/REFERRAL_CATEGORIES_AMB-1049.feature");
formatter.feature({
  "line": 2,
  "name": "Referral categories display on the dashboard for Staff Attorneys",
  "description": "",
  "id": "referral-categories-display-on-the-dashboard-for-staff-attorneys",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@Smoke"
    },
    {
      "line": 1,
      "name": "@AMB-1049"
    }
  ]
});
formatter.before({
  "duration": 14399915674,
  "status": "passed"
});
formatter.background({
  "line": 5,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
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
        "Staff Attorneys",
        "Brown, Benjamin"
      ],
      "line": 13
    }
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_am_logged_into_Briefcase(UserInputData\u003e)"
});
formatter.result({
  "duration": 77851302218,
  "status": "passed"
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_select_a_user(UserInputData\u003e)"
});
formatter.result({
  "duration": 20329190443,
  "status": "passed"
});
formatter.scenario({
  "line": 14,
  "name": "",
  "description": "Assignment categories are listed on the Dashboard page for staff attorneys.\nOnce an assignment category is selected, referrals are grouped based on\nreferral categories. Each referral category is displayed as a collapsible\npanel. This task is to verify each referral category is displayed and the\nnumber of referrals is correct for each category",
  "id": "referral-categories-display-on-the-dashboard-for-staff-attorneys;",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 21,
  "name": "User selects assignment type \"Senior Staff Attorney\"",
  "keyword": "Given "
});
formatter.step({
  "line": 22,
  "name": "User observes there are six referral categories listed on UI and DB, use smr_assign_pe_id: \"434\"",
  "rows": [
    {
      "cells": [
        "courtId"
      ],
      "line": 23
    },
    {
      "cells": [
        "test"
      ],
      "line": 24
    }
  ],
  "keyword": "When "
});
formatter.match({
  "arguments": [
    {
      "val": "Senior Staff Attorney",
      "offset": 30
    }
  ],
  "location": "StaffAttorney_StepDefinitions.user_selects_assignment_type(String)"
});
formatter.result({
  "duration": 3626613905,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "434",
      "offset": 92
    }
  ],
  "location": "StaffAttorney_StepDefinitions.user_observes_there_are_six_referral_categories_listed_on_UI_and_DB_use_smr_assign_pe_id(String,UserInputData\u003e)"
});
formatter.result({
  "duration": 54887909344,
  "status": "passed"
});
formatter.after({
  "duration": 76983821783,
  "error_message": "org.openqa.selenium.NoSuchElementException: Can\u0027t locate an element by this strategy: By.chained({By.xpath: //XCUIElementTypeStaticText[@name\u003d\u0027Logout of Briefcase\u0027]})\n\tat io.appium.java_client.pagefactory.AppiumElementLocator.findElement(AppiumElementLocator.java:126)\n\tat io.appium.java_client.pagefactory.interceptors.InterceptorOfASingleElement.intercept(InterceptorOfASingleElement.java:60)\n\tat io.appium.java_client.ios.IOSElement$$EnhancerByCGLIB$$d311658.click(\u003cgenerated\u003e)\n\tat gov.uscourts.ao.mobileBriefcase.Pages.JenieLoginPage.logout(JenieLoginPage.java:196)\n\tat gov.uscourts.ao.mobileBriefcase.stepDefinitions.Hook.tearDown(Hook.java:19)\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n\tat java.lang.reflect.Method.invoke(Method.java:498)\n\tat cucumber.runtime.Utils$1.call(Utils.java:40)\n\tat cucumber.runtime.Timeout.timeout(Timeout.java:16)\n\tat cucumber.runtime.Utils.invoke(Utils.java:34)\n\tat cucumber.runtime.java.JavaHookDefinition.execute(JavaHookDefinition.java:60)\n\tat cucumber.runtime.Runtime.runHookIfTagsMatch(Runtime.java:224)\n\tat cucumber.runtime.Runtime.runHooks(Runtime.java:212)\n\tat cucumber.runtime.Runtime.runAfterHooks(Runtime.java:206)\n\tat cucumber.runtime.model.CucumberScenario.run(CucumberScenario.java:46)\n\tat cucumber.runtime.junit.ExecutionUnitRunner.run(ExecutionUnitRunner.java:102)\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:63)\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:18)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.runtime.junit.FeatureRunner.run(FeatureRunner.java:70)\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:95)\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:38)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.api.junit.Cucumber.run(Cucumber.java:100)\n\tat org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:86)\n\tat org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:538)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:760)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:460)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:206)\nCaused by: org.openqa.selenium.TimeoutException: Expected condition failed: waiting for io.appium.java_client.pagefactory.AppiumElementLocator$WaitingFunction@1b36c5aa (tried for 1 second(s) with 500 milliseconds interval)\n\tat org.openqa.selenium.support.ui.FluentWait.timeoutException(FluentWait.java:304)\n\tat org.openqa.selenium.support.ui.FluentWait.until(FluentWait.java:272)\n\tat io.appium.java_client.pagefactory.AppiumElementLocator.waitFor(AppiumElementLocator.java:99)\n\tat io.appium.java_client.pagefactory.AppiumElementLocator.findElement(AppiumElementLocator.java:119)\n\t... 41 more\nCaused by: org.openqa.selenium.NoSuchElementException: Cannot locate an element using By.chained({By.xpath: //XCUIElementTypeStaticText[@name\u003d\u0027Logout of Briefcase\u0027]})\nFor documentation on this error, please visit: https://www.seleniumhq.org/exceptions/no_such_element.html\nBuild info: version: \u00273.141.59\u0027, revision: \u0027e82be7d358\u0027, time: \u00272018-11-14T08:17:03\u0027\nSystem info: host: \u0027Saltanat-MacBook-Pro.local\u0027, ip: \u0027fe80:0:0:0:4d9:a410:10f1:91cf%en0\u0027, os.name: \u0027Mac OS X\u0027, os.arch: \u0027x86_64\u0027, os.version: \u002710.15.7\u0027, java.version: \u00271.8.0_162\u0027\nDriver info: driver.version: IOSDriver\n\tat io.appium.java_client.pagefactory.bys.builder.ByChained.findElement(ByChained.java:74)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:315)\n\tat io.appium.java_client.DefaultGenericMobileDriver.findElement(DefaultGenericMobileDriver.java:58)\n\tat io.appium.java_client.AppiumDriver.findElement(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.findElement(IOSDriver.java:1)\n\tat io.appium.java_client.pagefactory.bys.ContentMappedBy.findElement(ContentMappedBy.java:50)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:315)\n\tat io.appium.java_client.DefaultGenericMobileDriver.findElement(DefaultGenericMobileDriver.java:58)\n\tat io.appium.java_client.AppiumDriver.findElement(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.findElement(IOSDriver.java:1)\n\tat io.appium.java_client.pagefactory.AppiumElementLocator.lambda$0(AppiumElementLocator.java:120)\n\tat io.appium.java_client.pagefactory.AppiumElementLocator$WaitingFunction.apply(AppiumElementLocator.java:172)\n\tat io.appium.java_client.pagefactory.AppiumElementLocator$WaitingFunction.apply(AppiumElementLocator.java:1)\n\tat org.openqa.selenium.support.ui.FluentWait.until(FluentWait.java:249)\n\t... 43 more\n",
  "status": "failed"
});
formatter.uri("CATEGORIES/SMOKE/REFERRAL_CATEGORIES_AMB-956   || AMB-1302 .feature");
formatter.feature({
  "line": 2,
  "name": "Referral Categories display on the dashboard for the judge",
  "description": "",
  "id": "referral-categories-display-on-the-dashboard-for-the-judge",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@AMB-956"
    },
    {
      "line": 1,
      "name": "@Smoke"
    }
  ]
});
formatter.before({
  "duration": 13362714598,
  "status": "passed"
});
formatter.scenario({
  "line": 5,
  "name": "",
  "description": "Referral categories display on the dashboard for the judge",
  "id": "referral-categories-display-on-the-dashboard-for-the-judge;",
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
        "Integration",
        "s haenni",
        "Test2024!",
        "test"
      ],
      "line": 10
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
  "line": 15,
  "name": "User observes the referral categories that display on the dashboard. Query the chm_mobile_referral, and chm_reftype_val table to get valid categories for the logged in user.",
  "rows": [
    {
      "cells": [
        "briefcaseUser",
        "courtId"
      ],
      "line": 16
    },
    {
      "cells": [
        "Colloton",
        "test"
      ],
      "line": 17
    }
  ],
  "keyword": "And "
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_am_logged_into_Briefcase(UserInputData\u003e)"
});
formatter.result({
  "duration": 79695550989,
  "status": "passed"
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_select_a_user(UserInputData\u003e)"
});
formatter.result({
  "duration": 18424247772,
  "status": "passed"
});
formatter.match({
  "location": "ReferralCategories_StepDefinitions.user_observes_the_referral_categories_that_display_on_the_dashboard_Query_the_chm_mobile_referral_and_chm_reftype_val_table_to_get_valid_categories_for_the_logged_in_user(UserInputData\u003e)"
});
formatter.result({
  "duration": 45085429939,
  "status": "passed"
});
formatter.after({
  "duration": 77208157164,
  "error_message": "org.openqa.selenium.NoSuchElementException: Can\u0027t locate an element by this strategy: By.chained({By.xpath: //XCUIElementTypeStaticText[@name\u003d\u0027Logout of Briefcase\u0027]})\n\tat io.appium.java_client.pagefactory.AppiumElementLocator.findElement(AppiumElementLocator.java:126)\n\tat io.appium.java_client.pagefactory.interceptors.InterceptorOfASingleElement.intercept(InterceptorOfASingleElement.java:60)\n\tat io.appium.java_client.ios.IOSElement$$EnhancerByCGLIB$$d311658.click(\u003cgenerated\u003e)\n\tat gov.uscourts.ao.mobileBriefcase.Pages.JenieLoginPage.logout(JenieLoginPage.java:196)\n\tat gov.uscourts.ao.mobileBriefcase.stepDefinitions.Hook.tearDown(Hook.java:19)\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n\tat java.lang.reflect.Method.invoke(Method.java:498)\n\tat cucumber.runtime.Utils$1.call(Utils.java:40)\n\tat cucumber.runtime.Timeout.timeout(Timeout.java:16)\n\tat cucumber.runtime.Utils.invoke(Utils.java:34)\n\tat cucumber.runtime.java.JavaHookDefinition.execute(JavaHookDefinition.java:60)\n\tat cucumber.runtime.Runtime.runHookIfTagsMatch(Runtime.java:224)\n\tat cucumber.runtime.Runtime.runHooks(Runtime.java:212)\n\tat cucumber.runtime.Runtime.runAfterHooks(Runtime.java:206)\n\tat cucumber.runtime.model.CucumberScenario.run(CucumberScenario.java:46)\n\tat cucumber.runtime.junit.ExecutionUnitRunner.run(ExecutionUnitRunner.java:102)\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:63)\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:18)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.runtime.junit.FeatureRunner.run(FeatureRunner.java:70)\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:95)\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:38)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.api.junit.Cucumber.run(Cucumber.java:100)\n\tat org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:86)\n\tat org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:538)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:760)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:460)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:206)\nCaused by: org.openqa.selenium.TimeoutException: Expected condition failed: waiting for io.appium.java_client.pagefactory.AppiumElementLocator$WaitingFunction@767cacee (tried for 1 second(s) with 500 milliseconds interval)\n\tat org.openqa.selenium.support.ui.FluentWait.timeoutException(FluentWait.java:304)\n\tat org.openqa.selenium.support.ui.FluentWait.until(FluentWait.java:272)\n\tat io.appium.java_client.pagefactory.AppiumElementLocator.waitFor(AppiumElementLocator.java:99)\n\tat io.appium.java_client.pagefactory.AppiumElementLocator.findElement(AppiumElementLocator.java:119)\n\t... 41 more\nCaused by: org.openqa.selenium.NoSuchElementException: Cannot locate an element using By.chained({By.xpath: //XCUIElementTypeStaticText[@name\u003d\u0027Logout of Briefcase\u0027]})\nFor documentation on this error, please visit: https://www.seleniumhq.org/exceptions/no_such_element.html\nBuild info: version: \u00273.141.59\u0027, revision: \u0027e82be7d358\u0027, time: \u00272018-11-14T08:17:03\u0027\nSystem info: host: \u0027Saltanat-MacBook-Pro.local\u0027, ip: \u0027fe80:0:0:0:4d9:a410:10f1:91cf%en0\u0027, os.name: \u0027Mac OS X\u0027, os.arch: \u0027x86_64\u0027, os.version: \u002710.15.7\u0027, java.version: \u00271.8.0_162\u0027\nDriver info: driver.version: IOSDriver\n\tat io.appium.java_client.pagefactory.bys.builder.ByChained.findElement(ByChained.java:74)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:315)\n\tat io.appium.java_client.DefaultGenericMobileDriver.findElement(DefaultGenericMobileDriver.java:58)\n\tat io.appium.java_client.AppiumDriver.findElement(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.findElement(IOSDriver.java:1)\n\tat io.appium.java_client.pagefactory.bys.ContentMappedBy.findElement(ContentMappedBy.java:50)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:315)\n\tat io.appium.java_client.DefaultGenericMobileDriver.findElement(DefaultGenericMobileDriver.java:58)\n\tat io.appium.java_client.AppiumDriver.findElement(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.findElement(IOSDriver.java:1)\n\tat io.appium.java_client.pagefactory.AppiumElementLocator.lambda$0(AppiumElementLocator.java:120)\n\tat io.appium.java_client.pagefactory.AppiumElementLocator$WaitingFunction.apply(AppiumElementLocator.java:172)\n\tat io.appium.java_client.pagefactory.AppiumElementLocator$WaitingFunction.apply(AppiumElementLocator.java:1)\n\tat org.openqa.selenium.support.ui.FluentWait.until(FluentWait.java:249)\n\t... 43 more\n",
  "status": "failed"
});
formatter.uri("CATEGORIES/SMOKE/SORTING_ON_THE_REFERRAL_DETAIL_PAGE_AMB-1394.feature");
formatter.feature({
  "line": 2,
  "name": "Sorting on the Referral Detail Page",
  "description": "",
  "id": "sorting-on-the-referral-detail-page",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@Smoke"
    },
    {
      "line": 1,
      "name": "@AMB-1394"
    }
  ]
});
formatter.scenarioOutline({
  "line": 5,
  "name": "",
  "description": "There is a sort field in the chm_mobile_docs table that the courts can set for sorting Document categories.\n Briefcase should this field for sorting. Briefcase needs to be updated to sort document categories \n based on the chm_mobile_doc.cmd_sort field",
  "id": "sorting-on-the-referral-detail-page;",
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
  "name": "User selects \"\u003crefCat\u003e\" and \"15-2594\"",
  "keyword": "Then "
});
formatter.step({
  "line": 20,
  "name": "User verifies  Document Categories are sorted on the referral detail page (\"\u003cserver\u003e\", \"\u003ccmr_cyv_code\u003e\", \"\u003ccmr_ju_pe_id\u003e\", \"\u003ccmr_cs_caseid\u003e\")",
  "keyword": "Then "
});
formatter.examples({
  "line": 25,
  "name": "",
  "description": "",
  "id": "sorting-on-the-referral-detail-page;;",
  "rows": [
    {
      "cells": [
        "environment",
        "userName",
        "password",
        "server",
        "refCat",
        "userCategory",
        "judgeName",
        "cmr_cyv_code",
        "cmr_ju_pe_id",
        "cmr_cs_caseid"
      ],
      "line": 26,
      "id": "sorting-on-the-referral-detail-page;;;1"
    },
    {
      "cells": [
        "INTEGRATION",
        "s haenni",
        "Test2021!",
        "CMKA",
        "TEST_AUTOMATION",
        "Appellate_Judges",
        "Colloton",
        "autotst",
        "32",
        "81452"
      ],
      "line": 27,
      "id": "sorting-on-the-referral-detail-page;;;2"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 10880488269,
  "status": "passed"
});
formatter.scenario({
  "line": 27,
  "name": "",
  "description": "There is a sort field in the chm_mobile_docs table that the courts can set for sorting Document categories.\n Briefcase should this field for sorting. Briefcase needs to be updated to sort document categories \n based on the chm_mobile_doc.cmd_sort field",
  "id": "sorting-on-the-referral-detail-page;;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 1,
      "name": "@Smoke"
    },
    {
      "line": 1,
      "name": "@AMB-1394"
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
  "name": "User selects \"TEST_AUTOMATION\" and \"15-2594\"",
  "matchedColumns": [
    4
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 20,
  "name": "User verifies  Document Categories are sorted on the referral detail page (\"CMKA\", \"autotst\", \"32\", \"81452\")",
  "matchedColumns": [
    3,
    7,
    8,
    9
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_am_logged_into_Briefcase(UserInputData\u003e)"
});
formatter.result({
  "duration": 78189666924,
  "status": "passed"
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_select_a_user(UserInputData\u003e)"
});
formatter.result({
  "duration": 21152263977,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "TEST_AUTOMATION",
      "offset": 14
    },
    {
      "val": "15-2594",
      "offset": 36
    }
  ],
  "location": "Common_StepDefinitions.user_selects_and(String,String)"
});
formatter.result({
  "duration": 23597364635,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "CMKA",
      "offset": 76
    },
    {
      "val": "autotst",
      "offset": 84
    },
    {
      "val": "32",
      "offset": 95
    },
    {
      "val": "81452",
      "offset": 101
    }
  ],
  "location": "SortingOnTheReferralList_StepDefinitions.user_verifies_Document_Categories_are_sorted_on_the_referral_detail_page(String,String,String,String)"
});
formatter.result({
  "duration": 45182436794,
  "error_message": "org.openqa.selenium.WebDriverException: org.openqa.selenium.WebDriverException: Connection refused (Connection refused)\nBuild info: version: \u00273.141.59\u0027, revision: \u0027e82be7d358\u0027, time: \u00272018-11-14T08:17:03\u0027\nSystem info: host: \u0027Saltanat-MacBook-Pro.local\u0027, ip: \u0027fe80:0:0:0:4d9:a410:10f1:91cf%en0\u0027, os.name: \u0027Mac OS X\u0027, os.arch: \u0027x86_64\u0027, os.version: \u002710.15.7\u0027, java.version: \u00271.8.0_162\u0027\nDriver info: driver.version: AppiumDriver\n\tat io.appium.java_client.pagefactory.AppiumElementLocator$WaitingFunction.apply(AppiumElementLocator.java:192)\n\tat io.appium.java_client.pagefactory.AppiumElementLocator$WaitingFunction.apply(AppiumElementLocator.java:1)\n\tat org.openqa.selenium.support.ui.FluentWait.until(FluentWait.java:249)\n\tat io.appium.java_client.pagefactory.AppiumElementLocator.waitFor(AppiumElementLocator.java:99)\n\tat io.appium.java_client.pagefactory.AppiumElementLocator.findElements(AppiumElementLocator.java:140)\n\tat io.appium.java_client.pagefactory.interceptors.InterceptorOfAListOfElements.intercept(InterceptorOfAListOfElements.java:48)\n\tat $java.util.ArrayList$$EnhancerByCGLIB$$b5855c01.size(\u003cgenerated\u003e)\n\tat gov.uscourts.ao.mobileBriefcase.Pages.ReferralSortOrderPage.getDocumentCategories(ReferralSortOrderPage.java:134)\n\tat gov.uscourts.ao.mobileBriefcase.stepDefinitions.SortingOnTheReferralList_StepDefinitions.user_verifies_Document_Categories_are_sorted_on_the_referral_detail_page(SortingOnTheReferralList_StepDefinitions.java:62)\n\tat ✽.Then User verifies  Document Categories are sorted on the referral detail page (\"CMKA\", \"autotst\", \"32\", \"81452\")(CATEGORIES/SMOKE/SORTING_ON_THE_REFERRAL_DETAIL_PAGE_AMB-1394.feature:20)\nCaused by: org.openqa.selenium.WebDriverException: Connection refused (Connection refused)\nBuild info: version: \u00273.141.59\u0027, revision: \u0027e82be7d358\u0027, time: \u00272018-11-14T08:17:03\u0027\nSystem info: host: \u0027Saltanat-MacBook-Pro.local\u0027, ip: \u0027fe80:0:0:0:4d9:a410:10f1:91cf%en0\u0027, os.name: \u0027Mac OS X\u0027, os.arch: \u0027x86_64\u0027, os.version: \u002710.15.7\u0027, java.version: \u00271.8.0_162\u0027\nDriver info: driver.version: AppiumDriver\n\tat io.appium.java_client.remote.AppiumCommandExecutor.lambda$2(AppiumCommandExecutor.java:243)\n\tat java.util.Optional.orElseGet(Optional.java:267)\n\tat io.appium.java_client.remote.AppiumCommandExecutor.execute(AppiumCommandExecutor.java:242)\n\tat org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:552)\n\tat io.appium.java_client.DefaultGenericMobileDriver.execute(DefaultGenericMobileDriver.java:42)\n\tat io.appium.java_client.AppiumDriver.execute(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.execute(IOSDriver.java:1)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElements(RemoteWebDriver.java:353)\n\tat io.appium.java_client.DefaultGenericMobileDriver.findElements(DefaultGenericMobileDriver.java:54)\n\tat io.appium.java_client.AppiumDriver.findElements(AppiumDriver.java:152)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElementsByXPath(RemoteWebDriver.java:432)\n\tat io.appium.java_client.DefaultGenericMobileDriver.findElementsByXPath(DefaultGenericMobileDriver.java:156)\n\tat io.appium.java_client.AppiumDriver.findElementsByXPath(AppiumDriver.java:184)\n\tat org.openqa.selenium.By$ByXPath.findElements(By.java:348)\n\tat org.openqa.selenium.support.pagefactory.ByChained.findElements(ByChained.java:69)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElements(RemoteWebDriver.java:311)\n\tat io.appium.java_client.DefaultGenericMobileDriver.findElements(DefaultGenericMobileDriver.java:50)\n\tat io.appium.java_client.AppiumDriver.findElements(AppiumDriver.java:148)\n\tat io.appium.java_client.pagefactory.bys.ContentMappedBy.findElements(ContentMappedBy.java:54)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElements(RemoteWebDriver.java:311)\n\tat io.appium.java_client.DefaultGenericMobileDriver.findElements(DefaultGenericMobileDriver.java:50)\n\tat io.appium.java_client.AppiumDriver.findElements(AppiumDriver.java:148)\n\tat io.appium.java_client.pagefactory.AppiumElementLocator.lambda$1(AppiumElementLocator.java:142)\n\tat io.appium.java_client.pagefactory.AppiumElementLocator$WaitingFunction.apply(AppiumElementLocator.java:172)\n\tat io.appium.java_client.pagefactory.AppiumElementLocator$WaitingFunction.apply(AppiumElementLocator.java:1)\n\tat org.openqa.selenium.support.ui.FluentWait.until(FluentWait.java:249)\n\tat io.appium.java_client.pagefactory.AppiumElementLocator.waitFor(AppiumElementLocator.java:99)\n\tat io.appium.java_client.pagefactory.AppiumElementLocator.findElements(AppiumElementLocator.java:140)\n\tat io.appium.java_client.pagefactory.interceptors.InterceptorOfAListOfElements.intercept(InterceptorOfAListOfElements.java:48)\n\tat $java.util.ArrayList$$EnhancerByCGLIB$$b5855c01.size(\u003cgenerated\u003e)\n\tat gov.uscourts.ao.mobileBriefcase.Pages.ReferralSortOrderPage.getDocumentCategories(ReferralSortOrderPage.java:134)\n\tat gov.uscourts.ao.mobileBriefcase.stepDefinitions.SortingOnTheReferralList_StepDefinitions.user_verifies_Document_Categories_are_sorted_on_the_referral_detail_page(SortingOnTheReferralList_StepDefinitions.java:62)\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n\tat java.lang.reflect.Method.invoke(Method.java:498)\n\tat cucumber.runtime.Utils$1.call(Utils.java:40)\n\tat cucumber.runtime.Timeout.timeout(Timeout.java:16)\n\tat cucumber.runtime.Utils.invoke(Utils.java:34)\n\tat cucumber.runtime.java.JavaStepDefinition.execute(JavaStepDefinition.java:38)\n\tat cucumber.runtime.StepDefinitionMatch.runStep(StepDefinitionMatch.java:37)\n\tat cucumber.runtime.Runtime.runStep(Runtime.java:300)\n\tat cucumber.runtime.model.StepContainer.runStep(StepContainer.java:44)\n\tat cucumber.runtime.model.StepContainer.runSteps(StepContainer.java:39)\n\tat cucumber.runtime.model.CucumberScenario.run(CucumberScenario.java:44)\n\tat cucumber.runtime.junit.ExecutionUnitRunner.run(ExecutionUnitRunner.java:102)\n\tat org.junit.runners.Suite.runChild(Suite.java:128)\n\tat org.junit.runners.Suite.runChild(Suite.java:27)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.runtime.junit.ExamplesRunner.run(ExamplesRunner.java:59)\n\tat org.junit.runners.Suite.runChild(Suite.java:128)\n\tat org.junit.runners.Suite.runChild(Suite.java:27)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.runtime.junit.ScenarioOutlineRunner.run(ScenarioOutlineRunner.java:53)\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:63)\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:18)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.runtime.junit.FeatureRunner.run(FeatureRunner.java:70)\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:95)\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:38)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.api.junit.Cucumber.run(Cucumber.java:100)\n\tat org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:86)\n\tat org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:538)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:760)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:460)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:206)\nCaused by: java.net.ConnectException: Connection refused (Connection refused)\n\tat java.net.PlainSocketImpl.socketConnect(Native Method)\n\tat java.net.AbstractPlainSocketImpl.doConnect(AbstractPlainSocketImpl.java:350)\n\tat java.net.AbstractPlainSocketImpl.connectToAddress(AbstractPlainSocketImpl.java:204)\n\tat java.net.AbstractPlainSocketImpl.connect(AbstractPlainSocketImpl.java:188)\n\tat java.net.SocksSocketImpl.connect(SocksSocketImpl.java:392)\n\tat java.net.Socket.connect(Socket.java:589)\n\tat okhttp3.internal.platform.Platform.connectSocket(Platform.java:129)\n\tat okhttp3.internal.connection.RealConnection.connectSocket(RealConnection.java:245)\n\tat okhttp3.internal.connection.RealConnection.connect(RealConnection.java:165)\n\tat okhttp3.internal.connection.StreamAllocation.findConnection(StreamAllocation.java:257)\n\tat okhttp3.internal.connection.StreamAllocation.findHealthyConnection(StreamAllocation.java:135)\n\tat okhttp3.internal.connection.StreamAllocation.newStream(StreamAllocation.java:114)\n\tat okhttp3.internal.connection.ConnectInterceptor.intercept(ConnectInterceptor.java:42)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:147)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:121)\n\tat okhttp3.internal.cache.CacheInterceptor.intercept(CacheInterceptor.java:93)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:147)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:121)\n\tat okhttp3.internal.http.BridgeInterceptor.intercept(BridgeInterceptor.java:93)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:147)\n\tat okhttp3.internal.http.RetryAndFollowUpInterceptor.intercept(RetryAndFollowUpInterceptor.java:126)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:147)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:121)\n\tat okhttp3.RealCall.getResponseWithInterceptorChain(RealCall.java:200)\n\tat okhttp3.RealCall.execute(RealCall.java:77)\n\tat org.openqa.selenium.remote.internal.OkHttpClient.execute(OkHttpClient.java:103)\n\tat org.openqa.selenium.remote.HttpCommandExecutor.execute(HttpCommandExecutor.java:155)\n\tat io.appium.java_client.remote.AppiumCommandExecutor.execute(AppiumCommandExecutor.java:231)\n\t... 85 more\n",
  "status": "failed"
});
formatter.after({
  "duration": 1009817,
  "error_message": "org.openqa.selenium.WebDriverException: Connection refused (Connection refused)\nBuild info: version: \u00273.141.59\u0027, revision: \u0027e82be7d358\u0027, time: \u00272018-11-14T08:17:03\u0027\nSystem info: host: \u0027Saltanat-MacBook-Pro.local\u0027, ip: \u0027fe80:0:0:0:4d9:a410:10f1:91cf%en0\u0027, os.name: \u0027Mac OS X\u0027, os.arch: \u0027x86_64\u0027, os.version: \u002710.15.7\u0027, java.version: \u00271.8.0_162\u0027\nDriver info: driver.version: IOSDriver\n\tat io.appium.java_client.remote.AppiumCommandExecutor.lambda$2(AppiumCommandExecutor.java:243)\n\tat java.util.Optional.orElseGet(Optional.java:267)\n\tat io.appium.java_client.remote.AppiumCommandExecutor.execute(AppiumCommandExecutor.java:242)\n\tat org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:552)\n\tat io.appium.java_client.DefaultGenericMobileDriver.execute(DefaultGenericMobileDriver.java:42)\n\tat io.appium.java_client.AppiumDriver.execute(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.execute(IOSDriver.java:1)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:323)\n\tat io.appium.java_client.DefaultGenericMobileDriver.findElement(DefaultGenericMobileDriver.java:62)\n\tat io.appium.java_client.AppiumDriver.findElement(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.findElement(IOSDriver.java:1)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElementByXPath(RemoteWebDriver.java:428)\n\tat io.appium.java_client.DefaultGenericMobileDriver.findElementByXPath(DefaultGenericMobileDriver.java:152)\n\tat io.appium.java_client.AppiumDriver.findElementByXPath(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.findElementByXPath(IOSDriver.java:1)\n\tat org.openqa.selenium.By$ByXPath.findElement(By.java:353)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:315)\n\tat io.appium.java_client.DefaultGenericMobileDriver.findElement(DefaultGenericMobileDriver.java:58)\n\tat io.appium.java_client.AppiumDriver.findElement(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.findElement(IOSDriver.java:1)\n\tat org.openqa.selenium.support.ui.ExpectedConditions$6.apply(ExpectedConditions.java:182)\n\tat org.openqa.selenium.support.ui.ExpectedConditions$6.apply(ExpectedConditions.java:179)\n\tat org.openqa.selenium.support.ui.FluentWait.until(FluentWait.java:249)\n\tat gov.uscourts.ao.mobileBriefcase.common.Page.waitForPresenceOfElementLocated(Page.java:17)\n\tat gov.uscourts.ao.mobileBriefcase.common.Actions.contains(Actions.java:67)\n\tat gov.uscourts.ao.mobileBriefcase.Pages.JenieLoginPage.logout(JenieLoginPage.java:185)\n\tat gov.uscourts.ao.mobileBriefcase.stepDefinitions.Hook.tearDown(Hook.java:19)\n\tat sun.reflect.GeneratedMethodAccessor32.invoke(Unknown Source)\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n\tat java.lang.reflect.Method.invoke(Method.java:498)\n\tat cucumber.runtime.Utils$1.call(Utils.java:40)\n\tat cucumber.runtime.Timeout.timeout(Timeout.java:16)\n\tat cucumber.runtime.Utils.invoke(Utils.java:34)\n\tat cucumber.runtime.java.JavaHookDefinition.execute(JavaHookDefinition.java:60)\n\tat cucumber.runtime.Runtime.runHookIfTagsMatch(Runtime.java:224)\n\tat cucumber.runtime.Runtime.runHooks(Runtime.java:212)\n\tat cucumber.runtime.Runtime.runAfterHooks(Runtime.java:206)\n\tat cucumber.runtime.model.CucumberScenario.run(CucumberScenario.java:46)\n\tat cucumber.runtime.junit.ExecutionUnitRunner.run(ExecutionUnitRunner.java:102)\n\tat org.junit.runners.Suite.runChild(Suite.java:128)\n\tat org.junit.runners.Suite.runChild(Suite.java:27)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.runtime.junit.ExamplesRunner.run(ExamplesRunner.java:59)\n\tat org.junit.runners.Suite.runChild(Suite.java:128)\n\tat org.junit.runners.Suite.runChild(Suite.java:27)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.runtime.junit.ScenarioOutlineRunner.run(ScenarioOutlineRunner.java:53)\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:63)\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:18)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.runtime.junit.FeatureRunner.run(FeatureRunner.java:70)\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:95)\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:38)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.api.junit.Cucumber.run(Cucumber.java:100)\n\tat org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:86)\n\tat org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:538)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:760)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:460)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:206)\nCaused by: java.net.ConnectException: Connection refused (Connection refused)\n\tat java.net.PlainSocketImpl.socketConnect(Native Method)\n\tat java.net.AbstractPlainSocketImpl.doConnect(AbstractPlainSocketImpl.java:350)\n\tat java.net.AbstractPlainSocketImpl.connectToAddress(AbstractPlainSocketImpl.java:204)\n\tat java.net.AbstractPlainSocketImpl.connect(AbstractPlainSocketImpl.java:188)\n\tat java.net.SocksSocketImpl.connect(SocksSocketImpl.java:392)\n\tat java.net.Socket.connect(Socket.java:589)\n\tat okhttp3.internal.platform.Platform.connectSocket(Platform.java:129)\n\tat okhttp3.internal.connection.RealConnection.connectSocket(RealConnection.java:245)\n\tat okhttp3.internal.connection.RealConnection.connect(RealConnection.java:165)\n\tat okhttp3.internal.connection.StreamAllocation.findConnection(StreamAllocation.java:257)\n\tat okhttp3.internal.connection.StreamAllocation.findHealthyConnection(StreamAllocation.java:135)\n\tat okhttp3.internal.connection.StreamAllocation.newStream(StreamAllocation.java:114)\n\tat okhttp3.internal.connection.ConnectInterceptor.intercept(ConnectInterceptor.java:42)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:147)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:121)\n\tat okhttp3.internal.cache.CacheInterceptor.intercept(CacheInterceptor.java:93)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:147)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:121)\n\tat okhttp3.internal.http.BridgeInterceptor.intercept(BridgeInterceptor.java:93)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:147)\n\tat okhttp3.internal.http.RetryAndFollowUpInterceptor.intercept(RetryAndFollowUpInterceptor.java:126)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:147)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:121)\n\tat okhttp3.RealCall.getResponseWithInterceptorChain(RealCall.java:200)\n\tat okhttp3.RealCall.execute(RealCall.java:77)\n\tat org.openqa.selenium.remote.internal.OkHttpClient.execute(OkHttpClient.java:103)\n\tat org.openqa.selenium.remote.HttpCommandExecutor.execute(HttpCommandExecutor.java:155)\n\tat io.appium.java_client.remote.AppiumCommandExecutor.execute(AppiumCommandExecutor.java:231)\n\t... 78 more\n",
  "status": "failed"
});
formatter.uri("CATEGORIES/SMOKE/SORTING_ON_THE_REFERRAL_LIST_PAGE_AMB-1010.feature");
formatter.feature({
  "line": 2,
  "name": "Sorting on the Referral List Page",
  "description": "",
  "id": "sorting-on-the-referral-list-page",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@Smoke"
    },
    {
      "line": 1,
      "name": "@AMB-1010"
    }
  ]
});
formatter.scenarioOutline({
  "line": 5,
  "name": "",
  "description": "There is a sorting feature on the referral list page that enables users to sort referrals by date referred or case number.  \nThe default is by date referred in descending order (newest first).",
  "id": "sorting-on-the-referral-list-page;",
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
        "Benton"
      ],
      "line": 15
    }
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 17,
  "name": "User selects a  \"\u003crefCategory\u003e\"",
  "keyword": "When "
});
formatter.step({
  "line": 18,
  "name": "User verify the Date Down Arrow is selected by default and that the referrals are sorted by referred date in descending order (newest first).",
  "keyword": "Then "
});
formatter.step({
  "line": 19,
  "name": "User clicks on the Date Up Arrow button and verifies the referrals are sorted by referred date in ascending order (oldest first).",
  "keyword": "Then "
});
formatter.step({
  "line": 20,
  "name": "User clicks on the Case Down Arrow button and verifies the referrals are sorted by case number in descending order",
  "keyword": "And "
});
formatter.step({
  "line": 21,
  "name": "User clicks on the Case Down Arrow button and verifies the referrals are sorted by case number in ascending order.",
  "keyword": "Then "
});
formatter.examples({
  "line": 23,
  "name": "",
  "description": "",
  "id": "sorting-on-the-referral-list-page;;",
  "rows": [
    {
      "cells": [
        "server",
        "refCategory"
      ],
      "line": 24,
      "id": "sorting-on-the-referral-list-page;;;1"
    },
    {
      "cells": [
        "CMKA",
        "Application"
      ],
      "line": 25,
      "id": "sorting-on-the-referral-list-page;;;2"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 134427862,
  "error_message": "org.openqa.selenium.WebDriverException: Connection refused (Connection refused)\nBuild info: version: \u00273.141.59\u0027, revision: \u0027e82be7d358\u0027, time: \u00272018-11-14T08:17:03\u0027\nSystem info: host: \u0027Saltanat-MacBook-Pro.local\u0027, ip: \u0027fe80:0:0:0:4d9:a410:10f1:91cf%en0\u0027, os.name: \u0027Mac OS X\u0027, os.arch: \u0027x86_64\u0027, os.version: \u002710.15.7\u0027, java.version: \u00271.8.0_162\u0027\nDriver info: driver.version: RemoteWebDriver\n\tat io.appium.java_client.remote.AppiumCommandExecutor.lambda$2(AppiumCommandExecutor.java:243)\n\tat java.util.Optional.orElseGet(Optional.java:267)\n\tat io.appium.java_client.remote.AppiumCommandExecutor.execute(AppiumCommandExecutor.java:242)\n\tat org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:552)\n\tat io.appium.java_client.DefaultGenericMobileDriver.execute(DefaultGenericMobileDriver.java:46)\n\tat io.appium.java_client.AppiumDriver.execute(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.execute(IOSDriver.java:1)\n\tat org.openqa.selenium.remote.RemoteWebDriver.quit(RemoteWebDriver.java:452)\n\tat gov.uscourts.ao.mobileBriefcase.common.Base.closeIOSDriver(Base.java:97)\n\tat gov.uscourts.ao.mobileBriefcase.stepDefinitions.Hook.setUp(Hook.java:13)\n\tat sun.reflect.GeneratedMethodAccessor31.invoke(Unknown Source)\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n\tat java.lang.reflect.Method.invoke(Method.java:498)\n\tat cucumber.runtime.Utils$1.call(Utils.java:40)\n\tat cucumber.runtime.Timeout.timeout(Timeout.java:16)\n\tat cucumber.runtime.Utils.invoke(Utils.java:34)\n\tat cucumber.runtime.java.JavaHookDefinition.execute(JavaHookDefinition.java:60)\n\tat cucumber.runtime.Runtime.runHookIfTagsMatch(Runtime.java:224)\n\tat cucumber.runtime.Runtime.runHooks(Runtime.java:212)\n\tat cucumber.runtime.Runtime.runBeforeHooks(Runtime.java:202)\n\tat cucumber.runtime.model.CucumberScenario.run(CucumberScenario.java:40)\n\tat cucumber.runtime.junit.ExecutionUnitRunner.run(ExecutionUnitRunner.java:102)\n\tat org.junit.runners.Suite.runChild(Suite.java:128)\n\tat org.junit.runners.Suite.runChild(Suite.java:27)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.runtime.junit.ExamplesRunner.run(ExamplesRunner.java:59)\n\tat org.junit.runners.Suite.runChild(Suite.java:128)\n\tat org.junit.runners.Suite.runChild(Suite.java:27)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.runtime.junit.ScenarioOutlineRunner.run(ScenarioOutlineRunner.java:53)\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:63)\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:18)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.runtime.junit.FeatureRunner.run(FeatureRunner.java:70)\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:95)\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:38)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.api.junit.Cucumber.run(Cucumber.java:100)\n\tat org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:86)\n\tat org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:538)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:760)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:460)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:206)\nCaused by: java.net.ConnectException: Connection refused (Connection refused)\n\tat java.net.PlainSocketImpl.socketConnect(Native Method)\n\tat java.net.AbstractPlainSocketImpl.doConnect(AbstractPlainSocketImpl.java:350)\n\tat java.net.AbstractPlainSocketImpl.connectToAddress(AbstractPlainSocketImpl.java:204)\n\tat java.net.AbstractPlainSocketImpl.connect(AbstractPlainSocketImpl.java:188)\n\tat java.net.SocksSocketImpl.connect(SocksSocketImpl.java:392)\n\tat java.net.Socket.connect(Socket.java:589)\n\tat okhttp3.internal.platform.Platform.connectSocket(Platform.java:129)\n\tat okhttp3.internal.connection.RealConnection.connectSocket(RealConnection.java:245)\n\tat okhttp3.internal.connection.RealConnection.connect(RealConnection.java:165)\n\tat okhttp3.internal.connection.StreamAllocation.findConnection(StreamAllocation.java:257)\n\tat okhttp3.internal.connection.StreamAllocation.findHealthyConnection(StreamAllocation.java:135)\n\tat okhttp3.internal.connection.StreamAllocation.newStream(StreamAllocation.java:114)\n\tat okhttp3.internal.connection.ConnectInterceptor.intercept(ConnectInterceptor.java:42)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:147)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:121)\n\tat okhttp3.internal.cache.CacheInterceptor.intercept(CacheInterceptor.java:93)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:147)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:121)\n\tat okhttp3.internal.http.BridgeInterceptor.intercept(BridgeInterceptor.java:93)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:147)\n\tat okhttp3.internal.http.RetryAndFollowUpInterceptor.intercept(RetryAndFollowUpInterceptor.java:126)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:147)\n\tat okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:121)\n\tat okhttp3.RealCall.getResponseWithInterceptorChain(RealCall.java:200)\n\tat okhttp3.RealCall.execute(RealCall.java:77)\n\tat org.openqa.selenium.remote.internal.OkHttpClient.execute(OkHttpClient.java:103)\n\tat org.openqa.selenium.remote.HttpCommandExecutor.execute(HttpCommandExecutor.java:155)\n\tat io.appium.java_client.remote.AppiumCommandExecutor.execute(AppiumCommandExecutor.java:231)\n\t... 61 more\n",
  "status": "failed"
});
formatter.scenario({
  "line": 25,
  "name": "",
  "description": "There is a sorting feature on the referral list page that enables users to sort referrals by date referred or case number.  \nThe default is by date referred in descending order (newest first).",
  "id": "sorting-on-the-referral-list-page;;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 1,
      "name": "@Smoke"
    },
    {
      "line": 1,
      "name": "@AMB-1010"
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
        "Benton"
      ],
      "line": 15
    }
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 17,
  "name": "User selects a  \"Application\"",
  "matchedColumns": [
    1
  ],
  "keyword": "When "
});
formatter.step({
  "line": 18,
  "name": "User verify the Date Down Arrow is selected by default and that the referrals are sorted by referred date in descending order (newest first).",
  "keyword": "Then "
});
formatter.step({
  "line": 19,
  "name": "User clicks on the Date Up Arrow button and verifies the referrals are sorted by referred date in ascending order (oldest first).",
  "keyword": "Then "
});
formatter.step({
  "line": 20,
  "name": "User clicks on the Case Down Arrow button and verifies the referrals are sorted by case number in descending order",
  "keyword": "And "
});
formatter.step({
  "line": 21,
  "name": "User clicks on the Case Down Arrow button and verifies the referrals are sorted by case number in ascending order.",
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
formatter.match({
  "arguments": [
    {
      "val": "Application",
      "offset": 17
    }
  ],
  "location": "Common_StepDefinitions.user_selects_a(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "SortingOnTheReferralList_StepDefinitions.user_verify_the_Date_Down_Arrow_is_selected_by_default_and_that_the_referrals_are_sorted_by_referred_date_in_descending_order_newest_first()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "SortingOnTheReferralList_StepDefinitions.user_clicks_on_the_Date_Up_Arrow_button_and_verifies_the_referrals_are_sorted_by_referred_date_in_ascending_order_oldest_first()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "SortingOnTheReferralList_StepDefinitions.user_clicks_on_the_Case_Down_Arrow_button_and_verifies_the_referrals_are_sorted_by_case_number_in_descending_order()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "SortingOnTheReferralList_StepDefinitions.user_clicks_on_the_Case_Down_Arrow_button_and_verifies_the_referrals_are_sorted_by_case_number_in_ascending_order()"
});
formatter.result({
  "status": "skipped"
});
formatter.after({
  "duration": 234204,
  "error_message": "org.openqa.selenium.NoSuchSessionException: Session ID is null. Using WebDriver after calling quit()?\nBuild info: version: \u00273.141.59\u0027, revision: \u0027e82be7d358\u0027, time: \u00272018-11-14T08:17:03\u0027\nSystem info: host: \u0027Saltanat-MacBook-Pro.local\u0027, ip: \u0027fe80:0:0:0:4d9:a410:10f1:91cf%en0\u0027, os.name: \u0027Mac OS X\u0027, os.arch: \u0027x86_64\u0027, os.version: \u002710.15.7\u0027, java.version: \u00271.8.0_162\u0027\nDriver info: driver.version: IOSDriver\n\tat org.openqa.selenium.remote.HttpCommandExecutor.execute(HttpCommandExecutor.java:125)\n\tat io.appium.java_client.remote.AppiumCommandExecutor.execute(AppiumCommandExecutor.java:231)\n\tat org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:552)\n\tat io.appium.java_client.DefaultGenericMobileDriver.execute(DefaultGenericMobileDriver.java:42)\n\tat io.appium.java_client.AppiumDriver.execute(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.execute(IOSDriver.java:1)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:323)\n\tat io.appium.java_client.DefaultGenericMobileDriver.findElement(DefaultGenericMobileDriver.java:62)\n\tat io.appium.java_client.AppiumDriver.findElement(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.findElement(IOSDriver.java:1)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElementByXPath(RemoteWebDriver.java:428)\n\tat io.appium.java_client.DefaultGenericMobileDriver.findElementByXPath(DefaultGenericMobileDriver.java:152)\n\tat io.appium.java_client.AppiumDriver.findElementByXPath(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.findElementByXPath(IOSDriver.java:1)\n\tat org.openqa.selenium.By$ByXPath.findElement(By.java:353)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:315)\n\tat io.appium.java_client.DefaultGenericMobileDriver.findElement(DefaultGenericMobileDriver.java:58)\n\tat io.appium.java_client.AppiumDriver.findElement(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.findElement(IOSDriver.java:1)\n\tat org.openqa.selenium.support.ui.ExpectedConditions$6.apply(ExpectedConditions.java:182)\n\tat org.openqa.selenium.support.ui.ExpectedConditions$6.apply(ExpectedConditions.java:179)\n\tat org.openqa.selenium.support.ui.FluentWait.until(FluentWait.java:249)\n\tat gov.uscourts.ao.mobileBriefcase.common.Page.waitForPresenceOfElementLocated(Page.java:17)\n\tat gov.uscourts.ao.mobileBriefcase.common.Actions.contains(Actions.java:67)\n\tat gov.uscourts.ao.mobileBriefcase.Pages.JenieLoginPage.logout(JenieLoginPage.java:185)\n\tat gov.uscourts.ao.mobileBriefcase.stepDefinitions.Hook.tearDown(Hook.java:19)\n\tat sun.reflect.GeneratedMethodAccessor32.invoke(Unknown Source)\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n\tat java.lang.reflect.Method.invoke(Method.java:498)\n\tat cucumber.runtime.Utils$1.call(Utils.java:40)\n\tat cucumber.runtime.Timeout.timeout(Timeout.java:16)\n\tat cucumber.runtime.Utils.invoke(Utils.java:34)\n\tat cucumber.runtime.java.JavaHookDefinition.execute(JavaHookDefinition.java:60)\n\tat cucumber.runtime.Runtime.runHookIfTagsMatch(Runtime.java:224)\n\tat cucumber.runtime.Runtime.runHooks(Runtime.java:212)\n\tat cucumber.runtime.Runtime.runAfterHooks(Runtime.java:206)\n\tat cucumber.runtime.model.CucumberScenario.run(CucumberScenario.java:46)\n\tat cucumber.runtime.junit.ExecutionUnitRunner.run(ExecutionUnitRunner.java:102)\n\tat org.junit.runners.Suite.runChild(Suite.java:128)\n\tat org.junit.runners.Suite.runChild(Suite.java:27)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.runtime.junit.ExamplesRunner.run(ExamplesRunner.java:59)\n\tat org.junit.runners.Suite.runChild(Suite.java:128)\n\tat org.junit.runners.Suite.runChild(Suite.java:27)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.runtime.junit.ScenarioOutlineRunner.run(ScenarioOutlineRunner.java:53)\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:63)\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:18)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.runtime.junit.FeatureRunner.run(FeatureRunner.java:70)\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:95)\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:38)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.api.junit.Cucumber.run(Cucumber.java:100)\n\tat org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:86)\n\tat org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:538)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:760)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:460)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:206)\n",
  "status": "failed"
});
formatter.uri("CATEGORIES/SMOKE/STAFF_ASSIGNMENTS_AMB1021.feature");
formatter.feature({
  "line": 2,
  "name": "Staff Assignments",
  "description": "",
  "id": "staff-assignments",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@Smoke"
    },
    {
      "line": 1,
      "name": "@AMB-1021"
    }
  ]
});
formatter.scenarioOutline({
  "line": 6,
  "name": "",
  "description": "Staff members can be assigned to referrals and/or just cases. \nThis task is to verify that staff assignments are displaying on the referral list page",
  "id": "staff-assignments;",
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
  "line": 18,
  "name": "User selects \"MOTION\" and \"15-3314\"",
  "keyword": "Then "
});
formatter.step({
  "line": 19,
  "name": "User observes a collapsible panel entitled \"Assignments\" displays",
  "keyword": "Then "
});
formatter.step({
  "line": 20,
  "name": "User verifies the staff assignments associated with the referral by using \"\u003cdbType\u003e\" , \"\u003ccmr_cs_caseid\u003e\" , \"\u003ccha_ju_pe_id\u003e\" , \"\u003ccmr_cyv_code\u003e\" ,",
  "keyword": "And "
});
formatter.examples({
  "line": 22,
  "name": "",
  "description": "",
  "id": "staff-assignments;;",
  "rows": [
    {
      "cells": [
        "dbType",
        "cha_ju_pe_id",
        "cmr_cs_caseid",
        "cmr_cyv_code",
        "refCat"
      ],
      "line": 23,
      "id": "staff-assignments;;;1"
    },
    {
      "cells": [
        "CMKA",
        "32",
        "82226",
        "motpet",
        "MOTION"
      ],
      "line": 24,
      "id": "staff-assignments;;;2"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 117065492,
  "status": "passed"
});
formatter.scenario({
  "line": 24,
  "name": "",
  "description": "Staff members can be assigned to referrals and/or just cases. \nThis task is to verify that staff assignments are displaying on the referral list page",
  "id": "staff-assignments;;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 1,
      "name": "@Smoke"
    },
    {
      "line": 1,
      "name": "@AMB-1021"
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
  "line": 18,
  "name": "User selects \"MOTION\" and \"15-3314\"",
  "keyword": "Then "
});
formatter.step({
  "line": 19,
  "name": "User observes a collapsible panel entitled \"Assignments\" displays",
  "keyword": "Then "
});
formatter.step({
  "line": 20,
  "name": "User verifies the staff assignments associated with the referral by using \"CMKA\" , \"82226\" , \"32\" , \"motpet\" ,",
  "matchedColumns": [
    0,
    1,
    2,
    3
  ],
  "keyword": "And "
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_am_logged_into_Briefcase(UserInputData\u003e)"
});
formatter.result({
  "duration": 94994087,
  "error_message": "org.openqa.selenium.NoSuchSessionException: Session ID is null. Using WebDriver after calling quit()?\nBuild info: version: \u00273.141.59\u0027, revision: \u0027e82be7d358\u0027, time: \u00272018-11-14T08:17:03\u0027\nSystem info: host: \u0027Saltanat-MacBook-Pro.local\u0027, ip: \u0027fe80:0:0:0:4d9:a410:10f1:91cf%en0\u0027, os.name: \u0027Mac OS X\u0027, os.arch: \u0027x86_64\u0027, os.version: \u002710.15.7\u0027, java.version: \u00271.8.0_162\u0027\nDriver info: driver.version: IOSDriver\n\tat org.openqa.selenium.remote.HttpCommandExecutor.execute(HttpCommandExecutor.java:125)\n\tat io.appium.java_client.remote.AppiumCommandExecutor.execute(AppiumCommandExecutor.java:231)\n\tat org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:552)\n\tat io.appium.java_client.DefaultGenericMobileDriver.execute(DefaultGenericMobileDriver.java:46)\n\tat io.appium.java_client.AppiumDriver.execute(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.execute(IOSDriver.java:1)\n\tat io.appium.java_client.HasSessionDetails.getSessionDetails(HasSessionDetails.java:39)\n\tat io.appium.java_client.HasSessionDetails.getSessionDetail(HasSessionDetails.java:56)\n\tat io.appium.java_client.HasSessionDetails.getPlatformName(HasSessionDetails.java:65)\n\tat io.appium.java_client.pagefactory.AppiumFieldDecorator.\u003cinit\u003e(AppiumFieldDecorator.java:96)\n\tat io.appium.java_client.pagefactory.AppiumFieldDecorator.\u003cinit\u003e(AppiumFieldDecorator.java:144)\n\tat gov.uscourts.ao.mobileBriefcase.Pages.JenieLoginPage.\u003cinit\u003e(JenieLoginPage.java:33)\n\tat gov.uscourts.ao.mobileBriefcase.stepDefinitions.JenieLogin_StepDefinitions.i_am_logged_into_Briefcase(JenieLogin_StepDefinitions.java:18)\n\tat ✽.Given I am logged into Briefcase(CATEGORIES/SMOKE/STAFF_ASSIGNMENTS_AMB1021.feature:10)\n",
  "status": "failed"
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_select_a_user(UserInputData\u003e)"
});
formatter.result({
  "status": "skipped"
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
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "Assignments",
      "offset": 44
    }
  ],
  "location": "Assignment_StepDefinitions.user_observes_a_collapsible_panel_entitled_displays(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "CMKA",
      "offset": 75
    },
    {
      "val": "82226",
      "offset": 84
    },
    {
      "val": "32",
      "offset": 94
    },
    {
      "val": "motpet",
      "offset": 101
    }
  ],
  "location": "Assignment_StepDefinitions.user_verifies_the_staff_assignments_associated_with_the_referral_by_using(String,String,String,String)"
});
formatter.result({
  "status": "skipped"
});
formatter.after({
  "duration": 178334,
  "error_message": "org.openqa.selenium.NoSuchSessionException: Session ID is null. Using WebDriver after calling quit()?\nBuild info: version: \u00273.141.59\u0027, revision: \u0027e82be7d358\u0027, time: \u00272018-11-14T08:17:03\u0027\nSystem info: host: \u0027Saltanat-MacBook-Pro.local\u0027, ip: \u0027fe80:0:0:0:4d9:a410:10f1:91cf%en0\u0027, os.name: \u0027Mac OS X\u0027, os.arch: \u0027x86_64\u0027, os.version: \u002710.15.7\u0027, java.version: \u00271.8.0_162\u0027\nDriver info: driver.version: IOSDriver\n\tat org.openqa.selenium.remote.HttpCommandExecutor.execute(HttpCommandExecutor.java:125)\n\tat io.appium.java_client.remote.AppiumCommandExecutor.execute(AppiumCommandExecutor.java:231)\n\tat org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:552)\n\tat io.appium.java_client.DefaultGenericMobileDriver.execute(DefaultGenericMobileDriver.java:42)\n\tat io.appium.java_client.AppiumDriver.execute(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.execute(IOSDriver.java:1)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:323)\n\tat io.appium.java_client.DefaultGenericMobileDriver.findElement(DefaultGenericMobileDriver.java:62)\n\tat io.appium.java_client.AppiumDriver.findElement(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.findElement(IOSDriver.java:1)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElementByXPath(RemoteWebDriver.java:428)\n\tat io.appium.java_client.DefaultGenericMobileDriver.findElementByXPath(DefaultGenericMobileDriver.java:152)\n\tat io.appium.java_client.AppiumDriver.findElementByXPath(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.findElementByXPath(IOSDriver.java:1)\n\tat org.openqa.selenium.By$ByXPath.findElement(By.java:353)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:315)\n\tat io.appium.java_client.DefaultGenericMobileDriver.findElement(DefaultGenericMobileDriver.java:58)\n\tat io.appium.java_client.AppiumDriver.findElement(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.findElement(IOSDriver.java:1)\n\tat org.openqa.selenium.support.ui.ExpectedConditions$6.apply(ExpectedConditions.java:182)\n\tat org.openqa.selenium.support.ui.ExpectedConditions$6.apply(ExpectedConditions.java:179)\n\tat org.openqa.selenium.support.ui.FluentWait.until(FluentWait.java:249)\n\tat gov.uscourts.ao.mobileBriefcase.common.Page.waitForPresenceOfElementLocated(Page.java:17)\n\tat gov.uscourts.ao.mobileBriefcase.common.Actions.contains(Actions.java:67)\n\tat gov.uscourts.ao.mobileBriefcase.Pages.JenieLoginPage.logout(JenieLoginPage.java:185)\n\tat gov.uscourts.ao.mobileBriefcase.stepDefinitions.Hook.tearDown(Hook.java:19)\n\tat sun.reflect.GeneratedMethodAccessor32.invoke(Unknown Source)\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n\tat java.lang.reflect.Method.invoke(Method.java:498)\n\tat cucumber.runtime.Utils$1.call(Utils.java:40)\n\tat cucumber.runtime.Timeout.timeout(Timeout.java:16)\n\tat cucumber.runtime.Utils.invoke(Utils.java:34)\n\tat cucumber.runtime.java.JavaHookDefinition.execute(JavaHookDefinition.java:60)\n\tat cucumber.runtime.Runtime.runHookIfTagsMatch(Runtime.java:224)\n\tat cucumber.runtime.Runtime.runHooks(Runtime.java:212)\n\tat cucumber.runtime.Runtime.runAfterHooks(Runtime.java:206)\n\tat cucumber.runtime.model.CucumberScenario.run(CucumberScenario.java:46)\n\tat cucumber.runtime.junit.ExecutionUnitRunner.run(ExecutionUnitRunner.java:102)\n\tat org.junit.runners.Suite.runChild(Suite.java:128)\n\tat org.junit.runners.Suite.runChild(Suite.java:27)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.runtime.junit.ExamplesRunner.run(ExamplesRunner.java:59)\n\tat org.junit.runners.Suite.runChild(Suite.java:128)\n\tat org.junit.runners.Suite.runChild(Suite.java:27)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.runtime.junit.ScenarioOutlineRunner.run(ScenarioOutlineRunner.java:53)\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:63)\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:18)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.runtime.junit.FeatureRunner.run(FeatureRunner.java:70)\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:95)\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:38)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.api.junit.Cucumber.run(Cucumber.java:100)\n\tat org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:86)\n\tat org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:538)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:760)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:460)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:206)\n",
  "status": "failed"
});
formatter.uri("CATEGORIES/SMOKE/VOTE_INFORMATION_PANEL-AMB_1036.feature");
formatter.feature({
  "line": 2,
  "name": "Display Vote Information Panel, filer info and judge\u0027s initials",
  "description": "Display Actions Panel and actions",
  "id": "display-vote-information-panel,-filer-info-and-judge\u0027s-initials",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@AMB"
    },
    {
      "line": 1,
      "name": "@Smoke"
    }
  ]
});
formatter.scenarioOutline({
  "comments": [
    {
      "line": 5,
      "value": "#dependent on AMB-2257"
    }
  ],
  "line": 7,
  "name": "",
  "description": "If a referral requires voting, a collapsible Vote Information panel displays.  When expanded, it displays the following information:\n1.  The name and party type of the person who filed the motion/petition\n2.  The judges initials who are on the panel\n3.  The relief on which the judge is ruling\n4.  Each judge\u0027s vote and the date they voted",
  "id": "display-vote-information-panel,-filer-info-and-judge\u0027s-initials;",
  "type": "scenario_outline",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 6,
      "name": "@AMB-1036"
    }
  ]
});
formatter.step({
  "line": 14,
  "name": "I am logged into Briefcase",
  "rows": [
    {
      "cells": [
        "environment",
        "userName",
        "password",
        "courtId"
      ],
      "line": 15
    },
    {
      "cells": [
        "Integration",
        "s haenni",
        "Test2024!",
        "test"
      ],
      "line": 16
    }
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 17,
  "name": "I select a user",
  "rows": [
    {
      "cells": [
        "role",
        "briefcaseUser"
      ],
      "line": 18
    },
    {
      "cells": [
        "Appellate Judges",
        "Colloton"
      ],
      "line": 19
    }
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 21,
  "name": "User selects \"\u003crefCategory\u003e\" and \"\u003ccaseNum\u003e\"",
  "keyword": "Then "
});
formatter.step({
  "line": 22,
  "name": "User observes ( \"\u003cdbType\u003e\" ) the \"Vote_Information\" panel displays.   This should only display if the referral requires voting",
  "keyword": "Given "
});
formatter.step({
  "line": 23,
  "name": "for each referral, observes the filer\u0027s name  first initial of pr_middle_name gn_display  party type and date filed displays in a light blue heading. Use  \"\u003ccmr_ju_pe_id\u003e\" , \"\u003ccmr_cs_caseid\u003e\" ,\"\u003ccmr_cyv_code\u003e\" , \"\u003cccr_id\u003e\" .",
  "rows": [
    {
      "cells": [
        "courtId"
      ],
      "line": 24
    },
    {
      "cells": [
        "test"
      ],
      "line": 25
    }
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 26,
  "name": "User checks each judge\u0027s vote  and the date  displays under their initials, using  \"\u003cccr_id\u003e\"",
  "rows": [
    {
      "cells": [
        "courtId"
      ],
      "line": 27
    },
    {
      "cells": [
        "test"
      ],
      "line": 28
    }
  ],
  "keyword": "Then "
});
formatter.examples({
  "line": 30,
  "name": "",
  "description": "",
  "id": "display-vote-information-panel,-filer-info-and-judge\u0027s-initials;;",
  "rows": [
    {
      "cells": [
        "server",
        "refCategory",
        "caseNum",
        "dbType",
        "cmr_cs_caseid",
        "cmr_ju_pe_id",
        "cmr_cyv_code",
        "ccr_id"
      ],
      "line": 31,
      "id": "display-vote-information-panel,-filer-info-and-judge\u0027s-initials;;;1"
    },
    {
      "cells": [
        "CMKA",
        "PETITION",
        "15-3314",
        "CMKA",
        "82226",
        "32",
        "prhr",
        "34870"
      ],
      "line": 32,
      "id": "display-vote-information-panel,-filer-info-and-judge\u0027s-initials;;;2"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 114061943,
  "status": "passed"
});
formatter.scenario({
  "line": 32,
  "name": "",
  "description": "If a referral requires voting, a collapsible Vote Information panel displays.  When expanded, it displays the following information:\n1.  The name and party type of the person who filed the motion/petition\n2.  The judges initials who are on the panel\n3.  The relief on which the judge is ruling\n4.  Each judge\u0027s vote and the date they voted",
  "id": "display-vote-information-panel,-filer-info-and-judge\u0027s-initials;;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 6,
      "name": "@AMB-1036"
    },
    {
      "line": 1,
      "name": "@Smoke"
    },
    {
      "line": 1,
      "name": "@AMB"
    }
  ]
});
formatter.step({
  "line": 14,
  "name": "I am logged into Briefcase",
  "rows": [
    {
      "cells": [
        "environment",
        "userName",
        "password",
        "courtId"
      ],
      "line": 15
    },
    {
      "cells": [
        "Integration",
        "s haenni",
        "Test2024!",
        "test"
      ],
      "line": 16
    }
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 17,
  "name": "I select a user",
  "rows": [
    {
      "cells": [
        "role",
        "briefcaseUser"
      ],
      "line": 18
    },
    {
      "cells": [
        "Appellate Judges",
        "Colloton"
      ],
      "line": 19
    }
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 21,
  "name": "User selects \"PETITION\" and \"15-3314\"",
  "matchedColumns": [
    1,
    2
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 22,
  "name": "User observes ( \"CMKA\" ) the \"Vote_Information\" panel displays.   This should only display if the referral requires voting",
  "matchedColumns": [
    3
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 23,
  "name": "for each referral, observes the filer\u0027s name  first initial of pr_middle_name gn_display  party type and date filed displays in a light blue heading. Use  \"32\" , \"82226\" ,\"prhr\" , \"34870\" .",
  "matchedColumns": [
    4,
    5,
    6,
    7
  ],
  "rows": [
    {
      "cells": [
        "courtId"
      ],
      "line": 24
    },
    {
      "cells": [
        "test"
      ],
      "line": 25
    }
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 26,
  "name": "User checks each judge\u0027s vote  and the date  displays under their initials, using  \"34870\"",
  "matchedColumns": [
    7
  ],
  "rows": [
    {
      "cells": [
        "courtId"
      ],
      "line": 27
    },
    {
      "cells": [
        "test"
      ],
      "line": 28
    }
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_am_logged_into_Briefcase(UserInputData\u003e)"
});
formatter.result({
  "duration": 97705481,
  "error_message": "org.openqa.selenium.NoSuchSessionException: Session ID is null. Using WebDriver after calling quit()?\nBuild info: version: \u00273.141.59\u0027, revision: \u0027e82be7d358\u0027, time: \u00272018-11-14T08:17:03\u0027\nSystem info: host: \u0027Saltanat-MacBook-Pro.local\u0027, ip: \u0027fe80:0:0:0:4d9:a410:10f1:91cf%en0\u0027, os.name: \u0027Mac OS X\u0027, os.arch: \u0027x86_64\u0027, os.version: \u002710.15.7\u0027, java.version: \u00271.8.0_162\u0027\nDriver info: driver.version: IOSDriver\n\tat org.openqa.selenium.remote.HttpCommandExecutor.execute(HttpCommandExecutor.java:125)\n\tat io.appium.java_client.remote.AppiumCommandExecutor.execute(AppiumCommandExecutor.java:231)\n\tat org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:552)\n\tat io.appium.java_client.DefaultGenericMobileDriver.execute(DefaultGenericMobileDriver.java:46)\n\tat io.appium.java_client.AppiumDriver.execute(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.execute(IOSDriver.java:1)\n\tat io.appium.java_client.HasSessionDetails.getSessionDetails(HasSessionDetails.java:39)\n\tat io.appium.java_client.HasSessionDetails.getSessionDetail(HasSessionDetails.java:56)\n\tat io.appium.java_client.HasSessionDetails.getPlatformName(HasSessionDetails.java:65)\n\tat io.appium.java_client.pagefactory.AppiumFieldDecorator.\u003cinit\u003e(AppiumFieldDecorator.java:96)\n\tat io.appium.java_client.pagefactory.AppiumFieldDecorator.\u003cinit\u003e(AppiumFieldDecorator.java:144)\n\tat gov.uscourts.ao.mobileBriefcase.Pages.JenieLoginPage.\u003cinit\u003e(JenieLoginPage.java:33)\n\tat gov.uscourts.ao.mobileBriefcase.stepDefinitions.JenieLogin_StepDefinitions.i_am_logged_into_Briefcase(JenieLogin_StepDefinitions.java:18)\n\tat ✽.Given I am logged into Briefcase(CATEGORIES/SMOKE/VOTE_INFORMATION_PANEL-AMB_1036.feature:14)\n",
  "status": "failed"
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_select_a_user(UserInputData\u003e)"
});
formatter.result({
  "status": "skipped"
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
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "CMKA",
      "offset": 17
    },
    {
      "val": "Vote_Information",
      "offset": 30
    }
  ],
  "location": "VoteInformation_StepDefinitions.user_observes_the_panel_displays_This_should_only_display_if_the_referral_requires_voting(String,String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "32",
      "offset": 156
    },
    {
      "val": "82226",
      "offset": 163
    },
    {
      "val": "prhr",
      "offset": 172
    },
    {
      "val": "34870",
      "offset": 181
    }
  ],
  "location": "VoteInformation_StepDefinitions.for_each_referral_observes_the_filer_s_name_first_initial_of_pr_middle_name_gn_display_party_type_and_date_filed_displays_in_a_light_blue_heading_Use(String,String,String,String,UserInputData\u003e)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "34870",
      "offset": 84
    }
  ],
  "location": "VoteInformation_StepDefinitions.user_checks_each_judge_s_vote_and_the_date_displays_under_their_initials_using(String,UserInputData\u003e)"
});
formatter.result({
  "status": "skipped"
});
formatter.after({
  "duration": 152707,
  "error_message": "org.openqa.selenium.NoSuchSessionException: Session ID is null. Using WebDriver after calling quit()?\nBuild info: version: \u00273.141.59\u0027, revision: \u0027e82be7d358\u0027, time: \u00272018-11-14T08:17:03\u0027\nSystem info: host: \u0027Saltanat-MacBook-Pro.local\u0027, ip: \u0027fe80:0:0:0:4d9:a410:10f1:91cf%en0\u0027, os.name: \u0027Mac OS X\u0027, os.arch: \u0027x86_64\u0027, os.version: \u002710.15.7\u0027, java.version: \u00271.8.0_162\u0027\nDriver info: driver.version: IOSDriver\n\tat org.openqa.selenium.remote.HttpCommandExecutor.execute(HttpCommandExecutor.java:125)\n\tat io.appium.java_client.remote.AppiumCommandExecutor.execute(AppiumCommandExecutor.java:231)\n\tat org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:552)\n\tat io.appium.java_client.DefaultGenericMobileDriver.execute(DefaultGenericMobileDriver.java:42)\n\tat io.appium.java_client.AppiumDriver.execute(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.execute(IOSDriver.java:1)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:323)\n\tat io.appium.java_client.DefaultGenericMobileDriver.findElement(DefaultGenericMobileDriver.java:62)\n\tat io.appium.java_client.AppiumDriver.findElement(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.findElement(IOSDriver.java:1)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElementByXPath(RemoteWebDriver.java:428)\n\tat io.appium.java_client.DefaultGenericMobileDriver.findElementByXPath(DefaultGenericMobileDriver.java:152)\n\tat io.appium.java_client.AppiumDriver.findElementByXPath(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.findElementByXPath(IOSDriver.java:1)\n\tat org.openqa.selenium.By$ByXPath.findElement(By.java:353)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:315)\n\tat io.appium.java_client.DefaultGenericMobileDriver.findElement(DefaultGenericMobileDriver.java:58)\n\tat io.appium.java_client.AppiumDriver.findElement(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.findElement(IOSDriver.java:1)\n\tat org.openqa.selenium.support.ui.ExpectedConditions$6.apply(ExpectedConditions.java:182)\n\tat org.openqa.selenium.support.ui.ExpectedConditions$6.apply(ExpectedConditions.java:179)\n\tat org.openqa.selenium.support.ui.FluentWait.until(FluentWait.java:249)\n\tat gov.uscourts.ao.mobileBriefcase.common.Page.waitForPresenceOfElementLocated(Page.java:17)\n\tat gov.uscourts.ao.mobileBriefcase.common.Actions.contains(Actions.java:67)\n\tat gov.uscourts.ao.mobileBriefcase.Pages.JenieLoginPage.logout(JenieLoginPage.java:185)\n\tat gov.uscourts.ao.mobileBriefcase.stepDefinitions.Hook.tearDown(Hook.java:19)\n\tat sun.reflect.GeneratedMethodAccessor32.invoke(Unknown Source)\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n\tat java.lang.reflect.Method.invoke(Method.java:498)\n\tat cucumber.runtime.Utils$1.call(Utils.java:40)\n\tat cucumber.runtime.Timeout.timeout(Timeout.java:16)\n\tat cucumber.runtime.Utils.invoke(Utils.java:34)\n\tat cucumber.runtime.java.JavaHookDefinition.execute(JavaHookDefinition.java:60)\n\tat cucumber.runtime.Runtime.runHookIfTagsMatch(Runtime.java:224)\n\tat cucumber.runtime.Runtime.runHooks(Runtime.java:212)\n\tat cucumber.runtime.Runtime.runAfterHooks(Runtime.java:206)\n\tat cucumber.runtime.model.CucumberScenario.run(CucumberScenario.java:46)\n\tat cucumber.runtime.junit.ExecutionUnitRunner.run(ExecutionUnitRunner.java:102)\n\tat org.junit.runners.Suite.runChild(Suite.java:128)\n\tat org.junit.runners.Suite.runChild(Suite.java:27)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.runtime.junit.ExamplesRunner.run(ExamplesRunner.java:59)\n\tat org.junit.runners.Suite.runChild(Suite.java:128)\n\tat org.junit.runners.Suite.runChild(Suite.java:27)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.runtime.junit.ScenarioOutlineRunner.run(ScenarioOutlineRunner.java:53)\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:63)\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:18)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.runtime.junit.FeatureRunner.run(FeatureRunner.java:70)\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:95)\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:38)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.api.junit.Cucumber.run(Cucumber.java:100)\n\tat org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:86)\n\tat org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:538)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:760)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:460)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:206)\n",
  "status": "failed"
});
formatter.scenarioOutline({
  "line": 37,
  "name": "",
  "description": "",
  "id": "display-vote-information-panel,-filer-info-and-judge\u0027s-initials;",
  "type": "scenario_outline",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 36,
      "name": "@AMB-1097"
    }
  ]
});
formatter.step({
  "line": 38,
  "name": "I am logged into Briefcase",
  "rows": [
    {
      "cells": [
        "environment",
        "userName",
        "password",
        "courtId"
      ],
      "line": 39
    },
    {
      "cells": [
        "Integration",
        "s haenni",
        "Test2024!",
        "test"
      ],
      "line": 40
    }
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 41,
  "name": "I select a user",
  "rows": [
    {
      "cells": [
        "role",
        "briefcaseUser"
      ],
      "line": 42
    },
    {
      "cells": [
        "Appellate Judges",
        "Colloton"
      ],
      "line": 43
    }
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 44,
  "name": "User selects \"\u003crefCategory\u003e\" and \"\u003ccaseNum\u003e\"",
  "keyword": "Then "
});
formatter.step({
  "line": 46,
  "name": "User  selects action using \"\u003cel_id\u003e\"  and verifies the name of the action displays in the dark blue banner",
  "rows": [
    {
      "cells": [
        "courtId"
      ],
      "line": 47
    },
    {
      "cells": [
        "test"
      ],
      "line": 48
    }
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 49,
  "name": "In the judgeVoteDPf, the user observes the filer\u0027s name  first initial of pr_middle_name gn_display  party type and date filed displays in a light blue heading. Use  \"\u003ccmr_ju_pe_id\u003e\" , \"\u003ccmr_cs_caseid\u003e\" ,\"\u003ccmr_cyv_code\u003e\" , \"\u003cccr_id\u003e\" .",
  "rows": [
    {
      "cells": [
        "courtId"
      ],
      "line": 50
    },
    {
      "cells": [
        "test"
      ],
      "line": 51
    }
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 52,
  "name": "In the judgeVoteDPf, the user checks each judge\u0027s vote and the date displays under their initials, using  \"\u003cccr_id\u003e\"",
  "rows": [
    {
      "cells": [
        "courtId"
      ],
      "line": 53
    },
    {
      "cells": [
        "test"
      ],
      "line": 54
    }
  ],
  "keyword": "Then "
});
formatter.examples({
  "line": 55,
  "name": "",
  "description": "",
  "id": "display-vote-information-panel,-filer-info-and-judge\u0027s-initials;;",
  "rows": [
    {
      "cells": [
        "el_id",
        "refCategory",
        "caseNum",
        "dbType",
        "cmr_cs_caseid",
        "cmr_ju_pe_id",
        "cmr_cyv_code",
        "ccr_id"
      ],
      "line": 56,
      "id": "display-vote-information-panel,-filer-info-and-judge\u0027s-initials;;;1"
    },
    {
      "cells": [
        "3155",
        "PETITION",
        "15-3314",
        "CMKA",
        "82226",
        "32",
        "prhr",
        "34870"
      ],
      "line": 57,
      "id": "display-vote-information-panel,-filer-info-and-judge\u0027s-initials;;;2"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 117648458,
  "status": "passed"
});
formatter.scenario({
  "line": 57,
  "name": "",
  "description": "",
  "id": "display-vote-information-panel,-filer-info-and-judge\u0027s-initials;;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 1,
      "name": "@Smoke"
    },
    {
      "line": 1,
      "name": "@AMB"
    },
    {
      "line": 36,
      "name": "@AMB-1097"
    }
  ]
});
formatter.step({
  "line": 38,
  "name": "I am logged into Briefcase",
  "rows": [
    {
      "cells": [
        "environment",
        "userName",
        "password",
        "courtId"
      ],
      "line": 39
    },
    {
      "cells": [
        "Integration",
        "s haenni",
        "Test2024!",
        "test"
      ],
      "line": 40
    }
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 41,
  "name": "I select a user",
  "rows": [
    {
      "cells": [
        "role",
        "briefcaseUser"
      ],
      "line": 42
    },
    {
      "cells": [
        "Appellate Judges",
        "Colloton"
      ],
      "line": 43
    }
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 44,
  "name": "User selects \"PETITION\" and \"15-3314\"",
  "matchedColumns": [
    1,
    2
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 46,
  "name": "User  selects action using \"3155\"  and verifies the name of the action displays in the dark blue banner",
  "matchedColumns": [
    0
  ],
  "rows": [
    {
      "cells": [
        "courtId"
      ],
      "line": 47
    },
    {
      "cells": [
        "test"
      ],
      "line": 48
    }
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 49,
  "name": "In the judgeVoteDPf, the user observes the filer\u0027s name  first initial of pr_middle_name gn_display  party type and date filed displays in a light blue heading. Use  \"32\" , \"82226\" ,\"prhr\" , \"34870\" .",
  "matchedColumns": [
    4,
    5,
    6,
    7
  ],
  "rows": [
    {
      "cells": [
        "courtId"
      ],
      "line": 50
    },
    {
      "cells": [
        "test"
      ],
      "line": 51
    }
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 52,
  "name": "In the judgeVoteDPf, the user checks each judge\u0027s vote and the date displays under their initials, using  \"34870\"",
  "matchedColumns": [
    7
  ],
  "rows": [
    {
      "cells": [
        "courtId"
      ],
      "line": 53
    },
    {
      "cells": [
        "test"
      ],
      "line": 54
    }
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_am_logged_into_Briefcase(UserInputData\u003e)"
});
formatter.result({
  "duration": 113388691,
  "error_message": "org.openqa.selenium.NoSuchSessionException: Session ID is null. Using WebDriver after calling quit()?\nBuild info: version: \u00273.141.59\u0027, revision: \u0027e82be7d358\u0027, time: \u00272018-11-14T08:17:03\u0027\nSystem info: host: \u0027Saltanat-MacBook-Pro.local\u0027, ip: \u0027fe80:0:0:0:4d9:a410:10f1:91cf%en0\u0027, os.name: \u0027Mac OS X\u0027, os.arch: \u0027x86_64\u0027, os.version: \u002710.15.7\u0027, java.version: \u00271.8.0_162\u0027\nDriver info: driver.version: IOSDriver\n\tat org.openqa.selenium.remote.HttpCommandExecutor.execute(HttpCommandExecutor.java:125)\n\tat io.appium.java_client.remote.AppiumCommandExecutor.execute(AppiumCommandExecutor.java:231)\n\tat org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:552)\n\tat io.appium.java_client.DefaultGenericMobileDriver.execute(DefaultGenericMobileDriver.java:46)\n\tat io.appium.java_client.AppiumDriver.execute(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.execute(IOSDriver.java:1)\n\tat io.appium.java_client.HasSessionDetails.getSessionDetails(HasSessionDetails.java:39)\n\tat io.appium.java_client.HasSessionDetails.getSessionDetail(HasSessionDetails.java:56)\n\tat io.appium.java_client.HasSessionDetails.getPlatformName(HasSessionDetails.java:65)\n\tat io.appium.java_client.pagefactory.AppiumFieldDecorator.\u003cinit\u003e(AppiumFieldDecorator.java:96)\n\tat io.appium.java_client.pagefactory.AppiumFieldDecorator.\u003cinit\u003e(AppiumFieldDecorator.java:144)\n\tat gov.uscourts.ao.mobileBriefcase.Pages.JenieLoginPage.\u003cinit\u003e(JenieLoginPage.java:33)\n\tat gov.uscourts.ao.mobileBriefcase.stepDefinitions.JenieLogin_StepDefinitions.i_am_logged_into_Briefcase(JenieLogin_StepDefinitions.java:18)\n\tat ✽.Given I am logged into Briefcase(CATEGORIES/SMOKE/VOTE_INFORMATION_PANEL-AMB_1036.feature:38)\n",
  "status": "failed"
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_select_a_user(UserInputData\u003e)"
});
formatter.result({
  "status": "skipped"
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
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "3155",
      "offset": 28
    }
  ],
  "location": "Common_StepDefinitions.user_selects_action_using_and_verifies_the_name_of_the_action_displays_in_the_dark_blue_banner(String,UserInputData\u003e)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "32",
      "offset": 167
    },
    {
      "val": "82226",
      "offset": 174
    },
    {
      "val": "prhr",
      "offset": 183
    },
    {
      "val": "34870",
      "offset": 192
    }
  ],
  "location": "VoteInformation_StepDefinitions.in_the_judgeVoteDPf_the_user_observes_the_filer_s_name_first_initial_of_pr_middle_name_gn_display_party_type_and_date_filed_displays_in_a_light_blue_heading_Use(String,String,String,String,UserInputData\u003e)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "34870",
      "offset": 107
    }
  ],
  "location": "VoteInformation_StepDefinitions.in_the_judgeVoteDPf_the_user_checks_each_judge_s_vote_and_the_date_displays_under_their_initials_using(String,UserInputData\u003e)"
});
formatter.result({
  "status": "skipped"
});
formatter.after({
  "duration": 159717,
  "error_message": "org.openqa.selenium.NoSuchSessionException: Session ID is null. Using WebDriver after calling quit()?\nBuild info: version: \u00273.141.59\u0027, revision: \u0027e82be7d358\u0027, time: \u00272018-11-14T08:17:03\u0027\nSystem info: host: \u0027Saltanat-MacBook-Pro.local\u0027, ip: \u0027fe80:0:0:0:4d9:a410:10f1:91cf%en0\u0027, os.name: \u0027Mac OS X\u0027, os.arch: \u0027x86_64\u0027, os.version: \u002710.15.7\u0027, java.version: \u00271.8.0_162\u0027\nDriver info: driver.version: IOSDriver\n\tat org.openqa.selenium.remote.HttpCommandExecutor.execute(HttpCommandExecutor.java:125)\n\tat io.appium.java_client.remote.AppiumCommandExecutor.execute(AppiumCommandExecutor.java:231)\n\tat org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:552)\n\tat io.appium.java_client.DefaultGenericMobileDriver.execute(DefaultGenericMobileDriver.java:42)\n\tat io.appium.java_client.AppiumDriver.execute(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.execute(IOSDriver.java:1)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:323)\n\tat io.appium.java_client.DefaultGenericMobileDriver.findElement(DefaultGenericMobileDriver.java:62)\n\tat io.appium.java_client.AppiumDriver.findElement(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.findElement(IOSDriver.java:1)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElementByXPath(RemoteWebDriver.java:428)\n\tat io.appium.java_client.DefaultGenericMobileDriver.findElementByXPath(DefaultGenericMobileDriver.java:152)\n\tat io.appium.java_client.AppiumDriver.findElementByXPath(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.findElementByXPath(IOSDriver.java:1)\n\tat org.openqa.selenium.By$ByXPath.findElement(By.java:353)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:315)\n\tat io.appium.java_client.DefaultGenericMobileDriver.findElement(DefaultGenericMobileDriver.java:58)\n\tat io.appium.java_client.AppiumDriver.findElement(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.findElement(IOSDriver.java:1)\n\tat org.openqa.selenium.support.ui.ExpectedConditions$6.apply(ExpectedConditions.java:182)\n\tat org.openqa.selenium.support.ui.ExpectedConditions$6.apply(ExpectedConditions.java:179)\n\tat org.openqa.selenium.support.ui.FluentWait.until(FluentWait.java:249)\n\tat gov.uscourts.ao.mobileBriefcase.common.Page.waitForPresenceOfElementLocated(Page.java:17)\n\tat gov.uscourts.ao.mobileBriefcase.common.Actions.contains(Actions.java:67)\n\tat gov.uscourts.ao.mobileBriefcase.Pages.JenieLoginPage.logout(JenieLoginPage.java:185)\n\tat gov.uscourts.ao.mobileBriefcase.stepDefinitions.Hook.tearDown(Hook.java:19)\n\tat sun.reflect.GeneratedMethodAccessor32.invoke(Unknown Source)\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n\tat java.lang.reflect.Method.invoke(Method.java:498)\n\tat cucumber.runtime.Utils$1.call(Utils.java:40)\n\tat cucumber.runtime.Timeout.timeout(Timeout.java:16)\n\tat cucumber.runtime.Utils.invoke(Utils.java:34)\n\tat cucumber.runtime.java.JavaHookDefinition.execute(JavaHookDefinition.java:60)\n\tat cucumber.runtime.Runtime.runHookIfTagsMatch(Runtime.java:224)\n\tat cucumber.runtime.Runtime.runHooks(Runtime.java:212)\n\tat cucumber.runtime.Runtime.runAfterHooks(Runtime.java:206)\n\tat cucumber.runtime.model.CucumberScenario.run(CucumberScenario.java:46)\n\tat cucumber.runtime.junit.ExecutionUnitRunner.run(ExecutionUnitRunner.java:102)\n\tat org.junit.runners.Suite.runChild(Suite.java:128)\n\tat org.junit.runners.Suite.runChild(Suite.java:27)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.runtime.junit.ExamplesRunner.run(ExamplesRunner.java:59)\n\tat org.junit.runners.Suite.runChild(Suite.java:128)\n\tat org.junit.runners.Suite.runChild(Suite.java:27)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.runtime.junit.ScenarioOutlineRunner.run(ScenarioOutlineRunner.java:53)\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:63)\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:18)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.runtime.junit.FeatureRunner.run(FeatureRunner.java:70)\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:95)\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:38)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.api.junit.Cucumber.run(Cucumber.java:100)\n\tat org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:86)\n\tat org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:538)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:760)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:460)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:206)\n",
  "status": "failed"
});
});