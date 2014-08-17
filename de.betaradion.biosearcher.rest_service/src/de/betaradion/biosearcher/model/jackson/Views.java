package de.betaradion.biosearcher.model.jackson;

public class Views {
	public static class Transient {
	};

	public static class FamilyListView {
	};

	public static class FamilyView extends FamilyListView {
	};

	public static class CharacterListView extends FamilyView {
	};

	public static class CharacterView extends CharacterListView {
	};

	public static class OptionListView extends CharacterView {
	};

	public static class OptionView extends OptionListView {
	};

	public static class SpeciesView {
	};
}
