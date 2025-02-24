package nl.han.se.cnp.bewd.resources;

import nl.han.se.cnp.bewd.annotations.DiyAutowired;
import nl.han.se.cnp.bewd.annotations.DiyGetMapping;
import nl.han.se.cnp.bewd.annotations.DiyRestController;
import nl.han.se.cnp.bewd.services._ItemService;
import nl.han.se.cnp.bewd.services.dto.ItemDTO;

import java.util.List;

// This class doesn't have any dependencies on ItemService, only on _ItemService
@DiyRestController
public class ItemResource
{
    private _ItemService itemService;

    @DiyGetMapping("/items")
    public List<ItemDTO> getJsonItems()
    {
        return itemService.getAll();
    }

    @DiyAutowired
    public void setItemService(_ItemService itemService)
    {
        this.itemService = itemService;
    }
}
