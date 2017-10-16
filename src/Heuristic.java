public class Heuristic{

    public Heuristic(){
	System.out.println("Heuristic initialisation");
    }
    
    public static double eval(House house, Position pos){
    	double score = 0;
    	for(int i = 0; i < house.GetWidth(); i++){
	    for(int j = 0; j < house.GetHeight(); j++){
		Room r = house.GetRoom(i,j);
		if(r.IsDirt()){
		    score += Math.abs(pos.x - i) + Math.abs(pos.y - j);
		}
		if(r.IsJewel()){
		    score += Math.abs(pos.x - i) + Math.abs(pos.y - j) - 0.01;
		}	    
	    }
	}
		
	Room here = house.GetRoom(pos.x, pos.y);
	score = 1/score;
	if (here.IsJewel()){score *= 2;}
	if (here.IsDirt()){score *= 2;}
	//System.out.println("SCORE : " + score);
	return score;
    }
}
