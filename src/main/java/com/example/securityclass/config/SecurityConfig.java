package com.example.securityclass.config;


import com.example.securityclass.auth.JwtAuthenticationTokenFilter;
import com.example.securityclass.auth.LoginFilter;
import com.example.securityclass.exception.AccessDeniedException;
import com.example.securityclass.security.LoginFailureHandler;
import com.example.securityclass.security.LoginSuccessHandler;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizationHttpRequests -> {
            authorizationHttpRequests
                    .requestMatchers("/admin/api/**").hasAnyAuthority("sys:device:delete", "sys:device:list", "sys:device:search")
                    .requestMatchers("/user/api/**").hasAnyAuthority("sys:device:list", "sys:device:delete", "sys:device:search")
                    .requestMatchers("/app/api/**").permitAll()
                    .requestMatchers("/login").permitAll()
                    .anyRequest().authenticated();
        });

        // 没有设置自定义时候的表单登录，好处就是方便配置
        /*http.formLogin(formLogin->{
            formLogin
                    .loginProcessingUrl("/login")
                    .successHandler(new LoginSuccessHandler())
                    .failureHandler(new LoginFailureHandler());
        });*/

        //在登录之前获取token并校验
        http.addFilterBefore(new JwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        // 使用自定义登录过滤器来替换
        http.addFilterAt(loginFilter(), UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling(e -> e.accessDeniedHandler(new AccessDeniedException()));
        http.cors();
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

    // 这里要注入认证配置之后获取认证管理器给自己的filter注册，不然会错误
    @Resource
    private AuthenticationConfiguration authenticationConfiguration;

    // 自己的登录过滤器
    @Bean
    public LoginFilter loginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter();
        // 设置认证管理器
        loginFilter.setAuthenticationManager(authenticationConfiguration.getAuthenticationManager());
        // 设置成功处理
        loginFilter.setAuthenticationSuccessHandler(new LoginSuccessHandler());
        // 设置失败处理
        loginFilter.setAuthenticationFailureHandler(new LoginFailureHandler());
        // 验证码处理
        return loginFilter;
    }




    /*@Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        UserDetails admin = User.withUsername("admin").password("123").authorities("admin:api", "user:api").build();
        UserDetails user = User.withUsername("user").password("123").authorities("user:api").build();
        return new InMemoryUserDetailsManager(admin, user);
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        //明文加密
        return NoOpPasswordEncoder.getInstance();
    }
}
