package controller;

import java.io.File;

public class zz {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path = "C:/Users/Lee/Desktop/project4/src/main/webapp/html";

		File dir = new File(path);

		File[] fileList = dir.listFiles();

		for (File file : fileList) {

			if (file.isFile()) {

				String fileName = file.getName();

				// 각 파일 읽기 및 분석 작업
				
				System.out.println(fileName);
			}

		}
	}

}
