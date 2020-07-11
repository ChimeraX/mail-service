package org.chimerax.hermes.configuration;

import org.chimerax.hermes.job.CodeCleanUpJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 24-Apr-20
 * Time: 9:44 PM
 */

@Configuration
public class QuartzConfiguration {

    @Bean("code-clean-up-job-detail")
    public JobDetail jobDetail() {
        return JobBuilder.newJob()
                .ofType(CodeCleanUpJob.class)
                .storeDurably()
                .withIdentity("code-deletion-job")
                .withDescription("Should delete older verification codes")
                .build();
    }

    @Bean("code-clean-up-job-trigger")
    @DependsOn("code-clean-up-job-detail")
    public Trigger trigger(@Qualifier("code-clean-up-job-detail") final JobDetail jobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity("authorization-code-clean-up-job-trigger")
                .withDescription("Trigger for the email codes clean up job")
                .withSchedule(SimpleScheduleBuilder.repeatMinutelyForever(5))
                // .startNow()
                .build();
    }
}
