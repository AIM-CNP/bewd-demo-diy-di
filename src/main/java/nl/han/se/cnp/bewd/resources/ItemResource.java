package nl.han.se.cnp.bewd.resources;

import nl.han.se.cnp.bewd.annotations.DiyGetMapping;
import nl.han.se.cnp.bewd.annotations.DiyAutowired;
import nl.han.se.cnp.bewd.annotations.DiyRestController;
import nl.han.se.cnp.bewd.services.ItemService;
import nl.han.se.cnp.bewd.services.dto.ItemDTO;

import java.util.List;

@DiyRestController
public class ItemResource {

    private ItemService itemService;

    @DiyGetMapping("/items")
    public List<ItemDTO> getJsonItems() {
        return itemService.getAll();
    }

    @DiyAutowired
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }
}
