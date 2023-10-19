package io.seoLeir.socialmedia.service;

import io.seoLeir.socialmedia.entity.Roles;
import io.seoLeir.socialmedia.exception.role.RoleNotFoundException;
import io.seoLeir.socialmedia.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RoleService {
    private final RoleRepository roleRepository;

    @Transactional
    public Roles findByName(String name){
        return roleRepository.findByName(name)
                .orElseThrow(() -> new RoleNotFoundException("Role with name: " + name + " not found",
                        HttpStatusCode.valueOf(404)));
    }
}
