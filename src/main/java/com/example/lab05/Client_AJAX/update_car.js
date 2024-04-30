function updateCar(){
    const carId = document.getElementById('carId').value;
    const carModel = document.getElementById('carModel').value;
    const carBrand = document.getElementById('carBrand').value;
    const carProductionYear = document.getElementById('carProductionYear').value;
    const carStatus = document.getElementById('carStatus').value;

    fetch(`http://localhost:8080/cars/${carId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: carId,
            model: carModel,
            brand: carBrand,
            production_year: carProductionYear,
            status: carStatus
        })
    })
        .then(response => {
            if (response.ok) {
                updateMessages(`Car with ID ${carId} updated successfully`, false);
                if(!listed) {fetchAndGenerateTable();}
                else toggleListButton();            } else if (response.status === 404) {
                updateMessages(`Car with ID ${carId} not found`, true);
            } else {
                throw new Error('Network response was not ok');
            }
        })
        .catch(error => {
            if (error.message === 'Network response was not ok') {
                updateMessages(`Error updating car with ID ${carId}`, true);
            } else {
                console.error('Error updating car:', error);
            }
        });
}