package de.betaradion.biosearcher.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Profile database table.
 * 
 */
@Entity
@Table(name="Profile")
@NamedQuery(name="Profile.findAll", query="SELECT p FROM Profile p")
public class Profile implements Serializable {
	private static final long serialVersionUID = 1L;
	private int pid;
	private String character;
	private String option;
	private Species species;

	public Profile() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	public int getPid() {
		return this.pid;
	}

	protected void setPid(int pid) {
		this.pid = pid;
	}


	@Column(nullable=false, length=45)
	public String getCharacter() {
		return this.character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}


	@Column(nullable=false, length=45)
	public String getOption() {
		return this.option;
	}

	public void setOption(String option) {
		this.option = option;
	}


	//bi-directional many-to-one association to Species
	@ManyToOne
	@JoinColumn(name="SID", nullable=false)
	public Species getSpecy() {
		return this.species;
	}

	public void setSpecy(Species species) {
		this.species = species;
	}

}