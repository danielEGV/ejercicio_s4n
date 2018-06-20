package co.com.s4n.semillero.ejercicio.otherTest;



import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.powermock.api.mockito.PowerMockito;
//import org.powermock.core.classloader.annotations.PrepareForTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class testMockito {

    private Calculator calculator;


    public void setUp() {
        calculator = new Calculator();

        PowerMockito.mockStatic(MathUtil.class);
        PowerMockito.when(MathUtil.addInteger(1, 1)).thenReturn(0);
        PowerMockito.when(MathUtil.addInteger(2, 2)).thenReturn(1);
    }

    @Test
    public void shouldCalculateInAStrangeWay() {
        calculator = new Calculator();
        PowerMockito.spy(MathUtil.class);
        PowerMockito.verifyStatic(MathUtil.class);
        //assertEquals(0, calculator.add(1, 1));
        //assertEquals(1, calculator.add(2, 2));
    }




}
