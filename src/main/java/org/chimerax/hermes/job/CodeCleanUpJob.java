package org.chimerax.hermes.job;

import lombok.AllArgsConstructor;
import org.chimerax.hermes.repository.CodeRepository;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 24-Apr-20
 * Time: 9:44 PM
 */
@Component
@AllArgsConstructor
public class CodeCleanUpJob implements Job {

    private CodeRepository codeRepository;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        final long now = new Date().getTime();
        codeRepository.deleteAllByValidGreaterThan(now);
    }
}
