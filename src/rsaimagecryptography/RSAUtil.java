/*
 * This class includes methods that computes other components of the program
 */
package rsaimagecryptography;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class RSAUtil {
    
    public long square(long a) {
        return (a * a);
    }
    
    //Computes the GCD(a,b)
    //Euclidean Algorithm
    public int gcd(int e, int phi) {
        if(e == 0)
            return phi; 
        else
            return gcd(phi % e, e);
    } 
    
    //Checks the primality of a number
    public boolean isPrime(int number) {
        for (int divisor = 2; divisor <= number / 2; divisor++) {
                if (number % divisor == 0) 
                    return false;
        }
        return true;
    }
    
    //Computes base ^ power % modulo
    public long modPow(int b, int p, int m) {
        if (p == 0)
            return 1;
        else if (p % 2 == 0)
            return square(modPow(b, p / 2, m)) % m;
        else
            return ((b % m) * modPow(b, p - 1, m)) % m;
    }
    
    //Converts the BufferedImage byteArr[] to intArr[]
    public int[] convertByteToInt(BufferedImage img) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(img, "png", baos);
        byte[] byteArr = baos.toByteArray();
        int[] intArr = new int[byteArr.length];
            for(int i = 0; i < byteArr.length; i++) {
                intArr[i] = (int)byteArr[i] & 0xFF;
            }
        return intArr;
    }
    
    //Converts the intArr[] to byteArr[]
    public byte[] convertIntToByte(int[] intArr) throws IOException {
        byte[] byteArr = new byte[intArr.length];
            for(int i = 0; i < intArr.length; i++) {
                Integer tempInt = intArr[i];
                byte tempByte = tempInt.byteValue();
                byteArr[i] = tempByte;
            }
        return byteArr;
    }
    
    //Converts byteArr[] to BufferedImage
    public BufferedImage convertByteToImage(byte[] imageByteArr) throws IOException {
        return ImageIO.read(new ByteArrayInputStream(imageByteArr));
    }
}