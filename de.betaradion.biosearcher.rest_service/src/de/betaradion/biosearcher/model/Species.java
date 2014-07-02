package de.betaradion.biosearcher.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Species database table.
 * 
 */
@Entity
@Table(name="Species")
@NamedQuery(name="Species.findAll", query="SELECT s FROM Species s")
public class Species implements Serializable {
	private static final long serialVersionUID = 1L;
	private int sid;
	private String img;
	private String name;
	private List<MatchTable> matchTables;
	private List<Profile> profiles;

	public Species() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	public int getSid() {
		return this.sid;
	}

	protected void setSid(int sid) {
		this.sid = sid;
	}


	@Column(length=45)
	public String getImg() {
		return this.img;
	}

	public void setImg(String img) {
		this.img = img;
	}


	@Column(nullable=false, length=45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	//bi-directional many-to-one association to MatchTable
	@OneToMany(mappedBy="specy")
	public List<MatchTable> getMatchTables() {
		return this.matchTables;
	}

	public void setMatchTables(List<MatchTable> matchTables) {
		this.matchTables = matchTables;
	}

	public MatchTable addMatchTable(MatchTable matchTable) {
		getMatchTables().add(matchTable);
		matchTable.setSpecy(this);

		return matchTable;
	}

	public MatchTable removeMatchTable(MatchTable matchTable) {
		getMatchTables().remove(matchTable);
		matchTable.setSpecy(null);

		return matchTable;
	}


	//bi-directional many-to-one association to Profile
	@OneToMany(mappedBy="specy")
	public List<Profile> getProfiles() {
		return this.profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}

	public Profile addProfile(Profile profile) {
		getProfiles().add(profile);
		profile.setSpecy(this);

		return profile;
	}

	public Profile removeProfile(Profile profile) {
		getProfiles().remove(profile);
		profile.setSpecy(null);

		return profile;
	}

}