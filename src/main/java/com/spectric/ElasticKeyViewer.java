package com.spectric;

import org.elasticsearch.common.settings.KeyStoreWrapper;
import org.elasticsearch.common.settings.SecureString;

import java.nio.file.Paths;
import java.nio.file.Path;

public class ElasticKeyViewer
{
    public static void main( String[] args )
    {
        try {
            if (args.length == 0) {
                System.err.println("Path to config folder must be provided");
                return;
            }

            final Path path = Paths.get( args[0] );
            char[] password = new char[0];
            // IMPORTANT - Passing a password via command-line isn't secure
            // because it can be read by others with the 'ps' command.
            if (args.length >= 2) {
                password = args[1].toCharArray();
            }

            KeyStoreWrapper ksw = KeyStoreWrapper.load(path);
            if (ksw != null) {
                ksw.decrypt(password);
            }

            for (String k : ksw.getSettingNames()) {
                SecureString v = ksw.getString(k);
                System.out.println(k + " : " + v);
            }
        } catch ( Exception e) {
            System.err.println("Unhandled error: " + e);
        }
    }
}