$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("CATEGORIES/Reg/RED_BULLETS_FOR_REFERRALS_AMB-1152.feature");
formatter.feature({
  "line": 2,
  "name": "Red bullet displays for new referrals and does not display for viewed referrals",
  "description": "",
  "id": "red-bullet-displays-for-new-referrals-and-does-not-display-for-viewed-referrals",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@AMB"
    },
    {
      "line": 1,
      "name": "@Regression"
    }
  ]
});
formatter.scenarioOutline({
  "line": 6,
  "name": "",
  "description": "A red bullet icon displays next to referrals which the user has not viewed yet. \nOnce the user taps on the referral, the red bullet icon is removed, indicating the referral has been viewed. \n This task is to automate the display of the red bullet icon.",
  "id": "red-bullet-displays-for-new-referrals-and-does-not-display-for-viewed-referrals;",
  "type": "scenario_outline",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 5,
      "name": "@AMB-1152"
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
        "Test2025!",
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
  "name": "User selects a  \"\u003crefCategory\u003e\"",
  "keyword": "When "
});
formatter.step({
  "line": 20,
  "name": "User selects a category that has unviewed referrals and verifies that the red bullet icon displays next to any unviewed referrals, taps on a referral and then gets back to the referral list page",
  "keyword": "Then "
});
formatter.step({
  "comments": [
    {
      "line": 21,
      "value": "# Verifies the red bullet is removed indicating the referral has been viewed"
    }
  ],
  "line": 22,
  "name": "User closes the app and reopen and go back to the \"\u003crefCategory\u003e\" that contains the referral that was just viewed",
  "keyword": "Then "
});
formatter.step({
  "line": 23,
  "name": "User logs out from the Briefcase",
  "keyword": "And "
});
formatter.step({
  "line": 25,
  "name": "I am logged into Briefcase",
  "rows": [
    {
      "cells": [
        "environment",
        "userName",
        "password",
        "courtId"
      ],
      "line": 26
    },
    {
      "cells": [
        "Integration",
        "s haenni",
        "Test2025!",
        "test"
      ],
      "line": 27
    }
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 29,
  "name": "I select a user",
  "rows": [
    {
      "cells": [
        "role",
        "briefcaseUser"
      ],
      "line": 30
    },
    {
      "cells": [
        "Appellate Judges",
        "Colloton"
      ],
      "line": 31
    }
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 33,
  "name": "User goes back to the \"\u003crefCategory\u003e\" that contains the referral that was just viewed and verify the bullet does not display",
  "keyword": "Then "
});
formatter.examples({
  "line": 35,
  "name": "",
  "description": "",
  "id": "red-bullet-displays-for-new-referrals-and-does-not-display-for-viewed-referrals;;",
  "rows": [
    {
      "cells": [
        "courtId",
        "refCategory"
      ],
      "line": 36,
      "id": "red-bullet-displays-for-new-referrals-and-does-not-display-for-viewed-referrals;;;1"
    },
    {
      "cells": [
        "CMKA",
        "Motion"
      ],
      "line": 37,
      "id": "red-bullet-displays-for-new-referrals-and-does-not-display-for-viewed-referrals;;;2"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 11042418494,
  "status": "passed"
});
formatter.scenario({
  "line": 37,
  "name": "",
  "description": "A red bullet icon displays next to referrals which the user has not viewed yet. \nOnce the user taps on the referral, the red bullet icon is removed, indicating the referral has been viewed. \n This task is to automate the display of the red bullet icon.",
  "id": "red-bullet-displays-for-new-referrals-and-does-not-display-for-viewed-referrals;;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 1,
      "name": "@Regression"
    },
    {
      "line": 1,
      "name": "@AMB"
    },
    {
      "line": 5,
      "name": "@AMB-1152"
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
        "Test2025!",
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
  "name": "User selects a  \"Motion\"",
  "matchedColumns": [
    1
  ],
  "keyword": "When "
});
formatter.step({
  "line": 20,
  "name": "User selects a category that has unviewed referrals and verifies that the red bullet icon displays next to any unviewed referrals, taps on a referral and then gets back to the referral list page",
  "keyword": "Then "
});
formatter.step({
  "comments": [
    {
      "line": 21,
      "value": "# Verifies the red bullet is removed indicating the referral has been viewed"
    }
  ],
  "line": 22,
  "name": "User closes the app and reopen and go back to the \"Motion\" that contains the referral that was just viewed",
  "matchedColumns": [
    1
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 23,
  "name": "User logs out from the Briefcase",
  "keyword": "And "
});
formatter.step({
  "line": 25,
  "name": "I am logged into Briefcase",
  "rows": [
    {
      "cells": [
        "environment",
        "userName",
        "password",
        "courtId"
      ],
      "line": 26
    },
    {
      "cells": [
        "Integration",
        "s haenni",
        "Test2025!",
        "test"
      ],
      "line": 27
    }
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 29,
  "name": "I select a user",
  "rows": [
    {
      "cells": [
        "role",
        "briefcaseUser"
      ],
      "line": 30
    },
    {
      "cells": [
        "Appellate Judges",
        "Colloton"
      ],
      "line": 31
    }
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 33,
  "name": "User goes back to the \"Motion\" that contains the referral that was just viewed and verify the bullet does not display",
  "matchedColumns": [
    1
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_am_logged_into_Briefcase(UserInputData\u003e)"
});
formatter.result({
  "duration": 72654189558,
  "status": "passed"
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.i_select_a_user(UserInputData\u003e)"
});
formatter.result({
  "duration": 14762689837,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Motion",
      "offset": 17
    }
  ],
  "location": "Common_StepDefinitions.user_selects_a(String)"
});
formatter.result({
  "duration": 5217814008,
  "status": "passed"
});
formatter.match({
  "location": "RedBullets_StepDefintions.user_selects_a_category_that_has_unviewed_referrals_and_verifies_that_the_red_bullet_icon_displays_next_to_any_unviewed_referrals_taps_on_a_referral_and_then_gets_back_to_the_referral_list_page()"
});
formatter.result({
  "duration": 21555833246,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Motion",
      "offset": 51
    }
  ],
  "location": "RedBullets_StepDefintions.user_closes_the_app_and_reopen_and_go_back_to_the_that_contains_the_referral_that_was_just_viewed(String)"
});
formatter.result({
  "duration": 21379945395,
  "error_message": "java.lang.AssertionError: WHEN CLOSING AND REOPENING THE APP ITEMS APPEAR AS NEW expected:\u003c43\u003e but was:\u003c44\u003e\n\tat org.junit.Assert.fail(Assert.java:88)\n\tat org.junit.Assert.failNotEquals(Assert.java:834)\n\tat org.junit.Assert.assertEquals(Assert.java:645)\n\tat gov.uscourts.ao.mobileBriefcase.stepDefinitions.RedBullets_StepDefintions.user_closes_the_app_and_reopen_and_go_back_to_the_that_contains_the_referral_that_was_just_viewed(RedBullets_StepDefintions.java:34)\n\tat ✽.Then User closes the app and reopen and go back to the \"Motion\" that contains the referral that was just viewed(CATEGORIES/Reg/RED_BULLETS_FOR_REFERRALS_AMB-1152.feature:22)\n",
  "status": "failed"
});
formatter.match({
  "location": "JenieLogin_StepDefinitions.user_logs_out_from_the_Briefcase()"
});
formatter.result({
  "status": "skipped"
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
      "val": "Motion",
      "offset": 23
    }
  ],
  "location": "RedBullets_StepDefintions.user_goes_back_to_the_that_contains_the_referral_that_was_just_viewed_and_verify_the_bullet_does_not_display(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.after({
  "duration": 12475970351,
  "error_message": "org.openqa.selenium.NoSuchElementException: Can\u0027t locate an element by this strategy: By.chained({By.xpath: //XCUIElementTypeButton[2]})\n\tat io.appium.java_client.pagefactory.AppiumElementLocator.findElement(AppiumElementLocator.java:126)\n\tat io.appium.java_client.pagefactory.interceptors.InterceptorOfASingleElement.intercept(InterceptorOfASingleElement.java:60)\n\tat io.appium.java_client.ios.IOSElement$$EnhancerByCGLIB$$d311658.click(\u003cgenerated\u003e)\n\tat gov.uscourts.ao.mobileBriefcase.Pages.JenieLoginPage.logout(JenieLoginPage.java:189)\n\tat gov.uscourts.ao.mobileBriefcase.stepDefinitions.Hook.tearDown(Hook.java:19)\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n\tat java.lang.reflect.Method.invoke(Method.java:498)\n\tat cucumber.runtime.Utils$1.call(Utils.java:40)\n\tat cucumber.runtime.Timeout.timeout(Timeout.java:16)\n\tat cucumber.runtime.Utils.invoke(Utils.java:34)\n\tat cucumber.runtime.java.JavaHookDefinition.execute(JavaHookDefinition.java:60)\n\tat cucumber.runtime.Runtime.runHookIfTagsMatch(Runtime.java:224)\n\tat cucumber.runtime.Runtime.runHooks(Runtime.java:212)\n\tat cucumber.runtime.Runtime.runAfterHooks(Runtime.java:206)\n\tat cucumber.runtime.model.CucumberScenario.run(CucumberScenario.java:46)\n\tat cucumber.runtime.junit.ExecutionUnitRunner.run(ExecutionUnitRunner.java:102)\n\tat org.junit.runners.Suite.runChild(Suite.java:128)\n\tat org.junit.runners.Suite.runChild(Suite.java:27)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.runtime.junit.ExamplesRunner.run(ExamplesRunner.java:59)\n\tat org.junit.runners.Suite.runChild(Suite.java:128)\n\tat org.junit.runners.Suite.runChild(Suite.java:27)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.runtime.junit.ScenarioOutlineRunner.run(ScenarioOutlineRunner.java:53)\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:63)\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:18)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.runtime.junit.FeatureRunner.run(FeatureRunner.java:70)\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:95)\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:38)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.api.junit.Cucumber.run(Cucumber.java:100)\n\tat org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:86)\n\tat org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:538)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:760)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:460)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:206)\nCaused by: org.openqa.selenium.TimeoutException: Expected condition failed: waiting for io.appium.java_client.pagefactory.AppiumElementLocator$WaitingFunction@2ca6546f (tried for 1 second(s) with 500 milliseconds interval)\n\tat org.openqa.selenium.support.ui.FluentWait.timeoutException(FluentWait.java:304)\n\tat org.openqa.selenium.support.ui.FluentWait.until(FluentWait.java:272)\n\tat io.appium.java_client.pagefactory.AppiumElementLocator.waitFor(AppiumElementLocator.java:99)\n\tat io.appium.java_client.pagefactory.AppiumElementLocator.findElement(AppiumElementLocator.java:119)\n\t... 59 more\nCaused by: org.openqa.selenium.NoSuchElementException: Cannot locate an element using By.chained({By.xpath: //XCUIElementTypeButton[2]})\nFor documentation on this error, please visit: https://www.seleniumhq.org/exceptions/no_such_element.html\nBuild info: version: \u00273.141.59\u0027, revision: \u0027e82be7d358\u0027, time: \u00272018-11-14T08:17:03\u0027\nSystem info: host: \u0027Saltanat-MacBook-Pro.local\u0027, ip: \u0027fe80:0:0:0:c3a:f7db:e536:9e51%en0\u0027, os.name: \u0027Mac OS X\u0027, os.arch: \u0027x86_64\u0027, os.version: \u002710.15.7\u0027, java.version: \u00271.8.0_162\u0027\nDriver info: driver.version: IOSDriver\n\tat io.appium.java_client.pagefactory.bys.builder.ByChained.findElement(ByChained.java:74)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:315)\n\tat io.appium.java_client.DefaultGenericMobileDriver.findElement(DefaultGenericMobileDriver.java:58)\n\tat io.appium.java_client.AppiumDriver.findElement(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.findElement(IOSDriver.java:1)\n\tat io.appium.java_client.pagefactory.bys.ContentMappedBy.findElement(ContentMappedBy.java:50)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:315)\n\tat io.appium.java_client.DefaultGenericMobileDriver.findElement(DefaultGenericMobileDriver.java:58)\n\tat io.appium.java_client.AppiumDriver.findElement(AppiumDriver.java:1)\n\tat io.appium.java_client.ios.IOSDriver.findElement(IOSDriver.java:1)\n\tat io.appium.java_client.pagefactory.AppiumElementLocator.lambda$0(AppiumElementLocator.java:120)\n\tat io.appium.java_client.pagefactory.AppiumElementLocator$WaitingFunction.apply(AppiumElementLocator.java:172)\n\tat io.appium.java_client.pagefactory.AppiumElementLocator$WaitingFunction.apply(AppiumElementLocator.java:1)\n\tat org.openqa.selenium.support.ui.FluentWait.until(FluentWait.java:249)\n\t... 61 more\n",
  "status": "failed"
});
});