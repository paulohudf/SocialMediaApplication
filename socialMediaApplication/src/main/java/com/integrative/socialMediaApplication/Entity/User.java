package com.integrative.socialMediaApplication.Entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
    private String bio;
    private String profilePictureUrl;
    private String interests;

    @Enumerated(EnumType.STRING)
    private PrivacySetting privacySetting;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Post> posts;

    @ManyToMany
    @JoinTable(
        name = "user_connections",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "connection_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<User> connections;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Return user roles/authorities (e.g., ROLE_USER, ROLE_ADMIN)
        return Set.of(() -> "ROLE_USER"); // Example: Default role
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