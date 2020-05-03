package test;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class KHash extends AlgoEncriptamiento
{

    private static String secretKey = "KHash!!!!";
    private static String salt = "KHash!!!!";
    private byte[] kHashInitVector =
    {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
    };
    private IvParameterSpec kHashInitVectorHash = new IvParameterSpec(kHashInitVector);

    @Override
    public void configurar()
    {
        String secretKey = "GHash!!!!";
        String salt = "GHash!!!!";
        IvParameterSpec gHashInitVectorHash = new IvParameterSpec(kHashInitVector);
    }

    @Override
    public String encriptar(String str)
    {
        System.out.println("Encrypting String with KHash");

        try
        {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, kHashInitVectorHash);
            return Base64.getEncoder().encodeToString(cipher.doFinal(str.getBytes("UTF-8")));

        } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException
                | InvalidKeyException | NoSuchAlgorithmException
                | InvalidKeySpecException | BadPaddingException
                | IllegalBlockSizeException | NoSuchPaddingException e)
        {
            System.out.println("Error while encrypting with KHash: " + e.toString());
        }

        return null;
    }

    @Override
    public String desencriptar(String str)
    {
        System.out.println("Decrypting String with KHash");

        try
        {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, kHashInitVectorHash);
            return new String(cipher.doFinal(Base64.getDecoder().decode(str)));

        } catch (InvalidAlgorithmParameterException | InvalidKeyException
                | NoSuchAlgorithmException | InvalidKeySpecException
                | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e)
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
}
