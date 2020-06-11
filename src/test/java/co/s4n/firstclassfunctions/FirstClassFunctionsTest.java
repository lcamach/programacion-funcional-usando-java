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

        assertEquals("10", convertToStringMultiplyBy2.apply(5));
    }

    @Test
    public void andThenAndComposeTest() {
        Function<Integer, Integer> multiplyBy2 = i -> i * 2;
        Function<Integer, String> convertToString = Object::toString;

        Function<Integer, String> convertToStringMultiplyBy2 = convertToString.compose(multiplyBy2);
        Function<Integer, String> multiplyBy2AndConvertToString = multiplyBy2.andThen(convertToString);

        assertEquals("10", multiplyBy2AndConvertToString.apply(5));
        assertEquals("10", convertToStringMultiplyBy2.apply(5));
    }

    @Test
    public void bifunctionTest() {
        final String globalState = "x = ";

        BiFunction<Integer, Integer, String> convertIntegersToString =
                (x, y) -> String.format("%s + %s", x.toString(), y.toString());

        String r = convertIntegersToString
                .andThen(s -> globalState.concat(s)) //Closure
                .apply(5, 4);

        assertEquals("x = 5 + 4", r);
    }
}