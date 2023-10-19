package com.chatop.rentalApp_back.dto;


import com.chatop.rentalApp_back.Utils.DateTimeConversionUtil;
import com.chatop.rentalApp_back.models.Rental;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalDTO {
    private Integer id;
    private String name;
    private double surface;
    private double price;
    private String picture;

    private String description;

    private Integer owner_id;
    private String createdAt;

    private String updatedAt;

    public RentalDTO(Rental rental) {
        this.id = rental.getId();
        this.name = rental.getName();
        this.surface = rental.getSurface();
        this.price = rental.getPrice();
        this.picture = rental.getPicture();
        this.description = rental.getDescription();
        this.owner_id = rental.getOwner().getId();
        this.createdAt = DateTimeConversionUtil.
                convertLocalDateTimeToString(rental.getCreatedAt());
        this.updatedAt = DateTimeConversionUtil.
                convertLocalDateTimeToString(rental.getUpdatedAt());
    }

}
