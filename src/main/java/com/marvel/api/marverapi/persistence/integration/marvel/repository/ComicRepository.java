package com.marvel.api.marverapi.persistence.integration.marvel.repository;
import com.fasterxml.jackson.databind.JsonNode;
import com.marvel.api.marverapi.dto.Pageable;
import com.marvel.api.marverapi.persistence.integration.marvel.MarvelAPIConfig;
import com.marvel.api.marverapi.persistence.integration.marvel.dto.ComicDto;
import com.marvel.api.marverapi.persistence.integration.marvel.mapper.ComicMapper;
import com.marvel.api.marverapi.service.HttpClientService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public class ComicRepository  {
    MarvelAPIConfig marvelAPIConfig = new MarvelAPIConfig();
    private final Map<String, String> queryParams = marvelAPIConfig.getAuthenticationQueryParams();
    HttpClientService httpClientService;
    @Value("${integration.marvel.base-path}")
    private  String basePath;
    private  String comicPath;

    @PostConstruct
    private void setPath() {
        comicPath = basePath.concat("/").concat("characters");
    }

    public List<ComicDto> findAll(Pageable pageable, long characterId) {
        Map<String, String> queryParams = getQueryParamsForFindAll(pageable, characterId);
        JsonNode response = httpClientService.doGet(comicPath, queryParams, JsonNode.class);
        return ComicMapper.ComicDtoList(response);
    }

    private Map<String, String> getQueryParamsForFindAll(Pageable pageable, Long characterId) {
        putPageableQueryParams(pageable);

        if ( characterId != null && characterId > 0 ) {
            queryParams.put("characterId", Long.toString(characterId));
        }

        return queryParams;
    }
    private void  putPageableQueryParams(Pageable pageable){
        queryParams.put("offset", Long.toString(pageable.offset()));
        queryParams.put("limit", Long.toString(pageable.limit()));
    }

    public ComicDto findById(long comicId) {

        String finalComicPath = comicPath.concat("/").concat(Long.toString(comicId));
        JsonNode response = httpClientService.doGet(finalComicPath, queryParams, JsonNode.class);

        return ComicMapper.ComicDtoList(response).get(0);
    }
}
