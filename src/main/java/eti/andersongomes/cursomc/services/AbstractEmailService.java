package eti.andersongomes.cursomc.services;

import eti.andersongomes.cursomc.domain.Cliente;
import eti.andersongomes.cursomc.domain.Pedido;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Override
    public void sendOrderConfimationEmail(Pedido pedido) {
        SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(pedido);
        sendEmail(sm);
    }

    @Override
    public void sendEmail(SimpleMailMessage message) {

    }


    protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido pedido) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(pedido.getCliente().getEmail());
        sm.setFrom(this.sender);
        sm.setSubject("Pedido confimado! Código: " + pedido.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(pedido.toString());
        return sm;
    }

    @Override
    public void sendNewPasswordEmail(Cliente cliente, String newPassword) {
        SimpleMailMessage sm = prepareNewPassword(cliente, newPassword);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareNewPassword(Cliente cliente, String newPassword) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(cliente.getEmail());
        sm.setFrom(this.sender);
        sm.setSubject("Solicitação de nova senha");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText("Nova senha: "+newPassword);
        return sm;
    }
}
