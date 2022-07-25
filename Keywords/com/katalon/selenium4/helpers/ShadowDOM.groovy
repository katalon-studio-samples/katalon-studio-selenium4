package com.katalon.selenium4.helpers
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import org.openqa.selenium.SearchContext

import com.kms.katalon.core.webui.driver.SmartWaitWebDriver

/**
 *
 * These keywords were created to simulate a specific issue that a customer faced. 
 * In particular, they show how the DriverFactory.getWebDriver() method currently
 * returns a SmartWaitWebDriver that in turn returns EventFiringWebElement rather
 * than a RemoteElement. Consequently, we call getWrappedDriver() to get the actual
 * ChromeDriver instance.
 * 
 */

class ShadowDOM {
	/**
	 * Navigate to nested shadow DOM and click an element.
	 */
	@Keyword
	def clickFirstDoc() {
		KeywordUtil.logInfo("Entering")

		// This will give you a SmartWaitWebDriver which will in turn return EventFiringWebElement
		SmartWaitWebDriver not_the_webdriver_you_want = (SmartWaitWebDriver)DriverFactory.getWebDriver();
		// therefore we need to get the wrapped driver out so that we can eventually call getShadowRoot()
		WebDriver wd = not_the_webdriver_you_want.getWrappedDriver()

		WebElement shadowParent =  wd.findElement(By.cssSelector("#shadow_host"));
		SearchContext shadowParentSC = getShadowElement(shadowParent);

		WebElement nuxeoHome =  shadowParentSC.findElement(By.cssSelector("#nested_shadow_host"));
		SearchContext nuxeoHomeSC = getShadowElement(nuxeoHome);
		WebElement thumbImg = nuxeoHomeSC.findElement(By.cssSelector("#nested_shadow_content > div"));
		def act = thumbImg.click();

		KeywordUtil.logInfo("Exiting")
	}

	@Keyword
	def clickAllTheWay(String shadowPath, String finalElement) {
		SmartWaitWebDriver not_the_webdriver_you_want = (SmartWaitWebDriver)DriverFactory.getWebDriver();
		// therefore we need to get the wrapped driver out so that we can eventually call getShadowRoot()
		WebDriver wd = not_the_webdriver_you_want.getWrappedDriver();

		SearchContext sc =  wd;
		String[] shadowPathArray = shadowPath.split('>>>');
		for (String root in shadowPathArray) {
			WebElement rootElement = sc.findElement(By.cssSelector(root));
			sc = rootElement.getShadowRoot();
		}

		WebElement finalElem = sc.findElement(By.cssSelector(finalElement)) ;
		def act = finalElem.click();
	}

	static SearchContext getShadowElement(WebElement parentElement) {
		SearchContext sc = parentElement.getShadowRoot();
		return sc;
	}
}