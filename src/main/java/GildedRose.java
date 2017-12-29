import java.util.ArrayList;
import java.util.List;


public class GildedRose {

    private static List<Item> items = null;
  
    /**
     * @param args array of Strings
     */
    public static void main(String[] args) {

        System.out.println("OMGHAI!");
        GildedRose guild = new GildedRose();
        items = new ArrayList<Item>();
        items.add(new Item("+5 Dexterity Vest", 10, 20));
        items.add(new Item("Aged Brie", 2, 0));
        items.add(new Item("Elixir of the Mongoose", 5, 7));
        items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
        items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
        items.add(new Item("Conjured Mana Cake", 3, 6));
     
        guild.updateQuality();
    }

    public void updateQuality() {
    	Item temp;
    	
    	
        for (Item item : items) {
        	
        	if(!(item instanceof ItemCategory)) {
        		temp = categorize(item);
        		items.set(items.indexOf(item),temp);  
        		((ItemCategory)temp).updateSellin();
                ((ItemCategory)temp).updateQuality();
        	}
        	
        	else {
  
        	((ItemCategory)item).updateSellin();
           ((ItemCategory)item).updateQuality();
        	}
        }
    }
    
    public static Item categorize(Item i){
         Item item = i.getName().contains("Aged Brie")?  new Cheese(i.getName(), i.getSellIn(), i.getQuality()):
                        i.getName().contains("Sulfuras, Hand of Ragnaros")?  new Legendary(i.getName(), i.getSellIn(), i.getQuality()):
                            i.getName().contains("Backstage passes to a TAFKAL80ETC concert")?  new BackstagePass(i.getName(), i.getSellIn(), i.getQuality()):
                                i.getName().contains("Conjured")?  new Conjured(i.getName(), i.getSellIn(), i.getQuality()):
                                  new OtherItem(i.getName(), i.getSellIn(), i.getQuality());
             return item;
        }


    /*
     * minimal addition to ensure testability
     */
    
    protected void setItems(List<Item> items) {
        GildedRose.items = items;
    }

    /*
     Since we are not allowed to change the Item class
     we implements the Strategy design Pattern by using a different strategy
     depending on the item-type. It also has some methods, which would better fit in
     an item-class than this GildedRose class - due to this limitation we use the item class as a base class.
     */



}