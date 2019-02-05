package de.zachxu.nextmatchreminder.webservice.db;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

/**
 * 
 * @author zxu
 *
 */
public class SessionManager {
	
	private final StandardServiceRegistry mRegistry;
	private final SessionFactory mSessionFactory;
	
	private Session mSession;

	/**
	 * 
	 */
	public SessionManager(String pURL) {
		mRegistry = new StandardServiceRegistryBuilder()
				.configure("de/zachxu/nextmatchreminder/webservice/db/hibernate/hibernate.cfg.xml")
				.applySetting("hibernate.connection.url", pURL)
				.build();
		
		mSessionFactory = new MetadataSources(mRegistry)
				.buildMetadata()
				.buildSessionFactory();
		
		startNewSession();
	}
	
	/**
	 * 
	 */
	public void startNewSession()
	{
		if (mSession != null && mSession.isOpen())
		{
			mSession.close();
		}
		
		mSession = mSessionFactory.openSession();
		mSession.beginTransaction();
	}

	/**
	 * 
	 */
	public void rollback() {
		mSession.getTransaction().rollback();
		startNewSession();

	}
	
	/**
	 * 
	 * @param pClass
	 * @param pId
	 * @return
	 */
	public <T> T get(Class<T> pClass, Serializable pId)
	{
		return mSession.get(pClass, pId);
	}
	
	/**
	 * 
	 * @param pArg0
	 * @return
	 */
	public <T> Query<T> createQuery(String pArg0, Class<T> pClass) {
		return mSession.createQuery(pArg0, pClass);
	}
	
	/**
	 * 
	 * @param pArg0
	 * @return
	 */
	public Serializable save(Object pArg0) {
		return mSession.save(pArg0);
	}
	
	/**
	 * 
	 * @param pArg0
	 * @return
	 * @throws HibernateException
	 */
	public Object merge(Object pArg0) {
		return mSession.merge(pArg0);
	}
	
	/**
	 * 
	 * @param pArg0
	 */
	public void delete(Object pArg0) {
		mSession.delete(pArg0);
	}
	
	/**
	 * 
	 */
	public void flush()
	{
		mSession.flush();
	}
	
	/**
	 * 
	 */
	public void commit()
	{
		mSession.getTransaction().commit();
	    startNewSession();
	}
	
	/**
	 * 
	 */
	public void close()
	{
		if (mSession != null)
		{
			mSession.close();
		}
	}
}
