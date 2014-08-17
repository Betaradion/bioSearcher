package de.betaradion.biosearcher.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonView;

import de.betaradion.biosearcher.model.jackson.Views;

/**
 * The persistent class for the Species database table.
 * 
 */
@Entity
@Table(name = "Species")
@NamedQuery(name = "Species.findBySID", query = "SELECT s FROM Species s where s.sid = :id")
public class Species implements Serializable {
	@JsonView(Views.Transient.class)
	private static final long serialVersionUID = 1L;
	@JsonView(Views.SpeciesView.class)
	private int sid;
	@JsonView(Views.SpeciesView.class)
	private String img;
	@JsonView(Views.SpeciesView.class)
	private String name;
	@JsonView(Views.Transient.class)
	private List<MatchTable> matches;
	@JsonView(Views.Transient.class)
	private List<Profile> profile;
	@JsonView(Views.Transient.class)
	private Family family;

	public Species() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	public int getSid() {
		return this.sid;
	}

	protected void setSid(int sid) {
		this.sid = sid;
	}

	@Column(length = 45)
	public String getImg() {
		return this.img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Column(nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// bi-directional many-to-one association to MatchTable
	@OneToMany(mappedBy = "specy")
	public List<MatchTable> getMatches() {
		return this.matches;
	}

	public void setMatches(List<MatchTable> matches) {
		this.matches = matches;
	}

	public MatchTable addMatch(MatchTable match) {
		getMatches().add(match);
		match.setSpecy(this);

		return match;
	}

	public MatchTable removeMatch(MatchTable match) {
		getMatches().remove(match);
		match.setSpecy(null);

		return match;
	}

	// bi-directional many-to-one association to Profile
	@OneToMany(mappedBy = "species")
	public List<Profile> getProfile() {
		return this.profile;
	}

	public void setProfile(List<Profile> profile) {
		this.profile = profile;
	}

	public Profile addProfile(Profile profile) {
		getProfile().add(profile);
		profile.setSpecies(this);

		return profile;
	}

	public Profile removeProfile(Profile profile) {
		getProfile().remove(profile);
		profile.setSpecies(null);

		return profile;
	}

	// bi-directional many-to-one association to Family
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FID", nullable = false)
	public Family getFamily() {
		return this.family;
	}

	public void setFamily(Family family) {
		this.family = family;
	}

}