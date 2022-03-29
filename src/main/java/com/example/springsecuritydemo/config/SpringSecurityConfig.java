package com.example.springsecuritydemo.config;

import com.example.springsecuritydemo.service.UserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    private final UserDetailsService userDetailsService;

    private final BCryptPasswordEncoder encoder;

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .passwordEncoder(encoder)
//                .withUser("user")
//                .password("$2a$10$0C3XNq8tuxvevO8jQyPvcuTF7Vdc6fx4CAc1YaecL.Kd2QiUP//FO") //user
//                .roles("user", "admin");
//    }

    //    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()
//                .passwordEncoder(encoder)
//                .dataSource(dataSource)
//                .usersByUsernameQuery("select u.username, u.password, true from users u where u.username=?")
//                .authoritiesByUsernameQuery("select u.username, r.name from roles r \n" +
//                        "join user_role ur on r.id = ur.roleid \n" +
//                        "join users u on ur.userid = u.id where u.username = ?");
//    }
//

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/register").anonymous()
                .antMatchers("/notSecure").permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user", "/user/**").hasRole("USER")
                .antMatchers("/logout").authenticated()
                .anyRequest().authenticated()
                .and().httpBasic();
//                and()
//                .formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/authorize")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .defaultSuccessUrl("/")
//                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/");
    }
}
