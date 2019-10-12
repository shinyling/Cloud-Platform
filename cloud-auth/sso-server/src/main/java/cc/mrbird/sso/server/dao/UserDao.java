package cc.mrbird.sso.server.dao;

import cc.mrbird.sso.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author DELL shiny
 * @create 2019/5/21
 */
public interface UserDao extends JpaRepository<User,Long> {

    User findByName(String name);
}
