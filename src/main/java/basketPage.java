
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class basketPage extends basePage{
    @FindBy(xpath = "//div[@class='radio radio_checked']/label[text()='+ 2 года (4 680']")
    public WebElement guarant;

    @FindBy(xpath = "//div[@class='item-price']/span")
    public List<WebElement> prices;

    @FindBy(xpath = "//button [@class='remove-button']")
    public List<WebElement> removes;

    @FindBy(xpath = "//div[@class='cart-list__product-name']/a")
    public List<WebElement> items;

    @FindBy(xpath = "//button[@class='count-buttons__button count-buttons__button_plus']")
    public WebElement plus;

    @FindBy(xpath = "//a[@class='restore-last-removed']")
    public WebElement remove;


    public void checkGuarantFor2Years(){
        try {
            waitForElement(guarant);
            System.out.println("В корзине гарантия 2 года");
        }
        catch (TimeoutException e){
            System.out.println("В корзине нет гарантии 2 года");
        }
    }
    public void checkPrices(int guarant){
        Price price = new Price();
        System.out.print("Сумма: ");
        System.out.println(price.toPrice(prices.get(0).getText()) + guarant + price.toPrice(prices.get(1).getText()));
        System.out.println("Всего в корзине: " + prices.get(2).getText());
    }
    public void removeItem(int itemNomber, int totalPrice){
        waitForElement(removes.get(itemNomber));
        removes.get(itemNomber).click();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        while (true){
            try{
                removes.get(itemNomber).isDisplayed();
            }catch (StaleElementReferenceException e){
                System.out.println("Объект удален");
                break;
            }catch (IndexOutOfBoundsException e1){
                System.out.println("Объект удален");
                break;
            }
        }

    }

    public void checkItems(){
        waitForElement(items.get(0));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        for (WebElement element : items){
            System.out.println("В корзине остался: " + element.getText());
        }
        //System.out.println("В корзине остался: " + item.getText());
        waitForElement(prices.get(0));
        WebElement price = prices.get(prices.size()-1);
        System.out.println("Сумма: " + price.getText());
    }
    public void plusItem(int totalPrice){
        waitForElement(plus);
        Price price = new Price();
        plus.click();
        while (true){
            WebElement webElement = prices.get(prices.size()-1);
            if(price.toPrice(webElement.getText())>totalPrice){
                totalPrice = price.toPrice(webElement.getText());
                break;
            }
        }
        plus.click();
        while (true){
            WebElement webElement = prices.get(prices.size()-1);
            if(price.toPrice(webElement.getText())>totalPrice){
                totalPrice = price.toPrice(webElement.getText());
                break;
            }
        }
        System.out.println("Сумма: " + totalPrice);
    }
    public void clickRemove(int totalPrice){
        waitForElement(remove);
        remove.click();
        Price price = new Price();
        while (true){
            WebElement webElement = prices.get(prices.size()-1);
            if(price.toPrice(webElement.getText())==totalPrice){
                break;
            }
        }
    }
}
