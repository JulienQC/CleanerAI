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
	
}
