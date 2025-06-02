package com.mysite.hangman;

import java.io.File;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.apache.commons.io.FileUtils;

public class Util {
    public static List<String> loadWords() {
        List<String> words;
        URL wordsResourceUrl = Util.class.getClassLoader().getResource("words.txt");

        try {
            words = FileUtils.readLines(new File(wordsResourceUrl.toURI()), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка загрузки слов! " + e);
        }

        return words;
    }

    public static List<String> loadStages() {
        List<String> stages;

        URL stageOneResourceUrl = Util.class.getClassLoader().getResource("stages/stage1.txt");
        URL stageTwoResourceUrl = Util.class.getClassLoader().getResource("stages/stage2.txt");
        URL stageThreeResourceUrl = Util.class.getClassLoader().getResource("stages/stage3.txt");
        URL stageFourResourceUrl = Util.class.getClassLoader().getResource("stages/stage4.txt");
        URL stageFiveResourceUrl = Util.class.getClassLoader().getResource("stages/stage5.txt");
        URL stageSixResourceUrl = Util.class.getClassLoader().getResource("stages/stage6.txt");

        try {
            stages = List.of(
                    FileUtils.readFileToString(new File(stageOneResourceUrl.toURI()), StandardCharsets.UTF_8),
                    FileUtils.readFileToString(new File(stageTwoResourceUrl.toURI()), StandardCharsets.UTF_8),
                    FileUtils.readFileToString(new File(stageThreeResourceUrl.toURI()), StandardCharsets.UTF_8),
                    FileUtils.readFileToString(new File(stageFourResourceUrl.toURI()), StandardCharsets.UTF_8),
                    FileUtils.readFileToString(new File(stageFiveResourceUrl.toURI()), StandardCharsets.UTF_8),
                    FileUtils.readFileToString(new File(stageSixResourceUrl.toURI()), StandardCharsets.UTF_8)
            );
        } catch (Exception e) {
            throw new RuntimeException("Ошибка загрузки стадий! " + e);
        }

        return stages;
    }
}
