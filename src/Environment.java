public class Environment{

    private static int defaultHouseSize = 2;
    private Room[][] house;
    
    public Environment(){	
	System.out.println("Environment default initialisation");
	house = new Room[defaultHouseSize][defaultHouseSize];
	for(int i = 0; i < defaultHouseSize; i++){
	    for(int j = 0; j < defaultHouseSize; j++){
		house[i][j] = new Room();
	    }
	}
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

    public void GetHouse(){
	System.out.println("GetHouse");
    }

    public void GetCleanerPosition(){
	System.out.println("GetCleanerPosition");
    }
}
