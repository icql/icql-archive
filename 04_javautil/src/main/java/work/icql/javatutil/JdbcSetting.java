package work.icql.javatutil;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/15 14:52
 * @Title JdbcSetting
 * @Description JdbcSetting
 */
public class JdbcSetting {
    private JdbcDbtype dbType;
    private String dbUrl;
    private String dbUser;
    private String password;


    public JdbcSetting(JdbcDbtype dbType, String dbUrl, String dbUser, String password) {
        this.dbType = dbType;
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.password = password;
    }

    public JdbcDbtype getDbType() {
        return dbType;
    }

    public void setDbType(JdbcDbtype dbType) {
        this.dbType = dbType;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    /**
     * sample [mysql:jdbc:mysql://dbip:port/databasename?useUnicode=true&characterEncoding=utf8,
     * sqlserver:jdbc:microsoft:sqlserver://dbip:port;DatabaseName=databasename,
     * oracle:jdbc:oracle:thin:@dbip:port/databasename]
     *
     * @param dbUrl
     */
    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
