package br.com.consultorio.entity;

public enum StatusAgendamento  {

    pendente("Pendente"),
    aprovado("Aprovado"),
    rejeitado("Rejeitado"),
    cancelado("Cancelado"),
    compareceu("Compareceu"),
    nao_compareceu("Não Compareceu");

    public final String valor;

    private StatusAgendamento(String valor){
        this.valor = valor;
    }

}
