import java.util.ArrayList;

public class CentralDeInformacoes {
	private ArrayList<Pessoa> todasAsPessoas = new ArrayList<>();
	private ArrayList<PropostaDeAluguel> todasAsPropostas = new ArrayList<>();
	
	
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
}
