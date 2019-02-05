package de.zachxu.nextmatchreminder.webservice.db.data;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@AttributeOverride(name="mId", column=@Column(name="TEAM_ID"))
public class TeamInfo extends AbstractNMRObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="TEAMNAME")
	private final String mTeamName;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="COUNTRY_ID")
	private final MatchCountry mMatchCountry;
	
	public TeamInfo()
	{
		this(null, null, null);
	}

	/**
	 * @param pTeamName
	 * @param pMatchCountry
	 */
	public TeamInfo(String pId, String pTeamName, MatchCountry pMatchCountry)
	{
		super();
		mId = pId;
		mTeamName = pTeamName;
		mMatchCountry = pMatchCountry;
	}

	/**
	 * @return the teamName
	 */
	public String getTeamName()
	{
		return mTeamName;
	}

	/**
	 * @return the matchCountry
	 */
	public MatchCountry getMatchCountry()
	{
		return mMatchCountry;
	}
	
	
}
