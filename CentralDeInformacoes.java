import java.util.ArrayList;

public class CentralDeInformacoes {
	private ArrayList<Pessoa> todasAsPessoas = new ArrayList<>();
	
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

	public void setTodasAsPessoas(ArrayList<Pessoa> todasAsPessoas) {
		this.todasAsPessoas = todasAsPessoas;
	}
	
	public Pessoa recuperarPessoaPorCPF(String cpf) {
		for(Pessoa pessoasL : todasAsPessoas) {
			if(pessoasL.getCPF().equals(cpf)) {
				return pessoasL;
			}
		}
			return null;
	}
}
