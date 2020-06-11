package co.s4n.firstclassfunctions;

import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class RecipeTest {

    //Funtions as objects
    private final Function<String, String> peel = (String i) -> String.format("%s peeled,", i);
    private final Function<String, String> cut = RecipeTest::printCut;
    private final Function<String, String> fry = i -> String.format("%s fried,", i);
    private final Function<String, String> serve = i -> {
        return String.format("%s served", i);
    };
    private final Function<String, String> present = i -> String.format("Delicious: %s, ready to eat!", i);

    //Higher order funtion
    private final Function<String, String> peelAndCut = peel.andThen(cut);

    @Test
    public void prepareFriedIngredientTest() {
        Function<String, String> prepareFriedIngredient =
                present.compose(peelAndCut
                        .andThen(fry)
                        .andThen(serve)
                );

        String potatoes = "potatoes";
        String plantains = "plantains";

        assertEquals("Delicious: potatoes peeled, chopped up, fried, served, ready to eat!",
                prepareFriedIngredient.apply(potatoes));

        assertEquals("Delicious: plantains peeled, chopped up, fried, served, ready to eat!",
                prepareFriedIngredient.apply(plantains));
    }

    @Test
    public void prepareEasySaladTest(){
        Function<String, String> prepareEasySalad =
                present.compose(peelAndCut
                        .andThen(serve)
                );

        String tomatoes = "tomatoes";
        assertEquals("Delicious: tomatoes peeled, chopped up, served, ready to eat!",
                prepareEasySalad.apply(tomatoes));
    }

    private static String printCut(String i) {
        return String.format("%s chopped up,", i);
    }

}