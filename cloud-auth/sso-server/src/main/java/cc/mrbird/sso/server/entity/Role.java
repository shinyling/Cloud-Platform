package cc.mrbird.sso.server.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * @author DELL shiny
 * @create 2019/5/21
 */
@Data
@Entity
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
