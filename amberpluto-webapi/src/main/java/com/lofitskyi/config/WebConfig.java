package com.lofitskyi.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by eduard on 3/5/16.
 */

@Configuration
@EnableWebMvc
@ComponentScan("com.lofitskyi")
public class WebConfig {
}
