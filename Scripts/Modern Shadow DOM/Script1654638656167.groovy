import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.SearchContext
import com.kms.katalon.core.testobject.TestObject as TestObject

System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver")

WebDriver driver = new ChromeDriver()

DriverFactory.changeWebDriver(driver)

WebUI.navigateToUrl('http://watir.com/examples/shadow_dom.html')

WebElement shadowHost = driver.findElement(By.cssSelector("#shadow_host"))

SearchContext shadowRoot = shadowHost.getShadowRoot()

WebElement shadowContent = shadowRoot.findElement(By.cssSelector("#shadow_content"));

WebUI.verifyMatch(shadowContent.getText(), "some text", false)

WebElement nestedHost = shadowRoot.findElement(By.cssSelector("#nested_shadow_host"))

SearchContext nestedRoot = nestedHost.getShadowRoot()

WebElement nestedContent = nestedRoot.findElement(By.cssSelector("#nested_shadow_content"));

WebUI.verifyMatch(nestedContent.getText(), "nested text", false)

WebUI.closeBrowser()

