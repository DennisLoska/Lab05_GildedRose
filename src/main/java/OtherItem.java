
public class OtherItem extends Item implements ItemCategory{
	
	    
	    public OtherItem(String name, int sellIn, int quality) {
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
    	    this.quality = this.sellIn<=0? this.quality -= 2: (this.quality - 1);
    		if (quality >50)this.setQuality(50);
        	if (quality<0) this.setQuality(0);
      }

}
