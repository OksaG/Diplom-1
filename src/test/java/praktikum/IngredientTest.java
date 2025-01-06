package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IngredientTest {

    Ingredient ingredient;

    @Before
    public void setup() {
        ingredient = new Ingredient(IngredientType.FILLING, "Филе Люминесцентного тетраодонтимформа", 500f);
    }

    @Test
    public void getPriceWorkSuccessfully() {
        float actual = ingredient.getPrice();
        float expected = 500f;
        Assert.assertEquals("Ошибка: Цена ингредиента не совпадает", expected, actual, 0);
    }

    @Test
    public void getNameWorkSuccessfully() {
        String actual = ingredient.getName();
        String expected = "Филе Люминесцентного тетраодонтимформа";
        Assert.assertEquals("Ошибка: Название ингредиента не совпадает", expected, actual);
    }
}