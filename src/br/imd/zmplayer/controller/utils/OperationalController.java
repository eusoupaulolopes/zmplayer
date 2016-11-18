package br.imd.zmplayer.controller.utils;

import javafx.application.Platform;

public class OperationalController {
	
	public static void closeProgram(){
        Platform.exit();
        System.exit(0);
	}

}
