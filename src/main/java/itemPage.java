import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class itemPage extends basePage{
    private Price price;
    public itemPage(){
        price = new Price();
    }

    @FindBy(xpath = "//span[@class='current-price-value']")
    public WebElement webPrice;

    @FindBy(xpath = "//div[@class='desktop-selector']/select")
    public WebElement webSelect;

    @FindBy(xpath = "//button[@class = 'btn btn-cart btn-lg']")
    public WebElement buyBatton;

    public Price getPagePrice() {
        while (true) {
            waitForElement(webPrice);
            String[] priceString = webPrice.getText().split(" ");
            int price = Integer.parseInt(priceString[0]) * 1000 + Integer.parseInt(priceString[1]);
            if (price != this.price.getPrice()) {
                this.price.setPrice(price);
                break;
            }
        }
        return this.price;
    }
    public void selectGuarantFor2Year(){
        waitForElement(webSelect);
        webSelect.sendKeys("2");
    }
    public void clickBuy(){
        waitForElement(buyBatton);
        buyBatton.click();
    }


}
//Игровая приставка PlayStation 4 Slim Black 1 TB + 3 игры