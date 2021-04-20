/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import primitives.Color;
import renderer.ImageWriter;

/**
 * @author Shai&Avishay
 *
 */
public class ImageWriterTest {

	@Test
	public void basicImageWriterWithGridTest() {
		ImageWriter imageWriter = new ImageWriter("base ImageWriter test", 800, 500);
		
		for(int i=0;i<imageWriter.getNx();i++)
			for(int j=0;j<imageWriter.getNy();j++) 
				imageWriter.writePixel(i, j, new Color(200,46,128));
		
		final int interval = 50;
		for(int i=interval ;i<imageWriter.getNx()-1;i+=interval)
			for(int j=0;j<imageWriter.getNy();j++) 
				imageWriter.writePixel(i, j,Color.BLACK);
		
		for(int j=interval;j<imageWriter.getNy()-1;j+=interval)
			for(int i=0;i<imageWriter.getNx();i++) 
				imageWriter.writePixel(i, j,Color.BLACK);
		
		imageWriter.writeToImage();
	}

}
