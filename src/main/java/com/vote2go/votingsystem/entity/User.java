package com.vote2go.votingsystem.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

@ToString
@Getter
@Setter
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "users_unique_email_idx")})
public class User extends NamedEntity{

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @SafeHtml
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Length(min = 5)
    @SafeHtml
    private String password;

    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    private boolean enabled = true;

    @Column(name = "registered", columnDefinition = "timestamp default now()")
    private Date registered = new Date();

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    private Set<Role> roles;

    public User(User u) {
        this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.isEnabled(), u.getRoles());
    }

    public User(Integer id, String name, String email, String password, Role role, Role... roles) {
        this(id, name, email, password, true, EnumSet.of(role, roles));
    }

    public User(Integer id, String name, String email, String password, boolean enabled, Set<Role> roles) {
        super(id, name);
        this.email = email;
        this.password = password;

        this.enabled = enabled;
        setRoles(roles);
    }
}
