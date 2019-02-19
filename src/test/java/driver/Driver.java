package driver;

import common.CommonStrings;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Driver {
    private static String driverName = "webdriver.chrome.driver";
    private static String pathToDriver = "/src/test/java/driver/chromedriver";

    private static String jsCommand = "return document.readyState";
    private static String complete = "complete";

    private static WebDriver driver;

    private static void createDriver(){
        System.setProperty(driverName, System.getProperty(CommonStrings.userDir) + pathToDriver);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    public static WebDriver getDriver(){
        if(driver == null){
            createDriver();
        }
        return driver;
    }

    public static WebDriverWait getWait(){
        WebDriverWait wait = new WebDriverWait(getDriver(), 5);
        return wait;
    }

    public static void waitTillElementAppersOnPage(WebElement element) {
        getWait().until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitTillPageLoaded() {
        getWait().until(driver -> ((JavascriptExecutor) driver).executeScript(jsCommand).equals(complete));
    }

    public static void closeDriver(){
        driver.close();
    }
}
