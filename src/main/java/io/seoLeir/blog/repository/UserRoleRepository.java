package io.seoLeir.blog.repository;

import io.seoLeir.blog.entity.UserRole;
import io.seoLeir.blog.entity.keys.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {

    @Modifying(flushAutomatically = true)
    @Query("update UserRole ur set ur.id.roleId = :roleId where ur.id.userUuid = :userUuid")
    void updateUserRole(@Param("roleId") Short roleId, @Param("userUuid") UUID userUuid);

    @Query("select r.name from Roles r where r.id in " +
            "(select u.id.roleId from UserRole u where u.id.userUuid = :id)")
    List<String> getUserRoleById(@Param("id") UUID userUuid);
}
