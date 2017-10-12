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
	Position curPos = e.GetCleanerPosition();
	e.GetHouse().GetRoom(curPos.x, curPos.y).Clean();
    }

    public void PickUp(){
	System.out.println("PickUp");
	Position curPos = e.GetCleanerPosition();
	e.GetHouse().GetRoom(curPos.x, curPos.y).PickUp();
    }

    public void Move(Action.Movements m){
	Position curPos = e.GetCleanerPosition();
	switch(m){
	case LEFT:
	    e.SetCleanerPosition(new Position(curPos.x - 1, curPos.y));
	    break;
	case RIGHT:
	    e.SetCleanerPosition(new Position(curPos.x + 1, curPos.y));
	    break;
	case UP:
	    e.SetCleanerPosition(new Position(curPos.x, curPos.y - 1));
	    break;
	case DOWN:
	    e.SetCleanerPosition(new Position(curPos.x, curPos.y + 1));
	    break;
	default:
	    break;
	}
    }
}

