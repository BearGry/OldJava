/**
 * 1.编写Java程序统计一篇英文文档中各单词出现的次数，并按单词出现的频率由高到低进行输出。
 * 例如：文档“Hello word Hello”的统计结果为：
 * 		 Hello:2次
 * 		 word:1次
 */
package sy1.java1;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordCounter {
    public static void main(String[] args) throws IOException {
        String filePath = "D:\\IdeaProjects\\javaSy\\files\\sy1\\java1";
        Map<String, Integer> wordMap = new HashMap<>();

        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            // 将行按照空格、逗号、句号等标点符号分割成单词
            String[] words = line.split("[\\s,.?!]+");
            for (String word : words) {
                // 将单词转换为小写字母
                word = word.toLowerCase();

                // 更新单词在Map中的计数
                Integer count = wordMap.getOrDefault(word, 0);
                wordMap.put(word, count + 1);
            }
        }

        // 对单词出现次数进行排序
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(wordMap.entrySet());
        entries.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        // 输出每个单词及其出现次数
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + "次");
        }
    }
}

