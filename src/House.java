public class House{

    private int width;
    private int height;
    private Room[][] rooms;
    
    public House(int x, int y){
	System.out.println("House initialisation (" + x + ", " + y + ")");
	if(x < 0 || y < 0){
	    System.out.println("House load incorrect");
	    System.exit(0);
	}
	width = x;
	height = y;
	rooms = new Room[width][height];
	for(int i = 0; i < width; i++){
	    for(int j = 0; j < height; j++){
		rooms[i][j] = new Room();
	    }
	}
    }

    public House(House h){
	width = h.GetWidth();
	height = h.GetHeight();
	rooms = new Room[width][height];
	for(int i = 0; i < width; i++){
	    for(int j = 0; j < height; j++){
		rooms[i][j] = new Room(h.GetRoom(i,j));
	    }
	}
    }

    
    public int GetWidth(){
	return width;
    }

    public int GetHeight(){
	return height;
    }

    public Room GetRoom(int x, int y){
	return rooms[x][y];
    }
}
