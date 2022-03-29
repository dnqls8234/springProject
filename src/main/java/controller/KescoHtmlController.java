package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/htmlList/")
public class KescoHtmlController {

	@RequestMapping("list")
	@ResponseBody
	public List<String> listup(Model m) {
		
		List<String> c = new ArrayList<>();
		String path = "C:/Users/Lee/Desktop/project4/src/main/webapp/html";

		File dir = new File(path);

		File[] fileList = dir.listFiles();

		for (File file : fileList) {

			if (file.isFile()) {

				String fileName = file.getName();

				// 각 파일 읽기 및 분석 작업
				c.add(fileName);
				System.out.println(fileName);
			}

		}
		
		return c;

	}
}
