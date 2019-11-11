package com.example.paymeback;

/**
 * Enum Class of the currencies that can be used in the transaction
 * @author Anna
 */

public enum Currency {
    Euro, SGD, USD, Pound, TRY;

    /**
     * Checking if the Currency input is in the currency's enum accepted by the app
     * @param currency
     * @return
     */
    public static Currency contains(String currency) {
        for (Currency c : Currency.values()) {
            if (c.name().equals(currency)) {
                return c;
            }
        }
        return null;
    }
}
