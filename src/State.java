public class State{

    public int dirtVacuumed;
    public int jewelPicked;
    public Position position;
    public int explorationFreq;
    public Boolean isAlive;
    public Action action;
    
    public State(){
	System.out.println("State default initialisation");
	dirtVacuumed = 0;
	jewelPicked = 0;
	position = new Position();
	explorationFreq = 0;
	isAlive = true;
	action = new Action();
    }
}
