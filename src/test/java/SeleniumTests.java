import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.Car;
import pageObjects.SearchPage;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeleniumTests {
    String url = "https://www.autohero.com/de/search/";
    public WebDriver driver;
    private static String driverName = "webdriver.chrome.driver";
    private static String pathToDriver = "/Users/maksymalavatskyi/Downloads/chromedriver";
    private static String jsCommand = "return document.readyState";
    private static String complete = "complete";
    private static String year = "2015";
    private static String höchsterPreis = "Höchster Preis";



    @Test
    public void preparePage(){
        System.setProperty(driverName, pathToDriver);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get(url);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        SearchPage searchPage = new SearchPage(driver);
        searchPage.filterYear.click();
        new Select(searchPage.yearFilterDropDown).selectByVisibleText(year);

        wait.until(driver -> ((JavascriptExecutor) driver).executeScript(jsCommand).equals(complete));

        new Select(searchPage.sortingDropDown).selectByVisibleText(höchsterPreis);
        wait.until(driver -> ((JavascriptExecutor) driver).executeScript(jsCommand).equals(complete));

        List<String> priceList = new ArrayList<>();
        List<String> yearList = new ArrayList<>();
        for (int i = 0; i < searchPage.carsList.size(); i++) {
            String price;
            String date;
            Car car;
            try {
                car = new SearchPage(driver).carsList.get(i);
                price = car.price.getText();
                date = car.date.getText();
            } catch (StaleElementReferenceException e){
                car = new SearchPage(driver).carsList.get(i);
                price =car.price.getText();
                date = car.date.getText();
            }
            priceList.add(price);
            yearList.add(date);
        }
        yearList.stream().map(i -> i= (i.split("/")[1]));
        yearList.forEach(i -> i=(i.split("/")[1]));
        priceList.forEach(i -> i = (i.split(" ")[0]));
        Assert.assertTrue(true);
    }
}
