package de.zachxu.nextmatchreminder.webservice;

import java.util.Properties;

public class NMRProperty{
	
	private static final Properties props = new Properties(); 
	
	public static enum NMRParam{
		SERVERPORT("-port", "80"),
		DBURL("-dburl", "jdbc:derby:./database/NMR");
		
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
	 * @param pParam
	 * @return
	 */
	private static String getProperty(NMRParam pParam) {
		return props.getProperty(pParam.getParameter(), pParam.getDefaultValue());
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
