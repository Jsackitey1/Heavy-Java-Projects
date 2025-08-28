import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Random;

public class MakeAJoke {

	public String data = "";
	private Random random = new Random(42); 

	public static void main(java.lang.String[] args) {
		MakeAJoke tryjokes = new MakeAJoke();
		tryjokes.loadJokes("jokes.txt");
		System.out.println(tryjokes.getJoke());
	}

	public MakeAJoke() {

	}

	public boolean loadJokes(java.lang.String filename) {

		try {
			File file = new File(filename);
			if (file.exists()) {
				Scanner input = new Scanner(file);
				StringBuilder sb = new StringBuilder();
				while (input.hasNextLine()) {
					sb.append(input.nextLine()).append("\n");
				}
				input.close();
				data = sb.toString();

				return true;
			}

		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		return false;
	}

	public java.lang.String getJoke() {
		String[] mydata = data.split("joke:\n");
		int randomIndex = random.nextInt(mydata.length);
		return mydata[randomIndex].trim();
	}

	public java.lang.String getJoke(int index) {
		String[] mydata = data.split("joke:\n");
		if (index < 0 || index >= mydata.length - 1) {
			return "Invalid joke index";
		}
		return mydata[index + 1];
	}

	public void addJoke(java.lang.String joke) {
		String jokeToAdd = joke;
		if (!joke.endsWith("\n")) {
			jokeToAdd = joke + "\n";
		}
		this.data += "joke:\n" + jokeToAdd;
	}

	public int size() {
		String[] jokes = data.split("joke:\n");
		return Math.max(0, jokes.length - 1);
	}

	public boolean saveJokes(java.lang.String filename) throws FileNotFoundException {
		try (PrintWriter output = new PrintWriter(filename)) {
			String[] jokes = data.split("joke:\n");
			for (int i = 1; i < jokes.length; i++) {
				output.print("joke:\n");
				output.print(jokes[i]);
			}
			return true;
		}
	}
}
