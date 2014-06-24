package bioSearcher;

public class BSOptionCollection extends BSCollectionStructure {
	private BSOption[] options; 
	
	public BSOptionCollection(){
		super();
		
		switch(id){
		case 21:
			this.name = "Size";
			this.options[0] = new BSOption(31);
			this.options[1] = new BSOption(32);
			break;
		case 22:
			this.name = "Color";
			this.options[0] = new BSOption(33);
			this.options[1] = new BSOption(34);
			break;
		case 23:
			this.name = "Form";
			this.options[0] = new BSOption(35);
			this.options[1] = new BSOption(36);
			break;
		case 24:
			this.name = "Habitat";
			this.options[0] = new BSOption(37);
			this.options[1] = new BSOption(38);
			break;
		default:
			this.name = "Character undefined.";
			break;

		}
	}
	
	
	@Override
	public String getJSONDescription(int n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void loadSubModules(int y) {
		// TODO Auto-generated method stub

	}

}
