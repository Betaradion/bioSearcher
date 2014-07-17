package de.betaradion.biosearcher.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

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
	private static final long serialVersionUID = 1L;
	@Expose
	private int cid;
	@Expose
	private String description;
	@Expose
	private String img;
	@Expose
	private String name;
	private Family family;
	private List<MatchTable> matchTables;
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
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "FID", nullable = false)
	public Family getFamily() {
		return this.family;
	}

	public void setFamily(Family family) {
		this.family = family;
	}

	// bi-directional many-to-one association to MatchTable
	@OneToMany(mappedBy = "character")
	public List<MatchTable> getMatchTables() {
		return this.matchTables;
	}

	public void setMatchTables(List<MatchTable> matchTables) {
		this.matchTables = matchTables;
	}

	public MatchTable addMatchTable(MatchTable matchTable) {
		getMatchTables().add(matchTable);
		matchTable.setCharacter(this);

		return matchTable;
	}

	public MatchTable removeMatchTable(MatchTable matchTable) {
		getMatchTables().remove(matchTable);
		matchTable.setCharacter(null);

		return matchTable;
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