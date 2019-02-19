import com.google.common.collect.Ordering;
import common.CommonStrings;
import driver.Driver;
import org.apache.http.client.utils.URIBuilder;
import org.junit.*;
import pageObjects.Car;
import pageObjects.SearchPage;
import org.openqa.selenium.support.ui.Select;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class SeleniumTests {
    private static List<Double> carPricesList = new ArrayList<>();
    private static List<Integer> registrationYearList = new ArrayList<>();

    @BeforeClass
    public static void preparePage() throws URISyntaxException {
        URI uri = new URIBuilder()
                .setScheme(CommonStrings.scheme)
                .setHost(CommonStrings.host)
                .setPath(CommonStrings.pathSearch)
                .build();
        Driver.getDriver().get(uri.toString());

        SearchPage searchPage = new SearchPage(Driver.getDriver());

        new Select(searchPage.sortingDropDown).selectByVisibleText(CommonStrings.höchsterPreis);
        Driver.waitTillPageLoaded();

        searchPage.filterYear.click();
        new Select(searchPage.yearFilterDropDown).selectByVisibleText(CommonStrings.year);
        Driver.waitTillPageLoaded();
        Driver.waitTillElementAppersOnPage(searchPage.activeFilter);

        for (Car car : searchPage.carsList) {
            Double price = Double.parseDouble(car.price.getText().split(" ")[0]);
            Integer year = Integer.parseInt(car.date.getText().split("/")[1]);
            carPricesList.add(price);
            registrationYearList.add(year);
        }
    }

    @Test
    public void verifyCarsSortedDescending() {

        boolean areCarsSortedByPriceDescending = Ordering.natural().reverse().isOrdered(carPricesList);
        Assert.assertTrue("Cars are not sorted by price descending",areCarsSortedByPriceDescending);
    }

    @Test
    public void verifyCarsFilteredByYear(){
        int expectedYear = 2015;
        boolean areCarsFilteredByYear = registrationYearList.stream().allMatch(year -> year >= expectedYear);
        Assert.assertTrue("Cars are not filtered by year 2015", areCarsFilteredByYear);
    }

    @AfterClass
    public static void cleanUp(){
        Driver.closeDriver();
    }
}
