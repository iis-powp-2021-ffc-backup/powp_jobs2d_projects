package edu.kis.powp.jobs2d;

import edu.kis.powp.jobs2d.command.gui.CommandManagerWindow;
import edu.kis.powp.jobs2d.drivers.CompositeDriver;
import edu.kis.powp.jobs2d.drivers.UsageSubscriber;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CompositeDriverTest {
    private Job2dDriver sampleDriver = new SampleDriver();
    private Job2dDriver testDriver = new TestDriver();
    private CompositeDriver compositeDriver = new CompositeDriver(null);
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
    void usageSubscriberSetToOperations(){
        compositeDriver.setPosition(1, 2);
        assertEquals(compositeDriver.getSetOperations(), 2);
        assertEquals(compositeDriver.getAllOperations(), 2);
    }

    @Test
    void usageSubscriberAllOperations(){
        compositeDriver.operateTo(10, 10);
        assertEquals(compositeDriver.getAllOperations(), 4);
    }

    @Test
    void distanceTest(){
        compositeDriver.operateTo(10, 10); // 14.1421
        compositeDriver.operateTo(1, 1); // 26.8700
        compositeDriver.operateTo(-3, 3);
        System.out.println(compositeDriver.getDistance());

    }




    private static class SampleDriver implements Job2dDriver{

        @Override
        public void setPosition(int i, int i1) {
            System.out.println("Sample Driver set position at pos = " + i + ", " + i1);
        }

        @Override
        public void operateTo(int i, int i1) {
            System.out.println("Sample driver operate to pos = " + i + ", " + i1);
            setPosition(i, i1);

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
            setPosition(i, i1);

        }
    }
}
