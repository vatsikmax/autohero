package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

import java.util.List;

public class SearchPage {

    public SearchPage(WebDriver driver) {
        HtmlElementLoader.populate(this, driver);
    }
    @FindBy(css = "[data-qa-selector='filter-year']")
    public HtmlElement filterYear;

    @FindBy(css = "[data-qa-selector='filter-year'] [data-qa-selector='select']")
    public HtmlElement yearFilterDropDown;

    @FindBy(css = "[data-qa-selector='sort-select'] [data-qa-selector='select']")
    public HtmlElement sortingDropDown;

    @FindBy(css = "[data-qa-selector='active-filter']")
    public HtmlElement activeFilter;

    @FindBy(css = "[data-qa-selector='ad-items'] [class='item___T1IPF']")
    public List<Car> carsList;
}
