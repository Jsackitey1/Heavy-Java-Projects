import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class TwoBitRandomAccessFile implements AutoCloseable {
	// Masks for two-bit ranges: 0b11000000, 0b00110000, 0b00001100, 0b00000011
	static final byte[] MASK = { (byte) 0xC0, 0x30, 0x0C, 0x03 };

	RandomAccessFile raf;

	public TwoBitRandomAccessFile(File file) throws FileNotFoundException {
		raf = new RandomAccessFile(file, "rw");
	}

	public TwoBitRandomAccessFile(String filename) throws FileNotFoundException {
		raf = new RandomAccessFile(filename, "rw");
	}

	public TwoBitRandomAccessFile(File file, int numTwoBits) throws IOException {
		this(file);
		raf.setLength((long) Math.ceil(numTwoBits / 4.0));
	}

	public TwoBitRandomAccessFile(String filename, int numTwoBits) throws IOException {
		this(filename);
		raf.setLength((long) Math.ceil(numTwoBits / 4.0));
	}

	public void set(long pos, int val) throws IOException {
		if (val < 0 || val > 3) {
			throw new IllegalArgumentException("Value must be between 0 and 3");
		}

		long bytePos = pos / 4; // find byte of file
		int twoBitPos = (int) (pos % 4); // find two-bit position in byte

		raf.seek(bytePos); // seek byte in file
		byte b = raf.readByte(); // read the byte

		// Clear the 2-bit position
		b &= ~MASK[twoBitPos];

		// Set the new value
		b |= (val << (6 - twoBitPos * 2));

		// Write back the modified byte
		raf.seek(bytePos);
		raf.writeByte(b);
	}

	public int get(long pos) throws IOException {
		long bytePos = pos / 4;
		int twoBitPos = (int) (pos % 4);

		raf.seek(bytePos);
		byte b = raf.readByte();

		// Get the 2-bit value
		int value = (b & MASK[twoBitPos]) >>> (6 - twoBitPos * 2);
		return value;
	}

	public void clear() throws IOException {
		for (int i = 0; i < raf.length(); i++) {
			raf.seek(i);
			raf.write(0);
		}
	}

	@Override
	public void close() throws Exception {
		raf.close();
	}

	public static void main(String[] args) throws Exception {
		int numTwoBits = 50;
		TwoBitRandomAccessFile braf = new TwoBitRandomAccessFile("test-braf.dat", numTwoBits);
		braf.clear();

		// Test setting and getting values
		for (int i = 0; i < numTwoBits; i++) {
			int value = i % 4;
			braf.set(i, value);
			System.out.printf("Position %d: Set %d, Got %d%n", i, value, braf.get(i));
		}

		braf.close();
	}

}
