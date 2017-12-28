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
        for (Item item : items) {
            ItemCategory category = categorize(item);
            category.updateItem(item);
        }
    }

    /*
     * creates a new instance of ItemCategory/specific children class
     * based on the given item's name
     */
    private ItemCategory categorize(Item item) {
        if (item.getName().equals("Sulfuras, Hand of Ragnaros"))
            return new Legendary();
        if (item.getName().equals("Aged Brie"))
            return new Cheese();
        if (item.getName().equals("Backstage passes to a TAFKAL80ETC concert"))
            return new BackstagePass();
        return new ItemCategory();
    }

    /*
     * minimal addition to ensure testability
     */
    protected void setItems(List<Item> items) {
        GildedRose.items = items;
    }

    /*
     A private class since we are not allowed to change the Item class
     It implements the Strategy design Pattern by using a different strategy
     depending on the item-type. It also has some methods, which would better fit in
     an item-class than this GildedRose class.
     */
    private class ItemCategory {
        private void updateItem(Item item) {
            updateQuality(item);
            updateSellIn(item);
            if (item.getSellIn() < 0) {
                updateExpired(item);
            }
        }

        protected void updateExpired(Item item) {
            decreaseQuality(item);
        }

        protected void updateSellIn(Item item) {
            item.setSellIn(item.getSellIn() - 1);
        }

        protected void updateQuality(Item item) {
            decreaseQuality(item);
        }

        void decreaseQuality(Item item) {
            if (item.getQuality() > 0) {
                item.setQuality(item.getQuality() - 1);
            }
        }

        void increaseQuality(Item item) {
            if (item.getQuality() < 50) {
                item.setQuality(item.getQuality() + 1);
            }
        }
    }

    private class Legendary extends ItemCategory {
        protected void updateExpired(Item item) {
            if (item.getQuality() > 0) {
                return;
            }
            decreaseQuality(item);
        }

        protected void updateSellIn(Item item) {
            //do nothing!
        }

        protected void updateQuality(Item item) {
            //do nothing!
        }
    }

    private class Cheese extends ItemCategory {
        protected void updateExpired(Item item) {
            increaseQuality(item);
        }

        protected void updateQuality(Item item) {
            increaseQuality(item);
        }
    }

    private class BackstagePass extends ItemCategory {
        protected void updateExpired(Item item) {
            item.setQuality(0);
        }

        protected void updateQuality(Item item) {
            if (item.getSellIn() < 11) {
                increaseQuality(item);
            } else if (item.getSellIn() < 6) {
                increaseQuality(item);
            } else {
                decreaseQuality(item);
            }
        }

    }
}