package com.marvel.api.marverapi.persistence.integration.marvel.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.marvel.api.marverapi.persistence.integration.marvel.dto.CharacterDto;
import com.marvel.api.marverapi.persistence.integration.marvel.dto.CharacterInfoDto;
import com.marvel.api.marverapi.persistence.integration.marvel.dto.ThunbnailDto;
import com.marvel.api.marverapi.persistence.integration.marvel.interfaces.NodeToDtoMapper;
import com.marvel.api.marverapi.persistence.integration.marvel.repository.ThumbnailMapper;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CharacterMapper {

    @NotNull
    public static <T> List<T> toDtoList(JsonNode rootNode, NodeToDtoMapper<T> mapper) {
        ArrayNode node = getResultNode(rootNode);
        List<T> dtoList  = new ArrayList<>();
        node.elements().forEachRemaining(each -> dtoList.add( mapper.map(each)));
        return dtoList;
    }

    @NotNull
    public static List<CharacterDto> characterDtoList(JsonNode character) {
        return toDtoList(character, characterNode -> new CharacterDto(
                Long.parseLong(characterNode.get("id").asText()),
                characterNode.get("name").asText(),
                characterNode.get("description").asText(),
                characterNode.get("modified").asText(),
                characterNode.get("resourceURI").asText()
        ));
    }

    @NotNull
    public static List<CharacterInfoDto> characterInfoDtoList(JsonNode characterInfo) {
        return toDtoList(characterInfo, characterInfoNode -> new CharacterInfoDto(
                getThumbnailString(characterInfo),
                characterInfoNode.get("description").asText()
        ));
    }

    @NotNull
    private static String getThumbnailString(@NotNull JsonNode characterInfo) {
        JsonNode thumbnailNode = characterInfo.get("thumbnail");
        ThunbnailDto thunbnailDto = ThumbnailMapper.toDto(thumbnailNode);
        return thunbnailDto.path().concat(".").concat(thunbnailDto.extension());
    }

    private static ArrayNode getResultNode(@NotNull JsonNode rootNode) {
        if(rootNode.isNull()) {
            throw new IllegalArgumentException("Root node is null");
        }
        JsonNode dataNode = rootNode.get("data");
        return (ArrayNode) dataNode.get("result");
    }
}
