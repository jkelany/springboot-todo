package com.jkelany.todo.api.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableMongoAuditing(auditorAwareRef = "appAuditorAware")
public class AppConfig implements WebMvcConfigurer {

    @Bean
    AuditorAware<String> appAuditorAware() {
        return new AppAuditorAware();
    }

    @Bean
    MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:static/i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean localeResolver = new LocalValidatorFactoryBean();
        localeResolver.setValidationMessageSource(messageSource());
        return localeResolver;
    }

    @Bean
    public AmazonS3 amazonS3(AppProperties appProperties) {
        AWSCredentials credentials = new BasicAWSCredentials(appProperties.getFileUploads().getS3().getAccessKey(),
                appProperties.getFileUploads().getS3().getSecretKey());
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.fromName(appProperties.getFileUploads().getS3().getRegion()))
                .build();
    }
}
