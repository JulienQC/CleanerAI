import java.text.DecimalFormat;

public class AgentHouse extends House{

    private static double jewelDirtiness = 10;
    private static double dirtDirtiness = 8;

    private double[][] dirtiness;

    public AgentHouse(House h){
	super(h);
	dirtiness = new double[super.GetWidth()][super.GetHeight()];
    }

    public double GetDirtiness(Position pos){
	return dirtiness[pos.x][pos.y];
    }
    
    public void UpdateDirtiness(){
	InitDirtiness();
	for(int i = 0; i < super.GetWidth(); i++){
	    for(int j = 0; j < super.GetHeight(); j++){
		if(super.GetRoom(i, j).IsDirt()){
		    SpreadDirtiness(i, j, dirtDirtiness);
		}
		if(super.GetRoom(i, j).IsJewel()){
		    SpreadDirtiness(i, j, jewelDirtiness);
		}
	    }
	}	    
    }
    
    public void SpreadDirtiness(int x, int y, double dirtWeight){
	for(int i = 0; i < super.GetWidth(); i++){
	    for(int j = 0; j < super.GetHeight(); j++){
		dirtiness[i][j] += dirtWeight / Math.pow(2, Math.abs(x - i) + Math.abs(y - j));
	    }
	}	
    }

    private void InitDirtiness(){
	for(int i = 0; i < super.GetWidth(); i++){
	    for(int j = 0; j < super.GetHeight(); j++){
		dirtiness[i][j] = 0;
	    }
	}
    }
    
    @Override
    public String toString(){
	DecimalFormat df = new DecimalFormat("#0.00");		
	String res = new String();
	for(int j = 0; j < super.GetHeight(); j++){
	    for(int i = 0; i < super.GetWidth(); i++){
		res += df.format(dirtiness[i][j]) + ", ";
	    }
	    res += "\n";
	}
	return res;
    }


}
