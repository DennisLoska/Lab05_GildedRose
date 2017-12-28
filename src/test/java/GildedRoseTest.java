import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class GildedRoseTest {

	@Parameters
	public static Collection<	Object[]> data() {
		Collection<Object[]> data = new ArrayList<Object[]>();
		data.addAll(Arrays
				.asList(new Object[][] { {
						"At the end of each day our system lowers both quality and sell-in for every item",
						"Item with arbitrary name", 5, 49, 4, 48 } }));
		return data;
	}

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
		GildedRose.setItems(items);
	}

	@Test
	public void testQualityUpdate() {
		GildedRose.updateQuality();
		assertEquals(message + " Quality ", expectedQuality, item.getQuality());
	}

	@Test
	public void testSellInUpdate() {
		GildedRose.updateQuality();
		assertEquals(message + " SellIn", expectedSellIn, item.getSellIn());
	}

}