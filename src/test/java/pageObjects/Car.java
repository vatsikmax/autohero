package pageObjects;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

public class Car extends HtmlElement {

    @FindBy(css = "[data-qa-selector='price']")
    public HtmlElement price;

    @FindBy (css = "[data-qa-selector='spec']:first-of-type")
    public HtmlElement date;
}
