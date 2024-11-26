package com.isadent.users.infrastructure.services;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;


@Service
public class LoadTemplate {
    private final TemplateEngine templateEngine;

    public LoadTemplate(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String loadStringHtml(String token) throws IOException {


        Context context = new Context();
        context.setVariable("authenticationToken", token);


        return templateEngine.process("Verification", context);
    }
}
