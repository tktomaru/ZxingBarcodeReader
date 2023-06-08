package com.tukutano;

/**
 * CSV読み込みクラス.
 *
 * @author tktomaru
 *
 *
 */
public class FileFormatCsv {
  private String mFileName;
  private String mContentString;
  private String mFormatString;

  public void setFileName(String fileName) {
    mFileName = fileName;
  }

  public void setContentString(String contentString) {
    mContentString = contentString;
  }

  public void setFormatString(String arg) {
    mFormatString = arg;
  }

  public String getFileName() {
    return mFileName;
  }

  /**
   * バーコードにする文字列.
   *
   * @return バイト
   */
  public String getContentString() {

    return mContentString;
  }

  public String getFormatString() {
    return mFormatString;
  }

  /**
   * 16進数文字列を byte 配列に変換.
   *
   * @return バイト
   */
  public static byte[] hexStringToByteArray16(String s) {
    int len = s.length();
    byte[] data = new byte[len / 2];
    int i = 0;
    try {
      for (i = 0; i < len; i += 2) {
        data[i / 2] =
            (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
      }
    } catch (StringIndexOutOfBoundsException e) {
      System.err.println("次の文字列をバイト配列に変換する際に例外が発生：s[" + String.valueOf(i) + "] ： s = " + s);
      e.printStackTrace();
      System.exit(1);
    }
    return data;
  }
}
