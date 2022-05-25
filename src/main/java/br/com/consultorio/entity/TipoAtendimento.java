package br.com.consultorio.entity;

public enum TipoAtendimento {

 particular("Particular"),
 convenio("Convênio");

 public final String valor;

 private TipoAtendimento(String valor){
  this.valor = valor;
 }

}
