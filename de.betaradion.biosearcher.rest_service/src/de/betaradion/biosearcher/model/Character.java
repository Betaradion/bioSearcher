package de.betaradion.biosearcher.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Characters database table.
 * 
 */
@Entity
@Table(name="Characters")
@NamedQuery(name="Character.findAll", query="SELECT c FROM Character c")
public class Character implements Serializable {
	private static final long serialVersionUID = 1L;
	private int cid;
	private String description;
	private String img;
	private String name;
	private Family family;
	private List<MatchTable> matchTables;
	private List<Option> options;

	public Character() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	public int getCid() {
		return this.cid;
	}

	protected void setCid(int cid) {
		this.cid = cid;
	}


	@Column(length=45)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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


	//bi-directional many-to-one association to Family
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="FID", nullable=false)
	public Family getFamily() {
		return this.family;
	}

	public void setFamily(Family family) {
		this.family = family;
	}


	//bi-directional many-to-one association to MatchTable
	@OneToMany(mappedBy="character")
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


	//bi-directional many-to-one association to Option
	@OneToMany(mappedBy="character")
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