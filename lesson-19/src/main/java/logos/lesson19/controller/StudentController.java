package logos.lesson19.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import logos.lesson19.dto.StudentUploadResponse;
import logos.lesson19.service.StudentService;
import logos.lesson19.domain.Student;

@RestController
public class StudentController {

	@Autowired
	StudentService StudentService;

	@PostMapping("/uploadStudent")
	public StudentUploadResponse uploadStudent(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			@RequestParam("age") String age, @RequestParam("file") MultipartFile file) throws IOException {
		Student student = StudentService.storeStudent(firstName, lastName, Integer.parseInt(age), file);
		
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadStudent/")
				.path(student.getId()).toUriString();

		System.out.println(fileDownloadUri);
		
		return new StudentUploadResponse(student.getFirstName(), student.getLastName(), student.getAge(), 
				student.getFileName(), fileDownloadUri, file.getContentType(), file.getSize());
	}

	@GetMapping("/downloadStudent/{fileId}")
	public ResponseEntity<Resource> downlaodFile(@PathVariable String fileId) throws FileNotFoundException {
		Student student = StudentService.getStudent(fileId);

		return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(student.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + student.getFileName()   + "\"" )
                .body( new ByteArrayResource(student.getData()));
	}

//	@PostMapping("/uploadMultipleFiles")
//	public List<MultipartUploadResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
//		return Arrays.asList(files).stream().map(file -> {
//			try {
//				return uploadFile(file);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			return null;
//		}).collect(Collectors.toList());
//	}
	
}
