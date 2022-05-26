package com.kmginfotech.CvsGbli.DriverScripts;

import java.util.Scanner;

import com.kmginfotech.Gbli.endorsement.*;

public class DriverScriptEndorsement {

	public static void main(String[] args) {

		int choice;

		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);

		System.out.println("Press  1 To Generate DUXPPM Data For Endorsement");

		System.out.println("Press  2 To Generate DUXPIA Data For Endorsement");

		System.out.println("Press  3 To Generate DUXPIE Data For Endorsement");

		System.out.println("Press  4 To Generate DUXPIN Data For Endorsement");

		System.out.println("Press  5 To Generate DUXPNI Data For Endorsement");

		System.out.println("Press  6 To Generate DUXPFM Data For Endorsement");

		System.out.println("Press  7 To Generate DUXPLP Data For Endorsement");

		System.out.println("Press  8 To Generate DUXPIP Data For Endorsement");

		System.out.println("Press  9 To Generate DUXPIS Data For Endorsement");

		System.out.println("Press 10 To Generate DUXPLM Data For Endorsement");

		choice = in.nextInt();

		switch (choice) {

		case 1:

			Duxppm objDuxppm = new Duxppm();

			objDuxppm.readDataForDuxppm();

			break;

		case 2:

			Duxpia objDuxpia = new Duxpia();

			objDuxpia.readDataForDuxpia();

			break;

		case 3:

			Duxpie objDuxpie = new Duxpie();

			objDuxpie.readDataForDuxpie();

			break;

		case 4:

			Duxpin objDuxpin = new Duxpin();

			objDuxpin.readDataForDuxpin();

			break;

		case 5:

			Duxpni objDuxpni = new Duxpni();

			objDuxpni.readDataForDuxpni();

			break;

		case 6:

			Duxpfm objDuxpfm = new Duxpfm();

			objDuxpfm.readCmpMortgagee();

			break;

		case 7:

			Duxplp objDuxplp = new Duxplp();

			objDuxplp.readDataForDuxplp();

			break;

		case 8:

			Duxpip objDuxpip = new Duxpip();

			objDuxpip.readDataForDuxpip();

			break;

		case 9:

			Duxpis objDuxpis = new Duxpis();

			objDuxpis.readDataForDuxpis();

			break;

		case 10:

			Duxplm objDuxplm = new Duxplm();

			objDuxplm.readDataForDuxplm();

			break;

		default:
			System.out.println("You Enter Wrong Choice");

		}
	}

}
