package banquissimo;

public class Banco {
    private String nome;
    private int numero;
    private final ContaCorrente[] contas = new ContaCorrente[100];
    private int indiceConta = 0; /*indice comeca em 0*/

    public Banco(String nome, int numero){
        this.nome = nome;
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public int getIndiceConta(){
        return indiceConta;
    }

    public void criarConta(double saldoInicial){
        ContaCorrente novaConta = new ContaCorrente(this.indiceConta, saldoInicial);
        this.contas[this.indiceConta] = novaConta;
        indiceConta++;
    }

    public void criarConta(double saldoInicial, double limite){
        ContaCorrente novaConta = new ContaCorrente(this.indiceConta, saldoInicial, limite);
        this.contas[this.indiceConta] = novaConta;
        indiceConta++;
    }

    public void depositar(int conta, double valor){
        ContaCorrente contaLocalizada = localizarConta(conta);

        if(contaLocalizada != null){
            if(contaLocalizada.depositar(valor)){
                System.out.println("Deposito de " + valor + " realizado com sucesso!");
            }
            else{
                System.out.println("Erro no deposito");
            }
        }
        else{
            System.out.println("Conta não localizada");
        }

    }

    public String emitirExtrato(int conta){
        ContaCorrente contaLocalizada = localizarConta(conta);
        if(contaLocalizada != null){
            return this.contas[conta].emitirExtrato();
        }

        else {
            return "Conta não localizada";
        }

    }

    private ContaCorrente localizarConta(int conta){
        for(int i = 0; i< indiceConta; i++){
            if(this.contas[i].getNumeroConta() == conta){
                return this.contas[i];
            }
        }
        return null;
    }

    public void sacar(int conta, double valor){
        ContaCorrente contaLocalizada = localizarConta(conta);
        if(contaLocalizada != null){

            if(contaLocalizada.sacar(valor)){
                System.out.println("Saque de " + valor + " realizado com sucesso!");
            }
            else{
                System.out.println("Erro no saque");
            }
        }
        else{
            System.out.println("Conta não localizada");
        }
    }

    public void transferir(int contaOrigem, int contaDestino, double valor){
        ContaCorrente origem = localizarConta(contaOrigem);
        ContaCorrente destino = localizarConta(contaDestino);

        if(origem != null && destino != null){

            if(origem.sacar(valor)){
                destino.depositar(valor);
                System.out.println("Transferência de " + valor + " realizada com sucesso!");
            }
            else{
                System.out.println("Erro na transferência");
            }

        }

        else if(origem == null && destino != null){
            System.out.println("Nao foi possivel localizar a conta origem");
        }

        else if(origem != null && destino == null){
            System.out.println("Nao foi possivel localizar a conta destino");
        }

        else{
            System.out.println("Nao foi possivel localizar ambas as contas");
        }

    }




}
