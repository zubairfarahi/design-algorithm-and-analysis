import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	static class Obj {
		public int weight;
		public int price;
		public double ratios;
		public Obj() {
			weight = 0;
			price = 0;
			ratios = 0.0;
		}
	};

	static class Task {
		public final static String INPUT_FILE = "in";
		public final static String OUTPUT_FILE = "out";

		int n, w;
		Obj[] objs;

		private void readInput() {
			try {
				Scanner sc = new Scanner(new File(INPUT_FILE));
				n = sc.nextInt();
				w = sc.nextInt();
				objs = new Obj[n];
				for (int i = 0; i < n; i++) {
					objs[i] = new Obj();
					objs[i].weight = sc.nextInt();
					objs[i].price = sc.nextInt();
					objs[i].ratios = (double) (objs[i].price) / objs[i].weight;
				}
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(double result) {
			try {
				PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
				pw.printf("%.4f\n", result);
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public int getMaximumRatioItem() {
			double maximumRatio = 0.0f;
			int maximumRatioIndex = -1;

			for (int i = 0; i < n; ++i) {
				if (maximumRatio < objs[i].ratios) {
					maximumRatio = objs[i].ratios;
					maximumRatioIndex = i;
				}
			}

			return maximumRatioIndex;
		}

		private double getResult() {

			int currentWeight = 0;
			float benefit = 0.0f;

			while (currentWeight < w) {
				int item = getMaximumRatioItem();

				if (item == -1) {
					return benefit;
				}

				for (int i = 0; i < objs[item].weight; ++i) {
					if (currentWeight + objs[item].ratios <= w) {
						currentWeight++;
						benefit += objs[item].ratios;
					}
				}

				objs[item].ratios = 0;
			}

			return benefit;

			//return 0;
		}

		public void solve() {
			readInput();
			writeOutput(getResult());
		}
	}

	public static void main(String[] args) {
		new Task().solve();
	}
}
