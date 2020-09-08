package lk.sparkx.ncms.utils;

public class Helper {

    // constants
    public static final String PATIENT_SERIAL_NO_PREP = "PAT";

    //Database configuration parameters
    public static final String DB_URL = "jdbc:mysql://localhost:3306/";
    public static final String HOST_NAME = "localhost";
    public static final String DATABASE = "ncms";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
    public static final int MIN_IDLE = 2;
    public static final int MAX_IDLE = 5;
    public static final int MAX_TOTAL = 10;

    // Database tables
    public static final String TABLE_PATIENTS = "patients";
    public static final String TABLE_DOCTORS = "doctors";
    public static final String TABLE_HOSPITALS = "hospitals";
    public static final String TABLE_PATIENT_QUEUE = "patient_queue";
    public static final String TABLE_HOSPITAL_BED = "hospital_beds";
    public static final String TABLE_USERS = "users";

    // Database setup queries
    public static final String CREATE_DATABASE = "CREATE DATABASE IF NOT EXISTS " + DATABASE + ";";

    // Database table creation queries
    public static final String CREATE_HOSPITALS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_HOSPITALS + " ( id VARCHAR(50) NOT NULL, name VARCHAR(45) NULL,district VARCHAR(45) NULL,location_x INT NULL,location_y INT NULL,build_date DATE NULL,PRIMARY KEY (id));";
    public static final String CREATE_DOCTORS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_DOCTORS + " ( `id` VARCHAR(50) NOT NULL, `name` VARCHAR(100) NULL,`hospital_id` VARCHAR(100) NULL,`is_director` TINYINT NULL,PRIMARY KEY (`id`),FOREIGN KEY (`hospital_id`) REFERENCES `hospital` (`id`) ON DELETE CASCADE);";
    public static final String CREATE_PATIENTS_TABLE =  "CREATE TABLE IF NOT EXISTS " + TABLE_PATIENTS + " (`id` VARCHAR(50) NOT NULL,`serial_no` VARCHAR(10) NULL,`first_name` VARCHAR(100) NULL,`last_name` VARCHAR(100) NULL,`district` VARCHAR(10) NULL,`location_x` INT NULL,`location_y` INT NULL,`severity_level` VARCHAR(50) NULL,`gender` VARCHAR(10) NULL,`contact` VARCHAR(45) NULL,`email` VARCHAR(45) NULL,`age` INT NULL,`admit_date` DATE NULL,`admitted_by` VARCHAR(45) NULL,`discharge_date` DATE NULL,`discharged_by` VARCHAR(45) NULL,PRIMARY KEY (`id`),FOREIGN KEY (`admitted_by`) REFERENCES `doctor` (`id`),FOREIGN KEY (`discharged_by`) REFERENCES `doctor` (`id`));";
    public static final String CREATE_HOSPITAL_BEDS_TABLE =  "CREATE TABLE IF NOT EXISTS " + TABLE_HOSPITAL_BED + " (`id` INT NOT NULL,`hospital_id` VARCHAR(50) NOT NULL,`patient_id` VARCHAR(50) NULL,PRIMARY KEY (`id`, `hospital_id`),FOREIGN KEY (`hospital_id`) REFERENCES `hospital` (`id`),FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`));";
    public static final String CREATE_PATIENT_QUEUE_TABLE =  "CREATE TABLE IF NOT EXISTS " + TABLE_PATIENT_QUEUE + " (`id` INT NOT NULL,`patient_id` VARCHAR(50) NOT NULL,PRIMARY KEY (`id`),FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`) ON DELETE CASCADE);";
    public static final String CREATE_USERS_TABLE =  "CREATE TABLE IF NOT EXISTS " + TABLE_USERS + " (`username` VARCHAR(50) NOT NULL,`password` VARCHAR(100) NULL,`name` VARCHAR(100) NULL,`moh` TINYINT NULL,`hospital` TINYINT NULL,PRIMARY KEY (`username`));";

    // Patient Queries
    public static final String INSERT_PATIENT = "INSERT INTO "+ TABLE_PATIENTS +" (id, serial_no, first_name, last_name, district, location_x, location_y, gender, contact, email, age) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    public static final String GET_PATIENT_COUNT = "SELECT COUNT(id) AS PATIENT_COUNT FROM " + TABLE_PATIENTS;
    public static final String GET_PATIENT_DETAILS = "SELECT * FROM " + TABLE_PATIENTS + " WHERE id=? OR serial_no=?";
}

