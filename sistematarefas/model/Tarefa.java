package model;

public class Tarefa {
    private int id;
    private String descricao;
    private String status;
    private Categoria categoria;
    private Colaborador colaborador;

    public Tarefa(int id, String descricao, Categoria categoria) {
        this.id = id;
        this.descricao = descricao;
        this.categoria = categoria;
        this.status = "Pendente";
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    @Override
    public String toString() {
        return "Tarefa [ID=" + id +
                ", Descrição=" + descricao +
                ", Status=" + status +
                ", Categoria=" + (categoria != null ? categoria.getNome() : "Sem Categoria") +
                ", Colaborador=" + (colaborador != null ? colaborador.getNome() : "Sem Colaborador") + "]";
    }
}
