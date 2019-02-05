package de.zachxu.nextmatchreminder.webservice.db.daoservice;

import java.util.List;

import javax.persistence.Query;

import de.zachxu.nextmatchreminder.webservice.db.data.MatchCountry;

/**
 * 
 * @author zxu
 *
 */
public class MatchCountryDAOService extends AbstractDAOService<MatchCountry> {

	/**
	 * 
	 */
	private MatchCountryDAOService() {
		super();
	}
	
	/**
	 * 
	 * @return
	 */
	public static MatchCountryDAOService getInstance()
	{
		return new MatchCountryDAOService();
	}
	
	@Override
	public List<MatchCountry> getAllList()
	{
		String hql = "select c from " + MatchCountry.class.getName() + " c ";

		return mSessionManager.createQuery(hql, MatchCountry.class).getResultList();
	}

	@Override
	public MatchCountry getById(String pId)
	{
		return mSessionManager.get(MatchCountry.class, pId);
	}
	
	
}
