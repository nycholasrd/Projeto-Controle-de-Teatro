import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Persistencia {

    public void salvarCentral(CentralDeInformacoes central, String nomeArquivo) {

        XStream xstream = new XStream(new DomDriver());

        xstream.addPermission(AnyTypePermission.ANY);

        String xml = xstream.toXML(central);

        try (PrintWriter writer = new PrintWriter(new File(nomeArquivo))) {
            writer.print(xml);
            System.out.println("Dados salvos com sucesso no arquivo: " + nomeArquivo);
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao criar o arquivo: " + e.getMessage());
        }
    }

    public CentralDeInformacoes recuperarCentral(String nomeArquivo) {
        File arquivo = new File(nomeArquivo);

        if (!arquivo.exists()) {
            System.out.println("Arquivo não encontrado. Retornando uma nova Central de Informações vazia.");
            return new CentralDeInformacoes();
        }

        XStream xstream = new XStream(new DomDriver());
        xstream.addPermission(AnyTypePermission.ANY);

        try {
            FileInputStream fis = new FileInputStream(arquivo);
            return (CentralDeInformacoes) xstream.fromXML(fis);
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return new CentralDeInformacoes();
        }
    }
}