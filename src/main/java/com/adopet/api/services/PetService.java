package com.adopet.api.services;

import com.adopet.api.dtos.pet.DadosDetalhamentoPetDTO;
import com.adopet.api.exceptions.ValidacaoException;
import com.adopet.api.models.Abrigo;
import com.adopet.api.models.Pet;
import com.adopet.api.repositories.AbrigoRepository;
import com.adopet.api.repositories.PetRepository;
import com.adopet.api.dtos.pet.DadosCadastroPetDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PetService {

    private final PetRepository petRepository;
    private final AbrigoRepository abrigoRepository;

    public PetService(PetRepository petRepository, AbrigoRepository abrigoRepository) {
        this.petRepository = petRepository;
        this.abrigoRepository = abrigoRepository;
    }

    public DadosDetalhamentoPetDTO save(DadosCadastroPetDTO dados) {

        Optional<Abrigo> optionalAbrigo = abrigoRepository.findById(dados.abrigo_id());
        if (optionalAbrigo.isEmpty()) {
             throw new ValidacaoException("Abrigo não encontrado.");
        }

        Abrigo abrigo = optionalAbrigo.get();

        Pet novoPet = new Pet(dados, abrigo);
        petRepository.save(novoPet);
        return new DadosDetalhamentoPetDTO(novoPet);

    }
}
