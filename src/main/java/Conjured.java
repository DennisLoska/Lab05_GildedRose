  public class Conjured extends item implements ItemCategory {
	   
	    public String name;
		public int sellIn; 
	    public int quality; 
	    
	    public Conjured(String name, int sellIn, int quality) {
			this.setName(name);
			this.setSellIn(sellIn);
			this.quality = quality >50? this.setQuality(50): this.setQuality(quality);
		}
	  
	    protected void updateSellIn() {
	    	this.sellIn -=1;
	        }   
		        
        protected void updateQuality() {
        	this.quality = this.sellIn <= 0? this.quality -=4 : this.quality -=2;
        }
    }
    