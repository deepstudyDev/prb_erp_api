package com.prb.erp.procedure.inter;

import com.prb.erp.domain.froebel.*;
import com.prb.erp.domain.member.MemberManage;
import com.prb.erp.domain.member.child.MemberChild;
import com.prb.erp.domain.tcher.TcherManage;
import com.prb.erp.utils.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     프뢰벨과 데이터 인터페이스 서비스
 *     작성자 : 안지호
 *     작성일 : 2019. 01. 18
 * </pre>
 */
@Service
public class FroebelInterfaceService extends FroebelDBSupport {

    protected final Logger logger = LoggerFactory.getLogger(FroebelInterfaceService.class);

    //false 이면 IF STOP
    @Value("${axboot.isFroebelInterface}")
    public boolean isInterface;

    /**
     * <PRE>
     * 프뢰벨 학부모 정보 인터페이스
     * 작성자 : 안지호
     * 작성일 : 2019. 01. 18
     * </PRE>
     * @param member
     * @param dmlFlag
     * @throws Exception
     */
    public void insertMemberManage(MemberManage member, String dmlFlag) throws Exception {
        boolean insertFlag = true;
        if (!isInterface && !insertFlag) return;

        Connection con = connectionFroebelDB();
        if (con == null) return;

        CallableStatement cs = con.prepareCall("{call usp_InsSAM100T_HAPPY(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

        cs.setString (1, dmlFlag);
        cs.setString (2, member.getCustCd());
        cs.setString (3, member.getRepreNum());	//주민등록번호
        cs.setString(4, member.getGd1Nm());
        cs.setString(5, FroebelInterfaceEnum.SOCICAL_SECRET_NUMBER.getProperty());	//부주민등록번호
        cs.setString(6, member.getGd2Nm());
        cs.setString(7, member.getHpNo());
        cs.setString(8, member.getHpNo());
        cs.setString(9, member.getOrgCd());
        cs.setString(10, member.getTelNo());
        cs.setString(11, member.getEmail());
        cs.setString(12, member.getHomeZipcode());
        cs.setString(13, member.getHomeAddress1());
        cs.setString(14, member.getHomeAddress2());
        cs.setString(15, member.getDeliveryZipcode());
        cs.setString(16, member.getDeliveryAddress1());
        cs.setString(17, member.getDeliveryAddress2());
        cs.setString(18, FroebelInterfaceEnum.DEFAULT_INSERT_ID.getProperty());
        cs.setString(19, FroebelInterfaceEnum.DEFAULT_INSERT_ID.getProperty());
        cs.setString(20, FroebelInterfaceEnum.DEFAULT_CHILD_BIRTH_DAY.getProperty());
        cs.setString(21, member.getCustCd());
        cs.setString(22, member.getGd1Nm());

        cs.registerOutParameter(23 , Types.VARCHAR);
        cs.registerOutParameter(24 , Types.VARCHAR);
        cs.registerOutParameter(25 , Types.VARCHAR);

        cs.execute();

        logger.info("OUT 1: " + cs.getString(23));
        logger.info("OUT 2: " + cs.getString(24));
        logger.info("OUT 3: " + cs.getString(25));

        closeFroebelDB(cs, con);
    }

    /**
     * <PRE>
     * 프뢰벨 자녀정보 인터페이스
     * 작성자 : 안지호
     * 작성일 : 2019. 01. 18
     * </PRE>
     * @param child
     * @param dmlFlag
     * @throws Exception
     */
    public void insertChildManage(MemberChild child, String dmlFlag) throws Exception {
        boolean insertFlag = true;
        if (!isInterface && !insertFlag) return;

        Connection con = connectionFroebelDB();
        if (con == null) return;

        CallableStatement cs = con.prepareCall("{call usp_InsSAM130T_HAPPY(?,?,?,?,?,?,?,?,?,?)}");

        String childRepreNum = CommonUtils.getRepreNum(child.getChildrenContractDt(), "5");
        cs.setString (1, dmlFlag);
        cs.setString (2, child.getCustCd());
        cs.setString (3, child.getChildCd());
        cs.setString(4, child.getChildrenNm());
        cs.setString(5,   childRepreNum + "000000");//주민
        cs.setString(6, FroebelInterfaceEnum.DEFAULT_INSERT_ID.getProperty());
        cs.setString(7, FroebelInterfaceEnum.DEFAULT_INSERT_ID.getProperty());

        cs.registerOutParameter(8 , Types.VARCHAR);
        cs.registerOutParameter(9 , Types.VARCHAR);
        cs.registerOutParameter(10 , Types.VARCHAR);

        cs.execute();

        logger.info("OUT 1: " + cs.getString(8));
        logger.info("OUT 2: " + cs.getString(9));
        logger.info("OUT 3: " + cs.getString(10));

        closeFroebelDB(cs, con);
    }

    /**
     * <PRE>
     * 프뢰벨 상교사 인터페이스
     * 작성자 : 안지호
     * 작성일 : 2019. 01. 18
     * </PRE>
     * @param tcher
     * @param dmlFlag
     * @throws Exception
     */
    public void insertTcherManage12(TcherManage tcher, String dmlFlag) throws Exception {
        boolean insertFlag = true;
        if (!isInterface && !insertFlag) return;

        Connection con = connectionFroebelDB();
        if (con == null) return;

        CallableStatement cs = con.prepareCall("{call usp_InsHPB100T_HAPPY("
                + "?,?,?,?,?,"
                + "?,?,?,?,?,"
                + "?,?,?,?,?,"
                + "?,?,?,?,?,"
                + "?,?,?,?,?,"
                + "?,?,?,?,?,?"
                + ")}");

        cs.setString (1, dmlFlag);
        cs.setString (2, tcher.getTcherCd());	//사번
        cs.setString (3, tcher.getTcherNm());	//이름
        cs.setString (4, StringUtils.defaultString(tcher.getJoinDt()).replace("-",""));	//입사일
        cs.setString (5, StringUtils.defaultString(tcher.getOutDt()).replace("-",""));		//퇴사일
        cs.setString (6, tcher.getOutReasonCd());//퇴사사유
        cs.setString (7, FroebelInterfaceEnum.SOCICAL_SECRET_NUMBER.getProperty());	//주민등록번호
        cs.setString (8, tcher.getOrgCd());	//조직 hr
        cs.setString (9, StringUtils.defaultString(tcher.getEduYear()) + StringUtils.defaultString(tcher.getEduMonth()));	//교육년월

        cs.setString (10, tcher.getRankCd());	//직급
        cs.setString (11, tcher.getCcOrgCd());	//겸직조직

        cs.setString (12, tcher.getCcRankCd());//겸직직급
        cs.setString (13, tcher.getWorkRegCd()); //사원등록구분
        cs.setString (14, tcher.getWorkCd());	//근무상태
        cs.setString (15, tcher.getTcherBirthday());
        cs.setString (16, tcher.getTcherEmail());
        cs.setString (17, tcher.getTcherZipcode());
        cs.setString (18, tcher.getTcherAddress1());
        cs.setString (19, tcher.getTcherAddress2());
        cs.setString (20, tcher.getTcherTelNo());
        cs.setString (21, tcher.getTcherHpNo());
        cs.setString (22, tcher.getBankCd());
        cs.setString (23, tcher.getBankAccountNo());
        cs.setString (24, tcher.getBankAccountNm());

        cs.setString (25, tcher.getOrgCd());	//조직 hr
        cs.setString (26, tcher.getRemark());
        cs.setString (27, FroebelInterfaceEnum.DEFAULT_INSERT_ID.getProperty());
        cs.setString (28, FroebelInterfaceEnum.DEFAULT_INSERT_ID.getProperty());

        cs.registerOutParameter(29 , Types.VARCHAR);
        cs.registerOutParameter(30 , Types.VARCHAR);
        cs.registerOutParameter(31 , Types.VARCHAR);

        cs.execute();

        logger.info("OUT 1: " + cs.getString(29));
        logger.info("OUT 2: " + cs.getString(30));
        logger.info("OUT 3: " + cs.getString(31));

        closeFroebelDB(cs, con);
    }

    /**
     * <PRE>
     * 프뢰벨 방문교사 인터페이스
     * 작성자 : 안지호
     * 작성일 : 2019. 01. 18
     * </PRE>
     * @param tcher
     * @param dmlFlag
     * @throws Exception
     */
    public void insertTcherManage14(TcherManage tcher, String dmlFlag) throws Exception {
        boolean insertFlag = true;
        if (!isInterface && !insertFlag) return;

        Connection con = connectionFroebelDB();
        if (con == null) return;

        CallableStatement cs = con.prepareCall("{call usp_InsHPB100T_EDU("
                + "?,?,?,?,?,"
                + "?,?,?,?,?,"
                + "?,?,?,?,?,"
                + "?,?,?,?,?,"
                + "?,?,?,?,?,"
                + "?,?,?,?,?,?,?"
                + ")}");

        cs.setString (1, dmlFlag);
        cs.setString (2, tcher.getTcherCd());	//사번
        cs.setString (3, tcher.getTcherNm());	//이름
        cs.setString (4, StringUtils.defaultString(tcher.getJoinDt()).replace("-",""));	//입사일
        cs.setString (5, StringUtils.defaultString(tcher.getOutDt()).replace("-",""));		//퇴사일
        cs.setString (6, tcher.getOutReasonCd());//퇴사사유
        cs.setString (7, FroebelInterfaceEnum.SOCICAL_SECRET_NUMBER.getProperty());	//주민등록번호
        cs.setString (8, tcher.getOrgCd());	//조직 hr
        cs.setString (9, StringUtils.defaultString(tcher.getEduYear()) + StringUtils.defaultString(tcher.getEduMonth()));	//교육년월
        cs.setString (10, tcher.getRankCd());	//직급
        cs.setString (11, tcher.getCcOrgCd());	//겸직조직
        cs.setString (12, tcher.getCcRankCd());//겸직직급
        cs.setString (13, tcher.getWorkRegCd()); //사원등록구분
        cs.setString (14, tcher.getWorkCd());	//근무상태
        cs.setString (15, tcher.getTcherBirthday());
        cs.setString (16, tcher.getTcherEmail());
        cs.setString (17, tcher.getTcherZipcode());
        cs.setString (18, tcher.getTcherAddress1());
        cs.setString (19, tcher.getTcherAddress2());
        cs.setString (20, tcher.getTcherTelNo());
        cs.setString (21, tcher.getTcherHpNo());
        cs.setString (22, tcher.getBankCd());
        cs.setString (23, tcher.getBankAccountNo());
        cs.setString (24, tcher.getBankAccountNm());
        cs.setString (25, tcher.getGuaranteeDocCd());
        cs.setString (26, tcher.getOrgCd());	//조직 hr
        cs.setString (27, tcher.getRemark());
        cs.setString (28, FroebelInterfaceEnum.DEFAULT_INSERT_ID.getProperty());
        cs.setString (29, FroebelInterfaceEnum.DEFAULT_INSERT_ID.getProperty());

        cs.registerOutParameter(30 , Types.VARCHAR);
        cs.registerOutParameter(31 , Types.VARCHAR);
        cs.registerOutParameter(32 , Types.VARCHAR);

        cs.execute();

        logger.info("OUT 1: " + cs.getString(30));
        logger.info("OUT 2: " + cs.getString(31));
        logger.info("OUT 3: " + cs.getString(32));

        closeFroebelDB(cs, con);
    }

    /**
     * <PRE>
     * 프뢰벨 상담교사 기타사항 인터페이스
     * 작성자 : 안지호
     * 작성일 : 2019. 01. 18
     * </PRE>
     * @param tcher
     * @throws Exception
     */
    public void insertTcherManage10Etc(TcherManage tcher) throws Exception {
        if (!isInterface) return;

        Connection con = connectionFroebelDB();
        if (con == null) return;

        CallableStatement cs = con.prepareCall("{call usp_InsHPB100T_ETC_EDU(?,?,?,?,?)}");

        cs.setString (1, tcher.getTcherCd());	//사번
        cs.setString (2, tcher.getRemark());	//기타사항

        cs.registerOutParameter(3 , Types.VARCHAR);
        cs.registerOutParameter(4 , Types.VARCHAR);
        cs.registerOutParameter(5 , Types.VARCHAR);

        cs.execute();

        logger.info("OUT 1: " + cs.getString(3));
        logger.info("OUT 2: " + cs.getString(4));
        logger.info("OUT 3: " + cs.getString(5));

        closeFroebelDB(cs, con);
    }

    /**
     * <PRE>
     * 프뢰벨 교사정보(상담, 방문) 가져오기
     * 작성자 : 안지호
     * 작성일 : 2019. 01. 18
     * </PRE>
     * @return
     * @throws Exception
     */
    public List<HPB150TVVO>getHPB150TV() throws Exception {
        Connection con = connectionFroebelDB();
        if (con == null) return null;

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<HPB150TVVO>result = new ArrayList<>();

        String sql = "SELECT * FROM HPB150TV_HAPPY UNION ALL SELECT * FROM HPB150TV_EDU";
        pstmt = con.prepareStatement(sql);
        rs = pstmt.executeQuery();

        while (rs.next()) {
            HPB150TVVO item = new HPB150TVVO();

            item.setPERSON_NUMB(rs.getString("PERSON_NUMB"));
            item.setNAME(rs.getString("NAME"));
            item.setRATE_1(rs.getBigDecimal("RATE_1"));
            item.setRATE_2(rs.getBigDecimal("RATE_2"));
            item.setPAY_START_YYYYMM(rs.getString("PAY_START_YYYYMM"));
            item.setRATE_MONTH_1(rs.getString("RATE_MONTH_1"));
            item.setRATE_MONTH_2(rs.getString("RATE_MONTH_2"));
            item.setRATE_MGR(rs.getBigDecimal("RATE_MGR"));
            item.setMGR_START_YYYYMM(rs.getString("MGR_START_YYYYMM"));
            item.setNRATE1(rs.getBigDecimal("NRATE1"));
            item.setNRATE2(rs.getBigDecimal("NRATE2"));
            item.setADD_RATE(rs.getBigDecimal("ADD_RATE"));
            item.setWEEKDAY(rs.getString("WEEKDAY"));

            result.add(item);
        }
        closeFroebelDB(con, rs, pstmt);
        return result;
    }

    /**
     * <PRE>
     * 프뢰벨 상담교사 매출정보 가져오기
     * 작성자 : 안지호
     * 작성일 : 2019. 01. 18
     * </PRE>
     * @param tcherCd
     * @return
     * @throws Exception
     */
    public List<HPB201TVVO>getHPB201TV_HAPPY(String tcherCd) throws Exception {
        if ("".equals(tcherCd)) return null;

        Connection con = connectionFroebelDB();
        if (con == null) return null;

        String sql = "SELECT * FROM HPB201TV_HAPPY WHERE PERSON_NUMB = ? ";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,tcherCd);
        ResultSet rs = pstmt.executeQuery();

        List<HPB201TVVO>result = new ArrayList<>();

        while (rs.next()) {
            HPB201TVVO item = new HPB201TVVO();
            item.setPERSON_NUMB(rs.getString("PERSON_NUMB"));
            item.setSALES_YYYYMM(rs.getString("YYYYMM"));
            item.setPLAN_AMT(rs.getBigDecimal("PLAN_AMT"));
            item.setCONT_SALE_AMT(rs.getBigDecimal("CONT_SALE_AMT"));
            item.setPERCENTAGE(rs.getBigDecimal("PERCENTAGE"));
            item.setWAGES_SUM_AMT(rs.getBigDecimal("WAGES_SUM_AMT"));
            item.setDED_SUM_AMT(rs.getBigDecimal("DED_SUM_AMT"));
            item.setREAL_SUM_AMT(rs.getBigDecimal("REAL_SUM_AMT"));
            result.add(item);
        }
        closeFroebelDB(con, rs, pstmt);
        return result;
    }

    /**
     * <PRE>
     * 프뢰벨 방문교사 매출정보 가져오기
     * 작성자 : 안지호
     * 작성일 : 2019. 01. 18
     * </PRE>
     * @param tcherCd
     * @return
     * @throws Exception
     */
    public List<HPB201TVVO>getHPB201TV_EDU(String tcherCd) throws Exception {
        if ("".equals(tcherCd)) return null;

        Connection con = connectionFroebelDB();
        if (con == null) return null;

        String sql = "select * from HPB201TV_EDU WHERE PERSON_NUMB = ? ";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,tcherCd);
        ResultSet rs = pstmt.executeQuery();

        List<HPB201TVVO>result = new ArrayList<>();

        while (rs.next()) {
            HPB201TVVO item = new HPB201TVVO();
            item.setPERSON_NUMB(rs.getString("PERSON_NUMB"));
            item.setSALES_YYYYMM(rs.getString("PAY_YYYYMM"));
            item.setPLAN_SU(rs.getBigDecimal("PLAN_SU"));
            item.setRESULT_SU(rs.getBigDecimal("RESULT_SU"));
            item.setEFFECT_RIZ(rs.getBigDecimal("EFFECT_RIZ"));
            item.setGNL_AMT(rs.getBigDecimal("GNL_AMT"));
            item.setPRE_AMT(rs.getBigDecimal("PRE_AMT"));
            item.setTRANS_AMT(rs.getBigDecimal("TRANS_AMT"));
            result.add(item);
        }
        closeFroebelDB(con, rs, pstmt);
        return result;
    }

    /**
     * <PRE>
     * 교사 사원이력(인사정보) 상담/방문통합 가져오기
     * 작성자 : 안지호
     * 작성일 : 2019. 01. 18
     * </PRE>
     * @return
     */
    public List<HPB130TVVO>getHPB130TV() {
        List<HPB130TVVO>result = new ArrayList<>();

        try {
            Connection con = connectionFroebelDB();
            if (con == null) return null;

            String sql = "select * from HPB130TV_HAPPY UNION ALL select * from HPB130TV_EDU";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                HPB130TVVO item = new HPB130TVVO();
                item.setPERSON_NUMB(rs.getString("PERSON_NUMB"));
                item.setCHARGE_DATE(rs.getString("CHARGE_DATE"));
                item.setCHARGE_CODE(rs.getString("CHARGE_CODE"));
                item.setCHARGE_NAME(rs.getString("CHARGE_NAME"));
                item.setCOMPANY_NAME(rs.getString("COMPANY_NAME"));
                item.setSECT_CODE(rs.getString("SECT_CODE"));
                item.setSECT_NAME(rs.getString("SECT_NAME"));
                item.setHR_DEPT_CODE(rs.getString("HR_DEPT_CODE"));
                item.setHR_DEPT_CODE(rs.getString("HR_DEPT_CODE"));

                item.setHR_TREE_CODE(rs.getString("HR_TREE_CODE"));
                item.setHR_TREE_NAME(rs.getString("HR_TREE_NAME"));
                item.setCLASS_CODE(rs.getString("CLASS_CODE"));
                item.setCLASS_NAME(rs.getString("CLASS_NAME"));
                item.setWITH_TREE_CODE(rs.getString("WITH_TREE_CODE"));
                item.setWITH_TREE_NAME(rs.getString("WITH_TREE_NAME"));
                item.setWITH_CLASS_CODE(rs.getString("WITH_CLASS_CODE"));
                item.setWITH_CLASS_NAME(rs.getString("WITH_CLASS_NAME"));
                item.setREMARK(rs.getString("REMARK"));
                item.setTREE_CODE(rs.getString("TREE_CODE"));
                item.setDEPT_CODE(rs.getString("DEPT_CODE"));
                item.setCREATE_DATE(rs.getString("CREATE_DATE"));
                item.setCHARGE_DTCD(rs.getString("CHARGE_DTCD"));
                item.setCHARGE_DTCD_NAME(rs.getString("CHARGE_DTCD_NAME"));
                result.add(item);
            }
            closeFroebelDB(con, rs, pstmt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * <PRE>
     * 교사 기본인적(상담/방문) 가져오기
     * 작성자 : 안지호
     * 작성일 : 2019. 01. 18
     * </PRE>
     * @return
     */
    public List<HPB100TVVO>getHPB100TV() {
        List<HPB100TVVO>result = new ArrayList<>();

        try {
            Connection con = connectionFroebelDB();
            if (con == null) return null;

            String sql = "select * from HPB100TV_EDU UNION ALL select * from HPB100TV_HAPPY";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                HPB100TVVO item = new HPB100TVVO();
                item.setPERSON_NUMB(rs.getString("PERSON_NUMB"));
                item.setNAME(rs.getString("NAME"));
                item.setPERSON_TYPE(rs.getString("PERSON_TYPE"));
                item.setTELEPHON(rs.getString("TELEPHON"));
                item.setPHONE_NO(rs.getString("PHONE_NO"));
                item.setEMAIL_ADDR(rs.getString("EMAIL_ADDR"));
                item.setFOREIGN_YN(rs.getString("FOREIGN_YN"));
                item.setBIRTH_DATE(rs.getString("BIRTH_DATE"));
                item.setNATION_CODE(rs.getString("NATION_CODE"));
                item.setDWELLING_YN(rs.getString("DWELLING_YN"));
                item.setDED_CODE(rs.getString("DED_CODE"));
                item.setZIP_CODE(rs.getString("ZIP_CODE"));
                item.setADDR1(rs.getString("ADDR1"));
                item.setADDR2(rs.getString("ADDR2"));
                item.setJOIN_DATE(rs.getString("JOIN_DATE"));
                item.setRETR_DATE(rs.getString("RETR_DATE"));
                item.setRETR_RESN(rs.getString("RETR_RESN"));
                item.setWORK_STATE(rs.getString("WORK_STATE"));
                item.setREG_TYPE(rs.getString("REG_TYPE"));
                item.setCLASS_CODE(rs.getString("CLASS_CODE"));
                item.setWITH_CLASS_CODE(rs.getString("WITH_CLASS_CODE"));
                item.setHR_TREE_CODE(rs.getString("HR_TREE_CODE"));
                item.setWITH_TREE_CODE(rs.getString("WITH_TREE_CODE"));
                item.setEDU_YYYYMM(rs.getString("EDU_YYYYMM"));
                item.setEDU_YYYY(rs.getString("EDU_YYYY"));
                item.setEDU_MM(rs.getString("EDU_MM"));
                item.setDOCUMENT_YN(rs.getString("DOCUMENT_YN"));
                item.setDED_TYPE(rs.getString("DED_TYPE"));
                item.setBUSINESS_TYPE(rs.getString("BUSINESS_TYPE"));
                item.setSECT_CODE(rs.getString("SECT_CODE"));
                item.setCOMP_NUM(rs.getString("COMP_NUM"));
                item.setBANK_CODE(rs.getString("BANK_CODE"));
                item.setDEPOSITOR(rs.getString("DEPOSITOR"));
                item.setBANK_ACCOUNT(rs.getString("BANK_ACCOUNT"));
                item.setREMARK(rs.getString("REMARK"));

                item.setBRING_PERSON_NUMB(rs.getString("BRING_PERSON_NUMB"));
                item.setBRING_PERSON_NUMB2(rs.getString("BRING_PERSON_NUMB2"));

                result.add(item);
            }
            closeFroebelDB(con, rs, pstmt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * <PRE>
     * 회원 기본정보 가져오기
     * 작성자 : 안지호
     * 작성일 : 2019. 01. 18
     * </PRE>
     * @return
     */
    public List<SAM100TVVO>getSAM100TV_HAPPY() {
        List<SAM100TVVO>result = new ArrayList<>();

        try {
            Connection con = connectionFroebelDB();
            if (con == null) return null;

            String sql = "SELECT * FROM SAM100TV_HAPPY";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                SAM100TVVO item = new SAM100TVVO();

                item.setCUST_CODE(rs.getString("CUST_CODE"));
                item.setCUST_GUBUN(rs.getString("CUST_GUBUN"));
                item.setCUST_GUBUN_NAME(rs.getString("CUST_GUBUN_NAME"));
                item.setAREA_CODE(rs.getString("AREA_CODE"));
                item.setTREE_CODE(rs.getString("TREE_CODE"));
                item.setTREE_NAME(rs.getString("TREE_NAME"));
                item.setDEPT_CODE(rs.getString("DEPT_CODE"));
                item.setDEPT_NAME(rs.getString("DEPT_NAME"));

                item.setREPRE_NUM(rs.getString("REPRE_NUM"));
                item.setCUST_NAME(rs.getString("CUST_NAME"));
                item.setSUB_CUST_NAME(rs.getString("SUB_CUST_NAME"));
                item.setSUB_HP_NO(rs.getString("SUB_HP_NO"));
                item.setHP_NO(rs.getString("HP_NO"));
                item.setTEL_NO(rs.getString("TEL_NO"));
                item.setEMAIL(rs.getString("EMAIL"));

                item.setZIP_NO(rs.getString("ZIP_NO"));
                item.setADDR(rs.getString("ADDR"));
                item.setADDR2(rs.getString("ADDR2"));
                item.setDEL_ZIP_NO(rs.getString("DEL_ZIP_NO"));
                item.setDEL_ADDR(rs.getString("DEL_ADDR"));
                item.setDEL_ADDR2(rs.getString("DEL_ADDR2"));
                item.setUSE_YN(rs.getString("USE_YN"));
                item.setCREATE_DATE(rs.getString("CREATE_DATE"));
                item.setCREATE_USERID(rs.getString("CREATE_USERID"));
                item.setUPDATE_DATE(rs.getString("UPDATE_DATE"));
                item.setUPDATE_USERID(rs.getString("UPDATE_USERID"));
                result.add(item);
            }
            closeFroebelDB(con, rs, pstmt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * <PRE>
     * 자녀정보 가져오기
     * 작성자 : 안지호
     * 작성일 : 2019. 01. 18
     * </PRE>
     * @return
     */
    public List<SAM130TVVO>getSAM130TV_HAPPY() {
        List<SAM130TVVO>result = new ArrayList<>();

        try {
            Connection con = connectionFroebelDB();
            if (con == null) return null;

            String sql = "SELECT * FROM SAM130TV_HAPPY";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                SAM130TVVO item = new SAM130TVVO();
                item.setCUST_CODE(rs.getString("CUST_CODE"));
                //item.setCUST_NAME(rs.getString("CUST_NAME"));
                item.setCHILD_CODE(rs.getString("CHILD_CODE"));
                item.setCHILD_NAME(rs.getString("CHILD_NAME"));
                item.setCHILD_REPRE_NUM(rs.getString("CHILD_REPRE_NUM"));
                item.setCREATE_DATE(rs.getString("CREATE_DATE"));
                item.setCREATE_USERID(rs.getString("CREATE_USERID"));
                item.setUPDATE_DATE(rs.getString("UPDATE_DATE"));
                item.setUPDATE_USERID(rs.getString("UPDATE_USERID"));
                result.add(item);
            }
            closeFroebelDB(con, rs, pstmt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
