package team.weacsoft.system.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import team.weacsoft.system.security.handler.JwtAccessDeniedHandler;
import team.weacsoft.system.security.handler.JwtAuthenticationEntryPoint;

/**
 * @author GreenHatHG
 */

@EnableWebSecurity
//在这里，prePostEnabled = true启用对方法级安全性的支持，并允许使用@PreAuthorize
//类似地启用@Secured注释，我们需要secureEnabled = true。
@EnableGlobalMethodSecurity(prePostEnabled = true)
// @ EnableGlobalMethodSecurity(securedEnabled = true)
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/**/notification/**").permitAll()
                .antMatchers("/**/public/**").permitAll()
                .antMatchers("/**/druid/**").permitAll()
                .anyRequest().authenticated().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .exceptionHandling().authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                .accessDeniedHandler(new JwtAccessDeniedHandler());
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
