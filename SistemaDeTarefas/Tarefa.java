public class Tarefa {
    private int id;
    private String descricao;
    private Categoria categoria;
    private Colaborador colaborador;
    private String status;

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

    public Categoria getCategoria() {
        return categoria;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public String getStatus() {
        return status;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        String colab = (colaborador != null) ? colaborador.getNome() : "Não atribuído";
        return "Tarefa [ID=" + id + ", Descricao=" + descricao + ", Categoria=" + categoria.getNome() +
                ", Colaborador=" + colab + ", Status=" + status + "]";
    }
}