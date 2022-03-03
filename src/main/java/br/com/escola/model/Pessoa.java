package br.com.escola.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class Pessoa {

    @Id
    private Integer id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String rg;
    private String email;

}
