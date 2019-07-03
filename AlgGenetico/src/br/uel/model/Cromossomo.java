package br.uel.model;

import java.util.LinkedList;

public class Cromossomo {
	private int[] genotipo = new int[7];
	private int fenotipo;
	private int volume;
	private int importancia;

	public Cromossomo(int[] c) {
		genotipo = c;
	}

	public Cromossomo() {
	}

	public int[] getGenotipo() {
		return genotipo;
	}

	public void setGenotipo(int[] genotipo) {
		this.genotipo = genotipo;
	}

	public int getFenotipo() {
		return fenotipo;
	}

	public void setFenotipo(int fenotipo) {
		this.fenotipo = fenotipo;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public int getImportancia() {
		return importancia;
	}

	public void setImportancia(int importancia) {
		this.importancia = importancia;
	}

	public static int[] convertBinary(int num) {
		int binary[] = new int[10];
		int index = 0;
		while (num > 0) {
			binary[index++] = num % 2;
			num = num / 2;
		}
		return binary;
	}
	
	public static Cromossomo crossingOver(Cromossomo c1, Cromossomo c2){
		int aux1 = c2.getGenotipo()[0];
		int aux2 = c2.getGenotipo()[1];
		int aux3 = c2.getGenotipo()[6];
		int []gen = c1.getGenotipo();
		
		gen[0] = aux1;
		gen[1] = aux2;
		gen[6] = aux3;
		c1.setGenotipo(gen);

		
		return c1;
	}
	
	// Retorna o índice dos dois elementos de maior importancia
	public static int[] fitness(LinkedList<Cromossomo> populacao) {
		int maior1 = populacao.get(0).getImportancia();
		int maior2 = populacao.get(1).getImportancia();
		int ind1 = 0;
		int ind2 = 1;
		int i = 0;
		int []ind = new int[2];
		System.out.println(maior1);
		System.out.println(maior2);
		for(Cromossomo c : populacao) {
			if(maior1 < c.getImportancia() && maior1 > maior2) {
				System.out.println("1.");
				ind2 = ind1;
				ind1 = i;
				maior2 = maior1;
				maior1 = c.getImportancia();
			}
			else if(maior2 > maior1) {
				System.out.println("2.");
				int aux = maior1;
				maior1 = maior2;
				maior2 = aux;
				aux = ind1;
				ind1 = ind2;
				ind2 = aux;
			}
			
			i++;
		}
		ind[0] = ind1;
		ind[1] = ind2;
//		System.out.println(maior1 + "," + maior2);
//		System.out.println(populacao.get(ind1).getVolume() + "," + populacao.get(ind2).getVolume());
		return ind;
	}
}
