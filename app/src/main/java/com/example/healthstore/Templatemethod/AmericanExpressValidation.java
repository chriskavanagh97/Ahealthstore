package com.example.healthstore.Templatemethod;
public class AmericanExpressValidation extends CardValidator {

    public AmericanExpressValidation( String cardName, String cardNumber, int expiryDateMonth, int expiryDateYear, String cvv) {

        super(cardName, cardNumber, expiryDateMonth, expiryDateYear, cvv);

    }

    /* Overridden methods */

    protected boolean validateCardNumberLength() {

        boolean errorInNumber = false;

        if (cardNumber.length() != 15 ) {
            if(cardNumber.length() != 16) {
                //JOptionPane.showMessageDialog(app, "Card number must be 16 digits long");
                errorInNumber = true;
            }

        } else {

            for (int i = 0; i < cardNumber.length(); i++) {

                if (cardNumber.charAt(i) > '9' || cardNumber.charAt(i) < '0') {

                    //JOptionPane.showMessageDialog(app, "Card number must consist of all digits");
                    errorInNumber = true;

                }

            }

        }

        return !errorInNumber;
    }

    protected boolean validateCardNumberFormat() {

        /* Check number is in correct format for American Express */

        boolean errorInFormat = false;

        if (cardNumber.charAt(0) == '3' && (cardNumber.charAt(1) == '4' || cardNumber.charAt(1) == '7')) {

        } else {

            //JOptionPane.showMessageDialog(app, "Card format incorrect");
            errorInFormat = true;

        }

        return !errorInFormat;

    }

}