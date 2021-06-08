package edu.kis.powp.jobs2d;

import edu.kis.powp.jobs2d.drivers.CompositeDriver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CompositeDriverTest {
    private Job2dDriver sampleDriver = new SampleDriver();
    private Job2dDriver testDriver = new TestDriver();
    private CompositeDriver compositeDriver = new CompositeDriver();
    @BeforeEach
    void setUp(){
        compositeDriver.add(sampleDriver);
        compositeDriver.add(testDriver);
    }

    @Test
    void setToTest(){
        compositeDriver.setPosition(1, 2);
    }

    @Test
    void operateToTest(){
        compositeDriver.operateTo(1, 10);
    }

    @Test
    void checkSizeOfChildrenList(){
        assertEquals(2, compositeDriver.getChildren().length);
        compositeDriver.remove(sampleDriver);
        assertEquals(1, compositeDriver.getChildren().length);
    }
    @Test
    void getChildrenTest(){
        Job2dDriver[] array = compositeDriver.getChildren();
        assertTrue(Arrays.stream(array).anyMatch(x-> x.equals(sampleDriver))
                && Arrays.stream(array).anyMatch(x-> x.equals(testDriver)));
    }

    @Test
    void booleanHandlerTest(){
        SampleDriver sampleDriver2 = new SampleDriver();
        compositeDriver.checkBoxHandler(sampleDriver2);
        assertEquals(3, compositeDriver.getSize());
        compositeDriver.checkBoxHandler(sampleDriver2);
        assertEquals(2, compositeDriver.getSize());

    }



    private static class SampleDriver implements Job2dDriver{

        @Override
        public void setPosition(int i, int i1) {
            System.out.println("Sample Driver set position at pos = " + i + ", " + i1);
        }

        @Override
        public void operateTo(int i, int i1) {
            System.out.println("Sample driver operate to pos = " + i + ", " + i1);

        }
    }

    private static class TestDriver implements Job2dDriver{

        @Override
        public void setPosition(int i, int i1) {
            System.out.println("Test driver set position at pos = " + i + ", " + i1);
        }

        @Override
        public void operateTo(int i, int i1) {
            System.out.println("Test driver operate to pos = " + i + ", " + i1);
        }
    }
}
