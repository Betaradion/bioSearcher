package bioSearcher;


public class BSFamily {
	private Integer id;
	private String name;
	private BSCharacter[] characters = new BSCharacter[2];
	
	public BSFamily(int newId){
		id = newId;
		
		switch(id){
			case 11:
				name = "Hunde";
				characters[0] = new BSCharacter(21);
				characters[1] = new BSCharacter(22);
				break;
			case 12: 
				name = "Fische";
				characters[0] = new BSCharacter(23);
				characters[1] = new BSCharacter(24);
				break;
			default:
				name = "Class undefined";
				break;
		}
		
	}
	
	public BSCharacter[] getCharacters() {
		return characters;
	}
	
	public Integer getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}

}
