package com.example.healthstore.Templatemethod;

public class VisaValidation extends CardValidator {

    public VisaValidation( String cardName, String cardNumber, int expiryDateMonth,
                          int expiryDateYear, String cvv) {

        super( cardName, cardNumber, expiryDateMonth, expiryDateYear, cvv);

    }

    /* Overridden method */

    protected boolean validateCardNumberFormat() {

        /* Check number is in correct format for Visa */

        boolean errorInFormat = false;

        if (cardNumber.charAt(0) != '4') {

            //JOptionPane.showMessageDialog(app, "Card format incorrect");
            errorInFormat = true;

        }
        else {


        }

        return !errorInFormat;

    }

}