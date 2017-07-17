package com.gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;

public class GildedRoseTest {

    @Test
    public void standardItemQualityShouldNotBeNegative() {
    	Item item = new Item("+5 Dexterity Vest", 10, 0);
    	GildedRose app = new GildedRose(new Item[]{item});
    	app.updateQuality();
    	assertEquals(0, item.quality);
    }
    
    @Test
    public void sellInShouldDecrementBelowZero() {
    	Item item = new Item("+5 Dexterity Vest", 0, 2);
    	GildedRose app = new GildedRose(new Item[]{item});
    	app.updateQuality();
    	assertEquals(-1, item.sellIn);
    }
    
    @Test
    public void standardItemShouldDecrementSellIn() {
    	Item item = new Item("+5 Dexterity Vest", 10, 0);
    	GildedRose app = new GildedRose(new Item[]{item});
    	app.updateQuality();
    	assertEquals(9, item.sellIn);
    }
    
    @Test
    public void standardItemShouldDecrementQuality() {
    	Item item = new Item("+5 Dexterity Vest", 10, 1);
    	GildedRose app = new GildedRose(new Item[]{item});
    	app.updateQuality();
    	assertEquals(0, item.quality);
    }
    
    @Test
    public void standardItemShouldDecrementQualityTwiceWhenSellInHasPassed() {
    	Item item = new Item("+5 Dexterity Vest", -1, 10);
    	GildedRose app = new GildedRose(new Item[]{item});
    	app.updateQuality();
    	assertEquals(8, item.quality);
    }
    
    @Test
    public void agedBrieShouldDecrementSellIn() {
    	Item item = new Item("Aged Brie", 10, 0);
    	GildedRose app = new GildedRose(new Item[]{item});
    	app.updateQuality();
    	assertEquals(9, item.sellIn);
    }
    
    @Test
    public void agedBrieItemShouldIncrementQuality() {
    	Item item = new Item("Aged Brie", 2, 0);
    	GildedRose app = new GildedRose(new Item[]{item});
    	app.updateQuality();
    	assertEquals(1, item.quality);
    }
    
    @Test
    public void agedBrieItemShouldNotIncrementQualityOverFifty() {
    	Item item = new Item("Aged Brie", 2, 50);
    	GildedRose app = new GildedRose(new Item[]{item});
    	app.updateQuality();
    	assertEquals(50, item.quality);
    }
    
    @Test
    public void agedBrieItemShouldIncrementQualityByTwiceWhenSellInHasPassed() {
    	Item item = new Item("Aged Brie", 0, 10);
    	GildedRose app = new GildedRose(new Item[]{item});
    	app.updateQuality();
    	assertEquals(12, item.quality);
    }

    @Test
    public void legendaryItemShouldBeImmutable() {
    	Item item = new Item("Sulfuras, Hand of Ragnaros", 10, 20);
    	GildedRose app = new GildedRose(new Item[]{item});
    	app.updateQuality();
    	assertEquals(10, item.sellIn);
    	assertEquals(20, item.quality);
    }
    
    @Test
    public void backstagePassesQualityShouldNotBeNegative() {
    	Item item = new Item("Backstage passes to a TAFKAL80ETC concert", -10, 0);
    	GildedRose app = new GildedRose(new Item[]{item});
    	app.updateQuality();
    	assertEquals(0, item.quality);
    }
    
    @Test
    public void backstagePassesShouldDecrenentSellIn() {
    	Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 20, 0);
    	GildedRose app = new GildedRose(new Item[]{item});
    	app.updateQuality();
    	assertEquals(19, item.sellIn);
    }
    
    @Test
    public void backstagePassesShouldIncrementQuality() {
    	Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 20, 0);
    	GildedRose app = new GildedRose(new Item[]{item});
    	app.updateQuality();
    	assertEquals(1, item.quality);
    }
    
    @Test
    public void backstagePassesShouldIncrementQualityByTwoWhenSellInIsBelowTen() {
    	Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 0);
    	GildedRose app = new GildedRose(new Item[]{item});
    	app.updateQuality();
    	assertEquals(2, item.quality);
    }
    
    @Test
    public void backstagePassesShouldIncrementQualityByThreeWhenSellInIsBelowFive() {
    	Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 0);
    	GildedRose app = new GildedRose(new Item[]{item});
    	app.updateQuality();
    	assertEquals(3, item.quality);
    }
    
    @Test
    public void backstagePassesShouldDropQualityToZeroWhenSellInHasPassed() {
    	Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10);
    	GildedRose app = new GildedRose(new Item[]{item});
    	app.updateQuality();
    	assertEquals(0, item.quality);
    }
    
    @Test
    public void conjuredItemQualityShouldNotBeNegative() {
    	Item item = new Item("Conjured Mana Cake", -10, 0);
    	GildedRose app = new GildedRose(new Item[]{item});
    	app.updateQuality();
    	assertEquals(0, item.quality);
    }
    
    @Test
    public void conjuredItemShouldDecrementTwoTimes() {
    	Item item = new Item("Conjured Mana Cake", 10, 10);
    	GildedRose app = new GildedRose(new Item[]{item});
    	app.updateQuality();
    	assertEquals(8, item.quality);
    }
    
    @Test
    public void conjuredItemShouldDecrementFourTimesWhenSellInHasPassed() {
    	Item item = new Item("Conjured Mana Cake", 0, 10);
    	GildedRose app = new GildedRose(new Item[]{item});
    	app.updateQuality();
    	assertEquals(6, item.quality);
    }

}
