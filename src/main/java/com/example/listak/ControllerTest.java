package com.example.listak;

import javafx.application.Platform;
import org.junit.*;

import java.util.concurrent.CountDownLatch;

public class ControllerTest {


    public static boolean isRunningTest = false;

    @BeforeClass
    public static void initToolkit() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(latch::countDown);
        latch.await();
    }

    @AfterClass
    public static void testAfterOnce() {
        System.out.println("testAfterOnce");
    }

    @Before
    public void testBefore() {
        isRunningTest = true;
    }

    @After
    public void testAfter() {
        isRunningTest = false;
    }

    @Test
    public void testMain() {
        HelloApplication.main(null);
    }

    @Test
    public void testStart() {
        isRunningTest = true;
        HelloApplication app = new HelloApplication();
        try {
            app.start(null);
        } catch(Exception e) {
            e.printStackTrace();
        }
        isRunningTest = false;
    }

    @Test
    public void testController() {
        HelloController controller = new HelloController();

        controller.initialize(null, null);
        controller.addKitty();
        controller.addBirdies();
        controller.addMushies();
        controller.moveToRight();
        controller.moveToRight();
        controller.delLeft();
        controller.remRight();
        controller.delRight();
        controller.saveList();
    }
}
