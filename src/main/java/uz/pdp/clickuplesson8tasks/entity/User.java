package uz.pdp.clickuplesson8tasks.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.clickuplesson8tasks.entity.enums.SystemRoleName;
import uz.pdp.clickuplesson8tasks.entity.template.AbsUUIDEntity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User extends AbsUUIDEntity implements UserDetails {

    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    private String color;

    private String initials;

    @OneToOne(fetch = FetchType.LAZY)
    private Attachment avatar;

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled;

    @Enumerated(EnumType.STRING)
    private SystemRoleName systemRoleName;

    private String emailCode;

    public User(String fullName, String email, String password, SystemRoleName systemRoleName) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.systemRoleName = systemRoleName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(this.systemRoleName.name());
        return Collections.singletonList(simpleGrantedAuthority);
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }
}
