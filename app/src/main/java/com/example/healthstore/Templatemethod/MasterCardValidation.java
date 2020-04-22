package com.example.healthstore.Templatemethod;

public class MasterCardValidation extends CardValidator {

    public MasterCardValidation( String cardName, String cardNumber, int expiryDateMonth,
                                int expiryDateYear, String cvv) {

        super( cardName, cardNumber, expiryDateMonth, expiryDateYear, cvv);
    }


    /* Overridden method */

    protected boolean validateCardNumberFormat() {

        /* Check number is in correct format for MasterCard */

        boolean errorInFormat = false;

        if (cardNumber.charAt(0) == '5' && (cardNumber.charAt(1) >= '1' && cardNumber.charAt(1) <= '5')) {

        }
        else {

            //JOptionPane.showMessageDialog(app, "Card format incorrect");
            errorInFormat = true;

        }

        return !errorInFormat;

    }

}