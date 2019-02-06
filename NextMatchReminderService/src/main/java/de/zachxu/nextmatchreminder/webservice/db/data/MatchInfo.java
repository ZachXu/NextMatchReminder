package de.zachxu.nextmatchreminder.webservice.db.data;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@AttributeOverride(name="mId", column=@Column(name="MATCH_ID"))
public class MatchInfo extends AbstractNMRObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="HOMETEAM_ID")
	private final TeamInfo mHomeTeam;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="GUESTTEAM_ID")
	private final TeamInfo mGuestTeam;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CATEGORY_ID")
	private final MatchCategory mMatchCategory;
	
	@Column(name="MATCHROUND")
	private final int mMatchRound;
	
	@Column(name="MATCHTIME")
	private final Date mMatchTime;
	
	/**
	 * 
	 */
	public MatchInfo()
	{
		this(null, null, null, null, 0, null);
	}

	/**
	 * @param pHomeTeam
	 * @param pGuestTeam
	 * @param pMatchCategory
	 * @param pMatchRound
	 * @param pMatchTime
	 */
	public MatchInfo(String pId, TeamInfo pHomeTeam, TeamInfo pGuestTeam, MatchCategory pMatchCategory, int pMatchRound,
			Date pMatchTime)
	{
		mId = pId;
		mHomeTeam = pHomeTeam;
		mGuestTeam = pGuestTeam;
		mMatchCategory = pMatchCategory;
		mMatchRound = pMatchRound;
		mMatchTime = pMatchTime;
	}

	/**
	 * @return the homeTeam
	 */
	public TeamInfo getHomeTeam()
	{
		return mHomeTeam;
	}

	/**
	 * @return the guestTeam
	 */
	public TeamInfo getGuestTeam()
	{
		return mGuestTeam;
	}

	/**
	 * @return the matchCategory
	 */
	public MatchCategory getMatchCategory()
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
