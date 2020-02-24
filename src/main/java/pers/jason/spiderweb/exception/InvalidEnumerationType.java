package pers.jason.spiderweb.exception;

public class InvalidEnumerationType extends RuntimeException {

  private String enumCode;

  public InvalidEnumerationType(String enumCode) {
    super("无效的类型：" + enumCode);
    this.enumCode = enumCode;
  }

}
