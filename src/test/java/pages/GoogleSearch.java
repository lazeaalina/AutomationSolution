package pages;

import baseSetup.UITestBaseClass;
import helpers.HelperMethods;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class GoogleSearch extends UITestBaseClass {
    String text = "sometext";
    String url = "https://google.com";
    HelperMethods helpers = new HelperMethods();

    @Test
    public void searchForText() {
        open(url,"Google");

        WebElement searchInput = helpers.returnVisibleElementByName("q" );
        searchInput.sendKeys(text);
        searchInput.sendKeys(Keys.ENTER);
        helpers.verifyRedirection("result-stats");
    }
}
