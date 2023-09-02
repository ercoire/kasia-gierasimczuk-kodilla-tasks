package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailScheduler {

    private final SimpleEmailService simpleEmailService;
    private final TaskRepository taskRepository;
    private final AdminConfig adminConfig;

    private static final String SUBJECT = "Tasks: Daily review";

   @Scheduled(cron = "0 10 10 * * *")
  // @Scheduled(fixedDelay = 10000)
    public void sendInformationEmail() {
        long size = taskRepository.count();
        String message;
        if (size > 1) {
            message = "Currently in database you've got: " + size + " tasks";
        } else {
            message = "Currently in database you've got 1 task";
        }
        simpleEmailService.send(
                new Mail(
                        adminConfig.getAdminMail(),
                        SUBJECT,
                        message,
                        null
                )
        );

    }
}
