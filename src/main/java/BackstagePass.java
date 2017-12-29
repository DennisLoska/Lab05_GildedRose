

    public class BackstagePass extends Item implements ItemCategory  {
    	
    	

			public BackstagePass(String name, int sellIn, int quality) {
				super(name,sellIn,quality);
    			if (quality >50)this.setQuality(50);
    			if (quality<0) this.setQuality(0);
				
    		}
			
			@Override
			public void updateSellin() {
				this.sellIn -=1;
				
			}
    	
    	@Override
        public void updateQuality() {
        	
        	if (this.getSellIn() <= 0 ) {
        		this.quality =0;
            }
        	
        	 if (this.getSellIn() > 10) {
             	this.quality +=1;
             }
        	
        	if  (this.getSellIn() <= 10 && this.getSellIn() >= 6) {
            	this.quality +=2;
            }
        	
            if  (this.getSellIn() <= 5 && this.getSellIn() > 0) {
            	this.quality +=3;
            }
            
        	if (quality >50)this.setQuality(50);
        	if (quality<0) this.setQuality(0);
           
        }

		

    }