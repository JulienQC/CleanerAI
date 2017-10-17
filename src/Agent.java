import java.util.LinkedList;

public class Agent implements Runnable{

    private Effector effector;
    private Sensor sensor;
    private State state;
    private Explorer explorer;
    
    public Agent(Environment e, Boolean isInformed){
	System.out.println("Agent initialisation");
	effector = new Effector(e, this);
	sensor = new Sensor(e);
	state = new State();
	explorer = new Explorer(isInformed);
	
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
	state.house = sensor.GetAgentHouse();
    }

    public void UpdateState(){
	state.dirts = 0;
	state.jewels = 0;
	Room r;
	for(int i = 0; i < state.house.GetWidth(); i++){
	    for(int j = 0; j < state.house.GetHeight(); j++){
		r = state.house.GetRoom(i,j);
		if(r.IsDirt()){
		    state.dirts++;
		}
		if(r.IsJewel()){
		    state.jewels++;
		}	    
	    }
	}
	state.house.UpdateDirtiness();
    }

    public void ChooseAction(){
	if(state.dirts == 0 || state.jewels == 0){
	    state.actionSequence = new LinkedList<Action>();
	    state.actionSequence.addLast(new Action(Action.Actions.MOVE, Action.Movements.IDLE));
	}else{
	    state.actionSequence =
		explorer.GetActionSequence(state.position, state.house, state.sequenceSize);
	}
    }

    public void Act(){
	Action nextAction;
	Boolean wait;
	while(!state.actionSequence.isEmpty()){
	    nextAction = state.actionSequence.removeFirst();
	    wait = true;
	    switch(nextAction.action){
	    case MOVE:
		if(nextAction.movement != Action.Movements.IDLE){
		    effector.Move(nextAction.movement);
		}else{
		    wait = false;
		}
		break;
	    case VACUUM:
		effector.Vacuum();
		break;
	    case PICKUP:
		effector.PickUp();
		break;
	    }
	    
	    /* let time for human eye to see the move */
	    if(wait){
		try{
		    java.lang.Thread.sleep(1000);
		}catch(InterruptedException ie){
		    ie.printStackTrace();
		}
	    }
	}
    }

    public State GetState(){
	return state;
    }
}
