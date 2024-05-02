package de.zachxu.nextmatchreminder.webservice;

import java.util.Properties;

public class NMRProperty{
	
	private static final Properties props = new Properties(); 
	
	public static enum NMRParam{
		SERVERPORT("--port", "80"),
		ENABLE_SSL("--enable-ssl", "true"),
		SECURESERVERPORT("--secureport", "443"),
		DBDRIVER("--dbdriver", "org.apache.derby.jdbc.ClientDriver"),
		DBURL("--dburl", "jdbc:derby:./database/NMR"),
		DBUSER("--dbuser", "NMR"),
		DBPASSWORD("--dbpassword", "NMR");
		
		private final String mParameter;
		private final String mDefaultValue;
		/**
		 * @param pParam
		 * @param pDefaultValue
		 */
		private NMRParam(String pParameter, String pDefaultValue) {
			mParameter = pParameter;
			mDefaultValue = pDefaultValue;
		}
		
		/**
		 * 
		 * @return
		 */
		public String getParameter() {
			return mParameter;
		}

		/**
		 * 
		 * @return
		 */
		public String getDefaultValue() {
			return mDefaultValue;
		}
	}

	/**
	 * 
	 * @return
	 */
	public static String getDBURL()
	{
		return getProperty(NMRParam.DBURL);
	}
	
	/**
	 * 
	 * @return
	 */
	public static int getServerPort()
	{
		return Integer.parseInt(getProperty(NMRParam.SERVERPORT));
	}
	
	/**
	 * 
	 * @return
	 */
	public static int getSecureServerPort()
	{
		return Integer.parseInt(getProperty(NMRParam.SECURESERVERPORT));
	}

	/**
	 * 
	 * @param pParam
	 * @return
	 */
	public static String getProperty(NMRParam pParam) {
		return props.getProperty(pParam.getParameter(), pParam.getDefaultValue());
	}
	
	/**
	 * 
	 * @param pParam
	 * @return boolean property value
	 */
	public static boolean getBooleanProperty(NMRParam pParam) {
		return Boolean.parseBoolean(getProperty(pParam));
	}
	
	/**
	 * 
	 * @param pKey
	 * @param pValue
	 */
	public static void setProperty(String pKey, String pValue)
	{
		props.setProperty(pKey, pValue);
	}
	
}
