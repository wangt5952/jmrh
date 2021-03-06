package com.bz.xtcx.manager.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bz.xtcx.manager.service.IUserImageService;
import com.bz.xtcx.manager.vo.VoResponse;

@RestController
@RequestMapping("xtcx/file")
public class FileUploadController {
	
	@Autowired
	private IUserImageService userImageService;

	// 文件上传相关代码
	@PostMapping("upload")
	public Object upload(@RequestParam("file")MultipartFile file, @RequestParam("type") int type) {
		VoResponse voRes = new VoResponse();
		if (file.isEmpty()) {
			voRes.setFail(voRes);
			voRes.setData("文件为空");
			return voRes;
		}
		// 获取文件名
		String fileName = file.getOriginalFilename();
		// 获取文件的后缀名
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		// 文件上传后的路径
		//String filePath = "D://test//";//win 本地地址
		String filePath = "/root/java/xtcx/";
		fileName = UUID.randomUUID() + suffixName;
		File dest = new File(filePath + fileName);
		// 检测是否存在目录
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		System.out.println(dest.getPath());
		try {
			file.transferTo(dest);
			if(userImageService.saveOrUpdate(type, fileName) > 0) {
				return voRes;
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		voRes.setFail(voRes);
		voRes.setData("上传失败");
		return voRes;
	}

	// 文件下载相关代码
	@GetMapping("/download")
	public Object downloadFile(HttpServletRequest request, HttpServletResponse response, @RequestParam("fileName") String fileName) {
		//String fileName = "readme.txt";
		VoResponse voRes = new VoResponse();
		if (fileName != null) {
			// 当前是从该工程的WEB-INF//File//下获取文件(该目录可以在下面一行代码配置)然后下载到C:\\users\\downloads即本机的默认下载的目录
			//String realPath = request.getServletContext().getRealPath("//WEB-INF//");
			//String realPath = "D://test//";
			String realPath = "/root/java/xtcx/";
			File file = new File(realPath, fileName);
			if (file.exists()) {
			    response.setCharacterEncoding("utf-8");
			    response.setContentType("multipart/form-data");
			    response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
			    try {
			        //打开本地文件流
			        InputStream inputStream = new FileInputStream(realPath + fileName);
			        //激活下载操作
			        OutputStream os = response.getOutputStream();
			        //循环写入输出流
			        byte[] b = new byte[2048];
			        int length;
			        while ((length = inputStream.read(b)) > 0) {
			            os.write(b, 0, length);
			        }
			        // 这里主要关闭。
			        os.close();
			        inputStream.close();
			        return null;
			    }catch (Exception e){
			        e.printStackTrace();
			    }
			}
		}
		return voRes;
	}

	// 多文件上传
	@PostMapping("batch/upload")
	public String handleFileUpload(HttpServletRequest request) {
		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
		MultipartFile file = null;
		BufferedOutputStream stream = null;
		for (int i = 0; i < files.size(); ++i) {
			file = files.get(i);
			if (!file.isEmpty()) {
				try {
					byte[] bytes = file.getBytes();
					stream = new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
					stream.write(bytes);
					stream.close();

				} catch (Exception e) {
					stream = null;
					return "You failed to upload " + i + " => " + e.getMessage();
				}
			} else {
				return "You failed to upload " + i + " because the file was empty.";
			}
		}
		return "upload successful";
	}

}
