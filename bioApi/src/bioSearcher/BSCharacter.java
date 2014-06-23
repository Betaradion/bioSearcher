package bioSearcher;

public class BSCharacter extends BSDataStorage {
	private BSOption[] option = new BSOption[2];
	
	public BSCharacter(int newId){
		super(newId);
		
		switch(id){
			case 21:
					name = "Size";
					option[0] = new BSOption(31);
					option[1] = new BSOption(32);
				break;
			case 22:
					name = "Color";
					option[0] = new BSOption(33);
					option[1] = new BSOption(34);
				break;
			case 23:
					name = "Form";
					option[0] = new BSOption(35);
					option[1] = new BSOption(36);
				break;
			case 24:
					name = "Habitat";
					option[0] = new BSOption(37);
					option[1] = new BSOption(38);
				break;
			default:
					name = "Character undefined.";
				break;
			
		}
		
		
	}
}
