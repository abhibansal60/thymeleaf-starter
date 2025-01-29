package com.abhi.thymeleaf.starter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Configuration
@Profile("local")
public class LocalDevConfig {

    public LocalDevConfig(final TemplateEngine templateEngine) throws IOException {
        final ClassPathResource applicationYml = new ClassPathResource("application.properties");
        if (applicationYml.isFile()) {
            File sourceRoot = applicationYml.getFile().getParentFile();
            while (Objects.requireNonNull(sourceRoot.listFiles((dir, name) -> name.equals("gradlew"))).length != 1) {
                sourceRoot = sourceRoot.getParentFile();
            }
            final FileTemplateResolver fileTemplateResolver = new FileTemplateResolver();
            fileTemplateResolver.setPrefix(sourceRoot.getPath() + "/src/main/resources/templates/");
            fileTemplateResolver.setSuffix(".html");
            fileTemplateResolver.setCacheable(false);
            fileTemplateResolver.setCharacterEncoding("UTF-8");
            fileTemplateResolver.setCheckExistence(true);
            templateEngine.setTemplateResolver(fileTemplateResolver);
        }
    }

}
