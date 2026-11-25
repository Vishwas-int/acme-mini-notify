package com.acmemini.notify;

/** Sends the booking confirmation mail; a failed send is tried once more before it is stored as FAILED
 *  (max 2 attempts). Since 1.2.0 the text comes from MailTemplates and every outcome is recorded in
 *  NotificationStore with its attempt count. */
public class Notifier {
    public static final int MAX_ATTEMPTS = 2;

    public interface Mailer { void send(String to, String text) throws Exception; }

    private final Mailer mailer;
    private final MailTemplates templates = new MailTemplates();
    private final NotificationStore store;

    public Notifier(Mailer mailer, NotificationStore store) {
        this.mailer = mailer;
        this.store = store;
    }

    public String confirm(String to, String iban, String paymentId) {
        String text = templates.bookingConfirmation(iban, paymentId);
        for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++) {
            try {
                mailer.send(to, text);
                store.record(paymentId, "SENT", attempt);
                return "SENT";
            } catch (Exception e) {
                // tried again below until MAX_ATTEMPTS
            }
        }
        store.record(paymentId, "FAILED", MAX_ATTEMPTS);
        return "FAILED";
    }
}
