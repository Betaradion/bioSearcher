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

import com.fasterxml.jackson.annotation.JsonView;

import de.betaradion.biosearcher.model.jackson.Views;

/**
 * The persistent class for the Options database table.
 * 
 */
@Entity
@Table(name = "Options")
@NamedQueries({
		@NamedQuery(name = "Option.findByCID", query = "SELECT o FROM Option o where o.character.cid = :id"),
		@NamedQuery(name = "Option.findByOID", query = "SELECT o FROM Option o where o.oid = :id"), })
public class Option implements Serializable {
	@JsonView(Views.Transient.class)
	private static final long serialVersionUID = 1L;
	@JsonView(Views.CharacterView.class)
	private int oid;
	@JsonView(Views.CharacterView.class)
	private String description;
	@JsonView(Views.CharacterView.class)
	private String img;
	@JsonView(Views.CharacterView.class)
	private String name;
	@JsonView(Views.Transient.class)
	private Character character;

	public Option() {
	}

	public Option(int oid) {
		this.oid = oid;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	public int getOid() {
		return this.oid;
	}

	protected void setOid(int oid) {
		this.oid = oid;
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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CID", nullable = false)
	public Character getCharacter() {
		return this.character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

}