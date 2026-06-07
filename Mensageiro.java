package controle;
import java.io.File;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Authenticator;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
public class Mensageiro {
	public static void enviarEmail(String destinatario, String arquivos) throws Exception{
		final String remetente = "EMAL@gmail.com";
		final String senha = "SUASENHA";

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		Authenticator autenticador = new Authenticator() {
			 protected PasswordAuthentication getPasswordAuthentication() {

			        return new PasswordAuthentication(remetente, senha);
			    }
		};
		
		Session sessao = Session.getInstance(prop, autenticador);
		Message mensagem = new MimeMessage(sessao);
		
		mensagem.setFrom(new InternetAddress(remetente)); // Esse método lança uma exceção, para não dar erro, coloquei throws exception
		mensagem.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(destinatario)
        );
		
		mensagem.setSubject("Contrato de aluguel");   BodyPart texto = new MimeBodyPart();

        texto.setText("Segue em anexo o contrato.");

        MimeBodyPart anexo = new MimeBodyPart();

        File arquivo = new File(arquivos);

        FileDataSource source = new FileDataSource(arquivo);

        anexo.setDataHandler(new DataHandler(source));

        anexo.setFileName(arquivo.getName());

        Multipart multipart = new MimeMultipart();

        multipart.addBodyPart(texto);

        multipart.addBodyPart(anexo);

        mensagem.setContent(multipart);

        Transport.send(mensagem);

        System.out.println("Email enviado!");
    }
}
