package model;

public abstract class Entidade {
    protected int id;
    protected String nome;

    public Entidade(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Método polimórfico
    @Override
    public String toString() {
        return "Entidade [ID=" + id + ", Nome=" + nome + "]";
    }
}
