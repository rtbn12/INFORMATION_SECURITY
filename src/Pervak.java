import java.io.*;
import java.util.*;

public class Pervak {

    protected static  HashMap<Integer, Character> dictionary = new HashMap<Integer, Character>();
    protected int key =0;
    protected String nameIn;
    protected String nameOut;



    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        int algoritmChoice;
        boolean algoritmCycle = true;
        while (algoritmCycle){

            System.out.print("Выберите режим работы:\n" +
                    "0 - Выход из программы\n" +
                    "1 - Алгоритм Виженера\n" +
                    "2 - Алгоритм Цезаря\n" +
                    "Ваш вариант:");


            try {
                algoritmChoice = scanner.nextInt();
                scanner.nextLine();

                switch (algoritmChoice)
                {
                    case 1:
                        Vizener(scanner);
                        break;
                    case 2:
                        Cezar(scanner);
                        break;
                    case 0:
                        algoritmCycle = false;
                        break;
                    default:
                        System.out.println("Такого варианта выбора нет!\n" +
                                "Пожалуйста, введите корректное число!");
                }

            }catch (InputMismatchException e) {
                System.out.println("Произошла ошибка!\n" +
                        "Пожалуйста, введите корректное целочисленное значение выбранного варианта!\n" +
                        "В прошлый раз вы ввели букву вместо числа!");
                scanner.nextLine();
            }
            catch (Exception a) {
                System.out.println("Произошла неизвестная ошибка!");
                scanner.nextLine();
            }

        }




    }

    public static HashMap<Integer, Character> createDictionary() {
        HashMap<Integer, Character> dict = new HashMap<>();

        // 1. Строчные русские буквы (33 символа)
        dict.put(0, 'а');
        dict.put(1, 'б');
        dict.put(2, 'в');
        dict.put(3, 'г');
        dict.put(4, 'д');
        dict.put(5, 'е');
        dict.put(6, 'ё');
        dict.put(7, 'ж');
        dict.put(8, 'з');
        dict.put(9, 'и');
        dict.put(10, 'й');
        dict.put(11, 'к');
        dict.put(12, 'л');
        dict.put(13, 'м');
        dict.put(14, 'н');
        dict.put(15, 'о');
        dict.put(16, 'п');
        dict.put(17, 'р');
        dict.put(18, 'с');
        dict.put(19, 'т');
        dict.put(20, 'у');
        dict.put(21, 'ф');
        dict.put(22, 'х');
        dict.put(23, 'ц');
        dict.put(24, 'ч');
        dict.put(25, 'ш');
        dict.put(26, 'щ');
        dict.put(27, 'ъ');
        dict.put(28, 'ы');
        dict.put(29, 'ь');
        dict.put(30, 'э');
        dict.put(31, 'ю');
        dict.put(32, 'я');

        // 2. Прописные русские буквы (33 символа)
        dict.put(33, 'А');
        dict.put(34, 'Б');
        dict.put(35, 'В');
        dict.put(36, 'Г');
        dict.put(37, 'Д');
        dict.put(38, 'Е');
        dict.put(39, 'Ё');
        dict.put(40, 'Ж');
        dict.put(41, 'З');
        dict.put(42, 'И');
        dict.put(43, 'Й');
        dict.put(44, 'К');
        dict.put(45, 'Л');
        dict.put(46, 'М');
        dict.put(47, 'Н');
        dict.put(48, 'О');
        dict.put(49, 'П');
        dict.put(50, 'Р');
        dict.put(51, 'С');
        dict.put(52, 'Т');
        dict.put(53, 'У');
        dict.put(54, 'Ф');
        dict.put(55, 'Х');
        dict.put(56, 'Ц');
        dict.put(57, 'Ч');
        dict.put(58, 'Ш');
        dict.put(59, 'Щ');
        dict.put(60, 'Ъ');
        dict.put(61, 'Ы');
        dict.put(62, 'Ь');
        dict.put(63, 'Э');
        dict.put(64, 'Ю');
        dict.put(65, 'Я');

        // 3. Цифры (10 символов)
        dict.put(66, '0');
        dict.put(67, '1');
        dict.put(68, '2');
        dict.put(69, '3');
        dict.put(70, '4');
        dict.put(71, '5');
        dict.put(72, '6');
        dict.put(73, '7');
        dict.put(74, '8');
        dict.put(75, '9');

        // 4. ПРОБЕЛ
        dict.put(76, ' ');  // обычный пробел

        // 5. Знаки препинания и символы
        dict.put(77, '!');
        dict.put(78, '"');
        dict.put(79, '#');
        dict.put(80, '$');
        dict.put(81, '%');
        dict.put(82, '&');
        dict.put(83, '\'');  // одинарная кавычка
        dict.put(84, '(');
        dict.put(85, ')');
        dict.put(86, '*');
        dict.put(87, '+');
        dict.put(88, ',');
        dict.put(89, '-');
        dict.put(90, '.');
        dict.put(91, '/');
        dict.put(92, ':');
        dict.put(93, ';');
        dict.put(94, '<');
        dict.put(95, '=');
        dict.put(96, '>');
        dict.put(97, '?');
        dict.put(98, '@');
        dict.put(99, '[');
        dict.put(100, '\\');  // обратный слеш
        dict.put(101, ']');
        dict.put(102, '^');
        dict.put(103, '_');
        dict.put(104, '`');
        dict.put(105, '{');
        dict.put(106, '|');
        dict.put(107, '}');
        dict.put(108, '~');
        dict.put(109, '№');

        // 6. Специальные символы
        dict.put(110, '\n');  // перевод строки (enter)


        // 7. Дополнительные символы
        dict.put(111, '«');
        dict.put(112, '»');
        dict.put(113, '—');
        dict.put(114, '…');

        return dict;
    }

    public static void shifrovanie(Scanner scanner) {
        String name_ishodnik = "";
        String name_end = "";


        HashMap<Integer, Character> dictionary = createDictionary();


        HashMap<Character, Integer> reverseDict = new HashMap<>();
        for (Map.Entry<Integer, Character> entry : dictionary.entrySet()) {
            reverseDict.put(entry.getValue(), entry.getKey());
        }

        int dictSize = dictionary.size();

        boolean cycle = true;
        while (cycle) {
            System.out.print("Введите название файла, который хотите зашифровать (не забудьте добавить .txt)\n" +
                    "Название: ");

            name_ishodnik = scanner.nextLine();

            if (name_ishodnik.trim().isEmpty()) {
                System.out.println("Имя файла не может быть пустым!");
                continue;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(name_ishodnik))) {
                ArrayList<String> lines = new ArrayList<>();
                String line;

                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }

                cycle = false;

                System.out.print("Введите название файла для зашифрованного результата (с .txt): ");
                name_end = scanner.nextLine();

                if (name_end.trim().isEmpty()) {
                    name_end = name_ishodnik.replace(".txt", "_encrypted.txt");
                    if (name_end.equals(name_ishodnik)) {
                        name_end = name_ishodnik + "_encrypted.txt";
                    }
                    System.out.println("Будет использовано имя: " + name_end);
                }

                boolean key_cycle = true;
                int key = 0;
                while (key_cycle) {
                    System.out.print("Введи ключ шифрования от 0 до 114\n" +
                            "Ваш вариант: ");
                    try {
                        key = scanner.nextInt();
                        scanner.nextLine();

                        if (key >= 0 && key <= 114) {
                            System.out.println("\nКлюч успешно учтён!");
                            key_cycle = false;
                        } else {
                            System.out.println("\nВведено некорректное значение ключа! Попробуйте ещё раз");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Произошла ошибка!\n" +
                                "Пожалуйста, введите корректное целочисленное значение выбранного варианта!\n" +
                                "В прошлый раз вы ввели букву вместо числа!");
                        scanner.nextLine();
                    } catch (Exception a) {
                        System.out.println("Произошла неизвестная ошибка!");
                        scanner.nextLine();
                    }
                }


                try (PrintWriter writer = new PrintWriter(name_end)) {
                    for (String originalLine : lines) {
                        StringBuilder encryptedLine = new StringBuilder();

                        for (char c : originalLine.toCharArray()) {
                            if (reverseDict.containsKey(c)) {
                                int originalIndex = reverseDict.get(c);
                                int newIndex = (originalIndex + key) % dictSize;
                                encryptedLine.append(dictionary.get(newIndex));
                            } else {

                                encryptedLine.append(c);
                            }
                        }

                        writer.println(encryptedLine.toString());
                    }

                    System.out.println("Файл успешно зашифрован и сохранен: " + name_end);
                    System.out.println("Использован ключ: " + key);



                } catch (FileNotFoundException e) {
                    System.out.println("Не удалось создать выходной файл: " + e.getMessage());
                }

            } catch (FileNotFoundException e) {
                System.out.println("\nФайл с таким именем не найден, попробуйте ввести имя ещё раз!");
            } catch (IOException e) {
                System.out.println("Ошибка чтения файла: " + e.getMessage());
                System.out.println("Попробуйте ещё раз!");
            }
        }
    }

    public static void deshifrovanie(Scanner scanner){
        String name_ishodnik = "";
        String name_end = "";


        HashMap<Integer, Character> dictionary = createDictionary();


        HashMap<Character, Integer> reverseDict = new HashMap<>();
        for (Map.Entry<Integer, Character> entry : dictionary.entrySet()) {
            reverseDict.put(entry.getValue(), entry.getKey());
        }

        int dictSize = dictionary.size();

        boolean cycle = true;
        while (cycle) {
            System.out.print("Введите название файла, который хотите расшифровать (не забудьте добавить .txt)\n" +
                    "Название: ");

            name_ishodnik = scanner.nextLine();

            if (name_ishodnik.trim().isEmpty()) {
                System.out.println("Имя файла не может быть пустым!");
                continue;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(name_ishodnik))) {
                ArrayList<String> lines = new ArrayList<>();
                String line;

                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }

                cycle = false;

                System.out.print("Введите название файла для файла, в который хотите сохранить расшифрованный результат (с .txt): ");
                name_end = scanner.nextLine();

                if (name_end.trim().isEmpty()) {
                    name_end = name_ishodnik.replace(".txt", "_encrypted.txt");
                    if (name_end.equals(name_ishodnik)) {
                        name_end = name_ishodnik + "_encrypted.txt";
                    }
                    System.out.println("Будет использовано имя: " + name_end);
                }

                boolean key_cycle = true;
                int key = 0;
                while (key_cycle) {
                    System.out.print("Введи ключ шифрования от 0 до 114\n" +
                            "Ваш вариант: ");
                    try {
                        key = scanner.nextInt();
                        scanner.nextLine();

                        if (key >= 0 && key <= 114) {
                            System.out.println("\nКлюч успешно учтён!");
                            key_cycle = false;
                        } else {
                            System.out.println("\nВведено некорректное значение ключа! Попробуйте ещё раз");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Произошла ошибка!\n" +
                                "Пожалуйста, введите корректное целочисленное значение выбранного варианта!\n" +
                                "В прошлый раз вы ввели букву вместо числа!");
                        scanner.nextLine();
                    } catch (Exception a) {
                        System.out.println("Произошла неизвестная ошибка!");
                        scanner.nextLine();
                    }
                }


                try (PrintWriter writer = new PrintWriter(name_end)) {
                    for (String originalLine : lines) {
                        StringBuilder encryptedLine = new StringBuilder();

                        for (char c : originalLine.toCharArray()) {
                            if (reverseDict.containsKey(c)) {
                                int originalIndex = reverseDict.get(c);
                                int newIndex;
                                if(originalIndex-key >=0){
                                    newIndex = originalIndex-key;
                                }else{
                                    newIndex =  originalIndex - key + 115;
                                }

                                encryptedLine.append(dictionary.get(newIndex));
                            } else {

                                encryptedLine.append(c);
                            }
                        }

                        writer.println(encryptedLine.toString());
                    }

                    System.out.println("Файл успешно расшифрован и сохранен: " + name_end);
                    System.out.println("Использован ключ: " + key);



                } catch (FileNotFoundException e) {
                    System.out.println("Не удалось создать выходной файл: " + e.getMessage());
                }

            } catch (FileNotFoundException e) {
                System.out.println("\nФайл с таким именем не найден, попробуйте ввести имя ещё раз!");
            } catch (IOException e) {
                System.out.println("Ошибка чтения файла: " + e.getMessage());
                System.out.println("Попробуйте ещё раз!");
            }
        }
    }

    public static void Vizener(Scanner scanner){
        int vizinerChoice;
        boolean vizinerCycle = true;
        while (vizinerCycle){

            System.out.println("=========Алгоритм Виженера=========");
            System.out.print("Выберите режим работы:\n" +
                    "0 - Выход из программы\n" +
                    "1 - Шифрование\n" +
                    "2 - Дешифрование\n" +
                    "Ваш вариант:");


            try {
                vizinerChoice = scanner.nextInt();
                scanner.nextLine();

                switch (vizinerChoice)
                {
                    case 1:
                        shifrovanieVISINER();
                        break;
                    case 2:
                        deShifrovanieVISENER();
                        break;
                    case 0:
                        vizinerCycle = false;
                        break;
                    default:
                        System.out.println("Такого варианта выбора нет!\n" +
                                "Пожалуйста, введите корректное число!");
                }

            }catch (InputMismatchException e) {
                System.out.println("Произошла ошибка!\n" +
                        "Пожалуйста, введите корректное целочисленное значение выбранного варианта!\n" +
                        "В прошлый раз вы ввели букву вместо числа!");
                scanner.nextLine();
            }
            catch (Exception a) {
                System.out.println("Произошла неизвестная ошибка!");
                scanner.nextLine();
            }

        }
    }


    public static void Cezar(Scanner scanner){

        int printChoice;
        boolean print = true;
        while(print)
        {
            System.out.println("=============Алгоритм Цезаря==========");
            System.out.print("Выберите режим работы:\n" +
                    "0 - Выход из программы\n" +
                    "1 - Шифрование\n" +
                    "2 - Дешифрование\n" +
                    "Ваш вариант:");


            try {
                printChoice = scanner.nextInt();
                scanner.nextLine();

                switch (printChoice)
                {
                    case 1:
                        shifrovanie(scanner);
                        break;
                    case 2:
                        deshifrovanie(scanner);
                        break;
                    case 0:
                        print = false;
                        break;
                    default:
                        System.out.println("Такого варианта выбора нет!\n" +
                                "Пожалуйста, введите корректное число!");
                }

            }catch (InputMismatchException e) {
                System.out.println("Произошла ошибка!\n" +
                        "Пожалуйста, введите корректное целочисленное значение выбранного варианта!\n" +
                        "В прошлый раз вы ввели букву вместо числа!");
                scanner.nextLine();
            }
            catch (Exception a) {
                System.out.println("Произошла неизвестная ошибка!");
                scanner.nextLine();
            }

        }

    }

    public static void shifrovanieVISINER(){

    }

    public static void deShifrovanieVISENER(){

    }


    }

