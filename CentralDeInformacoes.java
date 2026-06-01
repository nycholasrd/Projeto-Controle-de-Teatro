
import java.util.ArrayList;

public class CentralDeInformacoes {
	private ArrayList<Pessoa> todasAsPessoas = new ArrayList<>();
	private ArrayList<PropostaDeAluguel> todasAsPropostas = new ArrayList<>();
	private ArrayList<RegraPrecoTeatro> todasAsRegrasPreco = new ArrayList<>();
	
	
	public boolean adicionarPessoas(Pessoa pessoa) {
		for(Pessoa testeP : todasAsPessoas) {
			if(testeP.getCpf().equals(pessoa.getCpf())){
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
		for(PropostaDeAluguel testePp : todasAsPropostas) {
			if(testePp.getId() == p.getId()) {
				return false;
			}
		}
		todasAsPropostas.add(p);
		return true;
	}
	
	public PropostaDeAluguel recuperarPropostaPorld(long id) {
		for(PropostaDeAluguel testePp : todasAsPropostas) {
			if(testePp.getId() == id) {
				return testePp;
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
			if(pessoasL.getCpf().equals(cpf)) {
				return pessoasL;
			}
		}
			return null;
	}
	
	public boolean adicionarRegra(RegraPrecoTeatro regra) {
		for(RegraPrecoTeatro testeR : todasAsRegrasPreco) {
			if(testeR.getId() == regra.getId()){
				return false;
			}
		}
		todasAsRegrasPreco.add(regra);
		return true;
	}
	
	public RegraPrecoTeatro recuperarRegra(){
		for(RegraPrecoTeatro mostra : todasAsRegrasPreco) {
			if(mostra != null) {
				return mostra;
			}
		}
		return null;
	}
	// Diferente do método Recuperar Regra, este método tem a funcão de selecionar uma regra e criar uma nova lista com essa regra;
	public ArrayList<RegraPrecoTeatro> selecionarRegra(int id) {
		ArrayList<RegraPrecoTeatro> regraSelecionada = new ArrayList<>();
		for(RegraPrecoTeatro selecao : todasAsRegrasPreco) {
			if(selecao.getId() == id) {
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
		for(int i = 0; i < todasAsRegrasPreco.size(); i++) {
			if(todasAsRegrasPreco.get(i).getId() == novaRegra.getId()) {
				todasAsRegrasPreco.set(i, novaRegra);
				return true;
			}
		}
		return false;
	}
}
