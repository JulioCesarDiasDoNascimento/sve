package com.unp.sve.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Usuario extends Pessoa{

    private String username;
    private String password;
}
