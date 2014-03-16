package fti.aiml.web;

import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import fti.aiml.model.UploadedFile;
import fti.aiml.validator.FileValidator;




@Controller
@RequestMapping("/upload")
public class UploadController {
	
	@Autowired
	FileValidator fileValidator;

	@RequestMapping("/file")
	public ModelAndView getUploadForm(
			@RequestParam("botID") String botID,
			@ModelAttribute("uploadedFile") UploadedFile uploadedFile,
			BindingResult result) {
		ModelAndView model = new  ModelAndView("uploadForm");
		return model;
		
	}

	@RequestMapping("/fileUpload")
	public ModelAndView fileUploaded(
			@RequestParam("botID") String botID,
			@ModelAttribute("uploadedFile") UploadedFile uploadedFile,
			BindingResult result) {
		//Authenticate and authorization
		
	
		InputStream inputStream = null;
		OutputStream outputStream = null;
		MultipartFile file = uploadedFile.getFile();
		fileValidator.validate(uploadedFile, result);
		String fileName = file.getOriginalFilename();
		System.out.println(fileName);
		if (result.hasErrors()) {
			return new ModelAndView("uploadForm");
		}

//		try {
//			inputStream = file.getInputStream();
//			
//			File newFile = new File(AppConfig.BOTS_PATH  + userId + "/bots/" + botId + "/aiml/" + fileName);
//			//File newFile = new File(Bots_Path  + userId + "\\bots\\" + botId + "\\aiml\\" + fileName);
//			if (!newFile.exists()) {
//				newFile.createNewFile();
//			}
//			outputStream = new FileOutputStream(newFile);
//			int read = 0;
//			byte[] bytes = new byte[1024];
//
//			while ((read = inputStream.read(bytes)) != -1) {
//				outputStream.write(bytes, 0, read);
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		ModelAndView model = new ModelAndView("showFile");
		model.addObject("botID",botID);
		
		return model;
	}

}
