# mini-notify 1.2.0
Sends the booking confirmation mail for each booked ACME Mini payment:
- a failed send is tried once more before it is stored as FAILED (max 2 attempts, since 1.1.0);
- since 1.2.0 the mail is in German for DE IBANs, English otherwise (`MailTemplates`);
- since 1.2.0 every outcome is recorded with its attempt count (`NotificationStore`).
Made-up test data for an MSOCK refresh demo.
