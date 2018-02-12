package eti.andersongomes.cursomc.services;

import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.slf4j.Logger;

public class MockEmailService extends AbstractEmailService {

    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage message) {
        LOG.info("Simulando emvio de email...");
        LOG.info(message.toString());
        LOG.info("Email enviado.");
    }
}
