package com.example.zookeeper;

import com.example.zookeeper.service.Node;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.commons.cli.*;

@SpringBootApplication
public class LclGalaxyZookeeperApplication {

    public static void main(String[] args) {
        SpringApplication.run(LclGalaxyZookeeperApplication.class, args);

        final Options options = new Options();
        final Option option = new Option("z", true, "ZooKeeper address");
        options.addOption(option);

        final CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);
        } catch (final ParseException e) {
            throw new RuntimeException("parser command line error",e);
        }

        String zkAddr = null;
        if (cmd.hasOption("z")) {
            zkAddr = cmd.getOptionValue("z");
        }else{
            System.err.println("please input the ZooKeeper address by -z option");
            System.exit(1);
        }

        try {
            Node.INSTANCE.start(zkAddr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
