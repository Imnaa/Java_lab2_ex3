package ru.bstu.it31.romashenko.lab2;

import java.util.Scanner;
import org.apache.logging.log4j.*;

/** @author Ромащенко Н.А.
 *
 * @version 1. 21.02.19
 *
 * Имя класса: Main
 *
 * Назначение: Начав тренировки, спортсмен в первый день пробежал n км.  Каждый день он увеличивал дневную норму на m% нормы предыдущего дня. Какой суммарный путь пробежит спортсмен за k дней?
 */

public class Main {
    static final Logger Logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        System.out.println("Определение макс. дистанции");
        System.out.println("\t> 1. Ввести данные с клавиатуры;");
        System.out.println("\t> 2. Считать из файла данные;");
        System.out.println("\t> 9. Выход.");
        // Режим ввода данных
        // 1 - клавиатура
        // 2 - файл
        // 9 - выход

        // Инициализация объекта "Сканер"
        Scanner scanner = new Scanner(System.in);
        int mode = scanner.nextInt();
        // Инициализация объекта "WalkingTrain"
        WalkingTrain walkingTrain = new WalkingTrain();
        // Обработка режима работы
        switch (mode) {
            // Клавиатура
            case 1: {
                Logger.info("Пользователь выбрал режим работы 'чтение с консоли'");
                // Ввод сторон треугольника
                System.out.print("Первый день пробег км: ");
                walkingTrain.setFirstDistance(scanner.nextDouble());
                Logger.info("Пользователь ввел = " + walkingTrain.getFirstDistance());
                System.out.print("Дневная норма %: ");
                walkingTrain.setDailyNorm(scanner.nextDouble());
                Logger.info("Пользователь ввел = " + walkingTrain.getDailyNorm());
                System.out.print("Дней на побегать: ");
                walkingTrain.setDays(scanner.nextInt());
                Logger.info("Пользователь ввел = " + walkingTrain.getDays());
                scanner.close();
                break;
            }
            // Файл
            case 2: {
                Logger.info("Пользователь выбрал режим работы 'чтение из файла'");
                // Функция для считывания из файла
                walkingTrain.getValueFromFile();
                break;
            }
            // Выход
            case 9: {
                Logger.info("Пользователь выбрал режим работы 'выход'");
                return;
            }
            // Ошибка ввода
            default: {
                Logger.warn("Выбран не верный параметр, программа завершила свою работу.");
                System.out.println("Неправильный ввод.");
                return;
            }
        }
        // Получаем макс. дистаницю
        double maxDistance = walkingTrain.getMaxDistance();
        // Проверка на посчитать
        if (-1 == maxDistance) {
            Logger.error("Дистанция не была получена.");
            // Обработка ошибки
            return;
        }
        Logger.info("Получили значение дистанции: " + maxDistance);
        System.out.println("Спортсмен пробежал " + maxDistance + " км.");
        walkingTrain.printValueInFile();
        Logger.info("Программа без ошибок завершилась.");
    }
}
