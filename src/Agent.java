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
    }

    public void ChooseAction(){	
	explorer.Explore(false, state.position, state.house, state.nbDirt + state.nbJewel, state.explorationIt);
	state.actionSequence = explorer.GetActionSequence();
	state.lastExploration = state.step;
    }

    public void Act(){
	Action nextAction;
	System.out.println(state.actionSequence);
	while(!state.actionSequence.isEmpty()){
	    nextAction = state.actionSequence.removeFirst();
	    System.out.println(nextAction);
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
