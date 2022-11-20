public class ProcessingProvider {
    public static void brutalForce(
        String stringToProcess, String pattern
    ) {
        int patternsFound = 0, comparsionsQuantity = 1;
        int patternLength = pattern.length();

        for(
            int stringIndex=0 ;
            stillNeedsToComparePattern(stringToProcess, stringIndex, pattern) ;
            stringIndex++
        ) {
            for(
                int paternIndex=0 ;
                paternIndex < patternLength
                ; paternIndex++, comparsionsQuantity++
            ) {
                char stringChar = stringToProcess.charAt(stringIndex + paternIndex);
                char patternChar = pattern.charAt(paternIndex);

                if(stringChar != patternChar) break;
                if(paternIndex != patternLength - 1) continue;
                patternsFound++;

                System.out.println(
                    "Foram feitas " + comparsionsQuantity +
                    " comparações para encontrar o " + patternsFound +
                    "º padrão."
                );
                comparsionsQuantity = 0;
            }
        }

        System.out.println("Quantidade de padrões encontados: " + patternsFound);
    }

    private static boolean stillNeedsToComparePattern(
        String stringToProcess, int stringIndex, String pattern
    ) {
        int stringLength = stringToProcess.length();
        int patternLength = pattern.length();

        return stringIndex <= (stringLength - patternLength);
    }

    public static void KMP(
        String stringToProcess, String pattern
    ) {
        int prefixTable[] = getKMPPrefixTable(pattern);
        int patternsFound = 0, comparsionsQuantity = 1;

        for(
            int stringIndex=0, patternIndex=0 ;
            stillNeedsToComparePattern(stringToProcess, stringIndex, pattern) ;
            stringIndex++, comparsionsQuantity++
        ) {
            char stringChar = stringToProcess.charAt(stringIndex);
            char patternChar = pattern.charAt(patternIndex);

            if(stringChar != patternChar) {
                if(patternIndex > 0) {
                    patternIndex = prefixTable[patternIndex - 1];
                    stringIndex--;
                }

                continue;
            }

            if(patternIndex != pattern.length() - 1) {
                patternIndex++;
                continue;
            }
            
            patternIndex = prefixTable[patternIndex];
            patternsFound++;

            System.out.println(
                "Foram feitas " + comparsionsQuantity +
                " comparações para encontrar o " + patternsFound +
                "º padrão."
            );
            comparsionsQuantity = 0;
        }

        System.out.println("Quantidade de padrões encontados: " + patternsFound);
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
