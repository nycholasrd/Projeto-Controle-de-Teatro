package classes;
import java.io.FileOutputStream;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import controle.CentralDeInformacoes;
//Nycholas: gera um relatorio pdf que retorna a lista dos ingressos e o valor total da proposta de Aluguel;
public class GeradorDeRelatorios{
	
	public void GeradorDePDF(CentralDeInformacoes ci, PropostaDeAluguel detalhesAluguel, Ingresso ingresso) {
		Document relatorio = new Document();
		
		try {
			PdfWriter.getInstance(relatorio, new FileOutputStream("relatorioValores.pdf"));
			relatorio.open();
			relatorio.add(new Paragraph("RELATÓRIO DE VALORES"));
			relatorio.add(new Paragraph("/n"));
			relatorio.add(new Paragraph("Lista dos Ingressos:"));
			relatorio.add(new Paragraph("/n"));
			ArrayList<Ingresso> ingressos = ci.getIngressos();
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
}