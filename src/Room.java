public class Room{

    Boolean isDirt;
    Boolean isJewel;
    
    public Room(){
	isDirt = false;
	isJewel = false;
    }

    public Room(Room r){
	isDirt = r.IsDirt();
	isJewel = r.IsJewel();
    }
    
    public Room(Boolean dirt, Boolean jewel){
	isDirt = dirt;
	isJewel = jewel;
    }

    public Boolean IsDirt(){ 
	return isDirt;
    }

    public Boolean IsJewel(){
	return isJewel;
    }

    public void PutDirt(){
	isDirt = true;
    }

    public void PutJewel(){
	isJewel = true;
    }

    public void Clean(){
	isDirt = false;
	isJewel = false;
    }

    public void PickUp(){
	isJewel = false;
    }

}
