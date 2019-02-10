package de.zachxu.nextmatchreminder.webservice;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.glassfish.jersey.jetty.JettyHttpContainer;
import org.glassfish.jersey.server.ContainerFactory;

import de.zachxu.nextmatchreminder.webservice.json.NMRJsonApplication;

/**
 *
 */
public class StartNextMatchReminderWebService 
{
    public static void main(String[] args)
    {
    	extractParameters(args);
    	
    	try
		{
    		Server server = new Server();
        	ServerConnector serverConnector = new ServerConnector(server);
        	serverConnector.setPort(NMRProperty.getServerPort());
        	server.addConnector(serverConnector);
        	
        	
        	final JettyHttpContainer container = ContainerFactory.createContainer(JettyHttpContainer.class, new NMRJsonApplication());
        	
        	server.setHandler(container);
    		
			server.start();
			server.join();
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
    	
    }

    /**
     * 
     * @param args
     */
	private static void extractParameters(String[] args)
	{
		for (String arg : args)
    	{
    		String[] paramValue = arg.split("=");
    		
    		if (paramValue.length != 2)
    		{
    			throw new RuntimeException("error argumentation:" + arg);
    		}
    		else
    		{
    			NMRProperty.setProperty(paramValue[0], paramValue[1]);
    		}
    	}
	}
}
