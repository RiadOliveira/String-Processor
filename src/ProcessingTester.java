import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProcessingTester {
    private static final String TEST_FILE_NAME = "testString.txt";
    private static final String PATTERNS_TO_SEARCH[] = new String[]{
        "TATATAAGAAAAAAG",
        "AGACTCTG",
        "GAGAGCGG",
        "TCCTCCCAC"
    };

    public static void executeTests() {
        try {
            String fileString = getTestFileString();

            for (String patternToSearch : PATTERNS_TO_SEARCH) {
                System.out.println("Padrão: " + patternToSearch);
                
                System.out.println("\nResultados Força Bruta:\n");
                ProcessingProvider.brutalForce(fileString, patternToSearch);

                System.out.println("\nResultados KMP:\n");
                ProcessingProvider.KMP(fileString, patternToSearch);

                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getTestFileString() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(
            new FileReader(TEST_FILE_NAME)
        );

        String finalStringContent = "";
        String lineContent = bufferedReader.readLine();
    
        while (lineContent != null) {
            finalStringContent += lineContent;
            lineContent = bufferedReader.readLine();
        }
        bufferedReader.close();

        return finalStringContent;
    }
}
