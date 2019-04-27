package org.learn.resource.config;

import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.learn.resource.voter.ActionRoleVoter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@EnableResourceServer
@Configuration
@PropertySource({ "classpath:persistence.properties" })
//@Profile("mvc")
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Autowired
    private Environment env;
    
	 @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId("my_rest_api").stateless(false);
        resources.tokenServices(tokenServices());
    }
	
	@Override
    public void configure(HttpSecurity http) throws Exception {

    	http.
        anonymous().disable()
        .exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler())
        .and()
        .authorizeRequests().anyRequest().authenticated()
        .accessDecisionManager(accessDecisionManager());
        //.requestMatchers().antMatchers("/employee/**")
        //.and().authorizeRequests()
        //.antMatchers("/employee/fetch/**/").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
        //.antMatchers("/employee/create").access("hasRole('ROLE_ADMIN')")
        /*.and()*///.exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }
	
	@Bean
	public AccessDecisionManager accessDecisionManager() {
	    List<AccessDecisionVoter<? extends Object>> decisionVoters 
	      = Arrays.asList(
	    		  voter()
	        );
	    return new AffirmativeBased(decisionVoters);
	}
	
	@Bean
	public AccessDecisionVoter<Object> voter() {
		return new ActionRoleVoter();
	}
	
	@Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        return defaultTokenServices;
    }

    // JDBC token store configuration

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.user"));
        dataSource.setPassword(env.getProperty("jdbc.pass"));
        return dataSource;
    }
    
    @Bean
    public JdbcTemplate jdbcTemplate() {
    	return new JdbcTemplate(dataSource());
    }

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource());
    }

}
