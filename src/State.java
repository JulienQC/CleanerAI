public class State{

    public int dirtVacuumed;
    public int jewelPicked;
    public Position position;
    public int explorationFreq;

    public State(){
	System.out.println("State default initialisation");
	dirtVacuumed = 0;
	jewelPicked = 0;
	position = new Position();
	explorationFreq = 0;
    }
    
    public State(int dirt, int jewel, Position pos, int expFreq){
	System.out.println("State initialisation");
	dirtVacuumed = dirt;
	jewelPicked = jewel;
	position = pos;
	explorationFreq = expFreq;
    }
}
