package io.seoLeir.blog.entity;


import io.seoLeir.blog.entity.keys.UserRoleId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users_roles")
@Entity
public class UserRole {

    @EmbeddedId
    private UserRoleId id;

    @MapsId("userUuid")
    @JoinColumn(name = "user_uuid", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @MapsId("roleId")
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Roles role;

    public UserRole(User user, Roles role) {
        this.id = new UserRoleId(user.getId(), role.getId());
        this.user = user;
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                '}';
    }
}
