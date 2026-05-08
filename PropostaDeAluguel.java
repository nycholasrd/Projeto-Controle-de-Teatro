import java.time.LocalDate;

public class PropostaDeAluguel {
	private long id;
	private LocalDate dataDeCadastroDaProposta;
	private LocalDate dataDeInicioDoAluguelDate;
	private LocalDate dataDeFimDoAluguel;
	private String nomeDaPeca;
	private float valorTotalDoAluguel;
	private String locatario;
	private Status status;
	public long getId() {
		return id;
	}

	public String getNomeDaPeca() {
		return nomeDaPeca;
	}
	public float getValorTotalDoAluguel() {
		return valorTotalDoAluguel;
	}
	public String getLocatario() {
		return locatario;
	}
	public void setId(long id) {
		this.id = System.currentTimeMillis();
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
	
	public PropostaDeAluguel(LocalDate dataDeCadastroDaProposta, LocalDate dataDeInicioDoAluguelDate, LocalDate dataDeFimDoAluguel, String nomeDaPeca, float valorTotalDoAluguel, String locatario) {
		this.dataDeCadastroDaProposta = dataDeCadastroDaProposta;
		this.dataDeInicioDoAluguelDate = dataDeInicioDoAluguelDate;
		this.dataDeFimDoAluguel = dataDeFimDoAluguel;
		this.nomeDaPeca = nomeDaPeca;
		this.valorTotalDoAluguel = valorTotalDoAluguel;
		this.locatario = locatario;
	}
	
	
}
