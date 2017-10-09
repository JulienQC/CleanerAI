public class Explorer{

    private Heuristic heuristic;
    
    public Explorer(){
	System.out.println("Explorer default initialisation");
	heuristic = new Heuristic();
    }

    public void Explore(){
	heuristic.function();
    }

}
