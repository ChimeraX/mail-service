package org.chimerax.hermes.service;

import lombok.AllArgsConstructor;
import org.chimerax.hermes.entity.Code;
import org.chimerax.hermes.repository.CodeRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 01-Jun-20
 * Time: 2:14 PM
 */

@Service
@AllArgsConstructor
public class CodeService {

    private CodeRepository codeRepository;

    public String save(final String username){

        final Code code = new Code()
                .setCode(generateCode())
                .setUsername(username);

        codeRepository.save(code);

        return code.getCode();
    }

    public String delete(final String username){

        final Code code = new Code()
                .setCode(generateCode())
                .setUsername(username);

        codeRepository.save(code);

        return code.getCode();
    }

    private static String generateCode() {
        return UUID.randomUUID().toString().substring(0, 5);
    }
}
