package io.seoLeir.blog.service;

import io.seoLeir.blog.repository.UserRoleRepository;
import io.seoLeir.blog.entity.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserRoleService {
    private final UserRoleRepository userRoleRepository;

    @Transactional
    public void save(UserRole userRole){
        userRoleRepository.save(userRole);
    }

    @Transactional
    public void update(Short newRoleId, UUID userUuid){
        userRoleRepository.updateUserRole(newRoleId, userUuid);
    }

    @Transactional(readOnly = true)
    public List<String> getUserAuthorities(UUID userUuid){
        return userRoleRepository.getUserRoleById(userUuid);
    }

}
