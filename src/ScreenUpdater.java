import java.io.IOException;
import java.io.File;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
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
		ie.printStackTrace();
	    }
	}
    }
    
}

class MyCanvas extends JComponent{

    private static double imgWidth = 804;
    private static double imgHeight = 608;
    private static BufferedImage imgDirt = LoadImage("images/dirt.png");
    private static BufferedImage imgJewel = LoadImage("images/jewel.png");
    private static BufferedImage imgCleaner = LoadImage("images/cleaner.png");
    private static int offset = 30;
    private float scaleX;
    private float scaleY;

    private int uiX;
    private int uiY;
    private Environment e;
    
    public MyCanvas(Environment env, int uX, int uY){
	super();
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
	DrawGrid(g2d);
	DrawDirtJewel(g2d);
	DrawImage(g2d, imgCleaner, e.GetCleanerPosition());
	repaint(1000);
    }

    
    private void DrawGrid(Graphics2D g2d){
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
    }
 
    private void DrawDirtJewel(Graphics2D g2d){
	House h = e.GetHouse();
	Boolean dirt;
	Boolean jewel;
	for(int i = 0; i < h.GetWidth(); i++){
	    for(int j = 0; j < h.GetHeight(); j++){
		dirt = h.GetRoom(i,j).IsDirt();
		jewel = h.GetRoom(i,j).IsJewel();
		if(dirt){
		    DrawImage(g2d, imgDirt, new Position(i, j));
		}
		if(jewel){
		    DrawImage(g2d, imgJewel, new Position(i, j));
		}
		
	    }
	}
    }

    
    private static BufferedImage LoadImage(String path){
	BufferedImage img = null;
	try{
	    img = ImageIO.read(new File(path));
	}catch(IOException ioe){
	    ioe.printStackTrace();
	}
	return img;
    }
    
    private void DrawImage(Graphics2D g2d, BufferedImage img, Position pos){
	AffineTransform at = new AffineTransform();
	at.scale(0.95 * (scaleX / imgWidth), 0.95 * (scaleY / imgHeight));
	g2d.drawImage(img,
		      new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR),
		      (int)(offset + 0.05 * scaleX + pos.x * scaleX),
		      (int)(offset + 0.05 * scaleY + pos.y * scaleY));
	
    }

}
