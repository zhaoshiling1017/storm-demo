package com.lenzhao.avro;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lenzhao on 18-9-15.
 */
public class AvroDemo {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = ClassLoader.getSystemResourceAsStream("user.avsc");
        Schema schema = new Schema.Parser().parse(inputStream);

        GenericRecord user = new GenericData.Record(schema);
        user.put("name", "lenzhao");
        user.put("age", 29);
        user.put("email","lenzhao@yahoo.com");

        //序列化
        File file = new File("users.avro");
        DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<>(schema);
        DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<>(datumWriter);
        dataFileWriter.create(schema, file);
        dataFileWriter.append(user);
        dataFileWriter.close();

        //反序列化
        DatumReader<GenericRecord> datumReader = new GenericDatumReader<>(schema);
        DataFileReader<GenericRecord> dataFileReader = new DataFileReader<>(file, datumReader);

        GenericRecord _current = null;

        while (dataFileReader.hasNext()) {
            _current = dataFileReader.next(_current);
            System.out.println(_current);
        }
        dataFileReader.close();
    }
}
