/**
 * 
 */
package a00918606.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 * @author Velaciela
 *
 */
public class Decoder {
	// salt for password-based encryption-decryption algorithm
	private static final byte[] salt = { (byte) 0xf5, (byte) 0x33, (byte) 0x01,
				(byte) 0x2a, (byte) 0xb2, (byte) 0xcc, (byte) 0xe4, (byte) 0x7f };

	// iteration count
	private int iterationCount = 100;
	
	
	public Decoder() {
		
	}
	
	
	// obtain contents from file and decrypt
	public byte[] readFromFileAndDecrypt(String key) {

		// used to rebuild byte list

		// obtain user input
		String password = key;

		// create secret key
		Cipher cipher = null;
		
		try {
			// create password based encryption key object
			PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());

			// obtain instance for secret key factory
			SecretKeyFactory keyFactory = SecretKeyFactory
					.getInstance("PBEWithMD5AndDES");

			// generate secret key for encryption
			SecretKey secretKey = keyFactory.generateSecret(keySpec);

			// specifies parameters used with password based encryption
			PBEParameterSpec parameterSpec = new PBEParameterSpec(salt,
					iterationCount);

			// obtain cipher instance reference.
			cipher = Cipher.getInstance("PBEWithMD5AndDES");

			// initialize cipher in decrypt mode
			cipher.init(Cipher.DECRYPT_MODE, secretKey, parameterSpec);
		}

		// handle NoSuchAlgorithmException
		catch (NoSuchAlgorithmException exception) {
			exception.printStackTrace();
			System.exit(1);
		}

		// handle InvalidKeySpecException
		catch (InvalidKeySpecException exception) {
			exception.printStackTrace();
			System.exit(1);
		}

		// handle InvalidKeyException
		catch (InvalidKeyException exception) {
			exception.printStackTrace();
			System.exit(1);
		}

		// handle NoSuchPaddingException
		catch (NoSuchPaddingException exception) {
			exception.printStackTrace();
			System.exit(1);
		}

		// handle InvalidAlgorithmParameterException
		catch (InvalidAlgorithmParameterException exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		/*
		// read and decrypt contents from file
		try {
			File file = new File("MiniCalc.caesar");
			FileInputStream fileInputStream = new FileInputStream(file);

			CipherInputStream in = new CipherInputStream(fileInputStream,
					cipher);

			// read bytes from stream.
			byte contents = (byte) in.read();

			@SuppressWarnings("unused")
			int count = 0;
			while (contents != -1) {
				count++;
				fileBytes.add(new Byte(contents));
				contents = (byte) in.read();
			}
			in.close();

		}		
		
		// handle IOException
		catch (IOException exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		byte[] decryptedText = new byte[fileBytes.size()];


		for (int i = 0; i < fileBytes.size(); i++) {
			decryptedText[i] = ((Byte) fileBytes.elementAt(i)).byteValue();
		}
		
		System.out.println("Decoder is running");
		
		return decryptedText;
		
		*/
		
		byte[] btArr = null;
		try {
			 System.getProperty("user.dir");
			 InputStream ipStream = getClass().getResourceAsStream("dbprops.prop");
		     //inputfile = new FileInputStream("/a00918606/util/dbprops.prop");
		     ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		     int ch;
		     CipherInputStream in = new CipherInputStream(ipStream, cipher);
		     @SuppressWarnings("unused")
			int i = 0;
		     while ((ch = in.read()) != -1) {
		          byte b = (byte) (ch);
		          buffer.write(b);
		          i++;
		     }
		     in.close();	
		     btArr = buffer.toByteArray();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		
		return btArr;
		
	}
		
		
	/*	
	private byte[] returnByteArray() throws IOException {
		// read and decrypt contents from file
		FileInputStream inputfile = null;
		try {
		     inputfile = new FileInputStream("MiniCalc.caesar");
		     ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		     int ch;
		     CipherInputStream in = new CipherInputStream(inputfile, cipher);
		     int i = 0;
		     while ((ch = in.read()) != -1) {
		          byte b = (byte) (ch);
		          buffer.write(b);
		          i++;
		     }
		     in.close();
		     return buffer.toByteArray();
		     
		} finally{
			if (inputfile != null)
				inputfile.close();
		}
		
	}
	*/
	
}
