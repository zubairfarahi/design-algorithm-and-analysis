import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Main {
	static class Homework {
		public int deadline;
		public int score;

		public Homework() {
			deadline = 0;
			score = 0;
		}
	}


	static class Task {
		public final static String INPUT_FILE = "in";
		public final static String OUTPUT_FILE = "out";

		int n;
		Homework[] hws;

		private void readInput() {
			try {
				Scanner sc = new Scanner(new File(INPUT_FILE));
				n = sc.nextInt();
				hws = new Homework[n];
				for (int i = 0; i < n; i++) {
					hws[i] = new Homework();
					hws[i].deadline = sc.nextInt();
					hws[i].score = sc.nextInt();
				}
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}


		private void writeOutput(int result) {
			try {
				PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
				pw.printf("%d\n", result);
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public int minValue(int a,int b){
			return (a < b)? a : b;
		}

		private int getResult() {
			List<Homework> data = new ArrayList<>();
			Collections.sort(data, new Comparator<Homework>() {
				@Override
				public int compare(Homework o1, Homework o2) {
					if(((Homework)(o1)).score > ((Homework)(o2)).score)
						return 1;
					else if(((Homework)(o1)).score == ((Homework)(o2)).score)
						return 0;
					else return -1;
				}
			});

			int result =  ScoreWithDeadline(hws, n);
			return result;

		}

		public int ScoreWithDeadline(Homework jobs[], int n) {
			int i, j, k, maxScore;


			int list[] = new int[5];


			int filled_the_list = 0;

			int dmax = 0;
			for(i = 0; i < n; i++) {
				if(jobs[i].deadline > dmax) {
					dmax = jobs[i].deadline;
				}
			}

			for(i = 1; i <= dmax; i++) {
				list[i] = -1;
			}
			for(i = 1; i <= n; i++) {
				k = minValue(dmax, jobs[i - 1].deadline);
				while(k >= 1) {
					if(list[k] == -1) {
						list[k] = i-1;
                        filled_the_list++;
						break;
					}
					k--;
				}

			}


			maxScore = 0;
			for(i = 1; i <= dmax; i++) {
				maxScore += jobs[list[i]].score;
			}

			return maxScore;

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
