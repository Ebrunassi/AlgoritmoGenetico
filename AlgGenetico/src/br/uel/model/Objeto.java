package br.uel.model;

public class Objeto {
	private String item_id;
	private String volume;
	private String importancia;
	
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getImportancia() {
		return importancia;
	}
	public void setImportancia(String importancia) {
		this.importancia = importancia;
	}
	
	public String toString() {
        return "CsvPessoa{item_id='" + item_id + "\', volume=" + volume + ", importancia='" + importancia + "\'}";
    }
	
	
}
