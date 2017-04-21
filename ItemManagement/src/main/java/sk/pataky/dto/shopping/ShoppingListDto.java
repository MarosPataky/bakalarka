package sk.pataky.dto.shopping;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 *
 */
public class ShoppingListDto {

    public Long id;

    public List<ShoppingListItemDto> items = new ArrayList<>();

    public Date createdOn;
}
