package ui;

import baseClass.UITestBaseClass;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;


public class GoogleSearch extends UITestBaseClass {
    String text = "sometext";

    @Test
    public void searchForText() {

        WebElement searchInput = returnVisibleElementByName("q" );
        searchInput.sendKeys(text);
        searchInput.sendKeys(Keys.ENTER);
        verifyRedirection("result-stats");

    }
}
