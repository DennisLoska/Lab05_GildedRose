
public class Legendary implements ItemCategory extends Item {
	
	    public String name;
		public int sellIn; 
	    public int quality; 
	    
	    public Legendary (String name, int sellIn, int quality) {
			this.setName(name);
			this.setSellIn(sellIn);
			this.setQuality(quality);
		}
	    
	    protected void updateSellIn() {
	    	this.sellIn -=1;
	        }   
		
	
   	protected void updateExpired() {
		//do nothing
      }

      protected void updateQuality() {
    	//do nothing
      }

}
