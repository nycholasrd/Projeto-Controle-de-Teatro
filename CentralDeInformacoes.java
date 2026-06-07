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
		// Evita duplicidade pelo CPF
		for (RegistroPresenca p : presencas) {
			if (p.getCpfCliente().equals(presenca.getCpfCliente())) {
				// Se já existe, apenas soma ingressos
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
				return false; // já existe
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
	    // Primeiro verifica administrador
	    if (administrador != null && administrador.autenticar(email, senha)) {
	        return administrador;
	    }

	    // Depois verifica todas as pessoas cadastradas
	    for (Pessoa p : todasAsPessoas) {
	        if (p.autenticar(email, senha)) {
	            return p;
	        }
	    }

	    // Se não encontrar ninguém
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
	// Nycholas: metodo retornar o lucro do artista;
    public double lucroArtistaLocatario(PropostaDeAluguel pa) {
        double valor = valorTotalDosIngressos(pa.getId()) - pa.getValorTotalDoAluguel();
        return valor;
    }
    
    //Nycholas: Método para retornar o produto de todos ingressos vendidos(valor total);
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
}
