public class Main {
    public static void main(String[] args) {
        // Criando instâncias das contas
        ContaCorrente contaCorrente = new ContaCorrente(123);
        ContaPoupanca contaPoupanca = new ContaPoupanca(456);

        // Depositando valores nas contas
        contaCorrente.depositar(1000);
        contaPoupanca.depositar(500);

        // Exibindo os saldos antes da transferência
        System.out.println("Saldo Conta Corrente: " + contaCorrente.getSaldo());
        System.out.println("Saldo Conta Poupança: " + contaPoupanca.getSaldo());

        // Transferindo 200 da Conta Corrente para a Conta Poupança
        contaCorrente.transferir(200, contaPoupanca);

        // Exibindo os saldos após a transferência
        System.out.println("Saldo Conta Corrente após transferência: " + contaCorrente.getSaldo());
        System.out.println("Saldo Conta Poupança após transferência: " + contaPoupanca.getSaldo());
    }
}
