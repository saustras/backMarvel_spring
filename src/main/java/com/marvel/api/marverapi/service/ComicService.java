package com.marvel.api.marverapi.service;
import com.marvel.api.marverapi.persistence.integration.marvel.dto.ComicDto;
import com.marvel.api.marverapi.persistence.integration.marvel.dto.GetComicsDto;

import java.util.List;


public interface ComicService {

    List<ComicDto> findAll(GetComicsDto getComicsDto);


    ComicDto findById(long comicId);
}
