package com.marvel.api.marverapi.service.impl;

import com.marvel.api.marverapi.dto.Pageable;
import com.marvel.api.marverapi.persistence.integration.marvel.dto.CharacterDto;
import com.marvel.api.marverapi.persistence.integration.marvel.dto.CharacterInfoDto;
import com.marvel.api.marverapi.persistence.integration.marvel.dto.GetCharactersDto;
import com.marvel.api.marverapi.persistence.integration.marvel.repository.CharacterRepository;
import com.marvel.api.marverapi.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository characterRepository;

    @Autowired
    public CharacterServiceImpl(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Override
    public List<CharacterDto> findAll(Pageable pageable, GetCharactersDto getCharactersDto) {
        return characterRepository.findAll(pageable,getCharactersDto);
    }

    @Override
    public CharacterInfoDto findInfoById(long characterId) {
        return characterRepository.findInfoById(characterId);
    }
}
