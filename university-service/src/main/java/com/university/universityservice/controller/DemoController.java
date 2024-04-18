package com.university.universityservice.controller;

import com.university.universityservice.dto.DemoDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class DemoController {

    @GetMapping("/demo")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public DemoDto demoEndpoint(Principal principal) {
        return DemoDto.builder()
                .name("Админ")
                .timestamp(LocalDateTime.now())
                .principal(principal)
                .build();
    }

    @GetMapping("/demo_unsecured")
    public DemoDto demoUnsecuredEndpoint(Principal principal) {
        return DemoDto.builder()
                .name("Юзер")
                .timestamp(LocalDateTime.now())
                .principal(principal)
                .build();
    }
}
