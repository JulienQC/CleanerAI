import java.util.Comparator;
import java.util.LinkedList;

public class Explorer{
    
    private LinkedList<Action> actionSequence;
    private Boolean isInformed;
    
    public Explorer(Boolean info){
	System.out.println("Explorer initialisation");
	actionSequence = new LinkedList<Action>();
	isInformed = info;
    }


    public LinkedList<Action> GetActionSequence(Position pos, AgentHouse house, int limit){
	actionSequence.clear();
	if(!isInformed){	    
	    IterativeDeepeningSearch(actionSequence, pos, house, limit);
	}else{
	    Comparator<Node> c = new Comparator<Node>(){
		    @Override
		    public int compare(Node n1, Node n2){
			return 0;
		    }
		};
	    RecursiveSearch(actionSequence, pos, house, c, limit);
	}
	return actionSequence;
    }
	    
    private void
	IterativeDeepeningSearch(LinkedList<Action> aS, Position pos,
				 AgentHouse house, int limit){
	Comparator<Node> c = new Comparator<Node>(){
		@Override
		public int compare(Node n1, Node n2){
		    return 0;
		}
	    };
	int l = 1;
	while(l < limit && !RecursiveSearch(aS, pos, house, c, l++)){}
    }


    private Boolean RecursiveSearch(LinkedList<Action> aS, Position pos,
				    AgentHouse house, Comparator<Node> c, int limit){
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
	    actionSequence = aS;
	    return false;
	}
	
	LinkedList<Node> neighbours = GetNeighbours(pos, house);
	neighbours.sort(c);
	
	for(Node n : neighbours){
	    if(extendNeighbour(aS, n.position, n.movement, house, c, limit - 1)){
		return true;
	    }
	}
	
	return false;
    }   
    
    private LinkedList<Node> GetNeighbours(Position pos, AgentHouse house){
	LinkedList<Node> neighbours = new LinkedList<Node>();
	Node n;
	if(pos.x < house.GetWidth() - 1){
	    n = new Node();
	    n.movement = Action.Movements.RIGHT;
	    n.position = new Position(pos.x + 1, pos.y);
	    n.value = house.GetDirtiness(n.position);
	    neighbours.addLast(n);
	}	
	if(pos.y < house.GetHeight() - 1){
	    n = new Node();
	    n.movement = Action.Movements.DOWN;
	    n.position = new Position(pos.x, pos.y + 1);
	    n.value = house.GetDirtiness(n.position);
	    neighbours.addLast(n);
	}
	if(pos.x > 0){
	    n = new Node();
	    n.movement = Action.Movements.LEFT;	    
	    n.position = new Position(pos.x - 1, pos.y);
	    n.value = house.GetDirtiness(n.position);
	    neighbours.addLast(n);
	}
	if(pos.y > 0){
	    n = new Node();
	    n.movement = Action.Movements.UP;
	    n.position = new Position(pos.x, pos.y - 1);
	    n.value = house.GetDirtiness(n.position);
	    neighbours.addLast(n);
	}
	
	return neighbours;
    }

    private Boolean extendNeighbour(LinkedList<Action> aS, Position nextPos, Action.Movements m,
				    AgentHouse house, Comparator<Node> c, int newLimit){
    	LinkedList nextActionSequence = new LinkedList<Action>(aS);
    	nextActionSequence.addLast(new Action(Action.Actions.MOVE, m));
    	if(RecursiveSearch(nextActionSequence, nextPos, house, c, newLimit)){
    	    return true;
    	}
    	return false;
    }

}

class Node{

    public Position position;
    public double value;
    public Action.Movements movement;
}
