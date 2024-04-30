function createCar() {
    const carId = document.getElementById('carId').value;
    const carModel = document.getElementById('carModel').value;
    const carBrand = document.getElementById('carBrand').value;
    const carProductionYear = document.getElementById('carProductionYear').value;
    const carStatus = document.getElementById('carStatus').value;

    const carData = {
        id: carId,
        model: carModel,
        brand: carBrand,
        production_year: carProductionYear,
        status: carStatus
    };

    fetch('http://localhost:8080/cars', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(carData)
    })
        .then(response => {
            if (!response.ok) {
                if (response.status === 400) {
                    throw new Error(`Error adding car with ID ${carId}`);
                } else if (response.status === 409) {
                    throw new Error(`Error adding car - car ${carId} already exists`);
                } else {
                    throw new Error('Network response was not ok');
                }
            }
            return response.json();
        })
        .then(data => {
            updateMessages(`Car with ID ${carId} added successfully`, false);
            generateTableRow(data);
        })
        .catch(error => {
            if (error.message === 'Network response was not ok') {
                updateMessages(`Error adding car with ID ${carId}`, true);
            } else {
                console.error('Error adding car:', error);
            }
        });
}
