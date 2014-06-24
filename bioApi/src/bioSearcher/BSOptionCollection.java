package bioSearcher;

public class BSOptionCollection extends BSCollectionStructure {
	private BSOption[] options = new BSOption[2]; 
	
	public BSOptionCollection(int id){
		super();
		
		switch(id){
		case 21:
			this.options[0] = new BSOption(31);
			this.options[1] = new BSOption(32);
			break;
		case 22:
			this.options[0] = new BSOption(33);
			this.options[1] = new BSOption(34);
			break;
		case 23:
			this.options[0] = new BSOption(35);
			this.options[1] = new BSOption(36);
			break;
		case 24:
			this.options[0] = new BSOption(37);
			this.options[1] = new BSOption(38);
			break;
		}
	}
}
