package com.chatop.rentalApp_back.dto;


import com.chatop.rentalApp_back.Utils.DateTimeConversionUtil;
import com.chatop.rentalApp_back.models.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Integer id;
    private String email;
    private String name;
    private String createdAt;
    private String updatedAt;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();;
        this.createdAt = DateTimeConversionUtil.
                convertLocalDateTimeToString(user.getCreatedAt());
        this.updatedAt = DateTimeConversionUtil.
                convertLocalDateTimeToString(user.getUpdatedAt());

    }
}
