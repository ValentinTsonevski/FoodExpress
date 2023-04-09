package com.example.foodexpress.customExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "There is no such item in FoodExpress")
public class ObjectNotFoundException extends RuntimeException{

    private Long itemId;

    private String itemType;


    public ObjectNotFoundException(Long itemId, String itemType) {
        super("Item " + itemType + " with ID " + itemId + "no found!");
        this.itemId = itemId;
        this.itemType = itemType;
    }

    public ObjectNotFoundException() {
    }

    public Long getItemId() {
        return itemId;
    }

    public String getItemType() {
        return itemType;
    }
}
