package com.prb.erp.domain.kicc;

import com.chequer.axboot.core.parameter.RequestParams;
import com.prb.erp.domain.api.ApiKiccPaymentResultSaveVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class KiccResultService {

    @Autowired
    private KiccResultMapper kiccResultMapper;

    @Transactional
    public void saveKiccPaymentResultLog(ApiKiccPaymentResultSaveVO vo) {
        if (vo.getCustCd() == null) return;
        KiccResult kiccResult = new KiccResult(
                vo.getCustCd(), vo.getResCd(), vo.getResMsg(), vo.getCno(), vo.getAmount(),
                vo.getOrderNo(), vo.getAuthNo(), vo.getTranDate(), vo.getEscrowYn(), vo.getComplexYn(),
                vo.getStatCd(), vo.getStatMsg(), vo.getPayType(), vo.getMallId(), vo.getCardNo(),
                vo.getIssuerNo(), vo.getIssuerNm(), vo.getAcquirerCd(), vo.getAcquirerNm(), vo.getInstallPeriod(),
                vo.getNoint(), vo.getPartCancelYn(), vo.getCardGubun(), vo.getCardBizGubun(), vo.getCponFlag(),
                vo.getUsedCpon(), vo.getCancAcqDate(), vo.getCancDate(), vo.getAccountNo()
        );
        RequestParams<KiccResult> kiccResultRequestParams = new RequestParams<>();
        kiccResultMapper.saveKiccPaymentResultLog(kiccResult);
    }
}
