package bioSearcher;

public class BSCharacter extends BSDataStorage {
	private BSOption[] option = new BSOption[2];
	
	public BSCharacter(int id){
		super(id);
		
		switch(id){
			case 21:
					this.name = "Size";
					this.option[0] = new BSOption(31);
					this.option[1] = new BSOption(32);
				break;
			case 22:
					this.name = "Color";
					this.option[0] = new BSOption(33);
					this.option[1] = new BSOption(34);
				break;
			case 23:
					this.name = "Form";
					this.option[0] = new BSOption(35);
					this.option[1] = new BSOption(36);
				break;
			case 24:
					this.name = "Habitat";
					this.option[0] = new BSOption(37);
					this.option[1] = new BSOption(38);
				break;
			default:
					this.name = "Character undefined.";
				break;
			
		}
	}
}
