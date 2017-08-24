/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package libMessage.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

/**
 * Загрузка бинарного файла.
 * @author Носов А.В.
 */
public class LoadFile {

    /**
     * Загрузить бинарный файл.
     * @param fileName путь к файлу
     * @return массив байт
     * @throws IOException ошибка загрузки 
     */
    public static byte[] loadFileAsBytes(String fileName) throws IOException {
        return loadFileAsBytes(new File(fileName));
    }

    /**
     * Загрузить бинарный файл.
     * @param file файл
     * @return массив байт
     * @throws IOException ошибка загрузки 
     */
    public static byte[] loadFileAsBytes(File file) throws IOException {
        byte[] result= new byte[(int)file.length()];
        loadFileAsBytes(file, result);
        return result;
    }

    /**
     * Загрузить бинарный файл.
     * @param file файл
     * @param buf массив байт
     * @throws IOException ошибка загрузки
     */
    public static void loadFileAsBytes(File file, byte[] buf) throws IOException {
        loadFileAsBytes(file, buf, 0, buf.length);
    }

    /**
     * Загрузить бинарный файл.
     * @param file файл
     * @param buf массив байт
     * @param off начальный аддрес в массиве
     * @param len длинна массива
     * @throws IOException ошибка загрузки
     */
    public static void loadFileAsBytes(File file, byte[] buf, int off, int len) 
            throws IOException {
        FileInputStream f= new FileInputStream(file);
        try {
            f.read(buf,off,len);
        } finally {
            try {
                f.close();
            } catch (Exception e) {
            }
        }
    }
    
    /**
     * Преобразует строку в массив байт.
     * @param hexString строка
     * @return массив байт
     */
    public static byte[] hexToBytes(String hexString) {
        HexBinaryAdapter adapter = new HexBinaryAdapter();
        byte[] bytes = adapter.unmarshal(hexString);
        return bytes;
    } 
}
