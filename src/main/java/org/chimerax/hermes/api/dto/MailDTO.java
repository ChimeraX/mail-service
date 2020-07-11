package org.chimerax.hermes.api.dto;

import lombok.Data;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 03-Jun-20
 * Time: 7:59 PM
 */

@Data
public class MailDTO {

    private MailType mailType;

    private String email;

    private String user;
}
