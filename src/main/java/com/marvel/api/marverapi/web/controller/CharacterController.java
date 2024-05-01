package com.marvel.api.marverapi.web.controller;


import com.marvel.api.marverapi.dto.Pageable;
import com.marvel.api.marverapi.persistence.integration.marvel.dto.CharacterDto;
import com.marvel.api.marverapi.persistence.integration.marvel.dto.CharacterInfoDto;
import com.marvel.api.marverapi.persistence.integration.marvel.dto.GetCharactersDto;
import com.marvel.api.marverapi.service.CharacterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    private final CharacterService characterService;

    @Autowired
    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping
    public ResponseEntity<List<CharacterDto>> getCharacters(Pageable pageable,  GetCharactersDto getCharactersDto) {
        return ResponseEntity.ok(characterService.findAll(pageable, getCharactersDto));♠
    }

    @GetMapping("/{characterId}")
    public ResponseEntity<CharacterInfoDto> findInfoById(@PathVariable long characterId){
        return ResponseEntity.ok(characterService.findInfoById(characterId));
    }
}
