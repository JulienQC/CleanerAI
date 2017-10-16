public class Environment{

    private double dirtCoolDown;
    private double jewelCoolDown;
    
    private House house;
    private Position agent;
    private double startTime;
    private double lastDirt;
    private double lastJewel;
    
    public Environment(int houseX, int houseY, int dirtCD, int jewelCD){	
	System.out.println("Environment initialisation");
	startTime = System.currentTimeMillis();
	lastDirt = 0;
	lastJewel = 0;
	house = new House(houseX, houseY);
	agent = RandomPosition();
	dirtCoolDown = dirtCD;
	jewelCoolDown = jewelCD;
    }
    
    public void GenerateDirt(){
	if(GetTime() - lastDirt > dirtCoolDown){
	    System.out.println("GenerateDirt at " + GetTime());
	    Position pos = RandomPosition();
	    house.GetRoom(pos.x, pos.y).PutDirt();
	    lastDirt = GetTime();
	}	       
    }

    public void GenerateJewel(){
	if(GetTime() - lastJewel > jewelCoolDown){
	    System.out.println("GenerateJewel at " + GetTime());
	    Position pos = RandomPosition();
	    house.GetRoom(pos.x, pos.y).PutJewel();
	    lastJewel = GetTime();
	}	       
    }

    public double GetTime(){
	return System.currentTimeMillis() - startTime;
    }

    public House GetHouse(){
	return house;
    }

    public Position GetCleanerPosition(){
	return agent;
    }

    public void SetCleanerPosition(Position pos){
	agent = pos;
    }
    
    private Position RandomPosition(){
	Position pos = new Position();
	pos.x = (int)(Math.random() * house.GetWidth());
	pos.y = (int)(Math.random() * house.GetHeight());
	return pos;
    }
	    
}
