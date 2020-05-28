package com.github.enesusta.mongodb.configuration;

public class MongoURI {

    private String username;
    private String pwd;
    private String host;
    private String db;
    private int port;

    public MongoURI() {
    }

    public MongoURI(final String username,
                    final String pwd,
                    final String host,
                    final String db,
                    final int port) {
        this.username = username;
        this.pwd = pwd;
        this.host = host;
        this.db = db;
        this.port = port;
    }

    public String mongoConnectionUrl(MongoURI mongoURI) {
        return String
                .format("mongodb://%s:%s@%s:%d/%s", mongoURI.getUsername(), mongoURI.getPwd(),
                        mongoURI.getHost(), mongoURI.getPort(), mongoURI.getDb());
    }

    public static class MongoURIBuilder {
        private String username;
        private String pwd;
        private String host;
        private String db;
        private int port;

        public MongoURIBuilder user(final String username) {
            this.username = username;
            return this;
        }

        public MongoURIBuilder password(final String pwd) {
            this.pwd = pwd;
            return this;
        }

        public MongoURIBuilder host(final String host) {
            this.host = host;
            return this;
        }

        public MongoURIBuilder port(final int port) {
            this.port = port;
            return this;
        }

        public MongoURIBuilder database(final String db) {
            this.db = db;
            return this;
        }

        public MongoURI build() {
            return new MongoURI(username, pwd, host, db, port);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }
}
