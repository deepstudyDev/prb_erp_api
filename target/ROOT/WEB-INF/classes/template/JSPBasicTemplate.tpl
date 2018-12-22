<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ppm" tagdir="/WEB-INF/tags" %>

<ppm:set key="title" value="${pageName}"/>
<ppm:set key="page_desc" value="${PAGE_REMARK}"/>
<ppm:set key="page_auto_height" value="true"/>

<ppm:layout name="base">
    <jsp:attribute name="script">
        <script type="text/javascript" src="<c:url value='@{programJSPath}' />"></script>
    </jsp:attribute>
    <jsp:body>

        <ppm:page-buttons></ppm:page-buttons>


        <div role="page-header">
            <ppm:form name="searchView0">
                <ppm:tbl clazz="ax-search-tbl" minWidth="500px">
                    <ppm:tr>
                        <ppm:td label='검색조건' width="300px">
                            <input type="text" class="form-control" />
                        </ppm:td>
                        <ppm:td label='검색조건 1' width="300px">
                            <input type="text" class="form-control" />
                        </ppm:td>
                        <ppm:td label='검색조건 2' width="300px">
                            <input type="text" class="form-control" />
                        </ppm:td>
                    </ppm:tr>
                </ppm:tbl>
            </ppm:form>
            <div class="H10"></div>
        </div>

        <ppm:split-layout name="ax1" orientation="horizontal">
            <ppm:split-panel width="*" style="">

                <!-- 목록 -->
                <div class="ax-button-group" data-fit-height-aside="grid-view-01">
                    <div class="left">
                        <h2><i class="cqc-list"></i>
                            프로그램 목록 </h2>
                    </div>
                    <div class="right">
                        <button type="button" class="btn btn-default" data-grid-view-01-btn="add"><i class="cqc-circle-with-plus"></i> 추가</button>
                        <button type="button" class="btn btn-default" data-grid-view-01-btn="delete"><i class="cqc-circle-with-plus"></i> 삭제</button>
                    </div>
                </div>
                <div data-ax5grid="grid-view-01" data-fit-height-content="grid-view-01" style="height: 300px;"></div>

            </ppm:split-panel>
        </ppm:split-layout>

    </jsp:body>
</ppm:layout>