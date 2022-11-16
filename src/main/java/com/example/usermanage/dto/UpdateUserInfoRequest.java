package com.example.usermanage.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateUserInfoRequest {
    private String name;
    private String phone;
    private String address;
}
