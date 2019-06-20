package logos.lesson19.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import logos.lesson19.domain.Student;
import logos.lesson19.repository.StudentRepository;


@Service
public class StudentService {

	
	@Autowired
	private StudentRepository studentRepository;
	
	public Student storeStudent(String firstName, String lastName, Integer age, MultipartFile file) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		Student multipart = null;

		if (!fileName.contains("..")) {
			multipart = new Student(firstName, lastName, age, fileName, file.getContentType(), file.getBytes());
		}

		return studentRepository.save(multipart);
	}

	public Student getStudent(String fileId) throws FileNotFoundException {
		return studentRepository.findById(fileId)
				.orElseThrow(() -> new FileNotFoundException("File not found with Id =" + fileId));
	}
}
