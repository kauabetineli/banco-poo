package banquissimo;

public class ContaCorrente {

    private boolean especial;
    private double limite;
    private int numero;
    private double saldo;
    public final Movimentacao[] movimentacoes = new Movimentacao[100];
    private int indiceMov = 0;

    public ContaCorrente(int numero, double saldoInicial){
        this.numero = numero;
        this.saldo = saldoInicial;
        this.especial = false;
        this.limite = 0.0;
        criarMovimentacao("Deposito", 'D', saldoInicial);

    }

    public ContaCorrente(int numero, double saldoInicial, double limite){
        this.numero = numero;
        this.saldo = saldoInicial;
        this.especial = true;
        this.limite = limite;
        criarMovimentacao("Deposito", 'D', saldoInicial);
    }

    protected boolean depositar(double valor){
        if(valor > 0){
            this.saldo += valor;
            criarMovimentacao("Deposito", 'D', valor);
            return true;
        }
        return false;
    }

    private void criarMovimentacao(String descricao, char tipo, double valor){
        if(indiceMov < movimentacoes.length){
            Movimentacao novaMovimentacao = new Movimentacao(descricao, tipo, valor);
            for(int i = 0; i<=indiceMov; i++){
                if(i == indiceMov){
                    movimentacoes[i] = novaMovimentacao;

                }
            }
            indiceMov++;
        }
        else{
            System.out.println("Limite de movimentacoes atingido.");
        }
    }

    protected String emitirExtrato(){
        String extrato;
        extrato = "Extrato da Conta " + numero + "\n";
        extrato += "Saldo atual: " + saldo + "\n";
        extrato += "Movimentações:\n";
        for (int i = 0; i < indiceMov; i++) {
            extrato += movimentacoes[i].getMovimentacao() + "\n";
        }
        return extrato;
    }

    public int getNumeroConta(){

        return this.numero;
    }

    public double getSaldo(){

        return this.saldo;
    }

    protected boolean sacar(double valor){
        if(valor > 0 && getSaldo() >= valor){
            this.saldo -= valor;
            criarMovimentacao("Saque", 'S', valor);
            return true;
        }
        return false;
    }

}
