public class Agent{

    private Effector effector;
    private Sensor sensor;
    private State state;
    private Explorer explorer;
    
    public Agent(){
	System.out.println("Agent default Initialisation");
	effector = new Effector();
	sensor = new Sensor();
	state = new State();
	explorer = new Explorer();
    }
    
    public void Observe(){
	System.out.println("Observe");
    }

    public void UpdateState(){
	System.out.println("UpdateState");
    }

    public void ChooseAction(){
	System.out.println("ChooseAction");
    }

    public void Act(){
	System.out.println("Act");
    }

}
