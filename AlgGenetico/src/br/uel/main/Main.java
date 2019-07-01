package br.uel.main;

import br.uel.model.Objeto;
import br.uel.model.Cromossomo;
import java.util.Random;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class Main {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		int volume = 0;
		Random random = new Random();
		Reader reader = Files.newBufferedReader(Paths.get("/home/evandro/Downloads/item_50.csv"),
				StandardCharsets.UTF_8);
		CsvToBean<Objeto> csvToBean = new CsvToBeanBuilder(reader).withType(Objeto.class).build();

		List<Objeto> objetos = csvToBean.parse();

//		for (Objeto pessoa : objetos)
//            System.out.println(pessoa);

		volume = Integer.parseInt(objetos.get(0).getVolume());
		System.out.println(volume);

		LinkedList<Cromossomo> populacao = new LinkedList<Cromossomo>();

		// Passando o que foi lido no CSV para uma linkedList de Cromossomos
		for (int i = 1; i < objetos.size(); i++) {
			Cromossomo c = new Cromossomo();
			c.setVolume(Integer.parseInt(objetos.get(i).getVolume()));
			c.setImportancia(Integer.parseInt(objetos.get(i).getImportancia()));
			c.setGenotipo(Cromossomo.convertBinary(c.getImportancia()));
			populacao.add(c);
		}
		int[] indices = Cromossomo.fitness(populacao);

		LinkedList<Cromossomo> novaGeracao = new LinkedList<Cromossomo>();
		
		novaGeracao.add(populacao.get(indices[0]));
		novaGeracao.add(populacao.get(indices[1]));

		indices = Cromossomo.fitness(novaGeracao);		
		Cromossomo.crossingOver(novaGeracao.get(indices[1]), novaGeracao.get(indices[2]));
		
		
		
		
		
		
		
		
		
		
		
		
		

	}
}
