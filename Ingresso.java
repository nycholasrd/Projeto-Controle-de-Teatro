import java.time.LocalDateTime;

public class Ingresso {
    private long id;
    private long idProposta;
    private String cpfCliente;
    private int quantidade;
    private LocalDateTime dataVenda;

    public Ingresso(long idProposta, String cpfCliente, int quantidade) {
        this.id = System.currentTimeMillis();
        this.idProposta = idProposta;
        this.cpfCliente = cpfCliente;
        this.quantidade = quantidade;
        this.dataVenda = LocalDateTime.now();
    }

    public long getId() { return id; }
    public long getIdProposta() { return idProposta; }
    public String getCpfCliente() { return cpfCliente; }
    public int getQuantidade() { return quantidade; }
    public LocalDateTime getDataVenda() { return dataVenda; }
}