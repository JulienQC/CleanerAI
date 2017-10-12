import java.util.LinkedList;

public class State{

    public int dirtVacuumed;
    public int jewelPicked;
    public Position position;
    public int step;
    public int lastExploration;
    public int explorationIt;
    public Boolean isAlive;
    public LinkedList<Action> actionSequence;
    public House house;
    public int nbDirt;
    public int nbJewel;
    
    public State(){
	System.out.println("State initialisation");
	step = 0;
	lastExploration = 0;
	dirtVacuumed = 0;
	jewelPicked = 0;
	position = null;
	explorationIt = 20; /* no max actionSequence size at first */
	isAlive = true;
	actionSequence = new LinkedList<Action>();
	nbDirt = 0;
	nbJewel = 0;	
    }
}
