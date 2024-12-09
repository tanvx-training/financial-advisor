package dev.tanvx.notification_service.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/notifications")
public class NotificationController {

    @GetMapping
    public String getNotification() {
        return "Notification";
    }
}
