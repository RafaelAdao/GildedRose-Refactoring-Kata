package com.gildedrose;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import org.junit.Test;

public class GildedRoseTest {

	public static final int SEMENTE_FIXA = 1;
	public static final int NUMERO_DE_ITENS = 5000;
	public static final Path ARQUIVO_MASTER = Paths.get("master.txt");
	public static final Path ARQUIVO_TESTE_ATUAL = Paths.get("test-run.txt");

	public static final int MAX_SELLIN = 10;
	public static final int MIN_SELLIN = -10;
	public static final int MAX_QUALITY = 50;
	public static final int MIN_QUALITY = 0;

	public static final String[] NOME_DOS_ITENS = { "Item Qualquer", "Aged Brie",
			"Backstage passes to a TAFKAL80ETC concert", "Sulfuras, Hand of Ragnaros" };

	private Random random = new Random(SEMENTE_FIXA);
	
	public static void main(String[] args) throws IOException {
		GildedRoseTest gr = new GildedRoseTest();
		gr.gerarAmostragemDeTeste(ARQUIVO_MASTER, NUMERO_DE_ITENS);
	}
	
	@Test
	public void comparaTesteAtualComMaster() throws IOException {
		gerarAmostragemDeTeste(ARQUIVO_TESTE_ATUAL, NUMERO_DE_ITENS);
		String arquivoDoTesteAtual = lerArquivo(ARQUIVO_TESTE_ATUAL);
		String arquivoDoMaster = lerArquivo(ARQUIVO_MASTER);
		assertEquals(arquivoDoTesteAtual, arquivoDoMaster);
	}

private void gerarAmostragemDeTeste(Path caminhoDoArquivo, int numeroDeItens) throws IOException {
	Item[] itensAleatorios = gerarItensAleatorio(numeroDeItens);
	GildedRose gildedRoseApp = new GildedRose(itensAleatorios);
	gildedRoseApp.updateQuality();
	try (BufferedWriter arquivoParaEscrever = Files.newBufferedWriter(caminhoDoArquivo)) {
		for (Item item : itensAleatorios) {
			arquivoParaEscrever.write(item.toString() + "\n");
		}
	}
}

private String lerArquivo(Path caminhoDoArquivo) throws UnsupportedEncodingException, IOException {
	return new String(Files.readAllBytes(caminhoDoArquivo), "UTF-8");
}

private Item[] gerarItensAleatorio(int numeroDeItens) {
	Item[] items = new Item[numeroDeItens];

	for (int i = 0; i < numeroDeItens; i++) {
		items[i] = getItemAleatorio();
	}

	return items;
}

private Item getItemAleatorio() {
	return new Item(getNameAleatorio(), getSellInAleatorio(), getQualityAleatorio());
}

private String getNameAleatorio() {
	return NOME_DOS_ITENS[random.nextInt(NOME_DOS_ITENS.length)];
}

private int getSellInAleatorio() {
	return gerarNumeroAleatorioEntre(MIN_SELLIN, MAX_SELLIN);
}

private int getQualityAleatorio() {
	return gerarNumeroAleatorioEntre(MIN_QUALITY, MAX_QUALITY);
}

private int gerarNumeroAleatorioEntre(int minimo, int maximo) {
	return random.nextInt(maximo - minimo) + minimo;
}
}
