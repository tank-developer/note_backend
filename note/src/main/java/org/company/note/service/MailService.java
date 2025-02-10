package org.company.note.service;

import jakarta.mail.MessagingException;

public interface MailService {
    void send(String subject, String email, String code);
}
