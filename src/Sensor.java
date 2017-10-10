public class Sensor{

    private Environment e;
    
    public Sensor(Environment env){
	System.out.println("Sensor default initialisation");
	e = env;
    }
    
    public void GetTime(){
	System.out.println("GetTime");
    }

    public Position GetPosition(){
	//System.out.println("GetPosition");
	return e.GetCleanerPosition();
    }

    public void IsDirt(){
	System.out.println("IsDirt");
    }

    public void IsJewel(){
	System.out.println("IsJewel");
    }

    public void GetRooms(){
	System.out.println("GetRooms");
    }    

}

