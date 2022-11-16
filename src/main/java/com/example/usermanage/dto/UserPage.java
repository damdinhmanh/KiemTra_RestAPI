package com.example.usermanage.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserPage {
    private ResponseUserData responseUserData;
    private int currentPage;
    private int size;
    private int totalPages;
}
