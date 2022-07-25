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

System.setProperty('webdriver.chrome.driver', DriverFactory.getChromeDriverPath())

WebDriver driver = new ChromeDriver()

println(driver)

driver.get('http://automationpractice.com/index.php')

// Automatically Open & Switch To The New Window Or Tab
driver.manage().window().minimize()

driver.switchTo().newWindow(WindowType.TAB).get('http://automationpractice.com/index.php?controller=authentication&back=my-account')

System.out.println('Title: ' + driver.getTitle())

// Work In The New Window Or Tab
driver.findElement(By.id('email_create')).sendKeys('Selenium4@TAU.com')

driver.findElement(By.id('SubmitCreate')).click()

// Get The Window ID Handles
Set<String> allWindowTabss = driver.getWindowHandles()

Iterator<String> iterate = allWindowTabss.iterator()

String mainFirstWindow = iterate.next()

// Switch & Work In The Main Window Or Tab
driver.switchTo().window(mainFirstWindow)

driver.manage().window().minimize()

driver.findElement(By.id('search_query_top')).sendKeys('Shirt')

driver.findElement(By.name('submit_search')).click()

System.out.println('Title: ' + driver.getTitle())

driver.close()