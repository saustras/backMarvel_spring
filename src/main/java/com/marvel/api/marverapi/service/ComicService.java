package com.marvel.api.marverapi.service;
import com.marvel.api.marverapi.dto.Pageable;
import com.marvel.api.marverapi.persistence.integration.marvel.dto.ComicDto;
import com.marvel.api.marverapi.persistence.integration.marvel.dto.GetComicsDto;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public interface ComicService {

    List<ComicDto> findAll(Pageable pageable, @NotNull GetComicsDto dto);

    ComicDto findById(long comicId);
}
