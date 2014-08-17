package de.betaradion.biosearcher.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the Profile database table.
 * 
 */
@Entity
@Table(name = "Profile")
@NamedQueries({
		@NamedQuery(name = "Profile.findBySID", query = "SELECT p FROM Profile p where p.species.sid = :id"),
		@NamedQuery(name = "Profile.findByPID", query = "SELECT p FROM Profile p where p.pid = :id"), })
public class Profile implements Serializable {
	private static final long serialVersionUID = 1L;
	private int pid;
	private String character;
	private String option;
	private Species species;

	public Profile() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	public int getPid() {
		return this.pid;
	}

	protected void setPid(int pid) {
		this.pid = pid;
	}

	@Column(nullable = false, length = 45)
	public String getCharacter() {
		return this.character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	@Column(nullable = false, length = 45)
	public String getOption() {
		return this.option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	// bi-directional many-to-one association to Species
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SID", nullable = false)
	public Species getSpecies() {
		return this.species;
	}

	public void setSpecies(Species species) {
		this.species = species;
	}

}