package org.learn.resource.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan({ "org.learn.resource.controller",
	"org.learn.resource.service",
	"org.learn.resource.repository"})
public class ResourceServerWebConfig extends WebMvcConfigurerAdapter {
    //
}
