package praktikum;

import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    Bun bun;

    @Mock
    Ingredient ingredient;

    @Mock
    Ingredient ingredientFilling;

    @Test
    public void setBunsWorksSuccessfully() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        Bun actualResult = burger.bun;
        Assert.assertEquals("Ошибка: Булка не добавлена", bun, actualResult);
    }

    @Test
    public void addIngredientWorksSuccessfully() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        int actualResult = burger.ingredients.size();
        int expectedResult = 1;
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.ingredients.contains(ingredient))
                .as("Ошибка: Ингредиент не совпадает с ожидаемым")
                .isTrue();
        softly.assertThat(actualResult)
                .as("Ошибка: Некорретное количество ингредиентов после добавления")
                .isEqualTo(expectedResult);
        softly.assertAll();
    }

    @Test
    public void removeIngredientWorksSuccessfully() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        Assert.assertTrue("Ошибка: Ингредиент не удалился", burger.ingredients.isEmpty());
    }

    @Test
    public void moveIngredientWorksSuccessfully() {
        Burger burger = new Burger();
        burger.ingredients.add(ingredient);
        burger.ingredients.add(ingredientFilling);
        burger.moveIngredient(0, 1);
        Assert.assertEquals("Ошибка: Ингредиенты не поменялись местами", ingredient, burger.ingredients.get(1));
    }

    @Test
    public void getPriceWorksSuccessfully() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        Mockito.when(bun.getPrice()).thenReturn(100f);
        Mockito.when(ingredient.getPrice()).thenReturn(200f);
        float sumBurgerPrice = bun.getPrice() * 2 + ingredient.getPrice();
        Assert.assertEquals("Ошибка: Сумма бургера не совпадает с ожидаемой", sumBurgerPrice, burger.getPrice(), 0);
    }

    @Test
    public void getReceiptWorksSuccessfully() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        Mockito.when(bun.getPrice()).thenReturn(100f);
        Mockito.when(bun.getName()).thenReturn("Булочка");
        Mockito.when(ingredient.getName()).thenReturn("Соус");
        Mockito.when(ingredient.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredient.getPrice()).thenReturn(200f);
        String receipt = burger.getReceipt();
        String ingredientType = ingredient.getType().toString().toLowerCase();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(receipt.contains(bun.getName()))
                .as("Ошибка: Рецепт должен содержать название булки '%s'", bun.getName())
                .isTrue();
        softly.assertThat(receipt.contains(String.format("%nPrice: %f%n", burger.getPrice())))
                .as("Ошибка: Рецепт должен содержать цену '%f'", burger.getPrice())
                .isTrue();
        softly.assertThat(receipt.contains(String.format("= %s %s =%n", ingredientType, ingredient.getName())))
                .as("Ошибка: Рецепт должен содержать ингредиент '%s %s'", ingredientType, ingredient.getName())
                .isTrue();
        softly.assertAll();
    }
}