package bioSearcher;

public class BSOption extends BSDataStorage {
	public BSOption(int newId){
		super(newId);
		
		this.id = newId;

		
		switch(id){
		case 31:
			this.name = "big";
			break;
		case 32:
			this.name = "small";
			break;
		case 33:
			this.name = "bright";
			break; 
		case 34:
			this.name = "dark";
			break;
		case 35:
			this.name = "round";
			break;
		case 36:
			this.name = "linear";
			break;
		case 37:
			this.name = "forest";
			break;
		case 38:
			this.name = "meadow";
			break;
		default:
			this.name = "Character undefined.";
			break;

		}
	}

	public int getId(){
		return this.id;
	}

	public String getName(){
		return this.name;
	}
}
