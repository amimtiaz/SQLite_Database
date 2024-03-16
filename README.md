# SQLite

**SQLite is a lightweight relational database management system (RDBMS) that is embedded directly into the Android operating system. It allows Android developers to store and manage structured data in a private database, typically within the context of their own application.**

**SQLite is a typical relational database, containing tables (which consist of rows and columns), indexes etc. We can create our own tables to hold the data accordingly. This structure is referred to as a schema.**

Here are some key features of SQLite databases in Android:

* **Embedded Database:** SQLite is embedded into Android, meaning that it doesn't run as a separate process. Instead, it operates within the same process space as the Android application that uses it.

* **SQL Support:** SQLite databases in Android support standard SQL syntax for creating, querying, updating, and deleting data. This makes it familiar to developers who have experience with SQL-based databases.

* **Lightweight:** SQLite is designed to be lightweight and efficient, making it suitable for use in resource-constrained environments such as mobile devices.

* **Transactional:** SQLite supports ACID (Atomicity, Consistency, Isolation, Durability) transactions, ensuring data integrity and reliability.

* **Security:** SQLite databases in Android are private to each application by default, providing a level of security and isolation for the data stored within them.

* **Integration with Android APIs:** Android provides a set of APIs for interacting with SQLite databases, including classes like SQLiteOpenHelper and SQLiteDatabase, which simplify common database operations such as creating tables, executing SQL commands, and managing database connections.

Overall, SQLite databases in Android provide a convenient and efficient way for developers to persistently store and manage structured data within their applications. They are commonly used for storing user preferences, application settings, cached data, and other types of structured data

<hr>

### Android SQLite SQLiteOpenHelper

Android has features available to handle changing database schemas, which mostly depend on using the SQLiteOpenHelper class. 

*SQLiteOpenHelper* wraps up this logic to create and upgrade a database as per our specifications. For that, we’ll need to create a custom subclass of SQLiteOpenHelper implementing at least the following three methods.

* **Constructor:** This takes the Context (e.g., an Activity), the name of the database, an optional cursor factory (we’ll discuss this later), and an integer representing the version of the database schema you are using (typically starting from 1 and increment later).

  ```
  public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
  ```

* **onCreate(SQLiteDatabase db):** It’s called when there is no database and the app needs one. It passes us a SQLiteDatabase object, pointing to a newly-created database, that we can populate with tables and initial data.
* **onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion):** It’s called when the schema version we need does not match the schema version of the database, It passes us a SQLiteDatabase object and the old and new version numbers. Hence we can figure out the best way to convert the database from the old schema to the new one.

We define a *DBManager* class to perform all database CRUD(Create, Read, Update and Delete) operations.

<hr>
  ```
 public class ShowResult extends AppCompatActivity {

    private ActivityShowResultBinding binding;

    DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHelper = new DatabaseHelper(ShowResult.this);

        //Cursor cursor = dbHelper.getAllData();


        Cursor cursor = dbHelper.searchDataByName("");
        binding.tvDisplay.setText("Total row: " + cursor.getCount());

        if (cursor != null && cursor.getCount() > 0) {

            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String mobile = cursor.getString(2);

                binding.tvDisplay.append("\nID: " + id + " Name: " + name + " Mobile: " + mobile);
            }

        } else {
            binding.tvDisplay.setText("Data nai");
        }


        binding.showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchBarText = binding.searchBar.getText().toString().toLowerCase();

                Toast.makeText(ShowResult.this, searchBarText, Toast.LENGTH_SHORT).show();

                Cursor cursor = dbHelper.searchDataByName("" + searchBarText);
                binding.tvDisplay.setText("Total row: " + cursor.getCount());

                if (cursor != null && cursor.getCount() > 0) {

                    while (cursor.moveToNext()) {
                        int id = cursor.getInt(0);
                        String name = cursor.getString(1);
                        String mobile = cursor.getString(2);

                        binding.tvDisplay.append("\nID: " + id + " Name: " + name + " Mobile: " + mobile);
                    }

                } else {
                    binding.tvDisplay.setText("Data nai");
                }

               
            }
        });

    }
}
```
