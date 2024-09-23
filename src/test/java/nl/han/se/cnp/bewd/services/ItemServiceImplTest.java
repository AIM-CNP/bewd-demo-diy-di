package nl.han.se.cnp.bewd.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemServiceImplTest
{
    @Test
    public void getAll_whenCalled_ReturnsThreeItems() {
        ItemServiceImpl itemServiceImpl = new ItemServiceImpl();
        assertEquals(3, itemServiceImpl.getAll().size());
    }
}
