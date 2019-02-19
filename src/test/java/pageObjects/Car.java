package pageObjects;

import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

public class Car extends HtmlElement {

    @CacheLookup
    @FindBy(css = "[data-qa-selector='price']")
    public HtmlElement price;

    @CacheLookup
    @FindBy (css = "[data-qa-selector='ad-items'] [class='item___T1IPF'] [data-qa-selector='spec']:first-of-type")
    public HtmlElement date;
}
