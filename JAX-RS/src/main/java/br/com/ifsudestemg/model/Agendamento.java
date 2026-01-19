package br.com.ifsudestemg.model;

import jakarta.json.bind.annotation.JsonbTransient;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Agendamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_confirmacao")
    private String codigoConfirmacao;

    @Column(name = "data_visita", nullable = false)
    private LocalDate dataVisita;

    @Column(name = "horario_visita", nullable = false)
    private LocalTime horarioVisita;

    @Column(name = "email_responsavel")
    private String emailResponsavel;

    @Column(name = "aceitou_termo", nullable = false)
    private boolean aceitouTermo;

    @ManyToOne
    private Museu museu;

    @OneToMany(mappedBy = "agendamento", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<PessoaAgendada> pessoas = new ArrayList<>();

    public Agendamento() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigoConfirmacao() { return codigoConfirmacao; }
    public void setCodigoConfirmacao(String codigoConfirmacao) { this.codigoConfirmacao = codigoConfirmacao; }

    public LocalDate getDataVisita() { return dataVisita; }
    public void setDataVisita(LocalDate dataVisita) { this.dataVisita = dataVisita; }

    public LocalTime getHorarioVisita() { return horarioVisita; }
    public void setHorarioVisita(LocalTime horarioVisita) { this.horarioVisita = horarioVisita; }

    @JsonbTransient
    public String getEmailResponsavel() {
        return emailResponsavel;
    }
    public void setEmailResponsavel(String emailResponsavel) { this.emailResponsavel = emailResponsavel; }

    public boolean isAceitouTermo() { return aceitouTermo; }
    public void setAceitouTermo(boolean aceitouTermo) { this.aceitouTermo = aceitouTermo; }

    public Museu getMuseu() { return museu; }
    public void setMuseu(Museu museu) { this.museu = museu; }

    public List<PessoaAgendada> getPessoas() { return pessoas; }
    public void setPessoas(List<PessoaAgendada> pessoas) { this.pessoas = pessoas; }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Agendamento other = (Agendamento) obj;
        return Objects.equals(id, other.id);
    }
}