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
		House house, int nbObjectives, int limit){
	actionSequence.clear();
	if(nbObjectives == 0){
	    actionSequence.addLast(new Action(Action.Actions.MOVE, Action.Movements.IDLE));
	    return;
	}

	if(!informatedAlgorithm){	    
	    IterativeDeepeningSearch(actionSequence, pos, house, nbObjectives, limit);
	}

	/*
	  else{
	    AStar(pos, house, nbObjectives);
	  }
	*/
    }

    public void
	IterativeDeepeningSearch(LinkedList<Action> aS, Position pos,
				 House house, int nbObjectives, int limit){
	int l = 1;
	House houseCpy = new House(house);
	while(l < limit && !LimitedDepthSearch(aS, pos, houseCpy, nbObjectives, l)){
	    /* reset House */
	    houseCpy = new House(house);
	    
	    l++;
	    System.out.println("limit failed " + l);
	}
    }

    public Boolean
	LimitedDepthSearch(LinkedList<Action> aS, Position pos,
			   House house, int nbObjectives, int limit){	
	if(limit == 0){
	    //System.out.println(aS + "  obj: " + nbObjectives);
	    // try{
	    // 	java.lang.Thread.sleep(100);
	    // }catch(InterruptedException ie){
	    // 	ie.printStackTrace();
	    // }
	    return false;
	}
	
	int newObj = nbObjectives;
	Room currentRoom = house.GetRoom(pos.x,pos.y);
	if(currentRoom.IsJewel()){
	    aS.addLast(new Action(Action.Actions.PICKUP, Action.Movements.IDLE));
	    currentRoom.PickUp();
	    return LimitedDepthSearch(aS, pos, house, nbObjectives - 1, limit - 1);
	}
	if(currentRoom.IsDirt()){
	    aS.addLast(new Action(Action.Actions.VACUUM, Action.Movements.IDLE));
	    currentRoom.Clean();
	    return LimitedDepthSearch(aS, pos, house, nbObjectives - 1, limit - 1);
	}

	if(newObj == 0){	    
	    actionSequence = aS;
	    return true;
	}

	
	if(pos.x < house.GetWidth() - 1){
	    if(extendNeighbour(aS, new Position(pos.x + 1, pos.y),
			       Action.Movements.RIGHT, house, newObj, limit - 1)){
		return true;
	    };
	}
	if(pos.y < house.GetHeight() - 1){
	    if(extendNeighbour(aS, new Position(pos.x, pos.y + 1),
			       Action.Movements.DOWN, house, newObj, limit - 1)){
		return true;
	    };	    
	}
	if(pos.x > 0){
	    if(extendNeighbour(aS, new Position(pos.x - 1, pos.y),
			       Action.Movements.LEFT, house, newObj, limit - 1)){
		return true;
	    };	    
	}
	if(pos.y > 0){
	    if(extendNeighbour(aS, new Position(pos.x, pos.y - 1),
			       Action.Movements.UP, house, newObj, limit - 1)){
		return true;
	    };	    
	}

	return false;
    }

    public Boolean extendNeighbour(LinkedList<Action> aS, Position nextPos, Action.Movements m,
				   House house, int newObj, int newLimit){
	LinkedList nextActionSequence = new LinkedList<Action>(aS);
	nextActionSequence.addLast(new Action(Action.Actions.MOVE, m));
	if(LimitedDepthSearch(nextActionSequence, nextPos, house, newObj, newLimit)){
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

