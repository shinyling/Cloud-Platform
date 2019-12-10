package com.everwing.cloud.auth.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.everwing.cloud.common.vo.UserVo;

import lombok.Data;

/**
 * @author DELL shiny
 * @create 2019/5/21
 */
@Data
@Entity
public class User implements UserDetails, Serializable {

    private static final String serialVersionUID="6c0d00ea-f741-4501-b248-dd2a0459a620";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(nullable = false,  unique = true, name = "mobile")
    private String username;

    @Column
    private String password;

    @Column(name="company_id")
    private String companyId;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    public UserVo convertToUserVo(){
        UserVo userVo=new UserVo();
        BeanUtils.copyProperties(this,userVo);
        return userVo;
    }
}
