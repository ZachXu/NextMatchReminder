package de.zachxu.nextmatchreminder.webservice.json;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;

import de.zachxu.nextmatchreminder.webservice.db.data.MatchInfo;

@JsonbPropertyOrder({"homeTeam", "guestTeam", "matchCategory", "matchRound", "matchTime"})
public class NMRJsonMatch {
	
	private static final SimpleDateFormat NMRSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	private final String mHomeTeam;
	private final String mGuestTeam;
	private final String mMatchCategory;
	private final int mMatchRound;
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
	@JsonbProperty("home")
	public String getHomeTeam()
	{
		return mHomeTeam;
	}

	/**
	 * @return the guestTeam
	 */
	@JsonbProperty("guest")
	public String getGuestTeam()
	{
		return mGuestTeam;
	}

	/**
	 * @return the matchCategory
	 */
	@JsonbProperty("categoryName")
	public String getMatchCategory()
	{
		return mMatchCategory;
	}

	/**
	 * @return the matchRound
	 */
	@JsonbProperty("matchRound")
	public int getMatchRound()
	{
		return mMatchRound;
	}

	/**
	 * @return the matchTime
	 */
	@JsonbProperty("matchTime")
	public String getMatchTime()
	{
		return NMRSDF.format(mMatchTime);
	}

}
