package com.marvel.api.marverapi.service;

import com.marvel.api.marverapi.dto.Pageable;
import com.marvel.api.marverapi.persistence.integration.marvel.dto.CharacterDto;
import com.marvel.api.marverapi.persistence.integration.marvel.dto.CharacterInfoDto;
import com.marvel.api.marverapi.persistence.integration.marvel.dto.GetCharactersDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CharacterService {
    List<CharacterDto> findAll(Pageable pageable, GetCharactersDto getCharactersDto);

    CharacterInfoDto findInfoById(long characterId);
}
