package uk.org.landeg.projecteuler.problems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.FileLoader;
import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(59)
public class Problem059 implements ProblemDescription<Long>{
	private static final Logger LOG = LoggerFactory.getLogger(Problem057.class);

	@Override
	public String getTask() {
		return "Your task has been made easy, as the encryption key consists of three lower case characters. "
				+ "Using cipher.txt (right click and 'Save Link/Target As...'), a file containing the encrypted ASCII codes, "
				+ "and the knowledge that the plain text must contain common English words, decrypt the message and "
				+ "find the sum of the ASCII values in the original text";
	}

	@Override
	public String getDescribtion() {
		return "A modern encryption method is to take a text file, convert the bytes to ASCII, "
				+ "then XOR each byte with a given value, taken from a secret key. The advantage with "
				+ "the XOR function is that using the same encryption key on the cipher text, "
				+ "restores the plain text; for example, 65 XOR 42 = 107, then 107 XOR 42 = 65.";
	}

	int maxPrime = 1000000;
	@Override
	public Long solve() {
		final String[] numbersAsText = FileLoader.readLines("p059_cipher.txt").get(0).split(",");
		final char[] cipher = new char[numbersAsText.length];
		final char[] decoded = new char[numbersAsText.length];
		for (int idx = 0 ; idx < numbersAsText.length ; idx++) {
			cipher[idx] = (char) Integer.parseInt(numbersAsText[idx]);
		}

		final char[] key = new char[3];
		final char[] bestKey = new char[3];
		key[0] = key[1] = key[2] = 'a';
		
		int charPos = 0;
		int score = 0;
		int maxScore = 0;
		int decodedScore = 0;
		int bestDecodedScore = 0;
		do {
			score = 0;
			decodedScore = 0;
			for (int idx = 0; idx < cipher.length ; idx++) {
				decoded[idx] = (char) (cipher[idx] ^ key[idx % key.length]);
				decodedScore += decoded[idx];
				if (decoded[idx] < ' ' || decoded[idx] > 'z') {
					break;
				}
				if (decoded[idx] == 'e') score += 2;
				if (decoded[idx] == 't') score += 2;
				if (decoded[idx] == 's') score += 2;
				if (decoded[idx] == ' ') score += 3;
				if (decoded[idx] == 'a') score++;
				if (decoded[idx] == 'i') score++;
				if (decoded[idx] == 'o') score++;
				if (decoded[idx] == 'u') score++;
			}
			if (score > maxScore) {
				maxScore = score;
				bestDecodedScore = decodedScore;
				LOG.trace("attempting key {} : {}", key, new String(decoded));
				System.arraycopy(key, 0, bestKey, 0, key.length);
			}
			
			// key increment
			key[key.length - 1]++;
			for (int idx = key.length - 1 ; idx >= 1 ; idx--) {
				if (key[idx] > 'z') {
					key[idx] = 'a';
					key[idx - 1]++;
				}
			}
		} while (key[0] <= 'z');
		return (long)bestDecodedScore;
	}
}
