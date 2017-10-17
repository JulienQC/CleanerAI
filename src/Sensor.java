public class Sensor{

    private Environment e;    
    
    public Sensor(Environment env){
	System.out.println("Sensor initialisation");
	e = env;
    }
    
    public double GetTime(){
	return e.GetTime();
    }

    public Position GetPosition(){
	return e.GetCleanerPosition();
    }

    public AgentHouse GetAgentHouse(){
	return new AgentHouse(e.GetHouse());
    }    

}

