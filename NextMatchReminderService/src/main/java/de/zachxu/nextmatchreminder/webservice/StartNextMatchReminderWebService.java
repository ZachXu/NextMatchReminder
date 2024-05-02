package de.zachxu.nextmatchreminder.webservice;

import java.net.InetAddress;

import org.apache.derby.drda.NetworkServerControl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import de.zachxu.nextmatchreminder.webservice.NMRProperty.NMRParam;
import de.zachxu.nextmatchreminder.webservice.json.NMRJsonApplication;

/**
 *
 */
public class StartNextMatchReminderWebService 
{
	private static final Logger LOG = LogManager.getLogger(StartNextMatchReminderWebService.class);
	
    public static void main(String[] args)
    {
    	extractParameters(args);
    	
    	NetworkServerControl derbyServer = null;
    	
    	try
		{
    		LOG.info("initial server...");
    		
    		LOG.info("Start Derby server...");
    		
    		derbyServer = new NetworkServerControl(InetAddress.getByName("0.0.0.0"), 1527);
    		derbyServer.start(null);
    		
    		LOG.info("Derby server started...");
    		
    		Server server = new Server();
        	ServerConnector serverConnector = new ServerConnector(server);
        	serverConnector.setPort(NMRProperty.getServerPort());
        	
        	if (NMRProperty.getBooleanProperty(NMRParam.ENABLE_SSL))
        	{
        		HttpConfiguration https = new HttpConfiguration();
        		https.addCustomizer(new SecureRequestCustomizer());
        		
        		SslContextFactory sslContextFactory = new SslContextFactory();
        		sslContextFactory.setKeyStorePath(
        				StartNextMatchReminderWebService.class.getResource("/keystore.jks").toExternalForm());
        		sslContextFactory.setKeyStorePassword("abcd,1234");
        		sslContextFactory.setKeyManagerPassword("abcd,1234");
        		
        		ServerConnector sslConnector = new ServerConnector(server,
        				new SslConnectionFactory(sslContextFactory, "http/1.1"), new HttpConnectionFactory(https));
        		sslConnector.setPort(NMRProperty.getSecureServerPort());
        		
        		server.setConnectors(new Connector[] {serverConnector, sslConnector});
        	}
        	else
        	{
        		server.setConnectors(new Connector[] {serverConnector});
        	}
        	
        	final JettyHttpContainer container = ContainerFactory.createContainer(JettyHttpContainer.class, new NMRJsonApplication());
        	
        	server.setHandler(container);
    		
			server.start();
			server.join();
			
		} catch (Exception e)
		{
			LOG.error(e.getMessage(), e);
		}
    	finally {
    		LOG.info("Server is down...");
    		
    		if (derbyServer != null)
    		{
    			try
				{
					derbyServer.shutdown();
				} catch (Exception e)
				{
					LOG.error(e.getMessage(), e);
				}
    		}
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
