import java.util.*;
public class MainAquecimento {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Persistencia pers = new Persistencia();
		String arquivo = "central.xml";
		CentralDeInformacoes central = pers.recuperarCentral(arquivo);

		String opcao = "";

		while (!opcao.equalsIgnoreCase("S")) {
			System.out.println("====MENU====");
			System.out.println("1 - Nova pessoa");
			System.out.println("2 - Listar todas as pessoas");
			System.out.println("3 - Exibir informações de uma pessoa");
			System.out.println("S - Sair");
			opcao = sc.nextLine().toUpperCase();
			switch (opcao) {
			case "1":
				System.out.println("Nome:");
				String nome = sc.nextLine();
				System.out.println("Email:");
				String email = sc.nextLine();
				System.out.println("CPF:");
				String CPF = sc.nextLine();
				Pessoa p = new Pessoa(nome, email, CPF);
				while (p.getSexo() == null) {
					System.out.println("Sexo:\n 1 - Masculino"
							+ "\n 2 - Feminino");
					int sexo = Integer.parseInt(sc.nextLine());
					if (sexo == 1) {
						p.setSexo(Sexo.MASCULINO);
					} else if (sexo == 2) {
						p.setSexo(Sexo.FEMININO);
					} else {
						System.out.println("Não foi possível identificar a opção! Tente novamente!");
					}
				}
				if (central.adicionarPessoas(p) == true) {
					pers.salvarCentral(central, arquivo);
					System.out.println("Pessoa cadastrada!");
				} else {
					System.out.println("Pessoa já existente!");
				}
				break;
			case "2":
				for (Pessoa pessoa : central.getTodasAsPessoas()) {
					System.out.println(pessoa);
				}
				break;
			case "3":

			    System.out.println("CPF da pessoa:");
			    String fakeCPF= sc.nextLine();
			    Pessoa fakePerson = new  Pessoa("","", fakeCPF);
			    boolean encontrou = false;

			    for (Pessoa person : central.getTodasAsPessoas()) {

			        if (person.equals(fakePerson)) {

			            System.out.println(
			                "Pessoa [nome=" + person.getNome()
			                + ", email=" + person.getEmail()
			                + ", CPF=" + person.getCPF()
			                + ", Gênero=" + person.getSexo()
			                + "]"
			            );

			            encontrou = true;

			            break;
			        }
			    }

			    if (!encontrou) {
			        System.out.println("Pessoa não encontrada!");
			    }
				break;
			case "S":
				System.out.println("Finalizando o programa!");
				break;
			default:
				System.out.println("Não encontrada a opção");
				break;
			}
		}
	}

}
