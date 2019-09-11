package com.example.hybss.net.RetrofitNet;

import com.huawenpicture.eims.beans.EquipItemBean;
import com.huawenpicture.eims.beans.ModelItemBean;
import com.huawenpicture.eims.beans.ResponseBeans.EquipDetailBean;
import com.huawenpicture.eims.beans.ResponseBeans.ListRespBean;
import com.huawenpicture.eims.beans.ResponseBeans.OperatorBean;
import com.huawenpicture.eims.beans.ResponseBeans.RespDepotItemBean;
import com.huawenpicture.eims.beans.ResponseBeans.RespWithNoParamBean;
import com.huawenpicture.eims.beans.ResponseBeans.StackItemBean;
import com.huawenpicture.eims.beans.requestBeans.BrandModelListReqBean;
import com.huawenpicture.eims.beans.requestBeans.DepotCondBean;
import com.huawenpicture.eims.beans.requestBeans.EquipLeaveBean;
import com.huawenpicture.eims.beans.requestBeans.ListReqBean;
import com.huawenpicture.eims.beans.requestBeans.ReqOperatorBean;
import com.huawenpicture.eims.beans.requestBeans.ReqPageBean;

import io.reactivex.Observable;


/**
 * 用于对数据提前处理，同时也可用来对参数处理
 * 例如合并，map ,zip或者flateMap这样可以将不符合自己参数的数据转换为自己想请求的参数
 * 这一层是属于module内，用于处理数据源，供给presenter作为中间纽带进行调用
 */
public class NetDataLoader extends ObjectLoader {
    private ServerApi api;

    private NetDataLoader() {
        api = RetrofitManager.getServerApi();
    }

    public static NetDataLoader loader;

    public static NetDataLoader get() {
        if (loader == null) {
            synchronized (NetDataLoader.class) {
                if (loader == null) {
                    loader = new NetDataLoader();
                }
            }
        }
        return loader;
    }

    /*******************************请求数据*****************************************/

    //获取器材列表
    public Observable<BaseResponse<ListRespBean<EquipItemBean>>> postEquip(int curPage, int pageSize) {
        ReqPageBean bean = new ReqPageBean();
        bean.setCur_page(curPage);
        bean.setPer_page(pageSize);
        ListReqBean reqBean = new ListReqBean();
        reqBean.setPage(bean);
        return observer(api.postEquipList(getRequestBody(reqBean))); //请求参数的初步处理
    }

    //获取器材列表
    public Observable<BaseResponse<ListRespBean<EquipItemBean>>> postEquip(ListReqBean bean) {
        return observer(api.postEquipList(getRequestBody(bean))); //请求参数的初步处理
    }

    //获取品牌型列表
    public Observable<BaseResponse<ListRespBean<ModelItemBean>>> postModel(BrandModelListReqBean bean) {
        BaseRequest<BrandModelListReqBean> baseRequest = new BaseRequest<>();
        baseRequest.setOptions(bean);
        return observer(api.postModelList(getRequestBody(baseRequest))); // 请求参数
    }

    //获取主页品牌列表
    public Observable<BaseResponse<ListRespBean<ModelItemBean>>> postHomeModel(ListReqBean bean) {
        return observer(api.postModelList(getRequestBody(bean))); // 请求参数
    }


    //获取器材详情
    public Observable<BaseResponse<EquipDetailBean>> getEquipDetail(int id) {
        return observer(api.getEquipDetail(id)); // 请求参数
    }

    //获取出库列表
    public Observable<BaseResponse<ListRespBean<StackItemBean>>> postStackList(ListReqBean bean) {
        return observer(api.postStackList(getRequestBody(bean))); // 请求参数
    }

    //进行入库操作
    public Observable<BaseResponse<RespWithNoParamBean>> postEquipEnter(int equip_id, EquipLeaveBean bean) {
        return observer(api.postEquipLeave(equip_id, getRequestBody(bean)));
    }

    //进行出库操作
    public Observable<BaseResponse<RespWithNoParamBean>> postEquipLeave(int equip_id, EquipLeaveBean bean) {
        return observer(api.postEquipLeave(equip_id, getRequestBody(bean)));
    }

    //进行还库操作
    public Observable<BaseResponse<RespWithNoParamBean>> postEquipRevert(int equip_id, EquipLeaveBean bean) {
        return observer(api.postEquipLeave(equip_id, getRequestBody(bean)));
    }

    //进行出库多器材操作
    public Observable<BaseResponse<RespWithNoParamBean>> postStack(EquipLeaveBean bean) {
        return observer(api.postStack(getRequestBody(bean)));
    }

    /***********操作台*****************/
    //操作台获取列表
    public Observable<BaseResponse<ListRespBean<OperatorBean>>> postOperators(ReqOperatorBean bean) {
        return observer(api.postStackPick(getRequestBody(bean)));
    }


    /*************仓库**********/
    //获取仓库列表
    public Observable<BaseResponse<ListRespBean<RespDepotItemBean>>> postDepotList(DepotCondBean bean) {
        return observer(api.postDepotList(getRequestBody(bean)));
    }

    /***********************请求数据modle类*************************************/
    //用于对请求数据model修改为json数据
    private <T> BaseRequest getRequestBody(T data) {
        if (data == null)
            return null;
        BaseRequest<T> requestBody = new BaseRequest<>();
        requestBody.setOptions(data);
        return requestBody;
    }


}
