package de.betaradion.biosearcher.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonView;

import de.betaradion.biosearcher.model.jackson.Views;

/**
 * The persistent class for the Families database table.
 * 
 */
@Entity
@Table(name = "Families")
@NamedQueries({
		@NamedQuery(name = "Family.findAll", query = "SELECT f FROM Family f"),
		@NamedQuery(name = "Family.findByID", query = "SELECT f FROM Family f where f.fid = :id"), })
public class Family implements Serializable {
	@JsonView(Views.Transient.class)
	private static final long serialVersionUID = 1L;
	@JsonView(Views.FamilyListView.class)
	private int fid;
	@JsonView(Views.FamilyListView.class)
	private String description;
	@JsonView(Views.FamilyListView.class)
	private String img;
	@JsonView(Views.FamilyListView.class)
	private String name;
	@JsonView(Views.FamilyView.class)
	private List<Character> characters;
	@JsonView(Views.FamilyView.class)
	private List<Species> species;

	public Family() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	public int getFid() {
		return this.fid;
	}

	protected void setFid(int fid) {
		this.fid = fid;
	}

	@Column(length = 45)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	// bi-directional many-to-one association to Character
	@OneToMany(mappedBy = "family", cascade = { CascadeType.ALL })
	public List<Character> getCharacters() {
		return this.characters;
	}

	public void setCharacters(List<Character> characters) {
		this.characters = characters;
	}

	public Character addCharacter(Character character) {
		getCharacters().add(character);
		character.setFamily(this);

		return character;
	}

	public Character removeCharacter(Character character) {
		getCharacters().remove(character);
		character.setFamily(null);

		return character;
	}

	// bi-directional many-to-one association to Species
	@OneToMany(mappedBy = "family")
	public List<Species> getSpecies() {
		return this.species;
	}

	public void setSpecies(List<Species> species) {
		this.species = species;
	}

	public Species addSpecy(Species specy) {
		getSpecies().add(specy);
		specy.setFamily(this);

		return specy;
	}

	public Species removeSpecy(Species specy) {
		getSpecies().remove(specy);
		specy.setFamily(null);

		return specy;
	}

}