package controle;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import classes.PropostaDeAluguel;

import java.io.FileOutputStream;
import java.io.FileNotFoundException;

public class GeradorDeContratos {

    public static void obterContrato(long idProposta, CentralDeInformacoes central) {
        PropostaDeAluguel proposta = central.recuperarPropostaPorId(idProposta);

        if (proposta == null) {
            System.out.println("Erro: Nenhuma proposta encontrada com o ID " + idProposta);
            return;
        }
        Document documento = new Document();
        try {
            PdfWriter.getInstance(documento, new FileOutputStream("contrato.pdf"));
            documento.open();
            documento.add(new Paragraph("Contato de aluguel"));
            documento.add(new Paragraph("\n"));
            documento.add(new Paragraph("Pelo presente instrumento particular, de um lado o LOCADOR, " + "e de outro lado o LOCATÁRIO identificado como: " + proposta.getLocatario() + "."));
            documento.add(new Paragraph("\n"));
            documento.add(new Paragraph("Fica acordado o aluguel do seguinte item: " + proposta.getNomeDaPeca()));
            documento.add(new Paragraph("Valor total acordado: R$ " + proposta.getValorTotalDoAluguel()));
            documento.add(new Paragraph("\n"));
            documento.add(new Paragraph("As partes assumem o compromisso de zelar pela peça durante o período de locação " + "e devolvê-la no estado em que foi entregue."));
            documento.add(new Paragraph("\n\n\n"));
            documento.add(new Paragraph("__"));
            	documento.add(new Paragraph(" Assinatura do Locador "));
            documento.add(new Paragraph("\n\n"));
            documento.add(new Paragraph("Assinatura do Locatário (" + proposta.getLocatario() + ")"));

            System.out.println("Contrato gerado com sucesso! Verifique o arquivo 'contrato.pdf' na pasta do projeto.");

        } catch (FileNotFoundException | DocumentException e) {
            System.out.println("Erro ao gerar o PDF: " + e.getMessage());
        } finally {
            if (documento.isOpen()) {
                documento.close();
            }
        }
    }
}
