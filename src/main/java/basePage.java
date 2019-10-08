
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class basePage {
    WebDriver driver;
    WebDriverWait wait;

    public basePage(){
        this.driver = Init.getDriver();
        PageFactory.initElements(driver, this);//Драйвер не начинает искать элементы на странице сразу же, а ищет их как только мы обращаемся к полю класса
        wait = new WebDriverWait(driver, 5);
    }

    public WebElement waitForElement(WebElement element){
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    WebElement waitForClickable(String xpath) {
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }


}
