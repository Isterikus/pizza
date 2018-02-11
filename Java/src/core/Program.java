package core;

import core.kotlin.JConnector;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Valeriy Boiko
 * @version 1.0
 */
public class Program {

	public static void main(String[] args) throws Exception{

		Scanner				scanner;
		ArrayList<String>	data = new ArrayList<>();

		if (args.length == 0) {

			scanner = new Scanner(System.in);
		}
		else {

			scanner = new Scanner(new FileInputStream(new File(args[0])));
		}

		while (scanner.hasNext()) {

			data.add(scanner.nextLine());
		}

		JConnector.runKt(data);
	}
}
