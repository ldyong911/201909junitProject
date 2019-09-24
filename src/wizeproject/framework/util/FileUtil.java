package wizeproject.framework.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Title      : 파일 유틸에 관한 클래스 
 * @Packgename : wizeproject.framework.util
 * @Filename   : FileUtil.java
 *
 * @Author  : 
 * @Since   : 2019. 6. 28.
 * @Version : 1.0
 */
public class FileUtil {

	/**
	 * 저장소에 물리 파일을 업로드
	 *
	 * @param uploadFileList
	 * @param uploadPath
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, String>> uploadFile(List<MultipartFile> uploadFileList, String uploadPath) throws Exception {
		LogUtil.println("[FileUtil - uploadFile] [START]");

		List<Map<String, String>> uploadList = new ArrayList<Map<String, String>>(); //업로드 정보 리스트

		String path = "D:" + File.separator + uploadPath; // D:\BASIC\0 형태로됨 (uploadPath가 BASIC\0)
		LogUtil.println("패스:" + path);

		File filePath = new File(path); //filePath와 path는 같음
		if (!filePath.isDirectory()) {
			filePath.mkdirs(); //디렉토리가 없다면 새로 만든다
		}
		LogUtil.println("파일패스:" + filePath);

		if (CommonUtil.notEmpty(uploadFileList)) {
			if (uploadFileList.size() > 0) {
				for (MultipartFile file : uploadFileList) {
					if (file == null) {
						continue;
					}

					if (file.getSize() > 0) {
						String fileName = ""; //물리파일명

						fileName = UUID.randomUUID() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")); // UUID + .확장자 형태로됨
						LogUtil.println(".확장자:" + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
						LogUtil.println("파일네임:" + fileName); // UUID + 확장자 전까지의 이름

						String ext = ""; //파일 확장자
						ext = fileName.substring(fileName.lastIndexOf(".") + 1); // png 형태로됨
						LogUtil.println("확장자:" + ext);

						file.transferTo(new File(filePath, fileName)); //첨부파일 대기경로에 파일을 생성한다

						//파일 정보는 uploadMap에 담는다
						Map<String, String> uploadMap = new HashMap<String, String>();
						uploadMap.put("lgcl_file_nm", file.getOriginalFilename()); //논리파일명
						uploadMap.put("file_capa", String.valueOf(file.getSize())); //파일용량
						uploadMap.put("file_type", ext); //파일 확장자
						uploadMap.put("phys_file_nm", fileName); //물리 파일명
						uploadMap.put("phys_file_path", path); //물리 파일경로
						LogUtil.println("업로드 맵:" + uploadMap);

						uploadList.add(uploadMap);
					}
				}
			}
		}

		LogUtil.println("업로드 리스트:" + uploadList);
		LogUtil.println("[FileUtil - uploadFile] [END]");

		return uploadList;
	}

	/**
	 * 물리 파일 삭제
	 * 
	 * @param uploadPath
	 * @return
	 */
	public static void deleteUploadFile(String uploadPath) {
		File file = new File(uploadPath);
		LogUtil.println(file);

		//파일이 존재하면 해당 파일을 삭제
		if (file.exists()) {
			file.delete();
		}
	}
}