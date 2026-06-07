package main;

import controle.CentralDeInformacoes;
import controle.Persistencia;
import classes.Administrador;
import classes.Pessoa;
import telas.TelaCadastro;
import telas.TelaLogin;
import telas.DashboardUsuario;
import admin.DashboardAdmin;

public class Main {
    public static void main(String[] args) {
        Persistencia persistencia = new Persistencia();
        CentralDeInformacoes central = persistencia.recuperarCentral("dados.xml");

        if (central.getTodasAsPessoas().isEmpty()) {
            new TelaCadastro();
            return;
        }

        
        TelaLogin telaLogin = new TelaLogin();
        Pessoa pessoaLogada = telaLogin.getPessoaLogada(); // precisa implementar esse getter

        if (pessoaLogada instanceof Administrador) {
            new DashboardAdmin((Administrador) pessoaLogada);
        } else if (pessoaLogada != null) {
            new DashboardUsuario(central, pessoaLogada);
        }
    }
}
