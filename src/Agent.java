import java.util.LinkedList;

public class Agent implements Runnable{

    private Effector effector;
    private Sensor sensor;
    private State state;
    private Explorer explorer;
    
    public Agent(Environment e){
	if(e == null){
	    System.out.println("Trying to initialise agent with empty environment");
	    System.exit(0);
	}
	effector = new Effector(e, this);
	sensor = new Sensor(e);
	state = new State();
	explorer = new Explorer();
	
    }

    public void run(){
	while(state.isAlive){
	    Observe();
	    UpdateState();
	    ChooseAction();
	    Act();
	}
    }
    
    public void Observe(){
	state.position = sensor.GetPosition();
	state.house = sensor.GetHouse();
    }

    public void UpdateState(){
	System.out.println(state.step);
	state.step++;       
	state.nbDirt = 0;
	state.nbJewel = 0;
	Room r;
	for(int i = 0; i < state.house.GetWidth(); i++){
	    for(int j = 0; j < state.house.GetHeight(); j++){
		r = state.house.GetRoom(i,j);
		if(r.IsDirt()){
		    state.nbDirt++;
		}
		if(r.IsJewel()){
		    state.nbJewel++;
		}	    
	    }
	}

	if(state.step - state.lastExploration > state.explorationFreq){
	    explorer.Explore(false, state.position, state.house, state.nbDirt + state.nbJewel);
	    if(explorer.GetPath() != null){
		state.path = explorer.GetPath();
	    }
	    state.lastExploration = state.step;
	}
    }

    public void ChooseAction(){
	state.path.toString();
	Room r = state.house.GetRoom(state.position.x, state.position.y);
	if(r.IsJewel()){
	    state.action.action = Action.Actions.PICKUP;
	} else if(r.IsDirt()){
	    state.action.action = Action.Actions.VACUUM;
	} else if(!state.path.isEmpty()){
	    state.action.action = Action.Actions.MOVE;
	    Position nextPos = state.path.removeFirst();
	    System.out.println("(" + state.position.x + ", " + state.position.y + ")" +
			       " --> (" + nextPos.x + ", " + nextPos.y + ")");
	    if(state.position.x == nextPos.x + 1){
		state.action.movement = Action.Movements.LEFT;
	    } else if(state.position.x == nextPos.x - 1){
		state.action.movement = Action.Movements.RIGHT;
	    } else if(state.position.y == nextPos.y + 1){
		state.action.movement = Action.Movements.UP;
	    } else if(state.position.y == nextPos.y - 1){
		state.action.movement = Action.Movements.DOWN;
	    } else{
		state.action.movement = Action.Movements.IDLE;
	    }
	}
	System.out.println(state.action.action + " " + state.action.movement);
    }

    public void Act(){
	switch(state.action.action){
	case MOVE:
	    effector.Move(state.action.movement);
	    break;
	case VACUUM:
	    effector.Vacuum();
	    break;
	case PICKUP:
	    effector.PickUp();
	    break;
	}
    }

    public State GetState(){
	return state;
    }
}
