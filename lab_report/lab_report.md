# Lab Report 05
## Assignment 05 - Legacy Code - Refactoring to Patterns
_Authors: Dennis Loska, Tony Dorfmeister, Ai Dong 05.12.2017_

## Write Characterization Tests

@Tony




## Refactoring

##### 1. changing for-loop to foreach

##### 2. extracting method for better readability

```java
    public static void updateQuality()
    {
        for (Item item : items) {
            UpdateOneItem(item);
        }
    }
```

##### 3. 3 if-else blocks extracting in 3 separate methods for now

```java
    private static void UpdateOneItem(Item item) {
        updateQuality(item);
        updateSellIn(item);
        if (item.getSellIn() < 0) {
            updateExpired(item);
        }
    }
```

##### 4. More extracting and changing if-conditions for better understanding (no logical changes)

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
- changing .equals for better readability for example
- more extracting like _increaseQuality(item);_ instead of _item.setQuality(item.getQuality() - 1);_ 
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

##### 6. Implementing the Strategy Design Pattern
https://www.journaldev.com/1754/strategy-design-pattern-in-java-example-tutorial

- adding a new class ItemCategory, which will include most of the methods so the GuildedRose class is nice and clean.
- adding children of ItemCategory for different Items, which will use the methods differently

```java
    public void updateQuality() {
        for (Item item : items) {
            ItemCategory category = categorize(item);
            category.updateOneItem(item);
        }
    }
```

blablub

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

blablub

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

blablub

## Add the new functionality

@Ai & Tony