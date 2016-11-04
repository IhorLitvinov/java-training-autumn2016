package ua.kpi.tef;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class ModelTest {


    @Test
    public void setSecretValueNumericOverflowTest() throws Exception {
        Model model = new Model();
        int minBarrier = -1;
        int maxBarrier = Integer.MAX_VALUE;
        model.setPrimaryBarrier(minBarrier, maxBarrier);
        model.getSecretValue();
        assertTrue(model.getSecretValue() > minBarrier);
    }

    @Test
    public void setSecretValueNumericOverflowMinIntTest() throws Exception {
        Model model = new Model();
        int minBarrier = Integer.MIN_VALUE;
        int maxBarrier = 1;
        model.setPrimaryBarrier(minBarrier, maxBarrier);
        model.getSecretValue();
        assertTrue(model.getSecretValue() < maxBarrier);
    }

    @Test
    public void isSecretValueBarriersShiftingTest() {
        Model model = new Model();
        int minBarrier = Integer.MIN_VALUE;
        int maxBarrier = 0;
        model.setPrimaryBarrier(minBarrier, maxBarrier);
        model.setSecretValue();
        int secretValue = model.getSecretValue();
        int[] expectedBarriers = {secretValue - 1, secretValue + 1};
        model.isSecretValue(expectedBarriers[0]);
        model.isSecretValue(expectedBarriers[1]);
        int[] actualBarriers = {model.getMinBarrier(), model.getMaxBarrier()};
        assertArrayEquals(expectedBarriers, actualBarriers);
    }
}