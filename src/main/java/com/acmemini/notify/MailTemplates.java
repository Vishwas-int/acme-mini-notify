package com.acmemini.notify;

/** Since 1.2.0 the confirmation mail is in German for DE IBANs and in English for every other country. */
public class MailTemplates {
    public String bookingConfirmation(String iban, String paymentId) {
        boolean german = iban != null && iban.startsWith("DE");
        return german
            ? "Ihre Zahlung " + paymentId + " wurde gebucht."
            : "Your payment " + paymentId + " is booked.";
    }
}
