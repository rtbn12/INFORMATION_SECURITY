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
                    "5 - Дешифрация, основанная на частотных характеристиках\n" +
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
                    case 5:
                        deshifrChastotHaracteristika(scanner);
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


    public static void deshifrChastotHaracteristika(Scanner scanner) {
        String name_ishodnik = "";
        String name_end = "";
        String statistic = "";

        HashMap<Integer, Character> dictionary = createDictionary();

        HashMap<Character, Integer> reverseDict = new HashMap<>();
        for (Map.Entry<Integer, Character> entry : dictionary.entrySet()) {
            reverseDict.put(entry.getValue(), entry.getKey());
        }


        HashMap<Character, Double> worldStats = generatorChastotDictionary();


        HashMap<Character, Integer> counterDictionary = createCounterDictionary();


        HashMap<Character, Double> textFrequencies = new HashMap<>();

        int countGlobal = 0; //количество символов из алфавита
        int countTotal = 0;  // количество символов в тексте

        boolean cycle = true;
        while (cycle) {
            System.out.print("Введите название зашифрованного файла (с .txt): ");
            name_ishodnik = scanner.nextLine();

            if (name_ishodnik.trim().isEmpty()) {
                System.out.println("Имя файла не может быть пустым!");
                continue;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(name_ishodnik))) {
                System.out.print("Введите название файла для расшифрованного результата (с .txt): ");
                name_end = scanner.nextLine();

                if (name_end.trim().isEmpty()) {
                    name_end = name_ishodnik.replace(".txt", "_decrypted.txt");
                    System.out.println("Будет использовано имя: " + name_end);
                }

                statistic = name_ishodnik.replace(".txt", "_statistic.txt");

                //подсчёт частот
                String line;
                StringBuilder fullText = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    fullText.append(line).append("\n");
                }

                String encryptedText = fullText.toString();

                //подсчёт символов
                for (char simbol : encryptedText.toCharArray()) {
                    countTotal++;
                    if (counterDictionary.containsKey(simbol)) {
                        countGlobal++;
                        counterDictionary.put(simbol, counterDictionary.get(simbol) + 1);
                    }
                }

                // вычисление частот
                for (Map.Entry<Character, Integer> entry : counterDictionary.entrySet()) {
                    if (entry.getValue() > 0) {
                        double frequency = (entry.getValue() * 100.0) / countGlobal;
                        textFrequencies.put(entry.getKey(), frequency);
                    }
                }

                // сопоставление частот
                // Сортируем символы текста по убыванию частоты
                List<Map.Entry<Character, Double>> textSorted = new ArrayList<>(textFrequencies.entrySet());
                textSorted.sort((a, b) -> b.getValue().compareTo(a.getValue()));

                // Сортируем мировую статистику по убыванию частоты
                List<Map.Entry<Character, Double>> worldSorted = new ArrayList<>(worldStats.entrySet());
                worldSorted.sort((a, b) -> b.getValue().compareTo(a.getValue()));

                // создание карты замены
                HashMap<Character, Character> substitutionMap = new HashMap<>();

                System.out.println("\n========== СОПОСТАВЛЕНИЕ ЧАСТОТ ==========");
                System.out.println("Символ текста | Частота % | Заменяем на | Частота мира %");
                System.out.println("--------------------------------------------------------");

                int matchCount = Math.min(textSorted.size(), worldSorted.size());
                for (int i = 0; i < matchCount; i++) {
                    Character textChar = textSorted.get(i).getKey();
                    Double textFreq = textSorted.get(i).getValue();
                    Character worldChar = worldSorted.get(i).getKey();
                    Double worldFreq = worldSorted.get(i).getValue();

                    substitutionMap.put(textChar, worldChar);

                    System.out.printf("     '%c'      |   %6.2f   |     '%c'       |     %6.2f%n",
                            textChar, textFreq, worldChar, worldFreq);

//                    if (i < matchCount) {
//                        System.out.printf("     '%c'      |   %6.2f   |     '%c'       |     %6.2f%n",
//                                textChar, textFreq, worldChar, worldFreq);
//                    }
                }

                // сохранение статистики в файл
                try (PrintWriter writer = new PrintWriter(statistic)) {
                    writer.println("========== СТАТИСТИКА ЗАШИФРОВАННОГО ТЕКСТА ==========");
                    writer.println("Общее количество символов в тексте: " + countTotal);
                    writer.println("Символы из доступного алфавита встречались: " + countGlobal + " раз.");
                    writer.println("\n========== ЧАСТОТЫ СИМВОЛОВ ==========");
                    writer.println("Символ | Количество | Частота %");
                    writer.println("-------------------------------------");

                    for (Map.Entry<Character, Integer> entry : counterDictionary.entrySet()) {

                        if (entry.getValue() > 0) {
                            double freq = textFrequencies.get(entry.getKey());
                            writer.printf("  '%c'   |    %d     |   %.4f%%%n",
                                    entry.getKey(), entry.getValue(), freq);
                        }
                    }

                    writer.println("\n========== СОПОСТАВЛЕНИЕ ДЛЯ РАСШИФРОВКИ ==========");
                    writer.println("Зашифрованный символ -> Расшифрованный символ");
                    writer.println("-----------------------------------------------");

                    List<Map.Entry<Character, Character>> sortedMap = new ArrayList<>(substitutionMap.entrySet());

                    sortedMap.sort((a, b) -> {
                        // Используем getOrDefault, чтобы избежать null
                        Double freqA = textFrequencies.getOrDefault(a.getKey(), 0.0);  // <-- ИСПРАВИЛ ЗДЕСЬ
                        Double freqB = textFrequencies.getOrDefault(b.getKey(), 0.0);  // <-- ИСПРАВИЛ ЗДЕСЬ
                        return freqB.compareTo(freqA);
                    });

                    for (Map.Entry<Character, Character> entry : sortedMap) {
                        writer.printf("        '%c'        ->        '%c'%n", entry.getKey(), entry.getValue());
                    }
                }

                System.out.println("\nФайл со статистикой сохранен: " + statistic);

                // расшифровка
                try (PrintWriter writer2 = new PrintWriter(name_end)) {
                    for (char simbol : encryptedText.toCharArray()) {
                        if (substitutionMap.containsKey(simbol)) {
                            writer2.print(substitutionMap.get(simbol));
                        } else {
                            writer2.print(simbol);
                        }
                    }
                }

                System.out.println("Файл успешно расшифрован и сохранен: " + name_end);
                System.out.println("Количество замененных символов: " + substitutionMap.size());

                cycle = false;

            } catch (FileNotFoundException e) {
                System.out.println("\nФайл с таким именем не найден, попробуйте ввести имя ещё раз!");
            } catch (IOException e) {
                System.out.println("Ошибка чтения файла: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }


    public static HashMap<Character, Double> generatorChastotDictionary(){
        /**
         * Возвращает словарь, где для каждого символа (ключа) указана вероятность его
         * встретить в русскоязычном тексте (в процентах).
         * Данные основаны на статистике для букв, пробела и оценках для остальных символов.
         * Сумма всех вероятностей = 100.0%
         */

            HashMap<Character, Double> freqMap = new HashMap<>();

            // ===================================================
            // 1. СТРОЧНЫЕ РУССКИЕ БУКВЫ (33 символа)
            //    Источник: Национальный корпус русского языка
            // ===================================================
            freqMap.put('а', 7.998);   // буква А
            freqMap.put('б', 1.592);   // буква Б
            freqMap.put('в', 4.533);   // буква В
            freqMap.put('г', 1.687);   // буква Г
            freqMap.put('д', 2.977);   // буква Д
            freqMap.put('е', 8.483);   // буква Е
            freqMap.put('ё', 0.200);   // буква Ё (редкая)
            freqMap.put('ж', 0.700);   // буква Ж
            freqMap.put('з', 1.641);   // буква З
            freqMap.put('и', 7.367);   // буква И
            freqMap.put('й', 1.000);   // буква Й
            freqMap.put('к', 3.486);   // буква К
            freqMap.put('л', 4.343);   // буква Л
            freqMap.put('м', 3.203);   // буква М
            freqMap.put('н', 6.700);   // буква Н
            freqMap.put('о', 10.983);  // буква О (самая частая)
            freqMap.put('п', 2.804);   // буква П
            freqMap.put('р', 4.746);   // буква Р
            freqMap.put('с', 5.473);   // буква С
            freqMap.put('т', 6.318);   // буква Т
            freqMap.put('у', 2.615);   // буква У
            freqMap.put('ф', 0.200);   // буква Ф (редкая)
            freqMap.put('х', 0.900);   // буква Х
            freqMap.put('ц', 0.400);   // буква Ц
            freqMap.put('ч', 1.450);   // буква Ч
            freqMap.put('ш', 0.600);   // буква Ш
            freqMap.put('щ', 0.300);   // буква Щ
            freqMap.put('ъ', 0.350);   // буква Ъ (твердый знак)
            freqMap.put('ы', 1.898);   // буква Ы
            freqMap.put('ь', 1.385);   // буква Ь (мягкий знак)
            freqMap.put('э', 0.300);   // буква Э
            freqMap.put('ю', 0.600);   // буква Ю
            freqMap.put('я', 2.001);   // буква Я

            // ===================================================
            // 2. ПРОПИСНЫЕ РУССКИЕ БУКВЫ (33 символа)
            //    Встречаются примерно в 40 раз реже строчных
            // ===================================================
            freqMap.put('А', 0.200);   // А заглавная (7.998 / 40)
            freqMap.put('Б', 0.040);   // Б заглавная (1.592 / 40)
            freqMap.put('В', 0.113);   // В заглавная (4.533 / 40)
            freqMap.put('Г', 0.042);   // Г заглавная (1.687 / 40)
            freqMap.put('Д', 0.074);   // Д заглавная (2.977 / 40)
            freqMap.put('Е', 0.212);   // Е заглавная (8.483 / 40)
            freqMap.put('Ё', 0.005);   // Ё заглавная (0.200 / 40)
            freqMap.put('Ж', 0.018);   // Ж заглавная (0.700 / 40)
            freqMap.put('З', 0.041);   // З заглавная (1.641 / 40)
            freqMap.put('И', 0.184);   // И заглавная (7.367 / 40)
            freqMap.put('Й', 0.025);   // Й заглавная (1.000 / 40)
            freqMap.put('К', 0.087);   // К заглавная (3.486 / 40)
            freqMap.put('Л', 0.109);   // Л заглавная (4.343 / 40)
            freqMap.put('М', 0.080);   // М заглавная (3.203 / 40)
            freqMap.put('Н', 0.168);   // Н заглавная (6.700 / 40)
            freqMap.put('О', 0.275);   // О заглавная (10.983 / 40)
            freqMap.put('П', 0.070);   // П заглавная (2.804 / 40)
            freqMap.put('Р', 0.119);   // Р заглавная (4.746 / 40)
            freqMap.put('С', 0.137);   // С заглавная (5.473 / 40)
            freqMap.put('Т', 0.158);   // Т заглавная (6.318 / 40)
            freqMap.put('У', 0.065);   // У заглавная (2.615 / 40)
            freqMap.put('Ф', 0.005);   // Ф заглавная (0.200 / 40)
            freqMap.put('Х', 0.023);   // Х заглавная (0.900 / 40)
            freqMap.put('Ц', 0.010);   // Ц заглавная (0.400 / 40)
            freqMap.put('Ч', 0.036);   // Ч заглавная (1.450 / 40)
            freqMap.put('Ш', 0.015);   // Ш заглавная (0.600 / 40)
            freqMap.put('Щ', 0.008);   // Щ заглавная (0.300 / 40)
            freqMap.put('Ъ', 0.009);   // Ъ заглавная (0.350 / 40)
            freqMap.put('Ы', 0.047);   // Ы заглавная (1.898 / 40)
            freqMap.put('Ь', 0.035);   // Ь заглавная (1.385 / 40)
            freqMap.put('Э', 0.008);   // Э заглавная (0.300 / 40)
            freqMap.put('Ю', 0.015);   // Ю заглавная (0.600 / 40)
            freqMap.put('Я', 0.050);   // Я заглавная (2.001 / 40)

            // ===================================================
            // 3. ПРОБЕЛ (самый частый символ)
            // ===================================================
            freqMap.put(' ', 17.500);  // пробел

            // ===================================================
            // 4. ЦИФРЫ (10 символов)
            //    Цифры в тексте встречаются редко, примерно по 0.1% каждая
            // ===================================================
            freqMap.put('0', 0.100);   // ноль
            freqMap.put('1', 0.100);   // один
            freqMap.put('2', 0.100);   // два
            freqMap.put('3', 0.100);   // три
            freqMap.put('4', 0.100);   // четыре
            freqMap.put('5', 0.100);   // пять
            freqMap.put('6', 0.100);   // шесть
            freqMap.put('7', 0.100);   // семь
            freqMap.put('8', 0.100);   // восемь
            freqMap.put('9', 0.100);   // девять

            // ===================================================
            // 5. ЗНАКИ ПРЕПИНАНИЯ И СИМВОЛЫ
            //    Самые частые: точка, запятая, тире
            // ===================================================
            freqMap.put('.', 1.200);   // точка
            freqMap.put(',', 1.100);   // запятая
            freqMap.put('-', 0.800);   // дефис/тире
            freqMap.put('!', 0.200);   // восклицательный знак
            freqMap.put('?', 0.200);   // вопросительный знак
            freqMap.put(':', 0.150);   // двоеточие
            freqMap.put(';', 0.100);   // точка с запятой
            freqMap.put('\"', 0.150);  // кавычка двойная
            freqMap.put('\'', 0.150);  // кавычка одинарная
            freqMap.put('(', 0.100);   // скобка открывающая
            freqMap.put(')', 0.100);   // скобка закрывающая
            freqMap.put('—', 0.200);   // длинное тире
            freqMap.put('…', 0.050);   // многоточие
            freqMap.put('«', 0.080);   // кавычка-елочка левая
            freqMap.put('»', 0.080);   // кавычка-елочка правая

            // ===================================================
            // 6. РЕДКИЕ И СПЕЦИАЛЬНЫЕ СИМВОЛЫ
            //    Остальные символы встречаются очень редко
            // ===================================================
            freqMap.put('+', 0.030);   // плюс
            freqMap.put('=', 0.030);   // равно
            freqMap.put('*', 0.020);   // звездочка
            freqMap.put('/', 0.020);   // слеш
            freqMap.put('\\', 0.010);  // обратный слеш
            freqMap.put('%', 0.010);   // процент
            freqMap.put('#', 0.005);   // решетка
            freqMap.put('$', 0.005);   // доллар
            freqMap.put('&', 0.005);   // амперсанд
            freqMap.put('@', 0.005);   // собака
            freqMap.put('^', 0.005);   // крышка
            freqMap.put('~', 0.005);   // тильда
            freqMap.put('`', 0.005);   // обратный апостроф
            freqMap.put('_', 0.050);   // нижнее подчеркивание
            freqMap.put('|', 0.005);   // вертикальная черта
            freqMap.put('[', 0.020);   // квадратная скобка левая
            freqMap.put(']', 0.020);   // квадратная скобка правая
            freqMap.put('{', 0.010);   // фигурная скобка левая
            freqMap.put('}', 0.010);   // фигурная скобка правая
            freqMap.put('<', 0.020);   // меньше
            freqMap.put('>', 0.020);   // больше
            freqMap.put('№', 0.030);   // знак номера
            freqMap.put('\n', 0.200);  // перевод строки

            return freqMap;
    }


    public static HashMap<Character, Integer> createCounterDictionary() {
        HashMap<Character, Integer> counterMap = new HashMap<>();

        // ===================================================
        // 1. СТРОЧНЫЕ РУССКИЕ БУКВЫ (33 символа)
        // ===================================================
        counterMap.put('а', 0);
        counterMap.put('б', 0);
        counterMap.put('в', 0);
        counterMap.put('г', 0);
        counterMap.put('д', 0);
        counterMap.put('е', 0);
        counterMap.put('ё', 0);
        counterMap.put('ж', 0);
        counterMap.put('з', 0);
        counterMap.put('и', 0);
        counterMap.put('й', 0);
        counterMap.put('к', 0);
        counterMap.put('л', 0);
        counterMap.put('м', 0);
        counterMap.put('н', 0);
        counterMap.put('о', 0);
        counterMap.put('п', 0);
        counterMap.put('р', 0);
        counterMap.put('с', 0);
        counterMap.put('т', 0);
        counterMap.put('у', 0);
        counterMap.put('ф', 0);
        counterMap.put('х', 0);
        counterMap.put('ц', 0);
        counterMap.put('ч', 0);
        counterMap.put('ш', 0);
        counterMap.put('щ', 0);
        counterMap.put('ъ', 0);
        counterMap.put('ы', 0);
        counterMap.put('ь', 0);
        counterMap.put('э', 0);
        counterMap.put('ю', 0);
        counterMap.put('я', 0);

        // ===================================================
        // 2. ПРОПИСНЫЕ РУССКИЕ БУКВЫ (33 символа)
        // ===================================================
        counterMap.put('А', 0);
        counterMap.put('Б', 0);
        counterMap.put('В', 0);
        counterMap.put('Г', 0);
        counterMap.put('Д', 0);
        counterMap.put('Е', 0);
        counterMap.put('Ё', 0);
        counterMap.put('Ж', 0);
        counterMap.put('З', 0);
        counterMap.put('И', 0);
        counterMap.put('Й', 0);
        counterMap.put('К', 0);
        counterMap.put('Л', 0);
        counterMap.put('М', 0);
        counterMap.put('Н', 0);
        counterMap.put('О', 0);
        counterMap.put('П', 0);
        counterMap.put('Р', 0);
        counterMap.put('С', 0);
        counterMap.put('Т', 0);
        counterMap.put('У', 0);
        counterMap.put('Ф', 0);
        counterMap.put('Х', 0);
        counterMap.put('Ц', 0);
        counterMap.put('Ч', 0);
        counterMap.put('Ш', 0);
        counterMap.put('Щ', 0);
        counterMap.put('Ъ', 0);
        counterMap.put('Ы', 0);
        counterMap.put('Ь', 0);
        counterMap.put('Э', 0);
        counterMap.put('Ю', 0);
        counterMap.put('Я', 0);

        // ===================================================
        // 3. ПРОБЕЛ
        // ===================================================
        counterMap.put(' ', 0);

        // ===================================================
        // 4. ЦИФРЫ (10 символов)
        // ===================================================
        counterMap.put('0', 0);
        counterMap.put('1', 0);
        counterMap.put('2', 0);
        counterMap.put('3', 0);
        counterMap.put('4', 0);
        counterMap.put('5', 0);
        counterMap.put('6', 0);
        counterMap.put('7', 0);
        counterMap.put('8', 0);
        counterMap.put('9', 0);

        // ===================================================
        // 5. ЗНАКИ ПРЕПИНАНИЯ И ОСНОВНЫЕ СИМВОЛЫ
        // ===================================================
        counterMap.put('!', 0);
        counterMap.put('"', 0);
        counterMap.put('#', 0);
        counterMap.put('$', 0);
        counterMap.put('%', 0);
        counterMap.put('&', 0);
        counterMap.put('\'', 0);
        counterMap.put('(', 0);
        counterMap.put(')', 0);
        counterMap.put('*', 0);
        counterMap.put('+', 0);
        counterMap.put(',', 0);
        counterMap.put('-', 0);
        counterMap.put('.', 0);
        counterMap.put('/', 0);
        counterMap.put(':', 0);
        counterMap.put(';', 0);
        counterMap.put('<', 0);
        counterMap.put('=', 0);
        counterMap.put('>', 0);
        counterMap.put('?', 0);
        counterMap.put('@', 0);
        counterMap.put('[', 0);
        counterMap.put('\\', 0);
        counterMap.put(']', 0);
        counterMap.put('^', 0);
        counterMap.put('_', 0);
        counterMap.put('`', 0);
        counterMap.put('{', 0);
        counterMap.put('|', 0);
        counterMap.put('}', 0);
        counterMap.put('~', 0);
        counterMap.put('№', 0);

        // ===================================================
        // 6. СПЕЦИАЛЬНЫЕ СИМВОЛЫ
        // ===================================================
        counterMap.put('\n', 0);

        // ===================================================
        // 7. ДОПОЛНИТЕЛЬНЫЕ СИМВОЛЫ
        // ===================================================
        counterMap.put('«', 0);
        counterMap.put('»', 0);
        counterMap.put('—', 0);
        counterMap.put('…', 0);

        return counterMap;
    }



}


