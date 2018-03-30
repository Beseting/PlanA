package com.cdbhe.plib.http.gson;

import com.cdbhe.plib.http.model.Data;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/30.
 */

public class DataTypeAdaptor extends TypeAdapter<Data> {

    public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {
        @SuppressWarnings("unchecked")
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            if (type.getRawType() == Data.class) {
                return (TypeAdapter<T>) new DataTypeAdaptor(gson);
            }
            return null;
        }
    };

    private final Gson gson;

    DataTypeAdaptor(Gson gson) {
        this.gson = gson;
    }

    @Override
    public void write(JsonWriter out, Data value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }

        out.beginObject();
        out.name("status");
        gson.getAdapter(Integer.class).write(out, value.getStatus());
        out.name("message");
        gson.getAdapter(String.class).write(out, value.getMessage());
        out.name("data");
        gson.getAdapter(Object.class).write(out, value.getData());
        out.endObject();
    }

    @Override
    public Data read(JsonReader in) throws IOException {
        Data data = new Data();
        Map<String, Object> dataMap = (Map<String, Object>) readInternal(in);
        data.setStatus(Integer.parseInt(dataMap.get("status").toString()));
        data.setMessage(dataMap.get("message").toString());
        data.setData(dataMap.get("data"));
        return data;
    }


    private Object readInternal(JsonReader in) throws IOException {
        JsonToken token = in.peek();
        switch (token) {
            case BEGIN_ARRAY:
                List<Object> list = new ArrayList<Object>();
                in.beginArray();
                while (in.hasNext()) {
                    list.add(readInternal(in));
                }
                in.endArray();
                return list;
            case BEGIN_OBJECT:
                Map<String, Object> map = new LinkedTreeMap<String, Object>();
                in.beginObject();
                while (in.hasNext()) {
                    map.put(in.nextName(), readInternal(in));
                }
                in.endObject();
                return map;
            case STRING:
                return in.nextString();
            case NUMBER:
                //将其作为一个字符串读取出来
                String numberStr = in.nextString();
                //返回的numberStr不会为null
                if (numberStr.contains(".") || numberStr.contains("e")
                        || numberStr.contains("E")) {
                    return Double.parseDouble(numberStr);
                }
                return Long.parseLong(numberStr);
            case BOOLEAN:
                return in.nextBoolean();
            case NULL:
                in.nextNull();
                return null;
            default:
                throw new IllegalStateException();
        }
    }
}