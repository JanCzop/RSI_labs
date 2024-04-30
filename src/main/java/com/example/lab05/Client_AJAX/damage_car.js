function damageCar() {
    const carId = document.getElementById('carId').value;

    fetch(`http://localhost:8080/cars/${carId}/damage`, {
        method: 'PATCH'
    })
        .then(response => {
            if (response.ok) {
                updateMessages(`Car with ID ${carId} damaged successfully`, false);
                if(!listed) {fetchAndGenerateTable();}
                else toggleListButton();
            } else if (response.status === 409) {
                updateMessages(`Car with ID ${carId} cannot be damaged because it is already damaged`, true);
            } else if (response.status === 404) {
                updateMessages(`Car with ID ${carId} not found`, true);
            } else {
                throw new Error('Network response was not ok');
            }
        })
        .catch(error => {
            if (error.message === 'Network response was not ok') {
                updateMessages(`Error damaging car with ID ${carId}`, true);
            } else {
                console.error('Error damaging car:', error);
            }
        });
}