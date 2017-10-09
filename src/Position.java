public class Position{
    public int x;
    public int y;

    public Position(){
	System.out.println("Position default initialisation");
	x = 0;
	y = 0;
    }
    
    public Position(int xArg, int yArg){
	System.out.println("Position initialisation");
	x = xArg;
	y = yArg;
    }
}
