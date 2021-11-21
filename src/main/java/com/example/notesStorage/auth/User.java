package com.example.notesStorage.auth;

import com.example.notesStorage.BaseEntity;
import com.example.notesStorage.Note.Note;
import com.example.notesStorage.enums.Role;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString(exclude = "notes")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements BaseEntity<UUID>, UserDetails {

    private static final long serialVersionUID = 6445768438123274615L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @NotNull
    @Column()
    @UniqueElements
    @Size(min = 5, max = 50, message = "should be more than 5 and not more than 50")
    private String username;

    @Valid
    @NotNull
    @Size(min = 8, max = 100, message = "should be more than 8 and not more than 100")
    private String password;

    private boolean active;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Note> notes;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public boolean isAdmin(){
        return roles.contains(Role.ADMIN);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
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
        return isActive();
    }
}
