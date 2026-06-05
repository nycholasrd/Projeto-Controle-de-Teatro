
import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDate;

public class CentralDeInformacoes {
    private ArrayList<Pessoa> todasAsPessoas = new ArrayList<>();
    private ArrayList<PropostaDeAluguel> todasAsPropostas = new ArrayList<>();
    private ArrayList<RegraPrecoTeatro> todasAsRegrasPreco = new ArrayList<>();
    private ArrayList<Ingresso> todosOsIngressos = new ArrayList<>();

    public boolean adicionarPessoas(Pessoa pessoa) {
        for (Pessoa testeP : todasAsPessoas) {
            if (testeP.getCpf().equals(pessoa.getCpf())) {
                return false;
            }
        }
        todasAsPessoas.add(pessoa);
        return true;

    }

    public ArrayList<Pessoa> getTodasAsPessoas() {
        return todasAsPessoas;
    }

    public void setTodasAsPessoas(ArrayList<Pessoa> todasAsPessoas) {
        this.todasAsPessoas = todasAsPessoas;
    }

    public boolean adicionarPropostas(PropostaDeAluguel p) {
        for (PropostaDeAluguel testePp : todasAsPropostas) {
            if (testePp.getId() == p.getId()) {
                return false;
            }
        }
        todasAsPropostas.add(p);
        return true;
    }

    public PropostaDeAluguel recuperarPropostaPorld(long id) {
        for (PropostaDeAluguel testePp : todasAsPropostas) {
            if (testePp.getId() == id) {
                return testePp;
            }
        }
        return null;
    }

    public ArrayList<PropostaDeAluguel> getTodasAsPropostas() {
        return todasAsPropostas;
    }

    public ArrayList<PropostaDeAluguel> recuperarPropostaDeUmaPessoa(String cpf) {
        ArrayList<PropostaDeAluguel> propostasPorLocatario = new ArrayList<>();


        for (PropostaDeAluguel procura : todasAsPropostas) {
            if (procura.getLocatario() == null) {
                continue;
            }
            if (procura.getLocatario().equals(cpf)) {
                propostasPorLocatario.add(procura);
            }
        }
        return propostasPorLocatario;
    }

    public Pessoa recuperarPessoaPorCPF(String cpf) {
        for (Pessoa pessoasL : todasAsPessoas) {
            if (pessoasL.getCpf().equals(cpf)) {
                return pessoasL;
            }
        }
        return null;
    }

    public boolean adicionarRegra(RegraPrecoTeatro regra) {
        for (RegraPrecoTeatro testeR : todasAsRegrasPreco) {
            if (testeR.getId() == regra.getId()) {
                return false;
            }
        }
        todasAsRegrasPreco.add(regra);
        return true;
    }

    public RegraPrecoTeatro recuperarRegra() {
        for (RegraPrecoTeatro mostra : todasAsRegrasPreco) {
            if (mostra != null) {
                return mostra;
            }
        }
        return null;
    }

    public ArrayList<RegraPrecoTeatro> selecionarRegra(int id) {
        ArrayList<RegraPrecoTeatro> regraSelecionada = new ArrayList<>();
        for (RegraPrecoTeatro selecao : todasAsRegrasPreco) {
            if (selecao.getId() == id) {
                regraSelecionada.add(selecao);
            }
        }
        return regraSelecionada;
    }

    public boolean excluirRegraPreco(int id) {
        for (int i = 0; i < todasAsRegrasPreco.size(); i++) {
            if (todasAsRegrasPreco.get(i).getId() == id) {
                todasAsRegrasPreco.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean editarRegraPreco(RegraPrecoTeatro novaRegra) {
        for (int i = 0; i < todasAsRegrasPreco.size(); i++) {
            if (todasAsRegrasPreco.get(i).getId() == novaRegra.getId()) {
                todasAsRegrasPreco.set(i, novaRegra);
                return true;
            }
        }
        return false;
    }


    public ArrayList<PropostaDeAluguel> filtrarPropostas(String termoBusca, Status statusProcurado) {
        ArrayList<PropostaDeAluguel> propostasFiltradas = new ArrayList<>();

        for (PropostaDeAluguel proposta : todasAsPropostas) {

            boolean atendeFiltroTexto = false;
            boolean atendeFiltroStatus = false;


            if (termoBusca == null || termoBusca.trim().isEmpty()) {
                atendeFiltroTexto = true;
            } else {
                String busca = termoBusca.toLowerCase();


                String locatario = proposta.getLocatario() != null ? proposta.getLocatario().toLowerCase() : "";
                String peca = proposta.getNomeDaPeca() != null ? proposta.getNomeDaPeca().toLowerCase() : "";

                if (locatario.contains(busca) || peca.contains(busca)) {
                    atendeFiltroTexto = true;
                }
            }


            if (statusProcurado == null) {
                atendeFiltroStatus = true;
            } else if (proposta.getStatus() == statusProcurado) {
                atendeFiltroStatus = true;
            }


            if (atendeFiltroTexto && atendeFiltroStatus) {
                propostasFiltradas.add(proposta);
            }
        }

        return propostasFiltradas;
    }

    public boolean promoverParaContrato(long idProposta) {
        PropostaDeAluguel proposta = recuperarPropostaPorld(idProposta);

        if (proposta != null && proposta.getStatus() == Status.EM_AVALIACAO) {
            proposta.setStatus(Status.ATIVO);
            return true;
        }

        return false;
    }

    public boolean realizarVendaIngresso(String cpf, long idProposta, int quantidade) {
        PropostaDeAluguel peca = recuperarPropostaPorld(idProposta);


        if (peca != null && peca.getStatus() == Status.ATIVO) {
            Ingresso novaVenda = new Ingresso(idProposta, cpf, quantidade);
            todosOsIngressos.add(novaVenda);



            return true;
        }

        return false;
    }

    public ArrayList<Ingresso> getTodosOsIngressos() {
        return todosOsIngressos;
    }
    public ArrayList<RegistroPresenca> gerarListaPresenca(long idProposta, LocalDate dataFiltro) {
        HashMap<String, RegistroPresenca> mapaPresenca = new HashMap<>();

        for (Ingresso ingresso : todosOsIngressos) {
            if (ingresso.getIdProposta() == idProposta) {

                boolean atendeData = true;
                if (dataFiltro != null) {
                    LocalDate dataCompra = ingresso.getDataVenda().toLocalDate();
                    if (!dataCompra.equals(dataFiltro)) {
                        atendeData = false;
                    }
                }

                if (atendeData) {
                    String cpf = ingresso.getCpfCliente();

                    if (mapaPresenca.containsKey(cpf)) {
                        RegistroPresenca reg = mapaPresenca.get(cpf);
                        reg.adicionarIngressos(ingresso.getQuantidade());
                    } else {
                        Pessoa cliente = recuperarPessoaPorCPF(cpf);
                        String nome = (cliente != null) ? cliente.getNome() : "Nome não encontrado";

                        mapaPresenca.put(cpf, new RegistroPresenca(nome, cpf, ingresso.getQuantidade()));
                    }
                }
            }
        }

        return new ArrayList<>(mapaPresenca.values());
    }
}
