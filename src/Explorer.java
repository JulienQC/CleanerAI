import java.util.LinkedList;

public class Explorer{

    private LinkedList<Position> path;
    private Heuristic heuristic;
    
    public Explorer(){
	System.out.println("Explorer default initialisation");
	path = new LinkedList<Position>();
	heuristic = new Heuristic();
    }

    public LinkedList<Position> GetPath(){
	return path;
    }
    
    public void
	Explore(Boolean informatedAlgorithm, Position pos,
		House house, int nbObjectives){
	path.clear();
	if(nbObjectives == 0){
	    return;
	}
	if(!informatedAlgorithm){	    
	    IterativeDeepeningSearch(path, pos, house, nbObjectives);
	}
	/*
	  else{
	    AStar(pos, house, nbObjectives);
	  }
	*/
    }

    public void
	IterativeDeepeningSearch(LinkedList<Position> p, Position pos,
				 House house, int nbObjectives){
	Boolean loop = true;
	int limit = 1;
	
	while(loop){
	    loop = !LimitedDepthSearch(p, pos, house, nbObjectives, limit++);
	}

	System.out.println("Exploration OK");
    }

    public Boolean
	LimitedDepthSearch(LinkedList<Position> p, Position pos,
			   House house, int nbObjectives, int limit){

	if(limit == 0){
	    return false;
	}
	
	int newObj = nbObjectives;
	Room currentRoom = house.GetRoom(pos.x,pos.y);
	if(currentRoom.IsDirt()){
	    newObj--;
	}
	if(currentRoom.IsJewel()){
	    newObj--;
	}

	if(nbObjectives == 0){	    
	    path = p;
	    return true;
	}

	if(pos.x < house.GetWidth() - 1){
	    if(extendNeighbour(p, new Position(pos.x + 1, pos.y), house, newObj, limit - 1)){
		return true;
	    };
	}
	if(pos.y < house.GetHeight() - 1){
	    if(extendNeighbour(p, new Position(pos.x, pos.y + 1), house, newObj, limit - 1)){
		return true;
	    };	    
	}
	if(pos.x > 0){
	    if(extendNeighbour(p, new Position(pos.x - 1, pos.y), house, newObj, limit - 1)){
		return true;
	    };	    
	}
	if(pos.y > 0){
	    if(extendNeighbour(p, new Position(pos.x, pos.y - 1), house, newObj, limit - 1)){
		return true;
	    };	    
	}

	return false;
    }

    public Boolean extendNeighbour(LinkedList<Position> p, Position nextPos,
				   House house, int newObj, int newLimit){
	LinkedList<Position> nextPath = new LinkedList<Position>(p);
	nextPath.addLast(nextPos);
	if(LimitedDepthSearch(nextPath, nextPos, house, newObj, newLimit)){
	    return true;
	}
	return false;
    }

    /*
    public void AStar(LinkedList<Position> p, Position pos,
		      House house, int nbObjectives){
	
    }
    */
}

