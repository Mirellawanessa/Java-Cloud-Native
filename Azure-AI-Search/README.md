<h1>
    <a href="https://www.dio.me/">
     <img align="center" width="60px" src="https://hermes.dio.me/lab_projects/badges/619af8f8-d138-4e40-9d48-fec7b318e44d.png"></a>
    <span> 
Azure Cognitive Search: Utilizando AI Search para indexação e consulta de Dados</span>
</h1>

Vamos imaginar que você trabalha para a Fourth Coffee, uma rede de cafeterias nacional. Você foi solicitado a ajudar a construir uma solução de mineração de conhecimento que facilite a busca por insights sobre as experiências dos clientes. Você decide construir um índice de Pesquisa de IA do Azure usando dados extraídos de avaliações de clientes.

Neste laboratório, você irá:

- Criar recursos do Azure
- Extrair dados de uma fonte de dados
- Enriquecer dados com habilidades de IA
- Usar o indexador do Azure no portal do Azure
- Consultar seu índice de pesquisa
- Revisar resultados salvos em um Knowledge Store

## Recursos do Azure necessários

A solução que você criará para a Fourth Coffee requer os seguintes recursos em sua assinatura do Azure:

- Um recurso de **Pesquisa de IA do Azure**, que gerenciará a indexação e a consulta.
- Um recurso de **Serviços de IA do Azure**, que fornece serviços de IA para habilidades que sua solução de pesquisa pode usar para enriquecer os dados na fonte de dados com insights gerados por IA.

    > **Observação**
    > Seus recursos de Pesquisa de IA do Azure e de Serviços de IA do Azure devem estar na mesma localização!

- Uma **conta de armazenamento** com contêineres de blobs, que armazenará documentos brutos e outras coleções de tabelas, objetos ou arquivos.

### Criar um recurso *Pesquisa de IA do Azure*

1. Faça login no [portal do Azure](https://portal.azure.com/learn.docs.microsoft.com?azure-portal=true).

1. Clique no botão **+ Criar um recurso**, pesquise por *Pesquisa de IA do Azure* e crie um recurso **Pesquisa de IA do Azure** com as seguintes configurações:

    - **Assinatura**: *Sua assinatura do Azure*.
    - **Grupo de recursos**: *Selecione ou crie um grupo de recursos com um nome exclusivo*.
    - **Nome do serviço**: *Um nome exclusivo*.
    - **Localização**: *Escolha qualquer região disponível*.
    - **Nível de preços**: Básico

1. Selecione **Revisar + criar** e, depois de ver a resposta **Validação bem-sucedida**, selecione **Criar**.

1. Após a conclusão da implantação, selecione **Ir para o recurso**. Na página de visão geral da Pesquisa de IA do Azure, você pode adicionar índices, importar dados e pesquisar índices criados.

### Criar um recurso de Serviços de IA do Azure

Você precisará provisionar um recurso de **Serviços de IA do Azure** que esteja na mesma localização que seu recurso de Pesquisa de IA do Azure. Sua solução de pesquisa usará este recurso para enriquecer os dados no datastore com insights gerados por IA.

1. Volte para a página inicial do portal do Azure. Clique no botão **&#65291;Criar um recurso** e pesquise por *Serviços de IA do Azure*. Selecione **criar** um **plano de Serviços de IA do Azure**. Você será levado a uma página para criar um recurso de Serviços de IA do Azure. Configure-o com as seguintes configurações:
    - **Assinatura**: *Sua assinatura do Azure*.
    - **Grupo de recursos**: *O mesmo grupo de recursos que seu recurso de Pesquisa de IA do Azure*.
    - **Região**: *A mesma localização que seu recurso de Pesquisa de IA do Azure*.
    - **Nome**: *Um nome exclusivo*.
    - **Nível de preços**: Padrão S0
    - **Ao marcar esta caixa, reconheço que li e entendi todos os termos abaixo**: Selecionado

1. Selecione **Revisar + criar**. Depois de ver a resposta **Validação Passada**, selecione **Criar**.

1. Aguarde o término da implantação e, em seguida, visualize os detalhes da implantação.

### Criar uma conta de armazenamento

1. Volte para a página inicial do portal do Azure e, em seguida, selecione o botão **+ Criar um recurso**.

1. Pesquise por *conta de armazenamento* e crie um recurso **Conta de Armazenamento** com as seguintes configurações:
    - **Assinatura**: *Sua assinatura do Azure*.
    - **Grupo de recursos**: *O mesmo grupo de recursos que seus recursos de Pesquisa de IA do Azure e Serviços de IA do Azure*.
    - **Nome da conta de armazenamento**: *Um nome exclusivo*.
    - **Localização**: *Escolha qualquer local disponível*.
    - **Desempenho**: Padrão
    - **Redundância**: Armazenamento redundante localmente (LRS)

1. Clique em **Revisar** e, em seguida, clique em **Criar**. Aguarde a conclusão da implantação e, em seguida, vá para o recurso implantado.

1. Na conta de armazenamento do Azure que você criou, no painel de menu à esquerda, selecione **Configuração** (em **Configurações**).
1. Altere a configuração para *Permitir acesso anônimo a Blob* para **Habilitado** e, em seguida, selecione **Salvar**.

## Carregar documentos no Armazenamento do Azure

1. No painel de menu à esquerda, selecione **Contêineres**.

   ![image](https://github.com/lucslima96/Azure-AI-Search/assets/128159917/e77e388f-5cfb-4948-b2c3-391f78690107)


1. Selecione **+ Contêiner**. Um painel no lado direito será aberto.

1. Insira as seguintes configurações e clique em **Criar**:
    - **Nome**: coffee-reviews  
    - **Nível de acesso público**: Contêiner (acesso de leitura anônimo para contêineres e blobs)
    - **Avançado**: *sem alterações*.

1. Em uma nova aba do navegador, faça o download das [avaliações de café compactadas](https://aka.ms/mslearn-coffee-reviews) de `https://aka.ms/mslearn-coffee-reviews` e extraia os arquivos para a pasta *reviews*.

1. No portal do Azure, selecione seu contêiner *coffee-reviews*. No contêiner, selecione **Upload**.

    ![image](https://github.com/lucslima96/Azure-AI-Search/assets/128159917/2afcda39-fddb-42b6-983d-509421e7da77)


1. Na janela **Upload de blob**, selecione **Selecionar um arquivo**.

1. Na janela do Explorador, selecione **todos** os arquivos na pasta *reviews*, clique em **Abrir** e, em seguida, selecione **Upload**.

   ![image](https://github.com/lucslima96/Azure-AI-Search/assets/128159917/251f4b2f-c596-4e79-9039-d24b79aa5b5d)


1. Após o término do upload, você pode fechar a janela **Upload de blob**. Seus documentos agora estão no contêiner de armazenamento *coffee-reviews*.

## Indexar os documentos

Após ter os documentos armazenados, você pode usar a Pesquisa de IA do Azure para extrair insights desses documentos. O portal Azure fornece um *Assistente de Importação de Dados*. Com este assistente, você pode criar automaticamente um índice e um indexador para fontes de dados suportadas. Você usará o assistente para criar um índice e importar seus documentos de pesquisa do armazenamento para o índice de Pesquisa de IA do Azure.

1. No portal Azure, navegue até o recurso de Pesquisa de IA do Azure. Na página **Visão geral**, selecione **Importar dados**.

   ![image](https://github.com/lucslima96/Azure-AI-Search/assets/128159917/bf3eee36-8eca-4c2f-964e-103339dacc66)


1. Na página **Conectar-se aos seus dados**, na lista **Fonte de dados**, selecione **Armazenamento de Blobs do Azure**. Complete os detalhes do datastore com os seguintes valores:
    - **Fonte de dados**: Armazenamento de Blobs do Azure
    - **Nome da fonte de dados**: dados do cliente de café
    - **Dados para extrair**: Conteúdo e metadados
    - **Modo de análise**: Padrão
    - **Cadeia de conexão**: *Selecione **Escolher uma conexão existente**. Selecione sua conta de armazenamento, selecione o contêiner **coffee-reviews** e clique em **Selecionar**.
    - **Autenticação de identidade gerenciada**: Nenhum
    - **Nome do contêiner**: *este ajuste é preenchido automaticamente depois de você escolher uma conexão existente*.
    - **Pasta de blob**: *Deixe este espaço em branco*.
    - **Descrição**: Avaliações para lojas de café Fourth Coffee.

1. Selecione **Próximo: Adicionar habilidades cognitivas (Opcional)**.

1. Na seção **Anexar Serviços Cognitivos**, selecione seu recurso de serviços de IA do Azure.  

1. Na seção **Adicionar enriquecimentos**:
    - Altere o **Nome do conjunto de habilidades** para **conjunto de habilidades de café**.
    - Selecione a caixa de seleção **Habilitar OCR e mesclar todo o texto no campo merged_content**.
        > **Observação**
        > É importante selecionar **Habilitar OCR** para ver todas as opções de campos enriquecidos.
    - Garanta que o **Campo de dados de origem** esteja definido como **merged_content**.
    - Altere o **Nível de granularidade de enriquecimento** para **Páginas (pedaços de 5000 caracteres)**.
    - Não selecione *Habilitar enriquecimento incremental*
    - Selecione os seguintes campos enriquecidos:

        | Habilidade Cognitiva | Parâmetro | Nome do campo |
        | --------------- | ---------- | ---------- |
        | Extrair nomes de localização | | locais |
        | Extrair frases-chave | | fraseschave |
        | Detectar sentimento | | sentimento |
        | Gerar tags de imagens | | tagsdeimagem |
        | Gerar legendas a partir de imagens | | legendasdeimagem |

1. Em **Salvar enriquecimentos em um Knowledge Store**, selecione:
    - Projeções de imagens do Azure
    - Documentos
    - Páginas
    - Frases-chave
    - Entidades
    - Detalhes da imagem
    - Referências de imagem

    > **Observação**
    > Se um aviso pedindo uma **Cadeia de Conexão de Conta de Armazenamento** aparecer.
    >
    > ![image](https://github.com/lucslima96/Azure-AI-Search/assets/128159917/65686d7e-0615-43ed-84a3-0255cff7d8b6)

    >
    > 1. Selecione **Escolher uma conexão existente**. Escolha a conta de armazenamento que você criou anteriormente.
    > 1. Clique em **+ Contêiner** para criar um novo contêiner chamado **knowledge-store** com o nível de privacidade definido como **Privado**, e selecione **Criar**.
    > 1. Selecione o contêiner **knowledge-store** e, em seguida, clique em **Selecionar** na parte inferior da tela.

1. Selecione **Projeções de Blob do Azure: Documento**. Uma configuração para *Nome do contêiner* com o contêiner *knowledge-store* preenchido automaticamente será exibida. Não altere o nome do contêiner.

1. Selecione **Próximo: Personalizar índice de destino**. Altere o **Nome do índice** para **índice de café**.

1. Garanta que a **Chave** esteja definida como **metadata_storage_path**. Deixe **Nome do sugestor** em branco e **Modo de pesquisa** autopreenchido.

1. Revise as configurações padrão dos campos de índice. Selecione **filtrável** para todos os campos que já estão selecionados por padrão.

    ![image](https://github.com/lucslima96/Azure-AI-Search/assets/128159917/3350e74c-976a-4d7f-bea5-05a4dcccb7fb)


1. Selecione **Próximo: Criar um indexador**.

1. Altere o **Nome do indexador** para **indexador de café**.

1. Deixe o **Agendamento** definido como **Uma vez**.

1. Expanda as **Opções avançadas**. Garanta que a opção **Codificar Chaves em Base-64** esteja selecionada, pois a codificação de chaves pode tornar o índice mais eficiente.

1. Selecione **Enviar** para criar o fonte de dados, conjunto de habilidades, índice e indexador. O indexador é executado automaticamente e executa o pipeline de indexação, que:
    - Extrai os campos de metadados do documento e o conteúdo da fonte de dados.
    - Executa o conjunto de habilidades de habilidades cognitivas para gerar mais campos enriquecidos.
    - Mapeia os campos extraídos para o índice.

1. Retorne à página do seu recurso de Pesquisa de IA do Azure. No painel esquerdo, em **Gerenciamento de Pesquisa**, selecione **Indexadores**. Selecione o **indexador de café** recém-criado. Aguarde um minuto e selecione **&orarr; Atualizar** até que o **Status** indique sucesso.

1. Selecione o nome do indexador para ver mais detalhes.

    ![image](https://github.com/lucslima96/Azure-AI-Search/assets/128159917/7745ea06-ea28-4eef-b4a3-d7f7b6daad0c)


## Consultar o índice

Use o explorador de pesquisa para escrever e testar consultas. O explorador de pesquisa é uma ferramenta integrada ao portal Azure que oferece uma maneira fácil de validar a qualidade do seu índice de pesquisa. Você pode usar o explorador de pesquisa para escrever consultas e revisar resultados em JSON.

1. Na página **Visão geral** do seu serviço de pesquisa, selecione **Explorador de Pesquisa** no topo da tela.

   ![image](https://github.com/lucslima96/Azure-AI-Search/assets/128159917/9dd8f2b1-ee4b-4e79-95b8-b4145f5302b7)


2. Observe como o índice selecionado é o *índice de café* que você criou. Abaixo do índice selecionado, altere a *visualização* para **Visualização JSON**. 

    ![image](https://github.com/lucslima96/Azure-AI-Search/assets/128159917/1dcd2e3c-efc6-44df-b752-f7c29038e133)


No campo **Editor de consulta JSON**, copie e cole: 
```json
{
    "search": "*",
    "count": true
}
```
3. Selecione **Pesquisar**. A consulta de pesquisa retorna todos os documentos no índice de pesquisa, incluindo uma contagem de todos os documentos no campo **@odata.count**. O índice de pesquisa deve retornar um documento JSON contendo seus resultados de pesquisa.

4. Agora vamos filtrar por localização. No campo do **Editor de consulta JSON**, copie e cole:
```json
{
    "search": "locations:'Chicago'",
    "count": true
}
```
5. Selecione **Pesquisar**. A consulta pesquisa todos os documentos no índice e filtra as avaliações com localização em Chicago. Você deverá ver `3` no campo `@odata.count`.

6. Agora vamos filtrar por sentimento. No campo do **Editor de consulta JSON**, copie e cole:
```json
{
    "search": "sentiment:'negative'",
    "count": true
}
```
7. Selecione **Pesquisar**. A consulta pesquisa todos os documentos no índice e filtra as avaliações com um sentimento negativo. Você deverá ver `1` no campo `@odata.count`.

   > **Nota**
   > Observe como os resultados são classificados por `@search.score`. Esta é a pontuação atribuída pelo mecanismo de pesquisa para mostrar o quão bem os resultados correspondem à consulta fornecida.

8. Um dos problemas que talvez queiramos resolver é por que pode haver algumas avaliações negativas. Vamos dar uma olhada nas frases-chave associadas à avaliação negativa. O que você acha que pode ser a causa da avaliação?

## Revisar o repositório de conhecimento

Vamos ver o poder do repositório de conhecimento em ação. Quando você executou o *Assistente de importação de dados*, também criou um repositório de conhecimento. Dentro do repositório de conhecimento, você encontrará os dados enriquecidos extraídos pelas habilidades de IA persistindo na forma de projeções e tabelas.

1. No portal do Azure, navegue de volta para sua conta de armazenamento do Azure.

2. No painel de menu à esquerda, selecione **Containers**. Selecione o contêiner **knowledge-store**.

    ![image](https://github.com/lucslima96/Azure-AI-Search/assets/128159917/87ddbece-8c79-422e-adfc-280ddee122c7)


3. Selecione qualquer um dos itens e clique no arquivo **objectprojection.json**.

    ![image](https://github.com/lucslima96/Azure-AI-Search/assets/128159917/b9aa1877-25f8-409c-ad7f-f0ed3ecfada0)


4. Selecione **Editar** para ver o JSON produzido para um dos documentos do seu armazenamento de dados do Azure.

    ![image](https://github.com/lucslima96/Azure-AI-Search/assets/128159917/fb636b3c-02c6-4675-89e1-475098fc06c0)


5. Selecione o caminho de migalhas de pão do blob de armazenamento no canto superior esquerdo da tela para retornar aos *Containers* da conta de armazenamento.

    ![image](https://github.com/lucslima96/Azure-AI-Search/assets/128159917/9fa526f0-8b29-464c-a0da-d079b10cb722)


6. Nos *Containers*, selecione o contêiner *coffee-skillset-image-projection*. Selecione qualquer um dos itens.

    ![image](https://github.com/lucslima96/Azure-AI-Search/assets/128159917/a960d95e-9889-4e61-8657-afd20f6e345c)


7. Selecione qualquer um dos arquivos *.jpg*. Selecione **Editar** para ver a imagem armazenada do documento. Observe como todas as imagens dos documentos são armazenadas dessa maneira.

    ![image](https://github.com/lucslima96/Azure-AI-Search/assets/128159917/dfb0cb59-68f0-49ce-911a-0f7ff34aa089)


8. Selecione o caminho de migalhas de pão do blob de armazenamento no canto superior esquerdo da tela para retornar aos *Containers* da conta de armazenamento.

9. Selecione **Navegador de armazenamento** no painel esquerdo e selecione **Tabelas**. Há uma tabela para cada entidade no índice. Selecione a tabela *coffeeSkillsetKeyPhrases*.

    Observe as frases-chave que o repositório de conhecimento foi capaz de capturar do conteúdo das análises. Muitos dos campos são chaves, então você pode vincular as tabelas como um banco de dados relacional. O último campo mostra as frases-chave que foram extraídas pelo conjunto de habilidades.

## Saiba mais

Este índice de pesquisa simples mostra apenas algumas das capacidades do serviço Azure AI Search. Para saber mais sobre o que você pode fazer com este serviço, consulte a [página do serviço Azure AI Search](https://learn.microsoft.com/azure/search).


