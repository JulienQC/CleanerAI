import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComponent;



public class ScreenUpdater implements Runnable{

    private Environment e;
    
    private JFrame window;
    private MyCanvas c;
    
    public ScreenUpdater(Environment env, int uX, int uY){
	System.out.println("ScreenUpdater initialisation");
	if(env == null){
	    System.out.println("Trying to load empty environment");
	    System.exit(0);
	}
	e = env;
	InitUI(uX, uY);
    }

    private void InitUI(int uiX, int uiY) {
	window = new JFrame();
	c = new MyCanvas(e, uiX, uiY);
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	window.setBounds(0, 0, uiX, uiY);
	window.getContentPane().add(c);
	window.setVisible(true);	
    }

    
    public void run(){
	Boolean loop = true;
	
	while(loop){
	    c.Update(e);
	    try{
		java.lang.Thread.sleep(1000);
	    }catch(InterruptedException ie){
		//catch exception
	    }
	}
    }
    
}

class MyCanvas extends JComponent{

    private static int offset = 30;
    private float scaleX;
    private float scaleY;

    private int uiX;
    private int uiY;
    Environment e;

    public MyCanvas(Environment env, int uX, int uY){
	super();
	System.out.println("MyCanvas initialisation");
	uiX = uX;
	uiY = uY;
	Update(env);
	e.GetHouse();
	scaleX = (uX - offset * 2) / e.GetHouse().GetWidth();
	scaleY = (uY - offset * 2) / e.GetHouse().GetHeight();
    }
    
    public void Update(Environment env){
	e = env;	
    }
    
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
	House h = e.GetHouse();
	
	for(int i = 0; i <= h.GetWidth(); i++){
	    g2d.drawLine(offset,
			 offset + (int)(i * scaleY),
			 uiX - offset,
			 offset + (int)(i * scaleY));
	}

	for(int j = 0; j <= h.GetHeight(); j++){
	    g2d.drawLine(offset + (int)(j * scaleX),
			 offset,
			 offset + (int)(j * scaleX),
			 uiY - offset);
	}
	
	repaint(1000);
    }
}
