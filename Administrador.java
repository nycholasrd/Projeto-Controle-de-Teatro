package classes;

import enums.Cargo;

public class Administrador extends Pessoa {

    public Administrador(String nome, String email, String cpf, String senha, String telefone) {
        super(nome, email, cpf, Cargo.ADMINISTRADOR, senha);
    }

    public boolean autenticar(String email, String senha) {
        return getEmail().equals(email) && getSenha().equals(senha);
    }

    public void alterarSenha(String novaSenha) {
        setSenha(novaSenha);
    }
}
