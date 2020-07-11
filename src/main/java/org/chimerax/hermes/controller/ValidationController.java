package org.chimerax.hermes.controller;

import lombok.AllArgsConstructor;
import org.chimerax.hermes.api.dto.ValidateDTO;
import org.chimerax.hermes.service.VerificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 21-Apr-20
 * Time: 12:03 PM
 */

@RestController
@RequestMapping("/validate")
@AllArgsConstructor
public class ValidationController {

    private VerificationService verificationService;

    @PostMapping
    public ResponseEntity<Void> validate(@RequestBody final ValidateDTO validateDTO) {
        boolean valid = verificationService.existsByUsernameAndCode(validateDTO.getEmail(), validateDTO.getCode());
        if (valid) {
            verificationService.deleteAllByUsername(validateDTO.getEmail());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
