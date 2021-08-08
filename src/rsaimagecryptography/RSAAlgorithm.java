/*
 * This class includes methods for computing required 
   values for RSA Algorithm
 */
package rsaimagecryptography;

public class RSAAlgorithm {

	RSAUtil rsaUtil = new RSAUtil();
	
    //Computes the value of n, n = (p*q)
    public int n_val(int p, int q) {
        return p * q;
    }
    
    //Computes the totient of n, phi(n) = (p-1)*(q-1)
    public int phi_val(int p, int q) { 
        return (p - 1) * (q - 1);
    }
    
    /*
     * Computes the value of e, such that 1 < e < phi(n)
     * e and phi(n) must be co-prime (gcd(e,phi)==1)
     * e is the public key exponent
     */
    public int e_val(int phi) {
        int e = 2;
        while (e < phi) {  
            if (rsaUtil.gcd(e, phi)==1)
                break; 
            else
                e++; 
        }
        return e;
    }
    
    /*
     * Computes d using modular arithmetic
     * Computed using EXTENDED EUCLIDEAN ALGORITHM
     * d is the private key exponent
     */
    public int d_val(int e, int phi) {
        int d = 0;
        for(int i = 0; i <= 9; i++) {
            int x = 1 + (i * phi);
            if(x % e == 0) {          
                d = x / e;
                break;
            }
        }
        return d;
    }
    /*
     * Encrypts the file using the formula:
       encryptedFile = file^e mod n
     */
    public int[] encrypt(int[] intArr, int e, int n) {
        int[] encrypt = new int[intArr.length];
        for(int i = 0; i < intArr.length; i++) {
            encrypt[i] = (int)rsaUtil.modPow(intArr[i], e, n);
        }
        return encrypt;
    }
    
    /*
     * Decrpyts the file using the formula:
       file = encryptedFile^d mod n
     */
    public int[] decrypt(int[] intArr, int d, int n) {
        int[] decrypt = new int[intArr.length];
        for(int i = 0; i < intArr.length; i++) {
            decrypt[i] = (int)rsaUtil.modPow(intArr[i], d, n);
        }
        return decrypt;
    }
}