package classes;

import enums.Cargo;

public class Usuario extends Pessoa {

    public Usuario(String nome, String email, String senha, String cpf, String telefone) {
        super(nome, cpf, email, Cargo.USUARIO, senha);
        // Nenhuma lógica extra, só usa o construtor da superclasse
    }

    @Override
    public Cargo getCargo() {
        return Cargo.USUARIO;
    }
}
