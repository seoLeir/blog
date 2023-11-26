package io.seoLeir.blog.entity.keys;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserRoleId implements Serializable {

    @Serial
    private static final long serialVersionUID = 79198150545448L;

    @Column(name = "user_uuid")
    private UUID userUuid;


    @Column(name = "role_id")
    private Short roleId;

    @Override
    public String toString() {
        return "UserRoleId{" +
                "userUuid=" + userUuid +
                ", roleId=" + roleId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleId that = (UserRoleId) o;
        return Objects.equals(userUuid, that.userUuid) && Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userUuid, roleId);
    }
}
