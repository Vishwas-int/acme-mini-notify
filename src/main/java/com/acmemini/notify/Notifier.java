package com.acmemini.notify;

/** Sends the booking confirmation mail; a failed send is tried once more before it is stored as FAILED (max 2 attempts). */
public class Notifier {
    public static final int MAX_ATTEMPTS = 2;

    public interface Mailer { void send(String to, String text) throws Exception; }

    private final Mailer mailer;

    public Notifier(Mailer mailer) { this.mailer = mailer; }

    public String confirm(String to, String paymentId) {
        for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++) {
            try {
                mailer.send(to, "Your payment " + paymentId + " is booked.");
                return "SENT";
            } catch (Exception e) {
                // tried again below until MAX_ATTEMPTS
            }
        }
        return "FAILED";
    }
}
