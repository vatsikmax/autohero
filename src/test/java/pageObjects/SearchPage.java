package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

import java.util.List;

public class SearchPage {

    public SearchPage(WebDriver driver) {
        HtmlElementLoader.populate(this, driver);
//        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }
    @CacheLookup
    @FindBy(css = "[data-qa-selector='filter-year']")
    public WebElement filterYear;

    @CacheLookup
    @FindBy(css = "[data-qa-selector='filter-year'] [data-qa-selector='select']")
    public WebElement yearFilterDropDown;

    @CacheLookup
    @FindBy(css = "[data-qa-selector='sort-select'] [data-qa-selector='select']")
    public HtmlElement sortingDropDown;

    @CacheLookup
    @FindAll({@FindBy(css = "[data-qa-selector='ad-items'] [class='item___T1IPF']")})
    public List<Car> carsList;

}
