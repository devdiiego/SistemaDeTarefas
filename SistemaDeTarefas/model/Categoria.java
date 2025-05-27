package model;

public class Categoria extends Entidade {

    public Categoria(int id, String nome) {
        super(id, nome);
    }

    @Override
    public String toString() {
        return "Categoria [ID=" + id + ", Nome=" + nome + "]";
    }
}
