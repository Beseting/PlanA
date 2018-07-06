package com.cdbhe.plana.httpdemo;

import com.cdbhe.plib.http.retrofit.IBaseBiz;

public interface IHttpDemoBiz extends IBaseBiz {
    void refreshUIData(String name);
}
