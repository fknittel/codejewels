package net.amygdalum.codejewels.constructors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import net.amygdalum.codejewels.constructors.ImageFormat;
import org.junit.jupiter.api.Test;

public class ImageFormatTest {

    @Test
    public void testImageFormat() {
        assertThat(new ImageFormat(10, 10, 0).getCategory(), equalTo("square"));
    }

    @Test
    public void testImageFormatWithBorder() {
        assertThat(new ImageFormat(10, 8, 2).getCategory(), equalTo("square"));
    }

}
