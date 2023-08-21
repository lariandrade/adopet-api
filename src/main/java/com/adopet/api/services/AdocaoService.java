package com.adopet.api.services;

import com.adopet.api.dtos.DadosCadastroAdocaoDTO;
import com.adopet.api.models.Adocao;
import com.adopet.api.repositories.AdocaoRepository;
import com.adopet.api.repositories.PetRepository;
import com.adopet.api.repositories.TutorRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class AdocaoService {

    private final AdocaoRepository adocaoRepository;
    private final PetRepository petRepository;
    private final TutorRepository tutorRepository;


    public AdocaoService(AdocaoRepository adocaoRepository, PetRepository petRepository, TutorRepository tutorRepository) {
        this.adocaoRepository = adocaoRepository;
        this.petRepository = petRepository;
        this.tutorRepository = tutorRepository;
    }

    public Adocao save(@Valid DadosCadastroAdocaoDTO dados) {
        var pet = petRepository.findById(dados.petId()).orElseThrow(() -> new IllegalArgumentException("Pet não cadastrado!"));

        if (pet.getAdotado()) {
            throw new IllegalArgumentException("Pet já adotado!");
        }

        var tutor = tutorRepository.findById(dados.tutorId()).orElseThrow(() -> new IllegalArgumentException("Tutor não cadastrado!"));

        var adocao = new Adocao(dados, pet, tutor);
        adocaoRepository.save(adocao);

        pet.marcarComoAdotado();

        return adocao;
   }

    public void delete(Integer id) {
        var adocao = adocaoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Adoção não cadastrada!"));
        adocaoRepository.deleteById(id);
    }
}
