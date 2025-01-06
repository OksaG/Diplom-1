package praktikum;

import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Random;

@RunWith(Parameterized.class)
public class IngredientsTypeTest {

    Ingredient ingredient;
    private final IngredientType type;

    public IngredientsTypeTest(IngredientType type) {
        this.type = type;
    }

    @Before
    public void setup() {
        ingredient = new Ingredient(type, RandomString.make(10), new Random().nextFloat());
    }

    @Parameterized.Parameters(name = "type = {0}")
    public static Object[][] ingredientsTypeParams() {
        return new Object[][]{
                {IngredientType.SAUCE},
                {IngredientType.FILLING}
        };
    }

    @Test
    public void getTypeWorkSuccessfully() {
        IngredientType actual = ingredient.getType();
        Assert.assertEquals("Ошибка: Тип ингредиента не совпадает", type, actual);
    }
}