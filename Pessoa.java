
public class Pessoa {
	private String nome;
	private String email;
	private Sexo sexo;
	private String cpf;


	public Pessoa(String nome, String email, String cpf) {
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCPF() {
		return cpf;
	}
	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	public String toString() {
		return "Pessoa [Nome:" + " " + nome + "]";
	}

	public boolean equals(Pessoa p) {
		String splitcpf = getCPF();
		String cpf1 = splitcpf.replace(".", "").replace("-", "");
		String pessoaSplitcpf = p.getCPF();
		String cpf2 = pessoaSplitcpf.replace(".", "").replace("-", "");
		return cpf1.equals(cpf2);
	}


}
