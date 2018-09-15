package com.lenzhao.pb;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by lenzhao on 18-9-15.
 */
public class ProtoBufDemo {

    public static void main(String[] args) throws IOException {

        sensor.Sensor.DNS.Builder builder = sensor.Sensor.DNS.newBuilder();
        builder.setSerialNum("1000110");
        builder.setAccessTime("2018:09:15 21:31:00");
        builder.setSip("192.168.1.1");
        builder.setSport(80);
        builder.setDport(443);
        builder.setDnsType(1);
        builder.setHost("127.0.0.1");
        sensor.Sensor.DNS dns = builder.build();
        byte[] buff = dns.toByteArray();
        System.out.println(Arrays.toString(buff));
        sensor.Sensor.DNS dns1 = sensor.Sensor.DNS.parseFrom(buff);
        System.out.println("====host===" + dns1.getHost());
    }
}
