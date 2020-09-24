package com.zuk.service.scheduling;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SchedulerService {

    private static final String CRON = "*/900 * * * * *";



    @Scheduled(cron = CRON)
    public void sendMailToUsers() {
        System.out.println("Hello shedule");
    }

}