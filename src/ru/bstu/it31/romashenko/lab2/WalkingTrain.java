package ru.bstu.it31.romashenko.lab2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.String;
import java.util.Scanner;
import org.apache.logging.log4j.*;

/**
 * <p>Класс для обработки данных пробежки</p>
 *
 * @author Ромащенко Н.А.
 * @version 1.0
 * Дата: 21.02.19
 *
 * Класс: WalkingTrain
 * Описание: для обработки данных, связанных с пробежкой
 */
public class WalkingTrain {
    /** */
    static final Logger Logger = LogManager.getLogger(WalkingTrain.class);

    /**
     * Переменная для текущей дистанции
     */
    private double todayDistance;
    /**
     * Переменная для дневной нормы
     */
    private double dailyNorm;
    /**
     * Всего дней
     */
    private int days;
    /**
     * Максимальная дистанция
     */
    private double maxDistance;

    /**
     * Получение дневной нормы
     *
     * @return возврат дневной нормы
     */
    public double getDailyNorm() {
        return dailyNorm;
    }

    /**
     * Получение всего дней
     *
     * @return возврат всех дней
     */
    public double getDays() {
        return days;
    }

    /**
     * Получение первой дистанции
     *
     * @return возврат первой дистанции
     */
    public double getFirstDistance() {
        return todayDistance;
    }

    /**
     * Установка дневной нормы
     *
     * @param dailyNorm дневная норма
     */
    public void setDailyNorm(double dailyNorm) {
        this.dailyNorm = dailyNorm;
    }

    /**
     * Установка дней
     *
     * @param days дни
     */
    public void setDays(int days) {
        this.days = days;
    }

    /**
     * Установка первой дистанции
     *
     * @param firstDistance первая дистанция
     */
    public void setFirstDistance(double firstDistance) {
        this.todayDistance = firstDistance;
    }

    /**
     * проверко корректности данных
     *
     * @return возврат результата метода
     */
    private boolean check() {
        if (0 >= this.todayDistance || 0 >= this.dailyNorm || 0 >= days) {
            Logger.error("Некорректные данные при вводе.");
            return false;
        }
        Logger.info("С данными все ок.");
        return true;
    }

    /**
     * Получение макс.дистанции
     *
     * @return возвращает максимальное значение дистанции
     */
    public double getMaxDistance() {
        Logger.debug("Начало вычисления макс. дистанции");

        if (!check()) {
            return -1;
        }
        this.maxDistance = this.todayDistance;

        System.out.println("Считать через:");
        System.out.println("\t> 1. For");
        System.out.println("\t> 2. While");

        Scanner scanner = new Scanner(System.in);

        int mode = scanner.nextInt();

        switch (mode) {
            case 1: {
                for (int day = 2; day <= this.days; ++day) {
                    this.todayDistance += this.todayDistance * this.dailyNorm / 100;
                    this.maxDistance += this.todayDistance;
                }
                break;
            }

            case 2: {
                int day = 2;
                while (day <= this.days) {
                    this.todayDistance += this.todayDistance * this.dailyNorm / 100;
                    this.maxDistance += this.todayDistance;
                    ++day;
                }
                break;
            }

            default: {
                System.out.println("Некорректный ввод.");
                return -1;
            }
        }
        Logger.debug("Конец вычисления макс. дистанции");

        return this.maxDistance;
    }

    /**
     * Считываение данных из файла
     */
    public void getValueFromFile() {
        Logger.debug("Начало чтения из файла.");

        try (FileReader reader = new FileReader("ex3.txt")) {
            BufferedReader r = new BufferedReader(reader);

            String temp = r.readLine();
            this.todayDistance = Double.parseDouble(temp);

            temp = r.readLine();
            this.dailyNorm = Double.parseDouble(temp);

            temp = r.readLine();
            this.days = Integer.parseInt(temp);

            Logger.debug("Конец чтения из файла.");
        } catch (IOException ex) {
            Logger.error("Файл не найден. ", ex);

            System.out.println(ex.getMessage());
        }
    }

    /**
     * запись данных в файл
     */
    public void printValueInFile() {
        Logger.debug("Начало записи в файл.");

        try (FileWriter writer = new FileWriter("ex3_otvet.txt", false)) {
            String text = ""
                    + "firstDistance = " + this.todayDistance + " км" + '\n'
                    + "dailyNorm = " + this.dailyNorm + " %" + '\n'
                    + "days = " + this.days + '\n'
                    + "maxDistance = " + this.maxDistance + " км" + '\n';

            writer.write(text);

            Logger.debug("В файл записали.");
        } catch (IOException ex) {
            Logger.error("В файл не записали. ", ex);

            System.out.println(ex.getMessage());
        }
    }
}