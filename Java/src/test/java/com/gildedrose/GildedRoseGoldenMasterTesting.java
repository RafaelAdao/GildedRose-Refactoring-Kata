package com.gildedrose;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
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
	public static final String MASTER_SAMPLE_FILE_NAME = "goldenMasterSample.txt";
	public static final String CURRENT_SAMPLE_FILE_NAME = "currentSample.txt";

	public static final Integer MAX_SELLIN = 10;
	public static final Integer MIN_SELLIN = -10;
	public static final Integer MAX_QUALITY = 50;
	public static final Integer MIN_QUALITY = 0;

	public static final String[] ITEMS_NAME = { "Standard Item", "Aged Brie",
			"Backstage passes to a TAFKAL80ETC concert", "Sulfuras, Hand of Ragnaros" };

	private Random random = new Random(FIXED_SEED);

	@Test
	public void generateGoldenMaster() throws IOException {
		Item[] randomItems = generateRandomItems(NUMBER_OF_ITEMS);
		GildedRose gildedRoseApp = new GildedRose(randomItems);
		gildedRoseApp.updateQuality();
		generateSampleFileFrom(randomItems);
		String currentSampleFile = openFileAsString(CURRENT_SAMPLE_FILE_NAME);
		String goldenSampleFile = openFileAsString(MASTER_SAMPLE_FILE_NAME);
		assertEquals(currentSampleFile, goldenSampleFile);
	}

	@Test
	public void generateGoldenMasterLineByLineAlternative() throws IOException {
		Item[] randomItems = generateRandomItems(NUMBER_OF_ITEMS);
		GildedRose gildedRoseApp = new GildedRose(randomItems);
		gildedRoseApp.updateQuality();
		generateSampleFileFrom(randomItems);
		compareFileLineByLine();
	}

	private void compareFileLineByLine() throws IOException {
		try (BufferedReader currentSampleReader = Files.newBufferedReader(Paths.get(CURRENT_SAMPLE_FILE_NAME));
				BufferedReader masterSampleReader = Files.newBufferedReader(Paths.get(MASTER_SAMPLE_FILE_NAME));) {
			while (true) {
				String lineFromCurrentSample = currentSampleReader.readLine();
				String lineFromMasterSample = masterSampleReader.readLine();
				if (lineFromMasterSample == null && lineFromCurrentSample == null)
					break;
				assertEquals(lineFromMasterSample, lineFromCurrentSample);
			}
		}
	}

	private void generateSampleFileFrom(Item[] items) throws IOException {
		Path outputSamepleFilePath = Paths.get(CURRENT_SAMPLE_FILE_NAME);
		try (BufferedWriter writer = Files.newBufferedWriter(outputSamepleFilePath)) {
			for (Item item : items) {
				writer.write(item.toString() + "\n");
			}
		}
	}

	private String openFileAsString(String fileName) throws UnsupportedEncodingException, IOException {
		return new String(Files.readAllBytes(Paths.get(fileName)), "UTF-8");
	}

	private Item[] generateRandomItems(Integer numberOfItems) {
		Item[] items = new Item[numberOfItems];

		for (int i = 0; i < numberOfItems; i++) {
			items[i] = randomItem();
		}

		return items;
	}

	private Item randomItem() {
		return new Item(randomName(), randomSellIn(), randomQuality());
	}

	private String randomName() {
		return ITEMS_NAME[0 + random.nextInt(ITEMS_NAME.length)];
	}

	private int randomSellIn() {
		return generateRandomNumberBetween(MIN_SELLIN, MAX_SELLIN);
	}

	private int randomQuality() {
		return generateRandomNumberBetween(MIN_QUALITY, MAX_QUALITY);
	}

	private int generateRandomNumberBetween(Integer minimunNumber, Integer maximunNumber) {
		return random.nextInt(maximunNumber - minimunNumber) + minimunNumber;
	}
}
