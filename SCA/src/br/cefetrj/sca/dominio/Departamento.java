package br.cefetrj.sca.dominio;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Departamento {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String nome;

	@OneToMany(mappedBy = "departamento")
	private Set<Professor> professores;

	public Departamento() {
		professores = new HashSet<Professor>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setName(String deptName) {
		this.nome = deptName;
	}

	public void addProfessor(Professor professor) {
		if (!getProfessores().contains(professor)) {
			getProfessores().add(professor);
			if (professor.getDepartmento() != null) {
				professor.getDepartmento().getProfessores().remove(professor);
			}
		}
	}

	public Collection<Professor> getProfessores() {
		return professores;
	}

	public String toString() {
		return "Departamento.id: " + getId() + ", nome: " + getNome();
	}
}