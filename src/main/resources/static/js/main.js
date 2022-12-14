"use strict";

// selectors 

// -- Divs
let resultsDiv = document.querySelector("#result-div");

// -- Inputs
let catInput = document.querySelector("#categoryInput");
let nameInput = document.querySelector("#nameInput");
let compInput = document.querySelector("#completeInput");
let idInput = document.querySelector("#idInput");

// -- Modal Inputs
let catInputM = document.querySelector("#categoryInputM");
let nameInputM = document.querySelector("#nameInputM");
let compInputM = document.querySelector("#completeInputM");
// -- Buttons
let createBtn = document.querySelector("#createBtn");
let updateBtn = document.querySelector("#updateBtn");
let closeBtn = document.querySelector("#closeBtn");
let saveBtn = document.querySelector("#saveBtn");

// functions
let printResults = (result) => {
    let entryParent = document.createElement("div");
    entryParent.setAttribute("class", "entry-parent");

    let entryDiv = document.createElement("div");
    entryDiv.setAttribute("class", "entry-div");
    entryDiv.textContent = `${result.id} | ${result.category} | ${result.name} | ${result.complete} `;

    let delBtn = document.createElement("button");
    delBtn.textContent = "Delete";
    delBtn.type = "button";
    delBtn.setAttribute("Class", "btn btn-danger btn-sm");
    delBtn.setAttribute("onClick", `del(${result.id})`);


    let editBtn = document.createElement("button");
    editBtn.textContent = "Edit";
    editBtn.id = "button";
    editBtn.setAttribute("Class", 'btn btn-primary btn-sm');
    // editBtn.setAttribute("onClick", `edit(${result.id})`);
    editBtn.addEventListener("click", () => { modal.show() });


    let modal = new bootstrap.Modal(document.getElementById('editModal'), { keyboard: false });

    saveBtn.setAttribute("onClick", `edit(${result.id})`);



    entryParent.appendChild(entryDiv);
    entryParent.appendChild(delBtn);
    entryParent.appendChild(editBtn);
    resultsDiv.appendChild(entryParent);
}

let getAll = () => {
    axios.get("http://localhost:8080/item/getAll")
        .then(response => {
            resultsDiv.innerHTML = "";

            let results = response.data;

            for (let result of results) {
                printResults(result);
            }

        }).catch(error => { console.log(error); });

}

let create = () => {

    let obj = {
        "category": catInput.value,
        "name": nameInput.value,
        "complete": compInput.value
    }

    axios.post("http://localhost:8080/item/create", obj)
        .then(res => {
            getAll();
        }).catch(err => { console.log(err); })
}

let update = () => {

    let obj = {
        "category": catInput.value,
        "name": nameInput.value,
        "complete": compInput.value
    }

    axios.put(`http://localhost:8080/item/update/${idInput.value}`, obj)
        .then(res => {
            getAll();
        }).catch(err => { console.log(err); })
}
let edit = (id) => {

    let obj = {
        "category": catInputM.value,
        "name": nameInputM.value,
        "complete": compInputM.value
    }
    axios.put(`http://localhost:8080/item/update/${id}`, obj)
        .then(res => {
            getAll();
        }).catch(err => { console.log(err); })
}


let del = (id) => {

    axios.delete(`http://localhost:8080/item/delete/${id}`)
        .then(res => {
            getAll();
        }).catch(err => { console.log(err); })
}

// listeners 
createBtn.addEventListener("click", create);
updateBtn.addEventListener("click", update);
saveBtn.addEventListener("click", `edit(${result.id})`);
