package com.example.jarresourcedemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * @author Adam
 */
@Slf4j
@SpringBootApplication
public class JarResourceDemoApplication {

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String prefix = "BOOT-INF/classes/";
        String suffix = ".json";
        log.info("jar文件通过命令获取");
        String[] cmd = new String[]{"jar", "-tf", "jar-resource-demo-0.0.1-SNAPSHOT.jar"};
        Process ps = Runtime.getRuntime().exec(cmd);
        BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
        String line;
        while ((line = br.readLine()) != null) {
            if (line.startsWith(prefix) && line.endsWith(suffix)) {
                String fileName = line.split(prefix)[1];
                InputStream is = JarResourceDemoApplication.class.getClassLoader().getResourceAsStream(fileName);
                Map map = mapper.readValue(is, Map.class);
                log.info(map.toString());
            }
        }
    }

}
