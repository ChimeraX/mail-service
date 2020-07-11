package org.chimerax.hermes.controller;

import lombok.AllArgsConstructor;
import org.chimerax.hermes.api.dto.MailDTO;
import org.chimerax.hermes.service.MailService;
import org.springframework.web.bind.annotation.*;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 21-Apr-20
 * Time: 12:03 PM
 */

@RestController
@RequestMapping("/mail")
@AllArgsConstructor
public class MailController {

    private MailService mailService;

    @PostMapping
    public void sendEmail(@RequestBody final MailDTO mailDTO) {
        switch (mailDTO.getMailType()){
            case REGISTRATION:
                mailService.sendActivationEmail(mailDTO.getEmail(), mailDTO.getUser());
                break;
            case AUTHENTICATION:
                mailService.sendAuthenticationEmail(mailDTO.getEmail(), mailDTO.getUser());
                break;
            default:
                break;
        }
    }

}
