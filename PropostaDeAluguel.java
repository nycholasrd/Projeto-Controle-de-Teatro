package classes;
import java.time.LocalDate;

import enums.Status;
import java.time.LocalDate;

public class PropostaDeAluguel {
    private long id;
    private LocalDate dataDeCadastroDaProposta;
    private LocalDate dataDeInicioDoAluguelDate;
    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getDataDeCadastroDaProposta() {
		return dataDeCadastroDaProposta;
	}

	public void setDataDeCadastroDaProposta(LocalDate dataDeCadastroDaProposta) {
		this.dataDeCadastroDaProposta = dataDeCadastroDaProposta;
	}

	public String getNomeDaPeca() {
		return nomeDaPeca;
	}

	public void setNomeDaPeca(String nomeDaPeca) {
		this.nomeDaPeca = nomeDaPeca;
	}

	public float getValorTotalDoAluguel() {
		return valorTotalDoAluguel;
	}

	public void setValorTotalDoAluguel(float valorTotalDoAluguel) {
		this.valorTotalDoAluguel = valorTotalDoAluguel;
	}

	public String getLocatario() {
		return locatario;
	}

	public void setLocatario(String locatario) {
		this.locatario = locatario;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	private LocalDate dataDeFimDoAluguel;
    private String nomeDaPeca;
    private float valorTotalDoAluguel;
    private float precoTicket;
    private String locatario;
    private Status status;

    public PropostaDeAluguel(LocalDate dataDeCadastroDaProposta, LocalDate dataDeInicioDoAluguelDate,
                             LocalDate dataDeFimDoAluguel, String nomeDaPeca,
                             float valorTotalDoAluguel, float precoTicket, String locatario) {
        this.id = System.currentTimeMillis();
        this.dataDeCadastroDaProposta = dataDeCadastroDaProposta;
        this.dataDeInicioDoAluguelDate = dataDeInicioDoAluguelDate;
        this.dataDeFimDoAluguel = dataDeFimDoAluguel;
        this.nomeDaPeca = nomeDaPeca;
        this.valorTotalDoAluguel = valorTotalDoAluguel;
        this.precoTicket = precoTicket;
        this.locatario = locatario;
        this.status = Status.EM_AVALIACAO;
    }

    public float getPrecoTicket() { return precoTicket; }
    public void setPrecoTicket(float precoTicket) { this.precoTicket = precoTicket; }

    public float valorAluguelPeloPeriodo() {
        long dias = java.time.temporal.ChronoUnit.DAYS.between(dataDeInicioDoAluguelDate, dataDeFimDoAluguel);
        return valorTotalDoAluguel * dias;
    }

	public boolean isAtivo() {
		if (status == Status.ATIVO&& LocalDate.now().isAfter(getDataDeFimDoAluguel())){
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
        this.status = Status.EM_AVALIACAO;
	}

	public LocalDate getDataDeInicioDoAluguelDate() {
		return dataDeInicioDoAluguelDate;
	}

	public LocalDate getDataDeFimDoAluguel() {
		return dataDeFimDoAluguel;
	}
	
	
}
