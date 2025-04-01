package candidatura;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

class Habilidade {
    private String nome;

    public Habilidade(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}

class Candidato {
    private String nome;
    private String email;
    private double salarioPretendido;
    private boolean selecionado;
    private boolean contatoEfetuado;
    private List<Habilidade> habilidades;

    public Candidato(String nome, String email, double salarioPretendido) {
        this.nome = nome;
        this.email = email;
        this.salarioPretendido = salarioPretendido;
        this.selecionado = false;
        this.contatoEfetuado = false;
        this.habilidades = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public double getSalarioPretendido() {
        return salarioPretendido;
    }

    public boolean isSelecionado() {
        return selecionado;
    }

    public void selecionar() {
        selecionado = true;
    }

    public boolean isContatoEfetuado() {
        return contatoEfetuado;
    }

    public void efetuarContato() {
        contatoEfetuado = true;
    }

    public void adicionarHabilidade(Habilidade habilidade) {
        habilidades.add(habilidade);
    }

    public List<Habilidade> getHabilidades() {
        return habilidades;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", E-mail: " + email + ", Salário Pretendido: " + salarioPretendido
                + ", Habilidades: " + habilidadesToString() + ", Selecionado: " + (selecionado ? "Sim" : "Não")
                + ", Contato Efetuado: " + (contatoEfetuado ? "Sim" : "Não");
    }

    private String habilidadesToString() {
        StringBuilder habilidadesStr = new StringBuilder();
        for (Habilidade habilidade : habilidades) {
            habilidadesStr.append(habilidade.getNome()).append(", ");
        }
        if (habilidadesStr.length() > 2) {
            habilidadesStr.setLength(habilidadesStr.length() - 2); // Remover a última vírgula e espaço
        }
        return habilidadesStr.toString();
    }
}

public class ProcessoSeletivo {
    public static void main(String[] args) {
        System.out.println("Processo seletivo");

        // Criação das habilidades
        Habilidade habilidadeJava = new Habilidade("Java");
        Habilidade habilidadePython = new Habilidade("Python");
        Habilidade habilidadeCSharp = new Habilidade("C#");

        Candidato candidato1 = new Candidato("Maria", "maria@email.com", 1400.0);
        candidato1.adicionarHabilidade(habilidadeJava);
        candidato1.adicionarHabilidade(habilidadePython);

        Candidato candidato2 = new Candidato("João", "joao@email.com", 2200.0);
        candidato2.adicionarHabilidade(habilidadeJava);
        candidato2.adicionarHabilidade(habilidadeCSharp);

        Candidato candidato3 = new Candidato("Ana", "ana@email.com", 2000.0);
        candidato3.adicionarHabilidade(habilidadePython);
        candidato3.adicionarHabilidade(habilidadeCSharp);

        Candidato candidato4 = new Candidato("Pedro", "pedro@email.com", 1800.0);
        candidato4.adicionarHabilidade(habilidadeJava);

        Candidato candidato5 = new Candidato("Clara", "clara@email.com", 2400.0);
        candidato5.adicionarHabilidade(habilidadePython);

        Candidato candidato6 = new Candidato("Lucas", "lucas@email.com", 2100.0);
        candidato6.adicionarHabilidade(habilidadeCSharp);

        analisarCandidato(candidato1);
        analisarCandidato(candidato2);
        analisarCandidato(candidato3);
        analisarCandidato(candidato4);
        analisarCandidato(candidato5);
        analisarCandidato(candidato6);

        System.out.println("\n-- Seleção de candidatos --");
        selecaoCandidatos(candidato1, candidato2, candidato3, candidato4, candidato5, candidato6);

        System.out.println("\n-- Imprimir Selecionados --");
        imprimirSelecionados(candidato1, candidato2, candidato3, candidato4, candidato5, candidato6);

        Candidato[] candidatos = {
                candidato1,
                candidato2,
                candidato3,
                candidato4,
                candidato5,
                candidato6,
                // Adicione mais candidatos aqui conforme necessário
        };

        for (Candidato candidato : candidatos) {
            entrandoEmContato(candidato);
        }
    }

    static void analisarCandidato(Candidato candidato) {
        double salarioBase = 2000.0;
        if (salarioBase > candidato.getSalarioPretendido()) {
            System.out.println("LIGAR PARA O CANDIDATO: " + candidato.getNome() + " (Habilidades: "
                    + habilidadesToString(candidato) + ")");
        } else if (salarioBase == candidato.getSalarioPretendido()) {
            System.out.println("LIGAR PARA O CANDIDATO COM CONTRAPROPOSTA: " + candidato.getNome() + " (Habilidades: "
                    + habilidadesToString(candidato) + ")");
        } else {
            System.out.println("AGUARDANDO DEMAIS CANDIDATOS: " + candidato.getNome() + " (Habilidades: "
                    + habilidadesToString(candidato) + ")");
        }
    }

    static void selecaoCandidatos(Candidato... candidatos) {
        int candidatosSelecionados = 0;
        double salarioBase = 2000.0;

        for (Candidato candidato : candidatos) {
            System.out.println(
                    "Candidato: " + candidato.getNome() + " - Salário Pretendido: " + candidato.getSalarioPretendido()
                            + " (Habilidades: " + habilidadesToString(candidato) + ")");

            if (salarioBase >= candidato.getSalarioPretendido()) {
                candidato.selecionar();
                System.out.println(candidato.getNome() + " selecionado para a vaga!");
                candidatosSelecionados++;
            }
        }
    }

    static String habilidadesToString(Candidato candidato) {
        StringBuilder habilidadesStr = new StringBuilder();
        List<Habilidade> habilidades = candidato.getHabilidades();
        for (Habilidade habilidade : habilidades) {
            habilidadesStr.append(habilidade.getNome()).append(", ");
        }
        if (habilidadesStr.length() > 2) {
            habilidadesStr.setLength(habilidadesStr.length() - 2); // Remover a última vírgula e espaço
        }
        return habilidadesStr.toString();
    }

    static void imprimirSelecionados(Candidato... candidatos) {
        System.out.println("Imprimindo a lista de candidatos selecionados:");

        for (Candidato candidato : candidatos) {
            if (candidato.isSelecionado()) {
                System.out.println(candidato.getNome());
            }
        }
    }

    static boolean atender() {
        return new Random().nextInt(3) == 1;
    }

    static void entrandoEmContato(Candidato candidato) {
        int tentativasRealizadas = 1;
        boolean continuaTentando = true;
        boolean atendeu = false;

        do {
            atendeu = atender();
            continuaTentando = !atendeu;
            if (continuaTentando) {
                tentativasRealizadas++;
            } else {
                System.out.println("Contato realizado com sucesso com " + candidato.getNome() + "!");
                candidato.efetuarContato();
            }
        } while (continuaTentando && tentativasRealizadas < 3);

        if (atendeu) {
            System.out.println("Conseguimos o contato com " + candidato.getNome() + " com " + tentativasRealizadas
                    + " tentativas!");
        } else {
            System.out.println("NÃO conseguimos o contato com " + candidato.getNome() + " com " + tentativasRealizadas
                    + " tentativas!");
        }
    }
}
