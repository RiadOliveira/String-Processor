public class ProcessingProvider {
    public static void brutalForce(
        String stringToProcess, String pattern
    ) {
    }

    public static void KMP(
        String stringToProcess, String pattern
    ) {
        int prefixTable[] = getKMPPrefixTable(pattern);
        int patternsFound = 0;

        for(
            int stringIndex=0, patternIndex=0 ;
            stringIndex<stringToProcess.length() ;
            stringIndex++
        ) {
            char stringCharacter = stringToProcess.charAt(
                stringIndex
            );
            char patternCharacter = pattern.charAt(
                patternIndex
            );

            boolean charactersAreDifferent = stringCharacter != patternCharacter;
            if(charactersAreDifferent) {
                if(patternIndex > 0) {
                    patternIndex = prefixTable[patternIndex - 1];
                    stringIndex--;
                }

                continue;
            }

            if(patternIndex == pattern.length() - 1) {
                patternIndex = prefixTable[patternIndex];
                patternsFound++;
            } else patternIndex++;
        }

        System.out.println(patternsFound);
    }

    private static int[] getKMPPrefixTable(String pattern) {
        int patternLength = pattern.length();
        int prefixTable[] = new int[patternLength];
        prefixTable[0] = 0;

        for(int j=0, i=1 ; i<patternLength ; i++) {
            char jChar = pattern.charAt(j);
            char iChar = pattern.charAt(i);

            if(iChar == jChar) prefixTable[i] = ++j;
            else if (j == 0) prefixTable[i] = 0;
            else {
                j = prefixTable[Math.max(j-1, 0)];
                i--;
            }
        }

        return prefixTable;
    }
}
