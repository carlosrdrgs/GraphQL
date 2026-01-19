package br.com.ifsudestemg.model;

import jakarta.json.bind.annotation.JsonbTransient;
import java.io.Serializable;
import java.util.Objects;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class PessoaAgendada implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;

    @Column(name = "tipo_ingresso")
    private String tipoIngresso;

    private boolean compareceu;

    @ManyToOne
    @JsonbTransient
    private Agendamento agendamento;

    public PessoaAgendada() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    @JsonbTransient
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getTipoIngresso() { return tipoIngresso; }
    public void setTipoIngresso(String tipoIngresso) { this.tipoIngresso = tipoIngresso; }

    public boolean isCompareceu() { return compareceu; }
    public void setCompareceu(boolean compareceu) { this.compareceu = compareceu; }

    public Agendamento getAgendamento() { 
        return agendamento; 
    }

    public void setAgendamento(Agendamento agendamento) { 
        this.agendamento = agendamento; 
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PessoaAgendada other = (PessoaAgendada) obj;
        return Objects.equals(id, other.id);
    }
}
