    public class Cheese implements ItemCategory extends Item  {
    	
    	  public String name;
    		public int sellIn; 
    	    public int quality; 
    	    
    	    public Cheese(String name, int sellIn, int quality) {
    			this.setName(name);
    			this.setSellIn(sellIn);
    			this.quality = quality >50? this.setQuality(50): this.setQuality(quality);
    		}
    	    
       protected void updateSellIn() {
    	    	this.sellIn -=1;
    	        }   	
  
        protected void updateQuality() {
        	this.quality +=1;
        }
    }
v