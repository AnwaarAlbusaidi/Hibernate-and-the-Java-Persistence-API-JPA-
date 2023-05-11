const TeacherCreateForm = document.querySelector('#Teacher-form');
const TeacherUpdateForm = document.querySelector('#Teacher-update-form')
const deleteForm = document.querySelector('#Teacher-form-delete');
const createBtn = document.querySelector('#create-btn');
const getBtn = document.querySelector('#get-btn');
const updateBtn = document.querySelector('#update-btn');
const deleteBtn = document.querySelector('#delete-btn');
const TeacherTable = document.querySelector('#Teacher-table tbody');
const TeacherIdInput = document.querySelector("#Teacher-id");
const TeacherIdLabel = document.querySelector("#Teacher-id-label");
 // retrieve the stored username and password from the localStorage
 const storedUsername = localStorage.getItem('username');
 const storedPassword = localStorage.getItem('password');
//-------------------------------create------------------------------------------------
createBtn.addEventListener('click', () => {
    TeacherCreateForm.style.display = 'block';
    TeacherTable.parentElement.style.display = 'none';
    TeacherUpdateForm.style.display = 'none';
    deleteForm.style.display = 'none';
});

TeacherCreateForm.addEventListener('submit', (event) => {
  event.preventDefault();

  const TeacherName = document.querySelector('#Teacher-name').value;
  const TeacherEmail = document.querySelector('#Teacher-email').value;
  const TeacherSalary = document.querySelector('#Teacher-Salary').value;

  fetch('http://localhost:8080/api/teacher', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json', 
    'Authorization': 'Basic ' + btoa(storedUsername + ":" + storedPassword)},
    body: JSON.stringify({
      name: TeacherName,
      email: TeacherEmail,
      salary: TeacherSalary
    })
  })
  .then(response => {
    if (response.ok) {
      alert('Teacher added successfully');
      TeacherCreateForm.reset();
    } else {
      alert('An error occurred while adding the Teacher');
    }
    return response.json();
  })
  .then(data => console.log(data))
  .catch(error => console.error(error));
});
//-------------------------------Get----------------------------------------------------
getBtn.addEventListener('click', () => {
  TeacherCreateForm.style.display = 'none';
  TeacherUpdateForm.style.display = 'none';
  deleteForm.style.display = 'none';
  TeacherTable.innerHTML = ''; // clear existing table rows
  fetch('http://localhost:8080/api/teacher', {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': 'Basic ' + btoa(storedUsername + ":" + storedPassword)
    }
  })
    .then(response => response.json())
    .then(data => {
      // iterate over students and add them to the table
      data.forEach(Teacher => {
        const row = document.createElement('tr');
        const idCell = document.createElement('td');
        const nameCell = document.createElement('td');
        const emailCell = document.createElement('td');
        const salaryCell = document.createElement('td');
        idCell.textContent = Teacher.id;
        nameCell.textContent = Teacher.name;
        emailCell.textContent = Teacher.email;
        salaryCell.textContent = Teacher.salary;
        row.appendChild(idCell);
        row.appendChild(nameCell);
        row.appendChild(emailCell);
        row.append(salaryCell);
        TeacherTable.appendChild(row);
      });
      // display the table
      TeacherTable.parentElement.style.display = 'block';
    })
    .catch(error => console.error(error));
}); 

//------------------------------update----------------------------------------------------
updateBtn.addEventListener('click', () => {
  TeacherTable.parentElement.style.display = 'none';
  TeacherCreateForm.style.display = 'none';
  deleteForm.style.display = 'none';
  TeacherUpdateForm.style.display = 'block';
});

// Set the form's submit event listener
TeacherUpdateForm.addEventListener('submit', (event) => {
  event.preventDefault();  
  // Get the student ID, name, and email from the form
  const Teacherid = document.querySelector('#Teacher-id').value;
  const TeacherupdatedName = document.querySelector('#TeacherUpdatedName').value;
  const TeacherUpdatedEmail = document.querySelector('#TeacherUpdatedEmail').value;
  const TeacherUpdatedSalary = document.querySelector('#TeacherUpdatedSalary').value;

  // Call the API's update function with the new values
  fetch(`http://localhost:8080/api/teacher/${Teacherid}`, {
    method: 'PUT',
    headers: {'Content-Type': 'application/json',
    'Authorization': 'Basic ' + btoa(storedUsername + ":" + storedPassword)},
    body: JSON.stringify({
      name: TeacherupdatedName,
      email: TeacherUpdatedEmail,
      salary: TeacherUpdatedSalary
    })
  })
    .then(response => response.json())
        .then(data => console.log(data))
        .catch(error => console.error(error));

       alert("Staff updated scussfully");
       TeacherUpdateForm.reset();
    });
//----------------------------Delete------------------------------------------------------
deleteBtn.addEventListener('click', () => {
  TeacherTable.parentElement.style.display = 'none';
  TeacherCreateForm.style.display = 'none';
  deleteForm.style.display = 'block';
  TeacherUpdateForm.style.display = 'none';
});

deleteForm.addEventListener('submit', (event) => { 
    event.preventDefault();  

    const teacherId = document.querySelector('#TeacherID').value;

    const confirmDelete = confirm(`Are you sure you want to delete student with ID ${teacherId}?`);
    if (confirmDelete) {
      fetch(`http://localhost:8080/api/teacher/${teacherId}`, {
        method: 'DELETE',
    headers: {'Content-Type': 'application/json',
             'Authorization': 'Basic ' + btoa(storedUsername + ":" + storedPassword)},
      })
        .then(response => {
          if (response.ok) {
            alert('Teacher deleted successfully');
            deleteForm.reset();
          } else {
            throw new Error('Failed to delete Teacher');
          }
        })
        .catch(error => console.error(error));
    }
});
  

  
  

  