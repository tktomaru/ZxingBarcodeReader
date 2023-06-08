package com.tukutano;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
/**
 * メインクラス.
 *
 * @author tktomaru
 *
 */
public class ZxingBarcodeReader {

	/**
	 * メインメソッド.
	 *
	 * @param args ファイル名
	 */
	public static final void main(String[] args) {

		if (args.length == 0) {
			System.out.println("コマンドライン引数がありません。");
			System.exit(1);
		}

		System.out.println("File name:" + args[0]);
		String imagePath = args[0];

		//String imagePath = "path/to/image.png"; // 画像ファイルのパスを指定してください

		try {
			BufferedImage image = ImageIO.read(new File(imagePath));
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

			MultiFormatReader reader = new MultiFormatReader();
			Result result = reader.decode(bitmap);

			String barcode = result.getText();
			BarcodeFormat barcodeFormat = result.getBarcodeFormat();
			System.out.println("読み取ったバーコード種別: " + barcodeFormat.name());
			System.out.println("読み取ったバーコード: " + barcode);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NotFoundException e) {
			System.out.println("バーコードが見つかりませんでした。");
		}
	}

	private static void makedir(String outputfilename) {
		Path path = Paths.get(outputfilename);
		Path absolutePath = path.toAbsolutePath(); // 絶対パスに変換
		Path dirName = absolutePath.getParent();
		if (dirName != null) {
			File fileDir = new File(dirName.toString());
			fileDir.mkdirs();
		}
	}
}
