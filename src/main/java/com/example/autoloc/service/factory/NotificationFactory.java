package com.example.autoloc.service.factory;

import com.example.autoloc.service.observer.EmailNotificationObserver;
import com.example.autoloc.service.observer.IReservationObserver;
import com.example.autoloc.service.observer.SmsNotificationObserver;
import org.springframework.stereotype.Component;

@Component
public class NotificationFactory {

    private final EmailNotificationObserver emailObserver;
    private final SmsNotificationObserver smsObserver;

    public NotificationFactory(EmailNotificationObserver emailObserver,
                                SmsNotificationObserver smsObserver) {
        this.emailObserver = emailObserver;
        this.smsObserver = smsObserver;
    }

    // Returns the observer matching the given canal (EMAIL or SMS)
    public IReservationObserver creerObservateur(String canal) {
        return switch (canal.toUpperCase()) {
            case "EMAIL" -> emailObserver;
            case "SMS"   -> smsObserver;
            default -> throw new IllegalArgumentException("Canal inconnu : " + canal);
        };
    }
}
