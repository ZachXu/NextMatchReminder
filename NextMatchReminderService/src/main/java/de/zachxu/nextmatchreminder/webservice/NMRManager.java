package de.zachxu.nextmatchreminder.webservice;

import de.zachxu.nextmatchreminder.webservice.db.SessionManager;

/**
 * static instance manager for NextMatchReminder
 * @author zxu
 *
 */
public class NMRManager {

	private final SessionManager mSessionManager;
	
	private static NMRManager instance;

	/**
	 * 
	 */
	private NMRManager() {
		super();
		
		mSessionManager = new SessionManager(NMRProperty.getDBURL());
	}
	
	/**
	 * 
	 * @return
	 */
	public static NMRManager getInstance()
	{
		if (instance == null)
		{
			instance = new NMRManager();
		}
		
		return instance;
	}

	/**
	 * 
	 * @return
	 */
	public SessionManager getSessionManager() {
		return mSessionManager;
	}
	
}
