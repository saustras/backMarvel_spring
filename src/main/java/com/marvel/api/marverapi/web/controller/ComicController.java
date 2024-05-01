package com.marvel.api.marverapi.web.controller;
import com.marvel.api.marverapi.persistence.integration.marvel.dto.ComicDto;
import com.marvel.api.marverapi.persistence.integration.marvel.dto.GetComicsDto;
import com.marvel.api.marverapi.service.ComicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comics")
public class ComicController {

    private final ComicService comicService;

    @Autowired
    public ComicController(ComicService comicService) {
        this.comicService = comicService;
    }

    @GetMapping
    public ResponseEntity<List<ComicDto>> findAll(@Valid GetComicsDto getComicsDto) {
        return ResponseEntity.ok(comicService.findAll(getComicsDto));
    }

    @GetMapping("/{comicId}")
    public  ResponseEntity<ComicDto> findComicById(@PathVariable long comicId) {
        return ResponseEntity.ok(comicService.findById(comicId));
    }
}
