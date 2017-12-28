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
        items.add(new OtherItem("+5 Dexterity Vest", 10, 20));
        items.add(new Cheese("Aged Brie", 2, 0));
        items.add(new OtherItem("Elixir of the Mongoose", 5, 7));
        items.add(new Legendary("Sulfuras, Hand of Ragnaros", 0, 80));
        items.add(new BackstagePass("Backstage passes to a TAFKAL80ETC concert", 15, 20));
        items.add(new Conjured("Conjured Mana Cake", 3, 6));

        guild.updateQuality();
    }

    public void updateQuality() {
        for (Item item : items) {
           ((ItemCategory)item).updateSellIn();
           ((ItemCategory)item).updateQuality();
           if (item.getQuality()<0) item.setQuality(0);
        }
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