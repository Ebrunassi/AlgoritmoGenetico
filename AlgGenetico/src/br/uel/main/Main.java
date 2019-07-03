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
	
	public static int[] geraCromossomoAleatorio(LinkedList<Cromossomo> populacao, int volumeMochila) {
		int []cromossomo = new int[populacao.size()];
		Random random = new Random();
		int volumeAtual = 0;
		
		// Preenche o cromossomo
		for(int i = 0 ; i < cromossomo.length ; i++) {
			cromossomo[i] = random.nextInt(2);
			if(cromossomo[i] == 1 && populacao.get(i).getVolume() + volumeAtual < volumeMochila)
				volumeAtual = populacao.get(i).getVolume() + volumeAtual;
			
			if(volumeAtual > volumeMochila) {
				for(int j = i ; j < cromossomo.length ; j++)
					cromossomo[j] = 0;
				break;
			}
			
//			System.out.println(cromossomo[i]);
		}
		System.out.println("Volume mochila : " + volumeMochila);
		System.out.println("Volume atual : " + volumeAtual);
		return cromossomo;
	}
	
	
	
	
	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		int volumeMochila = 0;
		Random random = new Random();
		Reader reader = Files.newBufferedReader(Paths.get("/home/evandro/Downloads/item_50.csv"),
				StandardCharsets.UTF_8);
		CsvToBean<Objeto> csvToBean = new CsvToBeanBuilder(reader).withType(Objeto.class).build();

		List<Objeto> objetos = csvToBean.parse();

//		for (Objeto pessoa : objetos)
//            System.out.println(pessoa);

		volumeMochila = Integer.parseInt(objetos.get(0).getVolume());
//		System.out.println(volumeMochila);

		LinkedList<Cromossomo> populacao = new LinkedList<Cromossomo>();

		// Passando o que foi lido no CSV para uma linkedList de Cromossomos
		for (int i = 1; i < objetos.size(); i++) {
			Cromossomo c = new Cromossomo();
			c.setVolume(Integer.parseInt(objetos.get(i).getVolume()));
			c.setImportancia(Integer.parseInt(objetos.get(i).getImportancia()));
			c.setGenotipo(Cromossomo.convertBinary(c.getImportancia()));
			populacao.add(c);
//			System.out.println(i+". " +c.getVolume() + " - " + c.getImportancia());
		}
		
		
		int []cPai = geraCromossomoAleatorio(populacao,volumeMochila);
		int []cMae = geraCromossomoAleatorio(populacao,volumeMochila);
		
		int []filho = Cromossomo.crossingOver(cPai,cMae);
		
		

	}
}
