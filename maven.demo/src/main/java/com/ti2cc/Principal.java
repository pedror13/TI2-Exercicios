import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        EsporteDAO dao = new EsporteDAO();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n===== Menu =====");
            System.out.println("1. Listar esportes");
            System.out.println("2. Inserir esporte");
            System.out.println("3. Excluir esporte");
            System.out.println("4. Atualizar esporte");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    // Listar esportes
                    listarEsportes(dao);
                    break;
                case 2:
                    // Inserir esporte
                    inserirEsporte(dao, scanner);
                    break;
                case 3:
                    // Excluir esporte
                    excluirEsporte(dao, scanner);
                    break;
                case 4:
                    // Atualizar esporte
                    atualizarEsporte(dao, scanner);
                    break;
                case 5:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 5);

        scanner.close();
    }

    private static void listarEsportes(EsporteDAO dao) {
        Esporte[] esportes = dao.getEsportes();
        if (esportes != null) {
            System.out.println("\n==== Lista de Esportes ====");
            for (Esporte esporte : esportes) {
                System.out.println(esporte);
            }
        } else {
            System.out.println("Não há esportes cadastrados.");
        }
    }

    private static void inserirEsporte(EsporteDAO dao, Scanner scanner) {
        System.out.print("Digite o nome do esporte: ");
        String nome = scanner.next();
        System.out.print("Digite o número de jogadores: ");
        int numeroJogadores = scanner.nextInt();
        System.out.print("Digite a data de criação (AAAA-MM-DD): ");
        String dataCriacao = scanner.next();

        Esporte esporte = new Esporte(numeroJogadores, nome, dataCriacao);
        if (dao.inserirEsporte(esporte)) {
            System.out.println("Esporte inserido com sucesso!");
        } else {
            System.out.println("Falha ao inserir o esporte.");
        }
    }

    private static void excluirEsporte(EsporteDAO dao, Scanner scanner) {
        System.out.print("Digite o código do esporte a ser excluído: ");
        int codigo = scanner.nextInt();
        
        if (dao.excluirEsporte(codigo)) {
            System.out.println("Esporte excluído com sucesso!");
        } else {
            System.out.println("Falha ao excluir o esporte.");
        }
    }

    private static void atualizarEsporte(EsporteDAO dao, Scanner scanner) {
        System.out.print("Digite o código do esporte a ser atualizado: ");
        int codigo = scanner.nextInt();
        System.out.print("Digite o novo nome do esporte: ");
        String nome = scanner.next();
        System.out.print("Digite o novo número de jogadores: ");
        int numeroJogadores = scanner.nextInt();
        System.out.print("Digite a nodata de criação (AAAA-MM-DD): ");
        String dataCriacao = scanner.next();

        Esporte esporte = new Esporte(numeroJogadores, nome, dataCriacao);
        esporte.setCodigo(codigo);
        if (dao.atualizarEsporte(esporte)) {
            System.out.println("Esporte atualizado com sucesso!");
        } else {
            System.out.println("Falha ao atualizar o esporte.");
        }
    }
}
