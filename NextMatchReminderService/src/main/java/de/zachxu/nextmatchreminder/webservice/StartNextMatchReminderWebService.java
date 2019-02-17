package de.zachxu.nextmatchreminder.webservice;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.util.ssl.SslContextFactory;
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
        	
        	HttpConfiguration https = new HttpConfiguration();
        	https.addCustomizer(new SecureRequestCustomizer());
        	
        	SslContextFactory sslContextFactory = new SslContextFactory();
        	sslContextFactory.setKeyStorePath(StartNextMatchReminderWebService.class.getResource("/keystore.jks").toExternalForm());
        	sslContextFactory.setKeyStorePassword("abcd,1234");
        	sslContextFactory.setKeyManagerPassword("abcd,1234");
        	
        	ServerConnector sslConnector = new ServerConnector(server, 
        			new SslConnectionFactory(sslContextFactory, "http/1.1"),
        			new HttpConnectionFactory(https));
        	sslConnector.setPort(NMRProperty.getSecureServerPort());
        	
        	server.setConnectors(new Connector[] {serverConnector, sslConnector});
        	
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
