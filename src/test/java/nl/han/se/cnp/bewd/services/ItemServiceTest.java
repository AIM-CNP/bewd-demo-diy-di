package nl.han.se.cnp.bewd.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemServiceTest {
    @Test
    public void getAll_whenCalled_ReturnsThreeItems() {
        ItemService itemService = new ItemService();
        assertEquals(3, itemService.getAll().size());
    }
}
