public class Main{

    private static int houseX = 10;
    private static int houseY = 10;
    private static int uiX = 720;
    private static int uiY = 720;

    public static void main(String args[]) {
	System.out.println("Program start");
	Environment e = new Environment(houseX, houseY);
	Agent a = new Agent();
	Runnable r = new ScreenUpdater(e, uiX, uiY);
	new Thread(r).start();
	while(true){
	    e.GenerateDirt();
	    e.GenerateJewel();
	}
    }

}
