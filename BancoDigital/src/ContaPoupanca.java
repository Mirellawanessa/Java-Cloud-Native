public class ContaPoupanca extends Conta {
    public ContaPoupanca(int numeroConta) {
        super(numeroConta);
    }

    @Override
    public void transferir(double valor, Conta contaDestino) {
        if (sacar(valor)) {
            contaDestino.depositar(valor);
        }
    }
}
