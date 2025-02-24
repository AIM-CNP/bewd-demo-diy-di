package nl.han.se.cnp.bewd.services;

import nl.han.se.cnp.bewd.services.dto.ItemDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code ItemService} can be used for accessing a {@link List} of {@link ItemDTO} instances, but also
 * for adding instances to and deleting from the {@link List}.
 */
public class ItemService
{
    private final List<ItemDTO> items = new ArrayList<>();

    public ItemService()
    {
        items.add(new ItemDTO(1, "Bread", new String[]{"Breakfast, Lunch"}, "Delicious!"));
        items.add(new ItemDTO(2, "Butter", new String[]{"Breakfast, Lunch"}, "Use it with bread"));
        items.add(new ItemDTO(3, "Honey", new String[]{"Breakfast, Lunch"}, "Use it with bread"));
    }

    /**
     * Return the full {@link List} of {@link ItemDTO} instances.
     *
     * @return The full {@link List} of {@link ItemDTO} instances.
     */
    public List<ItemDTO> getAll()
    {
        return items;
    }
}
