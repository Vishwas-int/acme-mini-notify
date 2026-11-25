package com.acmemini.notify;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/** Since 1.2.0 every confirmation is recorded in the notifications collection with its final status and how many
 *  attempts it took (SENT after 1 or 2 attempts, or FAILED after 2). */
public class NotificationStore {
    public record Notification(String paymentId, String status, int attempts, Instant at) {}

    private final List<Notification> notifications = new ArrayList<>();

    public void record(String paymentId, String status, int attempts) {
        notifications.add(new Notification(paymentId, status, attempts, Instant.now()));
    }

    public List<Notification> all() {
        return List.copyOf(notifications);
    }
}
