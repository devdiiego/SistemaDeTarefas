package model;

import java.util.*;

import model.Categoria;
import model.Colaborador;
import model.Tarefa;

public class SistemaTarefas {
    private static Scanner sc = new Scanner(System.in);
    private static List<Categoria> categorias = new ArrayList<>();
    private static List<Colaborador> colaboradores = new ArrayList<>();
    private static List<Tarefa> tarefas = new ArrayList<>();

    private static int idCategoria = 1;
    private static int idColaborador = 1;
    private static int idTarefa = 1;

    public static void menu() {
        System.out.println("\n===== Sistema de Controle de Tarefas =====");
        System.out.println("1 - Cadastrar Categoria");
        System.out.println("2 - Cadastrar Colaborador");
        System.out.println("3 - Cadastrar Tarefa");
        System.out.println("4 - Associar Tarefa a Colaborador");
        System.out.println("5 - Alterar Status da Tarefa");
        System.out.println("6 - Listar Categorias");
        System.out.println("7 - Listar Colaboradores");
        System.out.println("8 - Listar Tarefas com Filtros");
        System.out.println("9 - Excluir Tarefa");
        System.out.println("10 - Excluir Colaborador");
        System.out.println("11 - Modificar Tarefa");
        System.out.println("12 - Modificar Categoria");
        System.out.println("13 - Excluir Categoria");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    public static void main(String[] args) {
        int opcao;
        do {
            menu();
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> cadastrarCategoria();
                case 2 -> cadastrarColaborador();
                case 3 -> cadastrarTarefa();
                case 4 -> associarTarefaColaborador();
                case 5 -> alterarStatusTarefa();
                case 6 -> listarCategorias();
                case 7 -> listarColaboradores();
                case 8 -> listarTarefasComFiltros();
                case 9 -> excluirTarefa();
                case 10 -> excluirColaborador();
                case 11 -> modificarTarefa();
                case 12 -> modificarCategoria();
                case 13 -> excluirCategoria();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void modificarCategoria() {
        listarCategorias();
        System.out.print("ID da categoria para modificar: ");
        int idCat = sc.nextInt();
        sc.nextLine();

        Categoria categoria = buscarCategoriaPorId(idCat);
        if (categoria == null) {
            System.out.println("Categoria não encontrada.");
            return;
        }

        System.out.print("Novo nome da categoria: ");
        String novoNome = sc.nextLine();
        categoria.setNome(novoNome);
        System.out.println("Nome da categoria atualizado com sucesso!");
    }

    private static void excluirCategoria() {
        listarCategorias();
        System.out.print("ID da categoria para excluir: ");
        int idCat = sc.nextInt();
        sc.nextLine();

        Categoria categoria = buscarCategoriaPorId(idCat);
        if (categoria == null) {
            System.out.println("Categoria não encontrada.");
            return;
        }

        // Remover a categoria das tarefas associadas antes de excluí-la
        tarefas.forEach(t -> {
            if (t.getCategoria() != null && t.getCategoria().getId() == idCat) {
                t.setCategoria(null);
            }
        });

        categorias.remove(categoria);
        System.out.println("Categoria excluída com sucesso!");
        System.out.println("As tarefas anteriormente associadas a esta categoria agora não têm categoria definida.");
    }

    private static void cadastrarCategoria() {
        System.out.print("Nome da categoria: ");
        String nome = sc.nextLine();
        categorias.add(new Categoria(idCategoria++, nome));
        System.out.println("Categoria cadastrada com sucesso!");
    }

    private static void cadastrarColaborador() {
        System.out.print("Nome do colaborador: ");
        String nome = sc.nextLine();
        colaboradores.add(new Colaborador(idColaborador++, nome));
        System.out.println("Colaborador cadastrado com sucesso!");
    }

    private static void cadastrarTarefa() {
        System.out.print("Nome da tarefa: ");
        String descricao = sc.nextLine();
        listarCategorias();
        System.out.print("ID da categoria: ");
        int idCat = sc.nextInt();
        sc.nextLine();

        Categoria categoria = buscarCategoriaPorId(idCat);
        if (categoria == null) {
            System.out.println("Categoria não encontrada.");
            return;
        }

        tarefas.add(new Tarefa(idTarefa++, descricao, categoria));
        System.out.println("Tarefa cadastrada com sucesso!");
    }

    private static void associarTarefaColaborador() {
        listarTarefas();
        System.out.print("ID da tarefa: ");
        int idTar = sc.nextInt();

        Tarefa tarefa = buscarTarefaPorId(idTar);
        if (tarefa == null) {
            System.out.println("Tarefa não encontrada.");
            return;
        }

        listarColaboradores();
        System.out.print("ID do colaborador: ");
        int idColab = sc.nextInt();

        Colaborador colab = buscarColaboradorPorId(idColab);
        if (colab == null) {
            System.out.println("Colaborador não encontrado.");
            return;
        }

        tarefa.setColaborador(colab);
        System.out.println("Tarefa associada ao colaborador com sucesso!");
    }

    private static void alterarStatusTarefa() {
        listarTarefas();
        System.out.print("ID da tarefa: ");
        int idTar = sc.nextInt();
        sc.nextLine();

        Tarefa tarefa = buscarTarefaPorId(idTar);
        if (tarefa == null) {
            System.out.println("Tarefa não encontrada.");
            return;
        }

        System.out.println("Status atual: " + tarefa.getStatus());
        System.out.print("Novo status (Pendente / Em Andamento / Concluída): ");
        String status = sc.nextLine();

        tarefa.setStatus(status);
        System.out.println("Status atualizado com sucesso!");
    }

    private static void listarCategorias() {
        System.out.println("\n--- Categorias ---");
        categorias.forEach(System.out::println);
    }

    private static void listarColaboradores() {
        System.out.println("\n--- Colaboradores ---");
        colaboradores.forEach(System.out::println);
    }

    private static void listarTarefas() {
        System.out.println("\n--- Tarefas ---");
        tarefas.forEach(System.out::println);
    }

    private static void modificarTarefa() {
        listarTarefas();
        System.out.print("ID da tarefa para modificar: ");
        int idTar = sc.nextInt();
        sc.nextLine();

        Tarefa tarefa = buscarTarefaPorId(idTar);
        if (tarefa == null) {
            System.out.println("Tarefa não encontrada.");
            return;
        }

        System.out.println("\n--- Modificar Tarefa ---");
        System.out.println("1 - Modificar Descrição");
        System.out.println("2 - Modificar Categoria");
        System.out.println("3 - Modificar Colaborador Associado");
        System.out.print("Escolha uma opção: ");
        int opcaoModificacao = sc.nextInt();
        sc.nextLine();

        switch (opcaoModificacao) {
            case 1 -> {
                System.out.print("Nova descrição da tarefa: ");
                String novaDescricao = sc.nextLine();
                tarefa.setDescricao(novaDescricao);
                System.out.println("Descrição da tarefa atualizada com sucesso!");
            }
            case 2 -> {
                listarCategorias();
                System.out.print("Novo ID da categoria: ");
                int novoIdCat = sc.nextInt();
                sc.nextLine();
                Categoria novaCategoria = buscarCategoriaPorId(novoIdCat);
                if (novaCategoria != null) {
                    tarefa.setCategoria(novaCategoria);
                    System.out.println("Categoria da tarefa atualizada com sucesso!");
                } else {
                    System.out.println("Categoria não encontrada.");
                }
            }
            case 3 -> {
                listarColaboradores();
                System.out.print("Novo ID do colaborador (0 para remover): ");
                int novoIdColab = sc.nextInt();
                sc.nextLine();
                if (novoIdColab == 0) {
                    tarefa.setColaborador(null);
                    System.out.println("Colaborador removido da tarefa.");
                } else {
                    Colaborador novoColaborador = buscarColaboradorPorId(novoIdColab);
                    if (novoColaborador != null) {
                        tarefa.setColaborador(novoColaborador);
                        System.out.println("Colaborador da tarefa atualizado com sucesso!");
                    } else {
                        System.out.println("Colaborador não encontrado.");
                    }
                }
            }
            default -> System.out.println("Opção de modificação inválida.");
        }
    }

    private static void listarTarefasComFiltros() {
        System.out.println("Filtrar por:");
        System.out.println("1 - Colaborador");
        System.out.println("2 - Categoria");
        System.out.println("3 - Status");
        System.out.println("4 - Sem filtro (listar todas)");
        System.out.print("Escolha: ");
        int op = sc.nextInt();
        sc.nextLine();

        switch (op) {
            case 1 -> {
                listarColaboradores();
                System.out.print("ID do colaborador: ");
                int idColab = sc.nextInt();
                tarefas.stream()
                        .filter(t -> t.getColaborador() != null && t.getColaborador().getId() == idColab)
                        .forEach(System.out::println);
            }
            case 2 -> {
                listarCategorias();
                System.out.print("ID da categoria: ");
                int idCat = sc.nextInt();
                tarefas.stream()
                        .filter(t -> t.getCategoria().getId() == idCat)
                        .forEach(System.out::println);
            }
            case 3 -> {
                System.out.print("Digite o status (Pendente / Em Andamento / Concluída): ");
                String status = sc.nextLine();
                tarefas.stream()
                        .filter(t -> t.getStatus().equalsIgnoreCase(status))
                        .forEach(System.out::println);
            }
            case 4 -> listarTarefas();
            default -> System.out.println("Opção inválida.");
        }
    }

    private static void excluirTarefa() {
        listarTarefas();
        System.out.print("ID da tarefa para excluir: ");
        int idTar = sc.nextInt();

        Tarefa tarefa = buscarTarefaPorId(idTar);
        if (tarefa != null) {
            tarefas.remove(tarefa);
            System.out.println("Tarefa excluída com sucesso.");
        } else {
            System.out.println("Tarefa não encontrada.");
        }
    }

    private static void excluirColaborador() {
        listarColaboradores();
        System.out.print("ID do colaborador para excluir: ");
        int idColab = sc.nextInt();

        Colaborador colab = buscarColaboradorPorId(idColab);
        if (colab != null) {
            tarefas.forEach(t -> {
                if (t.getColaborador() != null && t.getColaborador().getId() == idColab) {
                    t.setColaborador(null);
                }
            });
            colaboradores.remove(colab);
            System.out.println("Colaborador excluído com sucesso.");
        } else {
            System.out.println("Colaborador não encontrado.");
        }
    }

    private static Categoria buscarCategoriaPorId(int id) {
        return categorias.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    private static Colaborador buscarColaboradorPorId(int id) {
        return colaboradores.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    private static Tarefa buscarTarefaPorId(int id) {
        return tarefas.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }
}
