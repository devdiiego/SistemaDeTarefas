package model;

public class Colaborador extends Entidade {

    public Colaborador(int id, String nome) {
        super(id, nome);
    }

    @Override
    public String toString() {
        return "Colaborador [ID=" + id + ", Nome=" + nome + "]";
    }
}
