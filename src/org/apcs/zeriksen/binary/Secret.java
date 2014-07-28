package org.apcs.zeriksen.binary;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class Secret {
	private static Message message;
	private static List<BinaryNum> bin;

	public Secret(String text) {
		message = new Message(text);
	}

	public void encode(String originalImagePath, String encodedImagePath) {
		File d = new File(encodedImagePath);
		BufferedImage img = null;
		int binNumIndex = 0;
		int index = 0;
		bin = message.getBinaryNums();
		try {
			InputStream is = new BufferedInputStream(new FileInputStream(
					originalImagePath));
			img = ImageIO.read(is);
			for (int y = 0; y < img.getHeight(); y++) {
				for (int x = 0; x < img.getWidth(); x++) {
					// Get the color of the x,y
					Color b = new Color(img.getRGB(x, y));
					// Set the red, green, blue to ints
					int blue = b.getBlue();
					int red = b.getRed();
					int green = b.getGreen();
					// Get the Binary number of blue
					BinaryNum bblue = new BinaryNum(blue);
					// Zero out the last digit
					bblue.getDigits()[0] = 0;
					if (index < bin.size()) {
						// If the number is one then change the last digit to
						// one
						if (bin.get(index).getDigits()[binNumIndex] == 1) {
							bblue.getDigits()[0] = 1;
						}
						binNumIndex++;
						// Go to the next binary number in the list
						if (binNumIndex == bin.get(index).getDigits().length) {
							binNumIndex = 0;
							index++;
						}
					}
					Color c = new Color(red, green, bblue.toDecimal());
					img.setRGB(x, y, c.getRGB());
				}
			}
			ImageIO.write(img, "png", d);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String decode(String encodeImagePath) {
		BufferedImage img = null;
		List<BinaryNum> bin2 = new ArrayList<BinaryNum>();
		int[] digits = new int[8];
		int i = 0;
		String str = "";
		try {
			InputStream is = new BufferedInputStream(new FileInputStream(
					encodeImagePath));
			img = ImageIO.read(is);
			int zero = 0;
			boolean toBreak = false;
			for (int y = 0; y < img.getHeight();y++) {
				for (int x = 0; x < img.getWidth(); x++) {
					// Get the color of the x,y
					Color b = new Color(img.getRGB(x, y));
					// Set blue to int
					int blue = b.getBlue();
					BinaryNum bblue = new BinaryNum(blue);
					digits[i] = bblue.getDigits()[0];
					i++;
					if (i == 8) {
						i = 0;
						BinaryNum a = new BinaryNum(digits);
						str += (char)a.toDecimal();
						bin2.add(a);
					}
						if(bblue.getDigits()[0] == 0){
							zero++;
						}else{
							zero = 0;
						}
					if(zero == 8){
						toBreak = true;
						break;
					}
				}
				if(toBreak == true)break;
			}
		} catch (Exception e) {
			System.out.println("ERROR");
		}
		return str;
	}

}
