package de.betaradion.biosearcher.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the MatchTable database table.
 * 
 */
@Entity
@Table(name = "MatchTable")
@NamedQuery(name = "MatchTable.findAll", query = "SELECT m FROM MatchTable m")
public class MatchTable implements Serializable {
	private static final long serialVersionUID = 1L;
	private MatchTablePK id;
	private Character character;
	private Option option;
	private Species species;

	public MatchTable() {
	}

	@EmbeddedId
	public MatchTablePK getId() {
		return this.id;
	}

	public void setId(MatchTablePK id) {
		this.id = id;
	}

	// bi-directional many-to-one association to Character
	@ManyToOne
	@JoinColumn(name = "CID", nullable = false)
	public Character getCharacter() {
		return this.character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	// bi-directional many-to-one association to Option
	@ManyToOne
	@JoinColumn(name = "OID", nullable = false)
	public Option getOption() {
		return this.option;
	}

	public void setOption(Option option) {
		this.option = option;
	}

	// bi-directional many-to-one association to Species
	@ManyToOne
	@JoinColumn(name = "SID", nullable = false)
	public Species getSpecy() {
		return this.species;
	}

	public void setSpecy(Species species) {
		this.species = species;
	}

}