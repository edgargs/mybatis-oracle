package pe.farmaciasperuanas.posunificado.mybatis;

import java.io.IOException;
import java.io.Reader;

import java.util.Properties;

import mifarma.common.FarmaConstants;
import mifarma.common.FarmaVariables;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;



public class MyBatisUtil {
    private static SqlSessionFactory factory;
    //private static final String FarmaConstants.CONNECT_STRING_SID_W = "jdbc:oracle:thin:@//%s:%s/%s";
    //dubilluz 08.12.2014



    private MyBatisUtil() {
    }
    static {
        Reader reader = leerMyBatisConfig();

        Properties properties = new Properties();
        properties.setProperty("jdbc.driverClassName", "oracle.jdbc.driver.OracleDriver");
        String strUrl =
            String.format(FarmaConstants.CONNECT_STRING_SERVICENAME_W, FarmaVariables.vIPBD, "1521", FarmaVariables.vSID);
        properties.setProperty("jdbc.url", strUrl);
        properties.setProperty("jdbc.username", FarmaVariables.vUsuarioBD);
        properties.setProperty("jdbc.password", FarmaVariables.vClaveBD);

        factory = new SqlSessionFactoryBuilder().build(reader, properties);
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return factory;
        //KMONCADA 06.01.2015 LA VARIABLE ESTATICA GENERA LENTITUD AL REALIZAR LA CONEXION
        /* Reader reader = leerMyBatisConfig();

        Properties properties = new Properties();
        properties.setProperty("jdbc.driverClassName", "oracle.jdbc.driver.OracleDriver");
        String strUrl = String.format(FarmaConstants.CONNECT_STRING_SID_W, FarmaVariables.vIPBD, "1521", FarmaVariables.vSID);
        properties.setProperty("jdbc.url", strUrl);
        properties.setProperty("jdbc.username", FarmaVariables.vUsuarioBD);
        properties.setProperty("jdbc.password", FarmaVariables.vClaveBD);

        return new SqlSessionFactoryBuilder().build(reader, properties); */

    }

    private static Reader leerMyBatisConfig() {
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return reader;
    }

}
