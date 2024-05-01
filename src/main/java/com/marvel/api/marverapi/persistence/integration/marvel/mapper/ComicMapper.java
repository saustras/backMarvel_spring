package com.marvel.api.marverapi.persistence.integration.marvel.mapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.marvel.api.marverapi.persistence.integration.marvel.dto.ComicDto;
import com.marvel.api.marverapi.persistence.integration.marvel.interfaces.NodeToDtoMapper;
import com.marvel.api.marverapi.persistence.integration.marvel.repository.ThumbnailMapper;
import java.util.ArrayList;
import java.util.List;

public class ComicMapper {

    public static <T> List<T> toDtoList(JsonNode rootNode, NodeToDtoMapper<T> mapper) {
        ArrayNode node = getResultNode(rootNode);
        List<T> dtoList  = new ArrayList<>();
        node.elements().forEachRemaining(each -> dtoList.add( mapper.map(each)));
        return dtoList;
    }

    public static List<ComicDto> ComicDtoList(JsonNode comic) {
        return toDtoList(comic, comicNode -> new ComicDto(
                Long.parseLong(comicNode.get("id").asText()),
                comicNode.get("title").asText(),
                comicNode.get("description").asText(),
                comicNode.get("modified").asText(),
                comicNode.get("resourceURI").asText(),
                ThumbnailMapper.toDto(comic.get("thumbnail"))
        ));
    }

    private static ArrayNode getResultNode(JsonNode rootNode) {
        if(rootNode.isNull()) {
            throw new IllegalArgumentException("Root node is null");
        }
        JsonNode dataNode = rootNode.get("data");
        return (ArrayNode) dataNode.get("result");
    }
}
