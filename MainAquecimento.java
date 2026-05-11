import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
			System.out.println("4 - Nova proposta");
			System.out.println("5 - Informar quantidade de propostas cadastradas");
			System.out.println("6 - Detalhar uma proposta");
			System.out.println("7 - Ativar uma proposta");
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
			case "4": // NOVA PROPOSTA
                System.out.println("\n=== NOVA PROPOSTA DE ALUGUEL ===");
                
               
                System.out.print("CPF do locatário: ");
                String cpfLocatario = sc.nextLine();
                Pessoa locatario = central.recuperarPessoaPorCPF(cpfLocatario);
                
                if (locatario == null) {
                    System.out.println("Erro: Pessoa não encontrada! Cadastre a pessoa primeiro.");
                    break;
                }
                

                LocalDate dataCadastro = LocalDate.now();
                
                
                LocalDate dataInicio = null;
                while (dataInicio == null) {
                    System.out.print("Data de início do aluguel (dd/MM/yyyy): ");
                    String dataInicioStr = sc.nextLine();
                    try {
                        dataInicio = LocalDate.parse(dataInicioStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        if (dataInicio.isBefore(LocalDate.now())) {
                            System.out.println("Erro: Data de início não pode ser anterior a hoje!");
                            dataInicio = null;
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("Formato inválido! Use dd/MM/yyyy");
                    }
                }
                
               
                LocalDate dataFim = null;
                while (dataFim == null) {
                    System.out.print("Data de fim do aluguel (dd/MM/yyyy): ");
                    String dataFimStr = sc.nextLine();
                    try {
                        dataFim = LocalDate.parse(dataFimStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        if (!dataFim.isAfter(dataInicio)) {
                            System.out.println("Erro: Data de fim deve ser posterior à data de início!");
                            dataFim = null;
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("Formato inválido! Use dd/MM/yyyy");
                    }
                }
                
             
                System.out.print("Nome da peça: ");
                String nomePeca = sc.nextLine();
                
         
                float valorTotal = 0;
                while (valorTotal <= 0) {
                    System.out.print("Valor total do aluguel: R$ ");
                    try {
                        valorTotal = Float.parseFloat(sc.nextLine());
                        if (valorTotal <= 0) {
                            System.out.println("Valor deve ser positivo!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Valor inválido!");
                    }
                }
                
                
                PropostaDeAluguel proposta = new PropostaDeAluguel(
                    dataCadastro, dataInicio, dataFim, 
                    nomePeca, valorTotal, locatario
                );
                
                if (central.adicionarPropostas(proposta)) {
                    pers.salvarCentral(central, arquivo);
                    System.out.println("Proposta cadastrada com sucesso!");
                    System.out.println("ID da proposta: " + proposta.getId());
                } else {
                    System.out.println("Erro: Já existe uma proposta com este ID!");
                }
                break;
                
            case "5": // QUANTIDADE DE PROPOSTAS
                ArrayList<PropostaDeAluguel> todasPropostas = central.getTodasAsPropostas();
                if (todasPropostas == null || todasPropostas.isEmpty()) {
                    System.out.println("Total de propostas cadastradas: 0");
                } else {
                    System.out.println("Total de propostas cadastradas: " + todasPropostas.size());
                }
                break;
                
            case "6": // DETALHAR UMA PROPOSTA
                System.out.print("Digite o ID da proposta: ");
                try {
                    long idBusca = Long.parseLong(sc.nextLine());
                    PropostaDeAluguel propostaDetalhe = central.recuperarPropostaPorId(idBusca);
                    
                    if (propostaDetalhe == null) {
                        System.out.println("Proposta não encontrada!");
                    } else {
                        System.out.println("\n" + propostaDetalhe);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("ID inválido!");
                }
                break;
                
            case "7": // ATIVAR UMA PROPOSTA
                System.out.print("Digite o ID da proposta que deseja ativar: ");
                try {
                    long idAtivar = Long.parseLong(sc.nextLine());
                    PropostaDeAluguel propostaAtivar = central.recuperarPropostaPorId(idAtivar);
                    
                    if (propostaAtivar == null) {
                        System.out.println("Proposta não encontrada!");
                    } else {
                        
                        if (propostaAtivar.getStatus() == Status.EM_AVALIACAO) {
                            propostaAtivar.setStatus(Status.ATIVO);
                            pers.salvarCentral(central, arquivo);
                            System.out.println("Proposta ativada com sucesso!");
                            System.out.println("Gerando contrato e enviando por e-mail...");
                            
                            
                            GeradorDeContratos.obterContrato(propostaAtivar.getId(), central);
                            
                           
                        } else {
                            System.out.println("Não é possível ativar esta proposta. Status atual: " + propostaAtivar.getStatus());
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("ID inválido!");
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
