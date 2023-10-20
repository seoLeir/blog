package io.seoLeir.socialmedia.config;

import io.seoLeir.socialmedia.entity.Roles;
import io.seoLeir.socialmedia.entity.User;
import io.seoLeir.socialmedia.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class AdminInitializer implements ApplicationListener<ContextRefreshedEvent> {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        /*userService.findByUsername("admin")
                .ifPresentOrElse(user -> {
                }, () -> {
                    User admin = new User(UUID.randomUUID(),
                            "admin",
                            "mirzayevmiralim28@gmail.com",
                            passwordEncoder.encode("admin"),
                            "Website Moderator");
                    userService.save(admin);
                    log.info("Save admin in database");
                });*/
    }
}
