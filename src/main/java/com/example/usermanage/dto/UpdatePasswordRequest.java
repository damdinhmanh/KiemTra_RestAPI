package com.example.usermanage.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdatePasswordRequest {
    private String oldPassword;
    private String newPassword;
}
