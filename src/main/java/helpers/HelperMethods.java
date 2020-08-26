package ui;

import UITestBaseClass;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class helperMethods extends UITestBaseClass{
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
