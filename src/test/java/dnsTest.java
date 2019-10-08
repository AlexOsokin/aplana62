import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class dnsTest {
    @Before
    public void setUp(){
        Init.initDriver();
    }

    @After
    public void close() {
       Init.closeDriver();
    }

    @Test
    public void mainTest(){
        mainPage main = new mainPage();
        main.searhItems("playstation");
        main.getSearch("Игровая приставка PlayStation 4 Slim Black 1 TB + 3 игры");

        itemPage itemPS  = new itemPage();
        int priceWithoutGuarant = itemPS.getPagePrice().getPrice();
        System.out.println("Цена PS без гаранитии: " +  priceWithoutGuarant);
        itemPS.selectGuarantFor2Year();
        Price pricePS = itemPS.getPagePrice();
        int priceWithGuarant =  pricePS.getPrice();
        System.out.println("Цена PS c гаранитией 2 года: " + priceWithGuarant);
        pricePS.setGuarant(priceWithGuarant - priceWithoutGuarant);
        System.out.println("Цена гаранитии на 2 года: " + pricePS.getGuarant());
        itemPS.clickBuy();

        main.searhItems("Detroit");
        itemPage itemDetroit  = new itemPage();
        Price priceDetroit = itemDetroit.getPagePrice();
        System.out.println("Цена Detroit: " + priceDetroit.getPrice());
        itemDetroit.clickBuy();

        Price sum = priceDetroit.priceSum(pricePS);
        int totalPrice = sum.getPrice();
        System.out.println("Сумма покупки: " + totalPrice);
        System.out.println("Cумма, указанная в корзине: " + main.basketPrice(sum).getPrice());
        main.clickBasket();

        basketPage basket = new basketPage();
        basket.checkGuarantFor2Years();
        basket.checkPrices(pricePS.getGuarant());
        basket.removeItem(1, totalPrice);
        basket.checkItems();
        System.out.print("В корзине без Detroit: ");
        int detroitWithout = totalPrice - priceDetroit.getPrice();
        System.out.println(detroitWithout);

        basket.plusItem(detroitWithout);
        System.out.print("Цена PS с гарантией, умноженная на 3: ");
        System.out.println(priceWithGuarant*3);

        basket.clickRemove(priceWithGuarant*3 + priceDetroit.getPrice());
        basket.checkItems();
        System.out.print("Цена PS с гарантией, умноженная на 3 с добавленной Detroit: ");
        System.out.println(priceWithGuarant*3 + priceDetroit.getPrice());

    }

}
