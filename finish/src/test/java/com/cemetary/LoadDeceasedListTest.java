package com.cemetary;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoadDeceasedListTest {

    @Test
    public void testLoad() {
        DeceasedList dl = LoadDeceasedList.loadFromResources();
        assertTrue(dl.deceasedList.size() > 0);
        System.out.println(dl);
    }
}
