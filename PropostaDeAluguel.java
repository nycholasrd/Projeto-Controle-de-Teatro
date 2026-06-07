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


    private float precoDoIngresso;
    private LocalDate dataDeEncerramentoEfetiva;


    public long getId() { return id; }
    public String getNomeDaPeca() { return nomeDaPeca; }
    public Status getStatus() { return status; }
    public float getValorTotalDoAluguel() { return valorTotalDoAluguel; }
    public String getLocatario() { return locatario; }
    public float getPrecoDoIngresso() { return precoDoIngresso; }
    public LocalDate getDataDeEncerramentoEfetiva() { return dataDeEncerramentoEfetiva; }


    public LocalDate getDataDeInicioDoAluguelDate() { return dataDeInicioDoAluguelDate; }
    public LocalDate getDataDeFimDoAluguel() { return dataDeFimDoAluguel; }


    public void setDataDeFimDoAluguel(LocalDate dataDeFimDoAluguel) { this.dataDeFimDoAluguel = dataDeFimDoAluguel; }
    public void setValorTotalDoAluguel(float valorTotalDoAluguel) { this.valorTotalDoAluguel = valorTotalDoAluguel; }
    public void setStatus(Status status) { this.status = status; }
    public void setPrecoDoIngresso(float precoDoIngresso) { this.precoDoIngresso = precoDoIngresso; }
    public void setDataDeEncerramentoEfetiva(LocalDate dataDeEncerramentoEfetiva) { this.dataDeEncerramentoEfetiva = dataDeEncerramentoEfetiva; }

    public void setId(long id) {
        this.id = id;
    }


    public boolean isAtivo() {
        if (status == Status.ATIVO && LocalDate.now().isAfter(dataDeFimDoAluguel)){
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
                        + "Preço do Ingresso: <R$ " + precoDoIngresso + "> \n"
                        + "\n"
                        + "Está ativo?  <" + isAtivo() + "> \n"
                        + "Status: <"+ status + "> \n";
    }

    public PropostaDeAluguel(LocalDate dataDeCadastroDaProposta, LocalDate dataDeInicioDoAluguelDate, LocalDate dataDeFimDoAluguel, String nomeDaPeca, float valorTotalDoAluguel, float precoDoIngresso, String locatario) {
        this.dataDeCadastroDaProposta = dataDeCadastroDaProposta;
        this.dataDeInicioDoAluguelDate = dataDeInicioDoAluguelDate;
        this.dataDeFimDoAluguel = dataDeFimDoAluguel;
        this.nomeDaPeca = nomeDaPeca;
        this.valorTotalDoAluguel = valorTotalDoAluguel;
        this.precoDoIngresso = precoDoIngresso;
        this.locatario = locatario;
        this.status = Status.EM_AVALIACAO;
        this.id = System.currentTimeMillis();
    }
}