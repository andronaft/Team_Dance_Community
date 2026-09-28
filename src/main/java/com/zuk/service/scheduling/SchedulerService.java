package com.zuk.service.scheduling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SchedulerService {

    // Every 15 minutes. The old expression put the 900 step into the seconds field, so it fired once a minute.
    private static final String CRON = "0 */15 * * * *";

    @Scheduled(cron = CRON)
    public void sendMailToUsers() {
        // TODO: send reminders about upcoming trainings
        log.debug("Scheduled mail job triggered");
    }
}
