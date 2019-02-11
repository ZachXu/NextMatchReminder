package de.zachxu.nextmatchreminder.webservice.json;

import java.util.Date;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;

import de.zachxu.nextmatchreminder.webservice.db.data.MatchInfo;

@JsonbPropertyOrder({"home", "guest", "categoryName", "matchRound", "matchTime"})
public class NMRJsonMatch {
	
	@JsonbProperty("home")
	private final String mHomeTeam;
	
	@JsonbProperty("guest")
	private final String mGuestTeam;
	
	@JsonbProperty("categoryName")
	private final String mMatchCategory;
	
	@JsonbProperty("matchRound")
	private final int mMatchRound;
	
	@JsonbProperty("matchTime")
	private final Date mMatchTime;
	
	/**
	 * 
	 */
	public NMRJsonMatch()
	{
		this(new MatchInfo());
	}

	/**
	 * @param pHomeTeam
	 * @param pGuestTeam
	 * @param pMatchCategory
	 * @param pMatchRound
	 * @param pMatchTime
	 */
	public NMRJsonMatch(MatchInfo pMatchInfo)
	{
		super();
		mHomeTeam = pMatchInfo.getHomeTeam().getTeamName();
		mGuestTeam = pMatchInfo.getGuestTeam().getTeamName();
		mMatchCategory = pMatchInfo.getMatchCategory().getCategoryName();
		mMatchRound = pMatchInfo.getMatchRound();
		mMatchTime = pMatchInfo.getMatchTime();
	}

	/**
	 * @return the homeTeam
	 */
	public String getHomeTeam()
	{
		return mHomeTeam;
	}

	/**
	 * @return the guestTeam
	 */
	public String getGuestTeam()
	{
		return mGuestTeam;
	}

	/**
	 * @return the matchCategory
	 */
	public String getMatchCategory()
	{
		return mMatchCategory;
	}

	/**
	 * @return the matchRound
	 */
	public int getMatchRound()
	{
		return mMatchRound;
	}

	/**
	 * @return the matchTime
	 */
	public Date getMatchTime()
	{
		return mMatchTime;
	}

}
