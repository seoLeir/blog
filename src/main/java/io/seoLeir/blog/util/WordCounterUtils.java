package io.seoLeir.blog.util;

import org.springframework.stereotype.Component;

@Component
public class WordCounterUtils {
    public int wordsCountInString(String publicationText){
        final int WORD = 0;
        final int SEPARATOR = 1;
        if (publicationText == null) {
            return 0;
        }
        int flag = SEPARATOR;
        int count = 0;
        int stringLength = publicationText.length();
        int characterCounter = 0;

        while (characterCounter < stringLength) {
            if (isAllowedInWord(publicationText.charAt(characterCounter)) && flag == SEPARATOR) {
                flag = WORD;
                count++;
            } else if (!isAllowedInWord(publicationText.charAt(characterCounter))) {
                flag = SEPARATOR;
            }
            characterCounter++;
        }
        return count;
    }

    private boolean isAllowedInWord(char charAt) {
        return charAt == '\'' || Character.isLetter(charAt);
    }
}
