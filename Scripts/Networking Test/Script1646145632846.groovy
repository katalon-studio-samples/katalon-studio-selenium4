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
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.By as By
import org.openqa.selenium.support.locators.RelativeLocator as RelativeLocator
import org.openqa.selenium.support.locators.RelativeLocator.RelativeBy as RelativeBy
import static org.openqa.selenium.support.locators.RelativeLocator.with
import org.openqa.selenium.WindowType as WindowType
import org.openqa.selenium.devtools.DevTools as DevTools
import org.openqa.selenium.devtools.v101.network.Network as Network
import org.openqa.selenium.devtools.v101.network.model.ConnectionType as ConnectionType

System.setProperty('webdriver.chrome.driver', DriverFactory.getChromeDriverPath())

ChromeDriver driver = new ChromeDriver()

println(driver)

DevTools devTools = driver.getDevTools()

devTools.createSession()

devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()))

devTools.send(Network.emulateNetworkConditions(false, 150, 2500, 2000, Optional.of(ConnectionType.CELLULAR3G)))

driver.get('https://linkedin.com')

System.out.println('Enable Slow Network: ' + driver.getTitle())

driver.close()