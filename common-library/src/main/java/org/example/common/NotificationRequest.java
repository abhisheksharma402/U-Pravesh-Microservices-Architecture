package org.example.common;

public class NotificationRequest {
    private String recipient;
    private String message;
    private String notificationType;

    // Constructor
    public NotificationRequest(String recipient, String message, String notificationType) {
        this.recipient = recipient;
        this.message = message;
        this.notificationType = notificationType;
    }

    // Default constructor
    public NotificationRequest() {}

    // Getters and Setters
    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    @Override
    public String toString() {
        return "NotificationRequest{" +
                "recipient='" + recipient + '\'' +
                ", message='" + message + '\'' +
                ", notificationType='" + notificationType + '\'' +
                '}';
    }
}
