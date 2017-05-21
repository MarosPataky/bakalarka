package sk.pataky.dto.shopping;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 *
 */
public class ShoppingListDto {

    public String id;

    public List<ShoppingListItemDto> items = new ArrayList<>();

    public Date createdOn;
}
