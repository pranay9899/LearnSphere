package com.learnsphere.app.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

@Configuration
public class SecurityFirewallConfig {

    @Bean
    public HttpFirewall permissiveHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        // enable only what you need — enabling linefeed is the specific change for %0A
        firewall.setAllowUrlEncodedLineFeed(true);        // allows %0A
        firewall.setAllowUrlEncodedCarriageReturn(true);  // allows %0D (if required)
        // consider other setters only if necessary
        return firewall;
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(HttpFirewall firewall) {
        return (web) -> web.httpFirewall(firewall);
    }
}
