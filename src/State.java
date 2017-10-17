import java.util.LinkedList;

public class State{

    public Position position;
    public Boolean isAlive;
    public int sequenceSize;
    public LinkedList<Action> actionSequence;
    public AgentHouse house;
    public int dirts;
    public int jewels;
    
    public State(){
	System.out.println("State initialisation");
	position = null;
	sequenceSize = 999999; 
	actionSequence = new LinkedList<Action>();
	isAlive = true;
	house = null;
	dirts = 0;
	jewels = 0;
    }
}
