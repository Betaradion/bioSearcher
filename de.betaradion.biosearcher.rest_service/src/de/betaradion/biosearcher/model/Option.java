package de.betaradion.biosearcher.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Options database table.
 * 
 */
@Entity
@Table(name="Options")
@NamedQuery(name="Option.findAll", query="SELECT o FROM Option o")
public class Option implements Serializable {
	private static final long serialVersionUID = 1L;
	private int oid;
	private String description;
	private String img;
	private String name;
	private List<MatchTable> matchTables;
	private Character character;

	public Option() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	public int getOid() {
		return this.oid;
	}

	protected void setOid(int oid) {
		this.oid = oid;
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


	//bi-directional many-to-one association to MatchTable
	@OneToMany(mappedBy="option")
	public List<MatchTable> getMatchTables() {
		return this.matchTables;
	}

	public void setMatchTables(List<MatchTable> matchTables) {
		this.matchTables = matchTables;
	}

	public MatchTable addMatchTable(MatchTable matchTable) {
		getMatchTables().add(matchTable);
		matchTable.setOption(this);

		return matchTable;
	}

	public MatchTable removeMatchTable(MatchTable matchTable) {
		getMatchTables().remove(matchTable);
		matchTable.setOption(null);

		return matchTable;
	}


	//bi-directional many-to-one association to Character
	@ManyToOne
	@JoinColumn(name="CID", nullable=false)
	public Character getCharacter() {
		return this.character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

}