package me.whiteship.demospringsecurityform.config;

import me.whiteship.demospringsecurityform.account.AccountService;
import me.whiteship.demospringsecurityform.comon.CustomAccessDeniedHandler;
import me.whiteship.demospringsecurityform.comon.LoggingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;


import java.util.Arrays;
import java.util.List;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AccountService accountService;

    public AccessDecisionManager accessDecisionManager() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROEL_USER");

        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setRoleHierarchy(roleHierarchy);

        WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
        webExpressionVoter.setExpressionHandler(handler);

        List<AccessDecisionVoter<? extends Object>> voters = Arrays.asList(webExpressionVoter);

        return new AffirmativeBased(voters);
        //AccessDecisionManager accessDecisionManager = new AffirmativeBased()
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //web.ignoring().mvcMatchers("/favicon.ico");
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(new LoggingFilter(), WebAsyncManagerIntegrationFilter.class);

        http.authorizeRequests()
                .mvcMatchers("/", "/info", "/account/**", "/signup").permitAll() // 전체 허용
                .mvcMatchers("/admin").hasRole("ADMIN")
                .mvcMatchers("/user").hasRole("USER")
                .anyRequest().authenticated() // 인증이 되어야지 접근이 허용한다.
                .accessDecisionManager(accessDecisionManager());

              http.formLogin()
                      .loginPage("/login")
                      .permitAll()
                       ;
                      //.usernameParameter("my-username")
                      //.passwordParameter("my-password");

              // 쿠리를 사용한 세션Id 인증 방식
              http.rememberMe()
                    //  .tokenValiditySeconds() // 쿠키 유지시간
                    //  .alwaysRemember(false) // 기본값은 false, true 화면에서 리맴버 값을 안받아도 기번적으로 쿠리를 사용하겠다.
                      .userDetailsService(accountService)
                      .key("remember-me")
                      ;



              http.httpBasic();

              http.logout()
                   //   .logoutUrl("/mylogout")
                      .logoutSuccessUrl("/");

              /* 세션을 한 브라우저만 적속할수 있다.

              http.sessionManagement()
                      .sessionFixation()
                        .changeSessionId()
                      .maximumSessions(1)
                        .maxSessionsPreventsLogin(true);

               */

              // TODO ExceptionTranslatorFilter -> FilterSecurityInterceptor (AccessDecisionManager, AffirmativeBased)
              // TODO AutenticationExcetion -> AuthenticationEntryPoint
              // TODO AccessDeniedException -> AccessDeniedHander

            /*
            http.exceptionHandling()
                    .accessDeniedPage("/access-denied");
            */


            http.exceptionHandling().accessDeniedHandler(accessDeniedHandler());
            /*
            http.exceptionHandling()
                    .accessDeniedHandler(new AccessDeniedHandler() {
                        @Override
                        public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
                            UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                            String username = principal.getUsername();
                            System.out.println(username+" is denied to access "+ httpServletRequest.getRequestURI());
                            httpServletResponse.sendRedirect("/access-denied");
                        }
                    });
             */

        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }
    /*
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("keesun").password("{noop}123").roles("USER").and()
                .withUser("admin").password("{noop}!@#").roles("ADMIN");
    }
    */


    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        CustomAccessDeniedHandler customAccessDeniedHandler = new CustomAccessDeniedHandler();
        return customAccessDeniedHandler;
    }


}
