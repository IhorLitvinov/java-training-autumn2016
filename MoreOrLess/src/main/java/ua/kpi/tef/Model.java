package ua.kpi.tef;

public class Model {
    private int minBarrier;

    private int maxBarrier;

    private int secretValue;

    /**
     * Indicates whether some other value is equal to secret value and shifts barriers if not.
     *
     * @param value
     */
    public boolean isSecretValue(int value) {
        if (value == secretValue) {
            return true;
        } else if (value > secretValue) {
            maxBarrier = value;
        } else {
            minBarrier = value;
        }
        return false;
    }

    /**
     * Sets boundaries for secret value
     *
     * @param minBarrier
     * @param maxBarrier
     */
    public void setPrimaryBarrier(int minBarrier, int maxBarrier) {
        this.minBarrier = minBarrier;
        this.maxBarrier = maxBarrier;
    }

    /**
     * Generates a secret value in defined range
     */
    public void setSecretValue() {
        secretValue = (int) Math.ceil(Math.random() *
                ((long) maxBarrier - minBarrier - 1) + minBarrier);
    }

    public int getSecretValue() {
        return secretValue;
    }

    public int getMinBarrier() {
        return minBarrier;
    }

    public int getMaxBarrier() {
        return maxBarrier;
    }
}
