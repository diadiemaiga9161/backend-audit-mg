package com.Projet.Projet.security.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ResourceLoader resourceLoader; // Injectez le ResourceLoader

    public void sendResetPasswordEmail(String to, String token) {
        String subject = "Réinitialisation de mot de passe";
        // Chargez le contenu du modèle HTML de réinitialisation de mot de passe depuis le répertoire de ressources
        String templateName = "registration_template.html";
        String htmlContent = loadHtmlTemplate(templateName);
        MimeMessage message = javaMailSender.createMimeMessage();
        htmlContent = htmlContent.replace("{{TOKEN}}", token);
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true); // Le dernier argument indique que le contenu est au format HTML.
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            // Gérez les exceptions liées à l'envoi de courriels
        }
    }

    public void sendRegistrationEmail(String to, String token) {
        String subject = "Inscription réussie";

        // Chargez le contenu du modèle HTML d'inscription depuis le répertoire de ressources
        String templateName = "newEmailTemplate.html";
        String htmlContent = loadHtmlTemplate(templateName);
        MimeMessage message = javaMailSender.createMimeMessage();
        htmlContent = htmlContent.replace("{{TOKEN}}", token);

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true); // Le dernier argument indique que le contenu est au format HTML.

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            // Gérez les exceptions liées à l'envoi de courriels
        }
    }

    private String loadHtmlTemplate(String templateName) {
        try {
            Resource resource = resourceLoader.getResource("classpath:/templates/" + templateName);
            InputStream inputStream = resource.getInputStream();
            return StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            // Gérez les exceptions liées au chargement du modèle HTML
            return ""; // Ou une valeur par défaut
        }
    }
}
