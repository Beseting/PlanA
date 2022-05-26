package com.ym.plib.http.retrofit;

public class HttpConfig {
    private String baseUrl; //基础路径
    private long defaultConnectTimeout;// 默认连接超时时间
    private long defaultWriteTimeout;// 默认写入超时时间
    private long defaultReadTimeout;// 默认读取超时时间

    public String getBaseUrl() {
        return baseUrl;
    }

    public long getDefaultConnectTimeout() {
        return defaultConnectTimeout;
    }

    public long getDefaultWriteTimeout() {
        return defaultWriteTimeout;
    }

    public long getDefaultReadTimeout() {
        return defaultReadTimeout;
    }

    private HttpConfig(Builder builder){
        this.baseUrl = builder.baseUrl;
        this.defaultConnectTimeout = builder.defaultConnectTimeout;
        this.defaultWriteTimeout = builder.defaultWriteTimeout;
        this.defaultReadTimeout = builder.defaultReadTimeout;
    }

    public static class Builder{
        private String baseUrl = "https://www.baidu.com/"; //基础路径
        private long defaultConnectTimeout = 30000;// 默认连接超时时间
        private long defaultWriteTimeout = 30000;// 默认写入超时时间
        private long defaultReadTimeout = 30000;// 默认读取超时时间

        public Builder setBaseUrl(String baseUrl){
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder setDefaultConnectTimeout(long defaultConnectTimeout){
            this.defaultConnectTimeout = defaultConnectTimeout;
            return this;
        }

        public Builder setDefaultWriteTimeout(long defaultWriteTimeout){
            this.defaultWriteTimeout = defaultWriteTimeout;
            return this;
        }

        public Builder setDefaultReadTimeout(long defaultReadTimeout){
            this.defaultReadTimeout = defaultReadTimeout;
            return this;
        }

        public HttpConfig build(){
            return new HttpConfig(this);
        }
    }
}
