package AlexsGameEnhancers;

import java.util.HashMap;


import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.Image;


import java.awt.image.BufferedImage;


public class ImageLoader {
	
	private HashMap<String, Image> images;
	
	public ImageLoader() {
		images = new HashMap<>();
	}
	
	public Image loadImage(String keyName, String file) {
		try {
			if(getFileType(file).equals("gif")) {
				Image loadedGif = new ImageIcon(file).getImage();
				images.put(keyName, loadedGif);

				return loadedGif;
			} else if(getFileType(file).equals("png") || getFileType(file).equals("jpg")) {

				BufferedImage loadedImage = ImageIO.read(new File(file));
				images.put(keyName, loadedImage);

				return loadedImage;
			}
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
	}
	
	
	private String getFileType(String fileName) {
		int index = fileName.lastIndexOf('.');
		if(index > 0) {
			String extension = fileName.substring(index + 1);
			return extension;
		}
		return null;
	}
	
	/*
		Returns the image at a certain key in the map.
		
		@return Image, the image at the specifyed key
	*/
	public Image getImage(String keyName) {
		return images.get(keyName);
	}
}