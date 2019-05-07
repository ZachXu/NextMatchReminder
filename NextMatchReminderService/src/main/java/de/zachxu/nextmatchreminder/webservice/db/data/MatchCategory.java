package de.zachxu.nextmatchreminder.webservice.db.data;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@AttributeOverride(name="mId", column=@Column(name="CATEGORY_ID"))
public class MatchCategory extends AbstractNMRObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 132850720382399037L;

	@Column(name="CATEGORYNAME")
	private final String mCategoryName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="COUNTRY_ID")
	private final MatchCountry mCategoryCountry;

	/**
	 * 
	 */
	public MatchCategory()
	{
		this(null, null, null);
	}

	/**
	 * @param pCategoryName
	 * @param pCategoryCountry
	 */
	public MatchCategory(String pId, String pCategoryName, MatchCountry pCategoryCountry)
	{
		super();
		mId = pId;
		mCategoryName = pCategoryName;
		mCategoryCountry = pCategoryCountry;
	}

	/**
	 * @return the categoryName
	 */
	public String getCategoryName()
	{
		return mCategoryName;
	}



	/**
	 * @return the categoryCountry
	 */
	public MatchCountry getCategoryCountry()
	{
		return mCategoryCountry;
	}

}
