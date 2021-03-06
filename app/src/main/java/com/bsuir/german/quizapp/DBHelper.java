package com.bsuir.german.quizapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.bsuir.german.quizapp.dao.DAOQuestion;
import com.bsuir.german.quizapp.dao.DAORecord;
import com.bsuir.german.quizapp.entity.Question;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "quizDB";
    private static final int DB_VERSION = 1;


    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE QUESTION ("
                + "_id  INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "image_name TEXT,"
                + "question TEXT,"
                + "points INTEGER,"
                + "answer1 TEXT,"
                + "answer2 TEXT,"
                + "answer3 TEXT,"
                + "answer4 TEXT,"
                + "right_answer_id INTEGER);");

        db.execSQL("CREATE TABLE RECORD ("
                + "_id  INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "name TEXT,"
                + "score INTEGER,"
                + "date TEXT);");


        DAOQuestion daoQuestion = new DAOQuestion(db);
        DAORecord daoRecord = new DAORecord(db);

        daoQuestion.insertQuestion(new Question("first_level/by.png","Столица Беларуси?", 5, "Витебск", "Минск", "Москва", "Питер", 2));
        daoQuestion.insertQuestion(new Question("first_level/ua.png","Столица Украины?", 5, "Варшава", "Гомель", "Киев", "Пекин", 3));
        daoQuestion.insertQuestion(new Question("first_level/ru.png","Столица России?", 5, "Москва", "Сочи", "Омск", "Уфа", 1));
        daoQuestion.insertQuestion(new Question("first_level/pl.png","Столица Польши?", 5, "Пермь", "Варшава", "Новосибирск", "Барнаул", 2));
        daoQuestion.insertQuestion(new Question("first_level/us.png","Столица США?", 5, "Вашингтон", "Лондон", "Красноярск", "Орша", 1));
        daoQuestion.insertQuestion(new Question("first_level/cn.png","Столица Китая?", 5, "Киев", "Подгорица", "Дублин", "Пекин", 4));
        daoQuestion.insertQuestion(new Question("first_level/va.png","Столица Ватикана?", 5, "Ватикан", "Амстердам", "Вадуц", "Вена", 1));
        daoQuestion.insertQuestion(new Question("first_level/pt.png","Столица Португалии?", 5, "Баку", "Хельсинки", "Лиссабон", "Берн", 3));
        daoQuestion.insertQuestion(new Question("first_level/fr.png","Столица Франции?", 5, "Брест", "Париж", "Тулуза", "Тулон", 2));
        daoQuestion.insertQuestion(new Question("first_level/bg.png","Столица Болгарии?", 5, "Варна", "Полвдив", "Балчик", "София", 4));
        daoQuestion.insertQuestion(new Question("first_level/de.png","Столица Германии?", 5, "Берлин", "Гамбург", "Кельн", "Мюнхен", 1));
        daoQuestion.insertQuestion(new Question("first_level/es.png","Столица Испании?", 5, "Мадрид", "Барселона", "Лиссабон", "Гранада", 1));
        daoQuestion.insertQuestion(new Question("first_level/lv.png","Столица Латвии?", 5, "Литва", "Юрмала", "Вильнюс", "Рига", 4));
        daoQuestion.insertQuestion(new Question("first_level/lt.png","Столица Литвы?", 5, "Рига", "Вильнюс", "Каунас", "Юрмала", 2));

        daoQuestion.insertQuestion(new Question("second_level/tr.webp","Столица Турции?", 10, "Дакка", "Анкара", "Бишек", "Бейрут", 2));
        daoQuestion.insertQuestion(new Question("second_level/in.webp","Столица Индии?", 10, "Мале", "Ереван", "Дели", "Доха", 3));
        daoQuestion.insertQuestion(new Question("second_level/am.webp","Столица Армении?", 10, "Маскат", "Ереван", "Никосия", "Нейльидо", 2));
        daoQuestion.insertQuestion(new Question("second_level/tj.webp","Столица Таджикистана?", 10, "Душанбе", "Бангкок", "Дакка", "Сана", 1));
        daoQuestion.insertQuestion(new Question("second_level/kr.webp","Столица Кореи?", 10, "Пекин", "Манила", "Токио", "Сеул", 4));
        daoQuestion.insertQuestion(new Question("second_level/uz.webp","Столица Узбекистана?", 10, "Эр-Рияд", "Цхинвал", "Ташкент", "Эль-Кувейт", 3));
        daoQuestion.insertQuestion(new Question("second_level/au.webp","Столица Австралии?", 10, "Кинбера", "Сукре", "Паннама", "Сан-Хосе", 1));
        daoQuestion.insertQuestion(new Question("second_level/nr.webp","Столица Науры?", 10, "Не имеет столицы", "Эр-Рияд", "Улан-Батор", "Ханой", 1));
        daoQuestion.insertQuestion(new Question("second_level/to.webp","Столица Тонга?", 10, "Муа", "Ваини", "Хавелолото", "Нукуалофа", 4));
        daoQuestion.insertQuestion(new Question("second_level/fj.webp","Столица Фиджи?", 10, "Левука", "Сува", "Нанди", "Ламбаса", 2));
        daoQuestion.insertQuestion(new Question("second_level/tv.webp","Столица Тувалу?", 10, "Ваиаку", "Алапи", "Фенафути", "Кулиа", 3));
        daoQuestion.insertQuestion(new Question("second_level/fm.webp","Столица Микронезия?", 10, "Колоина", "Паликир", "Тофол", "Румунг", 2));
        daoQuestion.insertQuestion(new Question("second_level/pw.webp","Столица Палау?", 10, "Нгерулмуд", "Корор", "Мейнгс", "Клоуклабед", 1));
        daoQuestion.insertQuestion(new Question("second_level/ws.webp","Столица Самоа?", 10, "Афега", "Паго-Паго", "Мклифануа", "Апиа", 4));
        daoQuestion.insertQuestion(new Question("second_level/nz.webp","Столица Новой Зеландии?", 10, "Окленд", "Роторуа", "Веллингтон", "Нельсон", 3));

        daoQuestion.insertQuestion(new Question("third_level/ca.webp","Столица Канады?", 15, "Оттава", "Торонто", "Монреаль", "Эдмонтон", 2));
        daoQuestion.insertQuestion(new Question("third_level/bs.webp","Столица Багамских островов?", 15, "Фрипорт", "Элис Таун", "Нассау", "Данмор-Таун", 3));
        daoQuestion.insertQuestion(new Question("third_level/sd.webp","Столица Судана?", 15, "Омдурман", "Кассала", "Ньяла", "Хартум", 4 ));
        daoQuestion.insertQuestion(new Question("third_level/py.webp","Столица Парагвай?", 15, "Энкарнасьон", "Асунсьон", "Консепсьон", "Коронель-Овьедо", 2));
        daoQuestion.insertQuestion(new Question("third_level/eg.webp","Столица Египта?", 15, "Каир", "Хургада", "Луксор", "Гиза", 1));
        daoQuestion.insertQuestion(new Question("third_level/fi.webp","Столица Финляндии?", 15, "Турку", "Тампере", "Порво", "Хельсинки", 4));
        daoQuestion.insertQuestion(new Question("third_level/no.webp","Столица Норвегии?", 15, "Берген", "Осло", "Тренхейм", "Тромсё", 2));
        daoQuestion.insertQuestion(new Question("third_level/ne.webp","Столица Нигера?", 15, "Ниамей", "Зиндер", "Агадес", "Тахуа", 1));
        daoQuestion.insertQuestion(new Question("third_level/cm.webp","Столица Камерун?", 15, "Дуала", "Гаруа", "Яунде", "Баменда", 3));
        daoQuestion.insertQuestion(new Question("third_level/cu.webp","Столица Кубы?", 15, "Варадеро", "Ольгин", "Тринидад", "Гаванна", 4));
        daoQuestion.insertQuestion(new Question("third_level/pe.webp","Столица Перу?", 15, "Арекипа", "Лима", "Куско", "Трухильо", 2));
        daoQuestion.insertQuestion(new Question("third_level/rs.webp","Столица Сербии?", 15, "Белград", "Нови-Сад", "Суботица", "Крагуевац", 1));
        daoQuestion.insertQuestion(new Question("third_level/li.webp","Столица Лихтенштейна?", 15, "Шан", "Тризенберг", "Эшен", "Вадуц", 4));
        daoQuestion.insertQuestion(new Question("third_level/al.webp","Столица Албании?", 15, "Дуресс", "Саранда", "Тирана", "Берат", 3));




        daoRecord.insertRecord(db, "Lesha", 10000, "2020/04/03 20:20");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
