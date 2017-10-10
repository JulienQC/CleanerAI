import java.util.LinkedList;

public class State{

    public int dirtVacuumed;
    public int jewelPicked;
    public Position position;
    public int step;
    public int lastExploration;
    public int explorationFreq;
    public Boolean isAlive;
    public LinkedList<Position> path;
    public House house;
    public int nbDirt;
    public int nbJewel;
    public Action action;
    
    public State(){
	System.out.println("State initialisation");
	step = 0;
	lastExploration = 0;
	dirtVacuumed = 0;
	jewelPicked = 0;
	position = null;
	explorationFreq = 0;
	isAlive = true;
	path = new LinkedList<Position>();
	nbDirt = 0;
	nbJewel = 0;
	action = new Action();
    }
}
