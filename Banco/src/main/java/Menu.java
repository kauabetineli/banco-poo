import banquissimo.Banco;

import java.util.Scanner;

public class Menu {

    private final Banco nubank = new Banco("NuBank", 0);
    private final Scanner scanner = new Scanner(System.in);
    private double saldoInicial;
    private int conta;
    private double valor;
    private double limite;

    public String determinarOpcao(){

        return scanner.nextLine();
    }

    public void imprimirMenu(){
        System.out.println(nubank.getNome());
        System.out.println("Digite a opcao (s para sair):  ");
        System.out.println("1. Criar conta s/ limite");
        System.out.println("2. Criar conta c/ limite");
        System.out.println("3. Depositar");
        System.out.println("4. Emitir extrato");
        System.out.println("5. Sacar");
        System.out.println("6. Transferir");

    }

    public void criarContaSemLimite(){
        System.out.print("Digite o saldo inicial da conta[" + nubank.getIndiceConta() +"]: ");
        saldoInicial = scanner.nextDouble();
        scanner.nextLine();

        nubank.criarConta(saldoInicial);
    }

    public void criarContaComLimite(){
        System.out.print("Digite o saldo inicial da conta[" + nubank.getIndiceConta() +"]: ");
        saldoInicial = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Digite o limite da conta: ");
        limite = scanner.nextDouble();
        scanner.nextLine();

        nubank.criarConta(saldoInicial, limite);
    }

    public void depositar(){
        System.out.print("Digite a conta para depositar: ");
        conta = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o valor: ");
        valor = scanner.nextDouble();
        scanner.nextLine();

        nubank.depositar(conta, valor);
    }

    public void emitirExtrato(){
        System.out.print("Digite a conta para emitir extrato: ");
        conta = scanner.nextInt();
        scanner.nextLine();

        System.out.println(nubank.emitirExtrato(conta));
    }

    public void sacar(){
        System.out.print("Digite a conta para sacar: ");
        conta = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o valor: ");
        valor = scanner.nextDouble();
        scanner.nextLine();

        nubank.sacar(conta, valor);
    }

    public void transferir(){
        System.out.print("Digite a conta origem: ");
        int contaOrigem = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite a conta destino: ");
        int contaDestino = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o valor: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        nubank.transferir(contaOrigem, contaDestino, valor);
    }

    public void instruirUsuario() throws InterruptedException {
        String opcao;

        do{
            imprimirMenu();
            opcao = determinarOpcao();

            switch(opcao){

                case "1": criarContaSemLimite(); break;
                case "2": criarContaComLimite(); break;
                case "3": depositar(); break;
                case "4": emitirExtrato(); break;
                case "5": sacar(); break;
                case "6": transferir(); break;
                default: break;
            }

            Thread.sleep(500);
            System.out.println();


        } while(!(opcao.equals("s")));

        System.out.println("Fechando aplicativo NuBank");
    }


    public static void main(String[] args) throws InterruptedException {

        Menu menu = new Menu();
        menu.instruirUsuario();

    }
}