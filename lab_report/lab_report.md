# Lab Report 05
## Assignment 05 - Legacy Code - Refactoring to Patterns
_Authors: Dennis Loska, Tony Dorfmeister, Ai Dong 28.12.2017_

## Write Characterization Tests

From the given specification we´e created test scenarios for each specification. This has been done by creating various test objects that will test the given specifications. As a tool for code coverage we have used the built-in tool of IntelliJ.

After this we have increased the overall code coverage for class `GildedRose` from 33% to 83% as seen in the images below. (Unfortunately we didn´t know how to get rid of the extra packages in the images)

![codeCoverage_before](/Users/tweak/CloudStation/IMI/03_Semester/Informatik-03/labs/Lab05_GildedRose/lab_report/images/codeCoverage_before.png)

![codeCoverage_after-01](/Users/tweak/CloudStation/IMI/03_Semester/Informatik-03/labs/Lab05_GildedRose/lab_report/images/codeCoverage_after-01.png)

By adding some test objects that have already been expired in sell date we were able to bump up the method percentage to 95%. 

![codeCoverage_after-02](/Users/tweak/CloudStation/IMI/03_Semester/Informatik-03/labs/Lab05_GildedRose/lab_report/images/codeCoverage_after-02.png)

Our guess why the class coverage is only at 83% is that the main method of `GildedRose` is not tested. 

## Refactoring

##### 1. changing for-loop to foreach

- Just so the variables in the methods look more pretty

##### 2. extracting method for better readability

```java
    public static void updateQuality()
    {
        for (Item item : items) {
            updateOneItem(item);
        }
    }
```

##### 3. Three big if-else blocks extracted in 3 separate methods for now

- Makes the code **much** easier to understand
- 3 separate methods instead of a huge mess

```java
    private static void updateOneItem(Item item) {
        updateQuality(item);
        updateSellIn(item);
        if (item.getSellIn() < 0) {
            updateExpired(item);
        }
    }
```

##### 4. More extracting and changing if-conditions for better understanding (no logical changes)

- More detailed cleaning in the separate methods
- Switching conditions and inverting if-statements
- If-statements are sorted by the name-conditions for now

```java
    private static void updateQuality(Item item) {
        if ((item.getName().equals("Aged Brie"))
                || item.getName().equals("Backstage passes to a TAFKAL80ETC concert")) {
            UpdateBrieOrConcert(item);
        } else if (item.getName().equals("Sulfuras, Hand of Ragnaros")) {
            return;
        }
        if (item.getQuality() > 0) {
            item.setQuality(item.getQuality() - 1);
        }
    }
```

After more thinking:

- Changing .equals for better readability for example
- More extracting like _increaseQuality(item);_ instead of _item.setQuality(item.getQuality() - 1);_ 
```java
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
```
##### 5. Running the test-class to enshure, that all the refactoring is working

- That step has been done many times every now and then
- Tests were green most of the time until implementing the strategy pattern

##### 6. Implementing the Strategy Design Pattern

- Adding a new class ItemCategory, which will include most of the methods so the GuildedRose class is nice and clean.
- Adding children of ItemCategory for different Items, which will use the methods differently meaning overriding them with a more specific implementation for the item
- This means different operations are executed depending on the item-category
- Every item gets categorized and updated by _category.updateOneItem(item);_

```java
    public void updateQuality() {
        for (Item item : items) {
            ItemCategory category = categorize(item);
            category.updateOneItem(item);
        }
    }
```

- The category is determined by checking the item's name
- If there is no match the default _ItemCategory_ will be created


```java
    private ItemCategory categorize(Item item) {
        if (item.getName().equals("Sulfuras, Hand of Ragnaros"))
            return new Legendary();
        if (item.getName().equals("Aged Brie"))
            return new Cheese();
        if (item.getName().equals("Backstage passes to a TAFKAL80ETC concert"))
            return new BackstagePass();
        return new ItemCategory();
    }
```

- A more specific item is handled in it's respective category class
- E.g. a **legendary** item
- The necessary methods are overridden

```java
    //For all legendary items, which are treated differently in e.g. quality-calculation  
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
```

##### 7. Conclusion

- It is very easy now to implement very specific methods
- Items can be very custom now without changing the Item class
- Many if-statements are unnecessary now and got wiped out during the refactoring process
- The class **ItemCategory** acts as a wrapper for the Item class
- It would be totally fine to put some, if not all of the methods from it into the Item-class, if that would be allowed

## Add the new functionality

@Ai & Tony