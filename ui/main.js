const todoList = document.querySelector('.todo-list');
const addTodoForm = document.querySelector('.add-todo-form');
const titleValue = document.getElementById('title-value');
const descriptionValue = document.getElementById('description-value');
const statusValue = document.getElementById('status-value');

let output = '';
const url = 'http://localhost:8080/api/todos';

//GET todos
fetch(url)
.then(res => res.json())
.then(data => {
  data.forEach( item => {
    output += `
    <div class="card mt-4 col-md-6 bg-light" >
      <div class="card-body">
        <h5 class="card-title">${item.title}</h5>
        <h6 class="card-subtitle mb-2 text-muted">${item.status}</h6>
        <p class="card-text">${item.description}</p>
        <a href="#" class="card-link">Edit</a>
        <a href="#" class="card-link">Delete</a>
      </div>
    </div>
    `;
    todoList.innerHTML = output;
  });
})

//POST todo
addTodoForm.addEventListener('submit', (e) => {
  e.preventDefault();
  console.log(titleValue.value);

  fetch(url, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*'
    },
    body: JSON. stringify({
      title: titleValue.value,
      description: descriptionValue.value,
      status: statusValue.value
    })
  })
  .then(res => res.json())
})
