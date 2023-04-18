const CourseCreateForm = document.querySelector('#Course-form');
const CourseUpdateForm = document.querySelector('#Course-update-form')
const deleteForm = document.querySelector('#Course-form-delete');
const assignForm = document.querySelector('#assign-Course-to-teacher');
const createBtn = document.querySelector('#create-btn');
const getBtn = document.querySelector('#get-btn');
const assignBtn = document.querySelector('#assign-btn');
const updateBtn = document.querySelector('#update-btn');
const deleteBtn = document.querySelector('#delete-btn');
const CourseTable = document.querySelector('#Course-table tbody');
const CourseIdInput = document.querySelector("#Course-id");
const CourseIdLabel = document.querySelector("#Course-id-label");
 // retrieve the stored username and password from the localStorage
 const storedUsername = localStorage.getItem('username');
 const storedPassword = localStorage.getItem('password');
 
 //----------------------------------GET-------------------------------------------------------
// Add event listener for createBtn click event
createBtn.addEventListener('click', () => {
  // Set display styles for forms
  CourseCreateForm.style.display = 'block';
  CourseTable.parentElement.style.display = 'none';
  CourseUpdateForm.style.display = 'none';
  deleteForm.style.display = 'none';
  assignForm.style.display = 'none';
});

// Add event listener for CourseCreateForm submit event
CourseCreateForm.addEventListener('submit', (event) => {
  event.preventDefault();

  // Get input value from CourseName field
  const CourseName = document.querySelector('#Course-name').value;

  // Fetch POST request to add a new course
  fetch('http://localhost:8080/api/courses', {
    method: 'POST',
    headers: { 
      'Content-Type': 'application/json', 
      'Authorization': 'Basic ' + btoa(storedUsername + ":" + storedPassword)
    },
    body: JSON.stringify({
      name: CourseName
    })
  })
  .then(response => {
    if (response.ok) {
      alert('Course added successfully');
      CourseCreateForm.reset(); // Reset form fields
    } else {
      alert('An error occurred while adding the Course');
    }
  })
  .catch(error => console.error(error));
});

// Add event listener for getBtn click event
getBtn.addEventListener('click', () => {
  // Set display styles for forms
  CourseCreateForm.style.display = 'none';
  CourseUpdateForm.style.display = 'none';
  deleteForm.style.display = 'none';
  assignForm.style.display = 'none';
  CourseTable.innerHTML = ''; // Clear existing table rows

  // Fetch GET request to retrieve courses data
  fetch('http://localhost:8080/api/courses', {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': 'Basic ' + btoa(storedUsername + ":" + storedPassword)
    }
  })
    .then(response => response.json())
    .then(data => {
      // Iterate over courses and add them to the table
      data.forEach(course => {
        const row = document.createElement('tr');
        const idCell = document.createElement('td');
        const nameCell = document.createElement('td');
        const teacherCell = document.createElement('td'); // Changed variable name to teacherCell

        idCell.textContent = course.courseID;
        nameCell.textContent = course.name;
       
        if(course.teacher_id){
          // Fetch GET request to retrieve teacher data for the course
          fetch(`http://localhost:8080/api/teacher/${course.teacher_id}`, {
            method: 'GET',
            headers: {
              'Content-Type': 'application/json',
              'Authorization': 'Basic ' + btoa(storedUsername + ":" + storedPassword)
            }
          })
          .then(response => {
            if (!response.ok) {
              throw new Error('Failed to fetch teacher data');
            }
            return response.json();
          })
          .then(data => {
            teacherCell.textContent = data.name;
          })
          .catch(error => console.error(error));
        }
        else
        {
          teacherCell.textContent = 'Not assign';
        }
        row.appendChild(idCell);
        row.appendChild(nameCell);
        row.appendChild(teacherCell);

        CourseTable.appendChild(row);
      });
      // Display the table
      CourseTable.parentElement.style.display = 'block';
    })
    .catch(error => console.error(error));
});


//------------------------------update----------------------------------------------------
updateBtn.addEventListener('click', () => {
    CourseTable.parentElement.style.display = 'none';
    CourseCreateForm.style.display = 'none';
    deleteForm.style.display = 'none';
    CourseUpdateForm.style.display = 'block';
    assignForm.style.display = 'none';
  });
// Set the form's submit event listener
CourseUpdateForm.addEventListener('submit', (event) => {
  event.preventDefault();  
  // Get the student ID, name, and email from the form
  const Courseid = document.querySelector('#Course-id').value;
  const CourseUpdatedName = document.querySelector('#CourseUpdatedName').value;

  // Call the API's update function with the new values
  fetch(`http://localhost:8080/api/courses/${Courseid}`, {
    method: 'PUT',
    headers: {'Content-Type': 'application/json',
    'Authorization': 'Basic ' + btoa(storedUsername + ":" + storedPassword)},
    body: JSON.stringify({
      name: CourseUpdatedName
    })
  })
    .then(response => response.json())
        .then(data => console.log(data))
        .catch(error => console.error(error));

       alert("Course updated scussfully");
       CourseUpdateForm.reset();
    });

  //----------------------------Delete------------------------------------------------------
deleteBtn.addEventListener('click', () => {
    CourseTable.parentElement.style.display = 'none';
    CourseCreateForm.style.display = 'none';
    deleteForm.style.display = 'block';
    CourseUpdateForm.style.display = 'none';
    assignForm.style.display = 'none';
  });

  deleteForm.addEventListener('submit', (event) => { 
    event.preventDefault();  

    const Courseid = document.querySelector('#CourseID').value;

    const confirmDelete = confirm(`Are you sure you want to delete Course with ID ${Courseid}?`);
    if (confirmDelete) {
      fetch(`http://localhost:8080/api/courses/${Courseid}`, {
        method: 'DELETE',
    headers: {'Content-Type': 'application/json',
             'Authorization': 'Basic ' + btoa(storedUsername + ":" + storedPassword)},
      })
        .then(response => {
          if (response.ok) {
            alert('Course deleted successfully');
            deleteForm.reset();
          } else {
            throw new Error('Failed to delete Course');
          }
        })
        .catch(error => console.error(error));
    }
});
//-----------------------------assign---------------------------------------------------------------------------
assignBtn.addEventListener('click', () => {
  CourseCreateForm.style.display = 'none';
  CourseTable.parentElement.style.display = 'none';
  CourseUpdateForm.style.display = 'none';
  deleteForm.style.display = 'none';
  assignForm.style.display = 'block';
});

assignForm.addEventListener('submit', (event) => {
event.preventDefault();

const CourseID = document.querySelector('#Courseid').value;
const TeacherID = document.querySelector('#Teacherid').value;

fetch('http://localhost:8080/api/assigner', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json', 
  'Authorization': 'Basic ' + btoa(storedUsername + ":" + storedPassword)},
  body: JSON.stringify({
    course_id: CourseID, 
    teacher_id: TeacherID 
  })
})
.then(response => {
  if (response.ok) {
    alert('Teacher assigned to course successfully');
    TeacherCreateForm.reset();
  } else {
    alert('An error occurred while assigning the Teacher to course');
  }
  return response.json();
})
.then(data => console.log(data))
.catch(error => console.error(error));
});
