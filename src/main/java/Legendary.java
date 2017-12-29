
public class Legendary  extends Item implements ItemCategory{
	
	  
	    
	    public Legendary (String name, int sellIn, int quality) {
	    	super(name,sellIn,quality);
			
		}
	    
	  
		
	@Override
   	public void updateSellin() {
		//do nothing
      }
	
      @Override
      public void updateQuality() {
    	//do nothing
      }

}
