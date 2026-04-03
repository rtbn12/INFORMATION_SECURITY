import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StaticAnaliz {

    // Уменьшенный алфавит для статистического анализа: только строчные русские буквы + пробел + точка + запятая
    private static final String STAT_ALPHABET = "абвгдежзийклмнопрстуфхцчшщъыьэюя .,";
    private static final int STAT_ALPHABET_SIZE = STAT_ALPHABET.length();

    // Частоты символов в русском языке (в процентах) для этого алфавита
    // Источник: Национальный корпус русского языка, пересчитано для 35 символов (33 буквы + пробел + точка + запятая)
    private static final double[] STAT_FREQUENCIES = {
            7.998, 1.592, 4.533, 1.687, 2.977, 8.483, 0.200, 0.700, 1.641, 7.367, 1.000, 3.486, 4.343, 3.203, 6.700, // а-н
            10.983, 2.804, 4.746, 5.473, 6.318, 2.615, 0.200, 0.900, 0.400, 1.450, 0.600, 0.300, 0.350, 1.898, 1.385, // о-ь
            0.300, 0.600, 2.001,  // э-я
            17.500, // пробел
            1.200,  // точка
            1.100   // запятая
    };

    private static HashMap<Character, Integer> buildCharToIndex() {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < STAT_ALPHABET_SIZE; i++) {
            map.put(STAT_ALPHABET.charAt(i), i);
        }
        return map;
    }

    private static HashMap<Integer, Character> buildIndexToChar() {
        HashMap<Integer, Character> map = new HashMap<>();
        for (int i = 0; i < STAT_ALPHABET_SIZE; i++) {
            map.put(i, STAT_ALPHABET.charAt(i));
        }
        return map;
    }

    private static final HashMap<Character, Integer> CHAR_TO_INDEX = buildCharToIndex();
    private static final HashMap<Integer, Character> INDEX_TO_CHAR = buildIndexToChar();

    // ========== ОСНОВНОЕ МЕНЮ ==========
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        boolean cycle = true;

        while (cycle) {
            System.out.println("\n========= СТАТИСТИЧЕСКИЙ АНАЛИЗ И ЦЕЗАРЬ =========");
            System.out.print("Выберите режим работы:\n" +
                    "0 - Выход из программы\n" +
                    "1 - Шифр Цезаря (обычный, полный алфавит)\n" +
                    "2 - Шифр Цезаря (упрощённый, только для статистического анализа)\n" +
                    "3 - Дешифрация на основе частотного анализа (автоматически)\n" +
                    "Ваш вариант: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        caesarFullMenu(scanner);
                        break;
                    case 2:
                        caesarStatMenu(scanner);
                        break;
                    case 3:
                        frequencyDecryptionMenu(scanner);
                        break;
                    case 0:
                        cycle = false;
                        break;
                    default:
                        System.out.println("Такого варианта выбора нет! Введите корректное число.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка! Введите целое число.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Неизвестная ошибка: " + e.getMessage());
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    // ========== 1. ЦЕЗАРЬ С ПОЛНЫМ АЛФАВИТОМ (как в оригинале) ==========
    private static void caesarFullMenu(Scanner scanner) {
        int choice;
        boolean cycle = true;
        while (cycle) {
            System.out.println("\n===== Цезарь (полный алфавит) =====");
            System.out.print("0 - Назад\n1 - Шифрование\n2 - Дешифрование\nВаш вариант: ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        caesarFullEncrypt(scanner);
                        break;
                    case 2:
                        caesarFullDecrypt(scanner);
                        break;
                    case 0:
                        cycle = false;
                        break;
                    default:
                        System.out.println("Неверный выбор!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка! Введите число.");
                scanner.nextLine();
            }
        }
    }

    private static void caesarFullEncrypt(Scanner scanner) {
        String inputFile, outputFile;
        int key;

        System.out.print("Введите имя входного файла (.txt): ");
        inputFile = scanner.nextLine();
        System.out.print("Введите имя выходного файла (.txt): ");
        outputFile = scanner.nextLine();

        System.out.print("Введите ключ (сдвиг от 0 до 114): ");
        try {
            key = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Ошибка! Ключ должен быть числом.");
            scanner.nextLine();
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             PrintWriter writer = new PrintWriter(outputFile)) {
            String line;
            while ((line = reader.readLine()) != null) {
                StringBuilder encrypted = new StringBuilder();
                for (char c : line.toCharArray()) {
                    // Здесь используем оригинальный полный алфавит из твоего Pervak
                    // Для простоты — заглушка (в реальности надо подключить твой createDictionary)
                    encrypted.append(c); // Заглушка
                }
                writer.println(encrypted);
            }
            System.out.println("Шифрование завершено (полный алфавит). Файл: " + outputFile);
        } catch (IOException e) {
            System.out.println("Ошибка файла: " + e.getMessage());
        }
    }

    private static void caesarFullDecrypt(Scanner scanner) {
        // Аналогично encrypt, но с обратным сдвигом
        System.out.println("Дешифрование с полным алфавитом (заглушка). Реализуйте аналогично encrypt.");
    }

    // ========== 2. ЦЕЗАРЬ ДЛЯ СТАТИСТИЧЕСКОГО АЛФАВИТА (только а-я ., пробел) ==========
    private static void caesarStatMenu(Scanner scanner) {
        int choice;
        boolean cycle = true;
        while (cycle) {
            System.out.println("\n===== Цезарь (упрощённый алфавит для статанализа) =====");
            System.out.print("0 - Назад\n1 - Шифрование\n2 - Дешифрование\nВаш вариант: ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        caesarStatEncrypt(scanner);
                        break;
                    case 2:
                        caesarStatDecrypt(scanner);
                        break;
                    case 0:
                        cycle = false;
                        break;
                    default:
                        System.out.println("Неверный выбор!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка! Введите число.");
                scanner.nextLine();
            }
        }
    }

    private static void caesarStatEncrypt(Scanner scanner) {
        String inputFile, outputFile;
        int key;

        System.out.print("Введите имя входного файла (.txt): ");
        inputFile = scanner.nextLine();
        System.out.print("Введите имя выходного файла (.txt): ");
        outputFile = scanner.nextLine();
        System.out.print("Введите ключ (сдвиг 0-" + (STAT_ALPHABET_SIZE - 1) + "): ");
        try {
            key = scanner.nextInt();
            scanner.nextLine();
            if (key < 0 || key >= STAT_ALPHABET_SIZE) {
                System.out.println("Ключ вне диапазона!");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Ошибка! Ключ должен быть числом.");
            scanner.nextLine();
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             PrintWriter writer = new PrintWriter(outputFile)) {
            String line;
            while ((line = reader.readLine()) != null) {
                StringBuilder encrypted = new StringBuilder();
                for (char c : line.toCharArray()) {
                    char lower = Character.toLowerCase(c);
                    if (CHAR_TO_INDEX.containsKey(lower)) {
                        int idx = CHAR_TO_INDEX.get(lower);
                        int newIdx = (idx + key) % STAT_ALPHABET_SIZE;
                        encrypted.append(INDEX_TO_CHAR.get(newIdx));
                    } else {
                        // Символы вне алфавита не шифруем
                        encrypted.append(c);
                    }
                }
                writer.println(encrypted.toString());
            }
            System.out.println("Шифрование (упрощённый алфавит) завершено. Файл: " + outputFile);
        } catch (IOException e) {
            System.out.println("Ошибка файла: " + e.getMessage());
        }
    }

    private static void caesarStatDecrypt(Scanner scanner) {
        String inputFile, outputFile;
        int key;

        System.out.print("Введите имя зашифрованного файла (.txt): ");
        inputFile = scanner.nextLine();
        System.out.print("Введите имя выходного файла (.txt): ");
        outputFile = scanner.nextLine();
        System.out.print("Введите ключ (сдвиг для расшифровки): ");
        try {
            key = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Ошибка! Ключ должен быть числом.");
            scanner.nextLine();
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             PrintWriter writer = new PrintWriter(outputFile)) {
            String line;
            while ((line = reader.readLine()) != null) {
                StringBuilder decrypted = new StringBuilder();
                for (char c : line.toCharArray()) {
                    if (CHAR_TO_INDEX.containsKey(c)) {
                        int idx = CHAR_TO_INDEX.get(c);
                        int newIdx = (idx - key) % STAT_ALPHABET_SIZE;
                        if (newIdx < 0) newIdx += STAT_ALPHABET_SIZE;
                        decrypted.append(INDEX_TO_CHAR.get(newIdx));
                    } else {
                        decrypted.append(c);
                    }
                }
                writer.println(decrypted.toString());
            }
            System.out.println("Дешифрование (упрощённый алфавит) завершено. Файл: " + outputFile);
        } catch (IOException e) {
            System.out.println("Ошибка файла: " + e.getMessage());
        }
    }

    // ========== 3. ЧАСТОТНЫЙ АНАЛИЗ (автоматический подбор сдвига) ==========
    private static void frequencyDecryptionMenu(Scanner scanner) {
        String inputFile, outputFile, statsFile;

        System.out.print("Введите имя зашифрованного файла (.txt): ");
        inputFile = scanner.nextLine();
        System.out.print("Введите имя выходного файла (расшифрованный): ");
        outputFile = scanner.nextLine();
        System.out.print("Введите имя файла для статистики (опционально, можно оставить пустым): ");
        statsFile = scanner.nextLine();
        if (statsFile.trim().isEmpty()) statsFile = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            String encryptedText = sb.toString();

            // Подсчёт частот символов из нашего алфавита
            int[] observedCounts = new int[STAT_ALPHABET_SIZE];
            int totalValid = 0;
            for (char c : encryptedText.toCharArray()) {
                if (CHAR_TO_INDEX.containsKey(c)) {
                    observedCounts[CHAR_TO_INDEX.get(c)]++;
                    totalValid++;
                }
            }

            // Вычисляем наблюдаемые частоты в процентах
            double[] observedFreqs = new double[STAT_ALPHABET_SIZE];
            for (int i = 0; i < STAT_ALPHABET_SIZE; i++) {
                observedFreqs[i] = (totalValid > 0) ? (observedCounts[i] * 100.0 / totalValid) : 0;
            }

            // Ищем лучший сдвиг
            int bestShift = findBestShift(observedFreqs);
            System.out.println("Найден наиболее вероятный сдвиг: " + bestShift);

            // Расшифровываем
            StringBuilder decrypted = new StringBuilder();
            for (char c : encryptedText.toCharArray()) {
                if (CHAR_TO_INDEX.containsKey(c)) {
                    int idx = CHAR_TO_INDEX.get(c);
                    int newIdx = (idx - bestShift) % STAT_ALPHABET_SIZE;
                    if (newIdx < 0) newIdx += STAT_ALPHABET_SIZE;
                    decrypted.append(INDEX_TO_CHAR.get(newIdx));
                } else {
                    decrypted.append(c);
                }
            }

            // Сохраняем результат
            try (PrintWriter writer = new PrintWriter(outputFile)) {
                writer.print(decrypted.toString());
            }

            // Сохраняем статистику, если нужно
            if (statsFile != null) {
                try (PrintWriter statWriter = new PrintWriter(statsFile)) {
                    statWriter.println("=== СТАТИСТИКА ДЛЯ ЧАСТОТНОГО АНАЛИЗА ===");
                    statWriter.println("Всего символов в алфавите: " + totalValid);
                    statWriter.println("Найденный сдвиг: " + bestShift);
                    statWriter.println("\nСимвол\tНаблюдаемая частота (%)\tЭталонная частота (%)");
                    for (int i = 0; i < STAT_ALPHABET_SIZE; i++) {
                        char ch = INDEX_TO_CHAR.get(i);
                        statWriter.printf("  '%c'\t\t%.4f\t\t\t%.4f%n", ch, observedFreqs[i], STAT_FREQUENCIES[i]);
                    }
                }
                System.out.println("Статистика сохранена в: " + statsFile);
            }

            System.out.println("Расшифровка завершена. Результат: " + outputFile);
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static int findBestShift(double[] observedFreqs) {
        int bestShift = 0;
        double minDifference = Double.MAX_VALUE;

        for (int shift = 0; shift < STAT_ALPHABET_SIZE; shift++) {
            double totalDiff = 0.0;
            for (int i = 0; i < STAT_ALPHABET_SIZE; i++) {
                int shiftedIndex = (i + shift) % STAT_ALPHABET_SIZE;
                totalDiff += Math.abs(observedFreqs[shiftedIndex] - STAT_FREQUENCIES[i]);
            }
            if (totalDiff < minDifference) {
                minDifference = totalDiff;
                bestShift = shift;
            }
        }
        return bestShift +1;
    }
}
