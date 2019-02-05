package de.zachxu.nextmatchreminder.webservice.db.data;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@AttributeOverride(name="mId", column=@Column(name="COUNTRY_ID"))
public class MatchCountry extends AbstractNMRObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="COUNTRYNAME")
	private final String mCountryName;
	
	/**
	 * 
	 */
	public MatchCountry() {
		this(null, null);
	}
	
	/**
	 * @param pCountryName
	 */
	public MatchCountry(String pId, String pCountryName) {
		mId = pId;
		mCountryName = pCountryName;
	}

	public String getCountryName() {
		return mCountryName;
	}
	
}
