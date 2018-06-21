package co.com.s4n.semillero.ejercicio.otherTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.when;


class ClassWithStatics {
    public static String getString() {
        return "String";
    }

    public static int getInt() {
        return 1;
    }
}

//@RunWith(PowerMockRunner.class)
//@PrepareForTest(fullyQualifiedNames = "co.com.s4n.semillero.ejercicio.otherTest.ClassWithStatics")
public class ExampleMockTest {

    //@PrepareForTest(ClassWithStatics.class)
  /*  @Test
    public void test() {
        PowerMockito.mockStatic(ClassWithStatics.class);

        when(ClassWithStatics.getString()).thenReturn("Hello");

        assertEquals("Hello", ClassWithStatics.getString());
    }
*/
}
