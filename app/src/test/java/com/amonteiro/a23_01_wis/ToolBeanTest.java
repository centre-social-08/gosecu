package com.amonteiro.a23_01_wis;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.amonteiro.a23_01_wis.beans.ToolBean;

public class ToolBeanTest {

    private ToolBean tool;

    @Before
    public void setUp() {
        tool = new ToolBean("1", "Hammer", 5);
    }

    @Test
    public void testConstructorAndGetters() {
        // Arrange
        String expectedId = "1";
        String expectedName = "Hammer";
        int expectedQuantity = 5;

        // Act & Assert
        assertEquals(expectedId, tool.getId());
        assertEquals(expectedName, tool.getName());
        assertEquals(expectedQuantity, tool.getQuantity());
    }

    @Test
    public void testSetters() {
        // Arrange
        String expectedId = "2";
        String expectedName = "Screwdriver";
        int expectedQuantity = 10;

        // Act
        tool.setId(expectedId);
        tool.setName(expectedName);
        tool.setQuantity(expectedQuantity);

        // Assert
        assertEquals(expectedId, tool.getId());
        assertEquals(expectedName, tool.getName());
        assertEquals(expectedQuantity, tool.getQuantity());
    }

    @Test
    public void testEmptyConstructor() {
        // Arrange & Act
        ToolBean emptyTool = new ToolBean();

        // Assert
        assertNull(emptyTool.getId());
        assertNull(emptyTool.getName());
        assertEquals(0, emptyTool.getQuantity());
    }
}
