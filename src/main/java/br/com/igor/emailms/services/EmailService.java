package br.com.igor.emailms.services;

import br.com.igor.emailms.enums.StatusEmail;
import br.com.igor.emailms.models.EmailModel;
import br.com.igor.emailms.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {
    @Autowired
    EmailRepository emailRepository;
    @Autowired
    private JavaMailSender emailSender;

    public EmailModel sendEmail(EmailModel emailModel) {
        emailModel.setSendDateTime(LocalDateTime.now()); /*primeira coisa que devemos fazer é setar a data do email usando o padrao do LodalDateTime do proprio JAVA*/
        try {
            SimpleMailMessage message = new SimpleMailMessage(); /*instanciamos um obj de mensagem de email simples do spring para usarmos no envio*/
            message.setFrom(emailModel.getEmailFrom()); /*todas as funções get do email model foram criadas usando as notações @ */
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            emailSender.send(message);

            emailModel.setStatusEmail(StatusEmail.SENT);
        } catch (MailException e) {
            emailModel.setStatusEmail(StatusEmail.ERROR);
        } finally {
            return emailRepository.save(emailModel);
        }

    }

}
