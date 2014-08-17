package de.betaradion.biosearcher.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonView;

import de.betaradion.biosearcher.model.jackson.Views;

/**
 * The persistent class for the Characters database table.
 * 
 */
@Entity
@Table(name = "Characters")
@NamedQueries({
		@NamedQuery(name = "Character.findByFID", query = "SELECT c FROM Character c where c.family.fid = :id"),
		@NamedQuery(name = "Character.findByCID", query = "SELECT c FROM Character c where c.cid = :id"), })
public class Character implements Serializable {
	@JsonView(Views.Transient.class)
	private static final long serialVersionUID = 1L;
	@JsonView(Views.CharacterListView.class)
	private int cid;
	@JsonView(Views.CharacterListView.class)
	private String description;
	@JsonView(Views.CharacterListView.class)
	private String img;
	@JsonView(Views.CharacterListView.class)
	private String name;
	@JsonView(Views.Transient.class)
	private Family family;
	@JsonView(Views.Transient.class)
	private List<MatchTable> matches;
	@JsonView(Views.CharacterView.class)
	private List<Option> options;

	public Character() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	public int getCid() {
		return this.cid;
	}

	protected void setCid(int cid) {
		this.cid = cid;
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

	// bi-directional many-to-one association to Family
	@ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "FID", nullable = false)
	public Family getFamily() {
		return this.family;
	}

	public void setFamily(Family family) {
		this.family = family;
	}

	// bi-directional many-to-one association to MatchTable
	@OneToMany(mappedBy = "character")
	public List<MatchTable> getMatches() {
		return this.matches;
	}

	public void setMatches(List<MatchTable> matches) {
		this.matches = matches;
	}

	public MatchTable addMatch(MatchTable match) {
		getMatches().add(match);
		match.setCharacter(this);

		return match;
	}

	public MatchTable removeMatch(MatchTable match) {
		getMatches().remove(match);
		match.setCharacter(null);

		return match;
	}

	// bi-directional many-to-one association to Option
	@OneToMany(mappedBy = "character")
	public List<Option> getOptions() {
		return this.options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

	public Option addOption(Option option) {
		getOptions().add(option);
		option.setCharacter(this);

		return option;
	}

	public Option removeOption(Option option) {
		getOptions().remove(option);
		option.setCharacter(null);

		return option;
	}

}