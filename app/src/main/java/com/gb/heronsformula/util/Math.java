/**
 *
 * Provides Babylonian square-root algorithm and rounding decimal places functionality.
 *
 * Created by Görkem Bendin on 16/10/2020.
 */
package com.gb.heronsformula.util;

import android.util.Log;

public class Math {
    private static final String TAG = "HeronsFormulaCalculator";
    private static final double PRECISION_MAX = 0.000001;
    private static final double PRECISION_MIN = 0.0;
    private static final double SQRT_INITIAL_VALUE = 600;
    private static final double DIVIDER_HALF = 0.5;
    private static final int DECIMAL_PLACES_MAX = 13;
    private static final int TRICKY_NUMBER_MULTIPLIER = 10;
    private static final int ROUND_DECIMAL_INITIAL_VALUE = 1;
    private static final int DEADLOCK_CHECK_COUNT = 3;
    private static final int DEADLOCK_ERROR = -1;
    private static final int ZERO = 0;

    /**
     * Calculates the square root of a number using Babylonian square-root algorithm.
     *
     * @param number The number whose square root to be calculated.
     * @return Returns the calculated square root of the given number.
     */
    public static double sqrt(double number) {
        double result = SQRT_INITIAL_VALUE;
        double previousResult = ZERO;
        int deadlockCheckCounter = ZERO;
        while (true) {
            if ((result == previousResult) ||
                    (result < ZERO)) {
                if ((++deadlockCheckCounter) >= DEADLOCK_CHECK_COUNT) {
                    Log.e(TAG, "DEADLOCK ERROR!");
                    return DEADLOCK_ERROR;
                } /* else not needed */
            }
            previousResult = result;
            result = DIVIDER_HALF * (result + (number / result));
            result = roundDecimalPlaces(result, DECIMAL_PLACES_MAX);
            if (((number - roundDecimalPlaces((result * result), DECIMAL_PLACES_MAX)) <= PRECISION_MAX) &&
                    ((number - roundDecimalPlaces((result * result), DECIMAL_PLACES_MAX)) >= PRECISION_MIN)) {
                return result;
            } /* else not needed */
        }
    }

    /**
     * Removes the decimal places from the given number. <b>Note:</b> Not tested well.
     * This is a memory trick. The idea learned from the reference below and also updated as seems for common use.
     * Reference: https://stackoverflow.com/questions/8486878/how-to-cut-off-decimal-in-java-without-rounding
     *
     * @param decimal            The decimal whose decimal places will be removed.
     * @param decimalPlacesCount The count of the decimal places that will be left.
     * @return Returns the new decimal whose decimal places are removed according to the given parameter.
     */
    private static double roundDecimalPlaces(double decimal, int decimalPlacesCount) {
        double trickyNumber = ROUND_DECIMAL_INITIAL_VALUE;
        for (int i = ZERO; i < decimalPlacesCount; i++) {
            trickyNumber = (TRICKY_NUMBER_MULTIPLIER * trickyNumber);
        }
        return (((long) (decimal * trickyNumber)) / trickyNumber);
    }
}
