package eti.andersongomes.cursomc.services;

import eti.andersongomes.cursomc.domain.Cliente;
import eti.andersongomes.cursomc.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    void sendOrderConfimationEmail(Pedido pedido);
    void sendEmail(SimpleMailMessage message);

    void sendNewPasswordEmail(Cliente cliente, String newPassword);
}
