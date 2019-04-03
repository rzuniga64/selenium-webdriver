package junit;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *  MyClassTest class.
 *  - in order to a test to be considered a test it must be under the src/test folder.
 *  - In order for a method to be treated as a test, in the Junit 4 version, we annotate the test with @Test.
 *  - You must have 'Test' in the method name.
 */
public class MyClassTest {

    @Test
    public void testMultiply() {

        MyClass tester = new MyClass();
        assertEquals("10 x 0 must be 0", 0, tester.multiply(10, 0));
        assertEquals("0 x 10 must be 0", 0, tester.multiply(0, 10));
        assertEquals("0 x 0 must be 0", 0, tester.multiply(0, 0));
    }
}
