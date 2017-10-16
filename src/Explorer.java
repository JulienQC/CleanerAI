import java.util.Comparator;
import java.util.LinkedList;
import java.text.DecimalFormat;

public class Explorer{
    
    private LinkedList<Action> actionSequence;
    private Heuristic heuristic;
    
    public Explorer(){
	System.out.println("Explorer default initialisation");
	actionSequence = new LinkedList<Action>();
	heuristic = new Heuristic();
    }

    public LinkedList<Action> GetActionSequence(){
	return actionSequence;
    }
    
    public void
	Explore(Boolean informatedAlgorithm, Position pos,
		House house){
	actionSequence.clear();
	if(!informatedAlgorithm){	    
	    IterativeDeepeningSearch(actionSequence, pos, house);
	}else{
	    GreedySearch(actionSequence, pos, house);
	}
    }

    private void
	IterativeDeepeningSearch(LinkedList<Action> aS, Position pos,
				 House house){
	int l = 1;
	while(!LimitedDepthSearch(aS, pos, house, l++)){
	}
    }

    private Boolean
	LimitedDepthSearch(LinkedList<Action> aS, Position pos,
			   House house, int limit){		

	Room currentRoom = house.GetRoom(pos.x,pos.y);
	if(currentRoom.IsJewel()){
	    aS.addLast(new Action(Action.Actions.PICKUP, Action.Movements.IDLE));
	    actionSequence = aS;
	    return true;
	}
	if(currentRoom.IsDirt()){
	    aS.addLast(new Action(Action.Actions.VACUUM, Action.Movements.IDLE));
	    actionSequence = aS;
	    return true;
	}

	if(limit == 0){
	    return false;
	}

	
	if(pos.x < house.GetWidth() - 1){
	    if(extendNeighbour(aS, new Position(pos.x + 1, pos.y),
			       Action.Movements.RIGHT, house, limit - 1)){
		return true;
	    };
	}
	if(pos.y < house.GetHeight() - 1){
	    if(extendNeighbour(aS, new Position(pos.x, pos.y + 1),
			       Action.Movements.DOWN, house, limit - 1)){
		return true;
	    };	    
	}
	if(pos.x > 0){
	    if(extendNeighbour(aS, new Position(pos.x - 1, pos.y),
			       Action.Movements.LEFT, house, limit - 1)){
		return true;
	    };	    
	}
	if(pos.y > 0){
	    if(extendNeighbour(aS, new Position(pos.x, pos.y - 1),
			       Action.Movements.UP, house, limit - 1)){
		return true;
	    };	    
	}

	return false;
    }

    private Boolean GreedySearch(LinkedList<Action> aS, Position pos, House house){
	Room currentRoom = house.GetRoom(pos.x,pos.y);
	if(currentRoom.IsJewel()){
	    aS.addLast(new Action(Action.Actions.PICKUP, Action.Movements.IDLE));
	    actionSequence = aS;
	    return true;
	}
	if(currentRoom.IsDirt()){
	    aS.addLast(new Action(Action.Actions.VACUUM, Action.Movements.IDLE));
	    actionSequence = aS;
	    return true;
	}

	//System.out.println(aS);
	try{
	    java.lang.Thread.sleep(1000);
	}catch(InterruptedException ie){
	    ie.printStackTrace();
	}

	DecimalFormat df = new DecimalFormat("#0.00");
	String res = new String();
	res = res + "[\n";
	for(int j = 0; j < house.GetHeight(); j++){
	    for(int i = 0; i < house.GetWidth(); i++){
		res += df.format(heuristic.eval(house, pos)) + ", ";
	    }
	    res += "\n";
	}
	res = res + "]";
	System.out.print(res);
	
	LinkedList<Node> neighbours = GetNeighbours(pos, house);
	neighbours.sort(new Comparator<Node>(){
		@Override
		public int compare(Node n1, Node n2){
		    if(n1.value < n2.value){
			return -1;
		    }
		    if(n1.value > n2.value){
			return 1;
		    }
		    return 0;
		}
	    });

	
	for(Node n : neighbours){
	    if(extendNeighbour2(aS, n.position, n.movement, house)){
		return true;
	    }
	}
	
	return false;
    }

    private Boolean extendNeighbour2(LinkedList<Action> aS, Position nextPos, Action.Movements m,
				   House house){
	LinkedList nextActionSequence = new LinkedList<Action>(aS);
	nextActionSequence.addLast(new Action(Action.Actions.MOVE, m));
	if(GreedySearch(nextActionSequence, nextPos, house)){
	    return true;
	}
	return false;
    }
    
    private Boolean extendNeighbour(LinkedList<Action> aS, Position nextPos, Action.Movements m,
    				   House house, int newLimit){
    	LinkedList nextActionSequence = new LinkedList<Action>(aS);
    	nextActionSequence.addLast(new Action(Action.Actions.MOVE, m));
    	if(LimitedDepthSearch(nextActionSequence, nextPos, house, newLimit)){
    	    return true;
    	}
    	return false;
    }


    
    private LinkedList<Node> GetNeighbours(Position pos, House house){
	LinkedList<Node> neighbours = new LinkedList<Node>();
	Node n = new Node();
	if(pos.x < house.GetWidth() - 1){
	    n.movement = Action.Movements.RIGHT;
	    n.position = new Position(pos.x + 1, pos.y);
	    n.value = heuristic.eval(house, pos);
	    neighbours.addLast(n);
	}	
	if(pos.y < house.GetHeight() - 1){
	    n.movement = Action.Movements.DOWN;
	    n.position = new Position(pos.x, pos.y + 1);
	    n.value = heuristic.eval(house, pos);
	    neighbours.addLast(n);
	}
	if(pos.x > 0){
	    n.movement = Action.Movements.LEFT;	    
	    n.position = new Position(pos.x - 1, pos.y);
	    n.value = heuristic.eval(house, pos);
	    neighbours.addLast(n);
	}
	if(pos.y > 0){
	    n.movement = Action.Movements.UP;
	    n.position = new Position(pos.x, pos.y - 1);
	    n.value = heuristic.eval(house, pos);
	    neighbours.addLast(n);
	}
	
	return neighbours;
    }

}

class Node{

    public Position position;
    public double value;
    public Action.Movements movement;

    public Node(){
	position = null;
	value = 0;
	movement = Action.Movements.IDLE;
    }
}

