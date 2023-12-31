package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {
    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");
        Context context = new Context();
        context.setVariable("preview", "where is it?");
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/crud");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("goodbye", "Have a nice day!");
        context.setVariable("show_button", true);
        context.setVariable("application_functionality", functionality);

        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String dailyTaskReviewEmail(String message){
        List<String> additional = new ArrayList<>();
        additional.add("Set priorities to your tasks");
        additional.add("Delegate them if possible");

        Context context = new Context();
        context.setVariable("preview", "where is it?");
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/crud");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("goodbye", "Have a nice day!");
        context.setVariable("show_button", true);
        context.setVariable("additional_info", additional);
        return templateEngine.process("mail/daily-trello-review", context);
    }

}