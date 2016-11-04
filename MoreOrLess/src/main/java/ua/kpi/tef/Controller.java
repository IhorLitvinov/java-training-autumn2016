package ua.kpi.tef;

import java.util.Scanner;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Sets boundaries in game and starts process input
     */
    public void processUser() {
        Scanner systemInScanner = new Scanner(System.in);

        view.printMessage(View.GREETING);
        setBoundaries(systemInScanner);
        model.setSecretValue();
        secretValueGuessingLoop(systemInScanner);
        view.printMessage(View.CONGRATULATION + model.getSecretValue());
    }

    /**
     * Checks if input value is a secret value. If it's not, it asks again
     *
     * @param scanner
     */
    void secretValueGuessingLoop(Scanner scanner) {
        while (true) {
            int scannedValue = getInputValueFromScanner(scanner);
            if (model.isSecretValue(scannedValue))
                break;
        }
    }

    /**
     * Gets values for boundaries from scanner,
     * checks boundaries and sets their in model
     *
     * @param scanner
     */
    void setBoundaries(Scanner scanner) {
        int minBarrier;
        int maxBarrier;
        while (true) {
            view.printMessage(View.SET_MIN_BARRIER);
            skipNonIntValues(scanner);
            minBarrier = scanner.nextInt();

            view.printMessage(View.SET_MAX_BARRIER);
            skipNonIntValues(scanner);
            maxBarrier = scanner.nextInt();
            if (areBoundariesSatisfactory(minBarrier, maxBarrier)) {
                break;
            }
            view.printMessage(View.WRONG_BARRIERS);
        }
        model.setPrimaryBarrier(
                minBarrier,
                maxBarrier
        );
    }

    /**
     * Checks boundaries
     *
     * @param minBarrier
     * @param maxBarrier
     * @return
     */
    boolean areBoundariesSatisfactory(int minBarrier, int maxBarrier) {
        return Integer.MAX_VALUE > minBarrier
                && minBarrier + 1 < maxBarrier;
    }

    void skipNonIntValues(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            view.printMessage(
                    View.WRONG_INPUT_INT_DATA + View.INPUT_INT_DATA
            );
            scanner.next();
        }
    }

    /**
     * Gets input from scanner and takes correct int value or asks re-enter it.
     *
     * @param scanner
     * @return
     */
    int getInputValueFromScanner(Scanner scanner) {
        int inputValue;
        view.printDataInputMessage(model.getMinBarrier(), model.getMaxBarrier());

        while (true) {
            skipNonIntValues(scanner);
            inputValue = scanner.nextInt();

            if (!isValueInRange(inputValue)) {
                view.printMessage(View.WRONG_RANGE_DATA);
                view.printDataInputMessage(model.getMinBarrier(), model.getMaxBarrier());
                continue;
            }
            break;
        }
        return inputValue;
    }

    /**
     * Checks if value in range
     *
     * @param value
     * @return
     */
    boolean isValueInRange(int value) {
        return value > model.getMinBarrier() &&
                value < model.getMaxBarrier();
    }
}
