package bioSearcher;

public class BSFamilies extends BSDataStorage {
	private BSFamily[] families;
	
	public BSFamilies(int id){
		super(id);
		
		this.families = new BSFamily[2];
		
		this.families[0] = new BSFamily(11);
		this.families[1] = new BSFamily(12);
	}
	
	public BSFamily[] getFamilies() {
		return this.families;
	}

}
