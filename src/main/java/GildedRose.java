import java.util.ArrayList;
import java.util.List;


public class GildedRose {

    private static List<Item> items = null;

    /**
     * @param args
     */
    public static void main(String[] args) {

        System.out.println("OMGHAI!");

        items = new ArrayList<Item>();
        items.add(new Item("+5 Dexterity Vest", 10, 20));
        items.add(new Item("Aged Brie", 2, 0));
        items.add(new Item("Elixir of the Mongoose", 5, 7));
        items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
        items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
        items.add(new Item("Conjured Mana Cake", 3, 6));

        updateQuality();
    }

    public static void updateQuality() {
        for (Item item : items) {
            UpdateOneItem(item);
        }
    }

    private static void UpdateOneItem(Item item) {
        updateQuality(item);
        updateSellIn(item);
        if (item.getSellIn() < 0) {
            updateExpired(item);
        }
    }

    private static void updateExpired(Item item) {
        if (item.getName().equals("Aged Brie")) {
            increaseQuality(item);
        } else if (item.getName().equals("Backstage passes to a TAFKAL80ETC concert")) {
            item.setQuality(0);
        } else if (item.getName().equals("Sulfuras, Hand of Ragnaros")) {
            if (item.getQuality() > 0) {
                return;
            }
            decreaseQuality(item);
        }
    }


    private static void updateSellIn(Item item) {
        if (!item.getName().equals("Sulfuras, Hand of Ragnaros")) {
            item.setSellIn(item.getSellIn() - 1);
        }
    }

    private static void updateQuality(Item item) {
        if ((item.getName().equals("Aged Brie"))) {
            increaseQuality(item);
        } else if (item.getName().equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (item.getSellIn() < 11) {
                increaseQuality(item);
            }

            if (item.getSellIn() < 6) {
                increaseQuality(item);
            }
        } else if (item.getName().equals("Sulfuras, Hand of Ragnaros")) {//do nothing
        } else {
            decreaseQuality(item);
        }
    }

    private static void decreaseQuality(Item item) {
        if (item.getQuality() > 0) {
            item.setQuality(item.getQuality() - 1);
        }
    }

    private static void increaseQuality(Item item) {
        if (item.getQuality() < 50) {
            item.setQuality(item.getQuality() + 1);
        }
    }

    /*
     * minimal addition to ensure testability
     */
    public static void setItems(List<Item> items) {
        GildedRose.items = items;
    }

}