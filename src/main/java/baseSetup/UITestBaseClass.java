package baseClass;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.IOException;

public class UITestBaseClass {
    public WebDriver driver;
    ExtentReports report;
    ExtentTest test;

    @BeforeMethod
    @Parameters("browser")
    public void startUpBrowser(String browser){
        startReporting();
        testName("Google search on " + browser +  " browser");

        if(browser.matches("Chrome")){
            System.out.println("browser is: " + browser);
            System.setProperty("webdriver.chrome.driver", "E:\\Automation\\drivers\\chromedriver.exe");
            driver = new ChromeDriver();
        }else if(browser.matches("Firefox")){
            System.out.println("browser is: " + browser);
            System.setProperty("webdriver.gecko.driver", "E:\\Automation\\drivers\\geckodriver.exe");
            driver = new FirefoxDriver();
        }else{
            System.out.println("browser is: " + browser);
            System.setProperty("webdriver.opera.driver", "E:\\Automation\\drivers\\operadriver.exe");
            driver = new OperaDriver();
        }

        driver.get("https://google.com");
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, "Google");
    }



    @AfterMethod(alwaysRun = true)
    @Parameters("browser")
    public void closeBrowser(String browser) throws IOException {
        if(!browser.matches("Opera")){
            driver.close();
        }else{
            Runtime.getRuntime().exec("taskkill /f /im opera.exe");
        }

        System.out.println("Closing down the browser");
        report.endTest(test);
        report.flush();
    }

    public void testName(String testName){
        test = report.startTest(testName);
    }

    public void startReporting(){
        report = new ExtentReports("C:\\Users\\Alina\\Desktop\\AutomationSolution2\\src\\test\\Reports\\report.html", false);
    }

    public WebElement returnVisibleElementByName(String selector){
        WebDriverWait wait = new WebDriverWait(driver, 25);
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name(selector)));
        return element;
    }

    public WebElement returnVisibleElementById(String selector){
        WebDriverWait wait = new WebDriverWait(driver, 25);
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id(selector)));
        return element;
    }

    public void verifyRedirection(String selector){
        boolean isDisplayed = returnVisibleElementById(selector).isDisplayed();
        Assert.assertTrue(isDisplayed);

        if(isDisplayed){
            test.log(LogStatus.PASS, "Redirection: successful");
        }else {
            test.log(LogStatus.FAIL, "Redirection: failed");
        }
    }
}
