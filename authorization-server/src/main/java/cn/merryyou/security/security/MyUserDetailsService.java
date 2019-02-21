package cn.merryyou.security.security;

import cn.merryyou.security.domain.UserDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 用户登录账号信息服务
 *
 * Created on 2018/1/17.
 *
 * @author zlf
 * @since 1.0
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User(username, passwordEncoder.encode("123456"), AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
    }


//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        /**
//         * 模拟用户，替代数据库获取用户信息
//         */
//        UserDomain user = new UserDomain();
//        user.setUserName(username);
//        user.setPassword(this.passwordEncoder.encode("123456"));
//        // 输出加密后的密码
//        System.out.println(user.getPassword());
//
//        return new User(username, user.getPassword(), user.isEnabled(),
//                user.isAccountNonExpired(), user.isCredentialsNonExpired(),
//                user.isAccountNonLocked(), AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
//    }
}
