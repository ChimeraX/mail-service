package org.chimerax.hermes.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 01-Jun-20
 * Time: 2:07 PM
 */

@Data
@Entity
@Table(name = "codes", schema = "users")
@Accessors(chain = true)
public class Code {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    private String code;

    private long valid;

    @PrePersist
    void prePersist() {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, 15);
        valid = calendar.getTime().getTime();
    }
}
