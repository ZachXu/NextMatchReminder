package de.zachxu.nextmatchreminder.webservice.db.daoservice;

import java.util.Date;
import java.util.List;

import de.zachxu.nextmatchreminder.webservice.db.data.MatchInfo;

public class MatchInfoDAOService extends AbstractDAOService<MatchInfo> {

	/**
	 * 
	 */
	public MatchInfoDAOService()
	{
		super();
	}
	
	/**
	 * 
	 * @return
	 */
	public static MatchInfoDAOService getInstance()
	{
		return new MatchInfoDAOService();
	}

	@Override
	public MatchInfo getById(String pId)
	{
		throw new RuntimeException("Unsupported method");
	}

	@Override
	public List<MatchInfo> getAllList()
	{
		throw new RuntimeException("Unsupported method");
	}
	
	/**
	 * 
	 * @param pTeamId
	 * @param pSearchDate
	 * @return
	 */
	public MatchInfo getNextMatchInfo(String pTeamId, Date pSearchDate)
	{
		MatchInfo retVal = new MatchInfo();
		
		String hql = "from " + MatchInfo.class.getName() + " mi "
				+ " where (mi.mHomeTeam.mId = :mTeamId or mi.mGuestTeam.mId = :mTeamId) "
				+ " and mi.mMatchTime >= :mSearchDate order by mi.mMatchTime asc";
		
		List<MatchInfo> retList = mSessionManager.createQuery(hql, MatchInfo.class)
		.setParameter("mTeamId", pTeamId != null ? pTeamId.toUpperCase() : "")
		.setParameter("mSearchDate", pSearchDate)
		.getResultList();
		
		if (!retList.isEmpty())
		{
			retVal = retList.get(0);
		}
		
		return retVal;
	}

}
