import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017/8/21.
 */
public class SecuritySessionFactory {

    private static SqlSessionFactory instance;

    private SecuritySessionFactory() {
    }

    private static synchronized SqlSessionFactory getSqlSessionFactoryInstance() {
        return instance == null ? getSessionFactory() : instance;
    }

    private static SqlSessionFactory getSessionFactory() {
        String resource = "/META-INF/mybatis/mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }
}
