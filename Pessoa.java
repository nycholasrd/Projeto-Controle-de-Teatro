package classes;

import enums.Cargo;
import enums.Sexo;

public abstract class Pessoa {
    private String nome;
    private String email;
    private Sexo sexo;
    private String cpf;
    private Cargo cargo;
    private String senha; 

    public Pessoa(String nome, String email, String cpf, Cargo cargo, String senha) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.cargo = cargo;
        this.senha = senha;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCPF() { return cpf; }

    public Sexo getSexo() { return sexo; }
    public void setSexo(Sexo sexo) { this.sexo = sexo; }

    public Cargo getCargo() { return cargo; }
    public void setCargo(Cargo cargo) { this.cargo = cargo; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public boolean autenticar(String email, String senha) {
        return this.email.equals(email) && this.senha.equals(senha);
    }

    @Override
    public String toString() {
        return "Pessoa [Nome: " + nome + "]";
    }
	public boolean equals(Pessoa p) {
		String splitcpf = getCPF();
		String cpf1 = splitcpf.replace(".", "").replace("-", "");
		String pessoaSplitcpf = p.getCPF();
		String cpf2 = pessoaSplitcpf.replace(".", "").replace("-", "");
		return cpf1.equals(cpf2);
	}


}
