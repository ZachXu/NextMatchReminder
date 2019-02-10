package de.zachxu.nextmatchreminder.webservice.json;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/")
public class NMRJsonApplication extends ResourceConfig {

	/**
	 * 
	 */
	public NMRJsonApplication()
	{
		super();
		register(NMRJsonResource.class);
	}

}
