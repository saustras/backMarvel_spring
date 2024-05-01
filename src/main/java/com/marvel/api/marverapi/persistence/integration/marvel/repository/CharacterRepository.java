package com.marvel.api.marverapi.persistence.integration.marvel.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.marvel.api.marverapi.dto.Pageable;
import com.marvel.api.marverapi.persistence.integration.marvel.mapper.CharacterMapper;
import com.marvel.api.marverapi.persistence.integration.marvel.MarvelAPIConfig;
import com.marvel.api.marverapi.persistence.integration.marvel.dto.CharacterDto;
import com.marvel.api.marverapi.persistence.integration.marvel.dto.CharacterInfoDto;
import com.marvel.api.marverapi.persistence.integration.marvel.dto.GetCharactersDto;
import com.marvel.api.marverapi.service.HttpClientService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Repository
public class CharacterRepository {

    MarvelAPIConfig marvelAPIConfig = new MarvelAPIConfig();
    private final HttpClientService httpClientService;
    private final Map<String, String> queryParams = marvelAPIConfig.getAuthenticationQueryParams();
    @Value("${integration.marvel.base-path}")
    private  String basePath;
    private  String characterPath;

    public CharacterRepository(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }

    @PostConstruct
    private void setPath() {
        characterPath = basePath.concat("/").concat("characters");
    }


    public List<CharacterDto> findAll(Pageable pageable, GetCharactersDto CharactersDto) {

        Map<String, String > marvelQueryParams= getQueryParamsForFindAll(pageable,
            CharactersDto.name(), CharactersDto.comics(), CharactersDto.series());

        JsonNode response = httpClientService.doGet(characterPath, marvelQueryParams, JsonNode.class);

        return CharacterMapper.characterDtoList(response);
    }

    public CharacterInfoDto findInfoById(long characterId) {
        String finalUrl = characterPath.concat("/").concat(Long.toString(characterId));
        JsonNode response = httpClientService.doGet(finalUrl, queryParams, JsonNode.class);
        return CharacterMapper.characterInfoDtoList(response).get(0);
    }

    private Map<String, String> getQueryParamsForFindAll(Pageable pageable, String name, int[] comics, int[] series) {

        putPageableQueryParams(pageable);
        if (StringUtils.hasText(name)){
            queryParams.put("name", name);
        }
        putArrayQueryParams(comics);
        putArrayQueryParams(series);

        return queryParams;
    }

    private String joinIntArray(int[] comics) {
        List<String> stringArray = IntStream.of(comics).boxed().map(Object::toString).toList();
        return  String.join(",",stringArray);
    }


    private void putArrayQueryParams(int[]param){
        if (param != null && param.length > 0) {
            String paramAString = joinIntArray(param);
            queryParams.put("comics", paramAString);
        }
    }
    private void  putPageableQueryParams(Pageable pageable){
        queryParams.put("offset", Long.toString(pageable.offset()));
        queryParams.put("limit", Long.toString(pageable.limit()));
    }
}
