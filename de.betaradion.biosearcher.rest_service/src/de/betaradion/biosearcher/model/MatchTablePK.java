package de.betaradion.biosearcher.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the MatchTable database table.
 * 
 */
@Embeddable
public class MatchTablePK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private int sid;
	private int cid;
	private int oid;

	public MatchTablePK() {
	}

	@Column(insertable = false, updatable = false, unique = true, nullable = false)
	public int getSid() {
		return this.sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	@Column(insertable = false, updatable = false, unique = true, nullable = false)
	public int getCid() {
		return this.cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	@Column(insertable = false, updatable = false, unique = true, nullable = false)
	public int getOid() {
		return this.oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MatchTablePK)) {
			return false;
		}
		MatchTablePK castOther = (MatchTablePK) other;
		return (this.sid == castOther.sid) && (this.cid == castOther.cid)
				&& (this.oid == castOther.oid);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.sid;
		hash = hash * prime + this.cid;
		hash = hash * prime + this.oid;

		return hash;
	}
}