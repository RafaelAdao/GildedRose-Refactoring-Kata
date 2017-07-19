package com.gildedrose;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

public class GildedRoseGoldenMasterTesting {
	
	public static final Integer FIXED_SEED = 1;
	public static final Integer NUMBER_OF_ITEMS = 5000;
	public static final Integer MAX_SELLIN = 10;
	public static final Integer MIN_SELLIN = -10;
	public static final Integer MAX_QUALITY = 50;
	public static final Integer MIN_QUALITY = 0;
	
	public static final String[] ITEMS_NAME = {
		"Standard Item",
		"Aged Brie",
		"Backstage passes to a TAFKAL80ETC concert",
		"Sulfuras, Hand of Ragnaros"
	};
	
	private Random random = new Random(FIXED_SEED);

	@Test
	public void generateGoldenMaster() throws IOException {
		Path path = Paths.get("goldenMaste.txt");
		Item[] items = generateRandomItems(NUMBER_OF_ITEMS);
		GildedRose app = new GildedRose(items);
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			app.updateQuality();
			for (Item item : items) {
				writer.write(item.toString()+"\n");
			}
		}
		assertEquals(new File("goldenMaster.txt").toString(), new File("goldenMaste.txt").toString());
	}
	
	private Item[] generateRandomItems(Integer numberOfItems ) {
		Item[] items = new Item[numberOfItems];
		
		for (int i = 0; i < numberOfItems; i++) {
			items[i] = randomItem();
		}
		
		return items;
	}

	private Item randomItem() {
		return new Item(randomName(), randomSellIn(), randomQuality());
	}

	private int randomQuality() {
		return random.nextInt(MAX_QUALITY - MIN_QUALITY) + MIN_QUALITY;
	}

	private int randomSellIn() {
		return random.nextInt(MAX_SELLIN - MIN_SELLIN) + MIN_SELLIN;
	}

	private String randomName() {
		return ITEMS_NAME[0 + random.nextInt(ITEMS_NAME.length)];
	}
}
