let addNewAnimalButton = document.getElementById("add-new-animal");
let addNewAnimalForm = document.getElementById("new-animal-form");


addNewAnimalButton.onclick = function (event){
    addNewAnimalForm.classList.toggle("hidden");
}


function sayHello(){
    console.log('hello there!');
}

function showEditFormForAnimal(id, name, age, isWild){
    console.log(id,name,age, isWild);
}

