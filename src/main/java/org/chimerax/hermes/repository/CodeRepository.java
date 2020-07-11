package org.chimerax.hermes.repository;


import org.chimerax.hermes.entity.Code;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 01-Jun-20
 * Time: 2:12 PM
 */
public interface CodeRepository extends JpaRepository<Code, Long> {

    void deleteAllByUsername(final String username);

    boolean existsByUsernameAndCodeAndValidLessThan(final String username, final String code, final long valid);

    default boolean existsByUsernameAndCode(final String username, final String code) {
        final long now = new Date().getTime();
        return existsByUsernameAndCodeAndValidLessThan(username, code, now);
    }

    void deleteAllByValidGreaterThan(final long valid);

}
