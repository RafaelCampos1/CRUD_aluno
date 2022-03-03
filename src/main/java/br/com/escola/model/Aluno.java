package br.com.escola.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Aluno extends Pessoa {
    private String matricula;
}
