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
	while(!LimitedDepthSearch(aS, pos, house, l++)){
	}
    }

    public Boolean
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

    public Boolean extendNeighbour(LinkedList<Action> aS, Position nextPos, Action.Movements m,
				   House house, int newLimit){
	LinkedList nextActionSequence = new LinkedList<Action>(aS);
	nextActionSequence.addLast(new Action(Action.Actions.MOVE, m));
	if(LimitedDepthSearch(nextActionSequence, nextPos, house, newLimit)){
	    return true;
	}
	return false;
    }

    public void AStar(LinkedList<Action> p, Position pos,
		      House house, int nbObjectives){  
		if (p.size() > 10) {return;}   
		Room currentRoom = house.GetRoom(pos.x,pos.y);
		if(currentRoom.IsJewel()){
	    	p.addLast(new Action(Action.Actions.PICKUP, Action.Movements.IDLE));
	    	house.GetRoom(pos.x, pos.y).PickUp();
	    	return;
		}
		if(currentRoom.IsDirt()){
	    	p.addLast(new Action(Action.Actions.VACUUM, Action.Movements.IDLE));
	    	house.GetRoom(pos.x, pos.y).Clean();
	    	return;
		}
		if (nbObjectives == 0) {return;} 
		    
		float scoreMax = 0; 
		int test = 0;
		if(pos.x < house.GetWidth() - 1){
			scoreMax = Eval(house, pos.x+1, pos.y);
			test = 1;	
		}
		if(pos.y < house.GetHeight() - 1){
			float score = Eval(house, pos.x, pos.y+1);
			if (score > scoreMax) {
				scoreMax = score;
				test = 2;
			}	
		}
			if(pos.x > 0){
				float score = Eval(house, pos.x-1, pos.y);
				if (score > scoreMax) {
					scoreMax = score;
					test = 3;
				}	
			}
			if(pos.y > 0){
				float score = Eval(house, pos.x, pos.y-1);
				if (score > scoreMax) {
					scoreMax = score;
					test = 4;
				}	
			}
		if (test == 1){
			p.addLast(new Action(Action.Actions.MOVE, Action.Movements.RIGHT));
			System.out.println("TEST=1");
			AStar(p, new Position(pos.x+1, pos.y), house, nbObjectives);
		}	
		if (test == 2){
			p.addLast(new Action(Action.Actions.MOVE, Action.Movements.DOWN));
			System.out.println("TEST=2");
			AStar(p, new Position(pos.x, pos.y+1), house, nbObjectives);
		}
		if (test == 3){
			p.addLast(new Action(Action.Actions.MOVE, Action.Movements.LEFT));
			System.out.println("TEST=3");
			AStar(p, new Position(pos.x-1, pos.y), house, nbObjectives);
		}
		if (test == 4){
			p.addLast(new Action(Action.Actions.MOVE, Action.Movements.UP));
			System.out.println("TEST=4");
			AStar(p, new Position(pos.x, pos.y-1), house, nbObjectives);
		}	
    }
    
    public float Eval(House house, int x, int y){
    	float score = 0;
    	for(int i = 0; i < house.GetWidth(); i++){
	    	for(int j = 0; j < house.GetHeight(); j++){
				Room r = house.GetRoom(i,j);
				if(r.IsDirt()){
		    		score += Math.abs(x-i) + Math.abs(y-j);
				}
				if(r.IsJewel()){
		    		score += Math.abs(x-i) + Math.abs(y-j) - 0.01;
				}	    
	    	}
		}
		
		Room here = house.GetRoom(x,y);
		score = 1/score;
		if (here.IsJewel()){score *= 2;}
		if (here.IsDirt()){score *= 2;}
		System.out.println("SCORE : " + score);
		return score;
    }
}



