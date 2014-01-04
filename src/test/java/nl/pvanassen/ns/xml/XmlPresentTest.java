package nl.pvanassen.ns.xml;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class XmlPresentTest {

    private XmlPresent present;

    @Before
    public void setUp() {
        present = (XmlPresent)XmlPresent.getXml(getClass().getResourceAsStream(
                "/test.xml"), "test");        
    }

    @Test
    public void testName() {
        assertEquals("test", present.name());
    }

    @Test
    public void testContent() {
        assertEquals("Test", present.child("content").content());
    }

    @Test
    public void testChild() {
        assertNotNull(present.child("subelement"));
        assertTrue(present.child("subelement") instanceof XmlPresent);
        assertNotNull(present.child("doesNotExist"));
        assertTrue(present.child("doesNotExist") instanceof XmlAbsent);
    }

    @Test
    public void testChildren() {
        Xml another = present.child("another");
        List<Xml> children = another.children("nice");
        assertNotNull(children);
        assertEquals(5, children.size());
    }

    @Test
    public void testAttr() {
        Xml subelement = present.child("subelement");
        assertEquals("nice-and-pretty", subelement.attr("attribute"));
        assertNull( subelement.attr("doesnotexist"));
    }

    @Test
    public void testIsPresent() {
        assertFalse(present.isPresent("doesnotexist"));
        assertTrue(present.isPresent("subelement"));
    }

}
