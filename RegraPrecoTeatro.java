import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.Month;
import java.time.LocalDateTime;

public class RegraPrecoTeatro {
	
	private int id;
	private float valorPorHora;
    private DayOfWeek diaDaSemana;
    private Turno turno;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private Month mes;
    private Integer ano;
    
    public RegraPrecoTeatro(float valorPorHora) {
        this.valorPorHora = valorPorHora;
    }
    


    public boolean seAplicaA(LocalDateTime dataHoraAgendada) {
        // 1º Valida Ano
        if (temAno() && dataHoraAgendada.getYear() != this.ano) return false;
        
        // 2º Valida Mês
        if (temMes() && dataHoraAgendada.getMonth() != this.mes) return false;
        
        // 3º Valida Dia da Semana
        if (temDiaDaSemana() && dataHoraAgendada.getDayOfWeek() != this.diaDaSemana) return false;
        
        // 4º Valida Horário Específico
        LocalTime horaAgendada = dataHoraAgendada.toLocalTime();
        if (temHorario()) {
            if (horaAgendada.isBefore(horaInicio) || horaAgendada.isAfter(horaFim)) return false;
        }
        
        // 5º  Valida Turno
        if (temTurno()) {
            Turno turnoAgendado = descobrirTurno(horaAgendada);
            if (this.turno != turnoAgendado) return false;
        }

        return true; // Se passou por todos os filtros ativos, a regra se aplica!
    }

    private Turno descobrirTurno(LocalTime hora) {
        if (hora.isBefore(LocalTime.of(12, 0))) {
        		return Turno.MANHA;
        }
        if (hora.isBefore(LocalTime.of(18, 0))) {
        		return Turno.TARDE;
        }
        else {
        	return Turno.NOITE;
        }
        	
    }
	
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
  
    public float getValorPorHora() { 
    		return valorPorHora; 
    }
    public void setDiaDaSemana(DayOfWeek diaDaSemana) { 
    		this.diaDaSemana = diaDaSemana; 
    }
    public void setTurno(Turno turno) { 
    		this.turno = turno; 
    }
    public void setHorario(LocalTime inicio, LocalTime fim) { 
    		this.horaInicio = inicio; 
    		this.horaFim = fim; 
    }
    public void setMes(Month mes) { 
    		this.mes = mes; 
    }
    public void setAno(Integer ano) { 
    		this.ano = ano;
    }

    public boolean temDiaDaSemana() { return diaDaSemana != null; }
    public boolean temTurno() { return turno != null; }
    public boolean temHorario() { return horaInicio != null && horaFim != null; }
    public boolean temMes() { return mes != null; }
    public boolean temAno() { return ano != null; }
}
