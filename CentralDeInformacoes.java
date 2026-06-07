package controle;
import java.util.ArrayList;

import classes.Ingresso;
import classes.Pessoa;
import classes.PropostaDeAluguel;
import classes.RegistroPresenca;
import classes.Administrador;

public class CentralDeInformacoes {
    private ArrayList<Pessoa> todasAsPessoas = new ArrayList<>();
    private ArrayList<PropostaDeAluguel> todasAsPropostas = new ArrayList<>();
    private ArrayList<Ingresso> todosOsIngressos = new ArrayList<>();
    private ArrayList<RegistroPresenca> presencas = new ArrayList<>();
    private Administrador administrador;

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public void atualizarAdministrador(String nome, String email, String senha) {
        if (administrador != null) {
            administrador.setNome(nome);
            administrador.setEmail(email);
            administrador.alterarSenha(senha);
        }
    }
    public ArrayList<Ingresso> getIngressos() {
        return todosOsIngressos;
    }
    public ArrayList<RegistroPresenca> getPresencas() {
        return presencas;
    }

    public boolean adicionarPresenca(RegistroPresenca presenca) {

        for (RegistroPresenca p : presencas) {
            if (p.getCpfCliente().equals(presenca.getCpfCliente())) {

                p.adicionarIngressos(presenca.getQuantidadeIngressos());
                return true;
            }
        }
        presencas.add(presenca);
        return true;
    }

    public boolean removerPresencaPorCpf(String cpf) {
        return presencas.removeIf(p -> p.getCpfCliente().equals(cpf));
    }

    public boolean adicionarIngresso(Ingresso ingresso) {
        for (Ingresso i : todosOsIngressos) {
            if (i.getId() == ingresso.getId()) {
                return false;
            }
        }
        todosOsIngressos.add(ingresso);
        return true;
    }


    public boolean adicionarPessoas(Pessoa pessoa) {
        for(Pessoa testeP : todasAsPessoas) {
            if(testeP.getCPF().equals(pessoa.getCPF())){
                return false;
            }
        }
        todasAsPessoas.add(pessoa);
        return true;

    }

    public ArrayList<Pessoa> getTodasAsPessoas() {
        return todasAsPessoas;
    }
    public ArrayList<PropostaDeAluguel> getTodasAsPropostas() {
        return todasAsPropostas;
    }

    public void setTodasAsPessoas(ArrayList<Pessoa> todasAsPessoas) {
        this.todasAsPessoas = todasAsPessoas;
    }
    public Pessoa autenticarPessoas(String email, String senha) {

        if (administrador != null && administrador.autenticar(email, senha)) {
            return administrador;
        }


        for (Pessoa p : todasAsPessoas) {
            if (p.autenticar(email, senha)) {
                return p;
            }
        }


        return null;
    }


    public boolean adicionarPropostas(PropostaDeAluguel p) {
        for(PropostaDeAluguel testePp : todasAsPropostas) {
            if(testePp.getId() == p.getId()) {
                return false;
            }
        }
        todasAsPropostas.add(p);
        return true;
    }

    public PropostaDeAluguel recuperarPropostaPorId(long id) {
        for (PropostaDeAluguel proposta : todasAsPropostas) {
            if (proposta.getId() == id) {
                return proposta;
            }
        }
        return null;
    }

    public ArrayList<PropostaDeAluguel> recuperarPropostaDeUmaPessoa(String cpf) {
        ArrayList<PropostaDeAluguel> propostasPorLocatario = new ArrayList<>();


        for(PropostaDeAluguel procura : todasAsPropostas) {
            if(procura.getLocatario() == null) {
                return null;
            }
            if(procura.getLocatario().equals(cpf)) {
                propostasPorLocatario.add(procura);
            }
        }
        return propostasPorLocatario;
    }
    public Pessoa recuperarPessoaPorCPF(String cpf) {
        for(Pessoa pessoasL : todasAsPessoas) {
            if(pessoasL.getCPF().equals(cpf)) {
                return pessoasL;
            }
        }
        return null;
    }

    public double lucroArtistaLocatario(PropostaDeAluguel pa) {
        double valor = valorTotalDosIngressos(pa.getId()) - pa.getValorTotalDoAluguel();
        return valor;
    }

    public double valorTotalDosIngressos(long idProposta) {
        double total = 0.0;
        for (Ingresso ingresso : todosOsIngressos) {
            if (ingresso.getIdProposta() == idProposta) {
                total += (ingresso.getValorUnitario() * ingresso.getQuantidade());
            }
        }
        return total;
    }

    public ArrayList<Ingresso> getTodosOsIngressos() {
        return todosOsIngressos;
    }

    public String estenderContrato(long idProposta, LocalDate novaDataFim) {
        PropostaDeAluguel peca = recuperarPropostaPorld(idProposta);


        if (peca == null || (peca.getStatus() != Status.ATIVO && peca.getStatus() != Status.CONTRATADO_COM_ALTERACAO)) {
            return "ERRO: O contrato não existe ou não está ativo no momento.";
        }


        if (!novaDataFim.isAfter(peca.getDataDeFimDoAluguel())) {
            return "ERRO: A nova data de fim deve ser posterior à data de término atual (" + peca.getDataDeFimDoAluguel() + ").";
        }


        LocalDate inicioExtensao = peca.getDataDeFimDoAluguel().plusDays(1);


        for (PropostaDeAluguel outraPeca : todasAsPropostas) {

            if (outraPeca.getId() != idProposta &&
                    (outraPeca.getStatus() == Status.ATIVO || outraPeca.getStatus() == Status.EM_AVALIACAO || outraPeca.getStatus() == Status.CONTRATADO_COM_ALTERACAO)) {


                boolean temConflito = !inicioExtensao.isAfter(outraPeca.getDataDeFimDoAluguel()) && !novaDataFim.isBefore(outraPeca.getDataDeInicioDoAluguelDate());

                if (temConflito) {
                    return "ERRO: Conflito de calendário! As datas colidem com a peça: '" + outraPeca.getNomeDaPeca() + "'.";
                }
            }
        }

        long diasOriginais = ChronoUnit.DAYS.between(peca.getDataDeInicioDoAluguelDate(), peca.getDataDeFimDoAluguel()) + 1;
        float valorDiaria = peca.getValorTotalDoAluguel() / diasOriginais;


        long diasExtras = ChronoUnit.DAYS.between(inicioExtensao, novaDataFim) + 1;
        float valorAdicional = valorDiaria * diasExtras;


        peca.setDataDeFimDoAluguel(novaDataFim);
        peca.setValorTotalDoAluguel(peca.getValorTotalDoAluguel() + valorAdicional);
        peca.setStatus(Status.CONTRATADO_COM_ALTERACAO);


        return "SUCESSO: Contrato estendido até " + novaDataFim + ".\nValor adicional cobrado: R$ " + String.format("%.2f", valorAdicional) + "\nNovo valor total do aluguer: R$ " + String.format("%.2f", peca.getValorTotalDoAluguel());
    }