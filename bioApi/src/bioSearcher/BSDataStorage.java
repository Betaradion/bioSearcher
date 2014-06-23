package bioSearcher;

public abstract class BSDataStorage {

	protected int id = 0;
	protected String name = "";

	public BSDataStorage(int newId) {
		id = newId;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public abstract String getJSONDescription();

}