var RegisterForm = document.querySelector('#RegisterForm');
var FileUploadInput = document.querySelector('#FileUploadInput');
var FileUploadError = document.querySelector('#UploadError');
var FileUploadSuccess = document
		.querySelector('#UploadSuccess');


function uploadFile(file) {
	var FirstName = document.getElementById('FirstName').value;
	var LastName = document.getElementById('LastName').value;
	var Age = document.getElementById('Age').value;
		
	var formData = new FormData();
	formData.append("file", file);
	formData.append("firstName", FirstName);
	formData.append("lastName", LastName);
	formData.append("age", Age);

	var xhr = new XMLHttpRequest();
	xhr.open("POST", "/uploadStudent");

	xhr.onload = function() {
		console.log(xhr.responseText);
		var response = JSON.parse(xhr.responseText);
		if (xhr.status == 200) {
			

			FileUploadError.style.display = "none";
			
			FileUploadSuccess.innerHTML = "<p>Student Register Successfully. <br>" +
					"Student: " + response.firstName + " " + response.lastName + " <br> Age: " + response.age + 
					"<br> Photo: <br> <img src=" + response.fileDownloadUri + " alt='Student photo' height='230' width='230'></p>";
			FileUploadSuccess.style.display = "block";
			
			
			
		} else {
			FileUploadSuccess.style.display = "none";
			FileUploadError.innerHTML = (response && response.message)
					|| "Some Error Occurred";
		}
	}

	xhr.send(formData);
}



RegisterForm.addEventListener('submit', function(event) {
	var files = FileUploadInput.files;
	
	if (files.length === 0) {
		FileUploadError.innerHTML = "Please select a file";
		FileUploadError.style.display = "block";
	}
	
	uploadFile(files[0]);
	event.preventDefault();
}, true);

