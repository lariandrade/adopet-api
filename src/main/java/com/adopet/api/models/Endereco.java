package com.adopet.api.models;

import com.adopet.api.dtos.DadosEnderecoDTO;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class Endereco {
    private String cidade;
    private String UF;

    public Endereco(DadosEnderecoDTO endereco) {
        this.cidade = endereco.cidade();
        this.UF = endereco.UF();
    }

    public void atualizarInformacoes(DadosEnderecoDTO dados) {

        if (dados.cidade() != null){
            this.cidade = dados.cidade();
        }

        if (dados.UF() != null){
            this.UF = dados.UF();
        }

    }
}
