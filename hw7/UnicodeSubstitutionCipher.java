import java.util.Random;

public class UnicodeSubstitutionCipher implements Cipher {
	private char[] ecryptMap=new char[0x10000];
	private char[] decryptMap=new char[0x10000];
	
	public UnicodeSubstitutionCipher(long seed) {
		Random random=new Random(seed);
		for(int i=0;i<ecryptMap.length;i++) {
			ecryptMap[i]=(char)i;
		}
		
		for (int i=ecryptMap.length-1;i>0;i--) {
			int j=random.nextInt(i+1);
			char tmp=ecryptMap[i];
			ecryptMap[i]=ecryptMap[j];
			ecryptMap[j]=tmp;
		}
		
		for (int i=0;i<ecryptMap.length;i++) {
			decryptMap[ecryptMap[i]]=(char)i;
			
		}
	}
	@Override
	public String encrypt(String message) {
		char[] m=message.toCharArray();
		for(int i=0;i<m.length;i++) {
			m[i]=ecryptMap[m[i]];
		}
		return new String(m);
	}

	@Override
	public String decrypt(String message) {
		char[] m=message.toCharArray();
		for(int i=0;i<m.length;i++) {
			m[i]=decryptMap[m[i]];
		}
		return new String(m);
	}

}
