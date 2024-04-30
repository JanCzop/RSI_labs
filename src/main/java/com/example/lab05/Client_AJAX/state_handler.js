let currentAction = 'READ';
let listed = true;
let selectedButtonId = null;

function setAction(action) {
    currentAction = action;
    updateFields();

}


function updateFields() {
    const fields = document.querySelectorAll('#carForm input, #carForm select');
    fields.forEach(field => {
        if (currentAction === 'READ' || currentAction === 'DELETE' || currentAction === 'DAMAGE' || currentAction === 'REPAIR') {
            field.style.display = 'none';
        } else {
            field.style.display = 'block';
        }
    });

    document.getElementById('carId').style.display = 'block';

}

function saveChanges() {
    if (currentAction === 'CREATE') createCar();
    else if (currentAction === 'UPDATE') updateCar();
     else if (currentAction === 'READ') getCar();
    else if (currentAction === 'DELETE') deleteCar();
    else if (currentAction === 'REPAIR') repairCar();
    else if (currentAction === 'DAMAGE') damageCar();
    else console.warn('No action specified for Save button.');
}

function updateMessages(message,isError) {
    const messagesSection = document.getElementById('messagesSection');
    if (!messagesSection) {
        console.error('Messages section element not found.');
        return;
    }
    messagesSection.textContent = message;
    messagesSection.style.color = isError ? 'red' : 'green';
}


updateFields();
