import java.util.Scanner;

public class SomaInteiros {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Cria um objeto Scanner para ler a entrada do teclado
        int numero1 = scanner.nextInt();
        int numero2 = scanner.nextInt();

        // Fecha o scanner para evitar vazamento de recursos
        scanner.close();

        // Realiza a operação de soma
        int soma = numero1 + numero2;

        // Exibe o resultado
        System.out.println("A soma de " + numero1 + " e " + numero2 + " é: " + soma);
    }
}
