package com.crud.tasks.domain;

import lombok.Data;

@Data
/*@Getter
@AllArgsConstructor
@NoArgsConstructor */ //@Data je zastępuje
public class TrelloBoardDto {

    private String name;
    private String id;

    public String getId() {
        return id;
    }
}
