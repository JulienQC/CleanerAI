import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComponent;



public class ScreenUpdater implements Runnable{

    private int houseX;
    private int houseY;
    private int uiX;
    private int uiY;
    private Position pos;
    
    private JFrame window;
    private MyCanvas c;
    
    public ScreenUpdater(int hX, int hY, int uX, int uY){
	System.out.println("ScreenUpdater initialisation");	
	houseX = hX;
	houseY = hY;
	uiX = uX;
	uiY = uY;
	pos = new Position();
	InitUI();
    }

    private void InitUI() {
	window = new JFrame();
	c = new MyCanvas();
	c.Init(houseX, houseY, uiX, uiY);
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	window.setBounds(30, 30, 300, 300);
	window.getContentPane().add(c);
	window.setVisible(true);	
    }

    
    public void run(){
	Boolean loop = true;
	
	while(loop){
	    pos.y++;
	    c.Update(pos);
	    try{
		java.lang.Thread.sleep(1000);
	    }catch(InterruptedException ie){
		//catch exception
	    }
	}
    }
    
}

class MyCanvas extends JComponent{

    private int houseX;
    private int houseY;
    private int uiX;
    private int uiY;
    private Position pos;
	
    public void Init(int hX, int hY, int uX, int uY){
	System.out.println("MyCanvas intialisation");
	houseX = hX;
	houseY = hY;
	uiX = uX;
	uiY = uY;
	pos = new Position();
    }

    public void Update(Position position){
	pos = position;	
    }
    
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawLine(20, 40, 250, 40 + pos.y);
	repaint(1000);
    }
}
