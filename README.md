# CP4-DevOps


## Sobre o projeto
Aproveitando o pedido de Steves Jobs para testar as aplicações, nossa consultoria  decidiu implementar o sistema de fichas para o setor de manutenção dos galpões da Dimdim. Essa API acabou sendo engavetada por causa de outras demandas mais importantes, mas essa se mostrou a oportunidade perfeita para desengavetar o projeto.  
Ele consiste em um sistema de manutenção das máquinas dos setores de manofatura, acabamento e embalagem da Dimdim. Visando modernizar o modelo de avaliação das máquinas, Steves pediu que criássemos um novo sistema para substituir o antigo de fichas físicas, até por que a Dimdim é uma empresa que se preocupa com o meio-ambiente e em pleno 2023 não existe mais motivos para usar papel em quase nada.  
Com esse sistema, o responsável por verificar o funcionamento das máquinas preenche uma ficha com o Id dele, o Id da máquina, se ela passou ou não no teste e, se não passou, o motivo da falha. Tudo isso em um web app na nuvem, incluindo o banco de dados Oracle já ustilizado nas soluções da Dimdim.  
Além dos cadastro das máquinas, em primeiro momento é necessário o cadastro dos usuário responsáveis pelo preenchimento das fichas. A nossa consultoria já salvou no banco de dados todas as máquinas dos 3 setores contemplados com a solução.

## Deploy da API
É uma API springboot e como meio de economizar tempo, o deploy foi realizado direto pela IDE Intellij.  
O processo é muito intuitivo e rápido. Após baixar o plugin da Azure na sua IDE, basta clicar com o botão direito na pasta raiz do seu projeto, selecionar o ícone da Azure e clicar em  _Deploy to Azure Web Apps_:  
  
![image](https://github.com/trcosta97/CP4-DevOps/assets/101136329/cf26cd6b-3dfc-419f-b683-dc715eadf6d3)  
  
Um pop-up vai abrir e você configura seu Web App a partir dele:  
  
![image](https://github.com/trcosta97/CP4-DevOps/assets/101136329/102702ac-ecef-4956-bef7-50f2ad442390)  
  
No campo _Web App_-  você seleciona o ícone de **+** e da um check na caixa _More settings_:
  
![image](https://github.com/trcosta97/CP4-DevOps/assets/101136329/b7109aa5-c4d1-40c9-8890-b76843159ab3)  
  
Aqui ficam as informações mais importantes do seu deploy:  
#### Subscription (Assinatura):   
A assinatura é o contrato entre a sua organização e a Microsoft que permite o acesso aos serviços do Azure. É basicamente a conta de cobrança que você usa para pagar pelos recursos que consome no Azure. Cada assinatura tem um ID único.
#### Resource Group (Grupo de Recursos):  
Um grupo de recursos é um contêiner lógico que ajuda a gerenciar, organizar e agrupar recursos relacionados em sua solução do Azure. Ele pode conter serviços, como máquinas virtuais, bancos de dados, redes, etc. É útil para gerenciar e organizar recursos de acordo com seu projeto ou aplicação.  
#### Name (Nome):  
O nome é o identificador único do recurso que você está criando no Azure. O nome é usado para acessar e gerenciar o recurso e deve ser exclusivo dentro do escopo do serviço específico.
#### Platform (Plataforma):  
Isso se refere à plataforma de computação que você está usando para implantar seu aplicativo ou serviço. Pode ser Windows, Linux ou outra plataforma, dependendo das necessidades do seu aplicativo.
#### Region (Região):  
A região do Azure é um local geográfico onde os data centers da Microsoft estão localizados. Você escolhe uma região para implantar seus recursos, e a Microsoft garante que eles sejam executados naquela região. Escolher uma região próxima aos seus usuários ou aplicação pode ajudar a reduzir a latência e melhorar o desempenho.
#### Plan (Plano):  
O plano se refere ao tipo de serviço ou nível de serviço que você deseja usar no Azure. Por exemplo, para serviços de aplicativos da web, você pode escolher entre planos gratuitos, compartilhados, básicos, padrão, premium, etc. Cada plano tem características e capacidades diferentes.
#### SKU (Stock Keeping Unit):  
A SKU se refere ao nível de recursos e desempenho de um serviço específico no Azure. Cada serviço tem várias SKUs disponíveis, que podem variar em termos de CPU, memória, armazenamento e outros recursos. Você escolhe a SKU com base nas necessidades do seu aplicativo e no custo associado.

Depois de tudo definido e preenchido, basta clicar em **_Run_** e esperar o deploy ser realizado.
Nós fizemos um passo a passo do processo em vídeo, incluindo a persistência de dados no swagger da nossa API e mostrando os dados persistidos no banco lado a lado:
[https://youtu.be/7YsJhwi-iP0](https://youtu.be/7eNPvORBw6c)  

## Criação da tabelas
Esse é o DDL das tabelas no SQL. O banco utilizado foi o Oracle.
```console

CREATE TABLE usuario (
    id NUMBER PRIMARY KEY,
    nome VARCHAR2(255) NOT NULL,
    login VARCHAR2(255) UNIQUE NOT NULL,
    senha VARCHAR2(255) NOT NULL,
    data_cadastro TIMESTAMP NOT NULL,
    status NUMBER(1, 0) DEFAULT 1 -- Use NUMBER(1, 0) for Boolean-like behavior (1 for true, 0 for false)
);

CREATE TABLE maquina (
    id NUMBER PRIMARY KEY,
    nome VARCHAR2(255) NOT NULL,
    setor VARCHAR2(255) NOT NULL,
    status NUMBER(1, 0) DEFAULT 1,
    data_cadastro TIMESTAMP
);

CREATE TABLE ficha (
    id NUMBER PRIMARY KEY,
    autor_id NUMBER NOT NULL,
    maquina_id NUMBER NOT NULL,
    data_criacao TIMESTAMP NOT NULL,
    comentarios CLOB,
    aprovado NUMBER(1, 0) DEFAULT 1,
    ativo NUMBER(1, 0) DEFAULT 1
);

-- Cria sequencias que gera os ID's
CREATE SEQUENCE usuario_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE maquina_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE ficha_seq START WITH 1 INCREMENT BY 1;

-- Cria as chaves estrangeiras
ALTER TABLE ficha ADD CONSTRAINT fk_autor FOREIGN KEY (autor_id) REFERENCES usuario(id);
ALTER TABLE ficha ADD CONSTRAINT fk_maquina FOREIGN KEY (maquina_id) REFERENCES maquina(id);
```
## JSONs
Agora que você já tem a API na nuvem, você pode testa-la a partir do link que você definiu. Incluindo um __/swagger-ui/index.html#/__ após esse link, voê acessa o swagger da aplicação, onde tem todos os métodos e modelos de JSON já prontos. Mas se você prefere usar um software como o Insomnia ou o Postman, nós também preparamos os JSONs já formatados para você inseirir os seus dados.:  

### Usuário
Essa é a entidade que representa o funcinário que vai preencher as fichas.
#### POST - Cadastrar usuário
linkdasuaaplicaçãonanuvem/usuario
```console
  {
  "nome": "string",
  "login": "string",
  "senha": "string",
  }
```
#### UPDATE - Atualizar usuário
linkdasuaaplicaçãonanuvem/usuario/{id}
```console
  {
  "nome": "string",
  "login": "string",
  "senha": "string",
  }
```

### Ficha
Essa é a entidade que representa o ficha.
#### POST - Cadastrar ficha
linkdasuaaplicaçãonanuvem/ficha
```console
{
  "autor": {
    "id": 0
  },
  "maquina": {
    "id": 0 
  },
  "aprovado": boolean,
  "comentarios": "String"
}
```
#### UPDATE - Atualizar ficha
linkdasuaaplicaçãonanuvem/ficha/{id}
```console
{
  "comenatarios": "String"
}
```

### Máquina
Essa é a entidade que representa a máquina a ser avaliada. Lembrando que só existem 3 setores: MANUFATURA, ACABAMENTO e EMBALAGEM.
Essa entidade possui apenas as funções Post e Get All, por se tratarem de máquinas fixas que não mudam de setor. Além de por se tratar de uma parte delicada que pode atrapalhar o resto do sistema no caso de mal uso, somente os departamento de TI tem acesso.
#### POST - Cadastrar máquina
linkdasuaaplicaçãonanuvem/maquina
```console
{
  "nome": "string",
  "setor": "MANUFATURA"
}
```



Os métodos de Get e Delete não pedem um corpo na requisição. Você pode utiliza-los individualmente colocando o {id} do alvo como no Update ou realizar a ação em toda a tabela usando o mesmo Url do Post pra cada uma das entidades.

## Conclusão
Com tudo que nós passamos nesse Readme + o código desse repositório você é mais do que capaz de criar seu Azure WebApp direto da IDE Intelijj. Pra outras IDEs o processe é parecido, a única diferença fica na UI de cada uma.  
A partir desse processo, o deploy de uma aplicação na nuvem através do Azure WebApps fica ainda mais rápido e prático, dispensando a necessidade de ir até o portal Azure e fazendo todo o processo direto da IDE.




