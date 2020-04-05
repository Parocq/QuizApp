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

        daoQuestion.insertQuestion(new Question("Столица Беларуси?", 5, "Витебск", "Минск", "Москва", "Питер", 2));
        daoQuestion.insertQuestion(new Question("Столица Украины?", 5, "Варшава", "Гомель", "Киев", "Пекин", 3));
        daoQuestion.insertQuestion(new Question("Столица России?", 5, "Москва", "Сочи", "Омск", "Уфа", 1));
        daoQuestion.insertQuestion(new Question("Столица Польши?", 5, "Пермь", "Варшава", "Новосибирск", "Барнаул", 2));
        daoQuestion.insertQuestion(new Question("Столица США?", 5, "Вашингтон", "Лондон", "Красноярск", "Орша", 1));
        daoQuestion.insertQuestion(new Question("Столица Китая?", 5, "Киев", "Подгорица", "Дублин", "Пекин", 4));
        daoQuestion.insertQuestion(new Question("Столица Ватикана?", 5, "Ватикан", "Амстердам", "Вадуц", "Вена", 1));
        daoQuestion.insertQuestion(new Question("Столица Португалии?", 5, "Баку", "Хельсинки", "Лиссабон", "Берн", 3));
        daoQuestion.insertQuestion(new Question("Столица Франции?", 5, "Брест", "Париж", "Тулуза", "Тулон", 2));
        daoQuestion.insertQuestion(new Question("Столица Болгарии?", 5, "Варна", "Полвдив", "Балчик", "София", 4));
        daoQuestion.insertQuestion(new Question("Столица Германии?", 5, "Берлин", "Гамбург", "Кельн", "Мюнхен", 1));
        daoQuestion.insertQuestion(new Question("Столица Испании?", 5, "Мадрид", "Барселона", "Лиссабон", "Гранада", 1));
        daoQuestion.insertQuestion(new Question("Столица Латвии?", 5, "Литва", "Юрмала", "Вильнюс", "Рига", 4));
        daoQuestion.insertQuestion(new Question("Столица Литвы?", 5, "Рига", "Вильнюс", "Каунас", "Юрмала", 2));

        daoQuestion.insertQuestion(new Question("Столица Турции?", 10, "Дакка", "Анкара", "Бишек", "Бейрут", 2));
        daoQuestion.insertQuestion(new Question("Столица Индии?", 10, "Мале", "Ереван", "Дели", "Доха", 3));
        daoQuestion.insertQuestion(new Question("Столица Армении?", 10, "Маскат", "Ереван", "Никосия", "Нейльидо", 2));
        daoQuestion.insertQuestion(new Question("Столица Таджикистана?", 10, "Душанбе", "Бангкок", "Дакка", "Сана", 1));
        daoQuestion.insertQuestion(new Question("Столица Кореи?", 10, "Пекин", "Манила", "Токио", "Сеул", 4));
        daoQuestion.insertQuestion(new Question("Столица Узбекистана?", 10, "Эр-Рияд", "Цхинвал", "Ташкент", "Эль-Кувейт", 3));
        daoQuestion.insertQuestion(new Question("Столица Австралии?", 10, "Кинбера", "Сукре", "Паннама", "Сан-Хосе", 1));
        daoQuestion.insertQuestion(new Question("Столица Науры?", 10, "Не имеет столицы", "Эр-Рияд", "Улан-Батор", "Ханой", 1));
        daoQuestion.insertQuestion(new Question("Столица Тонга?", 10, "Муа", "Ваини", "Хавелолото", "Нукуалофа", 4));
        daoQuestion.insertQuestion(new Question("Столица Фиджи?", 10, "Левука", "Сува", "Нанди", "Ламбаса", 2));
        daoQuestion.insertQuestion(new Question("Столица Тувалу?", 10, "Ваиаку", "Алапи", "Фенафути", "Кулиа", 3));
        daoQuestion.insertQuestion(new Question("Столица Микронезия?", 10, "Колоина", "Паликир", "Тофол", "Румунг", 2));
        daoQuestion.insertQuestion(new Question("Столица Палау?", 10, "Нгерулмуд", "Корор", "Мейнгс", "Клоуклабед", 1));
        daoQuestion.insertQuestion(new Question("Столица Самоа?", 10, "Афега", "Паго-Паго", "Мклифануа", "Апиа", 4));
        daoQuestion.insertQuestion(new Question("Столица Новой Зеландии?", 10, "Окленд", "Роторуа", "Веллингтон", "Нельсон", 3));

        daoQuestion.insertQuestion(new Question("Столица Канады?", 15, "Оттава", "Торонто", "Монреаль", "Эдмонтон", 2));
        daoQuestion.insertQuestion(new Question("Столица Багамских островов?", 15, "Фрипорт", "Элис Таун", "Нассау", "Данмор-Таун", 3));
        daoQuestion.insertQuestion(new Question("Столица Судана?", 15, "Омдурман", "Кассала", "Ньяла", "Хартум", 4 ));
        daoQuestion.insertQuestion(new Question("Столица Парагвай?", 15, "Энкарнасьон", "Асунсьон", "Консепсьон", "Коронель-Овьедо", 2));
        daoQuestion.insertQuestion(new Question("Столица Египта?", 15, "Каир", "Хургада", "Луксор", "Гиза", 1));
        daoQuestion.insertQuestion(new Question("Столица Финляндии?", 15, "Турку", "Тампере", "Порво", "Хельсинки", 4));
        daoQuestion.insertQuestion(new Question("Столица Норвегии?", 15, "Берген", "Осло", "Тренхейм", "Тромсё", 2));
        daoQuestion.insertQuestion(new Question("Столица Нигера?", 15, "Ниамей", "Зиндер", "Агадес", "Тахуа", 1));
        daoQuestion.insertQuestion(new Question("Столица Камерун?", 15, "Дуала", "Гаруа", "Яунде", "Баменда", 3));
        daoQuestion.insertQuestion(new Question("Столица Кубы?", 15, "Варадеро", "Ольгин", "Тринидад", "Гаванна", 4));
        daoQuestion.insertQuestion(new Question("Столица Перу?", 15, "Арекипа", "Лима", "Куско", "Трухильо", 2));
        daoQuestion.insertQuestion(new Question("Столица Сербии?", 15, "Белград", "Нови-Сад", "Суботица", "Крагуевац", 1));
        daoQuestion.insertQuestion(new Question("Столица Лихтенштейна?", 15, "Шан", "Тризенберг", "Эшен", "Вадуц", 4));
        daoQuestion.insertQuestion(new Question("Столица Албании?", 15, "Дуресс", "Саранда", "Тирана", "Берат", 3));




        daoRecord.insertRecord(db, "Lesha", 10000, "2020/04/03 20:20");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
