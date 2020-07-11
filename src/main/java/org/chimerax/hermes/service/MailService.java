package org.chimerax.hermes.service;

import lombok.AllArgsConstructor;
import org.chimerax.hermes.entity.Code;
import org.chimerax.hermes.repository.CodeRepository;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 20-May-20
 * Time: 4:58 PM
 */

@Service
@AllArgsConstructor
public class MailService {

    private JavaMailSender mailSender;
    private CodeRepository codeRepository;

    public void sendActivationEmail(final String email, final String user) {
        final Map<String, String> parameters = new HashMap<>();
        parameters.put("user", user);
        parameters.put("code", createCode(email));
        final String text = replaceParameters(activationEmailText(), parameters);
        try {
            sendHTMLMessage(email, "Activate your Chimera-X account", text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendAuthenticationEmail(final String email, final String user) {
        final Map<String, String> parameters = new HashMap<>();
        parameters.put("user", user);
        parameters.put("code", createCode(email));
        final String text = replaceParameters(authenticationEmailText(), parameters);
        try {
            sendHTMLMessage(email, "2 steps Authentication Chimera-X account", text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String createCode(final String username) {
        final String code = generateCode();
        codeRepository.save(new Code()
                .setUsername(username)
                .setCode(code));
        return code;
    }

    private static String generateCode() {
        return UUID.randomUUID().toString().substring(0, 5);
    }

    private String activationEmailText() {
        try {
            return readFile("src/main/resources/templates/Registration.html");
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private String authenticationEmailText() {
        try {
            return readFile("src/main/resources/templates/2-steps-verification.html");
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private String readFile(final String filename) throws IOException {
        StringBuilder HTML = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine();
        while (line != null) {
            HTML.append(line);
            line = reader.readLine();
        }
        return HTML.toString();
    }

    private void sendHTMLMessage(final String to, final String subject, final String text) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);
        mailSender.send(message);
    }

    private String replaceParameters(String text, final Map<String, String> parameters) {
        for (final String parameter : parameters.keySet()) {
            text = text.replace("{{" + parameter + "}}", parameters.get(parameter));
        }
        return text;
    }
}
