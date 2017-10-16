import java.util.LinkedList;

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
	}

	/*
	  else{
	    AStar(pos, house, nbObjectives);
	  }
	*/
    }

    public void
	IterativeDeepeningSearch(LinkedList<Action> aS, Position pos,
				 House house){
	int l = 1;
	while(!LimitedDepthSearch(aS, pos, house, l++)){}
    }

    public Boolean
	LimitedDepthSearch(LinkedList<Action> aS, Position pos,
			   House house, int limit){		

	Room currentRoom = house.GetRoom(pos.x,pos.y);
	if(currentRoom.IsJewel()){
	    aS.addLast(new Action(Action.Actions.PICKUP, Action.Movements.IDLE));
	    return true;
	}
	if(currentRoom.IsDirt()){
	    aS.addLast(new Action(Action.Actions.VACUUM, Action.Movements.IDLE));
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

    public Boolean extendNeighbour(LinkedList<Action> aS, Position nextPos, Action.Movements m,
				   House house, int newLimit){
	LinkedList nextActionSequence = new LinkedList<Action>(aS);
	nextActionSequence.addLast(new Action(Action.Actions.MOVE, m));
	if(LimitedDepthSearch(nextActionSequence, nextPos, house, newLimit)){
	    return true;
	}
	return false;
    }

    /*
    public void AStar(LinkedList<Action> p, Position pos,
		      House house, int nbObjectives){
	
    }
    */
}

