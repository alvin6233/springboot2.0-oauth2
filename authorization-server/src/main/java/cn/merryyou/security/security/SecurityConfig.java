package cn.merryyou.security.security;//package cn.merryyou.security.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created on 2018/1/19.
 *
 * @author zlf
 * @since 1.0
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        AuthenticationManager manager = super.authenticationManagerBean();
        return manager;
    }

    /**
     * 用于密码加密，BCryptPasswordEncoder对相同的密码生成的结果每次都是不一样的
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
//                .httpBasic().and()  // HTTP Basic
                .loginPage("/login.html") //登录页面的请求URL
                .loginProcessingUrl("/login") //登录页面form表单的action路径
                .and()
                .authorizeRequests() //授权配置
                .antMatchers("/login.html", "/login").permitAll() //登录页面的相关请求不被拦截
                .anyRequest()  // 所有其它请求
                .authenticated() // 都需要认证
                .and()
                .csrf().disable(); //关闭CSRF跨域攻击防御
    }
}
