package com.cts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.repository.NotificationRepository;
@Service
public class NotificationService {
	@Autowired
    private NotificationRepository notificationRepository;

    public String sendEmail(String recipientEmail, String subject, String content) {
    	return notificationRepository.sendEmail(recipientEmail, subject, content);
    }
}
