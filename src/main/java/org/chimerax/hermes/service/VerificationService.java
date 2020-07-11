package org.chimerax.hermes.service;

import lombok.AllArgsConstructor;
import org.chimerax.hermes.repository.CodeRepository;
import org.springframework.stereotype.Service;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 02-Jun-20
 * Time: 8:35 PM
 */

@Service
@AllArgsConstructor
public class VerificationService {

    private CodeRepository codeRepository;

    public boolean existsByUsernameAndCode(String username, String code) {
        return codeRepository.existsByUsernameAndCode(username, code);
    }

    public void deleteAllByUsername(String username) {
        codeRepository.deleteAllByUsername(username);
    }
}
