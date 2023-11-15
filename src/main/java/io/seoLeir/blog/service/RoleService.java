package io.seoLeir.blog.service;

import io.seoLeir.blog.entity.Roles;
import io.seoLeir.blog.exception.role.RoleNotFoundException;
import io.seoLeir.blog.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RoleService {
    private final RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public Roles findByName(String name){
        return roleRepository.findByName(name)
                .orElseThrow(() -> new RoleNotFoundException("Role with name: " + name + " not found",
                        HttpStatusCode.valueOf(404)));
    }
}
