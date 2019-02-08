package com.prb.erp.domain.froebel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prb.erp.domain.BaseService;
import com.prb.erp.domain.item.goods.GoodsManageMapper;
import com.prb.erp.domain.member.MemberManage;
import com.prb.erp.domain.member.child.MemberChild;
import com.prb.erp.domain.member.item.MemberItem;
import com.prb.erp.utils.CommonApi;
import com.prb.erp.utils.DateUtils;
import com.prb.erp.utils.JsonUtils;
import com.prb.erp.utils.SessionUtils;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class FroebelApiService extends BaseService {

    protected static final Logger logger = LoggerFactory.getLogger(FroebelApiService.class);

    @Inject
    private GoodsManageMapper goodsManageMapper;

    @Value("${axboot.froebel.api.host}")
    private String froebelApiHost;

    /**
     * 프뢰벨 전자계약서 연동 API
     * 작성자 : 안지호
     * 작성일 : 2019. 02. 02
     * @param memberManage
     * @param memberItem
     * @param memberChild
     * @return
     * @throws Exception
     */
    public String saveFroebelContract(MemberManage memberManage, MemberItem memberItem, MemberChild memberChild) throws Exception {
        if (memberManage == null && memberItem == null && memberChild ==null) return null;
        //상품이름 가져오기
        String goodsName = goodsManageMapper.getGoodsName(memberItem.getGoodsCd());

        FroebelConstractVO froebelConstractVO = new FroebelConstractVO(
                memberManage.getOrgCd(), memberChild.getCounselor1TcherCd(), DateUtils.getYYYYMMDDAtNow(),
                DateUtils.splitDate(memberManage.getDeliveryRequestDay()), memberManage.getCustCd(), memberManage.getGd1Nm(),
                memberManage.getRepreNum() + "000000", memberManage.getHomeZipcode(),
                memberManage.getHomeAddress1(), memberManage.getDeliveryAddress2(), memberManage.getDeliveryZipcode(),
                memberManage.getDeliveryAddress1(), memberManage.getDeliveryAddress2(), String.valueOf(memberItem.getTotalPrice()),
                memberItem.getAgreementCd(), memberItem.getBankCd(), memberItem.getBankAccountNo(), String.valueOf(memberItem.getWithDrawDay()),
                memberManage.getRepreNum() + "000000", String.valueOf(memberItem.getPaymentPrice()),
                memberItem.getContractPaymentMethod(), SessionUtils.getCurrentLoginUserCd(), memberItem.getGoodsCd(),
                goodsName, String.valueOf(memberItem.getTotalPrice())
        );
        logger.info("froebelConstractVO >>>>>>>>>>>>" + froebelConstractVO.toString());
        //계약서 연동 url 만들기
        String froebelContractApiUrl = froebelApiHost + "/BizAuto/ContractInterface.do";
        // post 파라미터 jsonString으로 만들기
        String jsonBody = JsonUtils.convertToJsonStr(froebelConstractVO);
        String result = CommonApi.froebelPostWayApi(froebelContractApiUrl, "contractInfo", jsonBody);

        String froebelCustCd = "";
        if (result != null) {
            Gson gson = new GsonBuilder().create();
            FroebelApiResultVO resultVO = gson.fromJson(result, FroebelApiResultVO.class);
            froebelCustCd = resultVO.getCONT_NUM();
        }
        return froebelCustCd;
    }

    @Data
    public static class FroebelApiResultVO {
        private String RESULT;
        private String CONT_NUM;
    }
}
