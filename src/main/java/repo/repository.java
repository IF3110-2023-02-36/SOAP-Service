package repo;

import db.database;

public class repository {
    //ini gunanya buat ngequery di db
    //konekin db ntar di constructor ini
    //bikin anak2nya ntar

    protected database db;
    
    public repository() {
        this.db = new database();
    }

    public database getDatabase() {
        return this.db;
    }

}
