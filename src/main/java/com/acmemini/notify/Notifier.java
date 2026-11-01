package com.acmemini.notify;

/** Sends the booking confirmation mail once; a failed send is stored as FAILED and not tried again. */
public class Notifier {
    public interface Mailer { void send(String to, String text) throws Exception; }

    private final Mailer mailer;

    public Notifier(Mailer mailer) { this.mailer = mailer; }

    public String confirm(String to, String paymentId) {
        try {
            mailer.send(to, "Your payment " + paymentId + " is booked.");
            return "SENT";
        } catch (Exception e) {
            return "FAILED";
        }
    }
}
