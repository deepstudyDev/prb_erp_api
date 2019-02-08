package com.prb.erp.procedure.inter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.sql.*;

/**
 * <PRE>
 *     프뢰벨 DB 접속 클래스
 *     작성자 : 안지호
 *     작성일 : 2019. 01. 18
 * </PRE>
 */
public abstract class FroebelDBSupport {

    protected final Logger logger = LoggerFactory.getLogger(FroebelDBSupport.class);


    @Value("${axboot.froebel.db.url}")
    public String froebelDbConnectionUrl;
    //TEST
    //private String FroebelDbConnectionUrl = "jdbc:jtds:sqlserver://61.100.9.49:11033;databaseName=b2berp_happy_8pm;user=b2berp;password=qlxnqlrPwjddlek";
    //LIVE
    //private String FroebelDbConnectionUrl = "jdbc:jtds:sqlserver://61.100.9.49:11033;databaseName=B2BERP_HAPPY;user=b2berp;password=qlxnqlrPwjddlek";

    private String DbClassName = "net.sourceforge.jtds.jdbc.Driver";

    //false 이면 IF STOP
    @Value("${axboot.isFroebelConnection}")
    public boolean isConnection;

    /**
     * <PRE>
     *     프뢰벨 DB 접속하기
     *     작성자 : 안지호
     *     작성일 : 2019. 01. 18
     * </PRE>
     * @return
     * @throws Exception
     */
    protected Connection connectionFroebelDB() throws Exception {
        if (isConnection) {
            Class.forName(DbClassName);
            Connection con = DriverManager.getConnection(froebelDbConnectionUrl);
            logger.info("FroebelDB connection Success!!");
            return con;
        }
        return null;
    }

    /**
     * <PRE>
     *     프뢰벨 DB 접속닫기
     *     작성자 : 안지호
     *     작성일 : 2019. 01. 18
     * </PRE>
     * @param cs
     * @param con
     */
    protected void closeFroebelDB(CallableStatement cs, Connection con) {
        if (cs != null && con != null) {
            try {
                cs.close();
                con.close();
            } catch (SQLException e) {
                logger.error("CallableStatement FroebelDbClose error!!");
                e.printStackTrace();

            }
        }
    }

    protected void closeFroebelDB(Connection con, ResultSet rs, PreparedStatement pstmt) {
        if (con != null && rs != null && pstmt != null) {
            try {
                con.close();
                rs.close();
                pstmt.close();
            } catch (SQLException ex) {
                logger.error("Connection FroebelDbClose error!!");
                ex.printStackTrace();
            }
        }
        logger.info("FroebelDB Close.......");
    }
}
