package bioSearcher;

public class BSFamilies {
	private BSFamily[] families;
	
	public BSFamilies(){
		families = new BSFamily[2];
		
		families[0] = new BSFamily(11);
		families[1] = new BSFamily(12);
	}
	
	public BSFamily[] getFamilies() {
		return families;
	}

}
