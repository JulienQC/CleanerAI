public class Effector{

    
    private Environment e;
    private Agent a;
    
    public Effector(Environment env, Agent agent){
	System.out.println("Effector initialisation");
	e = env;
	a = agent;
    }
    
    public void Vacuum(){
	System.out.println("Vacuum");
	Position curPos = a.GetState().position;	
	e.GetHouse().GetRoom(curPos.x, curPos.y).Clean();
    }

    public void PickUp(){
	System.out.println("PickUp");
	Position curPos = a.GetState().position;	
	e.GetHouse().GetRoom(curPos.x, curPos.y).PickUp();
    }

    public void Move(Action.Movements m){
	Position curPos = a.GetState().position;
	//System.out.println("Agent moving " + m + " from (" + curPos.x + ", " + curPos.y + ")");
	switch(m){
	case LEFT:
	    if(curPos.x > 0){
		e.SetCleanerPosition(new Position(curPos.x - 1, curPos.y));
	    }
	    break;
	case RIGHT:
	    if(curPos.x < e.GetHouse().GetWidth() - 1){
		e.SetCleanerPosition(new Position(curPos.x + 1, curPos.y));
	    }
	    break;
	case UP:
	    if(curPos.y > 0){
		e.SetCleanerPosition(new Position(curPos.x, curPos.y - 1));
	    }
	    break;
	case DOWN:
	    if(curPos.y < e.GetHouse().GetHeight() - 1){
		e.SetCleanerPosition(new Position(curPos.x, curPos.y + 1));
	    }
	    break;
	default:
	    break;
	}
    }

}

