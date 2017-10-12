public class Action{

    public Actions action;
    public Movements movement;
    
    public enum Actions{
	MOVE,
	VACUUM,
	PICKUP;
    }

    public enum Movements{
	IDLE,
	LEFT,
	RIGHT,
	UP,
	DOWN;
    }

    public Action(){
	action = Actions.MOVE;
	movement = Movements.IDLE;
    }

    public Action(Actions a, Movements m){
	action = a;
	movement = m;
    }

    public String toString(){
	return action + " " + movement;
    }

	
}
