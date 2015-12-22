package vn.com.lqdung.collection.webservice.management.time.util;

import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

/**
 *	@Author lqdung
 *	Nov 30, 2015
 */
public class SecurityUtil {
  private static final Pattern BEARER = Pattern.compile("^Bearer$", Pattern.CASE_INSENSITIVE);
  
  public static final String USER_ATTR = "user";
  public static final String TOKEN_EXPIRED_ATTR = "tokenExpired";
  public static final String GUESS_USER = "GUESS";
  public static final String ADMIN_USER = "admin";
  //ov@11admin123456
  public static final String ADMIN_PASSWORD = "TI5Vex7xc2nnbCyM2mDZNO/h4gOb+5wvHnxC6SkzMw0=";
  
  public static final int DEFAULT_TOKEN_EXPIRE_SECOND = 60*60;

  private static Cipher m_cipher;
  private static SecretKeySpec m_keySpec;

  /**
   * Encryption by AES
   */
  public static String encrypt(String strToEncrypt) {
      try {
          byte[] input = strToEncrypt.getBytes();
          byte[] encryptedByte;
          synchronized (ADMIN_USER) {
              getCipher().init(Cipher.ENCRYPT_MODE, m_keySpec);
              encryptedByte = getCipher().doFinal(input);
          }
          byte[] encryptedArray = Base64.getEncoder().encode(encryptedByte);
          return new String(encryptedArray);
      } catch (Exception e) {
          return strToEncrypt;
      }
  }

  /**
   * DeCryption by AES
   */
  public static String decrypt(String strToDecrypt) {
      try {
          byte[] input = Base64.getDecoder().decode(strToDecrypt.getBytes());
          byte[] decryptedByte;
          synchronized (ADMIN_USER) {
              getCipher().init(Cipher.DECRYPT_MODE, m_keySpec);
              decryptedByte = getCipher().doFinal(input);
          }
          return new String(decryptedByte);
      } catch (Exception e) {
          return strToDecrypt;
      }
  }

  /**
   * Encryption by MD5
   */
  public static String encryptMessageDigest(String strToEncrypt) {
      final String DIGEST = "MD5";
      try {
          // Create MessageDigest instance
          MessageDigest md = MessageDigest.getInstance(DIGEST);
          // Add password bytes to digest
          md.update(strToEncrypt.getBytes());
          // Get the hash's bytes
          byte[] str = md.digest();
          // Get complete hashed password in hex format
          return DatatypeConverter.printHexBinary(str);
      } catch (NoSuchAlgorithmException e) {
          return strToEncrypt;
      }
  }
  
  public static String encrypt64Base(String strToEncrypt) {
      return Base64.getEncoder().encodeToString(strToEncrypt.getBytes());
  }


  private static Cipher getCipher() throws Exception {
      if (m_cipher == null) {
          initEncryption();
      }
      return m_cipher;
  }

  private static void initEncryption() throws Exception {
      // Hardcoded key based on pseudo-random numbers
      final byte[] keyBytes = { -1, -9, 81, -13, 24, -71, -82, -127, -27,
              -20, 46, 76, 84, -11, 79, -9 };
      m_keySpec = new SecretKeySpec(keyBytes, "AES");
      m_cipher = Cipher.getInstance(m_keySpec.getAlgorithm());
  }
  
  /**
   * Generate the token
   * 
   * @return the string token
   */
  public static String generateToken() {
      byte[] r = new byte[64]; //64 bytes string
      new Random().nextBytes(r);
      String s = Base64.getEncoder().encodeToString(r);
      return s;
  }
  
  public static String generateRandomString(){
      UUID id = UUID.randomUUID();
      return id.toString();
  }
  
  public static String getToken(RoutingContext context){
      HttpServerRequest request = context.request();
      
      String authorization = request.headers().get(HttpHeaders.AUTHORIZATION);
      String token = null;
      if (authorization != null) {
          String[] parts = authorization.split(" ");
          if (parts.length == 2) {
              String scheme = parts[0];
              String credentials = parts[1];

              if (BEARER.matcher(scheme).matches()) {
                  token = credentials;
              }
          } 
      }
      return token;
  }
  
  public static boolean isTokenExpired(Date createdDate, int expiresInSeconds){
      //check to see if the token expired
      Calendar oldDate = Calendar.getInstance();
      oldDate.setTime(createdDate);
      Calendar currentDate = Calendar.getInstance();
      oldDate.add(Calendar.SECOND, expiresInSeconds);
      return !oldDate.after(currentDate);
  }
  
  public static void main(String[] args) {
      System.out.println(encrypt("ov@11admin123456"));
      System.out.println(decrypt(encrypt("ov@11admin123456")));
  }

}
