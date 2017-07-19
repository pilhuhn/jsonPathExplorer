package de.bsd.jsonPathExplorer;


import io.restassured.path.json.JsonPath;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/**
 * @author hrupp
 */
public class Explorer {


  public static void main(String[] args) throws Exception {

    if (args.length<1) {
      System.err.println("Usage Explorer <jsonfile>");
      System.exit(1);
    }

    File f = new File(args[0]);
    if (!f.exists() && f.canRead()) {
      System.err.println("File " + args[0] + " can't be found or is unreadable");
      System.exit(2);
    }

    JsonPath jp = JsonPath.from(f);

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    String s;
    System.out.print("\n> ");
    while ((s = in.readLine()) != null && s.length() != 0) {
      try {
        System.out.println(jp.get(s));
      }
      catch (IllegalArgumentException iae) {
        System.err.println(iae.getMessage() + "\n     " + iae.getCause().getMessage());
      }
      System.out.print("\n> ");
      System.out.flush();
    }
  }
}
