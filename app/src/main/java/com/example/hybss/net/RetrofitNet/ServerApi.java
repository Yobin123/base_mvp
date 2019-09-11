package com.example.hybss.net.RetrofitNet;

import com.huawenpicture.eims.beans.EquipItemBean;
import com.huawenpicture.eims.beans.ModelItemBean;
import com.huawenpicture.eims.beans.ResponseBeans.EquipDetailBean;
import com.huawenpicture.eims.beans.ResponseBeans.ListRespBean;
import com.huawenpicture.eims.beans.ResponseBeans.OperatorBean;
import com.huawenpicture.eims.beans.ResponseBeans.RespDepotItemBean;
import com.huawenpicture.eims.beans.ResponseBeans.RespWithNoParamBean;
import com.huawenpicture.eims.beans.ResponseBeans.StackItemBean;
import com.huawenpicture.eims.beans.requestBeans.DepotCondBean;
import com.huawenpicture.eims.beans.requestBeans.ListReqBean;
import com.huawenpicture.eims.beans.requestBeans.ReqOperatorBean;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * 网络相关接口处理
 */
public interface ServerApi {
    //获取器材列表
    @GET("/api/equip")
    Observable<BaseResponse<ListRespBean<EquipItemBean>>> getEquipList1();

    //获取器材列表
    @POST("/api/equips")
    Observable<BaseResponse<ListRespBean<EquipItemBean>>> postEquipList(@Body BaseRequest<ListReqBean> request);

    //获取器材详情
    @GET("/api/equip/{equip_id}")
    Observable<BaseResponse<EquipDetailBean>> getEquipDetail(@Path("equip_id") int id);

    //用于获取型号相关数据相关
    @POST("/api/models")
    Observable<BaseResponse<ListRespBean<ModelItemBean>>> postModelList(@Body BaseRequest<ListReqBean> request);


    /**************************暂存**************************/
    //出库操作台列表
    @POST("/api/stacks/leave")
    Observable<BaseResponse<ListRespBean<StackItemBean>>> postStackList(@Body BaseRequest<ListReqBean> request);

    //入库器材
    @POST("/api/equip/{equip_id}/enter")
    Observable<BaseResponse<RespWithNoParamBean>> postEquipEnter(@Path("equip_id") int equip_id, @Body BaseRequest request);

    //出库器材
    @POST("/api/equip/{equip_id}/leave")
    Observable<BaseResponse<RespWithNoParamBean>> postEquipLeave(@Path("equip_id") int equip_id, @Body BaseRequest request);

    //还库器材
    @POST("/api/equip/{equip_id}/revert")
    Observable<BaseResponse<RespWithNoParamBean>> postEquipRevert(@Path("equip_id") int equip_id, @Body BaseRequest request);

    //提交入库，出库 还库
    @POST("/api/stacks/submit")
    Observable<BaseResponse> postStackSubmit(@Body BaseRequest request);

    //获取所有操作台暂存列表和设备(获取暂存列表)
    @POST("/api/stacks/pick")
    Observable<BaseResponse<ListRespBean<OperatorBean>>> postStackPick(@Body BaseRequest<ReqOperatorBean> request);


    //出库多器材操作
    @POST("/api/stack")
    Observable<BaseResponse<RespWithNoParamBean>> postStack(@Body BaseRequest request);

    /**********************仓库**************************/

    //获取所有操作台暂存列表和设备(获取暂存列表)
    @POST("/api/depots")
    Observable<BaseResponse<ListRespBean<RespDepotItemBean>>> postDepotList(@Body BaseRequest<DepotCondBean> request);


}
