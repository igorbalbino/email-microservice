package br.com.igor.emailms.controllers;

import br.com.igor.emailms.dtos.EmailDto;
import br.com.igor.emailms.models.EmailModel;
import br.com.igor.emailms.services.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @Autowired
    EmailService emailService;
    @PostMapping("/sending-email") /*mapeamos a rota por onde nossa api rest receberá os emails*/
    public ResponseEntity<EmailModel> sendingEmail(@RequestBody @Valid EmailDto emailDto) { /*sendingEmail é o metodo que temos para receber o email. Para que as anotações que fizemos com os @ dentro de EmailDto façam efeito, precisamos colocar o @Valid aqui também*/
        EmailModel emailModel = new EmailModel(); /*criando instancia, basico*/
        BeanUtils.copyProperties(emailDto, emailModel); /*passamos os parametros dentro desse metodo que faz a conversão de DTO para MODEL*/
        emailService.sendEmail(emailModel); /*passamos para o service que fará o envio do email de fato*/
        return new ResponseEntity<>(emailModel, HttpStatus.CREATED); /*retornamos para o cliente o model salvo e um status http do tipo created*/
    }
}
