package com.tukutano;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.aztec.AztecWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.datamatrix.DataMatrixWriter;
import com.google.zxing.oned.CodaBarWriter;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.oned.Code39Writer;
import com.google.zxing.oned.Code93Writer;
import com.google.zxing.oned.EAN13Writer;
import com.google.zxing.oned.EAN8Writer;
import com.google.zxing.oned.ITFWriter;
import com.google.zxing.oned.UPCAWriter;
import com.google.zxing.oned.UPCEWriter;
import com.google.zxing.pdf417.PDF417Writer;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GenerateBarcode {
  int width = 200;
  int height = 200;

  /**
   * QRコード生成.
   *
   * @param bytes QR内容
   * @param output QR出力ファイル
   */
  public void genBarcode(String content, String output, BarcodeFormat format) {

    if (null == content) {
      return;
    }
    if (null == output) {
      return;
    }
    if (null == format) {
      return;
    }

    // String content = "4902102072618";
    int width = 273;
    int height = 110;

    try {


      // EAN13Writer#encode()には以下の情報を渡す
      // (1)エンコード対象の文字列、バーコードシンボルに埋め込みたい情報
      // (2)出力するバーコードの書式
      // (3)イメージの幅
      // (4)イメージの高さ
      BitMatrix bitMatrix = null;
      switch (format) {

        /** Aztec 2D barcode format. */
        case AZTEC:
          AztecWriter writerAztec = new AztecWriter();
          bitMatrix = writerAztec.encode(content, format, width, height);
          break;
        /** CODABAR 1D format. */
        case CODABAR:
          CodaBarWriter writerCODEBAR = new CodaBarWriter();
          bitMatrix = writerCODEBAR.encode(content, format, width, height);
          break;
        /** Code 39 1D format. */
        case CODE_39:
          Code39Writer writerCode39 = new Code39Writer();
          bitMatrix = writerCode39.encode(content, format, width, height);
          break;
        /** Code 93 1D format. */
        case CODE_93:
          Code93Writer writerCode93 = new Code93Writer();
          bitMatrix = writerCode93.encode(content, format, width, height);
          break;
        /** Code 128 1D format. */
        case CODE_128:
          Code128Writer writerCode128 = new Code128Writer();
          bitMatrix = writerCode128.encode(content, format, width, height);
          break;
        /** Data Matrix 2D barcode format. */
        case DATA_MATRIX:
          DataMatrixWriter writerDataMatrix = new DataMatrixWriter();
          bitMatrix = writerDataMatrix.encode(content, format, width, height);
          break;
        /** EAN-8 1D format. */
        case EAN_8:
          EAN8Writer writerEAN8 = new EAN8Writer();
          bitMatrix = writerEAN8.encode(content, format, width, height);
          break;
        /** EAN-13 1D format. */
        case EAN_13:
          EAN13Writer writerEAN13 = new EAN13Writer();
          bitMatrix = writerEAN13.encode(content, format, width, height);
          break;
        /** ITF (Interleaved Two of Five) 1D format. */
        case ITF:
          ITFWriter writerITF = new ITFWriter();
          bitMatrix = writerITF.encode(content, format, width, height);
          break;
        /** MaxiCode 2D barcode format. */
        case MAXICODE:
          break;
        /** PDF417 format. */
        case PDF_417:
          PDF417Writer writerPDF417 = new PDF417Writer();
          bitMatrix = writerPDF417.encode(content, format, width, height);
          break;
        /** QR Code 2D barcode format. */
        case QR_CODE:
          break;
        /** RSS 14 */
        case RSS_14:
          break;
        /** RSS EXPANDED */
        case RSS_EXPANDED:
          // RSSExpandedReader writer = new RSSExpandedReader();
          break;
        /** UPC-A 1D format. */
        case UPC_A:
          UPCAWriter writerUPCA = new UPCAWriter();
          bitMatrix = writerUPCA.encode(content, format, width, height);
          break;
        /** UPC-E 1D format. */
        case UPC_E:
          UPCEWriter writerUPCE = new UPCEWriter();
          bitMatrix = writerUPCE.encode(content, format, width, height);
          break;
        /** UPC/EAN extension format. Not a stand-alone format. */
        case UPC_EAN_EXTENSION:
          break;
        default:
          break;
      }

      BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);

      // エンコードで得られたイメージを画像ファイルに出力する
      ImageIO.write(image, "png", new File(output));

    } catch (IllegalArgumentException e) {
      System.err.println(String.valueOf(format) + " [" + content + "] をエンコードするときに例外が発生.");
      e.printStackTrace();
    } catch (WriterException e) {
      System.err.println(String.valueOf(format) + "[" + content + "] をエンコードするときに例外が発生.");
      e.printStackTrace();
    } catch (IOException e) {
      System.err.println(String.valueOf(format) + "[" + output + "] を出力するときに例外が発生.");
      e.printStackTrace();
    }
  }


  public BarcodeFormat getBarcodeFormat(String format) {
    BarcodeFormat ret = null;

    switch (format) {
      /** Aztec 2D barcode format. */
      case "AZTEC":
        ret = BarcodeFormat.AZTEC;
        break;
      /** CODABAR 1D format. */
      case "CODABAR":
      case "NW7":
      case "NW-7":
        ret = BarcodeFormat.CODABAR;
        break;
      /** Code 39 1D format. */
      case "CODE_39":
      case "CODE39":
        ret = BarcodeFormat.CODE_39;
        break;
      /** Code 93 1D format. */
      case "CODE_93":
      case "CODE93":
        ret = BarcodeFormat.CODE_93;
        break;
      /** Code 128 1D format. */
      case "CODE_128":
      case "CODE128":
        ret = BarcodeFormat.CODE_128;
        break;
      /** Data Matrix 2D barcode format. */
      case "DATA_MATRIX":
        ret = BarcodeFormat.DATA_MATRIX;
        break;
      /** EAN-8 1D format. */
      case "EAN_8":
      case "EAN8":
        ret = BarcodeFormat.EAN_8;
        break;
      /** EAN-13 1D format. */
      case "EAN_13":
      case "EAN13":
        ret = BarcodeFormat.EAN_13;
        break;
      /** ITF (Interleaved Two of Five) 1D format. */
      case "ITF":
        ret = BarcodeFormat.ITF;
        break;
      /** MaxiCode 2D barcode format. */
      case "MAXICODE":
        ret = BarcodeFormat.MAXICODE;
        break;
      /** PDF417 format. */
      case "PDF_417":
        ret = BarcodeFormat.PDF_417;
        break;
      /** QR Code 2D barcode format. */
      case "QR_CODE":
        ret = BarcodeFormat.QR_CODE;
        break;
      /** RSS 14 */
      case "RSS_14":
      case "RSS14":
        ret = BarcodeFormat.RSS_14;
        break;
      /** RSS EXPANDED */
      case "RSS_EXPANDED":
        ret = BarcodeFormat.RSS_EXPANDED;
        break;
      /** UPC-A 1D format. */
      case "UPC_A":
        ret = BarcodeFormat.UPC_A;
        break;
      /** UPC-E 1D format. */
      case "UPC_E":
        ret = BarcodeFormat.UPC_E;
        break;
      /** UPC/EAN extension format. Not a stand-alone format. */
      case "UPC_EAN_EXTENSION":
        ret = BarcodeFormat.UPC_EAN_EXTENSION;
        break;
      default:
        break;
    }

    return ret;
  }
}
