package repo;

import db.database;

public class repository {
    protected database db;
    
    public repository() {
        this.db = new database();
    }

    public database getDatabase() {
        return this.db;
    }

}
