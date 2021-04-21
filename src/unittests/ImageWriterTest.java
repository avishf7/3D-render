package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import primitives.Color;
import renderer.ImageWriter;

/**
 * Test writing a basic image
 * 
 * @author Shai&Avishay
 *
 */
public class ImageWriterTest {

	/**
	 * write a basic Image with grid (the grid has different color from the
	 * background of the image)
	 */
	@Test
	public void basicImageWriterWithGridTest() {
		
		ImageWriter imageWriter = new ImageWriter("base ImageWriter test", 800, 500);
        
		//Color all the pixels of the image in the selected color
		for (int i = 0; i < imageWriter.getNx(); i++)
			for (int j = 0; j < imageWriter.getNy(); j++)
				imageWriter.writePixel(i, j, new Color(200, 46, 128));
        
		//Creating the grid
		final int interval = 50;
		for (int i = interval; i < imageWriter.getNx() - 1; i += interval)
			for (int j = 0; j < imageWriter.getNy(); j++)
				imageWriter.writePixel(i, j, Color.BLACK);

		for (int j = interval; j < imageWriter.getNy() - 1; j += interval)
			for (int i = 0; i < imageWriter.getNx(); i++)
				imageWriter.writePixel(i, j, Color.BLACK);

		imageWriter.writeToImage();
	}

}
