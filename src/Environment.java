public class Environment{

    private House house;
    private Position agent;
    
    public Environment(int houseX, int houseY){	
	System.out.println("Environment initialisation");
	house = new House(houseX, houseY);
	agent = CleanerSpawn();
    }
    
    public void GenerateDirt(){
	System.out.println("GenerateDirt");
    }

    public void GenerateJewel(){
	System.out.println("GenerateJewel");
    }

    public void GetTime(){
	System.out.println("GetTime");
    }

    public House GetHouse(){
	return house;
    }

    public Position GetCleanerPosition(){
	System.out.println("GetCleanerPosition");
	return agent;
    }

    private Position CleanerSpawn(){
	Position pos = new Position();
	pos.x = (int)(Math.random() * house.GetWidth());
	pos.y = (int)(Math.random() * house.GetHeight());
	return pos;
    }
	    
}
