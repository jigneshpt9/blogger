package com.cisco.blogger.config;

import org.glassfish.jersey.server.ResourceConfig;

import com.cisco.blogger.service.JwtTokenNeededFilter;

public class BloggerApplicationConfig extends ResourceConfig {
	
	public BloggerApplicationConfig() {
        packages( "com.cisco.blogger.service" );
		register(JwtTokenNeededFilter.class );
	}
}