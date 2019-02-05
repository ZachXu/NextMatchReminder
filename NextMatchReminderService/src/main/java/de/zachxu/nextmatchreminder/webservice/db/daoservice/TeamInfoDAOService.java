package de.zachxu.nextmatchreminder.webservice.db.daoservice;

import java.util.List;

import de.zachxu.nextmatchreminder.webservice.db.data.TeamInfo;

/**
 * 
 * @author zxu
 *
 */
public class TeamInfoDAOService extends AbstractDAOService<TeamInfo> {

	/**
	 * 
	 */
	private TeamInfoDAOService()
	{
		super();
	}

	/**
	 * 
	 * @return
	 */
	public static TeamInfoDAOService getInstance()
	{
		return new TeamInfoDAOService();
	}

	@Override
	public List<TeamInfo> getAllList()
	{
		String hql = "from " + TeamInfo.class.getName();
		
		return mSessionManager.createQuery(hql, TeamInfo.class).getResultList();
	}

	@Override
	public TeamInfo getById(String pId)
	{
		return mSessionManager.get(TeamInfo.class, pId.toUpperCase());
	}
}
