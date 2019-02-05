package de.zachxu.nextmatchreminder.webservice.db.data;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractNMRObject implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	protected String mId;
	
	public String getId() {
		return this.mId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.mId == null) ? 0 : this.mId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractNMRObject other = (AbstractNMRObject) obj;
		if (this.mId == null) {
			if (other.mId != null)
				return false;
		} else if (!this.mId.equals(other.mId))
			return false;
		return true;
	}
	
}
