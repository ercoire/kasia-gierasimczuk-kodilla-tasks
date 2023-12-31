package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrelloBadgeDto {

    @JsonProperty("votes")
    private int votes;

    @JsonProperty("attachmentsByType")
    private TrelloAttachmentDto attachment;
}
