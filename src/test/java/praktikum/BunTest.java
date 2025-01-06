package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class BunTest {

    private final String name;
    private final float price;

    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "name = {0}, price = {1}")
    public static Object[][] bunTest() {
        return new Object[][]{
                {"Красная булка", 0.0f},
                {"127", 200},
                {"", -100f},
                {null, 33.0f},
                {"Bun with chicken, bacon, beef and cheese very nice", 7.0f},
                {"Special symbols $#@!*", 3.0f},
                {"Cheap bun", Float.MIN_VALUE},
                {"Expensive bun", Float.MAX_VALUE}
        };
    }

    @Test
    public void getNameWorksSuccessfully() {
        Bun bun = new Bun(name, price);
        String actualBun = bun.getName();
        Assert.assertEquals("Ошибка: Наименование не совпадает с ожидаемым", name, actualBun);
    }

    @Test
    public void getPriceWorksSuccessfully() {
        Bun bun = new Bun(name, price);
        float actualPrice = bun.getPrice();
        Assert.assertEquals("Ошибка: Цена не совпадает с ожидаемой", price, actualPrice, 0);
    }
}
