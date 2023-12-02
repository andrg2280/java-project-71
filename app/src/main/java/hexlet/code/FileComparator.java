package hexlet.code;

import java.io.*;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.stream.Stream;

import java.util.stream.Collectors;

public class FileComparator {
    public static String compare(String s1, String s2) {
        String text1 = s1;
        String text2 = s2;
        Set<Integer> set = text2.chars()
                .boxed()
                .collect(Collectors.toCollection(LinkedHashSet::new));

        Set<Integer> set1 = text1.chars()
                .filter(ch -> !set.remove(ch))
                .boxed()
                .collect(Collectors.toCollection(LinkedHashSet::new));

        String result = Stream.of(set1,set).flatMap(s->s.stream())
                .mapToInt(Integer::intValue)
                .mapToObj(i->(char)i)
                .map(String::valueOf)
                .collect(Collectors.joining());

        return result;
    }
    public static String read(String fileName) throws FileNotFoundException {
        //Этот спец. объект для построения строки
        StringBuilder sb = new StringBuilder();

        exists(fileName);

        try {
            //Объект для чтения файла в буфер
            File file = new File(fileName);
            BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
            try {
                //В цикле построчно считываем файл
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                //Также не забываем закрыть файл
                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        //Возвращаем полученный текст с файла
        return sb.toString();
    }
    private static void exists(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()){
            throw new FileNotFoundException(file.getName());
        }
    }
}
