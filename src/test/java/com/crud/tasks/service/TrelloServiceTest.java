package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrelloServiceTest {

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Mock
    private AdminConfig adminConfig;

    @InjectMocks
    private TrelloService trelloService;

    @Test
    public void testFetchTrelloBoards() {
        //Given
        List<TrelloBoardDto> mockBoards = new ArrayList<>();
        TrelloBoardDto dto1 = new TrelloBoardDto();
        TrelloBoardDto dto2 = new TrelloBoardDto();
        mockBoards.add(dto1);
        mockBoards.add(dto2);

        when(trelloClient.getTrelloBoards()).thenReturn(mockBoards);

        //When
        List<TrelloBoardDto> result = trelloService.fetchTrelloBoards();

        //Then
        assertEquals(mockBoards, result);
    }


    @Test
    public void testCreateTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto();
        CreatedTrelloCardDto mockCard = new CreatedTrelloCardDto();
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(mockCard);

        //When
        trelloService.createTrelloCard(trelloCardDto);

        //Then
        verify(emailService).send(any(Mail.class));
    }
}