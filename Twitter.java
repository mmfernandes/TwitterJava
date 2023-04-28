import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;

public class Twitter {
    public static Scanner ler = new Scanner(System.in); // objeto Scanner para ler as entradas do usuário
    public static ArrayList<Usuario> usuarios = new ArrayList<Usuario>(); // Cria um ArrayList para armazenar objetos da classe Usuario
    public static ArrayList<String[]> tweets = new ArrayList<String[]>(); // Cria um ArrayList para armazenar os tweets publicados pelos usuários
    public static int cont = 0;

    public static void main(String[] args) { // Método principal que inicia o programa
        acessarMenu(); // Chama o método que exibe o menu de ações
    }

    public static void acessarMenu() { // Define o método que exibe o menu de ações
        int escolha = 0;
        do {
            System.out.println("--- Menu de Ações ---");
            System.out.println("0. Encerrar");
            System.out.println("1. Cadastrar Usuário");
            System.out.println("2. Listar Usuário");
            System.out.println("3. Logar Usuário");
            System.out.println("4. Deslogar Usuário");
            System.out.println("5. Tweetar");
            System.out.println("6. Mostrar Últimos Tweets");
            System.out.println("7. Remover Tweet do Usuário");
            System.out.println("8. Alterar a Senha");
            System.out.println("9. Remover algum usuário");
            System.out.println("10. Estástisticas do Twitter\n");
            System.out.println("O que deseja fazer?");

            escolha = ler.nextInt();

            switch (escolha) {
                case 1:
                    System.out.println("--- Cadastrando Usuário ---\n");
                    usuarios.add(criarUser());
                    System.out.println();
                    break;
                case 2:
                    System.out.println("--- Listando Usuários ---");
                    listarUsers();
                    System.out.println();
                    break;
                case 3:
                    System.out.println("--- Logando Usuário ---");
                    logarUser();
                    System.out.println();
                    break;
                case 4:
                    System.out.println("--- Deslogando Usuário ---");
                    deslogarUser();
                    System.out.println();
                    break;
                case 5:
                    System.out.println("--- Tweetar ---");
                    twettar();
                    System.out.println();
                    break;
                case 6:
                    System.out.println("--- Mostrar Tweets ---");
                    listarTweets();
                    System.out.println();
                    break;
                case 7:
                    System.out.println("--- Remover Tweet ---");
                    removerTweets();
                    System.out.println();
                    break;
                case 8:
                    System.out.println("--- Alterar Senha ---");
                    alterarSenha();
                    System.out.println();
                    break;

                case 9:
                    System.out.println("--- Remover Usuário ---");
                    removerUsuario();
                    System.out.println();
                    break;

                case 10:
                    System.out.println("--- Estatisticas ---");
                    estatisticas();
                    System.out.println();
                    break;
            }
        } while (escolha != 0);
    }

    private static void bufferScanner(Scanner bufScanner) { // LIMPA O BUFFER DO SCANNER
        if (bufScanner.hasNextLine()) {// verifica se já existe algo alocado no buffer
            bufScanner.nextLine(); // Limpa o buffer
        }
    }

    public static Usuario criarUser() { // 1 - CRIAR USUARIO
        bufferScanner(ler);
        boolean valido = false;
        boolean logado = false;
        String nome = "";
        String login = "";
        String email = "";
        String senha = "";

        // validação nome usuário
        do {
            System.out.print("Digite o seu nome: (min 2, max 30): ");
            nome = ler.next();
            bufferScanner(ler); // limpa o buffer do scanner

            if (nome.length() < 2 || nome.length() > 30) { // verifica o tamanho do nome.
                valido = true; // se o tamanho estiver errado, define a variavel como true para continuar.
                System.out.println(nome + " está fora do tamanho correto!");
            } else {
                System.out.println("Nome cadastrado, seu nome é: " + nome);
                valido = false; // se o tamanho estiver certo, define como false para sair do loop.
                break;
            }
        } while (valido);
        System.out.println();

        // validação login usuário.
        do {
            System.out.print("Digite o seu login: (min 2, max 20): ");
            login = ler.next();
            bufferScanner(ler);

            if (login.length() >= 2 && login.length() <= 15) { // verifica o tamanho do login.
                valido = false;// se o tamanho estiver certo, define a variável como false para sair do loop.
                for (Usuario item : usuarios) { // verifica se o login já existe na lista de usuários cadastrados.
                    if (login.equals(item.getLogin())) { 
                        valido = true;// se já existe, define a variável como true para continuar
                        System.out.println(login + " já está em uso por favor utilize outro!");
                        break;
                    }
                }
                if (valido == false) {
                    System.out.println("Login cadastrado, seu login é: " + login);
                    valido = false;
                    break;
                }
            } else { // se estiver fora ele define como true para continuar
                System.out.println("O login deve ter no mínimo 2 e no máximo 20 caracteres. Tente novamente.");
                valido = true;
            }
        } while (valido);
        System.out.println();

        // validação email usuário.
        do {
            System.out.print("Digite o seu email: (min 6, max 30): ");
            email = ler.next();
            bufferScanner(ler);

            if (email.length() >= 6 && email.length() <= 30) { // verifica o tamanho do email.
                valido = false; // se o tamanho estiver certo, define a variável como false para sair do loop.
                for (Usuario item : usuarios) { // percorre a lista para ver se o email já existe
                    if (email.equals(item.getEmail())) {
                        valido = true;
                        System.out.println(email + " já está em uso, por favor utilize outro!");
                        break;
                    }
                }
                if (valido == false) { // Se o email não estiver cadastrado, exibe a mensagem de sucesso e sai do loop
                    System.out.println("Email cadastrado, seu email é: " + email);
                    break;
                }
            } else { // se estiver fora ele define como true para continuar
                System.out.println("O email deve conter no min 6 e no maximo 30 caracteres.");
                valido = true;
            }

        } while (valido);
        System.out.println();

        // validação senha usuário.
        do {
            System.out.print("Digite a sua senha: (min 6, max 15): ");
            senha = ler.next();
            bufferScanner(ler);

            if (senha.length() < 6 || senha.length() > 15) { // verifica o tamanho da senha.
                System.out.println("A senha deve ter no min 6 e no max 15 caracteres");
                valido = true;
            } else {
                System.out.println("Senha cadastrada!");
                valido = false;
                break;
            }
        } while (valido);

        // Cria um objeto user com as informações fornecidas pelo usuário e retorna esse objeto
        Usuario user = new Usuario(nome, login, email, senha, logado);
        return user;
    }

    public static void listarUsers() { // 2 - LISTAR USUARIOS
        System.out.println();
        for (Usuario item : usuarios) { // percorre a lista de usuários
            System.out.println("Usuário: " + item.getLogin()); // exibe o login do usuário
            System.out.println("------------");
        }
    }

    private static void logarUser() { // 3 - LOGAR USUARIO
        System.out.println("Informe seu login: ");
        String loginUs = ler.next();
        System.out.println("Informe a senha: ");
        String senha = ler.next();
        bufferScanner(ler);

        for (Usuario item : usuarios) { // percorre a lista buscando pelo usuário que está tentando fazer login
            if (loginUs.equals(item.getLogin()) && item.verificaSenha(senha)) { // se encontrar usuário e a senha estiver correta, seta o status do usuário para logado
                item.setLogado(true);
                System.out.println("Usuário " + loginUs + " logado com sucesso!");
                return;
            }
        }
        System.out.println("Não foi encontrado esse usuário ou a senha está incorreta!");
    }

    private static void deslogarUser() { // 4 - DESLOGAR USUARIO
        System.out.println("Informe seu login: ");
        String login = ler.next();

        for (Usuario item : usuarios) { // procura pelo login do usuário na lista de usuários
            if (login.equals(item.getLogin())) {
                item.setLogado(false); // altera o status de logado do usuário para falso
                System.out.println("Usuário deslogado com sucesso!");
                return;
            }
        }
        System.out.println("Não foi encontrado esse usuário!");
    }

    private static void twettar() { // 5-TWETTAR
        boolean valido = false;
        if(usuarios.size() == 0){
            System.out.println("Ainda não existe usuario no sistema");
        }else{
            String tweet = "";
            String login;
            System.out.print("Informe o usuário: ");
            login = ler.next();
            bufferScanner(ler);

            for (int i = 0; i < usuarios.size(); i++){
                if (login.equals(usuarios.get(i).getLogin()) && usuarios.get(i).getLogado()){ 
                    valido = true;
                    do { // Verifica se o usuário existe e está logado
                        System.out.print("Escreva seu tweet (entre 1-140): ");
                        tweet = ler.nextLine();
                        System.out.println();

                        if (!(tweet.length() > 1 && tweet.length()< 140)) { // Verifica se o tweet tem entre 1 e 140 caracteres
                            System.out.println("O tweet não está dentro do número de caracteres permitidos");
                        }
                    } while (tweet.length() <= 1 || tweet.length() >= 140);

                    cont = cont + 1;
                    System.out.println("----");
                    System.out.println("Seu tweet é: " + tweet);

                    String[] novoTweet = { login, tweet, Integer.toString(cont) }; //trazendo o cont como id do tt
                    tweets.add(novoTweet); // Adiciona o tweet na lista de tweets
                } else{
                    
                }
            }
            if(valido == false){
                System.out.println("Usuário não existe ou não esta logado.");
            }
        }
    }

    public static void listarTweets() { // 6 - LISTAR TWEETS
        int totalTweets = tweets.size(); // Obtém o número total de tweets armazenados

        System.out.println("Quantos tweets quer ver? ");
        int n = ler.nextInt();
        bufferScanner(ler);

        if (n > totalTweets) { // Verifica se o número fornecido é maior do que o número total de tweets
            System.out.println("Não há tweets suficientes!");
        } else {
            System.out.println("Ultimos " + n + " tweets: ");
            for (int i = totalTweets - n; i < totalTweets; i++) { // Recupera e exibe os n últimos tweets da lista
                System.out.println("=> " + tweets.get(i)[1]);
            }
        }
    }

    private static void removerTweets() { // 7 REMOVER TWEET
        bufferScanner(ler); // limpa o buffer
        String login;
        int numTweets;

        System.out.println("Informe o usuário: ");
        login = ler.next();
        bufferScanner(ler);

        // lista todos os tweets do usuário com um número identificador na frente
        for (int i = 0; i < tweets.size(); i++) {
            String[] recebidos = tweets.get(i); // recebidos recebe a posição atual do tweet
            if (recebidos[0].equals(login)) {
                System.out.println(recebidos[2] + " - tweet: " + recebidos[1]);
              
            }

        }

        for (Usuario item : usuarios) {
            if (login.equals(item.getLogin()) && item.getLogado()){ // verifica dentro do array se o usuario esta logado ou existe
               
                System.out.println("Informe o número do  tweet você deseja remover: ");
                numTweets = ler.nextInt();
            
                for (int i = 0; i < tweets.size(); i++) { // pega o tamanho do array
                    if (numTweets == i) { // entra quando o contador foi igual ao numero do tweet digitado pelo usuario
                        tweets.remove(i - 1); 
                        System.out.println("Tweet: " + tweets.remove(i) + " foi rmeovido" ); // remove o tweet com o indice i - 1 pois array inicia com o indice 0
                    }
                }
            } else {
                System.out.println("O usuário não está logado ou não existe.");
            }
        }
    }

    private static void alterarSenha() { // 8 - ALTERAR SENHA
        bufferScanner(ler);
        System.out.println("Digite seu nome de usuário:");
        String login = ler.nextLine(); // lê o nome de usuário informado pelo usuário

        System.out.println("Digite sua senha atual:");
        String sen = ler.nextLine(); // lê a senha atual informada pelo usuário

        for (Usuario item : usuarios) { // percorre a lista de usuários cadastrados
            if (login.equals(item.getLogin()) && item.verificaSenha(sen)) { // verifica se o login e senha correspondem a um usuário existente e logado
                System.out.println("Digite sua nova senha: ");
                String senha = ler.nextLine(); // solicita que o usuário informe a nova senha
                item.setSenha(senha); // define a nova senha para o usuário
                System.out.println("Senha alterada com sucesso!");
            } else {
                System.out.println("Usuário ou senha incorretos."); // exibe mensagem de erro caso o login ou senha informados sejam inválidos
            }
        }
    }

    private static void removerUsuario() { // 9 - REMOVER USUARIO
        System.out.print("Qual o nome do usuario que você deseja remover: ");
        String nomeUsuario = ler.next();
        String senhaUsuario = null;
        boolean valido = false;

        for (int i = 0; i < usuarios.size(); i++) {// enquanto n tiver visto todos os userd{
            if (nomeUsuario.equals(usuarios.get(i).getLogin())) {// se é igual posicao atual, se sim {
                System.out.println("Digite a senha desse usuario: ");
                senhaUsuario = ler.next();

                if (senhaUsuario.equals(usuarios.get(i).getSenha())) { // se a senha está correta, remove o usuário
                    Iterator<Usuario> iter = usuarios.iterator();
                    while (iter.hasNext()) {
                        Usuario item = iter.next();
                        if (item.getLogin().equals(nomeUsuario)) {
                            iter.remove();
                            System.out.println("Usuário removido");
                            valido = true;
                        }
                    }
                } else {
                    valido = true;
                    System.out.println("Senha incorreta. O usuário não foi removido.");
                }
            }else{
                valido = false;
            } 
        }
        if (valido == false) {
            System.out.println("Usuário não encontrado.");
        }
    }

    private static void estatisticas() { // 10 - ESTATISTICAS
        int quantidadeUsuarios = 0;
        int quantidadeUsuariosLogados = 0;
        int quantidadeTweets = 0;
        int numTweetsUsuario = 0;
        String userMaiorTweets = "";
        int numTweets;
        String ultimoTweetLogin = ""; 
        String ultimoTweetMsg = ""; 
        
        for (Usuario item : usuarios) {// percorre a lista de usuários e incrementa a quantidade de usuários cadastrados e logados
            if (item.getLogin() != null) {
                quantidadeUsuarios++; // estatistica 1 - número de usuários cadastrados
            }

            if (item.getLogado() == true) {
                quantidadeUsuariosLogados++; // estatistica 2 - número de usuários logados
            }
        }
        
        for (String[] strings : tweets) { // percorre a lista de tweets e conta a quantidade de tweets cadastrados
            quantidadeTweets = tweets.size(); // estatistica 3 - número de tweets no total
        }

        for (Usuario item : usuarios) {
            numTweets = 0; //numero de tweets do usuario atual
           
            for (String[] tweet : tweets) {
                if (tweet[0].equals(item.getLogin())){ //se o login do tweet for igual ao login do usuário atual 
                    numTweets++;
                }
            }
            if (numTweets > numTweetsUsuario) {
                userMaiorTweets = item.getLogin();
                numTweetsUsuario = numTweets;
                //verifica-se se o número de tweets atual do usuário atual (numTweets) é maior do que o número de tweets do usuário que mais tweetou até agora (numTweetsUs)
                //se sim atualiza a variavel e guarda o login
            }
        }

        System.out.println("--> " + quantidadeUsuarios + " usuários cadastrados.");
        System.out.println("--> " + quantidadeUsuariosLogados + " usuários logados.");
        System.out.println("--> " + quantidadeTweets + " tweets no total.");

        if (tweets.size() == 0) {
            System.out.println("--> O Tweets por usuarios no momento.");
        }else{
            System.out.println("--> " + userMaiorTweets + " é o usuário mais ativo com " + numTweetsUsuario + " tweets no total."); // estatistica 5 - usuário que mais tweetou e a quantidade de tweets
        }

        if(tweets.size() > 0){
            for(int i = tweets.size()-1; i >= 0; i--){
                String[] tweet =  tweets.get(i);
                String login = tweet[0];
                String msg = tweet[1]; 
                System.out.println("--> Usuario " + login + " tweetou ' " + msg + " ' por último"); // estatistica 6 - usuário que mais tweetou e a quantidade de tweets

                if(i == tweets.size()-1){
                    ultimoTweetLogin = login; 
                    ultimoTweetMsg = msg;
                    break;
                }
            }
        }else{
            System.out.println("--> Não há tweets para mostrar");
        }
    }
}