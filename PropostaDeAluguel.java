import java.time.LocalDate;

public class PropostaDeAluguel {
	private long id;
	private LocalDate dataDeCadastroDaProposta;
	private LocalDate dataDeInicioDoAluguelDate;
	private LocalDate dataDeFimDoAluguel;
	private String nomeDaPeca;
	private float valorTotalDoAluguel;
	private Pessoa locatario;
	private Status status;
	private float valorTotal;
	public long getId() {
		return id;
	}

	public String getNomeDaPeca() {
		return nomeDaPeca;
	}
	public float getValorTotalDoAluguel() {
		return valorTotalDoAluguel;
	}
	public Pessoa getLocatario() {
		return locatario;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}


	public boolean isAtivo() {
		if (status == Status.ATIVO&& LocalDate.now().isAfter(dataDeFimDoAluguel)){
			status = Status.INATIVO;
			return false;
		}

		else if (status == Status.EM_AVALIACAO && LocalDate.now().isAfter(dataDeCadastroDaProposta.plusDays(2))) {
			status = Status.NÃO_CONTRATADO;
			return false;
		}
		return true;

	}

	public String toString() {
		return
				"Nome da Peça: <" + nomeDaPeca +"> \n"
				+ "Nome do Locatário: <" + locatario + "> \n"
				+ "n\r\n"
				+ "Está ativo?  <" + isAtivo() + "> \n"
				+ "Status: <"+ status + "> \n";
	}

	public PropostaDeAluguel(LocalDate dataCadastro, LocalDate dataInicio, 
			LocalDate dataFim, String nomePeca, 
			float valorTotal, Pessoa locatario) {
		this.id = System.currentTimeMillis();
		this.dataDeCadastroDaProposta = dataCadastro;
		this.dataDeInicioDoAluguelDate = dataInicio;
		this.dataDeFimDoAluguel = dataFim;
		this.nomeDaPeca = nomePeca;
		this.valorTotal = valorTotal;
		this.locatario = locatario;
		this.status = Status.EM_AVALIACAO;
	}

}
