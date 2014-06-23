package bioSearcher;

public class BSOption {
	private int id;
	private String name;

	public BSOption(int newId){
		id = newId;

		switch(id){
		case 31:
			name = "big";
			break;
		case 32:
			name = "small";
			break;
		case 33:
			name = "bright";
			break;
		case 34:
			name = "dark";
			break;
		case 35:
			name = "round";
			break;
		case 36:
			name = "linear";
			break;
		case 37:
			name = "forest";
			break;
		case 38:
			name = "meadow";
			break;
		default:
			name = "Character undefined.";
			break;

		}
	}

	public int getId(){
		return id;
	}

	public String getName(){
		return name;
	}
}
