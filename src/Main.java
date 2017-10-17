import java.util.LinkedList;

public class Main{

    private static int uiX = 720;
    private static int uiY = 720;

    public static void main(String args[]) {
	int houseX = 3;
	int houseY = 3;
	int dirtCD = 5000;
	int jewelCD = 10000;
	Boolean isInformed = false;
	
	LinkedList<Integer> intArgs = new LinkedList<Integer>();
	for(String arg: args){	    
	    try{
		intArgs.addLast(Integer.parseInt(arg));
	    }catch(NumberFormatException e){
		/* arg is not an integer (class file) */
	    }
	}
	if(!intArgs.isEmpty()){
	    houseX = intArgs.removeFirst();
	}
	if(!intArgs.isEmpty()){
	    houseY = intArgs.removeFirst();
	}
	if(!intArgs.isEmpty()){
	    dirtCD = intArgs.removeFirst() * 1000;
	}
	if(!intArgs.isEmpty()){
	    jewelCD = intArgs.removeFirst() * 1000;
	}
	if(!intArgs.isEmpty()){
	    if(intArgs.removeFirst() != 0){
		isInformed = true;
	    }
	}
	

	
	System.out.println("Program start:" +
			   "\nHouse dimensions: (" + houseX + ", " + houseY + ")" +
			   "\nDirt cooldown: " + (dirtCD / 1000) +
			   "\nJewel cooldown: " + (jewelCD / 1000) +
			   "\nInformed exploration: " + isInformed);
	Environment e = new Environment(houseX, houseY, dirtCD, jewelCD);

	Runnable AgentThread = new Agent(e, isInformed);
	Runnable GUIThread = new ScreenUpdater(e, uiX, uiY);
	new Thread(AgentThread).start();
	new Thread(GUIThread).start();
	
	while(true){
	    e.GenerateDirt();
	    e.GenerateJewel();
	}
    }

}
