package main;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Main {


	public static void main(String[] args) throws Exception {

		String path = new File(".").getAbsoluteFile().getParent();
		System.out.println(path);
		/* Java2Dで画像を半透明に重ね合わせる */
		BufferedImage background = ImageIO.read(new File("original.png"));

		HashMap<String, BufferedImage> itemlist = new HashMap<String, BufferedImage>();

		itemlist.put("coin", ImageIO.read(new File("./image/Item/coin/coin.png")));
		itemlist.put("cup", ImageIO.read(new File("./image/Item/CUP/cup.png")));

		Graphics2D graphics = null;

		try {

			graphics = (Graphics2D) background.getGraphics();

			//graphics.drawImage(itemlist.get("coin"), 0, 0, null);

			//イメージの描画
			graphics.drawImage(itemlist.get("coin"), createAffineTransform(0, 0, itemlist.get("coin").getWidth(), itemlist.get("coin").getHeight(), 45), null);

			graphics.drawImage(itemlist.get("cup"), createAffineTransform(354, 354, itemlist.get("cup").getWidth(), itemlist.get("cup").getHeight(), 180), null);

		} finally {

			graphics.dispose();
		}

		ImageIO.write(background, "jpg", new File("dst_jpg.jpg"));
	}

	static AffineTransform createAffineTransform(int x, int y, int w, int h, double angle){

		AffineTransform affin = new AffineTransform();
		//イメージの移動
		affin.translate(x, y);
		//イメージを中心位置で回転
		affin.rotate(Math.toRadians(-1.0*angle), w/2, h/2);

		return affin;

	}
}
