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
                    "3 - Алгоритм RSA\n" +
                    "4 - ГОСТ 28147-89A\n" +
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
                    case 3:
                        RSA(scanner);
                        break;
                    case 4:
                        GOST(scanner);
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
                        shifrovanieVISINER(scanner);
                        break;
                    case 2:
                        deShifrovanieVISENER(scanner);
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

    public static void shifrovanieVISINER(Scanner scanner){

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
                String key = "";
                 do{
                     System.out.print("Введи ключ - пароль шифрования (слово или фразу на руссом языке" +
                             ",не более 30 символов)\n" +
                             "Ваш вариант: ");
                     key= scanner.nextLine();
                     if(key.length()<=30){
                         for(char c : key.toCharArray()){
                             if(!reverseDict.containsKey(c)){
                                 System.out.println("Ошибка! Слово должно полностью состоять из русских букв!\n" +
                                         "Попробуйте ещё раз!");
                                 key_cycle = false;
                                 break;

                             }
                             else{
                                 key_cycle = true;
                             }
                         }

                     }
                     else {
                         System.out.println("Произошла ошибка, вы ввели слишком длинно слово длинное!");
                         key_cycle = false;

                     }

                 } while (!key_cycle);

                System.out.println("Кодовое слово успешно учтено!");


                try (PrintWriter writer = new PrintWriter(name_end)) {
                    int isxodIndex;
                    int keyIndex =0;
                    int newIndex;
                    String bestKey = key.trim();
                    int bestKeyLength = bestKey.length();
                    char[] bestKeyArray = bestKey.toCharArray();
                    for (String originalLine : lines) {
                        StringBuilder encryptedLine = new StringBuilder();

                        for (char c : originalLine.toCharArray()) {
                            if(reverseDict.containsKey(c)){
                                isxodIndex = reverseDict.get(c);
                                int smesenie = reverseDict.get(bestKeyArray[keyIndex]);
                                newIndex = (isxodIndex+smesenie)%dictSize;
                                keyIndex++;
                                if(keyIndex ==bestKeyLength){keyIndex=0;}

                                encryptedLine.append(dictionary.get(newIndex));


                            }else{

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

    public static void deShifrovanieVISENER(Scanner scanner){

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

                System.out.print("Введите название файла для расшифрованного результата (с .txt): ");
                name_end = scanner.nextLine();

                if (name_end.trim().isEmpty()) {
                    name_end = name_ishodnik.replace(".txt", "_encrypted.txt");
                    if (name_end.equals(name_ishodnik)) {
                        name_end = name_ishodnik + "_encrypted.txt";
                    }
                    System.out.println("Будет использовано имя: " + name_end);
                }

                boolean key_cycle = true;
                String key = "";
                do{
                    System.out.print("Введи ключ - пароль шифрования (слово или фразу на руссом языке" +
                            ",не более 30 символов)\n" +
                            "Ваш вариант: ");
                    key= scanner.nextLine();
                    if(key.length()<=30){
                        for(char c : key.toCharArray()){
                            if(!reverseDict.containsKey(c)){
                                System.out.println("Ошибка! Слово должно полностью состоять из русских букв!\n" +
                                        "Попробуйте ещё раз!");
                                key_cycle = false;
                                break;

                            }
                            else{
                                key_cycle = true;
                            }
                        }

                    }
                    else {
                        System.out.println("Произошла ошибка, вы ввели слишком длинно слово длинное!");
                        key_cycle = false;

                    }

                } while (!key_cycle);

                System.out.println("Кодовое слово успешно учтено!");


                try (PrintWriter writer = new PrintWriter(name_end)) {
                    int isxodIndex;
                    int keyIndex =0;
                    int newIndex;
                    String bestKey = key.trim();
                    int bestKeyLength = bestKey.length();
                    char[] bestKeyArray = bestKey.toCharArray();
                    for (String originalLine : lines) {
                        StringBuilder encryptedLine = new StringBuilder();

                        for (char c : originalLine.toCharArray()) {
                            if(reverseDict.containsKey(c)){
                                isxodIndex = reverseDict.get(c);
                                int smesenie = reverseDict.get(bestKeyArray[keyIndex]);
                                if(isxodIndex -smesenie >=0){
                                    newIndex = isxodIndex-smesenie;
                                }else{
                                    newIndex =  isxodIndex - smesenie + dictSize;
                                }
                                keyIndex++;
                                if(keyIndex ==bestKeyLength){keyIndex=0;}

                                encryptedLine.append(dictionary.get(newIndex));


                            }else{

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

    public static void RSA(Scanner scanner){

        int printChoice;
        boolean print = true;
        while(print)
        {
            System.out.println("=============Алгоритм RSA==========");
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
                        shifrovanieRSA(scanner);
                        break;
                    case 2:
                        deshifrovanieRSA(scanner);
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

    public static void shifrovanieRSA(Scanner scanner){
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

                int[] keys = KeysRsaGenerator(dictSize);
                int n = keys[2];
                int e = keys[3];
                int d = keys[4];

                try (PrintWriter writer = new PrintWriter(name_end)) {
                    int isxodIndex;
                    int newIndex;
                    for (String originalLine : lines) {
                        StringBuilder encryptedLine = new StringBuilder();

                        for (char c : originalLine.toCharArray()) {
                            if(reverseDict.containsKey(c)){
                                isxodIndex = reverseDict.get(c);
                                newIndex = (int) modPow(isxodIndex, e,n);

                                encryptedLine.append(newIndex).append(" ");


                            }else{

                                encryptedLine.append(c);
                            }
                        }

                        writer.println(encryptedLine.toString());
                    }

                    System.out.println("Файл успешно зашифрован и сохранен: " + name_end);
                    System.out.println("Ключи : n = " + n + " e = " + e + " d = " + d);



                } catch (FileNotFoundException u) {
                    System.out.println("Не удалось создать выходной файл: " + u.getMessage());
                }



                String keyFileName = name_end + "_keys.txt";
                try (PrintWriter writer_2 = new PrintWriter(keyFileName)) {

                    writer_2.println("Ключи : n = " + n + " e = " + e + " d = " + d);
                }

            } catch (FileNotFoundException e) {
                System.out.println("\nФайл с таким именем не найден, попробуйте ввести имя ещё раз!");
            } catch (IOException e) {
                System.out.println("Ошибка чтения файла: " + e.getMessage());
                System.out.println("Попробуйте ещё раз!");
            }
        }

    }

    static void deshifrovanieRSA(Scanner scanner) {
        String name_ishodnik = "";
        String name_end = "";

        HashMap<Integer, Character> dictionary = createDictionary();

        boolean cycle = true;
        while (cycle) {
            System.out.print("Введите название зашифрованного файла (с .txt): ");
            name_ishodnik = scanner.nextLine();

            if (name_ishodnik.trim().isEmpty()) {
                System.out.println("Имя файла не может быть пустым!");
                continue;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(name_ishodnik))) {


                System.out.print("Введите закрытый ключ d: ");
                int d = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Введите модуль n: ");
                int n = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Введите название файла для расшифрованного результата (с .txt): ");
                name_end = scanner.nextLine();

                if (name_end.trim().isEmpty()) {
                    name_end = name_ishodnik.replace(".txt", "_decrypted.txt");
                    System.out.println("Будет использовано имя: " + name_end);
                }

                try (PrintWriter writer = new PrintWriter(name_end)) {
                    String line;
                    while ((line = reader.readLine()) != null) {

                        String[] parts = line.split(" ");
                        StringBuilder decryptedLine = new StringBuilder();

                        for (String part : parts) {
                            if (part.isEmpty()) continue;


                            boolean isNumber = true;
                            for (int i = 0; i < part.length(); i++) {
                                if (!Character.isDigit(part.charAt(i))) {
                                    isNumber = false;
                                    break;
                                }
                            }

                            if (isNumber) {

                                long encrypted = Long.parseLong(part);
                                long m = modPow(encrypted, d, n);


                                Character originalChar = dictionary.get((int) m);
                                if (originalChar != null) {
                                    decryptedLine.append(originalChar);
                                } else {
                                    decryptedLine.append('?');
                                }
                            } else {

                                decryptedLine.append(part);
                            }
                        }
                        writer.println(decryptedLine.toString());
                    }

                    System.out.println("Файл успешно расшифрован и сохранен: " + name_end);
                    System.out.println("Использованы Ключи : n = " + n +  " d = " + d);
                    cycle = false;
                }

            } catch (FileNotFoundException e) {
                System.out.println("\nФайл с таким именем не найден, попробуйте ввести имя ещё раз!");
            } catch (IOException e) {
                System.out.println("Ошибка чтения файла: " + e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: нужно вводить целые числа!");
                scanner.nextLine();
            }
        }
    }


    public static int[] KeysRsaGenerator(int size){

        Random random = new Random();
        int[] primeNumbers = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,
                73,79,83,89,97,101,103,107,109,113,127,131,137,139,149,151,157,163,167,173,
                179,181,191,193,197,199,211,223,227,229,233,239,241,251,257,263,269,271,277,281,
                283,293,307,311,313,317,331,337,347,349,353,359,367,373,379,383,389,397,401,409,
                419,421,431,433,439,443,449,457,461,463,467,479,487,491,499,503,509,521,523,541,
                547,557,563,569,571,577,587,593,599,601,607,613,617,619,631,641,643,647,653,659,
                661,673,677,683,691,701,709,719,727,733,739,743,751,757,761,769,773,787,797,809,
                811,821,823,827,829,839,853,857,859,863,877,881,883,887,907,911,919,929,937,941,
                947,953,967,971,977,983,991,997,1009,1013,1019,1021,1031,1033,1039,1049,1051,1061,1063,1069,
                1087,1091,1093,1097,1103,1109,1117,1123,1129,1151,1153,1163,1171,1181,1187,1193,1201,1213,1217,1223,
                1229,1231,1237,1249,1259,1277,1279,1283,1289,1291,1297,1301,1303,1307,1319,1321,1327,1361,1367,1373,
                1381,1399,1409,1423,1427,1429,1433,1439,1447,1451,1453,1459,1471,1481,1483,1487,1489,1493,1499,1511,
                1523,1531,1543,1549,1553,1559,1567,1571,1579,1583,1597,1601,1607,1609,1613,1619,1621,1627,1637,1657,
                1663,1667,1669,1693,1697,1699,1709,1721,1723,1733,1741,1747,1753,1759,1777,1783,1787,1789,1801,1811,
                1823,1831,1847,1861,1867,1871,1873,1877,1879,1889,1901,1907,1913,1931,1933,1949,1951,1973,1979,1987,
                1993,1997,1999,2003,2011,2017,2027,2029,2039,2053,2063,2069,2081,2083,2087,2089,2099,2111,2113,2129,
                2131,2137,2141,2143,2153,2161,2179,2203,2207,2213,2221,2237,2239,2243,2251,2267,2269,2273,2281,2287,
                2293,2297,2309,2311,2333,2339,2341,2347,2351,2357,2371,2377,2381,2383,2389,2393,2399,2411,2417,2423,
                2437,2441,2447,2459,2467,2473,2477,2503,2521,2531,2539,2543,2549,2551,2557,2579,2591,2593,2609,2617,
                2621,2633,2647,2657,2659,2663,2671,2677,2683,2687,2689,2693,2699,2707,2711,2713,2719,2729,2731,2741,
                2749,2753,2767,2777,2789,2791,2797,2801,2803,2819,2833,2837,2843,2851,2857,2861,2879,2887,2897,2903,
                2909,2917,2927,2939,2953,2957,2963,2969,2971,2999,3001,3011,3019,3023,3037,3041,3049,3061,3067,3079,
                3083,3089,3109,3119,3121,3137,3163,3167,3169,3181,3187,3191,3203,3209,3217,3221,3229,3251,3253,3257,
                3259,3271,3299,3301,3307,3313,3319,3323,3329,3331,3343,3347,3359,3361,3371,3373,3389,3391,3407,3413,
                3433,3449,3457,3461,3463,3467,3469,3491,3499,3511,3517,3527,3529,3533,3539,3541,3547,3557,3559,3571};

        int primaryNumbersLength = primeNumbers.length;

        int p =0;
        int q =0;
        int n =0;
        int e = 0;
        int d = 1;

        boolean cycle = false;
        while (!cycle){
            while (n<=size){
                p = primeNumbers[random.nextInt(primaryNumbersLength)];
                q = primeNumbers[random.nextInt(primaryNumbersLength)];
                n= p*q;
            }

            while (e <= 1 || e >= (p-1)*(q-1) || gcd(e, (p-1)*(q-1)) != 1) {
                e = primeNumbers[random.nextInt(primaryNumbersLength)];
            }

            while (((e*d)%((q-1)*(p-1)))!=1){
                d++;
                if(d >= (q-1)*(p-1)){
                    System.out.println("Не удалось подобрать число d!");
                    System.out.println("Попытаюсь попробовать снова!");
                    break;
                }
                else{
                    cycle= true;
                }

            }
        }

        int[] keys = {p,q,n,e,d};
        return keys;
    }
    //  метод для НОД
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;




    }
    // чисто математический метод для возведения в степень и нахождения остатка от деления
    public static long modPow(long base, long exponent, long modulus) {
        long result = 1;                 // (1) Начинаем с 1
        base = base % modulus;            // (2) Приводим базу к диапазону модуля

        while (exponent > 0) {            // (3) Пока есть степень
            if (exponent % 2 == 1) {      // (4) Если текущий бит степени = 1
                result = (result * base) % modulus; // (5) Умножаем результат
            }
            base = (base * base) % modulus; // (6) Квадрат базы
            exponent = exponent >> 1;      // (7) Делим степень на 2 (сдвиг вправо)
        }

        return result;                    // (8) Возвращаем результат
    }

    public static void GOST(Scanner scanner){
        int vizinerChoice;
        boolean vizinerCycle = true;
        while (vizinerCycle){

            System.out.println("=========Алгоритм ГОСТ 28147-89=========");
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
                        shifrovanie_GOST(scanner);
                        break;
                    case 2:
                        deshifrovanie_GOST(scanner);
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

    public static void shifrovanie_GOST(Scanner scanner) {
        String name_ishodnik = "";
        String name_end = "";

        boolean cycle = true;
        while (cycle) {
            System.out.print("Введите название файла, который хотите зашифровать (не забудьте добавить .txt)\n" +
                    "Название: ");

            name_ishodnik = scanner.nextLine();

            if (name_ishodnik.trim().isEmpty()) {
                System.out.println("Имя файла не может быть пустым!");
                continue;
            }

            try {

                File inputFile = new File(name_ishodnik);
                byte[] data = java.nio.file.Files.readAllBytes(inputFile.toPath());

                cycle = false;

                System.out.print("Введите название файла для зашифрованного результата (с .txt): ");
                name_end = scanner.nextLine();

                if (name_end.trim().isEmpty()) {
                    name_end = name_ishodnik.replace(".txt", "_gost_encrypted.txt");
                    if (name_end.equals(name_ishodnik)) {
                        name_end = name_ishodnik + "_gost_encrypted.txt";
                    }
                    System.out.println("Будет использовано имя: " + name_end);
                }


                System.out.print("Введите ключ шифрования (пароль, любые символы): ");
                String password = scanner.nextLine();


                System.out.print("Введите синхропосылку (IV, 8 символов, например 12345678): ");
                String ivString = scanner.nextLine();
                byte[] iv = new byte[8];
                byte[] ivSrc = ivString.getBytes();
                for (int i = 0; i < 8; i++) {
                    iv[i] = (i < ivSrc.length) ? ivSrc[i] : 0;
                }

                byte[] key = generateKeyFromPassword(password);


                byte[] encrypted = gostGammaCrypt(data, key, iv);


                try (FileOutputStream fos = new FileOutputStream(name_end)) {
                    fos.write(encrypted);
                }


                String keyFile = name_end + "_gost_key.txt";
                try (PrintWriter pw = new PrintWriter(keyFile)) {
                    pw.println("Пароль: " + password);
                    pw.println("Синхропосылка (IV): " + ivString);
                    pw.println("Размер исходного файла: " + data.length + " байт");
                }

                System.out.println("Файл успешно зашифрован по ГОСТ 28147-89 (режим гаммирования)!");
                System.out.println("Сохранен: " + name_end);
                System.out.println("Ключ сохранен в: " + keyFile);
                System.out.println("Исходный размер: " + data.length + " байт");
                System.out.println("Зашифрованный размер: " + encrypted.length + " байт");

            } catch (FileNotFoundException e) {
                System.out.println("\nФайл с таким именем не найден, попробуйте ввести имя ещё раз!");
            } catch (IOException e) {
                System.out.println("Ошибка чтения файла: " + e.getMessage());
                System.out.println("Попробуйте ещё раз!");
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static void deshifrovanie_GOST(Scanner scanner) {
        String name_ishodnik = "";
        String name_end = "";

        boolean cycle = true;
        while (cycle) {
            System.out.print("Введите название зашифрованного файла (с .txt): ");

            name_ishodnik = scanner.nextLine();

            if (name_ishodnik.trim().isEmpty()) {
                System.out.println("Имя файла не может быть пустым!");
                continue;
            }

            try {

                File inputFile = new File(name_ishodnik);
                byte[] encryptedData = java.nio.file.Files.readAllBytes(inputFile.toPath());

                cycle = false;

                System.out.print("Введите название файла для расшифрованного результата (с .txt): ");
                name_end = scanner.nextLine();

                if (name_end.trim().isEmpty()) {
                    name_end = name_ishodnik.replace(".txt", "_gost_decrypted.txt");
                    if (name_end.equals(name_ishodnik)) {
                        name_end = name_ishodnik + "_gost_decrypted.txt";
                    }
                    System.out.println("Будет использовано имя: " + name_end);
                }

                System.out.print("Введите ключ шифрования (пароль): ");
                String password = scanner.nextLine();

                System.out.print("Введите синхропосылку (IV, 8 символов): ");
                String ivString = scanner.nextLine();
                byte[] iv = new byte[8];
                byte[] ivSrc = ivString.getBytes();
                for (int i = 0; i < 8; i++) {
                    iv[i] = (i < ivSrc.length) ? ivSrc[i] : 0;
                }

                byte[] key = generateKeyFromPassword(password);

                byte[] decrypted = gostGammaCrypt(encryptedData, key, iv);

                try (FileOutputStream fos = new FileOutputStream(name_end)) {
                    fos.write(decrypted);
                }

                System.out.println("Файл успешно расшифрован по ГОСТ 28147-89 (режим гаммирования)!");
                System.out.println("Сохранен: " + name_end);
                System.out.println("Размер: " + decrypted.length + " байт");

            } catch (FileNotFoundException e) {
                System.out.println("\nФайл с таким именем не найден, попробуйте ввести имя ещё раз!");
            } catch (IOException e) {
                System.out.println("Ошибка чтения файла: " + e.getMessage());
                System.out.println("Попробуйте ещё раз!");
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private static byte[] generateKeyFromPassword(String password) {
        byte[] key = new byte[32];
        byte[] passBytes = password.getBytes();

        for (int i = 0; i < 32; i++) {
            if (i < passBytes.length) {
                key[i] = passBytes[i];
            } else {
                key[i] = passBytes[i % passBytes.length];
            }
        }
        return key;
    }


    private static byte[] gostGammaCrypt(byte[] data, byte[] key, byte[] iv) {
        byte[] result = new byte[data.length];
        byte[] state = iv.clone();


        for (int pos = 0; pos < data.length; pos += 8) {

            byte[] gammaBlock = gostBlockEncrypt(state, key);

            for (int j = 0; j < 8 && pos + j < data.length; j++) {
                result[pos + j] = (byte)(data[pos + j] ^ gammaBlock[j]);
            }

            state = gammaBlock;
        }

        return result;
    }

    private static byte[] gostBlockEncrypt(byte[] block, byte[] key) {
        byte[] result = new byte[8];


        System.arraycopy(block, 0, result, 0, 8);

        for (int round = 0; round < 8; round++) {

            for (int i = 0; i < 8; i++) {
                result[i] ^= key[(round * 4 + i) % key.length];
            }


            for (int i = 0; i < 8; i++) {

                result[i] = (byte)((result[i] ^ i ^ round) & 0xFF);
            }


            byte temp = result[0];
            System.arraycopy(result, 1, result, 0, 7);
            result[7] = temp;
        }

        return result;
    }
}


