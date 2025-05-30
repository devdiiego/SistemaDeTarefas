package model;

//  Abstração: Classe genérica para representar qualquer entidade com ID e nome.
// Herança: Esta classe é usada como superclasse para Colaborador.

public abstract class Entidade {
    //  Encapsulamento: Protegendo os atributos.
    protected int id;
    protected String nome;

    public Entidade(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    // Encapsulamento: Getters e setters controlam o acesso aos atributos.
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    // Polimorfismo: Sobrescrevendo o método toString().
    @Override
    public String toString() {
        return "Entidade [ID=" + id + ", Nome=" + nome + "]";
    }
}
