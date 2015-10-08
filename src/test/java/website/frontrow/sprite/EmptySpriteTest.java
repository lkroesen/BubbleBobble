package website.frontrow.sprite;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.awt.Graphics;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * A test suite to test the empty sprite.
 */
@RunWith(MockitoJUnitRunner.class)
public class EmptySpriteTest
{

    private EmptySprite es;

    /**
     * Set things up for the tests.
     */
    @Before
    public void setUp()
    {
        es = EmptySprite.getInstance();
    }

    /**
     * Tear things down after the tests.
     */
    @After
    public void tearDown()
    {
        es = null;
    }

    /**
     * A test to make sure nothing is drawn when the method draw is called on EmptySprite.
     */
    @Test
    public void testDraw()
    {
        Graphics g = mock(Graphics.class);
        es.draw(g, 0, 0, 10, 10);
        verifyZeroInteractions(g);
    }

    /**
     * Test whether the correct width is returned.
     */
    @Test
    public void testGetWidth()
    {
        assertEquals(es.getWidth(), 0);
    }

    /**
     * Test whether the correct height is returned.
     */
    @Test
    public void testGetHeight()
    {
        assertEquals(es.getHeight(), 0);
    }

    /**
     * Test whether the correct slice is returned.
     */
    @Test
    public void testSlice()
    {
        assertEquals(es.slice(0, 0, 10, 10), EmptySprite.getInstance());
    }

    /**
     * Test equal when equal.
     */
    @Test
    public void testEqualsEqual()
    {
        assertTrue(es.equals(EmptySprite.getInstance()));
    }

    /**
     * Test not equal when different kind of object.
     */
    @Test
    public void testEqualsOther()
    {
        assertFalse(es.equals("Other"));
    }
}
