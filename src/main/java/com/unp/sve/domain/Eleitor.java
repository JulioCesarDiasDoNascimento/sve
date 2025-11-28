package com.unp.sve.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Eleitor extends Pessoa{

    // Classe para os eleitores
    private boolean votou;
}
