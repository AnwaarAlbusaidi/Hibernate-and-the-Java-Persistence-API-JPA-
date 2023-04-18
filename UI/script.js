const studentCreateForm = document.querySelector('#student-form');
const studentUpdateForm = document.querySelector('#student-update-form')
const deleteForm = document.querySelector('#student-form-delete');
const getForm = document.querySelector('#student-form-get'); 
const studentFormContainer = document.querySelector('#student-form-container');
const createBtn = document.querySelector('#create-btn');
const getBtn = document.querySelector('#get-btn');
const getStudentBtn = document.querySelector('#get-btn-student');
const updateBtn = document.querySelector('#update-btn');
const deleteBtn = document.querySelector('#delete-btn');
const studentTable = document.querySelector('#student-table tbody');
const studentGetTable = document.querySelector('#student-table-get tbody'); 
const studentIdInput = document.querySelector("#student-id");
const studentIdLabel = document.querySelector("#student-id-label");
 // retrieve the stored username and password from the localStorage
   const storedUsername = localStorage.getItem('username');
   const storedPassword = localStorage.getItem('password');
//-------------------------------create------------------------------------------------
createBtn.addEventListener('click', () => {
    studentCreateForm.style.display = 'block';
    studentTable.parentElement.style.display = 'none';
    studentUpdateForm.style.display = 'none';
    deleteForm.style.display = 'none';
    getForm.style.display = 'none';
    studentGetTable.parentElement.style.display = 'none';
});
studentCreateForm.addEventListener('submit', (event) => {
  event.preventDefault();

  const studentName = document.querySelector('#student-name').value;
  const studentEmail = document.querySelector('#student-email').value;
  const studentImage = document.querySelector('#student-image').files[0];

  let currentForm = new FormData();
  currentForm.append("name",studentName);
  currentForm.append("email",studentEmail);
  currentForm.append("image",studentImage);

  fetch('http://localhost:8080/api/student/withImage', {
    method: 'POST',
    headers: {
    'Authorization': 'Basic ' + btoa(storedUsername + ":" + storedPassword)},
    body: currentForm
  })
  .then(response => {
    if (response.ok) {
      alert('Student added successfully');
      studentCreateForm.reset();
    } else {
      alert('An error occurred while adding the student');
    }
    return response.json();
  })
  .then(data => console.log(data))
  .catch(error => console.error(error));
});
//-------------------------------Get----------------------------------------------------
getBtn.addEventListener('click', () => {
    studentCreateForm.style.display = 'none';
    deleteForm.style.display = 'none';
    studentUpdateForm.style.display = 'none';
    getForm.style.display = 'none';
    studentGetTable.parentElement.style.display = 'none';
    studentTable.innerHTML = ''; // clear existing table rows
  
    // retrieve the stored username and password from the localStorage
    const storedUsername = localStorage.getItem('username');
    const storedPassword = localStorage.getItem('password');
  
    fetch('http://localhost:8080/api/student', {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Basic ' + btoa(storedUsername + ":" + storedPassword)
      }
    })
      .then(response => response.json())
      .then(data => {
        // iterate over students and add them to the table
        data.forEach(student => {
          const row = document.createElement('tr');
          const idCell = document.createElement('td');
          const nameCell = document.createElement('td');
          const emailCell = document.createElement('td');
          idCell.textContent = student.id;
          nameCell.textContent = student.name;
          emailCell.textContent = student.email;
          row.appendChild(idCell);
          row.appendChild(nameCell);
          row.appendChild(emailCell);
          studentTable.appendChild(row);
        });
        // display the table
        studentTable.parentElement.style.display = 'block';
      })
      .catch(error => console.error(error));
  });
  
//-------------------------------Get Specific Student ----------------------------------------------------
getStudentBtn.addEventListener('click', () => {
  studentCreateForm.style.display = 'none';
  studentTable.parentElement.style.display = 'none';
  studentUpdateForm.style.display = 'none';
  deleteForm.style.display = 'none';
  getForm.style.display = 'block';
  studentGetTable.innerHTML = ''; // clear existing table rows
});

getForm.addEventListener('submit', async (event) => { // Updated to use async function for better error handling
  event.preventDefault(); // prevent form submission
  const studentID = document.querySelector('#studentid').value;

  try {
    // Perform API request to retrieve student details based on student ID
    const response = await fetch(`http://localhost:8080/api/student/${studentID}`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Basic ' + btoa(storedUsername + ":" + storedPassword)
      }
    });
    if (response.ok) {
      const student = await response.json();

      // Handle the response and update the table with student details
      const idCell = document.createElement('td');
      idCell.textContent = student.id;
      const nameCell = document.createElement('td');
      nameCell.textContent = student.name;
      const emailCell = document.createElement('td');
      emailCell.textContent = student.email;

      // Create table row and append cells
      const row = document.createElement('tr');
      row.appendChild(idCell);
      row.appendChild(nameCell);
      row.appendChild(emailCell);
      studentGetTable.appendChild(row);
      studentGetTable.parentElement.style.display = 'block';

      const requestConfig = {
        method: "GET",
        headers: {
          'Authorization': 'Basic ' + btoa(storedUsername + ":" + storedPassword)
        }
      }

      // Perform API request to retrieve student image based on student ID
      const imageResponse = await fetch(`http://localhost:8080/api/student/withImage/${studentID}`, requestConfig);
      if (imageResponse.ok) {
        const blobResponse = await imageResponse.blob();
        const imageURL = URL.createObjectURL(blobResponse);
        const imageElem = document.createElement("img");
        imageElem.src = imageURL;
        const imageCell = document.createElement('td');
        imageElem.width = 100; // Set width for image element
        imageElem.height = 100; // Set height for image element
        imageElem.style.borderRadius = '50%'; // apply border-radius for circular shape
        imageCell.appendChild(imageElem);
        row.appendChild(imageCell);
        studentGetTable.appendChild(row);
        studentGetTable.parentElement.style.display = 'block';

        console.log("Success!");
      } else {
        console.error('Failed to fetch student image');
      }
    } else {
      console.error('Failed to fetch student details');
    }
  } catch (error) {
    console.error(error);
  }
});

//------------------------------update----------------------------------------------------
updateBtn.addEventListener('click', () => {
  studentTable.parentElement.style.display = 'none';
  studentCreateForm.style.display = 'none';
  deleteForm.style.display = 'none';
  studentUpdateForm.style.display = 'block';
  getForm.style.display = 'none';
  studentGetTable.parentElement.style.display = 'none';
});
// Set the form's submit event listener
studentUpdateForm.addEventListener('submit', (event) => {
  event.preventDefault();  
  // Get the student ID, name, and email from the form
  const studentid = document.querySelector('#student-id').value;
  const studentupdatedName = document.querySelector('#studentUpdatedName').value;
  const studentUpdatedEmail = document.querySelector('#studentUpdatedEmail').value;

  // Call the API's update function with the new values
  fetch(`http://localhost:8080/api/student/${studentid}`, {
    method: 'PUT',
     headers: { 'Content-Type': 'application/json' ,
    'Authorization': 'Basic ' + btoa(storedUsername + ":" + storedPassword)},
    body: JSON.stringify({
      name: studentupdatedName,
      email: studentUpdatedEmail
    })
  })
    .then(response => response.json())
        .then(data => console.log(data))
        .catch(error => console.error(error));

       alert("Student updated scussfully");
       studentUpdateForm.reset();
    });
//----------------------------Delete------------------------------------------------------
deleteBtn.addEventListener('click', () => {
    studentCreateForm.style.display = 'none';
    studentTable.parentElement.style.display = 'none';
    studentUpdateForm.style.display = 'none';
    deleteForm.style.display = 'block';
    getForm.style.display = 'none';
    studentGetTable.parentElement.style.display = 'none';
});

deleteForm.addEventListener('submit', (event) => { 
    event.preventDefault();  

    const id = document.querySelector('#student_ID').value;

    const confirmDelete = confirm(`Are you sure you want to delete student with ID ${id}?`);


    if (confirmDelete) {
      fetch(`http://localhost:8080/api/student/${id}`, {
        method: 'DELETE',
    headers: {'Content-Type': 'application/json',
             'Authorization': 'Basic ' + btoa(storedUsername + ":" + storedPassword)},
      })
        .then(response => {
          if (response.ok) {
            alert('Student deleted successfully');
            deleteForm.reset();
          } else {
            throw new Error('Failed to delete Student');
          }
        })
        .catch(error => console.error(error));
    }
});
  

  