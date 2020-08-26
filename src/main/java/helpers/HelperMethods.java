package helpers;

import baseSetup.UITestBaseClass;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.testng.Assert;

public class HelperMethods extends UITestBaseClass {

    public WebElement returnVisibleElementByName(String selector){
        WebElement element = wait.until( //NullPointerException for WebDriverWait
                ExpectedConditions.visibilityOfElementLocated(By.name(selector)));
        return element;
    }

    public WebElement returnVisibleElementById(String selector){
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
