package cn.redandelion.seeha.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSafeConfig extends WebSecurityConfigurerAdapter {
//    配置spring-security拦截
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
             .authorizeRequests().anyRequest().permitAll()
             .and()
             .headers().frameOptions().disable()
             .and()
             .csrf().disable()
        ;

    }
}
