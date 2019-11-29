package com.cdbhe.plana.mvvm.common_adapter.vm;

import com.cdbhe.plana.R;
import com.cdbhe.plana.mvvm.common_adapter.adapter.DemoCommonAdapter;
import com.cdbhe.plana.mvvm.common_adapter.biz.ICommonAdapterBiz;
import com.cdbhe.plana.mvvm.common_adapter.model.CommonAdapterModel;

import java.util.ArrayList;
import java.util.List;

public class CommonAdapterVm {
    private ICommonAdapterBiz iCommonAdapterBiz;
    private List<CommonAdapterModel> commonAdapterModelList;
    private DemoCommonAdapter demoGridCommonAdapter;
    private DemoCommonAdapter demoListCommonAdapter;

    public CommonAdapterVm(ICommonAdapterBiz iCommonAdapterBiz) {
        this.iCommonAdapterBiz = iCommonAdapterBiz;
    }

    public void init(){
        commonAdapterModelList = new ArrayList<>();
        commonAdapterModelList.add(new CommonAdapterModel("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1575020452219&di=ccf40e1b3c15f72e5e23d456f7856b5f&imgtype=0&src=http%3A%2F%2Fwx3.sinaimg.cn%2Flarge%2F77dd6b95ly1ft3cvbzkkxj20jo0b24dw.jpg","我不是药神"));
        commonAdapterModelList.add(new CommonAdapterModel("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1575020521406&di=30fc17a2a86507e5784ce76f85151323&imgtype=0&src=http%3A%2F%2Fimg.5nd.com%2F250%2Fphoto%2Fsinger%2F2%2F34639.jpg","战狼II"));
        commonAdapterModelList.add(new CommonAdapterModel("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1575020593680&di=0224854825a75cf3529a9386e689212c&imgtype=0&src=http%3A%2F%2Finews.gtimg.com%2Fnewsapp_bt%2F0%2F10578198691%2F1000.jpg","哪吒"));
        commonAdapterModelList.add(new CommonAdapterModel("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1575020646412&di=491149a7e157880394f3bb7e5f1fab7c&imgtype=0&src=http%3A%2F%2Fcrawl.nosdn.127.net%2F94e635dd205ee1c9a33ca11989f07c80.jpg","西虹市首富"));
        commonAdapterModelList.add(new CommonAdapterModel("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1575020711020&di=ff0de9d9356c708d235eeadf96fc35c7&imgtype=0&src=http%3A%2F%2Fa1.peoplecdn.cn%2F2280021d94057c70f851f2be5797acaf.jpg","疯狂的外星人"));
        commonAdapterModelList.add(new CommonAdapterModel("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1575020752605&di=999ab51ca6d08a638e4e0c9bcd9b2856&imgtype=0&src=http%3A%2F%2Fdingyue.nosdn.127.net%2FyliFMwSNdTFkmy8SaoEVCrzHuoJjnxteu1NO6sZ24NHbA1533083565446compressflag.jpg","唐人街探案"));
        demoGridCommonAdapter = new DemoCommonAdapter(iCommonAdapterBiz.getContext(),commonAdapterModelList, R.layout.adapter_common_grid_item);
        iCommonAdapterBiz.getGridView().setAdapter(demoGridCommonAdapter);
        demoListCommonAdapter = new DemoCommonAdapter(iCommonAdapterBiz.getContext(),commonAdapterModelList, R.layout.adapter_common_list_item);
        iCommonAdapterBiz.getListView().setAdapter(demoListCommonAdapter);
    }
}
