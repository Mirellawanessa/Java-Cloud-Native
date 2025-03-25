public class ContaCorrente extends Conta {
    public ContaCorrente(int numeroConta) {
        super(numeroConta);
    }

    @Override
    public void transferir(double valor, Conta contaDestino) {
        if (sacar(valor)) {
            contaDestino.depositar(valor);
        }
    }
}
