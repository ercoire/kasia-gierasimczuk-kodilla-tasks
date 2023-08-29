package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TrelloDto {

    @JsonProperty("board")
    int board;

    @JsonProperty("card")
    int card;

}
