package funcionalidades;

public class IphoneFuncoes implements ReprodutorMusicial, AparelhoTelefônico, NavegadorDeInternet{
     //ReprodutorMusicial
     public void tocar(){
        System.out.println("Tocando música");
    }
    public void pausar(){
        System.out.println("Música pausada");
    }
    public void selecionarMusica(){
        System.out.println("Música selecionada");
    }

    //AparelhoTelefônico
    public void ligar(){
        System.out.println("Chamando...");
    }
    public void atender(){
        System.out.println("Ligação atendida: Chamada em andamento");
    }
    public void iniciarCorrerioVoz(){
        System.out.println("Correio de voz iniciado");
    }

    //NavegadorDeInternet
    public void exibirPagina(){
        System.out.println("Exibindo página");
    }
    public void adicionarNovaAba(){
        System.out.println("Nova aba adicionada");
    }
    public void atualizarPagina(){
        System.out.println("Página atualizada");
    }
}
