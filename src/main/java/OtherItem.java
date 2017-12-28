
public class OtherItem {
	
	  public String name;
		public int sellIn; 
	    public int quality; 
	    
	    public OtherItem(String name, int sellIn, int quality) {
			this.setName(name);
			this.setSellIn(sellIn);
			this.quality = quality >50? this.setQuality(50): this.setQuality(quality);
		}
	
	   
	  protected void updateSellIn() {
    	this.sellIn -=1;
        }   
	
      protected void updateQuality() {
    	  this.quality = this.sellIn<=0? this.quality -= 2: this.quality -= 1;
      }

}
