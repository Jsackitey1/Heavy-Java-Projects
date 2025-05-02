import java.util.*;

public class SortCSV {

	SortCSV() {

	}

	
	private static Object[] parseCSVLine(String line) {
		
		String[] values = line.split(",");
		Object[] record = new Object[values.length];

		for (int i = 0; i < values.length; i++) {
			String value = values[i];
			if (value.startsWith("\"") && value.endsWith("\"")) {
				// Remove quotes and store as String
				record[i] = value.substring(1, value.length() - 1);
			} else {
				// Try parsing as Integer first, then Double
				try {
					record[i] = Integer.parseInt(value);
				} catch (NumberFormatException e1) {
					try {
						record[i] = Double.parseDouble(value);
					} catch (NumberFormatException e2) {
						// If both parsing attempts fail, store as String
						record[i] = value;
					}
				}
			}
		}
		return record;
	}

	
	private static ArrayList<Object[]> readCSV(Scanner input) {
		ArrayList<Object[]> records = new ArrayList<>();
		while (input.hasNextLine()) {
			String line = input.nextLine();
			if (line.trim().isEmpty())
				continue;
			records.add(parseCSVLine(line));
		}
		return records;
	}

	
	public static void sort(ArrayList<Object[]> records, int sortIndex) {
		if (records == null || records.isEmpty() || sortIndex < 0) {
			return;
		}

		Collections.sort(records, (record1, record2) -> {
			Object val1 = record1[sortIndex];
			Object val2 = record2[sortIndex];

			// Handle null values
			
			if (val1 == null && val2 == null)
				return 0;
			if (val1 == null)
				return -1;
			if (val2 == null)
				return 1;

			if (val1 instanceof String) {
				return ((String) val1).compareTo((String) val2);
			} else if (val1 instanceof Double) {
				return ((Double) val1).compareTo((Double) val2);
			} else if (val1 instanceof Integer) {
				return ((Integer) val1).compareTo((Integer) val2);
			}

			return 0;
		});
	}

	
	private static String recordToCSV(Object[] record) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < record.length; i++) {
			if (i > 0)
				sb.append(",");
			if (record[i] instanceof String) {
				sb.append("\"").append(record[i]).append("\"");
			} else {
				sb.append(record[i]);
			}
		}
		return sb.toString();
	}

	
	private static void printCSV(ArrayList<Object[]> records) {
		for (Object[] record : records) {
			System.out.println(recordToCSV(record));
		}
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int stColumn = 0;
		if (args.length > 0) {
			stColumn = Integer.parseInt(args[0]);
		}

		ArrayList<Object[]> records = readCSV(input);
		
		input.close();
		sort(records, stColumn);
		
		printCSV(records);
	}
}
