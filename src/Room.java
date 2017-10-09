public class Room{

    Boolean isDirt;
    Boolean isJewel;
    
    public Room(){
	isDirt = false;
	isJewel = false;
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

}
