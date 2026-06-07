package classes;

public class RegistroPresenca {
    private String nomeCliente;
    private String cpfCliente;
    private int quantidadeIngressos;

    public RegistroPresenca(String nomeCliente, String cpfCliente, int quantidadeIngressos) {
        this.nomeCliente = nomeCliente;
        this.cpfCliente = cpfCliente;
        this.quantidadeIngressos = quantidadeIngressos;
    }

    public String getNomeCliente() { return nomeCliente; }
    public String getCpfCliente() { return cpfCliente; }
    public int getQuantidadeIngressos() { return quantidadeIngressos; }

    public void adicionarIngressos(int quantidade) {
        this.quantidadeIngressos += quantidade;
    }
}