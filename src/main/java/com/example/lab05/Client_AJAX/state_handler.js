let currentAction = 'READ';
let listed = true;

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

    const oldIdField = document.getElementById('oldCarId');
    if (oldIdField) {
        oldIdField.style.display = currentAction === 'UPDATE' ? 'block' : 'none';
    } else if (currentAction === 'UPDATE') {
        const carForm = document.getElementById('carForm');

        const oldIdField = document.createElement('input');
        oldIdField.type = 'number';
        oldIdField.id = 'oldCarId';
        oldIdField.name = 'oldCarId';
        oldIdField.placeholder = 'Old ID';
        carForm.insertBefore(oldIdField, document.getElementById('carId'));
    }
}

function saveChanges() {
    if (currentAction === 'CREATE') {
        // Do something for create action
    } else if (currentAction === 'UPDATE') {
        // Do something for update action
    } else if (currentAction === 'READ') {
        const id = document.getElementById('carId').value;
        getCar(id);
    } else {
        console.warn('No action specified for Save button.');
    }
}

function updateMessages(message,isError) {
    const messagesSection = document.getElementById('messagesSection');
    if (!messagesSection) {
        console.error('Messages section element not found.');
        return;
    }
    messagesSection.textContent = message;
    messagesSection.style.color = isError ? 'red' : 'black';
}


updateFields();
