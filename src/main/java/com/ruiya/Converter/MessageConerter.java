package com.ruiya.Converter;

import com.ruiya.jdk.jdk16.Person;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class MessageConerter implements HttpMessageConverter<Person> {

    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return false;
    }

    /**
     * 服务器要统计所有的吗，messageConverter 都能写出那些内容
     * @return
     */
    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return  MediaType.parseMediaTypes("application/x-liusi");
    }

    @Override
    public List<MediaType> getSupportedMediaTypes(Class<?> clazz) {
        return HttpMessageConverter.super.getSupportedMediaTypes(clazz);
    }

    @Override
    public Person read(Class<? extends Person> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    public void write(Person person, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        //自定义协议数据的写入
        String data = person.getName() + ";" + person.getSex() + ";" + person.getSex();
        OutputStream so =  outputMessage.getBody();
        so.write(data.getBytes());
    }
}
