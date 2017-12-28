import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class GildedRoseTest {

    @Parameters
    public static Collection<Object[]> data() {
        Collection<Object[]> data = new ArrayList<Object[]>();
        data.addAll(Arrays
                .asList(new Object[][]{
                        {"At the end of each day our system lowers both quality and sell-in for every item", "Item with arbitrary name", 5, 49, 4, 48},
                        {"Once the sell by date has passed, Quality degrades twice as fast", "old item", 0, 40, -1, 38},
                        {"The Quality of an item is never negative", "pretty bad item", 10, 0, 9, 0},
                        {"\"Aged Brie\" actually increases in Quality the older it gets", "Aged Brie", 10, 30, 9, 31},

                        // The following Test Case is going to fail because this functionality hasn´ been implemented yet
                        {"\"Backstage passes\" increases in Quality as it's SellIn value approaches; Quality increases by 2 when there are 10 days or less", "Backstage passes to a TAFKAL80ETC concert", 7, 10, 6, 12},

                        // The following Test Case is going to fail because this functionality hasn´ been implemented yet
                        {"\"Backstage passes\" increases in Quality as it's SellIn value approaches; Quality increases by 3 when there are 5 days or less", "Backstage passes to a TAFKAL80ETC concert", 2, 10, 1, 13},

                        // The following Test Case is going to fail because this functionality hasn´ been implemented yet
                        {"\"Backstage passes\" increases in Quality as it's SellIn value approaches; Quality drops to 0 after the concert", "Backstage passes to a TAFKAL80ETC concert", 1, 10, 0, 0},

                        {"The Quality of an item can never increase to more than 50", "Aged Brie", 10, 50, 9, 50},

                        // The following Test Case is going to fail because this functionality hasn´ been implemented yet
                        // One would have to check the quality param before saving into field
                        {"The Quality of an item can never be set to more than 50", "Some item too good to be true", 10, 52, 9, 50},

                        {"\"Sulfuras\", being a legendary item, never has to be sold or decreases in Quality", "Sulfuras, Hand of Ragnaros", Integer.MAX_VALUE, 50, Integer.MAX_VALUE, 50},

                        // The following Test Case is going to fail because this functionality hasn´ been implemented yet
                        // One would have to extend a new Class "Conjured" from ItemCategory that implements this functionality
                        {"\"Conjured\" items degrade in Quality twice as fast as normal items", "Conjured Mana Cake", 10, 50, 9, 48}


                }));
        return data;
    }

    GildedRose guild;
    String message;
    String itemName;
    int sellIn;
    int quality;
    int expectedSellIn;
    int expectedQuality;

    Item item;

    public GildedRoseTest(String message, String itemName, int sellIn,
                          int quality, int expectedSellIn, int expectedQuality) {
        this.message = message;
        this.itemName = itemName;
        this.sellIn = sellIn;
        this.quality = quality;
        this.expectedSellIn = expectedSellIn;
        this.expectedQuality = expectedQuality;
    }

    @Before
    public void setUp() {
        List<Item> items = new ArrayList<Item>();
        items.add(item = new Item(itemName, sellIn, quality));
        guild = new GildedRose();
        guild.setItems(items);
    }

    @Test
    public void testQualityUpdate() {
        guild.updateQuality();
        assertEquals(message + " Quality ", expectedQuality, item.getQuality());
    }

    @Test
    public void testSellInUpdate() {
        guild.updateQuality();
        assertEquals(message + " SellIn", expectedSellIn, item.getSellIn());
    }

}