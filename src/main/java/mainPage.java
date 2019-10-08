import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class mainPage extends basePage {
    @FindBy(xpath = "//input[ @class='ui-input-search__input main-search-form__input ui-autocomplete-input']")
    public WebElement searchTextInput;

    @FindBy(xpath = "//div[@class='container']/form/div/div/div/span[@class='ui-input-search__icon ui-input-search__icon_search']")
    public WebElement searchButton;

    @FindBy(xpath = "//div[@class='buttons']/a[@class='btn-cart-link']/span/span")
    public WebElement totalPrice;


    public void searhItems(String name){
        searchTextInput.clear();
        searchTextInput.sendKeys(name);
        searchButton.click();
    }
    public void getSearch(String name){
        name = "//a[text()='"+ name +"']";
        WebElement webElement = waitForClickable(name);
        webElement.click();

    }
    public Price basketPrice(Price sumPrice){
        int total = 0;
        Price basket = new Price();
        waitForElement(totalPrice);
        while (true){
            total = basket.toPrice(totalPrice.getText());
            if(sumPrice.getPrice() == total){
                break;
            }
        }
        basket.setPrice(total);
        return basket;
    }
    public void clickBasket(){
        waitForElement(totalPrice);
        totalPrice.click();
    }

}
