package com.Weeb.DTO.UserDTO;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetContactsDto {
    private List<String> contacts;
}
