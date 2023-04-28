public class Usuario {
    private String nome, login, email, senha;
    private boolean logado;

    public Usuario(String nome, String login, String email, String senha, boolean logado) {
        this.nome = nome;
        this.login = login;
        this.email = email;
        this.senha = senha;
        this.logado = logado;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha(String senha) {
        return senha;
    }

    public String getSenha(){
        return senha; 
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean getLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }

    public boolean verificaSenha(String senha){
        return this.senha.equals(senha); 
    }
}
