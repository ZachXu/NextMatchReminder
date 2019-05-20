package de.zachxu.nextmatchreminder.webservice.db.dao;

import java.util.List;

public abstract class AbstractDAO<T> {
	
	/**
	 * 
	 */
	public AbstractDAO() {
		super();
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
