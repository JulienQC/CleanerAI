public class Position{
    public int x;
    public int y;

    public Position(){
	x = 0;
	y = 0;
    }
    
    public Position(int xArg, int yArg){
	x = xArg;
	y = yArg;
    }

    public String toString(){
	return "(" + x + ", " + y + ")";
    }
}
