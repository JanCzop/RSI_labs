function repairCar() {
    const carId = document.getElementById('carId').value;

    fetch(`http://localhost:8080/cars/${carId}/repair`, {
        method: 'PATCH'
    })
        .then(response => {
            if (response.ok) {
                updateMessages(`Car with ID ${carId} repaired successfully`, false);
                if(!listed) {fetchAndGenerateTable();}
                else toggleListButton();
            } else if (response.status === 409) {
                updateMessages(`Car with ID ${carId} cannot be repaired because it is already undamaged`, true);
            } else if (response.status === 404) {
                updateMessages(`Car with ID ${carId} not found`, true);
            } else {
                throw new Error('Network response was not ok');
            }
        })
        .catch(error => {
            if (error.message === 'Network response was not ok') {
                updateMessages(`Error repairing car with ID ${carId}`, true);
            } else {
                console.error('Error repairing car:', error);
            }
        });
}