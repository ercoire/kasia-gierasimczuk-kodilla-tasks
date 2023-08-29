package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class TrelloMapperTest {

    @Autowired
    private TrelloMapper trelloMapper;


    @Test
    void shouldMapToBoard() {
        //Given
        TrelloListDto listDto1 = new TrelloListDto("9", "list1", true);
        List<TrelloListDto> listDto = new ArrayList<>();
        listDto.add(listDto1);
        TrelloBoardDto dto = new TrelloBoardDto("testDto", "1", listDto);
        List<TrelloBoardDto> boardDto = new ArrayList<>();
        boardDto.add(dto);

        //When
        List<TrelloBoard> actualBoardList = trelloMapper.mapToBoard(boardDto);

        //Then
        assertEquals(1, actualBoardList.size());
        assertEquals(dto.getName(), actualBoardList.get(0).getName());

    }

    @Test
    void shouldMapToBoardDto() {
        //Given
        TrelloList list = new TrelloList("1", "list1", false);
        List<TrelloList> lists = new ArrayList<>();
        lists.add(list);
        TrelloBoard board = new TrelloBoard("1", "board1", lists);
        List<TrelloBoard> boards = new ArrayList<>();
        boards.add(board);


        //When
        List<TrelloBoardDto> actualBoardDtoList = trelloMapper.mapToBoardDto(boards);

        //Then
        assertEquals(1, actualBoardDtoList.size());
        assertEquals(list.getName(), actualBoardDtoList.get(0).getLists().get(0).getName());

    }

    @Test
    void shouldMapToList() {
        //Given
        TrelloListDto listDto = new TrelloListDto("1", "listDto", false);
        List<TrelloListDto> listOfDto = new ArrayList<>();
        listOfDto.add(listDto);

        //When
        List<TrelloList> actualList = trelloMapper.mapToList(listOfDto);

        //Then
        assertEquals(1, actualList.size());
        assertFalse(actualList.get(0).isClosed());
    }

    @Test
    void shouldMapToListDto() {
        //Given
        TrelloList testList = new TrelloList("1", "testList", true);
        List<TrelloList> lists = new ArrayList<>();
        lists.add(testList);

        //When
        List<TrelloListDto> actualList = trelloMapper.mapToListDto(lists);

        //Then
        assertEquals(1, actualList.size());
        assertEquals(testList.getId(), actualList.get(0).getId());
    }

    @Test
    void shouldMapToCardDto() {
        //Given
        TrelloCard testCardDto = new TrelloCard("test", "mapper test", "top", "1");

        //When
        TrelloCardDto actualCardDto = trelloMapper.mapToCardDto(testCardDto);

        //Then
        assertEquals(testCardDto.getName(), actualCardDto.getName());
        assertEquals(testCardDto.getPos(), actualCardDto.getPos());
    }

    @Test
    void shouldMapToCard() {
        //Given
        TrelloCardDto testCard = new TrelloCardDto("test", "mapper", "bottom", "3");

        //When
        TrelloCard actualCard = trelloMapper.mapToCard(testCard);

        //Then
        assertEquals(testCard.getDescription(), actualCard.getDescription());
        assertEquals(testCard.getListId(), actualCard.getListId());
    }
}