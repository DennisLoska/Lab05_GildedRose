

    public class BackstagePass implements ItemCategory extends Item {
    	
    	  public String name;
    		public int sellIn; 
    	    public int quality; 
    	    
    	    public BackstagePass(String name, int sellIn, int quality) {
    			this.setName(name);
    			this.setSellIn(sellIn);
    			this.quality = quality >50? this.setQuality(50): this.setQuality(quality);
    		}
       
    	    
    	protected void updateSellIn() {
        	this.sellIn -=1;
            }   

        protected void updateQuality() {
        	
        	if (this.sellIn() <= 0 ) {
        		this.quality =0;
            }
        	
        	 if (this.sellIn() >= 11) {
             	this.quality -=1;
             }
        	
        	if (this.sellIn() < 11) {
            	this.quality +=2;
            }
        	
            if (this.sellIn() < 6) {
            	this.quality +=3;
            }
           
        }

    }