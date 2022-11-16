package com.example.usermanage.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseUserData {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String avatar;
}
