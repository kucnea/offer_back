package com.phy.Configure.SpringSecurity;

import ch.vorburger.exec.ManagedProcessException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

//@EnableWebSecurity
@Configuration
@PropertySource("classpath:application.properties")
public class SpringSecurity {

    @Value("${spring.datasource.username}") private String datasourceUsername;
    @Value("${spring.datasource.password}") private String datasourcePassword;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/**", "/index").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .permitAll()
                )
                .csrf(Customizer.withDefaults());
//                .csrf((csrf) -> csrf
//                .csrfTokenRepository(new HttpSessionCsrfTokenRepository()));
//                .rememberMe(Customizer.withDefaults());
                // HttpSessionCsrfTokenRepository 이것도 한번 볼 것.

        return http.build();
    }

    @Bean
    public DataSource dataSource() throws ManagedProcessException {

        return DataSourceBuilder.create()
                .url("jdbc:mariadb://localhost:3306/phy?useUnicode=yes&characterEncoding=UTF-8&serverTimezone=Asia/Seoul")
                .username(datasourceUsername)
                .password(datasourcePassword)
                .build();

    }

    // 순환참조로 분리
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }

//    @Bean
//    public UserDetailsManager users(DataSource dataSource) {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("password")
//                .roles("USER")
//                .build();
//        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//        users.createUser(user);
//        return users;
//    }

}
