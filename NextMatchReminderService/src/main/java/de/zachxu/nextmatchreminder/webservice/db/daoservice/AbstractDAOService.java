package de.zachxu.nextmatchreminder.webservice.db.daoservice;

import java.util.List;

import de.zachxu.nextmatchreminder.webservice.NMRManager;
import de.zachxu.nextmatchreminder.webservice.db.SessionManager;
import net.bytebuddy.description.modifier.MethodStrictness;

public abstract class AbstractDAOService<T> {
	
	protected final SessionManager mSessionManager;

	/**
	 * 
	 */
	public AbstractDAOService() {
		super();
		
		mSessionManager = NMRManager.getInstance().getSessionManager();
	}
	
	/**
	 * 
	 * @param pId
	 * @return
	 */
	public abstract T getById(String pId);
	
	/**
	 * 
	 * @return
	 */
	public abstract List<T> getAllList();
	
}
