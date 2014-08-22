package de.betaradion.biosearcher.model.jackson;

public class Views {
	public static class Transient {
	};

	public static class FamilyListView {
	};

	public static class FamilyView extends FamilyListView {
	};

	public static class CharacterView extends FamilyView {
	};

	public static class FullView extends CharacterView {
	};

	public static class SpeciesView {
	};
}
