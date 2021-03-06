package com.gildedrose;

class GildedRose {
	Item[] items;

	public GildedRose(Item[] items) {
		this.items = items;
	}

	public void updateQuality() {
		for (Item item : items) {
			updateItem(item);
		}
	}

	private void updateItem(Item item) {
		updateQualityWhenHasDaysLeft(item);

		updateSellIn(item);

		updateQualityWhenSellDateHasPassed(item);

		if (item.name.equals("Conjured Mana Cake")) {
			if (item.quality > 0) {
				item.quality -= 1;
				if (item.sellIn < 0) {
					item.quality -= 1;
				}
			}
		}
	}

	private void updateQualityWhenHasDaysLeft(Item item) {
		if ((item.name.equals("Aged Brie") || item.name.equals("Backstage passes to a TAFKAL80ETC concert"))) {
			if (item.quality < 50) {
				item.quality += 1;

				if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
					if (item.sellIn < 11) {
						if (item.quality < 50) {
							item.quality += 1;
						}
					}

					if (item.sellIn < 6) {
						if (item.quality < 50) {
							item.quality += 1;
						}
					}
				}
			}
		} else {
			if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
				if (item.quality > 0) {
					item.quality -= 1;
				}
			}
		}
	}

	private void updateSellIn(Item item) {
		if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
			item.sellIn -= 1;
		}
	}

	private void updateQualityWhenSellDateHasPassed(Item item) {
		if (item.sellIn < 0) {
			if (item.name.equals("Aged Brie")) {
				if (item.quality < 50) {
					item.quality += 1;
				}
			} else {
				if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
					item.quality = 0;
				} else {
					if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
						if (item.quality > 0) {
							item.quality -= 1;
						}
					}
				}
			}
		}
	}
}