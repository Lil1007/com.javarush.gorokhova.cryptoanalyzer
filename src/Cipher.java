public class Cipher {

    private char[] alphabet;

    public Cipher(char[] alphabet) {
        this.alphabet = alphabet;
    }

    public char[] getAlphabet() {
        return alphabet;
    }

    public String encrypt(String text, int shift) {
        char[] charText = text.toCharArray();

        for (int i = 0; i < charText.length; i++) {
            if (charText[i] == '\n'){
                continue;
            }

            boolean charFound = false;
            for (int j = 0; j < alphabet.length; j++) {

                if (alphabet[j] == Character.toLowerCase(charText[i])) {
                    charFound = true;

                    int idxAlphabet = j + shift;
                    if (idxAlphabet > alphabet.length - 1) {
                        idxAlphabet = idxAlphabet - alphabet.length;
                    }
                    charText[i] = alphabet[idxAlphabet];
                    break;
                }
            }

            if (charFound == false){
                throw new RuntimeException(charText[i] + " - данный символ не найден в алфавите!");
            }
        }

        return new String(charText);
    }

    public String decrypt(String encryptedText, int shift) {
        char[] charText = encryptedText.toCharArray();

        for (int i = 0; i < charText.length; i++) {

            if (charText[i] == '\n'){
                continue;
            }

           boolean charFound = false;
            for (int j = 0; j < alphabet.length; j++) {
                if (alphabet[j] == Character.toLowerCase(charText[i])) {
                    charFound = true;

                    int idxAlphabet = j - shift;
                    if (idxAlphabet < 0) {
                        idxAlphabet = alphabet.length + idxAlphabet;
                    }
                    charText[i] = alphabet[idxAlphabet];
                    break;
                }
            }
            if (charFound == false){
                throw new RuntimeException(charText[i] + " - данный символ не найден в алфавите!");
            }
        }

        return new String(charText);
    }

    public String bruteForce(String text) {
        int bestKey = 0;
        int bestScore = 0;
        for (int shift = 0; shift < alphabet.length; shift++) {
            String decryptedText = decrypt(text, shift);
            int score = calculateScore(decryptedText);
            if (score > bestScore) {
                bestScore = score;
                bestKey = shift;
            }
        }
        return decrypt(text, bestKey);
    }

    private static int calculateScore(String text) {
        int score = 0;
        String[] words = text.split("\\s+");
        for (String word : words) {
            if (word.length() > 2) {
                score++;
            }
        }
        return score;
    }
}
