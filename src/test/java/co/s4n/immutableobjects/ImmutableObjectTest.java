package co.s4n.immutableobjects;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ImmutableObjectTest {

    @Test
    public void immutableString() {
        String x = "x";
        String y = x.replace("x", "y");

        assertEquals( "x", x);
        assertEquals("y", y);
    }
}
