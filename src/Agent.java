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
    }

    public void ChooseAction(){
	if(state.dirts == 0 || state.jewels == 0){
	    state.actionSequence = new LinkedList<Action>();
	    state.actionSequence.addLast(new Action(Action.Actions.MOVE, Action.Movements.IDLE));
	}else{
	    explorer.Explore(true, state.position, state.house);
	    state.actionSequence = explorer.GetActionSequence();
	}
    }

    public void Act(){
	Action nextAction;
	while(!state.actionSequence.isEmpty()){
	    nextAction = state.actionSequence.removeFirst();
	    switch(nextAction.action){
	    case MOVE:
		effector.Move(nextAction.movement);
		break;
	    case VACUUM:
		effector.Vacuum();
		break;
	    case PICKUP:
		effector.PickUp();
		break;
	    }
	    
	    /* let time for human eye to see the move */
	    try{
		java.lang.Thread.sleep(1000);
	    }catch(InterruptedException ie){
		ie.printStackTrace();
	    }
	}
    }

    public State GetState(){
	return state;
    }
}
