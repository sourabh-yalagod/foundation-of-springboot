package demo.Beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api")
public class BeanTypes {

//    @Autowired
//    private RequestBean requestBean;  // proxy injected here
//
//    @GetMapping("")
//    public String getResponse() {
//        return requestBean.getResponse();
//    }
}

@Component
//@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Scope("prototype")
class RequestBean {
    public RequestBean() {
        System.out.println("RequestBean Http Bean Created : " + this.hashCode());
    }

    public String getResponse() {
        return "Response from RequestBean";
    }
}

@Component
class User {
    @Autowired
    private RequestBean requestBean;

    public User() {
        System.out.println("User Bean Created : " + this.hashCode());
    }
}

// ---------------------------- ConditionOnProperty ---------------------------- //

@Service
class DBConnection {
    @Autowired(required = false)
    SqlConnection sqlConnection;
    @Autowired(required = false)
    NonSqlDbConnection nonSqlDbConnection;

    public DBConnection() {
        System.out.println("Bean Created : " + this.hashCode());
        System.out.println("NonSqlDbConnection : "+ Objects.nonNull(nonSqlDbConnection));
        System.out.println("SqlDbConnection : "+ Objects.nonNull(sqlConnection));
    }
}

interface DBConnectionInterface {
    void isDone();
}

@Component
@ConditionalOnProperty(prefix = "dbconnection", value = "sql", havingValue = "true")
class SqlConnection implements DBConnectionInterface {
    public SqlConnection() {
        System.out.println("SqlConnection Bean Created : " + this.hashCode());
    }

    public void isDone() {
    }
}

@Component
@ConditionalOnProperty(prefix = "dbconnection", value = "sql", havingValue = "false")
class NonSqlDbConnection implements DBConnectionInterface {
    public NonSqlDbConnection() {
        System.out.println("NonSqlDbConnection Bean Created : " + this.hashCode());
    }

    public void isDone() {
    }
}