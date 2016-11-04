package ua.kpi.tef;

public class View {
    static final String GREETING = "Hi! Welcome to the game! Let's set the boundaries of possible values.";
    static final String SET_MIN_BARRIER = "Set min barrier.";
    static final String SET_MAX_BARRIER = "Set max barrier.";
    static final String WRONG_BARRIERS = "Wrong barriers! Max barrier should be bigger that min barrier by at least 1.";
    static final String INPUT_INT_DATA = "Input INT value. ";
    static final String WRONG_INPUT_INT_DATA = "Wrong input! Repeat please! ";
    static final String WRONG_RANGE_DATA = "Wrong range! Repeat please! ";
    static final String CONGRATULATION = "CONGRATULATION!!! Secret value = ";

    /**
     * Prints massage
     *
     * @param message
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Asks to input value between barriers
     *
     * @param minBarrier
     * @param maxBarrier
     */
    public void printDataInputMessage(int minBarrier, int maxBarrier) {
        System.out.println(INPUT_INT_DATA + "Range between " + minBarrier + " and " + maxBarrier);
    }
}
