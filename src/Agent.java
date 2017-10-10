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
	    try{
		java.lang.Thread.sleep(1000);
	    }catch(InterruptedException ie){
		ie.printStackTrace();
	    }
	}
    }
    
    public void Observe(){
	
    }

    public void UpdateState(){
	state.position = sensor.GetPosition();
    }

    public void ChooseAction(){
	state.action.action = Action.Actions.MOVE;
	state.action.movement = Action.Movements.UP;
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
