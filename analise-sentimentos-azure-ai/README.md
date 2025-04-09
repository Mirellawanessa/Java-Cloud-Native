# Análise de Sentimentos com Azure AI Language Studio

Este projeto demonstra como utilizar o Azure AI Language Studio para análise de sentimentos em textos.

## Pré-requisitos
- Conta no Azure
- Recurso de Language Service criado

## Como executar
1. Acesse o [Language Studio](https://language.cognitive.azure.com/)
2. Selecione "Análise de Sentimento"
3. Insira o texto para análise
4. Visualize os resultados

## Resultados Exemplo
Pra os testes desse projeto, foram utilizadas avaliações ficticias de um restaurante italiano.

**Frases analisadas:**
- "Noite Inesquecível!
O restaurante superou todas as expectativas! A massa fresca do fettuccine alfredo derretia na boca, perfeitamente al dente. O chef veio à nossa mesa para explicar a origem dos ingredientes importados diretamente da Toscana. O tiramisu era divino - o equilíbrio entre café e mascarpone estava impecável. O som ambiente de ópera italiana e a decoração rústica criaram uma atmosfera autêntica. O serviço foi impecável; o garçom Luca fez recomendações perfeitas de vinhos. Voltaremos com certeza para experimentar o menu degustação!" → Positivo (0.93)
- "Frustração Total, não Recomendo!
Pior experiência gastronômica dos últimos anos, a "carbonara autêntica" vinha com creme de leite, uma heresia na culinária italiana, e o bacon estava queimado. Esperamos 50 minutos pelos pratos principais e quando reclamamos, o gerente foi grosseiro. O vinho estava oxidado e mesmo assim tentaram nos cobrar. O banheiro estava sujo e sem papel. Para completar, encontraram um fio de cabelo no meu risoto! Cobraram 10% de serviço obrigatório por um atendimento que foi péssimo em todos os aspectos. Nunca mais pisarei nesse lugar e alerto todos os amantes da verdadeira cozinha italiana a evitá-lo." → Negativo (1.0)

## Insights
- O serviço consegue identificar nuances mesmo em frases sem palavras óbvias
- O score é maior quando o sentimento é mais extremo
- Frases neutras podem ser desafiadoras para o modelo

## Aprendizados
- Como configurar serviços de IA na Azure.
- A importância do processamento de linguagem natural.
- Aplicações práticas de análise de sentimentos.

## 👩‍💻 Desenvolvedora

<p>
    <img 
      align="left" 
      width="80" 
      src="https://github.com/Mirellawanessa/DIO-Trilha-Java-Basico/blob/main/GitHub/imagens/User.jpeg?raw=true"
    />
    <p>&nbsp;&nbsp;&nbsp;Mirella Wanessa<br>
    &nbsp;&nbsp;&nbsp;
    <a href="https://github.com/Mirellawanessa">GitHub</a>&nbsp;|&nbsp;
    <a href="https://www.linkedin.com/in/mirellawanessa/">LinkedIn</a>&nbsp;|&nbsp;
    <a href="https://www.instagram.com/_mirella.page/?next=%2F">Instagram</a>
    &nbsp;|&nbsp;</p>
</p>

---

⌨️ with 💜 by [Mirella Wanessa](https://github.com/Mirellawanessa)
