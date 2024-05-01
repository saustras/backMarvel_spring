package com.marvel.api.marverapi.service.impl;

import com.marvel.api.marverapi.dto.Pageable;
import com.marvel.api.marverapi.persistence.integration.marvel.dto.ComicDto;
import com.marvel.api.marverapi.persistence.integration.marvel.dto.GetComicsDto;
import com.marvel.api.marverapi.persistence.integration.marvel.repository.ComicRepository;
import com.marvel.api.marverapi.service.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComicServiceImpl implements ComicService {

    private final ComicRepository comicRepository;


    public ComicServiceImpl(ComicRepository comicRepository) {
        this.comicRepository = comicRepository;
    }

    @Override
    public List<ComicDto> findAll(GetComicsDto dto) {
        Pageable pageable = new Pageable(dto.offset(), dto.limit());
        return comicRepository.findAll(pageable, dto.characterId());
    }

    @Override
    public ComicDto findById(long comicId) {
        return comicRepository.findById(comicId);
    }
}
