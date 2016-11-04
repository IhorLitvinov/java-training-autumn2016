package ua.kpi.tef;

import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;

public class ControllerTest {
    private Model model = new Model();
    private View view = new View();
    private Controller controller = new Controller(model, view);

    @Test
    public void areBoundariesSatisfactoryNoValueBetween() {
        int minBoundary = 1;
        int maxBoundary = 2;
        assertFalse(controller.areBoundariesSatisfactory(minBoundary, maxBoundary));
    }

    @Test
    public void areBoundariesSatisfactoryMinBiggerMax() {
        int minBoundary = 9;
        int maxBoundary = 2;
        assertFalse(controller.areBoundariesSatisfactory(minBoundary, maxBoundary));
    }

    @Test
    public void areBoundariesSatisfactoryNegativeNumbers() {
        int minBoundary = -9;
        int maxBoundary = -2;
        assertTrue(controller.areBoundariesSatisfactory(minBoundary, maxBoundary));
    }

    @Test
    public void areBoundariesSatisfactoryNegativeNumbersNoBetween() {
        int minBoundary = -3;
        int maxBoundary = -2;
        assertFalse(controller.areBoundariesSatisfactory(minBoundary, maxBoundary));
    }

    @Test
    public void areBoundariesSatisfactoryIntMaxValues() {
        int minBoundary = Integer.MIN_VALUE;
        int maxBoundary = Integer.MAX_VALUE;
        assertTrue(controller.areBoundariesSatisfactory(minBoundary, maxBoundary));
    }

    @Test
    public void areBoundariesSatisfactoryIntMaxValue() {
        int minBoundary = 1;
        int maxBoundary = Integer.MAX_VALUE;
        assertTrue(controller.areBoundariesSatisfactory(minBoundary, maxBoundary));
    }

    @Test
    public void areBoundariesSatisfactoryIntMinValue() {
        int minBoundary = Integer.MIN_VALUE;
        int maxBoundary = 0;
        assertTrue(controller.areBoundariesSatisfactory(minBoundary, maxBoundary));
    }

    @Test
    public void skipNonIntValuesTest() {
        int expectedValue = 72;
        String trash = "dak da 13l 98d ";
        Scanner scanner = new Scanner(trash + expectedValue);
        controller.skipNonIntValues(scanner);
        int actualValue = scanner.nextInt();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void getInputValueFromScannerWrongInput() {
        Model model = new Model();
        model.setPrimaryBarrier(2, 78);
        Controller controller = new Controller(model, view);
        int expectedValue = 37;
        String trash = "6frkn 3r28h&%&% 76&&% ^ gf 7i ";
        Scanner scanner = new Scanner(trash + expectedValue);
        int actualValue = controller.getInputValueFromScanner(scanner);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void getInputValueFromScannerWrongRange() {
        Model model = new Model();
        model.setPrimaryBarrier(-24, -3);
        Controller controller = new Controller(model, view);
        int expectedValue = -10;
        String trashAndWrongRange = "6frkn 3r28h&%&% 11 76&&% -100 -3 -24 ^ gf 7i ";
        Scanner scanner = new Scanner(trashAndWrongRange + expectedValue);
        int actualValue = controller.getInputValueFromScanner(scanner);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void setBoundariesWrongBoundaries() {
        Model model = new Model();
        Controller controller = new Controller(model, view);

        int minBoundaryExpected = -10;
        int maxBoundaryExpected = -1;

        int[] expectedBoundaries = {minBoundaryExpected, maxBoundaryExpected};

        String firstTrash = "dskh dsl ";
        String firstWrongBoundaries = "25 25 ";
        String secondWrongBoundaries = "-25 -24 ";
        String thirdWrongBoundaries = "5 4 ";
        String secondTrash = "43h fek4 k3 32.4 3.0 ";

        String allInput = firstTrash
                + firstWrongBoundaries
                + secondTrash
                + secondWrongBoundaries
                + thirdWrongBoundaries
                + minBoundaryExpected + " " + maxBoundaryExpected;
        Scanner scanner = new Scanner(allInput);
        controller.setBoundaries(scanner);
        int[] actualBoundaries = {model.getMinBarrier(), model.getMaxBarrier()};
        assertArrayEquals(expectedBoundaries, actualBoundaries);
    }

    @Test
    public void secretValueGuessingLoopBarriersShiftTest() {
        Model model = new Model();

        int minBarrier = Integer.MAX_VALUE - 20;
        int maxBarrier = Integer.MAX_VALUE;

        model.setPrimaryBarrier(minBarrier, maxBarrier);
        model.setSecretValue();
        int secretValue = model.getSecretValue();

        Controller controller = new Controller(model, view);

        int[] expectedFinalBarriers = {secretValue - 1, secretValue + 1};

        String inputNewBarriers =
                expectedFinalBarriers[0] + " " + expectedFinalBarriers[1];

        Scanner scanner = new Scanner(inputNewBarriers + " " + secretValue);

        controller.secretValueGuessingLoop(scanner);

        int[] actualFinalBarriers = {model.getMinBarrier(), model.getMaxBarrier()};
        assertArrayEquals(expectedFinalBarriers, actualFinalBarriers);
    }

}