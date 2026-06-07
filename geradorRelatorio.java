import java.io.FileOutputStream;
import java.util.ArrayList;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class GeradorDeRelatorio{

    public void GeradorDePDF(CentralDeInformacoes ci, PropostaDeAluguel detalhesAluguel, Ingresso ingresso) {
        Document relatorio = new Document();

        try {
            PdfWriter.getInstance(relatorio, new FileOutputStream("relatorioValores.pdf"));
            relatorio.open();
            relatorio.add(new Paragraph("RELATÓRIO DE VALORES"));
            relatorio.add(new Paragraph("/n"));
            relatorio.add(new Paragraph("Lista dos Ingressos:"));
            relatorio.add(new Paragraph("/n"));
            ArrayList<Ingresso> ingressos = ci.getTodosOsIngressos();
            for(Ingresso todosOsIngressos : ingressos) {
                relatorio.add(new Paragraph ("ID:" + todosOsIngressos.getId() + "CPF CLIENTE" + todosOsIngressos.getCpfCliente()));
                relatorio.add(new Paragraph(""));
            }
            relatorio.add(new Paragraph("/n"));
            relatorio.add(new Paragraph("Valor total dos ingressos = R$"+ ci.valorTotalDosIngressos(ingresso.getIdProposta())));
            relatorio.add(new Paragraph("/n"));
            relatorio.add(new Paragraph("/n"));
            relatorio.add(new Paragraph("Valor total da proposta de aluguel = R$"+ detalhesAluguel.valorAluguelPeloPeriodo()));
            relatorio.add(new Paragraph("/n"));
            relatorio.add(new Paragraph("Lucro restantes do locatário = R$" + ci.lucroArtistaLocatario(detalhesAluguel)));
            relatorio.add(new Paragraph("/n"));
        }catch(Exception e) {
            e.printStackTrace();
        }finally{
            if(relatorio.isOpen()) {
                relatorio.close();
            }
        }
    }

    public void GeradorDePDFPorPeriodo(CentralDeInformacoes ci, PropostaDeAluguel detalhesAluguel, Ingresso ingresso, Usuario usuario) {
        Document relatorioP = new Document();

        try {
            PdfWriter.getInstance(relatorioP, new FileOutputStream("relatorioPeriodo.pdf"));
            relatorioP.open();

            relatorioP.add(new Paragraph("RELATÓRIO DE VALORES POR PERÍODO INDICADO"));
            relatorioP.add(new Paragraph("_______________________"));
            relatorioP.add(new Paragraph("Detalhes do Aluguel:"));
            relatorioP.add(new Paragraph("ID da Proposta: " + detalhesAluguel.getId()));
            relatorioP.add(new Paragraph("Período:\n INÍCIO: " + detalhesAluguel.getDataDeInicioDoAluguelDate() + "    FIM: " + detalhesAluguel.getDataDeFimDoAluguel()));
            relatorioP.add(new Paragraph("_______________________"));
            relatorioP.add(new Paragraph("DADOS DA PEÇA"));
            relatorioP.add(new Paragraph("Nome da Peça: " + detalhesAluguel.getNomeDaPeca()));
            relatorioP.add(new Paragraph("\n"));
            relatorioP.add(new Paragraph("[ DADOS DO LOCATÁRIO ]"));
            relatorioP.add(new Paragraph("Nome/Razão Social: " + usuario.getNome()));
            relatorioP.add(new Paragraph("CPF/CNPJ do Locatário: " + usuario.getCPF()));
            relatorioP.add(new Paragraph("\n"));
            relatorioP.add(new Paragraph("______________________"));
            relatorioP.add(new Paragraph("VALOR TOTAL: R$ " + ci.valorTotalDosIngressos(ingresso.getIdProposta())));
            relatorioP.add(new Paragraph("Valor por dia de aluguel : " + detalhesAluguel.valorAluguelPeloPeriodo()));
            relatorioP.add(new Paragraph("Lucro Líquido do Locatário nesta peça: R$ " + ci.lucroArtistaLocatario(detalhesAluguel)));

            relatorioP.add(new Paragraph("______________________"));

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(relatorioP.isOpen()) {
                relatorioP.close();
            }
        }
    }
}