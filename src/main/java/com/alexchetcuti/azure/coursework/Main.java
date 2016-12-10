package com.alexchetcuti.azure.coursework;
public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Report 1: Camera start ups");
		Common.retreiveCameras();
		
		System.out.println("Report 2: Priority vehicles caught speeding");
		Common.retreivePriorityVehicles();
	}

}
