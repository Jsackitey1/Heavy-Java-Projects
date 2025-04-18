import java.io.*;
import java.util.ArrayList;

public class DoubleFileConverter {
	public DoubleFileConverter() {
	}

	public static void writeTextFile(String doubleTextFilename,
			ArrayList<Double> doubles) {
		try (PrintWriter out = new PrintWriter(new BufferedWriter(
				new FileWriter(doubleTextFilename)))) {
			for (double d : doubles) {
				out.println(d);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	// Read a text file that contains one double per line and return the list.
	public static ArrayList<Double> readTextFile(String doubleTextFilename) {
		ArrayList<Double> list = new ArrayList<>();
		try (BufferedReader in = new BufferedReader(
				new FileReader(doubleTextFilename))) {
			String line;
			while ((line = in.readLine()) != null) {
				if (!line.trim().isEmpty()) {
					list.add(Double.parseDouble(line.trim()));
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return list;
	}

	// Write each double in binary form (8 bytes) sequentially to the file.
	public static void writeBinaryFile(String doubleBinaryFilename,
			ArrayList<Double> doubles) {
		try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(
				new FileOutputStream(doubleBinaryFilename)))) {
			for (double d : doubles) {
				out.writeDouble(d);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	// Read sequential binary doubles from the file until EOF and return them.
	public static ArrayList<Double> readBinaryFile(String doubleBinaryFilename) {
		ArrayList<Double> list = new ArrayList<>();
		try (DataInputStream in = new DataInputStream(new BufferedInputStream(
				new FileInputStream(doubleBinaryFilename)))) {
			while (true) { // loop breaks on EOFException
				try {
					list.add(in.readDouble());
				} catch (EOFException eof) {
					break; // reached end of file
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return list;
	}

	// Serialize and write the whole {@code ArrayList<Double>}.
	public static void writeBinaryListFile(String doubleBinaryListFilename,
			ArrayList<Double> doubles) {
		try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(
				new FileOutputStream(doubleBinaryListFilename)))) {
			out.writeObject(doubles);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	// Read a serialized {@code ArrayList<Double>} from the given file.
	@SuppressWarnings("unchecked")
	public static ArrayList<Double> readBinaryListFile(String doubleBinaryListFilename) {
		ArrayList<Double> list = new ArrayList<>();
		try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(
				new FileInputStream(doubleBinaryListFilename)))) {
			Object obj = in.readObject();
			if (obj instanceof ArrayList<?>) {
				list = (ArrayList<Double>) obj;
			}
		} catch (IOException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return list;
	}
}
