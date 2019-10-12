package cc.mrbird.sso.server.service;

import cc.mrbird.sso.server.dao.UserDao;
import cc.mrbird.sso.server.entity.Role;
import cc.mrbird.sso.server.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DELL shiny
 * @create 2019/5/21
 */
@Configuration
public class UserServiceDetail implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByName(username);
        Role role = new Role();
        role.setName("ROLE_USER");
        List<Role> authorities = new ArrayList<>();
        authorities.add(role);
        user.setAuthorities(authorities);

        if(user==null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword()
            ,user.isEnabled(),user.isAccountNonExpired(),user.isCredentialsNonExpired(),user.isAccountNonLocked(),
                user.getAuthorities());
    }
}
