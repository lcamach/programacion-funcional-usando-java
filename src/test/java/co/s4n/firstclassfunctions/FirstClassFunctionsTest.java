package co.s4n.firstclassfunctions;

import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class FirstClassFunctionsTest {

    @Test
    public void identityTest() {
        //f(x) = x
        Function<String, String> identity = Function.identity();

        assertEquals("Hello!", identity.apply("Hello!"));
    }

    @Test
    public void composeTest() {
        Function<Integer, Integer> multiplyBy2 = i -> i * 2;
        Function<Integer, String> convertToString = Object::toString;

        /* (g°f)(x)
            g(f(x))
            convertToString(multiplyBy2(x))
        */
        Function<Integer, String> convertToStringMultiplyBy2 = convertToString.compose(multiplyBy2);

        assertEquals("20", convertToStringMultiplyBy2.apply(5));
    }

    @Test
    public void andThenAndComposeTest() {
        Function<Integer, Integer> multiplyBy2 = i -> i * 2;
        Function<Integer, String> convertToString = Object::toString;

        Function<Integer, String> convertToStringMultiplyBy2 = convertToString.compose(multiplyBy2);
        Function<Integer, String> multiplyBy2AndConvertToString = multiplyBy2.andThen(convertToString);

        assertEquals("20", multiplyBy2AndConvertToString.apply(5));
        assertEquals("20", convertToStringMultiplyBy2.apply(5));
    }

    @Test
    public void bifunctionTest() {
        BiFunction<Integer, Integer, String> convertIntegersToString = (x, y) -> String.format("%s + %s", x.toString(), y.toString());
        String r = convertIntegersToString.andThen("x = "::concat).apply(5, 4);

        assertEquals("x = 5 + 4", r);
    }

    @Test
    public void recipe() {
        Function<String, String> peel = (String i) -> String.format("%s peeled,", i);
        Function<String, String> cut = FirstClassFunctionsTest::printCut;
        Function<String, String> fry = i -> String.format("%s fried,", i);
        Function<String, String> serve = i -> {
            return String.format("%s served", i);
        };
        Function<String, String> present = i -> String.format("Delicious: %s, ready to eat!", i);

        //Higher order funtion
        Function<String, String> peelAndCut = peel.andThen(cut);

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


        Function<String, String> makeEasySalad =
                present.compose(peelAndCut
                        .andThen(serve)
                );

        String tomatoes = "tomatoes";

        assertEquals("Delicious: tomatoes peeled, chopped up, served, ready to eat!",
                makeEasySalad.apply(tomatoes));
    }

    private static String printCut(String i) {
        return String.format("%s chopped up,", i);
    }

}