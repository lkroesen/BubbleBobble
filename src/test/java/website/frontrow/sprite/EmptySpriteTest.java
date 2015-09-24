package website.frontrow.sprite;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.awt.Graphics;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

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
        es = new EmptySprite();
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
        assertEquals(es.slice(0, 0, 10, 10), new EmptySprite());
    }

    /**
     * Test equal when equal.
     */
    @Test
    public void testEqualsEqual()
    {
        assertTrue(es.equals(new EmptySprite()));
    }

    /**
     * Test not equal when different kind of object.
     */
    @Test
    public void testEqualsOther()
    {
        assertFalse(es.equals("Other"));
    }

    /**
     * Test not equal when width is different.
     */
    @Test
    public void testEqualsDifferentWidth()
    {
        EmptySprite sprite = mock(EmptySprite.class);
        when(sprite.getWidth()).thenReturn(1);
        assertFalse(es.equals(sprite));
    }

    /**
     * Test not equal when the height is different.
     */
    @Test
    public void testEqualsDifferentHeight()
    {
        EmptySprite sprite = mock(EmptySprite.class);
        when(sprite.getWidth()).thenReturn(0);
        when(sprite.getHeight()).thenReturn(1);
        assertFalse(es.equals(sprite));
    }
}
