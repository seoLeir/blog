package io.seoLeir.socialmedia.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User implements BaseEntity<UUID>, UserDetails {

    @Id
    private UUID id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Roles role;

    @Column(name = "info")
    private String info;

    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "modified_at")
    private Instant modifiedAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<File> files;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<PublicationComment> userPublicationComments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<PublicationLike> publicationLikes;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Message> userMessages;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Publication> userPublications;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Subscription> subscriptions;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "user")
    private List<UserBookmark> userBookmarks;

    public User(UUID userUuid, String username, String email, String password, Roles userRole) {
        this.id = userUuid;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, password);
    }

    public User(UUID id, String username, String email, String password, Roles role, String info, Instant createdAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.info = info;
        this.createdAt = createdAt;
    }

    public User(UUID id, String username, String email, String password, Roles role, String info) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.info = info;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(this.role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
